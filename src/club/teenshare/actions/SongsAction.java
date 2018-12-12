package club.teenshare.actions;

import java.io.File;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionSupport;

import club.teenshare.beans.Admin;
import club.teenshare.beans.Song;
import club.teenshare.beans.User;
import club.teenshare.dao.HibernateSessionFactory;
import club.teenshare.dao.SongDAO;
import club.teenshare.utils.FileName;

public class SongsAction extends ActionSupport {
	HttpSession httpSession = ServletActionContext.getRequest().getSession();
	private Song song;
	private String keywords;
	
	//管理歌曲图片
	private File songpic;
	private String songpicFileName,songpicContentType;
	private String savePath;
	private String allowType;	//允许的类型
	
	
	public HttpSession getHttpSession() {
		return httpSession;
	}
	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
	public File getSongpic() {
		return songpic;
	}
	public void setSongpic(File songpic) {
		this.songpic = songpic;
	}
	public String getSongpicFileName() {
		return songpicFileName;
	}
	public void setSongpicFileName(String songpicFileName) {
		this.songpicFileName = songpicFileName;
	}
	public String getSongpicContentType() {
		return songpicContentType;
	}
	public void setSongpicContentType(String songpicContentType) {
		this.songpicContentType = songpicContentType;
	}
	public String getKeywords(){
		return this.keywords;
	}
	public void setKeywords(String keywords){
		this.keywords = keywords;
	}
	
	public Song getSong(){
		return this.song;
	}
	public void setSong(Song song){
		this.song = song;
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
	
	
	public void validateUserUploadSongs() {
		boolean b=check(songpicContentType);  
        boolean a = checkSize(songpic);
        
        if(!b){  
            addFieldError("imageUpload","您的图片类型有误");  
        }
        if (!a) {
			addFieldError("imageSize", "图片大小超出范围");
		}
	}
	//用户上传音乐
	public String userUploadSongs() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		//获取httpSession
		String sid = UUID.randomUUID().toString();
		//获取当前用户
		User currentUser = (User) httpSession.getAttribute("currentUser");
		if(currentUser==null || currentUser.equals("")){
			addFieldError("uploadError", "登录已过期，请重新登录~");
			return ERROR;
		}
		try {
			Transaction ts = session.beginTransaction();
			SongDAO sdao = new SongDAO();
			song.setSid(sid);
			song.setSongplaynum(0);
			song.setUptime(Timestamp.valueOf(LocalDateTime.now()));	//设定上传时间
			song.setUser(currentUser);
			String fname = FileName.reName(getSongpicFileName());
            //在WebContent下新建一个upload的文件夹,获取其在服务器的绝对磁盘路径
			String targetPath = ServletActionContext.getServletContext().getRealPath(getSavePath());
            //创建一个服务器端的文件
			File newFile = new File(targetPath,fname);
			System.out.println(getSongpic()+getSongpicFileName()+getSongpicContentType());
            //完成文件上传的操作
			FileUtils.copyFile(songpic, newFile);
			song.setSongpic(getSavePath()+"/"+fname);
			sdao.save(song);
			ts.commit();
			session.close();
			ServletActionContext.getRequest().setAttribute("sid", sid);
			return "UploadSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("uploadError", "未知原因上传失败");
			session.close();
			return ERROR;
		}
	}
	//用户上传音乐
	public String adminUploadSongs() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		//获取httpSession
		String sid = UUID.randomUUID().toString();
		//获取当前用户
		Admin currentAdmin = (Admin) httpSession.getAttribute("currentAdmin");
		if(currentAdmin==null || currentAdmin.equals("")){
			addFieldError("uploadError", "登录已过期，请重新登录~");
			return ERROR;
		}
		try {
			Transaction ts = session.beginTransaction();
			SongDAO sdao = new SongDAO();
			song.setSid(sid);
			song.setSongplaynum(0);
			song.setUptime(Timestamp.valueOf(LocalDateTime.now()));	//设定上传时间
			song.setUser(null);
			String fname = FileName.reName(getSongpicFileName());
            //在WebContent下新建一个upload的文件夹,获取其在服务器的绝对磁盘路径
			String targetPath = ServletActionContext.getServletContext().getRealPath(getSavePath());
            //创建一个服务器端的文件
			File newFile = new File(targetPath,fname);
			System.out.println(getSongpic()+getSongpicFileName()+getSongpicContentType());
            //完成文件上传的操作
			FileUtils.copyFile(songpic, newFile);
			song.setSongpic(getSavePath()+"/"+fname);
			sdao.save(song);
			ts.commit();
			session.close();
			return "aUploadSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("uploadError", "未知原因上传失败");
			session.close();
			return ERROR;
		}
	}
	//用户重新编辑音乐
	public String reUploadSongs() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		//获取httpSession
		//获取当前用户
		User currentUser = (User) httpSession.getAttribute("currentUser");
		if(currentUser==null || currentUser.equals("")){
			addFieldError("uploadError", "登录已过期，请重新登录~");
			return ERROR;
		}
		try {
			Transaction ts = session.beginTransaction();
			song.setSongplaynum(0);
			song.setUptime(Timestamp.valueOf(LocalDateTime.now()));	//设定上传时间
			song.setUser(currentUser);
			session.saveOrUpdate(song);
			ts.commit();
			session.close();
			ServletActionContext.getRequest().setAttribute("sid", song.getSid());
			return "UploadSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("uploadError", "未知原因上传失败");
			session.close();
			return ERROR;
		}
	}
	//用户重新编辑音乐
	public String areUploadSongs() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		//获取httpSession
		//获取当前用户
		Admin currentAdmin = (Admin) httpSession.getAttribute("currentAdmin");
		if(currentAdmin==null || currentAdmin.equals("")){
			addFieldError("uploadError", "登录已过期，请重新登录~");
			return ERROR;
		}
		try {
			Transaction ts = session.beginTransaction();
			song.setSongplaynum(0);
			song.setUptime(Timestamp.valueOf(LocalDateTime.now()));	//设定上传时间
			session.saveOrUpdate(song);
			ts.commit();
			session.close();
			ServletActionContext.getRequest().setAttribute("sid", song.getSid());
			return "aUploadSuccess";
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("uploadError", "未知原因上传失败");
			session.close();
			return ERROR;
		}
	}
	// 查看音乐详情
	public String DetailSongs() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		//获取httpSession
		String sid = ServletActionContext.getRequest().getParameter("sid");
		//获取sid
		try {
			Transaction ts = session.beginTransaction();
			SongDAO sdao = new SongDAO();
			Song currentSong = sdao.findById(sid);
			//查找song
			currentSong.setSongplaynum(currentSong.getSongplaynum()+1);
			sdao.save(currentSong);
			
			ts.commit();
			httpSession.removeAttribute("currentSong");
			//移除旧的
			httpSession.setAttribute("currentSong", currentSong);
			session.close();
			//放入新的
			return "showSongs";//显示音乐信息
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("SongError", "歌曲信息丢失！");
			session.close();
			return ERROR;
		}
	}
	// 查看音乐详情
	@SuppressWarnings("unchecked")
	public String SearchSongs() throws Exception{
		Session session = HibernateSessionFactory.getSession();
		//获取httpSession
		try {
			Transaction ts = session.beginTransaction();
			String hql = "from Song s where s.songname like ?";
			//查找song
			Query query = session.createQuery(hql);
			query.setParameter(0, "%"+keywords+"%");
			//设置词汇
			List<Song> resultSongs = query.list();
			ts.commit();
			httpSession.removeAttribute("resultSongs");
			//移除旧的
			httpSession.setAttribute("resultSongs", resultSongs);
			session.close();
			//放入新的
			return "resultSongs";//显示音乐信息
		} catch (Exception e) {
			e.printStackTrace();
			addFieldError("searchError", "搜索失败！");
			session.close();
			return ERROR;
		}
	}
}
