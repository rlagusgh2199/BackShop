<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.backshop.productDB.ProductDTO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Back Shop</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/user_page_css/user_main.css">
</head>

<body>
    <!-- 헤더  -->
    <%@ include file="user_module/user_header.jsp" %>
    
    <div class="clear"></div>
    <div class="banner"></div>
    
    <div class="clear"></div>
    <h2>하의</h2> <!-- DB에서 가져온 카테고리 제목 -->
    <section id="main">
        <div class="product-list">
            <%
                // 세션에서 상품 목록 가져오기
                List<ProductDTO> pantsList = (List<ProductDTO>) session.getAttribute("pantsList");
                if (pantsList != null && !pantsList.isEmpty()) {
                    for (ProductDTO product : pantsList) {
            %>
                <div class="product-item">
                <a href="<%= request.getContextPath() %>/ProductServlet?product_id=<%= product.getId() %>">
                    <img src="<%= request.getContextPath() + "/img_uploads/" + product.getProductImage() %>" alt="<%= product.getProductName() %>">
                </a>
                    <h3><%= product.getProductName() %></h3>
                    <p>₩<%= product.getProductPrice() %></p>
                    <form action="<%= request.getContextPath() %>/AddToCartServlet" method="post">
	                    <input type="hidden" name="product_id" value="<%= product.getId() %>">
	                    <input type="hidden" name="product_name" value="<%= product.getProductName() %>">
	                    <input type="hidden" name="product_price" value="<%= product.getProductPrice() %>">
	                    <input type="hidden" name="product_image" value="<%= product.getProductImage() %>">
	                    <input type="hidden" name="quantity" value="<%= 1 %>">
                   		<button type="submit">주문하기</button>
                    </form>
                </div>
            <%
                    }
                } else {
            %>
                <p>상품이 없습니다.</p>
            <%
                }
            %>
        </div>
    </section>
    
    <%@ include file="user_module/user_chat.jsp" %>

    <%@ include file="user_module/user_footer.jsp" %>
</body>
</html>
