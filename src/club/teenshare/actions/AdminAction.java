package club.teenshare.actions;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;

import club.teenshare.beans.Admin;
import club.teenshare.beans.Comments;
import club.teenshare.beans.Playlist;
import club.teenshare.beans.Plsong;
import club.teenshare.beans.Song;
import club.teenshare.beans.User;
import club.teenshare.dao.CommentsDAO;
import club.teenshare.dao.HibernateSessionFactory;
import club.teenshare.dao.PlaylistDAO;
import club.teenshare.dao.PlsongDAO;
import club.teenshare.dao.SongDAO;
import club.teenshare.dao.UserDAO;

public class AdminAction extends ActionSupport {
	
	HttpSession httpSession = ServletActionContext.getRequest().getSession();
	private Song song;
	
	public Song getSong(){
		return this.song;
	}
	public void setSong(Song song){
		this.song = song;
	}
	
	@Override
	public void validate() {
		Admin admin = (Admin)httpSession.getAttribute("currentAdmin");
		if(admin == null){
			addFieldError("notLogin", "您还未登录或登录已过期");
		}
	}
	
	public String initAdmin() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		try {
			Transaction ts = session.beginTransaction();
			SongDAO sdao = new SongDAO();	//定义songdao实例，用户获取所有歌曲的列表
			List<Song> allSongs = sdao.findAll();
			
			UserDAO udao = new UserDAO();	//定义userdao实例，用于获取所有用户的列表
			List<User> allUsers = udao.findAll();
			
			CommentsDAO cdao = new CommentsDAO();
			List<Comments> allComments = cdao.findAll();
			ts.commit();
			session.close();
			httpSession.removeAttribute("allSongs");
			httpSession.removeAttribute("allUsers");
			httpSession.removeAttribute("allComments");
			httpSession.setAttribute("allSongs", allSongs);
			httpSession.setAttribute("allUsers", allUsers);
			httpSession.setAttribute("allComments", allComments);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			addFieldError("initError", "后台信息初始化失败！");
			return ERROR;
		}
		
		
	}
	//创建关于用户显示个人上传信息的action
	public String ShowSongsAdmin() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		
		try {
			Transaction ts = session.beginTransaction();
			String searchHql = "from Song s order by s.uptime desc";
			Query query = session.createQuery(searchHql);
			//传入查询条件参数
			List<Song> allSongs = query.list();
			ts.commit();
			httpSession.removeAttribute("allSongs");//去除掉旧的session存储
			httpSession.setAttribute("allSongs", allSongs);
			//关闭掉Session
			session.close();
			return "allSongs";
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			addFieldError("userError", "歌曲信息记录查询失败，待会儿再试啊~");
			return ERROR;
		}
	}
	//显示歌单信息
	public String ShowPlaylistAdmin() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		
		try {
			Transaction ts = session.beginTransaction();
			String searchHql = "from Playlist pl";
			Query query = session.createQuery(searchHql);
			//传入查询条件参数
			List<Playlist> allPlaylist = query.list();
			ts.commit();
			httpSession.removeAttribute("allPlaylist");//去除掉旧的session存储
			httpSession.setAttribute("allPlaylist", allPlaylist);
			//关闭掉Session
			session.close();
			return "allPlaylist";
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			addFieldError("userError", "歌曲信息记录查询失败，待会儿再试啊~");
			return ERROR;
		}
	}
	//删除歌单信息
	public String delPlaylistAdmin() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		String plid = ServletActionContext.getRequest().getParameter("plid");
		try {
			Transaction ts = session.beginTransaction();
			//删除playlist
			PlsongDAO pldao = new PlsongDAO();
			PlaylistDAO pdao = new PlaylistDAO();
			Playlist pl = pdao.findById(plid);
			List<Plsong> plsongList = pldao.findByProperty("playlist", pl);
			for(Plsong p :plsongList){
				pldao.delete(p);
			}
			pdao.delete(pl);
			
			ts.commit();
			//关闭掉Session
			session.close();
			return "delPlaylist";
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			addFieldError("userError", "歌曲信息记录查询失败，待会儿再试啊~");
			return ERROR;
		}
	}
	
	//创建关于用户显示个人评论信息的action
	public String ShowCommentsAdmin() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		try {
			Transaction ts = session.beginTransaction();
			String searchHql = "from Comments c order by c.ctime desc";
			Query query = session.createQuery(searchHql);
			//传入查询条件参数
			List<Comments> allComments = query.list();
			ts.commit();
			httpSession.removeAttribute("allComments");//去除掉旧的session存储
			httpSession.setAttribute("allComments", allComments);
			//关闭掉Session
			session.close();
			return "allComments";
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			addFieldError("userError", "个人上传记录查询失败，待会儿再试啊~");
			return ERROR;
		}
	}
	//创建关于用户显示个人评论信息的action
	public String ShowUsersAdmin() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		try {
			Transaction ts = session.beginTransaction();
			String searchHql = "from User u";
			Query query = session.createQuery(searchHql);
			//传入查询条件参数
			List<User> allUsers = query.list();
			ts.commit();
			httpSession.removeAttribute("allUsers");//去除掉旧的session存储
			httpSession.setAttribute("allUsers", allUsers);
			//关闭掉Session
			session.close();
			return "allUsers";
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
			addFieldError("userError", "个人上传记录查询失败，待会儿再试啊~");
			return ERROR;
		}
	}
	//用户删除上传的歌曲
	public String delSongsAdmin() throws Exception{
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
	//删除用户上传的歌曲
	public String delCommentsAdmin() throws Exception{
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
	public String reEditSongsAdmin() throws Exception{
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
	
	//重新编辑歌曲信息
	public String reEditPlaylistAdmin() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		String plid = ServletActionContext.getRequest().getParameter("plid");
		try {
			Transaction ts = session.beginTransaction();
			PlaylistDAO pldao = new PlaylistDAO();
			Playlist pl = pldao.findById(plid);	//按照id寻找歌曲
			ts.commit();
			session.close();
			httpSession.setAttribute("reEditPlaylist", pl);
			return "reEditPlaylist";
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			return ERROR;
		}
	}
	//用户退出登录
	public String outLoginAdmin() throws Exception{
		httpSession.removeAttribute("currentAdmin");
		return "logoutSuccess";
	}
}
