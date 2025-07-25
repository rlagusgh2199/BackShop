package chat;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper; // Jackson 라이브러리

@ServerEndpoint("/chat")
public class ChatServer {

    // 현재 연결된 세션을 저장할 Set
    private static Set<Session> clients = new HashSet<>();
    // 각 세션에 대한 별칭을 저장할 Map
    private static Map<Session, String> aliases = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) {
        clients.add(session); // 새 클라이언트 세션 추가
        System.out.println("New connection: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            System.out.println("Raw message received: " + message); // 수신된 원시 메시지 로그
            ObjectMapper objectMapper = new ObjectMapper();
            Message msg = objectMapper.readValue(message, Message.class); // JSON 파싱

            // 메시지에서 필요한 데이터 추출
            String alias = msg.getAlias();
            String userMessage = msg.getMessage();
            String image = msg.getImage(); // 이미지 데이터

            System.out.println("Received message from " + alias + ": " + userMessage);
            if (image != null && !image.isEmpty()) {
                System.out.println("Image data received with length: " + image.length()); // 이미지 데이터 길이 확인
            }

            // 메시지 브로드캐스트
            broadcast(alias, userMessage, image, session);
        } catch (IOException e) {
            System.err.println("Error parsing message: " + e.getMessage());
            e.printStackTrace(); // 예외 로그 출력
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace(); // 예외 로그 출력
        }
    }




    @OnClose
    public void onClose(Session session) {
        clients.remove(session); // 클라이언트 세션 제거
        aliases.remove(session); // 별칭도 제거
        System.out.println("Connection closed: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.err.println("Error on session " + session.getId() + ": " + throwable.getMessage());
    }

    // 모든 클라이언트에게 메시지를 JSON 형식으로 전송하는 메서드
    private void broadcast(String alias, String userMessage, String image, Session sender) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Message msg = new Message();
            msg.setAlias(alias); // 별명 포함
            msg.setMessage(userMessage); // 메시지 설정
            msg.setImage(image); // 이미지 데이터 포함

            String messageJson = objectMapper.writeValueAsString(msg); // 메시지를 JSON 형식으로 변환
            System.out.println("Broadcasting message: " + messageJson); // 전송되는 메시지 로그

            for (Session client : clients) {
                client.getBasicRemote().sendText(messageJson);
                System.out.println("Message sent to client: " + client.getId()); // 클라이언트에게 메시지가 전송되었음을 확인
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
