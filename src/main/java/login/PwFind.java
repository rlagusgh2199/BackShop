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


@WebServlet("/PwFind.do")
public class PwFind extends HttpServlet {
	
    public PwFind() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = (String)request.getParameter("id");
		String alias = (String)request.getParameter("alias");		
		String pwd1 =  new MemberDAO().pwFind(id,alias);		
		MemberDTO mDto = new MemberDTO();
		System.out.println("1"+id );
		System.out.println("2"+alias );
		System.out.println("3"+pwd1 );

		HttpSession session = request.getSession();
		session.setAttribute("id2", id);
		session.setAttribute("alias", alias);
		
		if(pwd1!= null){// 비밀번호 찾기 성공
			 mDto.setPwd1(pwd1); // mDto 객체에 pwd1 값을 설정
			 System.out.println("3"+mDto.getPwd1());
			 session.setAttribute("pwd1", mDto.getPwd1());
			 
  		}else{// 실패
  			session.setAttribute("pwd1",null); //false
  			
  		}

		
		response.sendRedirect(request.getContextPath() + "/login_page/pwFind.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
