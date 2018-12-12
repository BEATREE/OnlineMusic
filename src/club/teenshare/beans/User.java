package club.teenshare.beans;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private String uid;
	private String username;
	private String userpasswd;
	private String nickname;
	private Set commentses = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String uid) {
		this.uid = uid;
	}

	/** full constructor */
	public User(String uid, String username, String userpasswd, String nickname, Set commentses) {
		this.uid = uid;
		this.username = username;
		this.userpasswd = userpasswd;
		this.nickname = nickname;
		this.commentses = commentses;
	}

	// Property accessors

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpasswd() {
		return this.userpasswd;
	}

	public void setUserpasswd(String userpasswd) {
		this.userpasswd = userpasswd;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Set getCommentses() {
		return this.commentses;
	}

	public void setCommentses(Set commentses) {
		this.commentses = commentses;
	}

}