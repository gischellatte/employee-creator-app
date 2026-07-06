-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: employees_db
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employees` (
  `employeeid` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `employment_type` varchar(255) DEFAULT NULL,
  `finish_date` date DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `hours_per_week` int DEFAULT NULL,
  `last_name` varchar(255) NOT NULL,
  `mid_name` varchar(255) DEFAULT NULL,
  `on_going` bit(1) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `work_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`employeeid`),
  CONSTRAINT `employees_chk_1` CHECK ((`hours_per_week` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'서울특별시 강남구 테헤란로 123','jihoon.kim@example.com','Full-Time','2026-12-31','Jihoon',40,'Kim',NULL,_binary '','010-1234-5678','2026-04-06','contract'),(3,'100 go Street','welcome_new@gmail.com','Part-Time','2027-07-06','Soo-jin',20,'Kang',NULL,_binary '\0','0427771498','2026-07-06','contract'),(6,'80 George Street','raina@123.com','Part-Time','2026-01-08','Raina',40,'Mein',NULL,_binary '\0','0490765398','2025-02-08','permanent'),(7,'FL Ave','glt@may.com','Full-Time','2029-09-30','Surak',40,'Baliyo',NULL,_binary '','010-1234-5678','2026-08-06','permanent'),(8,'','zn@zn.com','Part-Time','2026-05-14','nirmala',33,'zuuri',NULL,_binary '\0','','2026-04-23','permanent'),(9,'Chyulu Hills','mzi@example.com','Full-time','2026-04-09','Zazu',40,'Mzingo','',_binary '','08123456789','2026-04-09','contract'),(11,'Psherman Road','kira@outlook.com','Full-Time','2029-09-30','Kion',40,'Kiara',NULL,_binary '','010-1234-5678','2026-08-06','Remote'),(12,'Psherman Road','glt@may.com','Full-Time','2026-06-30','Charbel',40,'Warlon',NULL,_binary '','010-1234-5678','2025-01-06','Remote'),(13,'199 Universe Road','cwwg@gmail.com','permanent','2027-08-03','Gastoux',29,'Chester',NULL,_binary '\0','0430665777','2024-07-02','partTime'),(18,'308 Barbara Avenue','howmail@outlook.com','contract','2024-12-31','Woojin',8,'Hong',NULL,_binary '\0','0420775489','2024-12-29','partTime'),(19,'gangnam','choijunji@naver.com','contract','2026-07-02','Junji',12,'Choi',NULL,_binary '\0','043065908765','2026-07-01','partTime');
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-05 21:26:31
