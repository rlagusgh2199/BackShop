package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/IdFind.do")
public class IdFind extends HttpServlet {
	
    public IdFind() {
        super();
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String alias = (String)request.getParameter("alias");		
		String id2 = new MemberDAO().idFind(alias); // id2 값을 반환받음		
		MemberDTO mDto = new MemberDTO();
		
		HttpSession session = request.getSession();
		session.setAttribute("alias", alias);
		if(id2!= null){// 아이디 찾기 성공
			 mDto.setId2(id2); // mDto 객체에 id2 값을 설정
			 session.setAttribute("id2", mDto.getId2());
			 
  		}else{// 실패
  			session.setAttribute("id2",null); //false
  			
  		}
		
		response.sendRedirect(request.getContextPath() + "/login_page/idFind.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
