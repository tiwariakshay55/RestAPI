package com.MyAPI.model;

import java.util.Optional;

public class Posts {
	private Optional<Integer> id;
	private Optional<Integer> views;
	private Optional<Integer> reviews;
	private Optional <String> author;
	private Optional<String>  title;
	public Optional<Integer> getId() {
		return id;
	}
	public void setId(Optional<Integer> id) {
		this.id = id;
	}
	public Optional<Integer> getViews() {
		return views;
	}
	public void setViews(Optional<Integer> views) {
		this.views = views;
	}
	public Optional<Integer> getReviews() {
		return reviews;
	}
	public void setReviews(Optional<Integer> reviews) {
		this.reviews = reviews;
	}
	public Optional<String> getAuthor() {
		return author;
	}
	public void setAuthor(Optional<String> author) {
		this.author = author;
	}
	public Optional<String> getTitle() {
		return title;
	}
	public void setTitle(Optional<String> title) {
		this.title = title;
	}



}
