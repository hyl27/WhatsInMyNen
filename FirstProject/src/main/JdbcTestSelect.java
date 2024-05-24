package main;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTestSelect {

	public static void main(String args[]) throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {
			conn = DBConnection.getConnection();
			stmt = conn.createStatement();
			rset = stmt.executeQuery("SELECT MEMBER_ID, MEMBER_PW FROM MEMBERS");

			while (rset.next()) {
				System.out.print(rset.getString("MEMBER_ID") + " ");
				System.out.println(rset.getString("MEMBER_PW"));
			}
		}

		finally {
			if (rset != null)
				rset.close();
			if (stmt != null)
				stmt.close();
			if (conn != null)
				conn.close();
		}
	}

}