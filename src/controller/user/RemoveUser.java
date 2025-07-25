package user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cart.CartDAO;
import member.MemberDAO;


@WebServlet("/RemoveUser")
public class RemoveUser extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userno = request.getParameter("userId");
        
        // CartDAO 인스턴스를 사용하여 항목 삭제
        MemberDAO memberDAO = new MemberDAO();
        boolean isRemoved = memberDAO.removeUser(userno);
        
        if (isRemoved) {
            // 삭제 성공 시 장바구니 페이지로 리디렉션
            response.sendRedirect(request.getContextPath() + "/user_page/index.jsp");
        } else {
            // 삭제 실패 시 에러 페이지로 리디렉션
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
