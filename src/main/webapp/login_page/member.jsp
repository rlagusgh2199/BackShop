<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 서버의 호스트명 (예: localhost)
    String serverName = request.getServerName();

    // 서버의 포트 (예: 8080)
    int serverPort = request.getServerPort();

    // 프로토콜 (HTTP, HTTPS)
    String protocol = request.getScheme(); // http 또는 https

    // 프로토콜 + 호스트 + 포트 결합
    String protocolHostPort = protocol + "://" + serverName + ":" + serverPort;
%>   
    
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title>회원가입 동의화면</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/login_css/member.css">
 
</head>
<body>

<div class="container">
 <a href="<%= request.getContextPath() %>/productList_desc">
	 <h1 class="up">Back #</h1>
	</a>
	<div class="terms-container left-move">
	    <label class="custom-checkbox">
	     <input type="checkbox" id="allAgree" onclick="AllCheck(this)">
	        <span class="checkmark"></span> 
				<h3>전체동의하기</h3>
		</label>
		
	</div>
	
		
	<div class="terms-container">
	    <label class="custom-checkbox">
	     <input type="checkbox" class="term-checkbox required" required onclick="checkAllAgreeStatus()">
	       <span class="checkmark"></span> 
			 <em class="option">[필수]</em> <h3>back# 이용약관</h3>
	    </label>
	    
	    <div class="term_box">  
	        개인정보보호법에 따라 back #에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및
	        이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니
	        자세히 읽은 후 동의하여 주시기 바랍니다.
	    </div>
	</div>
		
	
		
	<div class="terms-container">
	    <label class="custom-checkbox">
	     <input type="checkbox" class="term-checkbox required" required onclick="checkAllAgreeStatus()">
	      <span class="checkmark"></span> 
		   <em class="option">[필수]</em> <h3>개인정보 수집 및 이용</h3>
	    </label>
	    
	    <div class="term_box">
	    	back #이용약관, 개인정보 수집 및 이용에 동의합니다.
	    </div>
	</div>
	
	<div class="terms-container">
	    <label class="custom-checkbox">
	     <input type="checkbox" class="term-checkbox" onclick="checkAllAgreeStatus()">
	      <span class="checkmark"></span> 
		   <em class="option2">[선택]</em> <h3>추가 제공 항목 동의</h3>
	    </label>
	    
	    <div class="term_box">  
	        귀하는 이메일 주소나 성별 등의 선택 사항을 제공하지 않으셔도 서비스 이용에 불편함이 없으며, 
	        선택적으로 제공하실 수 있습니다. 선택 사항을 제공해주신 경우, 더 개인화된 서비스와 정보를 제공받을 수 있습니다.
			<br>
			동의 여부: 위와 같은 개인정보 수집 및 이용에 동의하시면 "동의"를 선택해 주세요.
	    </div>
	</div>
	<br>
	<form action="member2.jsp" method="post">
		<input type="submit" value="다음으로" class="btn_submit" id="submitButton" disabled>
	</form>
</div>





	<script>
	// 전체 동의하기 체크박스를 클릭하면 모든 체크박스를 제어하는 함수
	function AllCheck(source) {
	    const checkboxes = document.querySelectorAll('.term-checkbox');
	    
	    // 전체 동의 여부에 맞게 체크 상태를 변경
	    checkboxes.forEach(checkbox => {
	        checkbox.checked = source.checked;
	    });
	    checkAllAgreeStatus();  // 체크박스 상태 변경 후 버튼 활성화 여부 확인
	}
	
	// 필수 체크박스들만 체크되면 버튼 활성화하는 함수
	function checkAllAgreeStatus() {
		const requiredCheckboxes = document.querySelectorAll('.term-checkbox.required');
	    const submitButton = document.getElementById('submitButton');
	    //const allAgree = document.getElementById('allAgree');
	    const nextLink = document.getElementById('nextLink'); // 링크 요소
	    
	 // 전체 동의하기 체크박스가 선택되었는지 확인
	    const allChecked = Array.from(requiredCheckboxes).every(checkbox => checkbox.checked);
	    
	    // 하나라도 필수 체크박스가 선택되지 않으면 전체 동의하기 체크박스 해제
	    allAgree.checked = allChecked && Array.from(document.querySelectorAll('.term-checkbox')).every(checkbox => checkbox.checked);
	    
		
		// 필수 체크박스가 모두 선택되면 '다음으로' 버튼을 활성화
	    if (allChecked) {
	        submitButton.disabled = false;
	        nextLink.style.pointerEvents = 'auto'; // 링크 활성화
	    } else {
	        submitButton.disabled = true;
	        nextLink.style.pointerEvents = 'none'; // 링크 비활성화
	    } 
	
	}
	</script>

</body>
</html>