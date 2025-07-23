<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
   	 	<h1>고객 문의 목록</h1>
        <table>
            <thead>
                <tr>
                    <th>문의 날짜</th>
                    <th>아이디</th>
                    <th>이름(별명)</th>
                    <th>이메일주소</th>
                    <th>문의 내역</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>2023-01-01</td>
                    <td>qwer1234</td>
                    <td>홍길동</td>
                    <td>qwer1234@naver.com</td>
                    <td><a href="#">사이즈 문의...</a></td>
                </tr>
                <!-- 추가 행은 여기에 -->
            </tbody>
        </table>
    </section>
    
</body>
</html>
