package com.vo;

import java.sql.Date;

public class ReviewVO {
    private int r_id;
    private String content;
    private java.sql.Date date; // java.sql.Date 타입으로 변경
    private int rating;
    private String url;
    private String memberId; // memberId 속성 추가
    private int menuId; // menuId 속성 추가

    public ReviewVO() {

    }

    public ReviewVO(int r_id, String content, java.sql.Date date, int rating, String url) { // 생성자도 변경
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

    public java.sql.Date getDate() { // 메서드도 변경
        return date;
    }

    public void setDate(java.sql.Date date) { // 메서드도 변경
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

    @Override
    public String toString() {
        return "Review ID: " + r_id + "\n" +
               "Content: " + content + "\n" +
               "Review Date: " + date + "\n" +
               "Rating: " + rating + "\n" +
               "Member ID: " + memberId + "\n" +
               "Menu ID: " + menuId + "\n" +
               "URL: " + url + "\n";
    }
}