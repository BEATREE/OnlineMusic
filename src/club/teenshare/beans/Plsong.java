package club.teenshare.beans;

/**
 * Plsong entity. @author MyEclipse Persistence Tools
 */

public class Plsong implements java.io.Serializable {

	// Fields

	private String lsid;
	private Song song;
	private Playlist playlist;

	// Constructors

	/** default constructor */
	public Plsong() {
	}

	/** full constructor */
	public Plsong(String lsid, Song song, Playlist playlist) {
		this.lsid = lsid;
		this.song = song;
		this.playlist = playlist;
	}

	// Property accessors

	public String getLsid() {
		return this.lsid;
	}

	public void setLsid(String lsid) {
		this.lsid = lsid;
	}

	public Song getSong() {
		return this.song;
	}

	public void setSong(Song song) {
		this.song = song;
	}

	public Playlist getPlaylist() {
		return this.playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

}