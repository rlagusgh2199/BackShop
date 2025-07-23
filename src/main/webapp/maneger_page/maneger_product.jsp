<%@page import="com.backshop.productDB.CategoryCountDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.backshop.productDB.ProductDTO" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/maneger_page_css/maneger_product.css">
    <title>상품 관리</title>
</head>
<body>

    <%@ include file="maneger_module/maneger_header.jsp" %>

    <section id="main">
        <div class="container">
            <div class="category">
			    <h2>카테고리</h2>
			    <ul>
			        <%
			            List<CategoryCountDTO> categoryCounts = (List<CategoryCountDTO>) session.getAttribute("categoryCounts");
			            if (categoryCounts != null && !categoryCounts.isEmpty()) {
			                for (CategoryCountDTO categoryCount : categoryCounts) {
			        %>
			            <li><%= categoryCount.getCategory() %>(<%= categoryCount.getCount() %>)</li>
			        <%
			                }
			            } else {
			        %>
			            <li>등록된 카테고리가 없습니다.</li>
			        <%
			            }
			        %>
			    </ul>
			</div>

            <div class="product-section">
                <div class="product-header">
                    <h2>상품 목록</h2>
                    <button class="add_product" onclick="location.href='maneger_productregister.jsp'">상품 등록</button>
                </div>

                <div class="product-list">
                    <%
                        // 세션에서 상품 목록 가져오기
                        HttpSession session2 = request.getSession();
                        List<ProductDTO> productList = (List<ProductDTO>) session.getAttribute("productList");
                        String errorMessage = (String) session.getAttribute("errorMessage");

                        // 오류 메시지 출력
                        if (errorMessage != null) {
                            out.println("<p>" + errorMessage + "</p>");
                            session.removeAttribute("errorMessage"); // 오류 메시지 출력 후 세션에서 제거
                        } else if (productList != null && !productList.isEmpty()) {
                            for (ProductDTO product : productList) {
                    %>
                        <div class="product-item">
                            <a href="<%= request.getContextPath() %>/ProductDetailServlet?id=<%= product.getId() %>">
                                <img src="<%= request.getContextPath() + "/img_uploads/" + product.getProductImage() %>" alt="<%= product.getProductName() %> 이미지" />
                            </a>
                            <a href="<%= request.getContextPath() %>/ProductDetailServlet?id=<%= product.getId() %>">
                                <h3><%= product.getProductName() %></h3>
                            </a>
                            <p>가격: <%= product.getProductPrice() %>원</p>
                        </div>
                    <%
                            }
                        } else {
                    %>
                        <p>등록된 상품이 없습니다.</p>
                    <%
                        }
                    %>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
