package com.backshop.weather;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/weather")
public class WeatherServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); // 세션 객체 가져오기
        final String baseDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        final int nx = 60; // 예시 좌표
        final int ny = 127; // 예시 좌표
        
        ApiResult api = new ApiResult();
        String responseBody = api.httpGet(baseDate, nx, ny);
        
        if (responseBody != null) {
            List<ApiItem> items = api.parseJson(responseBody);
            if (items != null && !items.isEmpty()) {
                session.setAttribute("weatherItems", items); // 세션에 데이터 저장
            } else {
                System.out.println("JSON 파싱 실패: items가 null 또는 비어있습니다.");
                session.setAttribute("weatherItems", null); // 세션에 null 저장
            }
        } else {
            System.out.println("API 호출 실패: responseBody가 null입니다.");
            session.setAttribute("weatherItems", null); // 세션에 null 저장
        }

        // JSP 페이지로 리다이렉션
        response.sendRedirect(request.getContextPath() + "/user_page/user_today.jsp");
    }
}





