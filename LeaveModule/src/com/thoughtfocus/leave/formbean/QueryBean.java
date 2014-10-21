package com.thoughtfocus.leave.formbean;


/**
 * The bean carrying the bookmark details to be shown to the end user
 * @author manaswita.mishra
 *
 */
public class QueryBean {
	
	private String password; 
	
	private String userName;
	
	private String tagFilter;
	


	public String getTagFilter() {
		return tagFilter;
	}

	public void setTagFilter(String tagFilter) {
		this.tagFilter = tagFilter;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
