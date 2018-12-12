package club.teenshare.beans;

/**
 * Admin entity. @author MyEclipse Persistence Tools
 */

public class Admin implements java.io.Serializable {

	// Fields

	private String aid;
	private String aname;
	private String apasswd;
	private String anickname;

	// Constructors

	/** default constructor */
	public Admin() {
	}

	/** minimal constructor */
	public Admin(String aid) {
		this.aid = aid;
	}

	/** full constructor */
	public Admin(String aid, String aname, String apasswd, String anickname) {
		this.aid = aid;
		this.aname = aname;
		this.apasswd = apasswd;
		this.anickname = anickname;
	}

	// Property accessors

	public String getAid() {
		return this.aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAname() {
		return this.aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getApasswd() {
		return this.apasswd;
	}

	public void setApasswd(String apasswd) {
		this.apasswd = apasswd;
	}

	public String getAnickname() {
		return this.anickname;
	}

	public void setAnickname(String anickname) {
		this.anickname = anickname;
	}

}