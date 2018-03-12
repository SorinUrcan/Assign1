CREATE DATABASE  IF NOT EXISTS `bank` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bank`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: bank
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `clientaccounts`
--

DROP TABLE IF EXISTS `clientaccounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientaccounts` (
  `IDaccount` int(11) NOT NULL AUTO_INCREMENT,
  `accountType` varchar(45) DEFAULT NULL,
  `ammountMoney` int(11) DEFAULT NULL,
  `IDclient` int(11) DEFAULT NULL,
  `dateCreated` datetime DEFAULT NULL,
  PRIMARY KEY (`IDaccount`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientaccounts`
--

LOCK TABLES `clientaccounts` WRITE;
/*!40000 ALTER TABLE `clientaccounts` DISABLE KEYS */;
INSERT INTO `clientaccounts` VALUES (1,'ron',1160,1,'2015-04-27 00:00:00'),(2,'ron',1240,1,'2015-04-27 00:00:00'),(3,'ron',2000,2,'2015-04-27 00:00:00'),(4,'ron',1400,1,'2015-04-27 00:00:00'),(5,'ron',5500,2,'2017-03-30 00:00:00'),(6,'ron',1000,6,'2017-03-30 00:00:00'),(7,'ron',3000,6,'2017-03-30 00:00:00'),(8,'ron',5000,1,'2017-03-30 00:00:00');
/*!40000 ALTER TABLE `clientaccounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clients` (
  `IDclient` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `personalNumericalCode` bigint(20) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `IDcardNumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDclient`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='			';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (1,'Mircea','Samuel',1930202101019,'Strada Meteor',102),(2,'Moga','Andrei',1,'Strada Viilor',101),(4,'Sami','Sami',999,'Strada A',1010),(6,'Sam','Samuel',1930202111111,'Strada A',10102);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactionhistory`
--

DROP TABLE IF EXISTS `transactionhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactionhistory` (
  `IDtransaction` int(11) NOT NULL AUTO_INCREMENT,
  `IDclient` int(11) DEFAULT NULL,
  `fromAccount` varchar(45) DEFAULT NULL,
  `toAccount` varchar(45) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `ammountTransfered` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDtransaction`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactionhistory`
--

LOCK TABLES `transactionhistory` WRITE;
/*!40000 ALTER TABLE `transactionhistory` DISABLE KEYS */;
INSERT INTO `transactionhistory` VALUES (1,1,'1','2','2015-04-27 00:00:00',10),(2,1,'2','1','2015-04-27 00:00:00',25),(3,1,'1','Orange','2015-04-27 00:00:00',10),(4,1,'1','2','2015-04-27 00:00:00',500),(5,1,'1','3','2015-04-27 00:00:00',500),(6,1,'2','1','2017-03-30 00:00:00',500),(7,1,'2','1','2017-03-30 00:00:00',500),(8,1,'4','RDS','2017-03-30 00:00:00',50),(9,1,'1','Orange','2017-03-30 00:00:00',50),(10,1,'1','Orange','2017-03-30 00:00:00',50),(11,1,'1','2','2017-03-30 00:00:00',120),(12,1,'1','2','2017-03-30 00:00:00',120),(13,1,'4','E-ON','2017-03-30 00:00:00',50);
/*!40000 ALTER TABLE `transactionhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccounts`
--

DROP TABLE IF EXISTS `useraccounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccounts` (
  `ID` int(11) NOT NULL,
  `userName` varchar(45) DEFAULT NULL,
  `userType` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccounts`
--

LOCK TABLES `useraccounts` WRITE;
/*!40000 ALTER TABLE `useraccounts` DISABLE KEYS */;
INSERT INTO `useraccounts` VALUES (0,'admin','admin','admin'),(1,'client','client','client'),(2,'andrei','client','andrei');
/*!40000 ALTER TABLE `useraccounts` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-13  1:02:42
