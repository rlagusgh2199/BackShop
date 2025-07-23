<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage=""%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
</head>
<body>
<%
    String userData = (String) request.getAttribute("userData");
%>
<h1>User Information</h1>
<pre><%= userData %></pre>
<h2><%= userData %></h2>
</body>
</html>