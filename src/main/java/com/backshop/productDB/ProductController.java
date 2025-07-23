package com.backshop.productDB;

import com.backshop.productDB.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.Part;

@WebServlet("/registerProduct")
@MultipartConfig
public class ProductController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String productCategory = request.getParameter("product_category");
        String productGender = request.getParameter("product_gender");
        String productName = request.getParameter("product_name");
        double productPrice = Double.parseDouble(request.getParameter("product_price"));
        int productStock = Integer.parseInt(request.getParameter("product_stock"));
        double productTemperature = Double.parseDouble(request.getParameter("product_temperature"));
        Part productImagePart = request.getPart("product_image"); // Part 객체를 통해 파일을 가져옵니다.
        String productImage = productImagePart.getSubmittedFileName(); // 파일 이름 추출
        String productDescription = request.getParameter("product_description");
        
        // 이미지 파일을 저장할 경로 설정
        String uploadPath = getServletContext().getRealPath("/img_uploads"); // 배포경로
        //String uploadPath = "C:\\Users\\a\\eclipse-workspace\\BackShop\\img_uploads"; // 절대 경로 지정
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
        	 if (uploadDir.mkdir()) {
        	        System.out.println("Directory created: " + uploadPath);
        	    } else {
        	        System.out.println("Failed to create directory: " + uploadPath);
        	    }
        }
        
        System.out.println("Upload Path: " + uploadPath);

        
        // 파일 이름 중복 방지를 위한 고유 파일 이름 생성
        String uniqueFileName = System.currentTimeMillis() + "_" + productImage;
        productImagePart.write(uploadPath + "/" + uniqueFileName); // 파일 저장
        
     // ProductDTO 객체 생성
        ProductDTO product = new ProductDTO();
        product.setProductCategory(productCategory);
        product.setProductGender(productGender);
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setProductStock(productStock);
        product.setProductTemperature(productTemperature);
        product.setProductImage(uniqueFileName);
        product.setProductDescription(productDescription);
        
        ProductDAO productDAO = new ProductDAO();
        try {
            productDAO.addProduct(product);
            response.sendRedirect(request.getContextPath() + "/productList");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
