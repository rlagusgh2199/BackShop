package com.backshop.productDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/maneger_productdelete")
public class ProductDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id")); // 삭제할 상품 ID 가져오기
        ProductDAO productDAO = new ProductDAO();

        try {
            productDAO.deleteProduct(id); // 상품 삭제 메소드 호출
            response.sendRedirect(request.getContextPath() + "/productList"); // 상품 목록 페이지로 리다이렉트
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // 오류 발생 시 오류 페이지로 리다이렉트
        }
    }
}
