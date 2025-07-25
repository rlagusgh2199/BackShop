//package captcha;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.sound.sampled.AudioFileFormat;
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//
//import nl.captcha.audio.Sample;
//import nl.captcha.audio.producer.RandomNumberVoiceProducer;
//
//
//
///**
// * Servlet implementation class AudioCaptchaServlet
// */
//@WebServlet("/AudioCaptcha")
//public class AudioCaptcha extends HttpServlet {
//
//   
//    public AudioCaptcha() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		 // 세션에서 캡차 텍스트 가져오기 (StickyCaptcha에서 설정한 텍스트)
//        HttpSession session = request.getSession();
//        String captchaText = (String) session.getAttribute("captchaText"); // StickyCaptcha에서 설정한 captchaText
//        
//        if (captchaText == null) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "캡차 텍스트를 찾을 수 없습니다.");
//            return;
//        }  
//		
//		
//        
//        // 예시: 숫자에 대해 두 가지 음성을 준비
//        String[] fileLocs0 = {"/sounds/en/numbers/0-alex.wav", "/sounds/noises/restaurant.wav"};
//        String[] fileLocs1 = {"/sounds/en/numbers/1-alex.wav", "/sounds/noises/restaurant.wav"};
//        String[] fileLocs2 = {"/sounds/en/numbers/2-alex.wav", "/sounds/noises/restaurant.wav"};
//        String[] fileLocs3 = {"/sounds/en/numbers/3-alex.wav", "/sounds/noises/restaurant.wav"};
//        String[] fileLocs4 = {"/sounds/en/numbers/4-alex.wav", "/sounds/noises/restaurant.wav"};
//        String[] fileLocs5 = {"/sounds/en/numbers/5-alex.wav", "/sounds/noises/restaurant.wav"};
//        String[] fileLocs6 = {"/sounds/en/numbers/6-alex.wav", "/sounds/noises/restaurant.wav"};
//        String[] fileLocs7 = {"/sounds/en/numbers/7-alex.wav", "/sounds/noises/restaurant.wav"};
//        String[] fileLocs8 = {"/sounds/en/numbers/8-alex.wav", "/sounds/noises/restaurant.wav"};
//        String[] fileLocs9 = {"/sounds/en/numbers/9-alex.wav", "/sounds/noises/restaurant.wav"};
//        
//
//        // 오디오 캡차에 사용할 음성 파일들을 Map으로 설정
//        Map<Integer, String[]> voicesMap = new HashMap<>();
//        
//        // 예시: 숫자 0과 1에 대해 두 가지 음성을 준비
//        voicesMap.put(0, fileLocs0);
//        voicesMap.put(1, fileLocs1);
//        voicesMap.put(2, fileLocs2);
//        voicesMap.put(3, fileLocs3);
//        voicesMap.put(4, fileLocs4);
//        voicesMap.put(5, fileLocs5);
//        voicesMap.put(6, fileLocs6);
//        voicesMap.put(7, fileLocs7);
//        voicesMap.put(8, fileLocs8);
//        voicesMap.put(9, fileLocs9);
//        
//
//     // 텍스트의 각 문자를 음성 파일로 변환 (문자열을 숫자로 변환)
//        Map<Integer, String[]> captchaVoicesMap = new HashMap<>();
//        
//     // captchaText에서 각 문자를 순회하면서 숫자에 맞는 fileLocs를 captchaVoicesMap에 추가
//        for (char ch : captchaText.toCharArray()) {
//            // 숫자인지 확인
//            if (Character.isDigit(ch)) {
//                int num = Character.getNumericValue(ch); // char를 숫자로 변환
//
//                
//                // 숫자에 해당하는 음성 파일 배열 준비
//                String[] fileLocs = {
//                    "/sounds/en/numbers/" + ch + "-alex.wav",
//                    "/sounds/noises/restaurant.wav"
//                };
//                // captchaVoicesMap에 숫자와 그에 해당하는 음성 파일 배열을 추가
//                captchaVoicesMap.put(num, fileLocs);
//                
//                // 기존 voicesMap에서 해당 숫자의 음성 파일 배열을 가져와서 captchaVoicesMap에 추가
//                
//                System.out.println("num"+captchaVoicesMap.get(num));
//            }
//        }
//        
//       
//        // VoiceProducer 객체 생성 
//        RandomNumberVoiceProducer vProd = new RandomNumberVoiceProducer(voicesMap);
//       
//        nl.captcha.audio.AudioCaptcha ac = new nl.captcha.audio.AudioCaptcha.Builder()
//        		.addAnswer()       // 캡차 텍스트 추가 (숫자나 문자)
//                .addVoice(vProd)   // 음성 추가
//                .addNoise()        // 노이즈 추가
//                .build();          // AudioCaptcha 빌드
//
//        
//     // 오디오 캡차를 클라이언트에 반환
//        response.setContentType("audio/wav");
//        Sample sample = ac.getChallenge();
//
//        
//        try (InputStream inputStream = sample.getAudioInputStream()) {
//            // AudioFormat 설정
//            AudioFormat audioFormat = new AudioFormat(16000, 16, 1, true, false);
//
//            // 길이를 명시적으로 지정하기 위해 스트림의 전체 길이를 구해본다.
//            byte[] audioData = inputStream.readAllBytes();
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(audioData);
//
//            // AudioInputStream 생성 (length를 명시적으로 지정)
//            AudioInputStream audioStream = new AudioInputStream(byteArrayInputStream, audioFormat, audioData.length);
//
//            // WAV 형식으로 출력
//            AudioSystem.write(audioStream, AudioFileFormat.Type.WAVE, response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "오디오 캡차 생성 중 오류 발생");
//        }
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		 // 사용자가 입력한 오디오 캡차 답안
//        String userAnswer = request.getParameter("answer");
//        
//        // 세션에서 정답을 가져옴
//        HttpSession session = request.getSession();
//        String correctAnswer = (String) session.getAttribute("audioCaptchaAnswer");
//
//        // 답안 비교
//        String message;
//        if (correctAnswer != null && correctAnswer.equals(userAnswer)) {
//            message = "캡차 통과 성공!";
//        } else {
//            message = "캡차 실패. 다시 시도해 주세요.";
//        }
//
//        // 메시지를 request에 저장하여 결과 페이지로 전달
//        request.setAttribute("message", message);
//        request.getRequestDispatcher("/member.jsp").forward(request, response);
//	}
//	
//	
//
//}
package member;


