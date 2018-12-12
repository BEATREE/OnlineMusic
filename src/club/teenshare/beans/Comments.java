package club.teenshare.beans;

/**
 * Comments entity. @author MyEclipse Persistence Tools
 */

public class Comments implements java.io.Serializable {

	// Fields

	private String cid;
	private User user;
	private Song song;
	private String ccontent;
	private String ctime;

	// Constructors

	/** default constructor */
	public Comments() {
	}

	/** minimal constructor */
	public Comments(String cid, String ccontent) {
		this.cid = cid;
		this.ccontent = ccontent;
	}

	/** full constructor */
	public Comments(String cid, User user, Song song, String ccontent, String ctime) {
		this.cid = cid;
		this.user = user;
		this.song = song;
		this.ccontent = ccontent;
		this.ctime = ctime;
	}

	// Property accessors

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Song getSong() {
		return this.song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public String getCcontent() {
		return this.ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public String getCtime() {
		return this.ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}

}