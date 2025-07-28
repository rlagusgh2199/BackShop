-- 사용자 테이블
CREATE TABLE users (
    id VARCHAR(50) PRIMARY KEY,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    role VARCHAR(20) DEFAULT 'user'
);

-- 상품 테이블
CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    price INT NOT NULL,
    image_url VARCHAR(255),
    stock INT DEFAULT 0
);

-- 장바구니 테이블
CREATE TABLE carts (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(50),
    product_id INT,
    quantity INT DEFAULT 1,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- 주문 테이블
CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(50),
    product_id INT,
    order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'PENDING',
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- 관리자 계정 더미 데이터
INSERT INTO users (id, password, name, email, role)
VALUES ('admin', '1234', '관리자', 'admin@example.com', 'manager');

-- 샘플 상품 데이터
INSERT INTO products (name, category, price, image_url, stock)
VALUES 
('스트라이프 셔츠', '상의', 29900, 'shirt1.jpg', 100),
('블랙진', '하의', 49900, 'pants1.jpg', 80),
('트렌치코트', '아우터', 89900, 'coat1.jpg', 50);