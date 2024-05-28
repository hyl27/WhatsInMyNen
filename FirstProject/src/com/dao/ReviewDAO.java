package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vo.ReviewVO;

import main.DBConnection;

public class ReviewDAO {

	private Connection conn; // DB와 연결된 객체
	private ResultSet rs; // SQL문 결과 담는 객체
	
	private PreparedStatement pstmt = null; // SQL문 담는 객체
	private CallableStatement cstmt = null;
	
	
	public void insert_review(String memberId, int menuId, ReviewVO rvo) throws SQLException {
	    try {
	        conn = DBConnection.getConnection();
	        String insert_review = "{CALL INSERT_REVIEW(?, ?, ?, ?, ?, ?, ?)}";
	        cstmt = conn.prepareCall(insert_review);

	        cstmt.setInt(1, rvo.getR_id());
	        cstmt.setString(2, rvo.getContent());
	        cstmt.setString(3, rvo.getDate());
	        cstmt.setInt(4, rvo.getRating());
	        cstmt.setString(5, memberId); // memberId 매개변수 값 사용
//	        cstmt.setString(5, loginInfo.get("memberId")); // loginInfo 맵에서 memberId 값 가져오기
	        cstmt.setInt(6, menuId); // menuId 값을 문자열로 설정
	        cstmt.setString(7, rvo.getUrl());

	        // 프로시저 실행
	        cstmt.execute();

	        conn.commit();

	    } finally {
	        if (cstmt != null)
	            cstmt.close();
	        if (conn != null) {
	            conn.close();
	        }
	    }
	}
}//class
