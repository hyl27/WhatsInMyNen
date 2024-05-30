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

public class menuDAO {

	private Connection conn;
	private ResultSet rs = null;
	private CallableStatement cstmt = null;
	String result = null;

	// 메뉴 추가
	public String insert_menu(String m_id, int menu_id, String name, String all_i) throws SQLException {
		try {
			conn = DBConnection.getConnection();
			String add_menu = "{CALL insert_menu(?, ?, ?, ?, ?)}";
			cstmt = conn.prepareCall(add_menu);
			cstmt.setString(1, m_id);
			cstmt.setInt(2, menu_id);
			cstmt.setString(3, name);
			cstmt.setString(4, all_i);
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

	// 메뉴 보기
	public String view_menu(String m_id, int menu_id) throws SQLException {
		try {
			conn = DBConnection.getConnection();
			String view_menu = "{CALL view_menu(?, ?, ?)}";
			cstmt = conn.prepareCall(view_menu);
			cstmt.setString(1, m_id);
			cstmt.setInt(2, menu_id);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);

			cstmt.execute();
			rs = (ResultSet) cstmt.getObject(3);

			if (rs != null) {
				while (rs.next()) {
					String menuName = rs.getString("menu_name");
					String allIngredient = rs.getString("all_ingredient");
					System.out.println("메뉴: " + menuName);
					System.out.println("필요한 재료: " + allIngredient);
				}
			}
		} finally {
			if (cstmt != null)
				cstmt.close();
		}
		return result;
	}

	// 재료 선택 후 메뉴 검색
	public List<String> search(List<String> ingredients) throws SQLException {
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		List<String> menuNames = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			String search_menu = "{CALL find_menu(?, ?, ?)}";

			String allIngredients = String.join(",", ingredients);
			cstmt = conn.prepareCall(search_menu);
			cstmt.setString(1, allIngredients);
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.registerOutParameter(3, OracleTypes.CURSOR);

			cstmt.execute();
			String resultMessage = cstmt.getString(2);
			System.out.println("결과: " + resultMessage);

			// 커서 가져오기
			rs = (ResultSet) cstmt.getObject(3);
			
			if (rs != null) {
				while (rs.next()) {
					String menuName = rs.getString("menu_name");
					System.out.println(menuName);
					menuNames.add(menuName);
				}
			}

		} finally {
			
			if (cstmt != null) {
				cstmt.close();
			}
		}
		return menuNames;
	}

	// 메뉴 수정
	public String update_menu(String m_id, int menu_id, String new_name, String new_all) throws SQLException {
		try {
			conn = DBConnection.getConnection();
			String edit_menu = "{CALL update_menu(?, ?, ?, ?, ?)}";
			cstmt = conn.prepareCall(edit_menu);
			cstmt.setString(1, m_id);
			cstmt.setInt(2, menu_id);
			cstmt.setString(3, new_name);
			cstmt.setString(4, new_all);
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

	// 메뉴 삭제
	public String delete_menu(String m_id, int menu_id) throws SQLException {
		try {
			conn = DBConnection.getConnection();
			String del_menu = "{CALL delete_menu(?, ?, ?)}";
			cstmt = conn.prepareCall(del_menu);
			cstmt.setString(1, m_id);
			cstmt.setInt(2, menu_id);
			cstmt.registerOutParameter(3, Types.VARCHAR);

			cstmt.execute();
			result = cstmt.getString(3);
			System.out.println(result);
			System.out.println(result);
		} finally {
			if (cstmt != null)
				cstmt.close();
		}
		return result;
	}

}
