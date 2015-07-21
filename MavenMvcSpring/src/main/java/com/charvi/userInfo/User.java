package com.charvi.userInfo;

public class User {

	private String username;
	private int userid;
	private String city;

	public User( int userid, String username, String city) {
		this.userid = userid;
		this.username = username;
		this.city = city;
		
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
