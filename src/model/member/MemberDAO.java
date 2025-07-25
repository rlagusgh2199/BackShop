package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbcUtil.JDBCUtil;


public class MemberDAO {
	
	public String idFind(String alias) { //아이디 찾기   
        String id2 = null;
        
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; 
        
        try {
			conn = JDBCUtil.getConnection();
			
			 // 3) SQL 쿼리 실행
            String sql = "select id2 from member2 where alias = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, alias);
     
            rs = pstmt.executeQuery();          
            if (rs.next()) {
                id2 = rs.getString("id2");  // id2 값을 가져옴
            }
        } catch (Exception ex) {
            System.out.println("Exception" + ex);
        } finally {
        	JDBCUtil.close(rs, pstmt, conn);
        }
        return id2;
	}
	
	public String pwFind(String id, String alias) { //비밀번호 찾기
		String pwd1 = null;
		
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
			conn = JDBCUtil.getConnection();
			
			 // 3) SQL 쿼리 실행
            String sql = "select pwd1 from member2 where id2 = ? and alias = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, alias);
            
            rs = pstmt.executeQuery();          
            if (rs.next()) {
            	pwd1 = rs.getString("pwd1");  // pwd1 값을 가져옴
            }
        } catch (Exception ex) {
            System.out.println("Exception" + ex);
        } finally {
        	JDBCUtil.close(rs, pstmt, conn);
        }
        return pwd1;	
	}
	
	
		
	public boolean loginCheck(String id, String password) {// 로그인 체크하는 함수
		// 메시지 변수
        String message;
		
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean loginCon = false;
        
        try {
			conn = JDBCUtil.getConnection();
			
			 // 3) SQL 쿼리 실행
            String sql = "select id2, pwd1 from member2 where id2 = ? and pwd1 = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();          
            loginCon = rs.next();
        } catch (Exception ex) {
            System.out.println("Exception" + ex);
        } finally {
        	JDBCUtil.close(rs, pstmt, conn);
        }
        return loginCon;
    }
	
	
	public String getAlias(String id, String password) {//로그인하고 별명 가져오기
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String alias = null;

	    try {
	        conn = JDBCUtil.getConnection();
	        String sql = "SELECT alias FROM member2 WHERE id2 = ? AND pwd1 = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, id);
	        pstmt.setString(2, password);
	        rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            alias = rs.getString("alias");
	        }
	    } catch (Exception ex) {
	        System.out.println("Exception: " + ex);
	    } finally {
	        JDBCUtil.close(rs, pstmt, conn);
	    }
	    return alias;
	}
	
	public boolean idCheck(MemberDTO mDTO) {//아이디 중복체크
		// 메시지 변수
        String message1;
	 
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean flag = false;
        
        try {
        	// 1) 드라이버 로딩
            // 2) 데이터베이스 연결 (jdbc:mysql://localhost:3306/테이블명) ***
        	conn = jdbcUtil.JDBCUtil.getConnection();
        	
        	 // 3) SQL 쿼리 실행
            String sql = "select count(*) from member2 where id2 = ?;";
            pstmt = conn.prepareStatement(sql);

            // 사용자 입력 값 설정
            pstmt.setString(1, mDTO.getId2());

            // 쿼리 실행
           rs = pstmt.executeQuery();
           if(rs.next()) {
        	   int count = rs.getInt(1);
        	   if (count > 0) {
                   // 아이디가 이미 존재하는 경우
                   message1 = "아이디가 중복됩니다. 다른 아이디를 입력해주세요.";
                   System.out.println(message1);
                   flag = true; // 중복됨을 표시
               } else {
                   // 아이디가 사용 가능한 경우
                   message1 = "사용 가능한 아이디입니다.";
                   System.out.println(message1);
                   flag = false; // 중복되지 않음을 표시
               } 
           }
           
        } catch (Exception ex) {
            System.out.println("Exception" + ex);
            message1 = "회원가입 중 오류가 발생했습니다. 다시 시도해 주세요.";
        } finally {
        	JDBCUtil.close( rs, pstmt, conn);
        }
        return flag;
        
	}
	
	public boolean aliasCheck(MemberDTO mDTO) {//별명 중복체크
		// 메시지 변수
        String message2;
	 
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean flag = false;
        
        try {
        	// 1) 드라이버 로딩
            // 2) 데이터베이스 연결 (jdbc:mysql://localhost:3306/테이블명) ***
        	conn = jdbcUtil.JDBCUtil.getConnection();
        	
        	String sql = "select count(*) from member2 where alias = ?;";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, mDTO.getAlias());
               
            rs = pstmt.executeQuery();
            
            int aliasCount = 0;
            if (rs.next()) {
                aliasCount = rs.getInt(1);
                if (aliasCount > 0) {
                    // 별명이 이미 존재하는 경우
                    message2 = "별명이 중복됩니다. 다른 별명을 입력해주세요.";
                    System.out.println(message2);
                    flag = true; // 중복됨을 표시
                } else {
                    // 별명이 사용 가능한 경우
                    message2 = "사용 가능한 별명입니다.";
                    System.out.println(message2);
                    flag = false; // 중복되지 않음을 표시
                } 
            }

        } catch (Exception ex) {
            System.out.println("Exception" + ex);
            ex.printStackTrace();
            message2 = "회원가입 중 오류가 발생했습니다. 다시 시도해 주세요.";
        } finally {
        	JDBCUtil.close(rs, pstmt, conn);
        }
        return flag;
        
	}
	
	
    public boolean memberInsert(MemberDTO mDTO) {//회원추가
        // 메시지 변수
         String message;
     
        Connection conn = null;
         PreparedStatement pstmt = null;
         boolean flag = false;
         
         try {
            // 1) 드라이버 로딩
              // 2) 데이터베이스 연결 (jdbc:mysql://localhost:3306/테이블명) ***
            conn = jdbcUtil.JDBCUtil.getConnection();
            
             // 입력 값 검증 (서버 측 유효성 검사 추가)
             if (mDTO.getId2() == null || mDTO.getId2().length() < 6) {
                 System.out.println("아이디는 최소 6자리 이상이어야 합니다.");
                 return false; // 잘못된 ID로 삽입 중단
             }
             if (mDTO.getPwd1() == null || mDTO.getPwd1().length() < 6) {
                 System.out.println("비밀번호는 최소 6자리 이상이어야 합니다.");
                 return false; // 잘못된 비밀번호로 삽입 중단
             }
             if (mDTO.getAlias() == null || mDTO.getAlias().length() < 2) {
                 System.out.println("별명은 최소 2자리 이상이어야 합니다.");
                 return false; // 잘못된 별명으로 삽입 중단
             }
             if(!mDTO.getPwd1().equals(mDTO.getPwd2())) {
                System.out.println("비밀번호가 다릅니다.");
                 return false; // 비번 달라서 삽입 중단
             }
            
             // INSERT SQL 작성
              String insertSql = "INSERT INTO member2 (id2, pwd1, alias, address, email, gender, favorite) VALUES (?, ?, ?, ?, ?, ?, ?)";
              PreparedStatement insertStmt = conn.prepareStatement(insertSql);

              // 사용자 입력 값 설정
              insertStmt.setString(1, mDTO.getId2());
              insertStmt.setString(2, mDTO.getPwd1()); 
              insertStmt.setString(3, mDTO.getAlias()); 
              insertStmt.setString(4, mDTO.getAddress() != null ? mDTO.getAddress() : "");// 빈 문자열이면 ''로 저장
              insertStmt.setString(5, mDTO.getEmail() != null ? mDTO.getEmail() : "");
              insertStmt.setString(6, mDTO.getGender() != null ? mDTO.getGender() : "");
              insertStmt.setString(7, mDTO.getFavorite() != null ? mDTO.getFavorite() : "");

              // 쿼리 실행
              int rowsAffected = insertStmt.executeUpdate();
              if (rowsAffected > 0) {
                  System.out.println("데이터 추가 성공!");
                  flag = true;
              } else {
                  System.out.println("데이터 추가 실패.");
                  message = "데이터 추가 중 문제가 발생했습니다. 다시 시도해주세요.";
              }

         } catch (Exception ex) {
             System.out.println("Exception" + ex);
              message = "회원가입 중 오류가 발생했습니다. 다시 시도해 주세요.";
         } finally {
            JDBCUtil.close( pstmt, conn);
         }
         return flag;
     }

	
	 public boolean memberInsert3(MemberDTO mDTO) {//회원추가
		 	// 메시지 변수
	        String message;
		 
	    	Connection conn = null;
	        PreparedStatement pstmt = null;
	        boolean flag = false;
	        
	        try {
	        	// 1) 드라이버 로딩
                // 2) 데이터베이스 연결 (jdbc:mysql://localhost:3306/테이블명) ***
	        	conn = jdbcUtil.JDBCUtil.getConnection();
	        	
	        	 // 입력 값 검증 (서버 측 유효성 검사 추가)
	            if (mDTO.getId2() == null || mDTO.getId2().length() < 6) {
	                System.out.println("아이디는 최소 6자리 이상이어야 합니다.");
	                return false; // 잘못된 ID로 삽입 중단
	            }
	            if (mDTO.getPwd1() == null || mDTO.getPwd1().length() < 6) {
	                System.out.println("비밀번호는 최소 6자리 이상이어야 합니다.");
	                return false; // 잘못된 비밀번호로 삽입 중단
	            }
	            if (mDTO.getAlias() == null || mDTO.getAlias().length() < 2) {
	                System.out.println("별명은 최소 2자리 이상이어야 합니다.");
	                return false; // 잘못된 별명으로 삽입 중단
	            }
	        	
	        	 // INSERT SQL 작성
                String insertSql = "INSERT INTO member2 (id2, pwd1, alias, address, email, gender, favorite) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);

                // 사용자 입력 값 설정
                insertStmt.setString(1, mDTO.getId2());
                insertStmt.setString(2, mDTO.getPwd1()); 
                insertStmt.setString(3, mDTO.getAlias()); 
                insertStmt.setString(4, mDTO.getAddress() != null ? mDTO.getAddress() : "");// 빈 문자열이면 ''로 저장
                insertStmt.setString(5, mDTO.getEmail() != null ? mDTO.getEmail() : "");
                insertStmt.setString(6, mDTO.getGender() != null ? mDTO.getGender() : "");
                insertStmt.setString(7, mDTO.getFavorite() != null ? mDTO.getFavorite() : "");

                // 쿼리 실행
                int rowsAffected = insertStmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("데이터 추가 성공!");
                    flag = true;
                } else {
                    System.out.println("데이터 추가 실패.");
                    message = "데이터 추가 중 문제가 발생했습니다. 다시 시도해주세요.";
                }

	        } catch (Exception ex) {
	            System.out.println("Exception" + ex);
                message = "회원가입 중 오류가 발생했습니다. 다시 시도해 주세요.";
	        } finally {
	        	JDBCUtil.close( pstmt, conn);
	        }
	        return flag;
	    }
	 
	 public MemberDTO getUserInfo(String id) {
		    MemberDTO user = null;
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;

		    try {
		        conn = JDBCUtil.getConnection();
		        String sql = "SELECT * FROM member2 WHERE id2 = ?";
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, id);
		        rs = pstmt.executeQuery();

		        if (rs.next()) {
		            user = new MemberDTO();
		            user.setId2(rs.getString("id2"));
		            user.setPwd1(rs.getString("pwd1"));
		            user.setAlias(rs.getString("alias"));
		            user.setAddress(rs.getString("address"));
		            user.setEmail(rs.getString("email"));
		            user.setGender(rs.getString("gender"));
		            user.setFavorite(rs.getString("favorite"));
		        }
		    } catch (Exception ex) {
		        System.out.println("Exception: " + ex);
		    } finally {
		        JDBCUtil.close(rs, pstmt, conn);
		    }
		    return user;
		}
	 
	 public boolean deleteUser(String id2) {
		    String query = "DELETE FROM member2 WHERE id2 = ?";
		    try (Connection conn = JDBCUtil.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(query)) {
		         
		        stmt.setString(1, id2);
		        int rowsAffected = stmt.executeUpdate();
		        return rowsAffected > 0;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return false;
		}
	 
	 public boolean updateUser(String id2, String email, String gender, String favorite, String alias, String address) {
		    String query = "UPDATE member2 SET email = ?, gender = ?, favorite = ?, alias = ?, address = ? WHERE id2 = ?";
		    try (Connection conn = JDBCUtil.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(query)) {
		         
		        stmt.setString(1, email);
		        stmt.setString(2, gender);
		        stmt.setString(3, favorite);
		        stmt.setString(4, alias); // 별명 업데이트
		        stmt.setString(5, address); // 주소 업데이트
		        stmt.setString(6, id2);

		        int rowsAffected = stmt.executeUpdate();
		        return rowsAffected > 0;
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return false;
		}

	 
	 
	 public boolean isUserExists(String id2) {
		    Connection conn = null;
		    PreparedStatement pstmt = null;
		    ResultSet rs = null;
		    boolean exists = false;

		    try {
		        conn = JDBCUtil.getConnection();
		        String sql = "SELECT COUNT(*) FROM member2 WHERE id2 = ?";
		        pstmt = conn.prepareStatement(sql);
		        pstmt.setString(1, id2);
		        rs = pstmt.executeQuery();

		        if (rs.next()) {
		            exists = rs.getInt(1) > 0; // 결과가 1보다 크면 존재함
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        JDBCUtil.close(rs, pstmt, conn);
		    }
		    return exists;
		}

	    public void saveUser(MemberDTO member) {
	        String sql = "INSERT INTO users (email, nickname) VALUES (?, ?)";
	        try (Connection conn = JDBCUtil.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, member.getEmail());
	            pstmt.setString(2, member.getAlias()); // 닉네임을 alias로 사용
	            pstmt.executeUpdate(); // INSERT 실행
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public boolean memberInsert2(MemberDTO mDTO) {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        boolean flag = false;

	        try {
	            conn = JDBCUtil.getConnection();
	            String insertSql = "INSERT INTO member2 (id2, pwd1, alias, address, email, gender, favorite) VALUES (?, ?, ?, ?, ?, ?, ?)";
	            pstmt = conn.prepareStatement(insertSql);

	            pstmt.setString(1, mDTO.getId2()); // ID (이메일)
	            pstmt.setString(2, ""); // 비밀번호 (비워두기)
	            pstmt.setString(3, mDTO.getAlias()); // 별명
	            pstmt.setString(4, ""); // 주소 (빈 문자열)
	            pstmt.setString(5, mDTO.getEmail()); // 이메일
	            pstmt.setString(6, mDTO.getGender()); // 성별
	            pstmt.setString(7, ""); // 관심사 (빈 문자열)

	            int rowsAffected = pstmt.executeUpdate();
	            if (rowsAffected > 0) {
	                flag = true; // 성공적으로 추가
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            JDBCUtil.close(pstmt, conn);
	        }
	        return flag;
	    }
	    
	    public boolean updateUserInfo(MemberDTO mDTO) {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        boolean flag = false;

	        try {
	            conn = JDBCUtil.getConnection();
	            String updateSql = "UPDATE member2 SET alias = ?, gender = ? WHERE id2 = ?";
	            pstmt = conn.prepareStatement(updateSql);

	            pstmt.setString(1, mDTO.getAlias());
	            pstmt.setString(2, mDTO.getGender());
	            pstmt.setString(3, mDTO.getId2());

	            int rowsAffected = pstmt.executeUpdate();
	            if (rowsAffected > 0) {
	                flag = true; // 성공적으로 업데이트
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            JDBCUtil.close(pstmt, conn);
	        }
	        return flag;
	    }
	    
	    public boolean removeUser(String userno) {
	        String sql = "DELETE FROM member2 WHERE id2 = ?";
	        try (Connection conn = JDBCUtil.getConnection();
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            
	            pstmt.setString(1, userno);
	            int rowsAffected = pstmt.executeUpdate();
	            return rowsAffected > 0;  // 삭제 성공 여부 반환
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	
}
