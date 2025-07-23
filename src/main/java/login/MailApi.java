package login;


import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class MailApi {
	static Properties prop;
	static Session session;
	static MimeMessage message;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			generateAndSendEmail();
			System.out.println("\n\n ===> your java program successfully. Check your email..");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void generateAndSendEmail() throws Exception {
		final String user = "awosung11@gmail.com";//발신자의 이메일 아이디를 입력
		final String password = "wlfltks@4";
		
		//Step1 프로퍼티 생성
		prop = new Properties();
		//mail.smtp.host 은 이메일 발송을 처리해줄 SMTP 서버를 나타냄
		//gmail: "smtp.gmail.com", 네이버: "smtp.naver.com"
		prop.put("mail.smtp.host", "smtp.gmail.com");
		//포트는 gmail의 경우 465, 네이버의 경우 587 사용
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		//Steo2 세션 설정
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			
			//수신자메일주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("awosung00@naver.com"));
			
			//Subject
			message.setSubject("제목을 입력하세요");
			
			//Text
			message.setText("내용을 입력하세요");
			
			Transport.send(message); //전송
			System.out.println("message sent successfully...");
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
