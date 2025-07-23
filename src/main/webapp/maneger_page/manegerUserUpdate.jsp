<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="maneger.ManegerDAO" %>
<%@ page import="maneger.ManegerDTO" %>
<%
String id2 = request.getParameter("id2");
ManegerDAO mdao = new ManegerDAO();
ManegerDTO user = mdao.getUserInfo(id2); // 사용자 정보 가져오기

if (user == null) {
    out.println("<p>사용자 정보를 찾을 수 없습니다.</p>");
    return; // 사용자 정보가 없으면 종료
}
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/maneger_page_css/maneger_user_update.css"> <!-- CSS 링크 -->
</head>
<body>
    <h1>회원 정보 수정</h1>
    <form action="<%= request.getContextPath() %>/ManegerUserUpdate" method="post">
        <input type="hidden" name="id2" value="<%= user.getId2() %>">
        
        <label for="alias">별칭</label>
        <input type="text" name="alias" value="<%= user.getAlias() %>"class="short" required><br>
        
        <label for="address">주소</label>
        <input type="text" name="address" value="<%= user.getAddress() %>" class="short"><br>
        
        <label for="email">이메일</label>
        <input type="email" name="email" value="<%= user.getEmail() %>" class="short"><br>
        
        <label for="gender">성별</label>
        <select name="gender">
            <option value="남성" <%= "남성".equals(user.getGender()) ? "selected" : "" %>>남성</option>
            <option value="여성" <%= "여성".equals(user.getGender()) ? "selected" : "" %>>여성</option>
            <option value="선택 안함" <%= "선택 안함".equals(user.getGender()) ? "selected" : "" %>>선택 안함</option>
        </select><br>
        
        <label for="favorite">관심 분야</label>
        <select id="favorite" name="favorite">
            <option value="셔츠" <%= "셔츠".equals(user.getFavorite()) ? "selected" : "" %>>셔츠</option>
            <option value="바지" <%= "바지".equals(user.getFavorite()) ? "selected" : "" %>>바지</option>
            <option value="선택 안함" <%= "선택 안함".equals(user.getFavorite()) ? "selected" : "" %>>선택 안함</option>
        </select>
        <br>
        
        <input type="submit" value="정보 수정">
    </form>
</body>
</html>
