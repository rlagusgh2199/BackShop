package order;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/OrderService")
public class OrderService extends HttpServlet {
	
	private OrderDAO orderDAO;

    public OrderService() {
        this.orderDAO = new OrderDAO();  // DAO 인스턴스 생성
    }

    // 사용자 ID에 해당하는 주문 목록을 가져오는 메서드
    public List<OrderDTO> getOrdersByUser(String userId) {
        return orderDAO.getOrdersByUserId(userId);  // DAO를 통해 주문 목록을 조회
    }
    
    //OrderServlet
	/*
	 * private OrderDAO orderDAO = new OrderDAO();
	 * 
	 * @Override protected void doPost(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException { String
	 * userId = request.getParameter("user_id"); String productName =
	 * request.getParameter("product_name"); double productPrice =
	 * Double.parseDouble(request.getParameter("product_price")); int quantity =
	 * Integer.parseInt(request.getParameter("quantity"));
	 * 
	 * // 주문 DTO 생성 OrderDTO order = new OrderDTO();
	 * 
	 * // 주문을 DB에 삽입 orderDAO.addOrder(order);
	 * 
	 * // 주문 목록을 가져와서 request에 담기 List<OrderDTO> orders =
	 * orderDAO.getOrdersByUserId(userId); // 사용자의 주문 목록을 가져오는 메서드
	 * 
	 * // 주문 목록을 request 객체에 세팅 request.setAttribute("orders", orders);
	 * 
	 * // 주문 완료 후 user_order.jsp로 포워딩 RequestDispatcher dispatcher =
	 * request.getRequestDispatcher("user_order.jsp"); dispatcher.forward(request,
	 * response); }
	 */
}
