<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.backshop.productDB.ProductDTO" %>
<%@ page import="com.backshop.productDB.ProductDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품정보 페이지</title>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/user_page_css/user_productinfo.css">

<script>
    function updateQuantity(action) {
        const quantityInput = document.getElementById("quantity_input");
        let quantity = parseInt(quantityInput.value);
        const stock = parseInt(document.getElementById("product_stock").innerText);

        if (action === 'up' && quantity < stock) {
            quantity++;
        } else if (action === 'down' && quantity > 1) {
            quantity--;
        }
        quantityInput.value = quantity;
    }
</script>



<%@ include file="user_module/user_header.jsp" %>

</head>
<body>
    <div>
        <div>
        <%
    // URL에서 상품 ID 가져오기
    String productId = request.getParameter("product_id");
    ProductDTO product = null;

    if (productId != null) {
        ProductDAO productDAO = new ProductDAO();
        product = productDAO.getProductById(Integer.parseInt(productId)); // 상품 정보 가져오기
    }

    if (product == null) {
%>
    <p>상품 정보를 불러올 수 없습니다.</p>
<%
        return;
    }
%>
        <div class="container1">
            <img src="<%= request.getContextPath() + "/img_uploads/" + product.getProductImage() %>" id="img">
            <div class="container2">
                <div class="info">
                    <h2><%= product.getProductName() %></h2>
                    <br/>
                    <h3>₩<%= product.getProductPrice() %></h3>
                    <br/>
                    <h3>재고량 <%= product.getProductStock() %>개</h3>
                    <br/><br/>
                    <h3>Quantity</h3>
                    <br/>
                    <form action="<%= request.getContextPath() %>/AddToCartServlet" method="post">
                        <input type="hidden" name="product_id" value="<%= product.getId() %>">
                        <input type="number" name="quantity" id="quantity_input" value="1" min="1" max="<%= product.getProductStock() %>">
                        <br/><br/>
                        <input type="submit" value="주문하기" id="order_btn"/>
                    </form>
                </div> <!-- info -->
            </div> <!-- container2 -->
        </div> <!-- container1 -->
        
        
        
        <hr>
        
        <div class="container3">
             <h1 id="review_title">Review</h1>
             <a href="#">
             <input type="submit" value="리뷰 작성하기" id="review_btn"/>
             </a>
        </div> <!-- container3  -->
        
        <div class="container4">
            <div class="review">
            USER_ID★★★★★
            <br/>
            GOOD!
            </div>
            
            <br/>
            <br/>
            <hr id="review_hr">
            <br/>
            
            <div class="review">
            USER_ID2★★
            <br/>
            NOT BAD!
            </div>
            
            <br/>
            <br/>
            <hr id="review_hr">
            <br/>
            
            <div class="review">
            USER_ID★★★★★
            <br/>
            GOOD!
            </div>
            
            <br/>
            <br/>
            <hr id="review_hr">
            <br/>
            
        </div> <!-- container4 -->
        
    </div>
</body>
</html>