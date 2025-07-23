package maneger;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import order.OrderDAO;
import order.OrderDTO;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();
        
        // orders 테이블에서 데이터를 가져옵니다.
        List<OrderDTO> orders = orderDAO.getOrders();  // 주문 목록을 가져옵니다.
        
        // orders 데이터를 세션에 담아 JSP로 전달
        HttpSession session = request.getSession();
        session.setAttribute("orders", orders);
        
        // 관리자 페이지로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/maneger_page/maneger_order.jsp");
    }
}
