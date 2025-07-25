package maneger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManegerUserUpdate
 */
@WebServlet("/ManegerUserUpdate")
public class ManegerUserUpdate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 인코딩 설정
        request.setCharacterEncoding("UTF-8");
        
        // 폼에서 전달된 파라미터 가져오기
        String id2 = request.getParameter("id2");
        String alias = request.getParameter("alias");
        String address = request.getParameter("address"); // 새로운 필드 추가
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String favorite = request.getParameter("favorite"); // 새로운 필드 추가

        // ManegerDTO 객체 생성 및 값 설정
        ManegerDTO member = new ManegerDTO();
        member.setId2(id2);
        member.setAlias(alias);
        member.setAddress(address); // 새로운 필드 설정
        member.setEmail(email);
        member.setGender(gender);
        member.setFavorite(favorite); // 새로운 필드 설정

        // DAO 객체 생성 및 사용자 정보 업데이트
        ManegerDAO memberDAO = new ManegerDAO();
        boolean result = memberDAO.updateUserInfo(member); // 사용자 정보 업데이트 메서드 호출

        // 결과에 따라 리다이렉트 또는 에러 페이지로 포워드
        if (result) {
            response.sendRedirect(request.getContextPath() + "/maneger_page/maneger_main.jsp"); // 성공 페이지로 리다이렉트
        } else {
            request.setAttribute("errorMessage", "회원 정보 수정에 실패했습니다.");
            request.getRequestDispatcher("/admin_page/error.jsp").forward(request, response); // 에러 페이지로 포워드
        }
    }
}
