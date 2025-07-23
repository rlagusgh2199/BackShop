package cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.backshop.productDB.ProductDAO;
import com.backshop.productDB.ProductDTO;


@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 세션에서 사용자 ID 가져오기
        HttpSession session = request.getSession(false);
        String userId = (String) session.getAttribute("id2");

        if (userId == null) {
            response.sendRedirect(request.getContextPath() + "/login_page/Login.jsp");
            return;
        }

        // 요청 파라미터 가져오기
        String productIdStr = request.getParameter("product_id");
        String quantityStr = request.getParameter("quantity");

        if (productIdStr == null || quantityStr == null) {
            response.sendRedirect("Error.jsp");
            return;
        }

        int productId;
        int quantity;

        try {
            productId = Integer.parseInt(productIdStr);
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("Error.jsp");
            return;
        }

        ProductDAO productDAO = new ProductDAO();
        ProductDTO product = productDAO.getProductById_na(productId);

        if (product == null) {
            response.sendRedirect("Error.jsp");
            return;
        }

        // 재고 확인
        if (quantity > product.getProductStock()) {
            request.setAttribute("errorMessage", "재고 부족으로 주문할 수 없습니다.");
            request.getRequestDispatcher("user_productinfo.jsp?product_id=" + productId).forward(request, response);
            return;
        }

        // 장바구니 추가
        CartDAO cartDAO = new CartDAO();
        CartDTO cartItem = new CartDTO();
        cartItem.setProductNo(String.valueOf(productId));
        cartItem.setProductName(product.getProductName());
        cartItem.setProductPrice(product.getProductPrice());
        cartItem.setQuantity(quantity);
        cartItem.setProductImage(product.getProductImage());
        cartItem.setUserId(userId);

        boolean isAdded = cartDAO.addToCart(cartItem);

        if (isAdded) {
            // 성공적으로 추가된 경우 장바구니 페이지로 이동
            response.sendRedirect("user_page/user_cart.jsp");
        } else {
            // 추가 실패 시 에러 페이지로 이동
            response.sendRedirect("Error.jsp");
        }
    }

}
