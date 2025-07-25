<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>

<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/login_css/pwFind.css"> <!-- header css -->
</head>
<body>

<header>
	<a href="<%= request.getContextPath() %>/productList_desc">
     <h1 class="up">Back #</h1>
	</a>
</header>

<%
String id2 = (String)session.getAttribute("id2");
String alias = (String)session.getAttribute("alias");
String pwd1 = (String)session.getAttribute("pwd1");//비밀번호 가져오기
String message= "";

if (pwd1 != null) { //비밀번호찾기성공
	message = alias + "님의 찾으시는 비밀번호는 "+pwd1+"입니다.";  
    message += "<a href='" + request.getContextPath() + "/login_page/Login.jsp'>" +
            "<input type='button' value='로그인'></a>";
} else if(id2 == null || id2.isEmpty() || alias == null || alias.isEmpty()){
	message = "";
} else { //실패
	message = "아이디나 별명을 다시한번 확인해주세요.";
}
%>


<div class="id_form">
 <section class="id_box">
        <form action="<%=request.getContextPath()%>/PwFind.do" method="get">
            <label><input type="radio" checked/>회원정보로 인증</label>
        	<br><br>
        	<input name="id" type="text" placeholder="아이디 입력" style="width: 300px; height:20px;"/>
 
        	<input name="alias" type="text" placeholder="별명 입력" style="width: 300px; height:20px;"/>
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