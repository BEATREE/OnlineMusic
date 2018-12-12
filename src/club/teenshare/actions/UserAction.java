package club.teenshare.actions;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;

import club.teenshare.beans.Comments;
import club.teenshare.beans.Song;
import club.teenshare.beans.User;
import club.teenshare.dao.CommentsDAO;
import club.teenshare.dao.HibernateSessionFactory;
import club.teenshare.dao.SongDAO;
import club.teenshare.dao.UserDAO;

public class UserAction extends ActionSupport {
	
	HttpSession httpSession = ServletActionContext.getRequest().getSession();
	
	public String initUser() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		User currentUser = (User) httpSession.getAttribute("currentUser");
		if(currentUser == null){
			addFieldError("notLogin", "您还未登录或登录已过期");
			return ERROR;
		}
		try {
			Transaction ts = session.beginTransaction();
			
			String searchHql = "from Song s where s.user=? order by s.uptime desc";
			String searchHql2 = "from Comments c where c.user=? order by c.ctime desc";
			
			Query query = session.createQuery(searchHql);
			//传入查询条件参数
			query.setParameter(0, currentUser);
			List<Song> mySongs = query.list();
			
			Query query2 = session.createQuery(searchHql2);
			//传入查询条件参数
			query2.setParameter(0, currentUser);
			List<Comments> myComments = query2.list();
			ts.commit();
			session.close();
			httpSession.removeAttribute("mySongs");//去除掉旧的session存储
			httpSession.removeAttribute("myComments");//去除掉旧的session存储
			httpSession.setAttribute("myComments", myComments);
			httpSession.setAttribute("mySongs", mySongs);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			addFieldError("initError", "后台信息初始化失败！");
			return ERROR;
		}
	}
	
	//创建关于用户显示个人上传信息的action
	public String ShowMySongsUser() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		User currentUser = (User) httpSession.getAttribute("currentUser");
		if(currentUser == null){
			addFieldError("notLogin", "您还未登录或登录已过期");
			return ERROR;
		}
		try {
			Transaction ts = session.beginTransaction();
			String searchHql = "from Song s where s.user=? order by s.uptime desc";
			Query query = session.createQuery(searchHql);
			//传入查询条件参数
			query.setParameter(0, currentUser);
			List<Song> mySongs = query.list();
			ts.commit();
			httpSession.removeAttribute("mySongs");//去除掉旧的session存储
			httpSession.setAttribute("mySongs", mySongs);
			//关闭掉Session
			session.close();
			return "mySongs";
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			addFieldError("userError", "个人上传记录查询失败，待会儿再试啊~");
			return ERROR;
		}
	}
	
	//创建关于用户显示个人评论信息的action
	public String ShowMyCommentsUser() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		User currentUser = (User) httpSession.getAttribute("currentUser");
		if(currentUser == null){
			addFieldError("notLogin", "您还未登录或登录已过期");
			return ERROR;
		}
		try {
			Transaction ts = session.beginTransaction();
			String searchHql = "from Comments c where c.user=? order by c.ctime desc";
			Query query = session.createQuery(searchHql);
			//传入查询条件参数
			query.setParameter(0, currentUser);
			List<Comments> myComments = query.list();
			ts.commit();
			httpSession.removeAttribute("myComments");//去除掉旧的session存储
			httpSession.setAttribute("myComments", myComments);
			//关闭掉Session
			session.close();
			return "myComments";
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			addFieldError("userError", "个人上传记录查询失败，待会儿再试啊~");
			return ERROR;
		}
	}
	//用户删除上传的歌曲
	public String delMysongsUser() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		String sid = ServletActionContext.getRequest().getParameter("sid");
		try {
			Transaction ts = session.beginTransaction();
			SongDAO sdao = new SongDAO();
			Song s = sdao.findById(sid);	//按照id寻找歌曲
			sdao.delete(s);					//对查找到的歌曲进行删除
			ts.commit();
			session.close();
			return "delSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			return ERROR;
		}
	}
	//用户删除上传的歌曲
	public String delMycommentsUser() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		String cid = ServletActionContext.getRequest().getParameter("cid");
		try {
			Transaction ts = session.beginTransaction();
			CommentsDAO cdao = new CommentsDAO();
			Comments c = cdao.findById(cid);	//按照id寻找评论
			cdao.delete(c);					//对查找到的评论进行删除
			ts.commit();
			session.close();
			return "delComSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			return ERROR;
		}
	}
	//重新编辑歌曲信息
	public String reEditMysongsUser() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		String sid = ServletActionContext.getRequest().getParameter("sid");
		try {
			Transaction ts = session.beginTransaction();
			SongDAO sdao = new SongDAO();
			Song s = sdao.findById(sid);	//按照id寻找歌曲
			ts.commit();
			session.close();
			httpSession.setAttribute("reEditSong", s);
			return "reEditSong";
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			return ERROR;
		}
	}
	//用户退出登录
	public String outLoginUser() throws Exception{
		httpSession.removeAttribute("currentUser");
		return "logoutSuccess";
	}
	//用户注销帐号
	public String delUser() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		String uid = ServletActionContext.getRequest().getParameter("uid");
		try {
			Transaction ts = session.beginTransaction();
			UserDAO udao = new UserDAO();
			User u = udao.findById(uid);	//按照id寻找对应用户
			udao.delete(u);					//对用户进行删除
			ts.commit();
			session.close();
			return "logoutSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			return ERROR;
		}
	}
}
