package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import com.vo.MynenVO;

import main.DBConnection;

public class MynenDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private CallableStatement cstmt = null;

	// 냉장고 생성
	public MynenVO insert_n(MynenVO nvo) throws SQLException {
		try {
			conn = DBConnection.getConnection();
			String insert_nen = "{CALL insert_nen(?, ?)}";
			cstmt = conn.prepareCall(insert_nen);
			cstmt.setString(1, nvo.getM_id());
			cstmt.registerOutParameter(2, Types.VARCHAR);

			cstmt.execute();
			String addStatus = cstmt.getString(2);
			System.out.println(addStatus);
		} finally {
			if (cstmt != null)
				cstmt.close();
		}
		return nvo;
	}

	// 냉장고 삭제
	public MynenVO delete_n(MynenVO nvo) throws SQLException {
		try {
			conn = DBConnection.getConnection();
			String delete_nen = "{CALL delete_nen(?, ?, ?)}";
			cstmt = conn.prepareCall(delete_nen);
			cstmt.setString(1, nvo.getM_id());
			cstmt.setInt(2, nvo.getId());
			cstmt.registerOutParameter(3, Types.VARCHAR);

			cstmt.execute();
			String delStatus = cstmt.getString(3);
		} finally {
			if (cstmt != null)
				cstmt.close();
		}
		return nvo;
	}

	// 냉장고 확인
	public MynenVO select_n(MynenVO nvo) throws SQLException {
		try {
			conn = DBConnection.getConnection();
			String select_nen = "{CALL select_nen(?, ?)}";
			cstmt = conn.prepareCall(select_nen);
			cstmt.setString(1, nvo.getM_id());
			cstmt.registerOutParameter(2, Types.VARCHAR);

			cstmt.execute();
			String nenjanggos = cstmt.getString(3);
		} finally {
			if (cstmt != null)
				cstmt.close();
		}
		return nvo;
	}

}
