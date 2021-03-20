package com.MyAPI.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.MyAPI.model.Authors;
import com.MyAPI.model.Posts;

@Component
public class Service {
	
	JsonWriter writer = new JsonWriter();
	List<Posts> postData = new ArrayList<>();
	
	List<Authors> authData = new ArrayList<>();
	
	public void savePost(Posts d) throws Exception
	{

	boolean ispost = postData.stream().anyMatch(post -> post.getId().equals(d.getId()));
		if(ispost == false) {
			Posts data = new Posts();
			data.setId(d.getId());
			data.setReviews(d.getReviews());
			data.setTitle(d.getTitle());
			data.setViews(d.getViews());
			data.setAuthor(d.getAuthor());
			postData.add(data);
		}else {
			throw new Exception("same id exsist");
		}

		
	}
	
	public void saveAuthor(Authors d) throws Exception
	{
     boolean isauthor = authData.stream().anyMatch(auth -> auth.getId() == (d.getId()));
     if(isauthor == false) {
		Authors auth = new Authors();
		auth.setId(d.getId());
		auth.setfname(d.getfname());
		auth.setlname(d.getlname());
		auth.setPosts(d.getPosts());
		authData.add(auth);
	}
     else throw new Exception("same id exsist");
	}
	
	
	public List<Posts> getAllPost(){
		return Optional.of(postData).orElse(null);
	}
	
	public Optional<Posts> getPostById(Optional<Integer>  postId)
	{
		Optional<Posts> myPost;
		try {
		 myPost = Optional.of((Posts) postData.stream().filter(post -> post.getId().equals( postId)).findFirst().get());
	} catch (Exception e) {
		//to handle no data found scenario
		return null;
	}
		return myPost;
	}
	
	public Optional<Posts> updatePost(Posts data, Optional<Integer>  postId) {
		
		Optional<Posts> myPost;
		try {
			 myPost = postData.stream().filter(post -> post.getId() .equals(postId)).findFirst().map(obj->{
				 if(data.getAuthor()!=null) obj.setAuthor(data.getAuthor());
				 if(data.getReviews()!=null) obj.setReviews(data.getReviews());
				 if(data.getTitle()!=null) obj.setTitle(data.getTitle());
				 if(data.getViews()!=null) obj.setViews(data.getViews());
				 
				 return obj;
			 });
			 
		} catch (Exception e) {
			//to handle no data found scenario
			return null;
		}
	return myPost;
	}
	
	public Optional<Authors> updateAuthor(Authors data, int authID) {
		
		Optional<Authors> myauth;
		try {
			myauth = authData.stream().filter(auth -> auth.getId() == authID).findFirst().map(obj->{
				 obj.setfname(data.getfname());
				 obj.setlname(data.getlname());
				 obj.setPosts(data.getPosts());	 
				 return obj;
			 });
			 
		} catch (Exception e) {
			//to handle no data found scenario
			return null;
		}
	return myauth;
	    	
	}
	
	public boolean deletePost(Optional<Integer> id)
	{
		boolean isremoved = this.postData.removeIf(post->post.getId().equals(id));
		return isremoved;
		
		
		
	}

	public List<Authors> getAllAuthors() {		
		return Optional.of(authData).orElse(null);
	}
	
}
