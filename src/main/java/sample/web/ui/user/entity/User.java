package sample.web.ui.user.entity;

import java.io.Serializable;

import sample.web.ui.annotations.PrimaryKey;
import sample.web.ui.annotations.Table;

@Table(tableName = "user")
public class User implements Serializable {
	@PrimaryKey
	private Long id;
	private String username;
	private String password;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
