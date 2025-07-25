<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/maneger_page_css/maneger_main.css">
    <title>관리자 페이지</title>
</head>
<body>

	<%@ include file="maneger_module/maneger_header.jsp" %>
    
    <section id="main">
        <h1>리뷰 목록</h1>
        <table>
            <thead>
                <tr>
                    <th>리뷰번호</th>
                    <th>상품 이름</th>
                    <th>고객아이디</th>
                    <th>별점</th>
                    <th>리뷰 내용</th>
                    <th>관리</th>
                </tr>
            </thead>
            
            <tbody> <!-- DB로 추가 시 함수?? -->
                <tr>
                    <td>1</td>
                    <td>아이폰450</td>
                    <td>qwert1234</td>
                    <td>★★★★☆</td>
                    <td>최고입니다...!!!</td>
                    <td class="delete"><button class="delete_button">삭제</button></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>갤럭시215</td>
                    <td>qwer1234</td>
                    <td>★★★★★</td>
                    <td>정말 좋습니다!</td>
                    <td class="delete"><button class="delete_button">삭제</button></td>
                </tr>
                <!-- 추가 행은 여기에 -->
            </tbody>
        </table>
    </section>
</body>
</html>
