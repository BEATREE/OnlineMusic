package club.teenshare.beans;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Song entity. @author MyEclipse Persistence Tools
 */

public class Song implements java.io.Serializable {

	// Fields

	private String sid;
	private User user;
	private String songpic;
	private String songname;
	private String songdesc;
	private String songsrc;
	private String songsinger;
	private Integer songplaynum;
	private Timestamp uptime;
	private Set commentses = new HashSet(0);
	private Set plsongs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Song() {
	}

	/** minimal constructor */
	public Song(String sid) {
		this.sid = sid;
	}

	/** full constructor */
	public Song(String sid, User user, String songpic, String songname, String songdesc, String songsrc,
			String songsinger, Integer songplaynum, Timestamp uptime, Set commentses, Set plsongs) {
		this.sid = sid;
		this.user = user;
		this.songpic = songpic;
		this.songname = songname;
		this.songdesc = songdesc;
		this.songsrc = songsrc;
		this.songsinger = songsinger;
		this.songplaynum = songplaynum;
		this.uptime = uptime;
		this.commentses = commentses;
		this.plsongs = plsongs;
	}

	// Property accessors

	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSongpic() {
		return this.songpic;
	}

	public void setSongpic(String songpic) {
		this.songpic = songpic;
	}

	public String getSongname() {
		return this.songname;
	}

	public void setSongname(String songname) {
		this.songname = songname;
	}

	public String getSongdesc() {
		return this.songdesc;
	}

	public void setSongdesc(String songdesc) {
		this.songdesc = songdesc;
	}

	public String getSongsrc() {
		return this.songsrc;
	}

	public void setSongsrc(String songsrc) {
		this.songsrc = songsrc;
	}

	public String getSongsinger() {
		return this.songsinger;
	}

	public void setSongsinger(String songsinger) {
		this.songsinger = songsinger;
	}

	public Integer getSongplaynum() {
		return this.songplaynum;
	}

	public void setSongplaynum(Integer songplaynum) {
		this.songplaynum = songplaynum;
	}

	public Timestamp getUptime() {
		return this.uptime;
	}

	public void setUptime(Timestamp uptime) {
		this.uptime = uptime;
	}

	public Set getCommentses() {
		return this.commentses;
	}

	public void setCommentses(Set commentses) {
		this.commentses = commentses;
	}

	public Set getPlsongs() {
		return this.plsongs;
	}

	public void setPlsongs(Set plsongs) {
		this.plsongs = plsongs;
	}

}