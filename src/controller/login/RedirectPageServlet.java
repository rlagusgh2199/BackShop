package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RedirectPageServlet
 */
@WebServlet("/RedirectPageServlet")
public class RedirectPageServlet extends HttpServlet {
	
	
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			 String userData = (String) request.getAttribute("userData");
			 String nickname = (String) request.getAttribute("nickname");
		 
		     // JSP로 데이터 전달
		     request.setAttribute("userData", userData);
		     request.setAttribute("nickname", nickname);
		     System.out.println(nickname);
	         //request.getRequestDispatcher("/user_page/user_main.jsp").forward(request, response);
	         response.sendRedirect(request.getContextPath() + "/user_page/index.jsp");
	    }

}
