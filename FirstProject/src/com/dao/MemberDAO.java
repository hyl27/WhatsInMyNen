package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import com.vo.MemberVO;

import main.DBConnection;

public class MemberDAO {

	private Connection conn; // DB와 연결된 객체
	private Statement stmt;
	private ResultSet rs; // SQL문 결과 담는 객체
	private PreparedStatement pstmt = null; // SQL문 담는 객체
	private CallableStatement cstmt = null;

	// 회원 가입
	public void insert_m(MemberVO vo) throws SQLException {
		try {
			conn = DBConnection.getConnection();
			String join = "{CALL join_member(?, ?, ?, ?, ?, ?, ?)}";
			cstmt = conn.prepareCall(join);

			cstmt.setString(1, vo.getId());
			cstmt.setString(2, vo.getPw());
			cstmt.setString(3, vo.getName());
			cstmt.setString(4, vo.getgender());
			cstmt.setString(5, vo.getbdate());
			cstmt.setString(6, vo.getemail());
			cstmt.registerOutParameter(7, Types.VARCHAR);

			// 프로시저 실행
			cstmt.execute();
			String joinStatus = cstmt.getString(7);
			System.out.println("회원가입 결과: " + joinStatus);

			conn.commit();
		} finally {
			if (cstmt != null)
				cstmt.close();
			if (conn != null) {
				conn.close();
			}
		}
	}

	// 로그인
	public String login_m(String id, String pw) throws SQLException {
		String loginStatus = null;
		try {
			conn = DBConnection.getConnection();
			String login = "{CALL login(?, ?, ?)}";
			CallableStatement cstmt = conn.prepareCall(login);
			cstmt.setString(1, id);
			cstmt.setString(2, pw);
			cstmt.registerOutParameter(3, java.sql.Types.VARCHAR);

			// 프로시저 실행
			cstmt.execute();
			loginStatus = cstmt.getString(3);

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("로그인 중 오류가 발생했습니다.");
		}
		return loginStatus;
	}

	//회원 정보를 list에 저장   
	public ArrayList<MemberVO> list() {
		ArrayList<MemberVO> list = new ArrayList<MemberVO>();
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("select * from members");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String bdate = rs.getString("bdate");
				String email = rs.getString("email");
				MemberVO data = new MemberVO(id, pw, name, gender, bdate, email);

				list.add(data);
			}
			rs.close();
			// stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 회원 정보 조회
	public MemberVO getInfo(String id) {
		rs = null;
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement("select * from members where members_id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String id1 = rs.getString("id");
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				String bdate = rs.getString("bdate");
				String email = rs.getString("email");

				MemberVO vo = new MemberVO(id1, pw, name, gender, bdate, email);
				return vo;
			} else {
				return null;
			}
		} catch (SQLException se) {
			System.out.println(se.getMessage());
			return null;
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

	// 회원 정보 삭제   
	public MemberVO delete_m(MemberVO vo) throws SQLException {
		try {
			conn = DBConnection.getConnection();
			String delete_member = "{CALL delete_member(?, ?)}";
			cstmt = conn.prepareCall(delete_member);
			cstmt.setString(1, vo.getId());		
			cstmt.registerOutParameter(2, Types.VARCHAR);

			cstmt.execute();
			String deleteStatus = cstmt.getString(2);
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}
		return vo;
	}

	// 회원 정보 수정
	public String update_m(String id, String newPw, String newEmail) throws SQLException {
		String updateStatus = null;
		try {
			conn = DBConnection.getConnection();
			String update_member = "{CALL update_member(?, ?, ?, ?)}";
			cstmt = conn.prepareCall(update_member);
			cstmt.setString(1, id);
			cstmt.setString(2, newPw);
			cstmt.setString(3, newEmail);			
			cstmt.registerOutParameter(4, Types.VARCHAR);

			cstmt.execute();
			updateStatus = cstmt.getString(4);	
		} 
			finally {
			try {
				if (cstmt != null)
					cstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}
		return updateStatus;
	}
}