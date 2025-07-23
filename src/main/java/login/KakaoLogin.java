package login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class KakaoLogin {
	private String clientId;
	private String redirectUri;
    private static final String TOKEN_URL = "https://kauth.kakao.com/oauth/token";

    public KakaoLogin(String clientId, String redirectUri) {
        this.clientId = clientId;
        this.redirectUri = redirectUri;
    }
    
    public String getAccessToken(String code) throws Exception {
        String params = "grant_type=authorization_code" +
                        "&client_id=" + clientId +
                        "&redirect_uri=" + redirectUri +
                        "&code=" + code;

        URL url = new URL(TOKEN_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(params.getBytes("UTF-8"));
            os.flush();
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

     // JSON 파싱하여 액세스 토큰 반환
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(response.toString());
        return (String) jsonObject.get("access_token"); // 액세스 토큰 반환
        
        
    }
}
