package cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backshop.productDB.ProductDAO;
import com.backshop.productDB.ProductDTO;

import javax.servlet.RequestDispatcher;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // URL에서 product_id 가져오기
        String productId = request.getParameter("product_id");
        if (productId == null || productId.isEmpty()) {
            response.sendRedirect("Error.jsp");
            return;
        }

        // ProductDAO로 데이터 가져오기
        ProductDAO productDAO = new ProductDAO();
        ProductDTO product = productDAO.getProductById_na(Integer.parseInt(productId));

        if (product == null) {
            response.sendRedirect("Error.jsp");
            return;
        }

        // 상품 데이터를 request에 추가
        request.setAttribute("product", product);

        // JSP로 전달
        RequestDispatcher dispatcher = request.getRequestDispatcher("user_page/user_productinfo.jsp");
        dispatcher.forward(request, response);
    }

}
