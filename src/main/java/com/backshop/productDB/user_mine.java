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

import member.MemberDAO;
import member.MemberDTO;


@WebServlet("/user_mine")
public class user_mine extends HttpServlet {
  
    public user_mine() {
        super();      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false); //사용자가 이미 로그인했는지 확인할 때 유용
		
		// 로그인 여부 확인
        String userId = (String) session.getAttribute("id2");
        if (userId == null) {
            // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/login_page/Login.jsp");
            return;
        }
		
		// 로그인했으면, 이후코드 실행

		
		//세션이 없으면 null
		ProductDAO productDAO = new ProductDAO();
		List<ProductDTO> shirtList = null;
		List<ProductDTO> MaleShirtList = null;
		List<ProductDTO> FemaleShirtList = null;
		
        List<ProductDTO> pantsList = null;
        List<ProductDTO> MalePantsList = null;
        List<ProductDTO> FemalePantsList = null;
        
        try {
        	shirtList = productDAO.getShirts(); // "상의" 카테고리의 상품 목록 가져오기
        	MaleShirtList = productDAO.getMaleShirts();
        	FemaleShirtList = productDAO.getFemaleShirts();
        	pantsList = productDAO.getPants(); // "하의" 카테고리의 상품 목록 가져오기
        	MalePantsList = productDAO.getMalePants();
        	FemalePantsList = productDAO.getFemalePants();
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 세션에 상의 목록 저장
        session.setAttribute("shirtList", shirtList);
        session.setAttribute("MaleShirtList", MaleShirtList);
        session.setAttribute("FemaleShirtList", FemaleShirtList);
        // 세션에 하의 목록 저장
        session.setAttribute("pantsList", pantsList);
        session.setAttribute("MalePantsList", MalePantsList);
        session.setAttribute("FemalePantsList", FemalePantsList);
        // JSP로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/user_page/user_mine.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
