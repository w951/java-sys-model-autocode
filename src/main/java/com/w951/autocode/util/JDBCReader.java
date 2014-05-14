package com.w951.autocode.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCReader {
	public static List<String[]> getTableInfo(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPass, String jdbcTable) {
		List<String[]> list = new ArrayList<String[]>();
		
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSetMetaData rsmd = null;
		DatabaseMetaData dmd = null;
		
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);
			pstmt = conn.prepareStatement("SELECT * FROM " + jdbcTable);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData(); // 获取字段名
			dmd = conn.getMetaData();
			
			// 获取字段注释
			ResultSet rsColumns = dmd.getColumns(null, "%", jdbcTable, "%");
			List<String> comments = new ArrayList<String>();
			while (rsColumns.next()) {
//				System.out.println(rsColumns.getString("COLUMN_NAME") + "----" + rsColumns.getString("REMARKS"));
				comments.add(rsColumns.getString("REMARKS"));
			}

			
			if (rsmd != null) {
				int count = rsmd.getColumnCount();
				for (int i = 1; i <= count; i++) {
					String javaType = rsmd.getColumnClassName(i);
					javaType = javaType.substring(javaType.lastIndexOf(".") + 1);
					if (javaType.equals("Timestamp")) {
						javaType = "String";
					}
					String[] str = new String[] {javaType, NamingRuleConvert.replaceUnderlineAndfirstToUpper(rsmd.getColumnName(i), "_", ""), comments.get(i - 1)};
					list.add(str);
				}
			}
		} catch (SQLException ex2) {
			ex2.printStackTrace();
		} catch (Exception ex2) {
			ex2.printStackTrace();
		} finally {
			rsmd = null;
			try {
				if (rs != null) {
					rs.close();
					if (stmt != null) {
						stmt.close();
					}
					if (conn != null) {
						conn.close();
					}
				}
			} catch (SQLException ex1) {
			}
		}
		
		return list;
	}
}