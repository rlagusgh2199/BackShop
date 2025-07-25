package com.backshop.productDB;

import com.backshop.productDB.ProductDAO;
import com.backshop.productDB.ProductDTO;
import com.backshop.productDB.CategoryCountDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/productList_desc")
public class ProductListDescServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductDTO> products = null;
        List<CategoryCountDTO> categoryCounts = null; // 카테고리 수를 저장할 리스트

        try {
            ProductDAO productDAO = new ProductDAO(); // DAO 객체 생성
            products = productDAO.getAllProducts_desc(); // 상품 목록을 가져옵니다.
            categoryCounts = productDAO.getCategoryCounts(); // 카테고리별 상품 수를 가져옵니다.

            if (products == null || products.isEmpty()) {
                System.out.println("상품이 없습니다."); // 디버깅 메시지
            } else {
                System.out.println("상품 수: " + products.size()); // 상품 개수 출력
            }

            // 세션에 상품 목록과 카테고리 수 저장
            HttpSession session = request.getSession();
            session.setAttribute("productList", products);

            // JSP로 포워딩하여 상품 목록과 카테고리 수를 전달
            response.sendRedirect(request.getContextPath() + "/user_page/index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
