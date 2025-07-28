-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: jspdb
-- ------------------------------------------------------
-- Server version	8.4.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `member2`
--

DROP TABLE IF EXISTS `member2`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member2` (
  `id1` int NOT NULL AUTO_INCREMENT,
  `id2` varchar(40) NOT NULL,
  `pwd1` varchar(40) NOT NULL,
  `alias` varchar(30) NOT NULL,
  `address` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` varchar(30) DEFAULT '선택 안함',
  `favorite` varchar(30) DEFAULT '선택 안함',
  `type` varchar(30) DEFAULT 'user',
  `inDate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id1`),
  UNIQUE KEY `id2` (`id2`),
  UNIQUE KEY `alias` (`alias`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member2`
--

LOCK TABLES `member2` WRITE;
/*!40000 ALTER TABLE `member2` DISABLE KEYS */;
INSERT INTO `member2` VALUES (1,'root','1234','관리자','','','선택 안함','선택 안함','maneger','2024-12-18 20:54:48');
/*!40000 ALTER TABLE `member2` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_no` int NOT NULL AUTO_INCREMENT,
  `product_no` varchar(50) NOT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `product_image` varchar(255) DEFAULT NULL,
  `order_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `order_state` varchar(50) DEFAULT '주문접수',
  `user_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_category` enum('상의','하의') NOT NULL,
  `product_gender` enum('남자','여자') NOT NULL,
  `product_name` varchar(255) NOT NULL,
  `product_price` decimal(10,2) NOT NULL,
  `product_stock` int NOT NULL,
  `product_temperature` decimal(4,2) DEFAULT NULL,
  `product_humidity` decimal(4,2) DEFAULT NULL,
  `product_image` varchar(255) DEFAULT NULL,
  `product_description` text,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'상의','남자','포근한 감성 가디건',120000.00,20,8.00,NULL,'1734558319510_가디건(남자10).jpg','','2024-12-18 21:45:19'),(2,'상의','남자','쿨한 남자, 반팔의 정석',10000.00,15,20.00,NULL,'1734558519359_남자반팔(20).jpg','','2024-12-18 21:48:39'),(3,'하의','여자','완벽한 핏, 여자 청바지',19900.00,10,10.00,NULL,'1734558557973_여자하의(10).jpg','','2024-12-18 21:49:17'),(4,'상의','남자','가벼움의 정수 여름 셔츠',3.00,3,20.00,NULL,'1734558600308_가벼운셔츠남자(20).jpg','','2024-12-18 21:50:00'),(5,'하의','여자','여름 필수템, 쿨 반바지',5900.00,200,20.00,NULL,'1734558639172_여자하의(20).jpg','','2024-12-18 21:50:39'),(6,'상의','남자','시크함의 매력 가죽자켓',1000000.00,10,15.00,NULL,'1734558707500_자켓(남자15).jpg','','2024-12-18 21:51:47'),(7,'하의','남자','시간을 초월한 클래식, 청바지',40000.00,10,10.00,NULL,'1734558756169_청바지(남자10).jpg','','2024-12-18 21:52:36'),(8,'상의','여자','겨울의 따뜻한 동반자, 패딩코트',200000.00,20,0.00,NULL,'1734558788567_패딩코트(여자).jpg','','2024-12-18 21:53:08'),(9,'상의','남자','스타일과 보온성을 동시에!',200000.00,20,0.00,NULL,'1734558823317_패딩코트남자(0).jpg','','2024-12-18 21:53:43'),(10,'상의','여자','스타일리시한 여성 패딩코트',99999.00,30,0.00,NULL,'1734558855990_패딩코트여자(0).jpg','','2024-12-18 21:54:15');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_cart`
--

DROP TABLE IF EXISTS `shop_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_cart` (
  `no` int NOT NULL AUTO_INCREMENT,
  `product_no` varchar(30) NOT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `product_image` varchar(255) DEFAULT NULL,
  `date` varchar(50) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `user_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_cart`
--

LOCK TABLES `shop_cart` WRITE;
/*!40000 ALTER TABLE `shop_cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_cart` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-19 21:53:39
