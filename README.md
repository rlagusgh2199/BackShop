# 🛒 BackShop ERP System

간단한 매장 관리(ERP) 시스템으로, 매출, 지출, 재고, 회원 및 관리자 기능 등을 웹 기반으로 구현한 백엔드 프로젝트입니다.  
Java Servlet과 JSP를 기반으로 동작하며, 실무 백엔드 개발 역량을 기르기 위해 설계되었습니다.

---

## 📁 폴더 구조

BackShop/
└── src/
    ├── controller/   # Servlet, Controller 계층
    ├── model/        # DAO, DTO 등 비즈니스 로직
    ├── view/         # JSP, CSS, 이미지 등 화면 UI 구성
    └── config/       # META-INF, WEB-INF, 라이브러리 설정 관련


---

## ⚙️ 기술 스택

- Java 8+
- JSP / Servlet
- JDBC (MySQL)
- HTML / CSS / JS
- GitHub Desktop

---

## 📌 주요 기능

- 🧾 **회원/관리자 로그인 및 인증**
  - 카카오 / 네이버 OAuth 연동
  - 이메일 인증, ID/PW 찾기 기능

- 🛍️ **장바구니 및 상품 관리**
  - 장바구니 추가/제거
  - 주문 처리 및 이력 관리

- 📊 **매출, 지출, 재고 관리**
  - 일별 매출/지출 현황 확인
  - 상품 재고 조회 및 수정

- 🛠️ **관리자 페이지**
  - 회원 리스트 조회
  - 매출/지출 통계 확인

---

## 🗄️ 데이터베이스 구조 (요약)

| 테이블         | 설명              |
|----------------|-------------------|
| `member`       | 사용자/관리자 정보 |
| `product`      | 상품 정보          |
| `cart`         | 장바구니 항목       |
| `orders`       | 주문 내역          |
| `expenses`     | 지출 항목          |

---

## 🎬 시연 영상

[![BackShop 시연 영상](https://img.youtube.com/vi/QNnIRpblNKk/0.jpg)](https://www.youtube.com/watch?v=QNnIRpblNKk)

---

## 🧑‍💻 개발자 정보

| 이름 | 역할 |
|------|------|
| 김현호 | 백엔드 개발, DB 설계, 기능 구현 전반 |

---

## 💡 프로젝트 목적

이 프로젝트는 동양미래대학교 백엔드 실습 프로젝트로 진행되었으며,  
실무에 가까운 구조로 MVC 설계 및 깃허브 협업 환경을 경험하기 위해 개발되었습니다.

---


