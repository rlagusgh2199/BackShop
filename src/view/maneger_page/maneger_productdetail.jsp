<%@page import="com.backshop.productDB.ProductDTO"%>
<%@page import="com.backshop.productDB.ProductDAO"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>상품 수정 페이지</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/maneger_page_css/maneger_productdetail.css">
    <script>
        function confirmDelete(productId) {
            if (confirm('정말 삭제하시겠습니까?')) {
                // 삭제 요청을 위한 폼 생성
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = '<%= request.getContextPath() %>/maneger_productdelete';

                const hiddenField = document.createElement('input');
                hiddenField.type = 'hidden';
                hiddenField.name = 'id';
                hiddenField.value = productId;

                form.appendChild(hiddenField);
                document.body.appendChild(form);
                form.submit(); // 폼 제출
            }
        }
    </script>
</head>
<body>
    <h1>상품 정보 수정</h1>

    <div class="product">
        <div id="img">
            <%
                // 상품 ID를 URL 파라미터에서 가져오기
                String id = request.getParameter("id");
                ProductDAO productDAO = new ProductDAO();
                ProductDTO product = null;

                try {
                    // 상품 정보 가져오기
                    product = productDAO.getProductById(Integer.parseInt(id));
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<p>상품 정보를 불러오지 못했습니다.</p>");
                }
            %>
            <img src="<%= request.getContextPath() + "/img_uploads/" + (product != null ? product.getProductImage() : "") %>" alt="상품 이미지" />
        </div>

        <section id="product">
            <form action="<%= request.getContextPath() %>/maneger_productupdate" method="post" enctype="multipart/form-data" style="display: inline;">
                <input type="hidden" name="id" value="<%= product != null ? product.getId() : "" %>"/>
                
                <table>
                    <tbody>
                        <tr>
                            <td>상품번호</td>
                            <td><%= product != null ? product.getId() : "정보 없음" %></td>
                        </tr>
                        <tr>
                            <td>카테고리</td>
                            <td>
                                <select name="product_category" id="product_category">
                                    <option value="상의" <%= product != null && product.getProductCategory().equals("상의") ? "selected" : "" %>>상의</option>
                                    <option value="하의" <%= product != null && product.getProductCategory().equals("하의") ? "selected" : "" %>>하의</option>
                                    <!-- 필요에 따라 다른 카테고리 추가 가능 -->
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>상품명</td>
                            <td>
                                <input type="text" name="product_name" value="<%= product != null ? product.getProductName() : "" %>" />
                            </td>
                        </tr>
                        <tr>
                            <td>가격</td>
                            <td>
                                <input type="text" name="product_price" value="<%= product != null ? product.getProductPrice() : "" %>" />
                            </td>
                        </tr>
                        <tr>
                            <td>재고 수량</td>
                            <td>
                                <input type="number" name="product_stock" value="<%= product != null ? product.getProductStock() : "" %>" />
                            </td>
                        </tr>
                        <tr>
                            <td>상품설명</td>
                            <td>
                                <textarea name="product_description"><%= product != null ? product.getProductDescription() : "" %></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>상품에 적합한 온도</td>
                            <td>
                                <input type="text" name="product_temperature" value="<%= product != null ? product.getProductTemperature() : "" %>" />
                            </td>
                        </tr>
                        <tr>
                            <td>상품 이미지</td>
                            <td>
                                <input type="file" name="product_image" />
                            </td>
                        </tr>
                        <tr>
                            <td>등록 날짜</td>
                            <td><%= product != null ? product.getRegisterDate() : "정보 없음" %></td>
                        </tr>
                    </tbody>
                </table>   
                <input type="submit" value="수정" id="edit"/> 
                <input type="button" value="삭제" id="delete" onclick="confirmDelete('<%= product != null ? product.getId() : "" %>');" />
            </form>
        </section>
    </div>
</body>
</html>
