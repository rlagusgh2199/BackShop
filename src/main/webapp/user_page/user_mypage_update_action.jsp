<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="member.MemberDAO" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<%
    String id2 = request.getParameter("id2");
    String email = request.getParameter("email");
    String gender = request.getParameter("gender");
    String favorite = request.getParameter("favorite");
    String alias = request.getParameter("alias"); // 별명 추가
    String address = request.getParameter("address"); // 주소 추가

    MemberDAO dao = new MemberDAO();
    // 수정 메서드 호출 시 별명과 주소도 포함
    boolean isUpdated = dao.updateUser(id2, email, gender, favorite, alias, address);

    if (isUpdated) {
        out.println("<script>alert('회원정보가 수정되었습니다.'); location.href='user_mypage.jsp';</script>");
    } else {
        out.println("<script>alert('회원정보 수정에 실패했습니다. 다시 시도해주세요.'); history.back();</script>");
    }
%>
