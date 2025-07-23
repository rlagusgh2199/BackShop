<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.MemberDAO" %>
<%@ page import="member.MemberDTO" %>
<%@ page import="order.OrderDTO" %>
<%@ page import="order.OrderDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.backshop.productDB.ProductDTO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>주문조회 - Back Shop</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/user_page_css/user_mypage.css">
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
        response.sendRedirect("Login.jsp");  // 로그인되지 않으면 로그인 페이지로 이동
        return;
    }
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
                <h2>주문조회</h2>
                
                <% 
                OrderDAO orderDAO = new OrderDAO();
                // 주문 후 사용자 주문 내역을 다시 가져와서 request에 설정
                List<OrderDTO> orders = orderDAO.getOrdersByUserId(loginId);

                // 주문 정보가 있을 경우 request에 설정
                if (orders != null && !orders.isEmpty()) {
                    // 정상적으로 주문 내역이 있을 경우
                    request.setAttribute("orders", orders);
                } else {
                    // 주문 내역이 없을 경우
                    request.setAttribute("message", "주문 내역이 없습니다.");
                }
                
                    // 세션에서 전달받은 주문 정보 리스트를 가져오기
                    // List<OrderDTO> orders = (List<OrderDTO>) request.getAttribute("orders");

                    if (orders != null) {
                        for (OrderDTO order : orders) {
                %>
                    <div class="order_item">
                        <img src="<%= request.getContextPath() + "/img_uploads/" + order.getProduct_image() %>" alt="<%= order.getProductName() %>">
                        <div class="item_details">
                          <p><%= order.getProductName() %></p>
                          <p>₩<%= order.getProductPrice() %></p>
                          <p>x <%= order.getQuantity() %></p>
                          <p>주문상태: <%= order.getOrderStatus() %></p> <!-- 주문 상태 표시만 -->
                        </div>
                   </div>
                <% 
                        }
                    } else {
                %>
                    <p>주문 내역이 없습니다.</p>
                <% 
                    }
                %>
                
                
                
                <%-- <div class="order_item">
                    <img src="<%= request.getContextPath() %>/img/shirt1.png" alt="남색 체크무늬 여름티">
                    <div class="item_details">
                        <p>남색 체크무늬 여름티</p>
                        <p>$12000</p>
                        <p>x 1</p>
                        <button class="review_button">리뷰 작성</button>
                        <button class="cancel_button">주문 취소</button>
                    </div>
                </div>

                <div class="order_item">
                    <img src="<%= request.getContextPath() %>/img/shirt2.png" alt="흰색 여름티">
                    <div class="item_details">
                        <p>흰색 여름티</p>
                        <p>$12000</p>
                        <p>x 1</p>
                        <button class="review_button">리뷰 작성</button>
                        <button class="cancel_button">주문 취소</button>
                    </div>
                </div> --%>
            </div>
        </section>
    </main>
    
    <%@ include file="user_module/user_footer.jsp" %>
    
</body>
</html>
