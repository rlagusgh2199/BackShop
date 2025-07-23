package com.backshop.productDB;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProductUserCategoryShirt
 */
@WebServlet("/ProductUserCategoryShirt")
public class ProductUserCategoryShirt extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProductUserCategoryShirt() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        List<ProductDTO> shirtList = null;

        try {
            shirtList = productDAO.getShirts(); // "상의" 카테고리의 상품 목록 가져오기
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 세션에 상품 목록 저장
        HttpSession session = request.getSession();
        session.setAttribute("shirtList", shirtList);

        // JSP로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/user_page/user_shirt.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
