<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/chat_css/chat.css">

<!-- 채팅 아이콘 -->
<div id="chat-icon" onclick="toggleChat()" style="cursor: pointer; font-size: 24px;">
    💬
</div>

<!-- 채팅 컨테이너 -->
<div id="chat-container" style="display: none;"> <!-- 초기 상태를 숨김 -->
    <div id="chat-window" style="height: 300px; overflow-y: scroll;"></div>

    <div id="input-container">
	    <input type="text" id="message-input" placeholder="메시지를 입력하세요" />
	    <button id="send-button">전송</button>
	</div>
</div>

<script>
    let socket;
    const chatWindow = document.getElementById('chat-window');
    const contextPath = '<%= request.getContextPath() %>';
    const nickname = '<%= session.getAttribute("alias") != null ? session.getAttribute("alias") : "" %>'; // 별명이 null일 경우 빈 문자열로 설정
    const dropArea = document.getElementById('chat-container');

    function connect() {
        socket = new WebSocket('ws://' + window.location.host + contextPath + '/chat');

        socket.onopen = function() {
            console.log("Connected to the chat server");
        };

        socket.onmessage = function(event) {
            console.log("Received message:", event.data); // 수신된 메시지 로그
            try {
                const data = JSON.parse(event.data); // JSON 파싱
                console.log("Parsed data:", data); // 파싱한 데이터 로그

                const newMessage = document.createElement('div'); // 새 메시지 div 생성
                const currentTime = new Date().toLocaleTimeString(); // 현재 시간 가져오기

                if (data.image) {
                    // 이미지 메시지 처리
                    if (data.alias === nickname) {
                        // 발신자가 현재 사용자일 경우
                        newMessage.className = 'message user-message';
                        newMessage.innerHTML = '<div class="message-user-container">' +
                        					   '<span class="timestamp-user">' + currentTime + '</span>' +
                       						   '<img src="' + data.image + '" alt="Image" style="max-width: 100%;">';
                    } else {
                        // 수신자일 경우
                        newMessage.className = 'message other-message';
                        newMessage.innerHTML = '<span class="username">' + escapeHtml(data.alias) + '</span>' +
                       						   '<div class="message-container">' +
                                               		'<img src="' + data.image + '" alt="Image" style="max-width: 100%;">' +
                                              	 	'<span class="timestamp">' + currentTime + '</span>' +
                                               '</div>';
                    }
                } else {
                    // 일반 메시지 처리
                    if (data.alias === nickname) {
                        newMessage.className = 'message user-message';
                        newMessage.innerHTML = '<div class="message-user-container">' +
                        '<span class="timestamp-user">' + currentTime + '</span>' +
                        '<span class="message-user-text">' + escapeHtml(data.message) + '</span>' +
                        '</div>';

                    } else {
                    	newMessage.className = 'message other-message';
                    	newMessage.innerHTML = 
                    	    '<span class="username">' + escapeHtml(data.alias) + '</span>' +
                    	    '<div class="message-container">' +
                    	        '<span class="message-text">' + escapeHtml(data.message) + '</span>' +
                    	        '<span class="timestamp">' + currentTime + '</span>' +
                    	    '</div>';

                    }
                }

                // 채팅창에 새 메시지를 추가
                chatWindow.appendChild(newMessage);
                chatWindow.scrollTop = chatWindow.scrollHeight; // 스크롤을 맨 아래로
            } catch (error) {
                console.error("Error parsing message data:", error); // JSON 파싱 오류 로그
            }
        };



        socket.onclose = function(event) {
            console.log("Disconnected from the chat server", event);
        };

        socket.onerror = function(error) {
            console.error("WebSocket error: ", error);
        };
    }

    const input = document.getElementById('message-input');

	 // 엔터 키를 눌렀을 때 메시지를 전송
	 input.addEventListener('keydown', function(event) {
	     if (event.key === 'Enter') {
	         event.preventDefault(); // 기본 동작 방지 (줄바꿈 방지)
	         document.getElementById('send-button').click(); // 전송 버튼 클릭 이벤트 발생
	     }
	 });
	
	 document.getElementById('send-button').onclick = function() {
		    const message = input.value.trim(); // 입력값에서 공백 제거
		    const alias = nickname; // 별명 변수

		    console.log("Input message:", message); // 입력된 메시지 로그

		    if (message === '') {
		        alert("메시지를 입력하세요.");
		        return; // 메시지가 비어있으면 전송하지 않음
		    } else if (alias === null || alias === '') { // 별명이 null이거나 빈 문자열인 경우
		        alert("메시지를 전송하시려면 로그인이 필요합니다.");
		        return; // 별명이 없으면 전송하지 않음
		    }

		    const formattedMessage = JSON.stringify({ alias: alias, message: message });
		    console.log("Sending message:", formattedMessage); // 전송 메시지 로그

		    if (socket.readyState === WebSocket.OPEN) {
		        socket.send(formattedMessage);
		    } else {
		        console.error("WebSocket is not open. Ready state: " + socket.readyState);
		    }

		    input.value = ''; // 입력 필드 초기화
		};



    // 드래그 앤 드롭 이벤트 처리
    dropArea.addEventListener('dragover', (event) => {
        event.preventDefault();
        dropArea.classList.add('hover');
    });

    dropArea.addEventListener('dragleave', () => {
        dropArea.classList.remove('hover');
    });

    dropArea.addEventListener('drop', (event) => {
        event.preventDefault();
        dropArea.classList.remove('hover');

        const files = event.dataTransfer.files; // 드롭된 파일 목록
        if (files.length > 0) {
            const file = files[0]; // 첫 번째 파일 선택

            // 이미지 리사이즈 후 전송
            resizeImage(file, (resizedImage) => {
                const imageMessage = {
                    alias: nickname,
                    message: file.name, // 파일 이름 저장
                    image: resizedImage // 리사이즈된 base64 이미지 데이터
                };
                console.log("Sending image message:", JSON.stringify(imageMessage)); // JSON 문자열 로그
                socket.send(JSON.stringify(imageMessage)); // JSON 문자열로 전송
            });
        }
    });

    function toggleChat() {
        const chatContainer = document.getElementById('chat-container');
        if (chatContainer.style.display === 'none' || chatContainer.style.display === '') {
            chatContainer.style.display = 'flex'; //block 또는 'flex'
        } else {
            chatContainer.style.display = 'none';
        }
    }

    function escapeHtml(unsafe) {
        return unsafe
            .replace(/&/g, "&amp;")
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;")
            .replace(/"/g, "&quot;")
            .replace(/'/g, "&#039;");
    }
    
    function resizeImage(file, callback) {
        const reader = new FileReader();
        reader.onload = function(event) {
            const img = new Image();
            img.onload = function() {
                const canvas = document.createElement('canvas');
                const ctx = canvas.getContext('2d');
                const MAX_WIDTH = 75; // 최대 너비 설정
                const MAX_HEIGHT = 100; // 최대 높이 설정
                let width = img.width;
                let height = img.height;

                if (width > height) {
                    if (width > MAX_WIDTH) {
                        height *= MAX_WIDTH / width;
                        width = MAX_WIDTH;
                    }
                } else {
                    if (height > MAX_HEIGHT) {
                        width *= MAX_HEIGHT / height;
                        height = MAX_HEIGHT;
                    }
                }

                canvas.width = width;
                canvas.height = height;
                ctx.drawImage(img, 0, 0, width, height);
                callback(canvas.toDataURL('image/jpeg', 0.7)); // JPEG 형식으로 압축
            };
            img.src = event.target.result;
        };
        reader.readAsDataURL(file);
    }


    window.onload = connect;
</script>
