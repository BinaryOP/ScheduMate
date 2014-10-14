package com.app.schedumate;

public class UserClass {
	
	private String user_name;
	private String user_email;

	public UserClass() {
		user_name = null;
		user_email = null;
	}
	
	public String returnUserName() {
		return this.user_name;
	}
	
	public String returnUserEmail() {
		return this.user_email;
	}
}
