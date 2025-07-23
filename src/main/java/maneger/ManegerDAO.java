package maneger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import jdbcUtil.JDBCUtil;

public class ManegerDAO {
	public List<ManegerDTO> getAllMembers() {
	    List<ManegerDTO> memberList = new ArrayList<>();
	    String query = "SELECT id1, id2, alias, email, inDate, address FROM member2 WHERE type = 'user'"; // type이 user인 회원만 조회
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        conn = JDBCUtil.getConnection();
	        stmt = conn.prepareStatement(query);
	        rs = stmt.executeQuery();

	        // ResultSet에서 회원 정보를 읽어와서 DTO에 설정
	        while (rs.next()) {
	            ManegerDTO user = new ManegerDTO();
	            user.setId1(String.valueOf(rs.getInt("id1"))); // ID1 설정
	            user.setId2(rs.getString("id2")); // ID2 설정
	            user.setAlias(rs.getString("alias")); // 별명 설정
	            user.setEmail(rs.getString("email")); // 이메일 설정
	            user.setFavorite(rs.getString("inDate")); // 가입 날짜 설정
	            user.setAddress(rs.getString("address")); // 주소 설정
	            
	            memberList.add(user); // 리스트에 추가
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // SQL 예외 발생 시 스택 트레이스 출력
	    } finally {
	        // 리소스 정리
	        JDBCUtil.close(rs, stmt, conn);
	    }

	    return memberList; // 회원 목록 반환
	}


    
    public ManegerDTO getUserInfo(String id2) {
        ManegerDTO member = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM member2 WHERE id2 = ?"; // id2로 사용자 정보 조회
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id2);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                member = new ManegerDTO();
                member.setId2(rs.getString("id2"));
                member.setAlias(rs.getString("alias"));
                member.setEmail(rs.getString("email"));
                member.setGender(rs.getString("gender"));
                member.setFavorite(rs.getString("favorite"));
                member.setAddress(rs.getString("address")); // 주소를 가져오는 부분 추가
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pstmt, conn);
        }
        return member; // 사용자 정보 반환
    }

    
    public boolean updateUserInfo(ManegerDTO mDTO) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean flag = false;

        try {
            conn = JDBCUtil.getConnection();
            String updateSql = "UPDATE member2 SET alias = ?, address = ?, email = ?, gender = ?, favorite = ? WHERE id2 = ?";
            pstmt = conn.prepareStatement(updateSql);
            pstmt.setString(1, mDTO.getAlias());
            pstmt.setString(2, mDTO.getAddress());
            pstmt.setString(3, mDTO.getEmail()); // 이메일 업데이트
            pstmt.setString(4, mDTO.getGender());
            pstmt.setString(5, mDTO.getFavorite());
            pstmt.setString(6, mDTO.getId2()); // WHERE 절의 조건

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                flag = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(pstmt, conn);
        }
        return flag;
    }


    
    public boolean deleteUser(String id2) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean flag = false;

        try {
            conn = JDBCUtil.getConnection();
            String deleteSql = "DELETE FROM member2 WHERE id2 = ?";
            pstmt = conn.prepareStatement(deleteSql);
            pstmt.setString(1, id2);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                flag = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JDBCUtil.close(pstmt, conn);
        }
        return flag;
    }
}
