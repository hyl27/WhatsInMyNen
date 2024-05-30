package com.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			String insert_nen = "{CALL insert_nen(?, ?, ?)}";
			cstmt = conn.prepareCall(insert_nen);
			cstmt.setString(1, nvo.getM_id());
			cstmt.registerOutParameter(2, Types.VARCHAR);
			cstmt.registerOutParameter(3, Types.INTEGER);

			cstmt.execute();
			String addStatus = cstmt.getString(2);
			int now_nenId = cstmt.getInt(3);
			nvo.setId(now_nenId);
			
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
			System.out.println(delStatus);
		} finally {
			if (cstmt != null)
				cstmt.close();
		}
		return nvo;
	}

	// 냉장고 확인
	public List<Integer> view_nen(String memberId) throws SQLException {
		List<Integer> nenIds = new ArrayList<>();
		try {
			conn = DBConnection.getConnection();
			String select_nen = "{CALL view_nen(?, ?)}";
			cstmt = conn.prepareCall(select_nen);
			cstmt.setString(1, memberId);
			cstmt.registerOutParameter(2, Types.VARCHAR);

			cstmt.execute();
			String viewNenResult = cstmt.getString(2);
			if (viewNenResult != null) {
			    // 문자열을 공백 기준으로 분리하여 String 배열로 만듦
			    String[] nenIdStrings = viewNenResult.split(" ");
			    
			    // String 배열을 Integer 배열로 변환
			    Integer[] nenIdArray = new Integer[nenIdStrings.length];
			    for (int i = 0; i < nenIdStrings.length; i++) {
			        nenIdArray[i] = Integer.parseInt(nenIdStrings[i]);
			    }
			    
			    // Integer 리스트로 변환하여 추가
			    nenIds.addAll(Arrays.asList(nenIdArray));
			}

		} finally {
			if (cstmt != null)
				cstmt.close();
		}
		return nenIds;
	}

}
