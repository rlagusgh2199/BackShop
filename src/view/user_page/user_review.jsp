<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성 페이지</title>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/user_page_css/user_review.css">
<h1 id="headertitle">리뷰 작성</h1>

</head>
<body>
    <div class="container1">
        <img src="<%= request.getContextPath() %>/img/shirt1.png" id="img">
        <h2 id="product_name">초록색 여름상의</h2>
    </div>
    
    <br/>
    
    <div class="container2">
        <h3 id="title1">이 상품에 얼마나 만족하시나요?</h3>
        <h3 id="star">★★★★★</h3>
        <br/>
    </div>
    
    <br/>
    
    <div class="container3">
        <h3>리뷰내용을 아래에 작성해주세요</h3>
        <textarea id="review_content"></textarea>
    </div>
    
    <br/>
    
    <input type="submit" value="리뷰 작성 완료" id="review_btn"/>
</body>
</html>