package com.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.model.vo.MemberVO;

import oracle.jdbc.OracleTypes;

public class MemberDAO {

	private Connection conn; // DB와 연결된 객체
	private ResultSet rs; // SQL문 결과 담는 객체
	private PreparedStatement pstmt = null; // SQL문 담는 객체
	private CallableStatement cstmt = null;
	MemberVO vo = new MemberVO();

	// 회원 가입
	public String insert_m(MemberVO vo) throws SQLException {
		String joinStatus = null;
		try {
			conn = DBConnection.getConnection();
			String join = "{CALL join_member(?, ?, ?, ?, ?, ?, ?)}";
			cstmt = conn.prepareCall(join);

			cstmt.setString(1, vo.getId());
			cstmt.setString(2, vo.getPw());
			cstmt.setString(3, vo.getName());
			cstmt.setString(4, String.valueOf(vo.getgender()));
			cstmt.setString(5, vo.getbdate());
			cstmt.setString(6, vo.getemail());
			cstmt.registerOutParameter(7, Types.VARCHAR);

			// 프로시저 실행
			cstmt.execute();
			joinStatus = cstmt.getString(7);
			System.out.println("회원가입 결과: " + joinStatus);

			//conn.commit();
		} finally {
			if (cstmt != null)
				cstmt.close();

		}
		return joinStatus;
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

	// 회원 정보 조회
	public MemberVO getInfo(String id) {
		 MemberVO vo = null;
		try {
			conn = DBConnection.getConnection();
			String login = "{CALL MEMBER_INFO(?, ?)}";
			CallableStatement cstmt = conn.prepareCall(login);
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);

			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(2);

			// 결과 처리
			while (rs.next()) {
				String memberID = rs.getString("member_id");
				String memberPW = rs.getString("member_pw");
				String name = rs.getString("name");
				char gender = rs.getString("gender").charAt(0);
				String birthdate = rs.getString("birthdate");
				String email = rs.getString("email");
				System.out.println("여긴 되야하는데 "+memberID);
				vo = new MemberVO(memberID, memberPW, name, gender, birthdate, email);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("오류: 회원 정보 불러오기");
		}
		return vo; 
	
	}

	// 회원 정보 삭제
	public String delete_m(String id) throws SQLException {
		String deleteStatus = null;
		try {
			conn = DBConnection.getConnection();
			String delete_member = "{CALL delete_member(?, ?)}";
			cstmt = conn.prepareCall(delete_member);
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, Types.VARCHAR);

			cstmt.execute();
			deleteStatus = cstmt.getString(2);
			System.out.println(deleteStatus);
			//conn.commit();
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();
			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}
		return deleteStatus;
	}

	// 회원 정보 수정
	public String update_m(MemberVO mvo) throws SQLException {
		String updateStatus = null;
		try {
			conn = DBConnection.getConnection();
			String update_member = "{CALL update_member(?, ?, ?, ?)}";
			cstmt = conn.prepareCall(update_member);
			cstmt.setString(1, mvo.getId());
			cstmt.setString(2, mvo.getPw());
			cstmt.setString(3, mvo.getemail());
			cstmt.registerOutParameter(4, Types.VARCHAR);

			cstmt.execute();
			updateStatus = cstmt.getString(4);
			System.out.println(updateStatus);
		} finally {
			try {
				if (cstmt != null)
					cstmt.close();

			} catch (SQLException se) {
				System.out.println(se.getMessage());
			}
		}
		return updateStatus;
	}
}