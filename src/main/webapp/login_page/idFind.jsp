<%@page import="member.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/login_css/idFind.css"> <!-- header css -->
</head>
<body>

<header>
	<a href="<%= request.getContextPath() %>/productList_desc">
     <h1 class="up">Back #</h1>
	</a>
</header>

<%
String alias = (String)session.getAttribute("alias");
String id2 = (String)session.getAttribute("id2");//아이디 가져오기
String message = "";

if (id2 != null) { //아이디찾기성공
	message = alias + "님의 찾으시는 아이디는 "+id2+"입니다.";  
    message += "<a href='" + request.getContextPath() + "/login_page/Login.jsp'>" +
            "<input type='button' value='로그인'></a>";
} else if(alias == null || alias.isEmpty()){
	message = "";
} else { //실패
	message = "별명을 다시한번 확인해주세요.";
}
%>

<div class="id_form">
 <section class="id_box">
        <form action="<%=request.getContextPath()%>/IdFind.do" method="get">
            <label><input type="radio" checked/>별명으로 인증</label>
        	<br><br>
        	<input name="alias" type="text" placeholder="별명 입력" style="width: 300px; height:20px;" />
       		<input type="submit" value="인증"> 
       		
		
       		<p>
       		<%=message %>
       		</p>
        </form>
    
        
    
    	 
  </section>   
</div>


<jsp:include page="/user_page/user_module/user_footer.jsp"/>

</body>
</html>