<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문상세 페이지</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/maneger_page_css/maneger_orderdetail.css">
</head>
<body>
    <h1 id="title">주문번호 77의 상세내역</h1>
    
    <section id="order">
    <table>
        <tbody>
            <tr>
                <td>상품번호</td>
                <td>125</td>
            </tr>
            
            <tr>
                <td>상품명</td>
                <td>아이폰120</td>
            </tr>
            
            <tr>
                <td>주문고객</td>
                <td>slcpia0821</td>
            </tr>
            
            <tr>
                <td>상품이미지</td>
                <td id="product_img"></td>
            </tr>
            
            <tr>
                <td>주문수량</td>
                <td>2</td>
            </tr>
            
            <tr>
                <td>주문금액</td>
                <td>90000000000000</td>
            </tr>
            
            <tr>
                <td>배달주소</td>
                <td>서울 특별시</td>
            </tr>
            
            <tr>
                <td>주문일자</td>
                <td>2020-20-20 20:12:11</td>
            </tr>
            
            <tr>
                <td>주문상태</td>
                <td>결제완료</td>
            </tr>
        </tbody>
    </table>
    </section>
    
    <input type="submit" value="배송시작" class="order_btn"/>
    <input type="submit" value="주문취소" class="order_btn"/>
</body>
</html>