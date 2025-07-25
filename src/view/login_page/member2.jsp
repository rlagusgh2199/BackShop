<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage=""%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Back Shop 개인정보</title>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/login_css/member2.css">


	<script>	
		// 서버에서 아이디 중복 상태를 가져옵니다
    	let isIdDuplicate = <%= session.getAttribute("idDuplicate") != null ? session.getAttribute("idDuplicate") : "false" %>;
    	// 서버에서 별명 중복 상태를 가져옵니다
    	let isAliasDuplicate = <%= session.getAttribute("aliasDuplicate") != null ? session.getAttribute("aliasDuplicate") : "false" %>;
	
        // "다시하기" 버튼을 눌렀을 때 캡차 이미지 새로 고침
        function reloadCaptcha() {
            var captchaImage = document.getElementById('captchaImage');
            // 캡차 이미지를 새로 고침 (src URL에 타임스탬프를 추가하여 캐시를 방지)
            captchaImage.src = 'stickyImg?' + new Date().getTime();
        }
                 
     	// 서버에서 캡차 정답을 가져오기
        let correctAnswer = "<%= session.getAttribute("captchaText") != null ? session.getAttribute("captchaText") : "" %>";
              
        // 입력 필수값과 CAPTCHA 검증 후 버튼 활성화
        function checkFormValidity() {
        	
        	
            const id = document.getElementById('id').value.trim(); //문자열 앞뒤 공백제거
            const pwd1 = document.getElementById('pwd1').value.trim();
            const pwd2 = document.getElementById('pwd2').value.trim();
            const alias = document.getElementById('alias').value.trim();
            const address = document.getElementById('address').value.trim();
            const email = document.getElementById('email1').value.trim();
            const captchaAnswer = document.getElementById('captchaAnswer').value.trim();
            const idMessage = document.getElementById('idMessage');
            const passwordMessage = document.getElementById('passwordMessage');
            const aliasMessage = document.getElementById('aliasMessage');
            
            
            
         	// 비밀번호 일치 여부 확인
            if (pwd1 && pwd2 && pwd1 !== pwd2) {
                passwordMessage.textContent = "비밀번호가 일치하지 않습니다.";
                passwordMessage.style.color = "red";         
                return;
            } else {
                passwordMessage.textContent = pwd1 && pwd2 ? "비밀번호가 일치합니다." : "";
                passwordMessage.style.color = "green";
            }
            
            // 아이디와 비밀번호 최소 6자리 확인
            if (id.length < 6) {
            	idMessage.textContent = "아이디는 최소 6자리 이상이어야 합니다."; 	
            	idMessage.style.color = "red";          	
                return;
            } else {
                idMessage.textContent = ""; // 유효하면 오류 메시지 지우기
            }
            
            if (pwd1.length < 6) {
            	passwordMessage.textContent = "비밀번호는 최소 6자리 이상이어야 합니다."; 
            	passwordMessage.style.color = "red";	
                return;
            } else {
                idMessage.textContent = ""; // 유효하면 오류 메시지 지우기
            }
           	
            if (alias.length < 2) {
            	aliasMessage.textContent = "별명은 최소 2자리 이상이어야 합니다."; 	
            	aliasMessage.style.color = "red";          	
                return;
            } else {
            	aliasMessage.textContent = ""; // 유효하면 오류 메시지 지우기
            }
            
            
            
         	// 아이디 중복 여부 확인
            if (isIdDuplicate) {
                idMessage.textContent = "이미 사용 중인 아이디입니다.";
                idMessage.style.color = "red";
                return;
            }
            
         	// 별명 중복 여부 확인
            if (isAliasDuplicate) {
                aliasMessage.textContent = "이미 사용 중인 별명입니다.";
                aliasMessage.style.color = "red";
                return;
            }
            
            // 필수 입력값 확인(address && email 는 선택사항임->빈칸가능)
            const isFormValid = id && pwd1 && pwd2 && (pwd1 === pwd2) && alias && captchaAnswer === correctAnswer;
        
            // 모든 조건(필수 값 입력하고, 캡차인증 성공하면) 충족 시 버튼 활성화
            
            
         // 모든 검증을 통과하면 로그인 페이지로 리다이렉트
            if (isFormValid) {
                alert("회원가입이 완료되었습니다!!!");
                window.location.href = "Login.jsp"; // 페이지 이동
            }
        }  
        
     
    </script>
 	
</head>
<body>
<% 
	String message = (String) request.getAttribute("message");   
	String message1 = (String) request.getAttribute("message1"); //아이디
	String message2 = (String) request.getAttribute("message2"); //별명
	String id2 = request.getParameter("id");
	String alias = request.getParameter("alias");
	 
//요청 인코딩 설정
request.setCharacterEncoding("UTF-8");
// 응답 인코딩 설정
response.setContentType("text/html; charset=UTF-8");	
%>


<header>
	<a href="<%= request.getContextPath() %>/productList_desc">
     <h1 class="up">Back #</h1>
	</a>
</header>

<form action="stickyImg" method="post">
  <div class="section1">
        <input name="id" type="input" id="id" placeholder="아이디" value="<%= request.getParameter("id") != null ? request.getParameter("id") : "" %>" oninput="checkFormValidity()"/> 
		
        <% if (message1 != null) { %>
        	<% if (message1.equals("이미 사용 중인 아이디입니다.")) { %>
	    		  <span style="color: red;"><%= message1 %></span>
	    	<% } else if(id2.length() < 6){ %>
	    	  	  <span style="color: red;"><%= message1 %></span>
        	<% } else { %>
	    			<span style="color: green;"><%= message1 %></span>
	    	<% } %>
		<% } %>
		
         <p id="idMessage"></p>
        <input name="pwd1" type="password" id="pwd1" placeholder="비밀번호" value="<%= request.getParameter("pwd1") != null ? request.getParameter("pwd1") : "" %>"  />
         <p id="passwordMessage"></p>
        <input name="pwd2" type="password" id="pwd2" placeholder="비밀번호확인" value="<%= request.getParameter("pwd2") != null ? request.getParameter("pwd2") : "" %>" />
        <br>
        <input name="alias" type="input" id="alias" placeholder="별명" value="<%= request.getParameter("alias") != null ? request.getParameter("alias") : "" %>" />
        <% if (message2 != null) { %>
        	<% if (message2.equals("이미 사용 중인 별명입니다.")) { %>
	    		  <span style="color: red;"><%= message2 %></span>
	    	<% } else if(alias.length() < 2){ %>
	    	  	  <span style="color: red;"><%= message2 %></span>
        	<% } else { %>
	    			<span style="color: green;"><%= message2 %></span>
	    	<% } %>
		<% } %>
         
         <p id="aliasMessage"></p>
         
         
        <input name="address" type="address" id="address" placeholder="주소" value="<%= request.getParameter("address") != null ? request.getParameter("address") : "" %>"  />
        <br>
        <input name="email" type="email" id="email1" placeholder="이메일" value="<%= request.getParameter("email") != null ? request.getParameter("email") : "" %>"  />
   		<br> 		
   	</div>
    
    
    <div class="section2">
        <div class="">
            성별 <label><input type="radio" class="radio" value="남" name="gender"/>남</label>
                <label><input type="radio" class="radio" value="여" name="gender"/>여</label>
                <label><input type="radio" class="radio" value="선택 안함" name="gender" checked/>선택 안함</label>
        </div>
    
        <br/>
    
        <div class="">
            관심분야<label><input type="radio" class="radio2" value="셔츠" name="favorite"/>셔츠</label>
                  <label><input type="radio" class="radio2" value="바지" name="favorite"/>바지</label>
                  <label><input type="radio" class="radio2" value="선택 안함" name="favorite" checked/>선택 안함</label>
        </div>
    </div>
    <hr id="hr" style="width: 300px; margin-left: 0;">
 	   


	    <div>
			<img id="captchaImage" src="<%= request.getContextPath() %>/login_page/stickyImg" alt="Captcha Image"/>
	        <!-- 서버에서 가져올 때 get방식으로  -->
	        <input type="button" id="retryButton" value="다시하기" onclick="reloadCaptcha()" />
	        <br>
	        <input name="captchaAnswer" id="captchaAnswer" placeholder="글자 입력" 
	        value="<%= request.getParameter("captchaAnswer") != null ? request.getParameter("captchaAnswer") : "" %>"
	        "/>
	        <input type="submit" value="제출하기">
	    </div>
	    <% if (message != null) { %>
        	<% if (message.equals("회원가입이 완료되었습니다!")) { %>
	    		  <p style="color: green;"><%= message %></p>
        	<% } else { %>
	    			<p style="color: red;"><%= message %></p>
	    	<% } %>
		<% } %>
	    <br>
	    <input type="button" value="회원가입" onclick="checkFormValidity()"/>
	    
</form>	   
	<!--     <audio controls autoplay src="AudioCaptcha" alt="Captcha audio"></audio>
	    <form action="AudioCaptcha" method="post">
		</form> 
	--> 
	
	
	
		
    
    	
   

</body>
</html>