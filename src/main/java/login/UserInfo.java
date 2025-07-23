package login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UserInfo {
    private static final String USER_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    // 사용자 정보 전체를 가져오는 메서드
    public String getUserInfo(String accessToken) throws Exception {
        URL url = new URL(USER_INFO_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + accessToken);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        // JSON 파싱
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response.toString());

        return response.toString(); // 전체 응답 반환
    }

    // 닉네임만 반환하는 메서드
    public String getNickname(String accessToken) throws Exception {
        String response = getUserInfo(accessToken);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response);
        JSONObject properties = (JSONObject) jsonObject.get("properties");
        return properties.get("nickname").toString(); // 닉네임만 반환
    }

    // 이메일 반환 메서드
    public String getEmail(String accessToken) throws Exception {
        String response = getUserInfo(accessToken);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response);
        JSONObject kakaoAccount = (JSONObject) jsonObject.get("kakao_account");
        return kakaoAccount.get("email") != null ? kakaoAccount.get("email").toString() : null; // 이메일 반환, null일 경우 처리
    }

    // 성별 반환 메서드 추가
    public String getGender(String accessToken) throws Exception {
        String response = getUserInfo(accessToken);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response);
        JSONObject kakaoAccount = (JSONObject) jsonObject.get("kakao_account");
        return kakaoAccount.get("gender") != null ? kakaoAccount.get("gender").toString() : "선택 안함"; // 성별 반환
    }
    
    // 관심분야 반환 메서드 추가
    public String getFavorite(String accessToken) throws Exception {
        String response = getUserInfo(accessToken);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response);
        JSONObject kakaoAccount = (JSONObject) jsonObject.get("kakao_account");
        return kakaoAccount.get("favorite") != null ? kakaoAccount.get("favorite").toString() : "선택 안함"; // 관심분야 반환
    }
}
