package login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;


@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그인
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		//로그인 유지 체크 여부 확인(true,false)->로그인 유지 체크 박스
		String keepLogin = request.getParameter("keepLogin");
		
		
		
		MemberDAO mDao = new MemberDAO();
		boolean loginCheck = mDao.loginCheck(id, pw);
		String alias = mDao.getAlias(id, pw);
		
		 if(alias != null){//연동해서 로그인성공
			 	//db연동
				HttpSession session = request.getSession();
				session.setAttribute("idKey", id);
				session.setAttribute("id2", id);
				session.setAttribute("alias", alias); // 별칭 저장
		    	
				  if ("root".equals(id) && "1234".equals(pw)) { // 관리자 로그인
			            response.sendRedirect(request.getContextPath() + "/maneger_page/maneger_main.jsp");
			        } else { // 일반 사용자 로그인
			            if (keepLogin == null) { // 로그인 유지 안 한 경우
			                session.setAttribute("loginCheck", "ok");
			            } else { // 로그인 유지 한 경우
			                ServletContext application = getServletContext();
			                application.setAttribute("loginCheck2", "ok");
			                application.setAttribute("user_", alias);
			            }
			           // RequestDispatcher dispatcher = request.getRequestDispatcher("/user_page/user_main.jsp");
			           // dispatcher.forward(request, response);
			            response.sendRedirect(request.getContextPath() + "/productList_desc");
			        }
		        } else {
		            // 로그인 실패 처리
		            response.sendRedirect(request.getContextPath() + "/login_page/Login.jsp");
		     	
		       }
		    	
		    	
		 
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//로그아웃
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
        

        response.sendRedirect(request.getContextPath() +"/user_page/index.jsp");
		
		
	}

}
