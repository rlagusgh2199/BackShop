package com.backshop.productDB;

import com.backshop.weather.ApiItem;
import com.backshop.weather.ApiResult;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/today")
public class TodayServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); // 세션 객체 가져오기

        // 데이터베이스 연결 및 상품 목록 가져오기
        try { 
            ProductDAO productDAO = new ProductDAO();
            List<ProductDTO> products = productDAO.getAllProducts();

            // 상품 목록 확인
            if (products == null || products.isEmpty()) {
                System.out.println("상품이 없습니다."); // 디버깅 메시지
            } else {
                System.out.println("상품 수: " + products.size()); // 상품 개수 출력
            }

            // 상품 목록을 세션에 설정
            session.setAttribute("productList", products);

            // 날씨 정보 가져오기
            final String baseDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
            final int nx = 60; // 예시 좌표
            final int ny = 127; // 예시 좌표

            ApiResult api = new ApiResult();
            String responseBody = api.httpGet(baseDate, nx, ny); // API 호출

            if (responseBody != null) {
                List<ApiItem> weatherItems = api.parseJson(responseBody); // JSON 파싱
                if (weatherItems != null && !weatherItems.isEmpty()) {
                    session.setAttribute("weatherItems", weatherItems); // 세션에 데이터 저장

                    // 온도 값 가져오기
                    String temperature = null; // 온도 값을 저장할 변수
                    for (ApiItem item : weatherItems) {
                        if ("T1H".equals(item.getCategory())) {
                            temperature = item.getObsrValue(); // 온도 값을 저장
                            break; // 온도를 찾으면 반복 종료
                        }
                    }
                    session.setAttribute("temperature", temperature); // 세션에 온도 저장
                } else {
                    System.out.println("JSON 파싱 실패: items가 null 또는 비어있습니다.");
                    session.setAttribute("weatherItems", null); // 세션에 null 저장
                }
            } else {
                System.out.println("API 호출 실패: responseBody가 null입니다.");
                session.setAttribute("weatherItems", null); // 세션에 null 저장
            }

            // JSP 페이지로 리다이렉트 (URL 변경)
            response.sendRedirect(request.getContextPath() + "/user_page/user_today.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // 오류 페이지로 리다이렉트
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // GET 요청과 동일하게 처리
    }
}
