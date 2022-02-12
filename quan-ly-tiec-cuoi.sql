-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlytieccuoi
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `firstName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `lastName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateOfBirth` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `phoneNumber` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `openingDate` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `positions` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `qualification` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `startingDate` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `staff_id_UNIQUE` (`admin_id`),
  KEY `positions` (`positions`),
  CONSTRAINT `positions` FOREIGN KEY (`positions`) REFERENCES `positions` (`position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('09964974-35a3-4866-bc1d-b8edb7f417d9','Thảo','Trương','2019-10-27','Female','0975524661','1751010142thao@ou.edu.vn','Nguyễn Kiệm, p3, Gò Vấp','2019-10-30','P01','Khiêm tốn, thật thà','2019-09-29'),('123456','Vĩ','Võ','1989-02-03','Female','0857727702','1751010181vi@gmail.com','Nguyễn kiệm, p3, Gò Vấp','2019-10-30','P02','Hòa đồng','1989-02-03'),('1d9f0287-fcfc-4690-855a-7b4ae7b2fb61','Trinh','Nguyễn','2019-10-03','Female','0244568991','trinh@gmail.com','Cà Mau','2019-10-30','P03','Vui Vẻ','2019-10-09'),('311181e0-5e08-4103-b40b-62ed5009ad42','Hằng','Nguyễn','2019-10-11','Female','0255689777','hangntk@gmail.com','Gò Vấp','2019-10-30','P01','Vui vẻ','2019-10-10'),('73479b09-9738-47b0-abd7-d5b30c89ce24','Vi','Mai','2019-11-01','Female','0521447881','1754052101vi@ou.edu.vn','Bình Dương','2019-11-05','P02','Dễ gần, Vui vẻ','2019-11-13'),('7f2d1762-0b00-496b-b88f-f1cc581a6b2a','Thanh','Nguyễn','2019-10-17','Female','0955622144','thanh.ntk@gmail.com','Bình Thạnh','2019-10-30','P01','Thanh','2019-10-02'),('9a0ed69a-4c5d-499c-aaa7-f32a2ce2d211','Nhi','Trần','2019-12-02','Female','0125887744','nhitran@gmail.com','edwe','2019-12-19','P02','vui vẻ','2019-12-25'),('a65d1e10-eeaa-47fd-ae29-e2a978866848','Thảo','Lương','2019-11-06','Female','0123654789','thao@gmail.com','Tân Bình','2019-11-21','P02','Vui tính','2019-11-29'),('a67b2eda-085e-4bce-afb5-0689dda3cd05','Hoa','Nguyễn','2019-11-14','Female','0456118755','1751010037hoa@gmail.com','Tân Bình','2019-11-10','P03','Vi tính, Thật thà','2019-10-30'),('b39af2d0-874e-4f13-8fbf-991fb32186f9','Triệu','Nguyễn','2019-11-02','Female','0332456897','trieu.nt@gmail.com','Gò Vấp','2019-11-05','P02','Vui tính','2019-11-01'),('e403d209-317a-457e-aa87-9432620fb214','Huy','Nguyễn','2019-11-05','Male','0123654789','175101005huy@ou.edu.vn','Gò Vấp','2019-11-21','P03','Vui tính','2019-11-22');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banquethall`
--

DROP TABLE IF EXISTS `banquethall`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banquethall` (
  `hallID` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `acreage` double DEFAULT NULL,
  `maxPeople` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `image` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `openDate` date DEFAULT NULL,
  PRIMARY KEY (`hallID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banquethall`
--

LOCK TABLES `banquethall` WRITE;
/*!40000 ALTER TABLE `banquethall` DISABLE KEYS */;
INSERT INTO `banquethall` VALUES ('6d0d380b-04fd-4963-82a0-1351db95fdac','Hall E',600,550,17000,'h1.png','2019-11-15'),('899cbaaa-0d78-4faa-bcc6-0f22dd5964a3','Hall B',500,300,10000,'h2.png','2019-11-15'),('936c73bf-e7b4-4aff-acb6-d94cc8e62fc0','Hall A',900,800,20000,'h3.png','2019-11-15'),('a7c6de23-a776-40e6-8fa1-af44985ac85d','Hall D',1000,900,30000,'h4.png','2019-11-15'),('c6845fec-ae0d-46d2-aa3f-82bae6ee41e6','Hall C',700,500,15000,'h5.png','2019-11-15');
/*!40000 ALTER TABLE `banquethall` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customer_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `firstName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `lastName` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateOfBirth` date DEFAULT NULL,
  `gender` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phoneNumber` varchar(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `openingDate` date DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `people_id_UNIQUE` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES ('a20b252d-cd33-41a0-b673-84d9d7316a33','Vĩ','Võ','2019-09-03','Female','0857727702','vothivi@gmail.com','nguyễn kiệm','2020-01-02'),('b43d419c-6b89-43ff-abdc-72fd685a6a51','Như','Bùi','2019-10-28','Female','0245568891','nhu@gmail.com','Tây Ninh','2019-11-21'),('b96acb0b-67f4-493a-abc7-9ffb6897cde0','Nam','Trần','1997-12-01','Male','0364589941','nam.tv@gmail.com','Lâm Đồng','2019-11-21'),('bbb2dcd1-a74b-4207-9d9f-6c3226d7b3fb','Duyên','Lê','1996-01-15','Female','0123654798','duyen.ltm@gmail.com','Kiên Giang','2019-11-05'),('fbcdca40-54ce-4dc2-b139-d87ab67bcf68','Huỳnh','Nguyễn','1989-02-22','Female','0123658749','huynh.nt@gmail.com','Đà Nẵng','2019-11-21');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dateofbooking`
--

DROP TABLE IF EXISTS `dateofbooking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dateofbooking` (
  `date_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `dateAction` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `hall_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`date_id`),
  KEY `hall_id` (`hall_id`),
  CONSTRAINT `hall_id` FOREIGN KEY (`hall_id`) REFERENCES `banquethall` (`hallID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dateofbooking`
--

LOCK TABLES `dateofbooking` WRITE;
/*!40000 ALTER TABLE `dateofbooking` DISABLE KEYS */;
INSERT INTO `dateofbooking` VALUES ('00b1bcbd-d858-4b22-8dff-002a927d6017','2020-01-09','c6845fec-ae0d-46d2-aa3f-82bae6ee41e6'),('0ca43fb3-9182-4aff-8672-c2fd05d67497','2020-01-02','6d0d380b-04fd-4963-82a0-1351db95fdac'),('2b7112ed-2f63-4b9d-a3de-7efb6dadfbdd','2019-12-31','899cbaaa-0d78-4faa-bcc6-0f22dd5964a3'),('2f0ff3fa-bd32-4bf0-880a-18e752f0c500','2020-01-28','936c73bf-e7b4-4aff-acb6-d94cc8e62fc0'),('3384130e-0644-413a-82d3-fa2abc78f6d5','2019-12-28','936c73bf-e7b4-4aff-acb6-d94cc8e62fc0'),('35a5cf33-e588-4d16-b896-17020a4f2397','2020-01-05','899cbaaa-0d78-4faa-bcc6-0f22dd5964a3'),('3eb2d30d-a025-4819-9df2-c2d017b93d61','2019-12-26','a7c6de23-a776-40e6-8fa1-af44985ac85d'),('4f659ef8-ac13-4aa8-8cf1-b2cc56606359','2020-01-01','936c73bf-e7b4-4aff-acb6-d94cc8e62fc0'),('513fee64-f00c-4b80-8a92-42a025f305a6','2019-12-29','a7c6de23-a776-40e6-8fa1-af44985ac85d'),('52811ab3-2c56-4fd1-8db5-eb1440fbb85b','2020-01-17','936c73bf-e7b4-4aff-acb6-d94cc8e62fc0'),('60309d7b-75e1-4524-ad61-765284ce0add','2020-01-22','936c73bf-e7b4-4aff-acb6-d94cc8e62fc0'),('65565959-088d-4a56-b0d5-c92a99179690','2020-01-07','936c73bf-e7b4-4aff-acb6-d94cc8e62fc0'),('69d496ae-4e5f-4f0e-aefd-ac3fe0d08af5','2019-12-29','936c73bf-e7b4-4aff-acb6-d94cc8e62fc0'),('757843c0-9d01-4099-8d01-16f5586e9c9d','2020-01-16','899cbaaa-0d78-4faa-bcc6-0f22dd5964a3'),('78d3df88-69e5-4aa1-9b5a-2636053efb99','2019-12-26','899cbaaa-0d78-4faa-bcc6-0f22dd5964a3'),('7ed5ab9e-40ac-43ec-8641-e792a56a99d5','2020-01-20','936c73bf-e7b4-4aff-acb6-d94cc8e62fc0'),('813205b4-6c19-49f8-b8cd-08133ef286ca','2020-01-03','899cbaaa-0d78-4faa-bcc6-0f22dd5964a3'),('92869bc9-0dd2-4810-864e-553ce8ac72ee','2020-01-03','a7c6de23-a776-40e6-8fa1-af44985ac85d'),('9f9430b5-5919-4cca-bd83-b2b77aca8456','2019-12-26','936c73bf-e7b4-4aff-acb6-d94cc8e62fc0'),('a9f64224-a3c2-430f-840d-42f4cb6a6208','2020-01-07','a7c6de23-a776-40e6-8fa1-af44985ac85d'),('ccd71238-a745-4d22-a5f4-912c0f8c8338','2020-01-05','c6845fec-ae0d-46d2-aa3f-82bae6ee41e6'),('d59fcd0d-52b0-452b-bdf4-3aea947799c5','2019-12-31','c6845fec-ae0d-46d2-aa3f-82bae6ee41e6'),('fcace88c-cc2e-42bc-9515-28acf7630de4','2019-12-24','899cbaaa-0d78-4faa-bcc6-0f22dd5964a3');
/*!40000 ALTER TABLE `dateofbooking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `home`
--

DROP TABLE IF EXISTS `home`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `home` (
  `ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `image` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `IDType` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `home`
--

LOCK TABLES `home` WRITE;
/*!40000 ALTER TABLE `home` DISABLE KEYS */;
INSERT INTO `home` VALUES ('1','11','f1.png',NULL),('2','22','f2.png',NULL),('3','23','f3.png',NULL);
/*!40000 ALTER TABLE `home` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menugroup`
--

DROP TABLE IF EXISTS `menugroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menugroup` (
  `ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menugroup`
--

LOCK TABLES `menugroup` WRITE;
/*!40000 ALTER TABLE `menugroup` DISABLE KEYS */;
INSERT INTO `menugroup` VALUES ('01','Food'),('02','Drink'),('03','Service');
/*!40000 ALTER TABLE `menugroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderdetail`
--

DROP TABLE IF EXISTS `orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetail` (
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `maSFD` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `unitPrice` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `discount` float DEFAULT NULL,
  PRIMARY KEY (`order_id`,`maSFD`),
  KEY `maSFD` (`maSFD`),
  CONSTRAINT `maSFD` FOREIGN KEY (`maSFD`) REFERENCES `servicefooddrink` (`ID`),
  CONSTRAINT `order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetail`
--

LOCK TABLES `orderdetail` WRITE;
/*!40000 ALTER TABLE `orderdetail` DISABLE KEYS */;
INSERT INTO `orderdetail` VALUES ('2f5c56c3-576b-4bf2-903a-116591df8446','19126718-a6fd-464e-9d8e-5e88906b59cd',1,'79,000',0.15),('2f5c56c3-576b-4bf2-903a-116591df8446','382c7902-d547-4e87-8976-6f28b5564143',7,'539,000',0.15),('2f5c56c3-576b-4bf2-903a-116591df8446','75492487-bdd4-4309-bcff-5efe83545789',2,'178,000',0.15),('4f7f9cab-9739-4095-9fe2-1ad2c988994a','19126718-a6fd-464e-9d8e-5e88906b59cd',2,'158,000',0.15),('4f7f9cab-9739-4095-9fe2-1ad2c988994a','31a77358-563c-468b-849e-cadc2f053411',2,'118,000',0.15),('4f7f9cab-9739-4095-9fe2-1ad2c988994a','75492487-bdd4-4309-bcff-5efe83545789',7,'623,000',0.15),('ed6f0e11-104e-460e-9d77-39ca6e12cd21','07b18807-76ca-4279-9748-597b7bb66de6',4,'196,000',0.15),('ed6f0e11-104e-460e-9d77-39ca6e12cd21','31a77358-563c-468b-849e-cadc2f053411',5,'295,000',0.15),('ed6f0e11-104e-460e-9d77-39ca6e12cd21','75492487-bdd4-4309-bcff-5efe83545789',2,'178,000',0.15);
/*!40000 ALTER TABLE `orderdetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orderId` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `customerID` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `hallID` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `orderDate` date DEFAULT NULL,
  `totalMoney` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateID` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `timeOfDay` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderId`,`customerID`,`hallID`,`dateID`),
  KEY `maSA` (`hallID`),
  KEY `maCustomer_idx` (`customerID`),
  KEY `dateID` (`dateID`),
  CONSTRAINT `customerID` FOREIGN KEY (`customerID`) REFERENCES `customers` (`customer_id`),
  CONSTRAINT `dateID` FOREIGN KEY (`dateID`) REFERENCES `dateofbooking` (`date_id`),
  CONSTRAINT `hallID` FOREIGN KEY (`hallID`) REFERENCES `banquethall` (`hallID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('2f5c56c3-576b-4bf2-903a-116591df8446','bbb2dcd1-a74b-4207-9d9f-6c3226d7b3fb','936c73bf-e7b4-4aff-acb6-d94cc8e62fc0','2020-01-04','431,000','2f0ff3fa-bd32-4bf0-880a-18e752f0c500',1),('407b056f-cddd-4efb-8cf9-1e18ded32139','a20b252d-cd33-41a0-b673-84d9d7316a33','936c73bf-e7b4-4aff-acb6-d94cc8e62fc0','2020-01-04','20,000','7ed5ab9e-40ac-43ec-8641-e792a56a99d5',NULL),('4f7f9cab-9739-4095-9fe2-1ad2c988994a','fbcdca40-54ce-4dc2-b139-d87ab67bcf68','899cbaaa-0d78-4faa-bcc6-0f22dd5964a3','2020-01-04','909,000','757843c0-9d01-4099-8d01-16f5586e9c9d',1),('ed6f0e11-104e-460e-9d77-39ca6e12cd21','b43d419c-6b89-43ff-abdc-72fd685a6a51','936c73bf-e7b4-4aff-acb6-d94cc8e62fc0','2020-01-04','689,000','60309d7b-75e1-4524-ad61-765284ce0add',2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `positions`
--

DROP TABLE IF EXISTS `positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `positions` (
  `position_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `positionName` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `positions`
--

LOCK TABLES `positions` WRITE;
/*!40000 ALTER TABLE `positions` DISABLE KEYS */;
INSERT INTO `positions` VALUES ('P01','Staff'),('P02','Employee'),('P03','Manager');
/*!40000 ALTER TABLE `positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicefooddrink`
--

DROP TABLE IF EXISTS `servicefooddrink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicefooddrink` (
  `ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `image` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `unit` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `IDType` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `unitPrice` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `loai_ID` (`IDType`),
  CONSTRAINT `IDType` FOREIGN KEY (`IDType`) REFERENCES `menugroup` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicefooddrink`
--

LOCK TABLES `servicefooddrink` WRITE;
/*!40000 ALTER TABLE `servicefooddrink` DISABLE KEYS */;
INSERT INTO `servicefooddrink` VALUES ('01104587-cee0-470f-bf72-2b625d46b7e6','sadasd','doan 1 (2).png','sdad','qwqwqwq','120,500','01',0,NULL),('07b18807-76ca-4279-9748-597b7bb66de6','Melted cake','f10.png','Cái','black-brown-and-white-melted-cake-on-round-white-ceramic','49,000','01',0,NULL),('19126718-a6fd-464e-9d8e-5e88906b59cd','Seabass lavash','f3.png','Đĩa','seabass-lavash-dish','79,000','01',0,NULL),('1dd6b0cc-c37b-4db0-bdb7-d51c3eab2252','Craft beer','dr7.png','Ly','-','19,000','02',0,NULL),('31a77358-563c-468b-849e-cadc2f053411','Chocolate Molten','f9.png','Đĩa','Chocolate-Molten-Lava-Cake','59,000','01',0,NULL),('382c7902-d547-4e87-8976-6f28b5564143','Frog tim green','f5.png','Đĩa','Frog tim green','77,000','01',0,NULL),('470c02bf-e340-4f35-9b2f-14c522a55509','Glassware lemon','dr6.png','Ly','-','30,000','02',0,NULL),('5ee224a2-d970-49c7-82bf-c2f9caa322b8','Sherry','dr8.png','Chai','-','2,300,000','02',0,NULL),('68159937-c17d-4e78-937d-2e616ec3d874','Chicken spaghetti','f7.png','Đĩa','Không ','120,000','01',0,NULL),('71770fed-98fc-47f8-bb2c-48a7bdacf95f','Voodco Ranger Beer','dr10.png','Chai','-','29,000','02',0,NULL),('75492487-bdd4-4309-bcff-5efe83545789','Salmon meals','f2.png','Đĩa','salmon-dish-food-meal','89,000','01',0,NULL),('75ff9089-eda8-4210-b4de-9bfd4125e369','Water','dr3.png','Ly','-','9,000','02',0,NULL),('7d0b6f11-3d8a-498a-a8b6-dbb539c46d0c','Beers','dr2.png','Chai','Heineken - Budweiser','21,000','02',0,NULL),('7eb708f0-2361-4d53-9d35-337158ceaa9c','Corona beer','dr1.png','Chai','Không','21,000','02',0,NULL),('80c6e802-c24d-4fa3-990e-1e5a43067139','Hamberger','f4.png','Cái','Không','89,000','01',0,NULL),('8c648b80-fe4b-438d-a203-61327ff8661d','fsf','ĐIỀU PHỐI ĐẤT-Model.png','sdfsd','sdfs','12,000','03',0,NULL),('96603a2c-f589-4e54-991e-9e4bf887c5d6','Fish dish','f8.png','Đĩa','Không ','110,000','01',0,NULL),('a68f37d1-ab9d-4997-8bae-483d783b9327','Lemon juice','dr5.png','Ly','-','30,000','02',0,NULL),('ae63c404-f4fc-4ad4-b0f2-8e8ab9223491','Honey tea','dr4.png','Ly','-','25,000','02',0,NULL),('c5326d45-7619-4834-b966-7264aa69bea1','BBQ Chicken','f6.png','Đĩa','Không','230,000','01',0,NULL),('ed6fdd06-04e0-4fd4-92a3-7534f63db0bf','Fish and carry','f1.png','Combo','Không','290,000','01',0,NULL),('f21b77d1-40fa-4261-b2f3-40966cebca29','Bar Beer','dr9.png','Ly','-','25,000','02',0,NULL);
/*!40000 ALTER TABLE `servicefooddrink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `ID` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `userName` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  CONSTRAINT `ID` FOREIGN KEY (`ID`) REFERENCES `admin` (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('09964974-35a3-4866-bc1d-b8edb7f417d9','thao','$2a$05$gkmsBzPeiMxWVUIr3NtE2.ewCiWh3VyUQfN2QJ68wVy.PpCx8uEuO'),('1d9f0287-fcfc-4690-855a-7b4ae7b2fb61','vivi','$2a$05$wms/3fUCFb5J.JMi2bmO6.AdgpwkMRzxsxmDxaqzscRrM.xLLiB8u'),('311181e0-5e08-4103-b40b-62ed5009ad42','vothi','$2a$05$Im2aQOi/vAwqqzw7oen4bue81KlRDoPw5ZDMCbanDfiuMc8VhZ0J6'),('73479b09-9738-47b0-abd7-d5b30c89ce24','xczx','$2a$05$7ihmnrJ.GDhfmgvfK0TFIub8kUPSRPzXGneItyrLLtqvmzIc5aPN.'),('7f2d1762-0b00-496b-b88f-f1cc581a6b2a','hien','$2a$05$vvtVk6Hd/qYKAEehag44heeDTM3qEWWUkoccvj9vExobI44JYRRp2'),('9a0ed69a-4c5d-499c-aaa7-f32a2ce2d211','nhi123','$2a$05$3xIg1O3lKutHiNTtpa./mOQxMVBblANNBDY4xvn6OAlVxLuN4R9eC'),('a65d1e10-eeaa-47fd-ae29-e2a978866848','thao123','$2a$05$ldr0rQxLeQmxKNi1jqCq8.J0UA9Ld36LbncZd/MZ6SfcUUpUvWIBm'),('a67b2eda-085e-4bce-afb5-0689dda3cd05','vonam','$2a$05$3KPVnDckDakXrvp.ComKN./quhUrb/AzvHOIG/wn.mRcMwAE/mCDu'),('e403d209-317a-457e-aa87-9432620fb214','thao125','$2a$05$P85DQzm1I/7.lX99vv0VTu2ttKrUmMc/DP51fzwUjJ4aZWrKszWG2');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-05 15:50:36
