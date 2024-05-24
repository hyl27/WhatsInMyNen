package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.vo.MynenVO;

import main.DBConnection;

public class MynenDAO {
	private Connection conn; 
	private Statement stmt; 
	private ResultSet rs; 
	private PreparedStatement pstmt;
	 private SessionManager session;

	    public MynenDAO(SessionManager session) {
	        this.session = session;
	    }
	
	
	// 냉장고 생성
	public int insert_n(MynenVO vo) throws SQLException {  
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("insert into mynenjanggo values ('', ?)");
			
			pstmt.setString(2, vo.getM_id());
			rs = pstmt.executeQuery();
			conn.commit();
		}
			finally {
				if(pstmt != null)
					pstmt.close();
			}
		return -1;
	}
	
	// 냉장고 삭제
	public int delete_n(int id) throws SQLException {
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("delete from mynenjanggo where nen_id=?");
			pstmt.setInt(1, id);
			int n = pstmt.executeUpdate();
			return n;
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					pstmt.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}
	}

}
