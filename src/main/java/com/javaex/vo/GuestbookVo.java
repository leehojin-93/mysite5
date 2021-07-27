package com.javaex.vo;

public class GuestbookVo {

	// field
	private int no;
	private String name;
	private String password;
	private String content;
	private String date;

	// constructors
	public GuestbookVo() {

	}
	
	public GuestbookVo(int n, String p) {
		this.no = n;
		this.password = p;
	}
	
	public GuestbookVo(String na, String p, String c) {
		this.name = na;
		this.password = p;
		this.content = c;
	}

	public GuestbookVo(int n, String na, String p, String c, String d) {
		this.no = n;
		this.name = na;
		this.password = p;
		this.content = c;
		this.date = d;
	}
	
	// method getter/setter
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public String toString() {
		return "GuestbookVo [no= " + no + ", name= " + name + ", password= " + password + ", content= " + content + ", date= " + date + "]";
	}
	

}
