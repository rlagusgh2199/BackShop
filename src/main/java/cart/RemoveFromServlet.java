package cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/RemoveFromServlet")
public class RemoveFromServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartItemNo = Integer.parseInt(request.getParameter("cart_item_no"));
        
        // CartDAO 인스턴스를 사용하여 항목 삭제
        CartDAO cartDAO = new CartDAO();
        boolean isRemoved = cartDAO.removeItemFromCart(cartItemNo);
        
        if (isRemoved) {
            // 삭제 성공 시 장바구니 페이지로 리디렉션
            response.sendRedirect(request.getContextPath() + "/user_page/user_cart.jsp");
        } else {
            // 삭제 실패 시 에러 페이지로 리디렉션
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

}
