<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="member.MemberDTO"%>
<%@ page import="member.MemberDAO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지 - Back Shop</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/user_page_css/user_mypage.css">
    
</head>
<body>
    <%@ include file="user_module/user_header.jsp" %>

    <main>
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
                   
            <div class="profile_info">
                <h2>회원정보</h2>
                
                <p>아이디: <%= user.getId2() %></p>
                <p>이메일: <%= user.getEmail() %></p>
                <p>별명: <%= user.getAlias() %></p>
                <p>주소: <%= user.getAddress() %></p>
                <p>성별: <%= user.getGender() %></p>
                <p>관심분야: <%= user.getFavorite() %></p>
                
                <div class="btn">
                    <!-- 탈퇴하기 버튼 수정된 곳 -->
                    <form action="<%= request.getContextPath() %>/RemoveUser" method="post">
                      <input type="hidden" name="userId" value="<%= loginId %>">
                      <button id="withdrawbtn" >탈퇴하기</button>
                    </form>
                    
                    <!-- 수정하기 버튼 -->
                    <button id="correctionbtn" onclick="location.href='user_mypage_update.jsp?id2=<%= user.getId2() %>'">수정하기</button>
                </div>
            </div>
        </section>
    </main>
    
    <%@ include file="user_module/user_footer.jsp" %>
</body>
</html>
