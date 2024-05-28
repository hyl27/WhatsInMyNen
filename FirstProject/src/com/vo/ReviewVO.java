package com.vo;

public class ReviewVO {
	private int r_id;
	private String content;
	private String date;
	private int rating;
	private String url;
	private String memberId; // memberId 속성 추가
    private int menuId; // menuId 속성 추가
	
	public ReviewVO() {
		
	}
	
	public ReviewVO(int r_id, String content, String date, int rating, String url) {
		super();
		this.r_id = r_id;
		this.content = content;
		this.date = date;
		this.rating = rating;
		this.url = url;
	}
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
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
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

}


