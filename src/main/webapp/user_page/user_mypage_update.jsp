<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="member.MemberDTO" %>
<%@ page import="member.MemberDAO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원정보 수정 - Back Shop</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/user_page_css/user_update.css">
</head>
<body>
    <% 
    // 세션에서 사용자 정보 가져오기
    HttpSession session1 = request.getSession(false);
    String loginId = (String) session1.getAttribute("id2"); // ID로 변경
    MemberDTO user = null;

    if (loginId != null) {
        MemberDAO mDao = new MemberDAO();
        user = mDao.getUserInfo(loginId);  // 로그인된 사용자 정보 가져오기
    } else {
        response.sendRedirect("Login.jsp");  // 로그인되지 않으면 로그인 페이지로 이동
        return;
    }
    %>

    <h1>회원정보 수정</h1>
    <form action="user_mypage_update_action.jsp" method="post">
        <input type="hidden" name="id2" value="<%= user.getId2() %>">
        
        <label for="alias">별명:</label>
        <input type="text" id="alias" name="alias" value="<%= user.getAlias() %>" required>
        <br>
        
        <label for="email">이메일:</label>
        <input type="email" id="email" name="email" value="<%= user.getEmail() %>">
        <br>

        <label for="gender">성별:</label>
        <select id="gender" name="gender">
            <option value="남성" <%= "남성".equals(user.getGender()) ? "selected" : "" %>>남성</option>
            <option value="여성" <%= "여성".equals(user.getGender()) ? "selected" : "" %>>여성</option>
            <option value="선택 안함" <%= "선택 안함".equals(user.getGender()) ? "selected" : "" %>>선택 안함</option>
        </select>
        <br>

        <label for="favorite">관심분야:</label>
        <select id="favorite" name="favorite">
            <option value="셔츠" <%= "셔츠".equals(user.getFavorite()) ? "selected" : "" %>>셔츠</option>
            <option value="바지" <%= "바지".equals(user.getFavorite()) ? "selected" : "" %>>바지</option>
            <option value="선택 안함" <%= "선택 안함".equals(user.getFavorite()) ? "selected" : "" %>>선택 안함</option>
        </select>
        <br>
        
        <label for="address">주소:</label>
        <input type="text" id="address" name="address" value="<%= user.getAddress() %>">
        <br>

        <button type="submit">수정 완료</button>
    </form>
</body>
</html>
