<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="order.OrderDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/maneger_page_css/maneger_main.css">
    <title>주문 관리</title>
</head>
<body>

    <%@ include file="maneger_module/maneger_header.jsp" %>

    <section id="main">
        <h2>전체 고객 주문 목록</h2>
        <table>
            <thead>
                <tr>
                    <th>상품명</th>
                    <th>주문 일자</th>
                    <th>주문 고객</th>
                    <th>주문 번호</th>
                    <th>주문 상태</th>
                    <th>작업</th>
                </tr>
            </thead>
            <tbody>
                <% 
                List<OrderDTO> orders = (List<OrderDTO>) session.getAttribute("orders");
                for (OrderDTO order : orders) {
            %>
                <tr>
                    <td><%= order.getProductName() %></td>
                    <td><%= order.getOrderDate() %></td>
                    <td><%= order.getUserId() %></td>
                    <td><%= order.getOrderNo() %></td>
                    <td>
                        <form action="<%= request.getContextPath() %>/UpdateOrderServlet" method="post">
                            <input type="hidden" name="no" value="<%= order.getOrderNo() %>"/>
                            <select name="state">
                                <option value="pending" <%= "pending".equals(order.getOrderStatus()) ? "selected" : "" %>>대기</option>
                                <option value="shipped" <%= "shipped".equals(order.getOrderStatus()) ? "selected" : "" %>>배송</option>
                                <option value="completed" <%= "completed".equals(order.getOrderStatus()) ? "selected" : "" %>>완료</option>
                            </select>
                            <input type="submit" value="상태 변경"/>
                        </form>
                    </td>
                </tr>
            <% } %>
        </table>
    </section>
</body>
</html>
