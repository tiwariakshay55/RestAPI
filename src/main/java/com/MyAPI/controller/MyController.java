package com.MyAPI.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MyAPI.model.Authors;
import com.MyAPI.model.Posts;
import com.MyAPI.service.Service;
import com.google.gson.JsonIOException;

@RestController
public class MyController {
	@Autowired
	Service service;

	@GetMapping("/posts")
	public ResponseEntity<?> getAllPostSorted(@RequestParam(required = false) String  _sort, @RequestParam(required = false) String  _order) throws JsonIOException, IOException {
		List<Posts> allPost = new ArrayList<>();
		if(_sort !=null&&_order!=null) 
		{
			 allPost = service.getAllPostSorted(_sort , _order);
		}
		else allPost = service.getAllPost();
		if (allPost != null) {
			return ResponseEntity.status(HttpStatus.OK).body(allPost);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No data found");
		}

	}
	

	@GetMapping("/authors")
	public ResponseEntity<?> getAllAuthors() throws JsonIOException, IOException {
		List<Authors> allAuthors = service.getAllAuthors();
		if (allAuthors != null) {
			return ResponseEntity.status(HttpStatus.OK).body(allAuthors);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No data found");
		}

	}

	@GetMapping("/posts/{id}")
	public ResponseEntity<?> getPostById(@PathVariable("id") Optional<Integer> postId) throws JsonIOException, IOException {
		Optional<Posts> myPost = service.getPostById(postId);
		if (myPost != null) {
			return ResponseEntity.status(HttpStatus.OK).body(myPost);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No data found");
		}

	}

	@PostMapping("/posts")
	public ResponseEntity<?> savePost(@RequestBody Posts data) {
		try {
			service.savePost(data);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate ID Found");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Record added");
	}

	@PostMapping("/authors")
	public ResponseEntity<?> saveAuthor(@RequestBody Authors authors) {
		try {
			service.saveAuthor(authors);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate ID Found");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Record added");
	}

	@PutMapping("/authors/{id}")
	public ResponseEntity<?> updateAuthorById(@RequestBody Authors data, @PathVariable("id") int authorId) throws Exception {
		Optional<Authors> auth = service.updateAuthor(data, authorId);
		if (auth != null) {
			return ResponseEntity.status(HttpStatus.OK).body("PUT operation done");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No data found");
		}

	}

	@PatchMapping("/posts/{id}")
	public ResponseEntity<?> updatePostById(@RequestBody Posts data, @PathVariable("id") Optional<Integer> postId) throws JsonIOException, IOException {
		Optional<Posts> myPost = service.updatePost(data, postId);
		if (myPost != null) {
			return ResponseEntity.status(HttpStatus.OK).body("Updated");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No data found");
		}
	}

	@DeleteMapping("/posts/{id}")
	public ResponseEntity<?> deletePostById(@PathVariable("id") Optional<Integer> postId) throws JsonIOException, IOException {
		boolean isremoved = service.deletePost(postId);
		if (!isremoved) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No data found");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Deleted Sucessfully");

	}

}
