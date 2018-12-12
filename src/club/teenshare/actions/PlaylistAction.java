package club.teenshare.actions;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;

import club.teenshare.beans.Playlist;
import club.teenshare.beans.Plsong;
import club.teenshare.beans.Song;
import club.teenshare.dao.HibernateSessionFactory;
import club.teenshare.dao.PlaylistDAO;
import club.teenshare.dao.PlsongDAO;
import club.teenshare.dao.SongDAO;
import club.teenshare.utils.FileName;

public class PlaylistAction extends ActionSupport{
	
	HttpSession httpSession = ServletActionContext.getRequest().getSession();
	
	private Playlist playlist;
	private File plimg;
	private String plimgFileName,plimgContentType;
	private String savePath;
	private String allowType;	//允许的类型
	//歌单包含的歌曲
	private String allSongs;
	
	private String tempNumber;
	
	public String getTempNumber(){
		return this.tempNumber;
	}
	public void setTempNumber(String tempNumber){
		this.tempNumber = tempNumber;
	}
	
	public Playlist getPlaylist() {
		return playlist;
	}
	
	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}
	
	public File getPlimg() {
		return plimg;
	}
	
	public void setPlimg(File plimg) {
		this.plimg = plimg;
	}
	
	public String getPlimgFileName() {
		return plimgFileName;
	}
	
	public void setPlimgFileName(String plimgFileName) {
		this.plimgFileName = plimgFileName;
	}
	
	public String getPlimgContentType() {
		return plimgContentType;
	}

	public void setPlimgContentType(String plimgContentType) {
		this.plimgContentType = plimgContentType;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getAllowType() {
		return allowType;
	}

	public void setAllowType(String allowType) {
		this.allowType = allowType;
	}

	public String getAllSongs() {
		return allSongs;
	}

	public void setAllSongs(String allSongs) {
		this.allSongs = allSongs;
	}
	
	
	public boolean check(String type){  	//检查类型
        String[] types=allowType.split(",");  
        for(String s:types){  
            if(s.equals(type)){  
                return true;  
            }  
        }  
        return false;  
    } 
	//检查图片大小
	public boolean checkSize(File file){  	
        if (file!=null) {
		
	       if (file.length()<20971520) {//小于20MB
			return true;
		}
	   	
		}
	       return false;
	}  
	
	public void validateCreatePlaylist() {
		boolean b=check(plimgContentType);  
        boolean a = checkSize(plimg);
        
        if(!b){  
            addFieldError("imageUpload","您的图片类型有误");  
        }
        if (!a) {
			addFieldError("imageSize", "图片大小超出范围");
		}
	}
	
	//创建新歌单
	public String createPlaylist() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		try {
			Transaction ts = session.beginTransaction();
			this.tempNumber = UUID.randomUUID().toString();
			playlist.setPlid(this.tempNumber);	//为其设置id
			httpSession.setAttribute("tempNumber", tempNumber);
			playlist.setPlnumber(0);//播放次数设置为0
			String fname = FileName.reName(getPlimgFileName());
            //在WebContent下新建一个upload的文件夹,获取其在服务器的绝对磁盘路径
			String targetPath = ServletActionContext.getServletContext().getRealPath(getSavePath());
            //创建一个服务器端的文件
			File newFile = new File(targetPath,fname);
            //完成文件上传的操作
			FileUtils.copyFile(plimg, newFile);
			playlist.setPlimg(getSavePath()+"/"+fname);
			session.saveOrUpdate(playlist);	//保存歌单
			ts.commit();
			
			System.out.println("--------------------"+allSongs+"--------------------");
			String allSongsLs[] = allSongs.split(", ");
			
			for (String singleSong:allSongsLs) {
				Transaction tx = session.beginTransaction();
				Song s = new SongDAO().findById(singleSong);
				Plsong plsong = new Plsong(UUID.randomUUID().toString(), 
						s, playlist);
				new PlsongDAO().save(plsong);
				tx.commit();
			}
			
			session.close();
			return "createSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("createPlaylistError", "创建歌单失败！");
			session.close();
			return ERROR;
		}
	}
	
	//创建新歌单
	public String reEditPlaylist() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		try {
			Transaction ts = session.beginTransaction();
			Playlist playlist = new PlaylistDAO().findById(this.playlist.getPlid());
			playlist.setPlname(this.playlist.getPlname());
			playlist.setPldesc(this.playlist.getPldesc());
			//删除过去的旧纪录
			String pldelsql = "delete from plsong where playlist=?";
			Query query = session.createSQLQuery(pldelsql);
			query.executeUpdate();
			//Set<Song> songset = new HashSet<Song>();//define a set of songs
			System.out.println("--------------------"+allSongs+"--------------------");
			String allSongsLs[] = allSongs.split(", ");
			session.saveOrUpdate(playlist);	//保存歌单
			ts.commit();
			for (String singleSong:allSongsLs) {
				Transaction tx = session.beginTransaction();
				Song s = new SongDAO().findById(singleSong);
				Plsong plsong = new Plsong(UUID.randomUUID().toString(), 
						s, playlist);
				new PlsongDAO().save(plsong);
				tx.commit();
			}
			session.close();
			return "reditSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("createPlaylistError", "创建歌单失败！");
			session.close();
			return ERROR;
		}
	}
	
	//歌单详情
	public String DetailPlaylist() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		//获取httpSession
		String plid = ServletActionContext.getRequest().getParameter("plid");
		//获取sid
		try {
			Transaction ts = session.beginTransaction();
			PlaylistDAO plDao = new PlaylistDAO();
			//查找song
			Playlist currentPlaylist = plDao.findById(plid);
			currentPlaylist.setPlnumber(currentPlaylist.getPlnumber()+1);
			plDao.save(currentPlaylist);
			
			ts.commit();
			httpSession.removeAttribute("currentPlaylist");
			//移除旧的
			httpSession.setAttribute("currentPlaylist", currentPlaylist);
			session.close();
			//放入新的
			return "showPlaylist";//显示音乐信息
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("SongError", "歌曲信息丢失！");
			session.close();
			return ERROR;
		}
	}
}
