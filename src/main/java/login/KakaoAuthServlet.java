package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.MemberDAO;
import member.MemberDTO;

@WebServlet("/login_page/KakaoAuth")
public class KakaoAuthServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 카카오 REST API 키
        String clientId = "38422e8c89290de7a44dbaa240e30483"; // 실제 키로 교체
        // 리다이렉트 URI
        String redirectUri = request.getScheme() + "://" + request.getServerName() + ":" 
                + request.getServerPort() + request.getContextPath() + "/login_page/KakaoAuth"; // 실제 URI로 교체

        String code = request.getParameter("code");
        
        if (code == null) {
            // 인증 URL 생성
            String authUrl  = "https://kauth.kakao.com/oauth/authorize?" +
                    "response_type=code" +
                    "&client_id=" + clientId + 
                    "&redirect_uri=" + redirectUri + 
                    "&prompt=consent";
            response.sendRedirect(authUrl); // 인증 URL로 리다이렉트
        } else {
            // 액세스 토큰 요청
            KakaoLogin kakaoLogin = new KakaoLogin(clientId, redirectUri);
            String accessToken;

            try {
                // 액세스 토큰 가져오기
                accessToken = kakaoLogin.getAccessToken(code);
                UserInfo userInfo = new UserInfo();

                // 사용자 정보 가져오기
                String userData = userInfo.getUserInfo(accessToken); // 전체 사용자 데이터
                String nickname = userInfo.getNickname(accessToken); // 닉네임만 가져오기
                String email = userInfo.getEmail(accessToken); // 이메일 가져오기
                System.out.println("Retrieved email: " + email); // 로그 추가
                String gender = userInfo.getGender(accessToken); // 성별 가져오기 (필요한 경우)
                String favorite = userInfo.getFavorite(accessToken); // 관심분야 가져오기 (필요한 경우)

                // MemberDTO 객체 생성
                MemberDTO member = new MemberDTO();
                member.setId2(email); // ID를 이메일로 사용
                member.setAlias(nickname); // 닉네임을 alias로 사용
                member.setEmail(email); // 이메일 설정
                member.setGender(gender); // 성별 설정 (필요한 경우)
                member.setFavorite(favorite); // 관심분야 설정 (필요한 경우)
                
                // DB에 사용자 정보 저장
                MemberDAO memberDAO = new MemberDAO();
                if (!memberDAO.isUserExists(email)) { // 이메일로 사용자 존재 여부 확인
                   member.setPwd1("default_password"); // 기본 비밀번호 설정
                    memberDAO.memberInsert3(member); // 사용자 추가 메서드 호출
                } else {
                    // 이미 존재하는 경우, 사용자 정보 업데이트
                    memberDAO.updateUserInfo(member); // 사용자 정보 업데이트 메서드 호출
                }

                // 세션에 사용자 정보 저장
                request.getSession().setAttribute("userData", userData); //전체데이터
                request.getSession().setAttribute("nickname", nickname); //닉네임가져오기
                request.getSession().setAttribute("id2", email); // 이메일을 세션에 저장
                request.getSession().setAttribute("alias", nickname); // 이메일을 세션에 저장

                // 리다이렉트
                response.sendRedirect(request.getContextPath() + "/productList_desc");
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "사용자 정보를 가져오는 데 실패했습니다." + e.getMessage());
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
            }
        }
    }
}
