<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문취소 페이지</title>

<link rel="stylesheet" href="<%= request.getContextPath() %>/css/maneger_page_css/maneger_orderdelete.css">

</head>
<body>
    <h1>주문취소</h1>
    
    <div class="container1">
        <h2>주문번호</h2>
        <h3 class="box">120</h3>
        
        <br/>
        
        <h2>상품명</h2>
        <h3 class="box">초록색 여름상의</h3>
        
        <br/>
        
        <h2>주문수량</h2>
        <h3 class="box">1</h3>
        
        <br/>
        
        <h2>주문금액</h2>
        <h3 class="box">12000</h3>
        
        <br/>
        
        <h2>배달주소</h2>
        <h3 class="box">동양미래대학교</h3>
        
        <br/>
        
        <h2>주문일자</h2>
        <h3 class="box">2020-12-21</h3>
        
    </div>
    
    <br/>
    
    <div class="container2">
        <h3>고객의 주문을 취소하려면 관리자ID와 PW를 입력해주세요</h3>
        
        <br/>
        
        <h2>관리자ID</h2>
        <input id="id"/>
        
        <br/>
        
        <h2>관리자PW</h2>
        <input id="pw"/>
        
        <br/>
        
        <input type="submit" value="주문취소" id="btn"/>
    </div>
</body>
</html>