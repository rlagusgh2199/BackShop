package order;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.backshop.productDB.ProductDAO;
import com.backshop.productDB.ProductDTO;

import cart.CartDAO;
import cart.CartDTO;


@WebServlet("/OrderProcessingServlet")
public class OrderProcessingServlet extends HttpServlet {
	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { String userId =
	 * request.getParameter("userId"); // 세션에서 로그인된 사용자 ID 받기
	 * 
	 * CartDAO cartDAO = new CartDAO(); List<CartDTO> cartItems =
	 * cartDAO.getCartItems(userId); // 사용자 장바구니 항목들 가져오기
	 * 
	 * if (cartItems != null && !cartItems.isEmpty()) { for (CartDTO item :
	 * cartItems) { // orders 테이블에 주문 내역 추가 OrderDAO orderDAO = new OrderDAO();
	 * OrderDTO order = new OrderDTO(); order.setUserId(userId);
	 * order.setProductNo(Integer.parseInt(item.getProductNo())); // 상품 번호
	 * order.setProductName(item.getProductName()); // 상품명
	 * order.setProductPrice(item.getProductPrice()); // 상품 가격
	 * order.setQuantity(item.getQuantity()); // 수량 order.setOrderStatus("주문접수"); //
	 * 주문 상태 (기본값: 주문접수)
	 * 
	 * orderDAO.addOrder(order); // orders 테이블에 주문 추가
	 * 
	 * // 장바구니에서 해당 항목 삭제 cartDAO.removeItemFromCart(item.getNo()); } }
	 * 
	 * // 주문 처리 후 주문조회 페이지로 리다이렉트
	 * response.sendRedirect(request.getContextPath()+"/user_page/user_order.jsp");
	 * }
	 */
	
	
	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // 세션에서 사용자 ID 가져오기
	 * HttpSession session = request.getSession(false); String userId = (String)
	 * session.getAttribute("id2"); // 세션에서 사용자 ID 가져오기
	 * 
	 * if (userId != null) { // 사용자 ID를 이용해 주문 목록 조회 OrderDAO orderDAO = new
	 * OrderDAO(); List<OrderDTO> orders = orderDAO.getOrdersByUserId(userId);
	 * 
	 * // 주문 정보가 있다면 if (orders != null && !orders.isEmpty()) { // orders 리스트를
	 * request 객체에 설정 request.setAttribute("orders", orders);
	 * 
	 * // 주문 조회 페이지로 포워딩 RequestDispatcher dispatcher =
	 * request.getRequestDispatcher("user_page/user_order.jsp");
	 * dispatcher.forward(request, response); } else { // 주문이 없는 경우 처리
	 * request.setAttribute("message", "주문 내역이 없습니다."); RequestDispatcher dispatcher
	 * = request.getRequestDispatcher("user_page/user_order.jsp");
	 * dispatcher.forward(request, response); } } else {
	 * response.sendRedirect(request.getContextPath()+"login_page/Login.jsp"); //
	 * 세션이 없으면 로그인 페이지로 리다이렉트 } }
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 세션에서 사용자 ID 가져오기
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("id2");  // 세션에서 사용자 ID 가져오기

        if (userId != null) {
            // 사용자 ID를 이용해 장바구니 항목들 가져오기
            CartDAO cartDAO = new CartDAO();
            List<CartDTO> cartItems = cartDAO.getCartItems(userId);  // 사용자 장바구니 항목들 가져오기

            // 장바구니에 아이템이 있는 경우
            if (cartItems != null && !cartItems.isEmpty()) {
                OrderDAO orderDAO = new OrderDAO();

                // 장바구니의 모든 항목을 주문으로 추가
                for (CartDTO item : cartItems) {
                    OrderDTO order = new OrderDTO();
                    order.setUserId(userId);
                    order.setProductNo(Integer.parseInt(item.getProductNo()));
                    order.setProductName(item.getProductName());
                    order.setProductPrice(item.getProductPrice());
                    order.setQuantity(item.getQuantity());
                    order.setOrderStatus("주문접수"); // 기본 주문 상태
                    order.setProduct_image(item.getProductImage());

                    orderDAO.addOrder(order); // 주문 추가
                    cartDAO.removeItemFromCart(item.getNo()); // 장바구니에서 항목 제거
                }

                // 주문 후 사용자 주문 내역을 다시 가져와서 request에 설정
                List<OrderDTO> orders = orderDAO.getOrdersByUserId(userId);

                // 주문 정보가 있을 경우 request에 설정
                if (orders != null && !orders.isEmpty()) {
                    // 정상적으로 주문 내역이 있을 경우
                    request.setAttribute("orders", orders);
                } else {
                    // 주문 내역이 없을 경우
                    request.setAttribute("message", "주문 내역이 없습니다.");
                }
            }

            // 주문 처리 후 주문 조회 페이지로 포워딩
            //RequestDispatcher dispatcher = request.getRequestDispatcher("user_page/user_order.jsp");
            //dispatcher.forward(request, response);
            response.sendRedirect(request.getContextPath() + "/user_page/user_order.jsp"); 

        } else {
            response.sendRedirect(request.getContextPath() + "/login_page/Login.jsp");  // 세션이 없으면 로그인 페이지로 리다이렉트
        }
    }
}
