package club.teenshare.beans;

import java.util.HashSet;
import java.util.Set;

/**
 * Playlist entity. @author MyEclipse Persistence Tools
 */

public class Playlist implements java.io.Serializable {

	// Fields

	private String plid;
	private String plname;
	private String pldesc;
	private String plimg;
	private Integer plnumber;
	private Set plsongs = new HashSet(0);

	// Constructors

	/** default constructor */
	public Playlist() {
	}

	/** minimal constructor */
	public Playlist(String plid) {
		this.plid = plid;
	}

	/** full constructor */
	public Playlist(String plid, String plname, String pldesc, String plimg, Integer plnumber, Set plsongs) {
		this.plid = plid;
		this.plname = plname;
		this.pldesc = pldesc;
		this.plimg = plimg;
		this.plnumber = plnumber;
		this.plsongs = plsongs;
	}

	// Property accessors

	public String getPlid() {
		return this.plid;
	}

	public void setPlid(String plid) {
		this.plid = plid;
	}

	public String getPlname() {
		return this.plname;
	}

	public void setPlname(String plname) {
		this.plname = plname;
	}

	public String getPldesc() {
		return this.pldesc;
	}

	public void setPldesc(String pldesc) {
		this.pldesc = pldesc;
	}

	public String getPlimg() {
		return this.plimg;
	}

	public void setPlimg(String plimg) {
		this.plimg = plimg;
	}

	public Integer getPlnumber() {
		return this.plnumber;
	}

	public void setPlnumber(Integer plnumber) {
		this.plnumber = plnumber;
	}

	public Set getPlsongs() {
		return this.plsongs;
		
	}

	public void setPlsongs(Set plsongs) {
		this.plsongs = plsongs;
	}

}