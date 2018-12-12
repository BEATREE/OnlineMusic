package club.teenshare.actions;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;

import club.teenshare.beans.Song;
import club.teenshare.dao.HibernateSessionFactory;

public class ShowAction extends ActionSupport {
	
	HttpSession httpSession = ServletActionContext.getRequest().getSession();
	//define a HTTPSESSION
	private String currentPage;
	
	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	//热歌榜分页显示
	@SuppressWarnings("unchecked")
	public String HotlistShow() throws Exception{
		Session session = HibernateSessionFactory.getSession();//get hibernate Session
		try {
			Transaction ts = session.beginTransaction();
			/*if(currentPage==null){
				currentPage="1";
			}
			//查询总的数目
			String countHql = "select count(*) from Song as user";
			int pageNo = Integer.parseInt(currentPage);
			*/
			
			Criteria criteria = session.createCriteria(Song.class);
			//每页显示5条数据
			criteria.addOrder(Order.desc("songplaynum"));
			/*criteria.setFirstResult((pageNo*5)-1);//设置开始的编号
			criteria.setMaxResults(5);*/
			List<Song> HotSongList = criteria.list();
			int pageNum = HotSongList.size();
			ts.commit();
			httpSession.removeAttribute("HotsongList");
			httpSession.removeAttribute("pageNum");
			
			httpSession.setAttribute("HotsongList", HotSongList);
			httpSession.setAttribute("pageNum", pageNum);
			session.close();
			return "HotList";
		} catch (Exception e) {
			session.close();
			e.printStackTrace();
		}
		addFieldError("hotError", "歌曲信息查询失败，请稍后再试...");
		return ERROR;
	}
	
	//按歌手进行分类
	public String SingersShow() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		try {
			Transaction ts = session.beginTransaction();
			String singerHql = "select DISTINCT s.songsinger from Song s";
			Query query = session.createQuery(singerHql);
			List<String> allSingers = query.list();
			ts.commit();
			httpSession.removeAttribute("allSingers");
			httpSession.setAttribute("allSingers", allSingers);
			session.close();
			return "allSingers";
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		}
		return ERROR;
	}
	//按歌手进行搜索歌曲
	@SuppressWarnings("unchecked")
	public String singerSongShow() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		try {
			String singerName = ServletActionContext.getRequest().getParameter("singerName");
			Transaction ts = session.beginTransaction();
			String singerHql = "from Song s where s.songsinger like ? order by s.uptime desc";//按歌手名称查询
			Query query = session.createQuery(singerHql);
			query.setParameter(0, "%"+singerName+"%");
			List<Song> singerSongs = query.list();
			ts.commit();
			httpSession.removeAttribute("singerSongs");
			httpSession.removeAttribute("singerName");
			httpSession.setAttribute("singerSongs", singerSongs);
			httpSession.setAttribute("singerName", singerName);
			session.close();
			return "singerSongs";
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		}
		addFieldError("singerSongs","根据歌手查询歌曲信息失败！");
		return ERROR;
	}
}
