<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Back Shop</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/maneger_page_css/maneger_header.css">
</head>

<body>
    <header>
        <nav id="maneger_menu">
            <ul>
                <li><a href="maneger_main.jsp">회원 관리</a></li>
                <li><a href="<%= request.getContextPath() %>/productList">상품 관리</a></li>
                <li><a href="maneger_question.jsp">문의 내역 관리</a></li>
                <li><a href="<%= request.getContextPath() %>/OrderServlet">주문 관리</a></li>
                <li><a href="maneger_review.jsp">리뷰 관리</a></li>
            </ul>
        </nav>
        <p>관리자 페이지 입니다</p>
        <a id="logout" href="<%=request.getContextPath() %>/LoginServlet">로그아웃</a>
    </header>
</body>
</html>
