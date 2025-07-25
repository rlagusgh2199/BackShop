<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="maneger.ManegerDAO" %>
<%@ page import="maneger.ManegerDTO" %>
    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/maneger_page_css/maneger_main.css">
    <title>관리자 페이지</title>
</head>
<body>
    <%@ include file="maneger_module/maneger_header.jsp" %>
    
    <section id="main">
    	<h1>회원 목록</h1>
        <table>
            <thead>
                <tr>
                    <th>회원 ID</th>
                    <th>이름(별명)</th>
                    <th>가입 날짜</th>
                    <th>회원 이메일</th>
                    <th>주소</th> <!-- 주소 열 추가 -->
                    <th>주문 상품</th> <!-- 빈칸으로 유지 -->
                    <th>관리</th> <!-- 관리 열 추가 -->
                </tr>
            </thead>
            <tbody>
                <%
                    ManegerDAO mdao = new ManegerDAO();
                    List<ManegerDTO> userList = mdao.getAllMembers();
                    
                    for (ManegerDTO user : userList) {
                %>
                <tr>
                    <td><%= user.getId2() %></td>
                    <td><%= user.getAlias() %></td>
                    <td><%= user.getFavorite() %></td>
                    <td><%= user.getEmail() %></td>
                    <td><%= user.getAddress() %></td> <!-- 주소 값 출력 -->
                    <td></td> <!-- 주문 상품은 빈칸으로 유지 -->
					<td>
					    <form action="<%= request.getContextPath() %>/maneger_page/manegerUserUpdate.jsp" method="post">
					        <input type="hidden" name="id2" value="<%= user.getId2() %>">
					        <input type="submit" class="edit_button" value="수정"> <!-- 수정 버튼에 클래스 추가 -->
					    </form>
					    <form action="<%= request.getContextPath() %>/ManegerUserDelete" method="post" onsubmit="return confirm('정말로 탈퇴시키겠습니까?');">
					        <input type="hidden" name="id2" value="<%= user.getId2() %>">
					        <input type="submit" class="delete_button" value="탈퇴"> <!-- 탈퇴 버튼에 클래스 추가 -->
					    </form>
					</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </section>
    
</body>
</html>
