package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vo.ReviewVO;

import main.DBConnection;
import oracle.jdbc.OracleTypes;

public class ReviewDAO {

   private Connection conn; // DB와 연결된 객체
   private ResultSet rs; // SQL문 결과 담는 객체

   private PreparedStatement pstmt = null; // SQL문 담는 객체
   private CallableStatement cstmt = null;

   // 특정 회원의 리뷰 리스트를 가져오는 메소드
   public List<ReviewVO> selectList(String memberId) throws SQLException {
      List<ReviewVO> reviewList = new ArrayList<>();
      try {
         conn = DBConnection.getConnection();
         String sql = "{CALL SELECT_REVIEW_LIST(?, ?)}"; // 저장 프로시저 호출
         cstmt = conn.prepareCall(sql);
         cstmt.setString(1, memberId); // 회원 ID 설정
         cstmt.registerOutParameter(2, OracleTypes.CURSOR); // 결과 커서 등록

         cstmt.execute();

         // 결과 커서 가져오기
         ResultSet rs = (ResultSet) cstmt.getObject(2);

         while (rs.next()) {
            ReviewVO reviewVO = new ReviewVO();
            reviewVO.setR_id(rs.getInt("REVIEW_ID"));
            reviewVO.setContent(rs.getString("CONTENT"));
            reviewVO.setDate(new java.sql.Date(rs.getDate("REVIEW_DATE").getTime()));
            reviewVO.setRating(rs.getInt("RATING"));
            reviewVO.setMemberId(rs.getString("MEMBER_ID"));
            reviewVO.setMenuId(rs.getInt("MENUS_MENU_ID"));
            reviewVO.setUrl(rs.getString("URL"));

            reviewList.add(reviewVO);
         }

         rs.close();

      } finally {
         if (cstmt != null)
            cstmt.close();
      }
      return reviewList;
   }

//리뷰삽입
   public void insert_review(String memberId, int menuId, ReviewVO rvo) throws SQLException {
      try {
         conn = DBConnection.getConnection();
         String insert_review = "{CALL INSERT_REVIEW(?, ?, ?, ?, ?, ?)}";
         cstmt = conn.prepareCall(insert_review);

         cstmt.setString(1, rvo.getContent());
         cstmt.setDate(2, rvo.getDate());
         cstmt.setInt(3, rvo.getRating());
         cstmt.setString(4, memberId); // memberId 매개변수 값 사용
//              cstmt.setString(5, loginInfo.get("memberId")); // loginInfo 맵에서 memberId 값 가져오기
         cstmt.setInt(5, menuId); // menuId 값을 문자열로 설정
         cstmt.setString(6, rvo.getUrl());

         // 프로시저 실행
         cstmt.execute();

         conn.commit();

      } finally {
         if (cstmt != null)
            cstmt.close();
      }
   }// insert_review
   
   
   
   
   
}// class
