# 🛒 BackShop ERP System

간단한 매장 관리(ERP) 시스템으로, 매출, 지출, 재고, 회원 및 관리자 기능 등을 웹 기반으로 구현한 백엔드 프로젝트입니다.  
Java Servlet과 JSP를 기반으로 동작하며, 실무 백엔드 개발 역량을 기르기 위해 설계되었습니다.

---

## 📁 폴더 구조

```
BackShop/
└── src/
    ├── controller/   # Servlet, Controller 계층
    ├── model/        # DAO, DTO 등 비즈니스 로직
    ├── view/         # JSP, CSS, 이미지 등 화면 UI 구성
    └── config/       # META-INF, WEB-INF, 라이브러리 설정 관련
```

---

## ⚙️ 기술 스택

- Java 8+
- JSP / Servlet
- JDBC (MySQL)
- HTML / CSS / JS
- GitHub Desktop
- Kakao & Naver Login API
- 날씨 공공데이터 API

---

## 📌 주요 기능

- 🧾 **회원/관리자 로그인 및 인증**
  - 카카오 / 네이버 OAuth 연동
  - 이메일 인증, ID/PW 찾기 기능

- 🛍️ **장바구니 및 상품 관리**
  - 장바구니 추가/제거
  - 주문 처리 및 이력 관리

- 👕 **오늘의 코디 / 나만의 코디**
  - 날씨 API 기반 추천
  - 관심분야, 성별 기반 상품 추천

- 📊 **매출, 지출, 재고 관리**
  - 일별 매출/지출 현황 확인
  - 상품 재고 조회 및 수정

- 🛠️ **관리자 페이지**
  - 회원 리스트 조회 및 수정/탈퇴
  - 상품 등록, 수정, 삭제
  - 주문 상태 변경

- 💬 **실시간 채팅 기능**
  - 사용자 간 채팅 기능
  - 상품 추천 및 이미지 공유 가능

---

## 🎬 시연 영상

[![BackShop 시연 영상](https://img.youtube.com/vi/QNnIRpblNKk/0.jpg)](https://www.youtube.com/watch?v=QNnIRpblNKk)

---

## 🗄️ 데이터베이스 구조 (요약)

| 테이블명     | 설명                     |
|--------------|--------------------------|
| `member2`    | 회원 정보 (id, pw, email 등) |
| `products`   | 상품 정보 (카테고리, 가격, 재고 등) |
| `shop_cart`  | 장바구니 항목             |
| `orders`     | 주문 내역 및 상태 관리     |

---

## 🧪 실행 방법

1. Tomcat 서버 설치 (버전 9 이상 추천)
2. MySQL DB 실행 및 `BackShop_Dump.sql` 실행
3. Eclipse 또는 IntelliJ에서 프로젝트 import
4. `src/view/` 내 JSP 진입 → `web.xml` 기준 서블릿 매핑 확인
5. 실행 후 `localhost:8080/backshop` 으로 접속

---

## 👤 팀원 / 기여

| 이름     | 역할                          |
|----------|-------------------------------|
| 양민우   | 백엔드 전체 개발 / 프로젝트 구조 리팩토링 / GitHub 관리 |
| 김현호   | 로그인, 회원가입, 관리자 기능 구현 |
| 나은주   | 채팅, 카카오 로그인 연동 |
| 임종원   | 오늘의 코디, 나만의 코디 추천 알고리즘 |

---

## 🎓 프로젝트 정보

- 과목명: 백엔드 실습 (동양미래대학교 컴퓨터소프트웨어공학과)
- 개발 기간: 2024년 10월 ~ 12월
- 개발 방식: JSP + MVC Model 2 구조

---