package com.vo;

public class MemberVO {
	private String id;
	private String pw;
	private String name;
	private char gender;
	private String bdate;
	private String email;

	public MemberVO() {
	}


	public MemberVO(String id, String pw, String name, char gender, String bdate, String email) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.gender = gender;
		this.bdate = bdate;
		this.email = email;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getgender() {
		return gender;
	}

	public void setgender(char gender) {
		this.gender = gender;
	}

	public String getbdate() {
		return bdate;
	}

	public void setbdate(String bdate) {
		this.bdate = bdate;
	}

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

}


