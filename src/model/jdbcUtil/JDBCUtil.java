package jdbcUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
	public static Connection getConnection() {
		try {
			// 1) 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 2) 데이터베이스 연결 (jdbc:mysql://localhost:3306/테이블명) ***
			 String jdbcurl = "jdbc:mysql://localhost:3306/jspdb?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=utf-8&allowPublicKeyRetrieval=true";
             Connection con = DriverManager.getConnection(jdbcurl, "root", "dongyang");
			
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static void close(PreparedStatement stmt, Connection conn) {
		if (stmt != null) {
			try {
				if (!stmt.isClosed())
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				stmt = null;
			}
		}
		if (conn != null) {
			try {
				if (!conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}

	public static void close(ResultSet rs, PreparedStatement stmt, Connection conn) {
		if (rs != null) {
			try {
				if (!rs.isClosed())
					rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		if (stmt != null) {
			try {
				if (!stmt.isClosed())
					stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				stmt = null;
			}
		}
		if (conn != null) {
			try {
				if (!conn.isClosed())
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
