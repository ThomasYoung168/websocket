package com.yangxf.vo;

import java.util.List;

import com.google.gson.Gson;

public class Message {

	private String welcome;
	private List<String> usernames;
	public String getWelcome() {
		return welcome;
	}
	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}
	public List<String> getUsernames() {
		return usernames;
	}
	public void setUsernames(List<String> usernames) {
		this.usernames = usernames;
	}
	
	public String toJson() {
		return gson.toJson(this);
	}
	
	private static Gson gson = new Gson();
}
