package login;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginProcess
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
       
	// 로그인 처리
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 한글 인코딩 처리
        request.setCharacterEncoding("UTF-8");
        
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String keepLogin = request.getParameter("keepLogin");
        
        // 로그인 체크
        if ("dong".equals(id) && "123".equals(pw)) {
            if (keepLogin == null) {  // 로그인 유지 안 한 경우
                HttpSession session = request.getSession();
                session.setAttribute("loginCheck", "ok");
                session.setAttribute("loginName", id);  // 사용자 id(session)
                response.sendRedirect(request.getContextPath() + "/login_page/Login.jsp");
            } else {  // 로그인 유지 한 경우
                ServletContext application = getServletContext();
                application.setAttribute("loginCheck2", "ok");
                application.setAttribute("user_", id);  // 사용자 id(application)
                
                response.sendRedirect(request.getContextPath() + "/login_page/Login.jsp");
            }
        } else if ("root".equals(id) && "1234".equals(pw)) {
            if (keepLogin == null) {  // 로그인 유지 안 한 경우
                HttpSession session = request.getSession();
                session.setAttribute("loginCheck", "ok");
                session.setAttribute("loginName", id);  // 사용자 id(session)
                response.sendRedirect(request.getContextPath() + "/maneger_page/maneger_main.jsp");
            } else {  // 로그인 유지 한 경우
                ServletContext application = getServletContext();
                application.setAttribute("loginCheck2", "ok");
                application.setAttribute("user_", id);  // 사용자 id(application)
                response.sendRedirect(request.getContextPath() + "/maneger_page/maneger_main.jsp");
            }
        } else {
            // 로그인 실패 처리
            response.sendRedirect(request.getContextPath() + "/login_page/Login.jsp");
        }
        
    }
    
    // 로그아웃 처리
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
//          session.removeAttribute("loginCheck");
//          session.removeAttribute("loginName");
            session.invalidate(); //session만 가능
        }

        // 로그인 유지가 활성화된 경우 (어플리케이션에서 상태 제거)
        ServletContext application = getServletContext();
        application.removeAttribute("loginCheck2");
        application.removeAttribute("user_");
        application.removeAttribute("keepLoginCheck");
        

        response.sendRedirect(request.getContextPath() +"/login_page/Login.jsp");
    }

}
