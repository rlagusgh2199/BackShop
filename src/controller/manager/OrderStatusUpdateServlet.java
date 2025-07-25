package maneger;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.OrderDAO;
import order.OrderDTO;


@WebServlet("/OrderStatusUpdateServlet")
public class OrderStatusUpdateServlet extends HttpServlet {
	// 주문 상태 수정 처리
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String orderId = request.getParameter("orderId");  // 주문 번호
        String orderStatus = request.getParameter("orderStatus");  // 선택한 주문 상태

        OrderDAO orderDAO = new OrderDAO();
        
        // 상태 업데이트
        boolean result = orderDAO.updateOrderStatus(orderId, orderStatus);

        if (result) {
            // 상태가 성공적으로 수정되면 관리자 페이지로 리다이렉트
            response.sendRedirect("OrderProcessingServlet");
        } else {
            response.sendRedirect("manager_page/manager_order.jsp?message=주문 상태 수정 실패");
        }
    }

}
