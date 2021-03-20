package com.MyAPI.model;

import java.util.ArrayList;
import java.util.List;

public class ListofPostsAndAuthors {

	List<Posts> posts = new ArrayList<>();
	List<Authors> authors = new ArrayList<>();

	public List<Authors> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Authors> authors) {
		this.authors = authors;
	}

	public ListofPostsAndAuthors() {
		super();
	}

	public List<Posts> getPosts() {
		return posts;
	}

	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}
	
}
