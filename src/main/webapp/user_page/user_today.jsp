<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.backshop.weather.ApiItem" %>
<%@ page import="com.backshop.productDB.ProductDTO" %>

<%
    List<ProductDTO> productList = (List<ProductDTO>) session.getAttribute("productList");
    String temperatureStr = (String) session.getAttribute("temperature");
    double currentTemperature = (temperatureStr != null) ? Double.parseDouble(temperatureStr) : 0.0; // 초기화

 // 상품 목록 정렬 로직
    if (productList != null) {
        final double temp = currentTemperature; // 현재 온도

        // 온도 차이가 15도 이하인 상품만 필터링
        productList.removeIf(product -> Math.abs(product.getProductTemperature() - temp) > 15);

        // 필터링된 상품 목록 정렬
        productList.sort((p1, p2) -> {
            double diff1 = Math.abs(p1.getProductTemperature() - temp);
            double diff2 = Math.abs(p2.getProductTemperature() - temp);
            return Double.compare(diff1, diff2);
        });
    }


    // 날씨 정보 처리
    String weatherMessage = "날씨 정보를 가져오지 못했습니다."; // 기본 메시지
    @SuppressWarnings("unchecked")
    List<ApiItem> weatherItems = (List<ApiItem>) session.getAttribute("weatherItems");
    
    if (weatherItems != null) {
        String weatherStatus = "흐림"; // 기본 날씨 상태
        String windSpeed = "0"; // 바람 속도
        String rainStatus = "없음"; // 강수 상태
        double humidity = 0; // 습도 초기화
        double rainAmount = 0; // 강수량 초기화
        
        for (ApiItem item : weatherItems) {
            switch (item.getCategory()) {
                case "VVV":
                    windSpeed = item.getObsrValue(); // 바람 속도
                    break;
                case "PTY":
                    rainStatus = item.getObsrValue().equals("0") ? "없음" : "있음"; // 강수 여부
                    break;
                case "REH":
                    humidity = Double.parseDouble(item.getObsrValue()); // 습도
                    break;
                case "T1H":
                    currentTemperature = Double.parseDouble(item.getObsrValue()); // 기온 업데이트
                    break;
                case "RN1":
                    rainAmount = Double.parseDouble(item.getObsrValue()); // 1시간 강수량
                    break;
            }
        }

        // 날씨 메시지 생성
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append(String.format("현재 기온은 %.1f°로, 추운 날씨입니다. ", currentTemperature));

        if (humidity > 70) {
            messageBuilder.append(String.format("현재 습도는 %.1f%%로, 다소 답답한 날씨가 예상됩니다. ", humidity));
        } else if (humidity < 70) {
            messageBuilder.append(String.format("현재 습도는 %.1f%%로 쾌적하지만, 외출 시 따뜻한 옷을 챙기는 것이 좋습니다. ", humidity));
        }

        if ("있음".equals(rainStatus) || rainAmount > 0) {
            messageBuilder.append(String.format("현재 비가 내리고 있습니다. 강수량은 %.1fmm입니다. ", rainAmount));
        } else {
            messageBuilder.append("현재 비는 내리지 않고 있으며, ");
        }

        messageBuilder.append(String.format("바람은 약간 불고 있습니다(풍속:%sm/s).<br> ", windSpeed));
        messageBuilder.append("이런 날씨에는 두꺼운 니트나 외투와 함께 따뜻한 바지를 추천합니다.<br>");
        messageBuilder.append("<span class='highlight'>저희 Back # 쇼핑몰에서 다양한 스타일의 아이템을 확인해 보세요!</span>");

        
        weatherMessage = messageBuilder.toString();
    }
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Back Shop</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/user_page_css/user_main.css">
</head>

<body>
    <!-- 헤더  -->
    <%@ include file="user_module/user_header.jsp" %>
    
    <div class="clear"></div>
    <div class="banner"></div>
    
    <div class="clear"></div>
    <div class="weather-info">
    
        <div class="weather-item">
        	 <%= weatherMessage %> <!-- 동적 날씨 메시지 출력 -->
        </div>
    </div>
    <h2>오늘의 코디</h2>
    <section id="main">
        <div class="product-list">
            <%
                if (productList != null && !productList.isEmpty()) {
                    for (ProductDTO product : productList) {
            %>
	                <div class="product-item">
	                <a href="<%= request.getContextPath() %>/ProductServlet?product_id=<%= product.getId() %>">
	                    <img src="<%= request.getContextPath() + "/img_uploads/" + product.getProductImage() %>" alt="<%= product.getProductName() %>">
	                </a>
	                    <h3><%= product.getProductName() %></h3>
	                    <p>₩<%= product.getProductPrice() %></p>
	                    <form action="<%= request.getContextPath() %>/AddToCartServlet" method="post">
		                    <input type="hidden" name="product_id" value="<%= product.getId() %>">
		                    <input type="hidden" name="product_name" value="<%= product.getProductName() %>">
		                    <input type="hidden" name="product_price" value="<%= product.getProductPrice() %>">
		                    <input type="hidden" name="product_image" value="<%= product.getProductImage() %>">
		                    <input type="hidden" name="quantity" value="<%= 1 %>">
	                   		<button type="submit">주문하기</button>
	                    </form>
	                </div>
            <%
                    }
                } else {
            %>
                    <p>상품이 없습니다.</p>
            <%
                    System.out.println("productList가 null이거나 비어 있습니다.");
                }
            %>
        </div>
    </section>
    
    <%@ include file="user_module/user_chat.jsp" %>
    
    <%@ include file="user_module/user_footer.jsp" %>
</body>
</html>
