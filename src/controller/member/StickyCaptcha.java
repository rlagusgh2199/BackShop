package member;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.producer.NumbersAnswerProducer;


@WebServlet("/login_page/stickyImg")
public class StickyCaptcha extends HttpServlet {

    public StickyCaptcha() {
        super();    
    }
    //서버의 응답
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Captcha 객체 생성
        Captcha captcha = new Captcha.Builder(200, 50)
                .addText(new DefaultTextProducer()) // 숫자 추가
                .addBackground(new GradiatedBackgroundProducer())
                .addNoise()
                //.gimp() // 이 부분에서 예외 발생 가능
                .addBorder()
                .build(); // 필수! 항상 호출해야 함

        // Captcha 텍스트를 세션에 저장
        HttpSession session = request.getSession();
        String captchaText = captcha.getAnswer();  // 캡차 텍스트
        session.setAttribute("captchaText", captchaText); //텍스트를 세션에 저장
        
        // Captcha 이미지를 클라이언트에 반환
        response.setContentType("image/png");
        BufferedImage captchaImage = captcha.getImage(); // BufferedImage 가져오기
        ImageIO.write(captchaImage, "png", response.getOutputStream()); // 이미지 출력

        // 콘솔에 Captcha 텍스트 출력(데이터 확인용) 
        System.out.println("Get Text: " + captchaText+ session.getId());
        
        
        
    }
    
    //사용자요청
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 인코딩 설정
        request.setCharacterEncoding("UTF-8"); // POST 방식 요청의 인코딩을 UTF-8로 설정

        // 응답 인코딩 설정
        response.setContentType("text/html; charset=UTF-8"); // 응답 콘텐츠 유형과 인코딩 설정
       
       //사용자 CAPTCHA 답안 가져오기(사용자 정보 업데이트)
       HttpSession session = request.getSession();
       
       //Captcha 텍스트가져오기(=이미지텍스트)
        String correctAnswer= (String) session.getAttribute("captchaText");
        
        //데이터확인용
        System.out.println("Post Text: " + correctAnswer+ session.getId());
        
        // 사용자가 입력한 답안 가져오기
        String userAnswer = request.getParameter("captchaAnswer");
        System.out.println("userAnswer: " +  userAnswer);
        // 메시지 변수
        String message;
        
        // 답안 비교
        if (correctAnswer != null && correctAnswer.equals(userAnswer)) {
            message = "캡챠 성공!";
            
            // 사용자 입력 값(name속성) 가져오기
            String id2 = request.getParameter("id");
            String pwd1 = request.getParameter("pwd1");
            String pwd2 = request.getParameter("pwd2");
            String alias = request.getParameter("alias");
            String address = request.getParameter("address");
            String email = request.getParameter("email");
            String gender = request.getParameter("gender");
            String favorite = request.getParameter("favorite");

            MemberDTO mDto = new MemberDTO();
            mDto.setId2(id2);
            mDto.setPwd1(pwd1);
            mDto.setPwd2(pwd2);
            mDto.setAlias(alias);
            mDto.setAddress(address);
            mDto.setEmail(email);
            mDto.setGender(gender);
            mDto.setFavorite(favorite);
            
            MemberDAO mDao = new MemberDAO();   
            
            boolean idCheck = mDao.idCheck(mDto); //아이디 중복체크
            boolean aliasCheck = mDao.aliasCheck(mDto); //별명 중복체크
            boolean insertCheck = mDao.memberInsert(mDto); //회원추가
            
            if(insertCheck){
                request.setAttribute("joinResult", insertCheck);
               request.setAttribute("idKey", id2);
               
            }else{
                request.setAttribute("joinResult", 0);
          
            }
            
            
            
            // JDBC 연결 및 데이터베이스 연동
            
                            
                //             
                //int idCount = 0;
                
                
                // 결과 출력
                String message1="";
                if (idCheck) {
                    System.out.println("이미 사용 중인 아이디입니다");
                    message1 = "이미 사용 중인 아이디입니다.";
                    request.setAttribute("message1", message1);
                    session.setAttribute("idDuplicate", true); // 저장
                } else if(id2.length() < 6) {
                   System.out.println("아이디는 최소 6자리 이상이어야 합니다.");
                   message1 = "아이디는 최소 6자리 이상이어야 합니다.";
                   request.setAttribute("message1", message1);
                } else {
                    System.out.println("사용 가능한 아이디입니다.");
                    message1 = "사용 가능한 아이디입니다.";
                    request.setAttribute("message1", message1);
                    session.setAttribute("idDuplicate", false); // 저장x
                }
                //
                
                // 4) 별명 중복 체크 추가
           
                String message2 = "";

                if (aliasCheck) {
                    System.out.println("이미 사용 중인 별명입니다.");
                    message2 = "이미 사용 중인 별명입니다.";
                    request.setAttribute("message2", message2);
                    session.setAttribute("aliasDuplicate", true);
                } else if(alias.length() < 2) {
                   System.out.println("별명은 최소 2자리 이상이어야 합니다.");
                   message2 = "별명은 최소 2자리 이상이어야 합니다.";
                   request.setAttribute("message2", message2);
                } else {
                    System.out.println("사용 가능한 별명입니다.");
                    message2 = "사용 가능한 별명입니다.";
                    request.setAttribute("message2", message2);
                    session.setAttribute("aliasDuplicate", false);
                }
                //
                

                // 리소스 정리
              
                
               
                //입력다했을경우
                boolean isValid = (!id2.isEmpty()) && 
                      (!pwd1.isEmpty()) && 
                      (!pwd2.isEmpty()) &&
                      (!alias.isEmpty()) && 
                      (gender != null && !gender.isEmpty()) && 
                      (favorite != null && !favorite.isEmpty());
                
             // 회원가입 완료 메시지, 아이디와 별명 중복이 없을 경우에만
                if (!idCheck && !aliasCheck  && isValid) { //회원가입이 완료되면 insert
                    message = "회원가입이 완료되었습니다!";
     
                } else if(!idCheck && !aliasCheck){
                   message = "입력을 해주세요.";
                }else { 
                    message = "아이디 또는 별명이 중복되었습니다. 다시 시도해 주세요.";
                }
                  
                if(id2.length() < 6){
                   message = "아이디는 최소 6자리 이상이어야 합니다.";
                } 
                if(pwd1.length() < 6){
                   message = "비밀번호는 최소 6자리 이상이어야 합니다.";
                } 
                if(alias.length() < 2){
                   message = "별명은 최소 2자리 이상이어야 합니다.";
                } 
                
                if(!pwd1.equals(pwd2)){
                   message = "비밀번호가 다릅니다.";
                }
                
            
        } else { //캡챠가 비어있거나 다를경우
            message = "캡챠 실패 , 다시 시도 해주세요.";
        }
        
        // 메시지를 request에 저장하여 JSP에서 사용
        request.setAttribute("message", message);
        
        // 메시지와 함께 결과 페이지로 이동 (혹은 다시 캡차를 표시하도록 리다이렉트)
        request.getRequestDispatcher("/login_page/member2.jsp").forward(request, response);
        
   }
 

}
