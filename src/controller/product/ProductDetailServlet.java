package com.backshop.productDB;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductDetailServlet")
public class ProductDetailServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id")); // URL에서 상품 ID 가져오기
        ProductDAO productDAO = new ProductDAO();
        ProductDTO product = null;

        try {
            product = productDAO.getProductById(id); // 상품 정보 가져오기
            if (product != null) {
                request.setAttribute("product", product); // 상품 정보를 JSP로 전달
                RequestDispatcher dispatcher = request.getRequestDispatcher("/maneger_page/maneger_productdetail.jsp");
                dispatcher.forward(request, response); // JSP로 포워딩
            } else {
                response.sendRedirect("error.jsp"); // 상품을 찾을 수 없는 경우
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // 예외 발생 시 오류 페이지로 리다이렉트
        }
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
