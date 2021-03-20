package com.MyAPI.model;

public class Authors {
	private int id;
	private int posts;
	private String fname;
	private String lname;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPosts() {
		return posts;
	}

	public void setPosts(int posts) {
		this.posts = posts;
	}

	public String getfname() {
		return fname;
	}

	public void setfname(String firstname) {
		fname = firstname;
	}

	public String getlname() {
		return lname;
	}

	public void setlname(String lastName) {
		lname = lastName;
	}
}
