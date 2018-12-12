package club.teenshare.actions;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

public class CommentsAction extends ActionSupport {
	
	HttpSession httpSession = ServletActionContext.getRequest().getSession();
	private String Ccontent;
	
	public String getCcontent() {
		return Ccontent;
	}
	public void setCcontent(String ccontent) {
		Ccontent = ccontent;
	}
	
	//显示和当前歌曲相关的评论消息
	public String currentComments() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		try {
			Transaction ts = session.beginTransaction();
			//开始查找相关评论
			String comHql = "from Comments c where c.song=?";
			Query query = session.createQuery(comHql);
			query.setParameter(0, (Song)httpSession.getAttribute("currentSong"));
			List<Comments> currentComments = query.list();
			ts.commit();
			httpSession.removeAttribute("currentComments");
			httpSession.setAttribute("currentComments", currentComments);
			session.close();
			return "showComments";
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
			addFieldError("commentsError", "歌曲相关评论查询失败");
			return ERROR;
		}
	}
	//提交评论信息
	public String submitComments() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		try {
			User cu = (User)httpSession.getAttribute("currentUser");
			if (cu==null) {
				addFieldError("notLogin", "登陆后再发表评论哦~");
				session.close();				
				return ERROR;
			}
			Transaction ts = session.beginTransaction();
			CommentsDAO comDao = new CommentsDAO();
			Comments comments = new Comments();
			comments.setCcontent(Ccontent);
			comments.setSong((Song)httpSession.getAttribute("currentSong"));
			comments.setCid(UUID.randomUUID().toString());
			comments.setUser(cu);
			comments.setCtime(LocalDate.now().toString());
			comDao.save(comments);
			ts.commit();
			session.close();
			return "submitSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("commentError", "评论内容提交失败！");
			session.close();
			return ERROR;
		}
	}
}
