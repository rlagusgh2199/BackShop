package maneger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.OrderDAO;


@WebServlet("/UpdateOrderServlet")
public class UpdateOrderServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderNo = Integer.parseInt(request.getParameter("no"));
        String newState = request.getParameter("state");

        OrderDAO orderDAO = new OrderDAO();
        boolean success = orderDAO.updateOrderState(orderNo, newState);

        if (success) {
            // 상태 수정 후 주문 목록을 다시 불러서 보여줍니다.
            response.sendRedirect("OrderServlet");  // OrderServlet이 호출되어 다시 목록을 표시합니다.
        } else {
            response.getWriter().println("상태 변경 실패");
        }
    }

}
