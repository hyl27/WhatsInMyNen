package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.vo.IngredientVO;
import com.vo.MemberVO;
import com.vo.MynenVO;

import main.DBConnection;

public class IngredientDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private CallableStatement cstmt = null;

	// 재료 추가
	public IngredientVO insert_i(IngredientVO ivo) throws SQLException {
		try {
			MemberVO mvo = new MemberVO();
			conn = DBConnection.getConnection();
			String add_ing = "{CALL insert_ing(?, ?, ?, ?)}";
			cstmt = conn.prepareCall(add_ing);
			String id = mvo.getId();
			cstmt.setString(1, id);
			cstmt.setInt(2, ivo.getNen_id());
			cstmt.setString(3, ivo.getIng_name());
			cstmt.registerOutParameter(4, Types.VARCHAR);

			cstmt.execute();
			String add_ing_Status = cstmt.getString(4);
		} finally {
			if (cstmt != null)
				cstmt.close();
			if (conn != null) {
				conn.close();
			}
		}
		return ivo;
	}

	// 재료 선택
	public IngredientVO select_i(IngredientVO ivo) throws SQLException {
		try {
			MemberVO mvo = new MemberVO();
			conn = DBConnection.getConnection();
			String add_ing = "{CALL insert_ing(?, ?, ?, ?)}";
			cstmt = conn.prepareCall(add_ing);
			String id = mvo.getId();
			cstmt.setString(1, id);
			cstmt.setInt(2, ivo.getNen_id());
			cstmt.setString(3, ivo.getIng_name());
			cstmt.registerOutParameter(4, Types.VARCHAR);

			cstmt.execute();
			String add_ing_Status = cstmt.getString(4);
		} finally {
			if (cstmt != null)
				cstmt.close();
			if (conn != null) {
				conn.close();
			}
		}
		return ivo;
	}

	// 재료 수정
	public IngredientVO update_i(IngredientVO ivo) throws SQLException {
		try {
			MemberVO mvo = new MemberVO();
			conn = DBConnection.getConnection();
			String add_ing = "{CALL insert_ing(?, ?, ?, ?)}";
			cstmt = conn.prepareCall(add_ing);
			String id = mvo.getId();
			cstmt.setString(1, id);
			cstmt.setInt(2, ivo.getNen_id());
			cstmt.setString(3, ivo.getIng_name());
			cstmt.registerOutParameter(4, Types.VARCHAR);

			cstmt.execute();
			String add_ing_Status = cstmt.getString(4);
		} finally {
			if (cstmt != null)
				cstmt.close();
			if (conn != null) {
				conn.close();
			}
		}
		return ivo;
	}

	// 재료 삭제
	public IngredientVO delete_i(IngredientVO ivo) throws SQLException {
		try {
			MemberVO mvo = new MemberVO();
			conn = DBConnection.getConnection();
			String add_ing = "{CALL insert_ing(?, ?, ?, ?)}";
			cstmt = conn.prepareCall(add_ing);
			String id = mvo.getId();
			cstmt.setString(1, id);
			cstmt.setInt(2, ivo.getNen_id());
			cstmt.setString(3, ivo.getIng_name());
			cstmt.registerOutParameter(4, Types.VARCHAR);

			cstmt.execute();
			String add_ing_Status = cstmt.getString(4);
		} finally {
			if (cstmt != null)
				cstmt.close();
			if (conn != null) {
				conn.close();
			}
		}
		return ivo;
	}

}
