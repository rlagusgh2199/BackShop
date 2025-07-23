package com.backshop.weather;

public class ApiItem {
    private String baseDate;  // 기준 날짜
    private String baseTime;  // 기준 시간
    private String category;   // 데이터 종류 (예: T1H, REH)
    private int nx;           // X 좌표
    private int ny;           // Y 좌표
    private String obsrValue;  // 관측 값

    // Getter 메서드 추가
    public String getBaseDate() {
        return baseDate;
    }

    public String getBaseTime() {
        return baseTime;
    }

    public String getCategory() {
        return category;
    }

    public int getNx() {
        return nx;
    }

    public int getNy() {
        return ny;
    }

    public String getObsrValue() {
        return obsrValue;
    }
}
