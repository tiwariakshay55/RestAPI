package com.MyAPI.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.MyAPI.model.Posts;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class JsonWriter {
public void writeToJson(List<Posts> post) throws JsonIOException, IOException {
	Gson gson = new Gson();
	
	gson.toJson(post , new FileWriter("C:\\public\\workspace\\restAPI\\RestAPI\\src\\main\\resources\\templates\\store.json"));
}

public void readfromJson() {
	
}
}
