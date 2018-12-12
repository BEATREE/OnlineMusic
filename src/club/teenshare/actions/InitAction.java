package club.teenshare.actions;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;

import club.teenshare.beans.Playlist;
import club.teenshare.beans.Song;
import club.teenshare.dao.HibernateSessionFactory;

public class InitAction extends ActionSupport {
	
	HttpSession httpSession = ServletActionContext.getRequest().getSession();
	
	@SuppressWarnings("unchecked")
	public String execute() throws Exception{
		try {
			Session session = HibernateSessionFactory.getSession();
			Transaction ts = session.beginTransaction();
			//获取歌单列表
			String getPlHql = "from Playlist pl";
			Query getPlQuery = session.createQuery(getPlHql);
			getPlQuery.setMaxResults(3);
			List<Playlist> plIndexList = getPlQuery.list();
			//获取最新和热门歌曲
			String getNewSongHql = "from Song s order by s.uptime desc";
			Query getNewSongQuery = session.createQuery(getNewSongHql);
			getNewSongQuery.setMaxResults(3);
			List<Song> newSongList = getNewSongQuery.list();
			//
			String getHotSongHql = "from Song s order by s.songplaynum desc";
			Query getHotSongQuery = session.createQuery(getHotSongHql);
			getHotSongQuery.setMaxResults(3);
			List<Song> hotSongList = getHotSongQuery.list();
			
			String getPlaylistHql = "from Playlist pl order by pl.plnumber desc";
			Query getPlaylistQuery = session.createQuery(getPlaylistHql);
			getPlaylistQuery.setMaxResults(3);
			List<Playlist> allPlayList = getPlaylistQuery.list();
			ts.commit();
			//存入httpSession中
			httpSession.removeAttribute("plIndexList");
			httpSession.removeAttribute("newSongList");
			httpSession.removeAttribute("hotSongList");
			httpSession.removeAttribute("allPlayList");
			httpSession.setAttribute("allPlayList", allPlayList);
			httpSession.setAttribute("plIndexList", plIndexList);
			httpSession.setAttribute("newSongList", newSongList);
			httpSession.setAttribute("hotSongList", hotSongList);
			session.close();
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
		
	}
}
