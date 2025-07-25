package com.backshop.weather;

/* Java 1.8 샘플 코드 */
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.BufferedReader;
import java.io.IOException;

public class ApiResult {
	private static final String SERVICE_KEY = "ztuPqPbx1ekk32l6XPCu8jW1xQSfe52ZzTN%2Fkl1sQtJP1Dmqf1y%2B5qthe%2Bsu9XlYrtURx%2F%2BHzwpFWs4gsH8YqA%3D%3D"; //SERVICE_KEY 상수에 발급받은 일반 인증키 넣기
    private static final String SERVICE_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
	private OkHttpClient client = new OkHttpClient();
	
	private URL buildUrl(String baseDate, int nx, int ny) {
		StringBuilder sb = new StringBuilder(SERVICE_URL)
		.append("?serviceKey=" + SERVICE_KEY)
		.append("&pageNo=1")
		.append("&numOfRows=10")
		.append("&dataType=JSON")
		.append("&base_date=" + baseDate)
		.append("&base_time=0000")
		.append("&nx=" + Integer.toString(nx))
		.append("&ny=" + Integer.toString(ny));
		
		URL url = null;
		try {
			url = new URL(sb.toString());
			System.out.println("URL: " + sb.toString());
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException: " + e.getMessage());
		}
		return url;
	}
	                   
	public String httpGet(String baseDate, int nx, int ny) {
		URL url = buildUrl(baseDate, nx, ny);
		if (url == null) {
			return "";
		}
		
		okhttp3.Request request = new okhttp3.Request.Builder().url(url).build();
		try (Response response = client.newCall(request).execute()) {
			String res = response.body().string();
			return res;
		} catch (IOException e) {
			return "error : " + e.getMessage();
		}
	}
	
	public List<ApiItem> parseJson(String json) {
		JsonElement element = JsonParser.parseString(json);
		JsonArray itemArr = element.getAsJsonObject()
		.get("response").getAsJsonObject()
		.get("body").getAsJsonObject()
		.get("items").getAsJsonObject()
		.get("item").getAsJsonArray();
		
		Gson gson = new Gson();
		List<ApiItem> result = new ArrayList<>();
		for (int i = 0; i<itemArr.size(); i++) {
			ApiItem item = gson.fromJson(itemArr.get(i), ApiItem.class);
			result.add(item);
		}
		
		return result;
	}
	
	public void printResult(List<ApiItem> items) {
		for (ApiItem item: items) {
			System.out.println(item.getCategory() + ": " + item.getObsrValue());
		}
	}
	
	public void printResult2(List<ApiItem> items) {
	    for (ApiItem item : items) {
	        if ("REH".equals(item.getCategory()) || "T1H".equals(item.getCategory())) {
	            System.out.println(item.getCategory() + ": " + item.getObsrValue());
	        }
	    }
	}



    
	public static void main(String[] args) throws IOException {
		final String baseDate = new SimpleDateFormat("yyyyMdd").format(new Date());
        final int nx = 60;
        final int ny = 127;
        
        ApiResult api = new ApiResult();
        String response = api.httpGet(baseDate, nx, ny);
        List<ApiItem> items = api.parseJson(response);
        api.printResult(items);
        
        System.out.println("------------------------------");
        
        api.printResult2(items);
        
    }
}