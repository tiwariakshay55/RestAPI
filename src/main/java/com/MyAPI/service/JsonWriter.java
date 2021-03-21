package com.MyAPI.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.MyAPI.model.Authors;
import com.MyAPI.model.ListofPostsAndAuthors;
import com.MyAPI.model.Posts;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.gson.JsonIOException;

public class JsonWriter {
	Resource resource = new ClassPathResource("/templates/store.json");

	public ListofPostsAndAuthors readFromJson() throws JsonIOException, IOException {
		ListofPostsAndAuthors posts = new ListofPostsAndAuthors();
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());
		try {
			 
			
			InputStream is = new FileInputStream(new File(resource.getURL().getPath()));
			TypeReference<ListofPostsAndAuthors> reference = new TypeReference<ListofPostsAndAuthors>() {
			};
			posts = mapper.readValue(is, reference);
			
			is.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return posts;
	}

	public void writeToJson(List<Posts> postData, List<Authors> AuthData) {
		ListofPostsAndAuthors posts = new ListofPostsAndAuthors();
		posts.setPosts(postData);
		posts.setAuthors(AuthData);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());
		try {
			
				mapper.writeValue(new File(resource.getURL().getPath()), posts);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	

}
