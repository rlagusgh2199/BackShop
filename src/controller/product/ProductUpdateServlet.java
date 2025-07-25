package com.backshop.productDB;

import com.backshop.productDB.ProductDTO;
import com.backshop.productDB.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/maneger_productupdate")
@MultipartConfig
public class ProductUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String productName = request.getParameter("product_name");
        double productPrice = Double.parseDouble(request.getParameter("product_price"));
        int productStock = Integer.parseInt(request.getParameter("product_stock"));
        String productDescription = request.getParameter("product_description");
        double productTemperature = Double.parseDouble(request.getParameter("product_temperature"));
        Part productImagePart = request.getPart("product_image");
        String productCategory = request.getParameter("product_category");

        ProductDAO productDAO = new ProductDAO();
        ProductDTO product = new ProductDTO();
        product.setId(id);
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setProductStock(productStock);
        product.setProductDescription(productDescription);
        product.setProductTemperature(productTemperature);
        product.setProductCategory(productCategory);

        // 이미지 파일이 업로드되었는지 확인
        if (productImagePart != null && productImagePart.getSize() > 0) {
            String productImage = productImagePart.getSubmittedFileName();
            String uniqueFileName = System.currentTimeMillis() + "_" + productImage;

            // 파일 저장 경로
            String uploadPath = getServletContext().getRealPath("/img_uploads");
            productImagePart.write(uploadPath + "/" + uniqueFileName);
            product.setProductImage(uniqueFileName);
        } else {
            // 이미지가 수정되지 않는 경우 기존 이미지를 유지
        	ProductDTO existingProduct = null; // 초기화
        	try {
        	    existingProduct = productDAO.getProductById(id);
        	} catch (SQLException e) {
        	    e.printStackTrace();
        	    response.sendRedirect("error.jsp"); // 오류 페이지로 리다이렉트
        	    return; // 메소드 종료
        	}

        	// existingProduct가 null인지 확인
        	if (existingProduct != null) {
        	    product.setProductImage(existingProduct.getProductImage());
        	} else {
        	    // 기존 상품을 찾지 못한 경우 처리
        	    System.out.println("상품을 찾을 수 없습니다.");
        	    response.sendRedirect("error.jsp"); // 오류 페이지로 리다이렉트
        	    return; // 메소드 종료
        	}
            product.setProductImage(existingProduct.getProductImage());
        }

        try {
            productDAO.updateProduct(product);
            response.sendRedirect(request.getContextPath() + "/productList");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
