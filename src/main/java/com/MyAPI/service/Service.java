package com.MyAPI.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.MyAPI.model.Authors;
import com.MyAPI.model.ListofPostsAndAuthors;
import com.MyAPI.model.Posts;
import com.google.gson.JsonIOException;

@Component
public class Service {
     FieldComparator comparator = new FieldComparator();
	JsonWriter writerReader = new JsonWriter();
	ListofPostsAndAuthors listofPosts = new ListofPostsAndAuthors();
	List<Posts> postData = new ArrayList<>();

	List<Authors> authData = new ArrayList<>();

	public void savePost(Posts d) throws Exception {
		
		listofPosts = writerReader.readFromJson();
		postData = listofPosts.getPosts();
		boolean ispost = postData.stream().anyMatch(post -> post.getId().equals(d.getId()));
		if (ispost == false) {
			Posts data = new Posts();
			data.setId(d.getId());
			data.setReviews(d.getReviews());
			data.setTitle(d.getTitle());
			data.setViews(d.getViews());
			data.setAuthor(d.getAuthor());
			postData.add(data);
		} else {
			throw new Exception("same id exsist");
		}
		writerReader.writeToJson(postData , authData);
	}

	public void saveAuthor(Authors d) throws Exception {
		listofPosts = writerReader.readFromJson();
		authData = listofPosts.getAuthors();
		boolean isauthor = authData.stream().anyMatch(auth -> auth.getId() == (d.getId()));
		if (isauthor == false) {
			Authors auth = new Authors();
			auth.setId(d.getId());
			auth.setfname(d.getfname());
			auth.setlname(d.getlname());
			auth.setPosts(d.getPosts());
			authData.add(auth);
		} else
			throw new Exception("same id exsist");
		writerReader.writeToJson(postData , authData);
	}

	public List<Posts> getAllPost(String _sort, String _order) throws JsonIOException, IOException {
		
		listofPosts = writerReader.readFromJson();
		postData = listofPosts.getPosts();
		comparator.set_sort(_sort, _order);
		Collections.sort(postData, comparator );
		return Optional.of(postData).orElse(null);		
	}

	public Optional<Posts> getPostById(Optional<Integer> postId) throws JsonIOException, IOException {
		listofPosts = writerReader.readFromJson();
		postData = listofPosts.getPosts();
		Optional<Posts> myPost;
		try {
			myPost = Optional
					.of((Posts) postData.stream().filter(post -> post.getId().equals(postId)).findFirst().get());
		} catch (Exception e) {
			// to handle no data found scenario
			return null;
		}
		return myPost;
	}

	public Optional<Posts> updatePost(Posts data, Optional<Integer> postId) throws JsonIOException, IOException {
		listofPosts = writerReader.readFromJson();
		postData = listofPosts.getPosts();
		Optional<Posts> myPost;
		boolean found = postData.stream().anyMatch(post -> post.getId().equals(postId));
		if(found) {
			myPost = postData.stream().filter(post -> post.getId().equals(postId)).findFirst().map(obj -> {
				if (data.getAuthor() != null)
					obj.setAuthor(data.getAuthor());
				if (data.getReviews() != null)
					obj.setReviews(data.getReviews());
				if (data.getTitle() != null)
					obj.setTitle(data.getTitle());
				if (data.getViews() != null)
					obj.setViews(data.getViews());
				
				return obj;
			});		
			writerReader.writeToJson(postData , authData);
		return myPost;
		}else return null;
	}

	public Optional<Authors> updateAuthor(Authors data, int authID) throws Exception {
		listofPosts = writerReader.readFromJson();
		authData = listofPosts.getAuthors();
		Optional<Authors> myauth;

		boolean found = authData.stream().anyMatch(auth -> auth.getId() == authID);
		if (found) {
			myauth = authData.stream().filter(auth -> auth.getId() == authID).findFirst().map(obj -> {

				obj.setfname(data.getfname());
				obj.setlname(data.getlname());
				obj.setPosts(data.getPosts());
				return obj;
			});
			writerReader.writeToJson(postData , authData);
			return myauth;
		} else
			return null;

	}

	public boolean deletePost(Optional<Integer> id) throws JsonIOException, IOException {
		listofPosts = writerReader.readFromJson();
		postData = listofPosts.getPosts();
		boolean isremoved = this.postData.removeIf(post -> post.getId().equals(id));
		writerReader.writeToJson(postData , authData);
		return isremoved;

	}

	public List<Authors> getAllAuthors() throws JsonIOException, IOException {
		listofPosts = writerReader.readFromJson();
		authData = listofPosts.getAuthors();
		return Optional.of(authData).orElse(null);
	}

}
