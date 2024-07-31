package com.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.internal.OracleTypes;

public class menuDAO {

	private Connection conn;
	private ResultSet rs = null;
	private CallableStatement cstmt = null;
	String result = null;


	// 재료 선택 후 메뉴 검색
	public List<String> search(List<String> ingredients) throws SQLException {
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

}
