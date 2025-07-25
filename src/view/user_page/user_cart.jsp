<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.MemberDAO" %>
<%@ page import="member.MemberDTO" %>
<%@ page import="cart.CartDAO" %>
<%@ page import="cart.CartDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.backshop.productDB.ProductDTO" %>
<%@ page import="com.backshop.productDB.ProductDAO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문조회 - Back Shop</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/user_page_css/user_cart.css">
</head>

<% 
    // 세션에서 사용자 ID 가져오기
    HttpSession session2 = request.getSession(false);
    String loginId = (String) session2.getAttribute("id2"); // 세션에서 ID 가져오기
    MemberDTO user = null;

    if (loginId != null) {
        MemberDAO mDao = new MemberDAO();
        user = mDao.getUserInfo(loginId);  // 로그인된 사용자 정보 가져오기
        if (user == null) {
            response.sendRedirect("LogError.jsp"); // 사용자 정보가 없으면 에러 페이지로 이동
            return;
        }
    } else {
        response.sendRedirect("login_page/Login.jsp");  // 로그인되지 않으면 로그인 페이지로 이동
        return;
    }
    
 // 장바구니 항목 불러오기
    CartDAO cartDAO = new CartDAO();
    List<CartDTO> cartItems = cartDAO.getCartItems(loginId);
    
    %>

<body>
    <!-- 헤더 -->
    <%@ include file="user_module/user_header.jsp" %>

    <main>
        <section id="mypage_container">
        
            <div class="sidebar_section">
                <div class="profile_image">
                    <img src="<%= request.getContextPath() %>/img/user_icon.png" alt="사용자 이미지">
                </div>
                <h2><%= user.getAlias() %>님</h2>
                <nav class="sidebar_menu">
                    <ul>
                        <li><a href="user_mypage.jsp">회원정보</a></li>
                        <li><a href="user_order.jsp">주문조회</a></li>
                        <li><a href="user_cart.jsp">장바구니</a></li>
                        <li><a href="#">고객센터</a></li>
                    </ul>
                </nav>
            </div>

            <div class="order_info">
                <h2>장바구니</h2>
                
                <% 
                 // 장바구니 항목이 있을 경우 반복하여 출력
                 if (cartItems != null && !cartItems.isEmpty()) {
                        for (CartDTO item : cartItems) {
                %>
                     <div class="order_item">
                        <img src="<%= request.getContextPath() + "/img_uploads/" + item.getProductImage() %>" alt="<%= item.getProductName() %>">
                        <div class="item_details">
                          <p><%= item.getProductName() %></p>
                          <p>₩<%= item.getProductPrice() %></p>
                          <p>x <%= item.getQuantity() %></p>
                          
                          
                          <form action="<%= request.getContextPath() %>/RemoveFromServlet" method="post">
                            <input type="hidden" name="cart_item_no" value="<%= item.getNo() %>">
                             <button type="submit" class="cancel_button">제거</button>
                           </form>
                        </div>
                   </div>
<% 
        }
    } else {
%>
    <p>장바구니에 항목이 없습니다.</p>
<% 
    }
%>

                <!-- 주문하기 버튼 -->
                <% if (cartItems != null && !cartItems.isEmpty()) { %>
                <form action="<%= request.getContextPath() %>/OrderProcessingServlet" method="post">
                    <input type="hidden" name="userId" value="<%= loginId %>"> <!-- 로그인 ID를 hidden 필드로 전달 -->
                    <button type="submit" class="order_button">주문하기</button>
                </form>
                <% } %>
                
            </div>
        </section>
    </main>
    
    <%@ include file="user_module/user_footer.jsp" %>
    
</body>
</html>
