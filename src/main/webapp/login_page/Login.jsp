<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="error/errPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Back Shop 로그인</title>
 <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/login_css/Login.css">

</head>
<body>

<% 
String projectName = request.getContextPath(); //프로젝트명

String check = (String)session.getAttribute("loginCheck"); //체크x
String check2 = (String)application.getAttribute("loginCheck2"); //체크o
String id = (String) session.getAttribute("loginName");


if(check == null && check2 == null) {//빈 폼
%>

<header>
	<a href="<%= request.getContextPath() %>/productList_desc">
     <h1 class="up">Back #</h1>
	</a>
</header>
	
<div class="login_form">
	<section class="login_box">
		<form action="<%=request.getContextPath()%>/login.do" method="post">
		 
			아이디 :<input type="text" name="id" placeholder="아이디 입력" style="width: 200px; height:20px;"> <br> 
			비밀번호 :<input type="password" name="pw" placeholder="비밀번호 입력" style="width: 200px; height:20px;"> <br> 
			<div id="space">
			<input type="submit"value="로그인"> 
			<input type="reset" value="취소" style="margin-right: 50px;"> 
			<a href="member.jsp"><input type="button" value="회원가입"> </a>
			<br>
		    <a href="idFind.jsp" class="find" style="margin-left: 50px;">아이디 찾기</a> <span style="display:inline-block; width:5px;"></span>
		    <a href="pwFind.jsp" class="find" style="margin-left: 10px;">비밀번호 찾기</a>
		    </div>
		</form>
	</section>

	<section id="SNS">
		<h3>SNS 연동</h3><br>
		<a href="<%= projectName %>/login_page/KakaoAuth"> 
			<img src="<%=projectName%>/images/kakao_login_medium_narrow.png" alt="카카오 로그인" />
			<img src="<%=projectName%>/images/btnW_완성형.png" alt="네이버 로그인" style="width:200px; height:45px;"/>
		</a>	
	</section>
	
	
</div>


<jsp:include page="/user_page/user_module/user_footer.jsp"/>






<%// 로그인 유지 체크된상태
} else if("on".equals(application.getAttribute("keepLoginCheck"))){
%>
	<form action="<%=request.getContextPath()%>/LoginServlet" method="get">
		<%= application.getAttribute("user_" ) %> 님, 로그인 유지 <input type="submit" value="로그아웃">
	</form>
<%
} else {// 로그아웃
%>

	<form action="<%=request.getContextPath()%>/LoginServlet" method="get">
		<%= session.getAttribute("loginName")%> 님, <input type="submit" value="로그아웃">
	</form>
<% 
}
%>




</body>
</html>