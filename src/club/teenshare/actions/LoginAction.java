package club.teenshare.actions;

import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import club.teenshare.beans.Admin;
import club.teenshare.beans.User;
import club.teenshare.dao.HibernateSessionFactory;
import club.teenshare.dao.UserDAO;

public class LoginAction extends ActionSupport {
	private User user;
	private Admin admin;
	HttpSession httpSession = ServletActionContext.getRequest().getSession();
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	//登录方法
	@SuppressWarnings("unchecked")
	public String loginMethod() throws Exception{
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			/*String hql = "from User u where u.username=? and u.userpasswd=?";
			Query query=session.createQuery(hql);
			query.setParameter(0, user.getUsername()); //设置参数
			query.setParameter(1, user.getUserpasswd());
			List<User> uresult = query.list();*/
			
			Criteria crit = session.createCriteria(User.class);
			crit.add(Restrictions.eq("username", user.getUsername()));
			crit.add(Restrictions.eq("userpasswd", user.getUserpasswd()));
			List<User> uresult = crit.list();
			
			ts.commit();
			session.close();
			if (uresult != null) {
				User currentUser = uresult.get(0);
				httpSession.removeAttribute("currentUser");
				httpSession.setAttribute("currentUser", currentUser);
				return "loginSuccess";
			}else{
				addFieldError("loginError", "用户名或帐号错误！");
				return "loginError";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
		
	}
	//注册方法
	public String registerMethod() throws Exception{
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			Criteria crit = session.createCriteria(User.class);
			crit.add(Restrictions.eq("username", user.getUsername()));
			List<User> uresult = crit.list();
			ts.commit();
			
			if (uresult.size() != 0) {	//查看是否已经被注册了
				addFieldError("registerError", "小可爱，手慢了哦，用户名已经被注册了~");
				session.close();
				
				return "registerError";
			}else{
				Transaction tx = session.beginTransaction();
				UserDAO udao = new UserDAO();
				user.setUid(UUID.randomUUID().toString());
				udao.save(user);
				tx.commit();
				session.close();
				return "registerSuccess";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
		
	}
	
	
	//登录方法
	@SuppressWarnings("unchecked")
	public String AdminloginMethod() throws Exception{
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			/*String hql = "from User u where u.username=? and u.userpasswd=?";
			Query query=session.createQuery(hql);
			query.setParameter(0, user.getUsername()); //设置参数
			query.setParameter(1, user.getUserpasswd());
			List<User> uresult = query.list();*/
			
			Criteria crit = session.createCriteria(Admin.class);
			crit.add(Restrictions.eq("aname", admin.getAname()));
			crit.add(Restrictions.eq("apasswd", admin.getApasswd()));
			List<Admin> aresult = crit.list();
			
			ts.commit();
			session.close();
			if (aresult != null) {
				Admin currentAdmin = aresult.get(0);
				httpSession.removeAttribute("currentAdmin");
				httpSession.setAttribute("currentAdmin", currentAdmin);
				return "loginSuccess";
			}else{
				addFieldError("loginError", "用户名或帐号错误！");
				return "loginError";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
		
	}
	
	public String execute() throws Exception{
		return super.execute();
	}
}
