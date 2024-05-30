package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import main.DBConnection;
import oracle.jdbc.internal.OracleTypes;

public class IngredientDAO {

	private Connection conn;
	private ResultSet rs = null;
	private CallableStatement cstmt = null;

	// 재료 추가
	public String insert_i(String m_id, int nen_id, String name) throws SQLException {
		String add_ing_Status = null;
		try {
			// MemberVO mvo = new MemberVO();
			conn = DBConnection.getConnection();
			String add_ing = "{CALL insert_ing(?, ?, ?, ?)}";
			cstmt = conn.prepareCall(add_ing);
			// String id = mvo.getId();
			cstmt.setString(1, m_id);
			cstmt.setInt(2, nen_id);
			cstmt.setString(3, name);
			cstmt.registerOutParameter(4, Types.VARCHAR);

			cstmt.execute();
			add_ing_Status = cstmt.getString(4);
			System.out.println(add_ing_Status);
		} finally {
			if (cstmt != null)
				cstmt.close();

		}
		return add_ing_Status;
	}

	// 재료 조회
	public List<String> view_i(String m_id, int nen_id) throws SQLException {
		List<String> ingredientsList = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			String view_ing = "{CALL view_ing(?, ?, ?)}";
			cstmt = conn.prepareCall(view_ing);
			cstmt.setString(1, m_id);
			cstmt.setInt(2, nen_id);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);

			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(3);
			while (rs.next()) {
				String ingredient = rs.getString("name");
				ingredientsList.add(ingredient);
				System.out.println(ingredient);
			}
		} catch (SQLException e) {
			System.out.println("선택 냉장고 X -> 재료 X " + e.getMessage());
		} finally {
			if (cstmt != null)
				cstmt.close();
		}
		return ingredientsList;
	}

	// 재료 수정
	public String update_i(String m_id, int nen_id, String ingredientName, String new_name) throws SQLException {
		String result = null;
		try {
			// MemberVO mvo = new MemberVO();
			conn = DBConnection.getConnection();
			String edit_ing = "{CALL update_ing(?, ?, ?, ?, ?)}";
			cstmt = conn.prepareCall(edit_ing);
			cstmt.setString(1, m_id);
			cstmt.setInt(2, nen_id);
			cstmt.setString(3, ingredientName);
			cstmt.setString(4, new_name);
			cstmt.registerOutParameter(5, Types.VARCHAR);

			cstmt.execute();
			result = cstmt.getString(5);
			System.out.println(result);
		} finally {
			if (cstmt != null)
				cstmt.close();

		}
		return result;
	}

	// 재료 삭제
	public String delete_i(String m_id, int nen_id, String name) throws SQLException {
		String result = null;
		try {
			// MemberVO mvo = new MemberVO();
			conn = DBConnection.getConnection();
			String delete_ing = "{CALL delete_ing(?, ?, ?, ?)}";
			cstmt = conn.prepareCall(delete_ing);
			// String id = mvo.getId();
			cstmt.setString(1, m_id);
			cstmt.setInt(2, nen_id);
			cstmt.setString(3, name);
			cstmt.registerOutParameter(4, Types.VARCHAR);

			cstmt.execute();
			result = cstmt.getString(4);
			System.out.println(result);
		} finally {
			if (cstmt != null)
				cstmt.close();

		}
		return result;
	}

}
