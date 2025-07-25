package maneger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManegerUserDelete
 */
@WebServlet("/ManegerUserDelete")
public class ManegerUserDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id2 = request.getParameter("id2");

        ManegerDAO memberDAO = new ManegerDAO();
        boolean result = memberDAO.deleteUser(id2);

        if (result) {
            response.sendRedirect(request.getContextPath() + "/maneger_page/maneger_main.jsp");
        } else {
            request.setAttribute("errorMessage", "회원 탈퇴에 실패했습니다.");
            request.getRequestDispatcher("/admin_page/error.jsp").forward(request, response);
        }
    }
}

