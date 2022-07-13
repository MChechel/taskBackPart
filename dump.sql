-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: homework
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `homework`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `homework` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `homework`;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` bigint NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `personal_code` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fullName` (`first_name`,`last_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (31,'Chechel',NULL,'123456789','Lastekodu26, 52','mixa262@gmail.com','Mykhailo','56664273'),(33,'456',NULL,'666','Lastekodu26, 52','mixa262@gmail.com','Mykhailo','56664273');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_loan_application_list`
--

DROP TABLE IF EXISTS `client_loan_application_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_loan_application_list` (
  `client_id` bigint NOT NULL,
  `loan_application_list_id` bigint NOT NULL,
  UNIQUE KEY `UK_5iahga4lvvaufdejp4opgj191` (`loan_application_list_id`),
  KEY `FKgealos0fkpw7k4gaky14v1wrf` (`client_id`),
  CONSTRAINT `FKgealos0fkpw7k4gaky14v1wrf` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `FKtfk9ssefirid30aggeypjirtk` FOREIGN KEY (`loan_application_list_id`) REFERENCES `loan_application` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_loan_application_list`
--

LOCK TABLES `client_loan_application_list` WRITE;
/*!40000 ALTER TABLE `client_loan_application_list` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_loan_application_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (35);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan_application`
--

DROP TABLE IF EXISTS `loan_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loan_application` (
  `id` bigint NOT NULL,
  `loan_sum` double NOT NULL,
  `loan_type_enum` tinyblob,
  `last_update_time` varchar(255) DEFAULT NULL,
  `loan_type` varchar(255) DEFAULT NULL,
  `client_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKillaajicx0nnb4m3c53ay4531` (`client_id`),
  CONSTRAINT `FKillaajicx0nnb4m3c53ay4531` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan_application`
--

LOCK TABLES `loan_application` WRITE;
/*!40000 ALTER TABLE `loan_application` DISABLE KEYS */;
INSERT INTO `loan_application` VALUES (32,9785,NULL,'2022-07-13 09:07:54- last update time','STUDENT_LOAN',31),(34,999,NULL,'2022-07-13 09:07:44- last update time','REAL_ESTATE',33);
/*!40000 ALTER TABLE `loan_application` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-13 21:07:58
