<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Back Shop</title>
   <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/user_page_css/user_header.css">
</head>

<body>
    <header>
       <%
       
       String mem_id = (String)session.getAttribute("idKey"); //db연동성공
       String alias = (String) session.getAttribute("alias"); // 별칭 가져오기
       String appAlias = (String) application.getAttribute("user_"); // Application에서 별칭 가져오기 (로그인 유지)
       
        // request에서 nickname을 가져오기
        String nickname = (String) session.getAttribute("nickname");  // request로 nickname 가져오기
       // 로그인 상태 확인
         String log = "";
       String mem = "";
       
       if (mem_id != null && alias != null ) { 
           // 세션 로그인 상태
           log = "<a id='login' href='" + request.getContextPath() + "/login.do' style='margin-right: 50px;'>"
                 + alias + "님, 로그아웃</a>";
       } else if (appAlias != null) {
           // 로그인 유지 상태
           log = "<a id='login' href='" + request.getContextPath() + "/login.do' style='margin-right: 50px;'>"
                 + appAlias + "님, 로그아웃</a>";
       } else {
           // 비로그인 상태
           log = "<a id='login' href='" + request.getContextPath() + "/login_page/Login.jsp' style='margin-right: 50px;'>로그인</a>";
           mem = "<a id='login' href='" + request.getContextPath() + "/login_page/member.jsp'> 회원가입 </a>";
       }


       // 회원가입 링크 설정
       
       if (mem_id == null && appAlias == null && nickname == null ) {
           mem = "<a id='login' href='" + request.getContextPath() + "/login_page/member.jsp'> 회원가입 </a>";
       }
       
       
       // nickname을 활용하여 '님, 로그아웃' 형태로 표시
        if (nickname != null) {
            log = "<a id='login' href='" + request.getContextPath() + "/login.do' style='margin-right: 50px;'>"
                  + nickname + "님, 로그아웃</a>";
        }
       
       %>
       
       
       <section id="top">
           <a id="main_logo" href="<%= request.getContextPath() %>/productList_desc">Back #</a> <!-- Back# main logo -->
           
           <form id="search" action="#" method="get">
            <input type="text" name="#" placeholder="검색어를 입력하세요"> <!-- DB할 때 name 지정 -->
         </form>
         
         <%=log %>
          <%=mem %>
           
           <a id="mypage" 
            href="<%= (mem_id != null || appAlias != null || nickname != null) ? request.getContextPath() + "/user_page/user_mypage.jsp" : request.getContextPath() + "/login_page/Login.jsp" %>">
             <img src="<%= request.getContextPath() %>/img/user_icon.png" alt="마이페이지" id="user_icon">
         </a>

         </section> <!-- section top -->
         
         <div class="clear"></div>
         
         <section id="category">
            <nav id = "category_menu">
              <ul>
                <li><a href="<%= request.getContextPath() %>/ProductUserCategoryShirt">SHIRT</a></li>
                <li><a href="<%= request.getContextPath() %>/ProductUserCategoryPants">PANTS</a></li>
                <li><a href="<%= request.getContextPath() %>/today">오늘의 코디</a></li>
                <li><a href="<%= request.getContextPath() %>/productList_desc">신상</a></li>
                <li><a href="<%= request.getContextPath() %>/user_mine">나만의 코디</a></li>
              </ul>
           </nav>
       </section>      <!-- section category -->
    </header>
</body>
</html>
