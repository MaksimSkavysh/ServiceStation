CREATE DATABASE  IF NOT EXISTS `service_station` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `service_station`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: service_station
-- ------------------------------------------------------
-- Server version	5.6.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cars`
--

DROP TABLE IF EXISTS `cars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cars` (
  `VIN` varchar(20) NOT NULL,
  `MAKE` varchar(20) NOT NULL,
  `MODEL` varchar(20) NOT NULL,
  `YEAR` int(11) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  PRIMARY KEY (`VIN`),
  KEY `USER_ID` (`USER_ID`),
  CONSTRAINT `cars_ibfk_1` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars`
--

LOCK TABLES `cars` WRITE;
/*!40000 ALTER TABLE `cars` DISABLE KEYS */;
INSERT INTO `cars` VALUES ('21g123g12','volvo','xc60',2001,1),('as223f4434','reno','logan',2006,2),('as223f44341xc1','volvo','qw12',2006,6),('as223f44342sw2','reno','logan',2006,6),('as223f4434wzfg','lada','kalina',2000,3),('assdf1232esd2','bmw','x5',2007,6),('asw231asw22','bmw','x7',2009,6);
/*!40000 ALTER TABLE `cars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `ORDER_ID` int(11) NOT NULL,
  `DATE` varchar(20) NOT NULL,
  `AMOUNT` int(11) NOT NULL,
  `STATUS` varchar(20) NOT NULL,
  `VIN` varchar(20) NOT NULL,
  PRIMARY KEY (`ORDER_ID`),
  UNIQUE KEY `ORDER_ID_UNIQUE` (`ORDER_ID`),
  KEY `VIN` (`VIN`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`VIN`) REFERENCES `cars` (`VIN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'01.10.2015',1500,'completed','21g123g12'),(2,'01.10.2015',2000,'in progress','21g123g12');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `USER_ID` int(11) NOT NULL,
  `FIRSTNAME` varchar(20) NOT NULL,
  `LASTNAME` varchar(20) NOT NULL,
  `PHONE` varchar(20) NOT NULL,
  `ADDRESS` varchar(40) DEFAULT NULL,
  `EMAIL` varchar(20) DEFAULT NULL,
  `BIRTHDAY` varchar(20) NOT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'vasya','petrov','+375291186343','gagarina 21','vasya1@mail.ru','30.06.1995'),(2,'vasya','petrov','+375291186313','gagarina 22','vasya2@mail.ru','30.02.1995'),(3,'vasya3','petrov3','+375291186321','gagarina 23','vasya3@mail.ru','30.03.1995'),(4,'vasya4','petrov4','+375291186349','gagarina 24','vasya3@mail.ru','30.06.1995'),(5,'vasya','crem','+375291182349','gag','1@m.ru','10.09.1899'),(6,'bob ','ross','+375291907349','florida, 17 street 12A','bobo@mail.ru','29.10.1942');
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

-- Dump completed on 2015-10-02 10:55:12
