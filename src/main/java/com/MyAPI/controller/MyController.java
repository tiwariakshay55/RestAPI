package com.MyAPI.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.MyAPI.model.Authors;
import com.MyAPI.model.Posts;
import com.MyAPI.service.Service;

@RestController
public class MyController {
	@Autowired
	Service service;

	@GetMapping("/posts")
	public ResponseEntity<List<Posts>> getAllPost() {
		List<Posts> allPost = service.getAllPost();
		if (allPost != null) {
			return ResponseEntity.status(HttpStatus.OK).body(allPost);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

	}

	@GetMapping("/authors")
	public ResponseEntity<List<Authors>> getAllAuthors() {
		List<Authors> allAuthors = service.getAllAuthors();
		if (allAuthors != null) {
			return ResponseEntity.status(HttpStatus.OK).body(allAuthors);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

	}

	@GetMapping("/posts/{id}")
	public ResponseEntity<Optional<Posts>> getPostById(@PathVariable("id") Optional<Integer> postId) {
		Optional<Posts> myPost = service.getPostById(postId);
		if (myPost != null) {
			return ResponseEntity.status(HttpStatus.OK).body(myPost);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

	}

	@PostMapping("/posts")
	public ResponseEntity<?> savePost(@RequestBody Posts data) {
		try {
			service.savePost(data);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate ID Found");
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PostMapping("/authors")
	public ResponseEntity<?> saveAuthor(@RequestBody Authors authors) {
		try {
			service.saveAuthor(authors);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate ID Found");
		}
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@PutMapping("/authors/{id}")
	public ResponseEntity<?> updateAuthorById(@RequestBody Authors data, @PathVariable("id") int authorId) {
		Optional<Authors> auth = service.updateAuthor(data, authorId);
		if (auth != null) {
			return ResponseEntity.status(HttpStatus.OK).build();
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}

	}

	@PatchMapping("/posts/{id}")
	public ResponseEntity<Posts> updatePostById(@RequestBody Posts data, @PathVariable("id") Optional<Integer> postId) {
		Optional<Posts> myPost = service.updatePost(data, postId);
		if (myPost != null) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@DeleteMapping("/posts/{id}")
	public ResponseEntity<?> deletePostById(@PathVariable("id") Optional<Integer> postId) {
		boolean isremoved = service.deletePost(postId);
		if (!isremoved) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.status(HttpStatus.OK).build();

	}

}
