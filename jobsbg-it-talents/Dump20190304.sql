-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: jobs-bg
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `applications`
--

DROP TABLE IF EXISTS `applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `applications` (
  `application_id` int(11) NOT NULL AUTO_INCREMENT,
  `apply_date` date NOT NULL,
  `offer_id` int(11) NOT NULL,
  `user_reg_id` int(11) NOT NULL,
  PRIMARY KEY (`application_id`),
  KEY `fk_applications_offers1_idx` (`offer_id`),
  KEY `fk_applications_users1_idx` (`user_reg_id`),
  CONSTRAINT `fk_applications_offers1` FOREIGN KEY (`offer_id`) REFERENCES `offers` (`offer_id`),
  CONSTRAINT `fk_applications_users1` FOREIGN KEY (`user_reg_id`) REFERENCES `users` (`use_reg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications`
--

LOCK TABLES `applications` WRITE;
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
/*!40000 ALTER TABLE `applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cities`
--

DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cities` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `country_id` int(11) NOT NULL,
  PRIMARY KEY (`city_id`),
  UNIQUE KEY `name_UNIQUE` (`name`),
  KEY `fk_cities_countries1_idx` (`country_id`),
  CONSTRAINT `fk_cities_countries1` FOREIGN KEY (`country_id`) REFERENCES `countries` (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `companies`
--

DROP TABLE IF EXISTS `companies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `companies` (
  `company_reg_id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `website` varchar(45) DEFAULT NULL,
  `bulstat` int(10) unsigned NOT NULL,
  PRIMARY KEY (`company_reg_id`),
  UNIQUE KEY `bulstat_UNIQUE` (`bulstat`),
  KEY `fk_companies_registrations1_idx` (`company_reg_id`),
  CONSTRAINT `fk_companies_registrations1` FOREIGN KEY (`company_reg_id`) REFERENCES `registrations` (`registration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `companies`
--

LOCK TABLES `companies` WRITE;
/*!40000 ALTER TABLE `companies` DISABLE KEYS */;
INSERT INTO `companies` VALUES (4,'Billa','www.billa.bg',10152356),(5,'Microsoft','www.microsoft.com',54214568);
/*!40000 ALTER TABLE `companies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `countries`
--

DROP TABLE IF EXISTS `countries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `countries` (
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`country_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
/*!40000 ALTER TABLE `countries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_category`
--

DROP TABLE IF EXISTS `job_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `job_category` (
  `job_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(45) NOT NULL,
  PRIMARY KEY (`job_category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_category`
--

LOCK TABLES `job_category` WRITE;
/*!40000 ALTER TABLE `job_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_languages`
--

DROP TABLE IF EXISTS `job_languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `job_languages` (
  `job_language_id` int(11) NOT NULL AUTO_INCREMENT,
  `language` varchar(45) NOT NULL,
  PRIMARY KEY (`job_language_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_languages`
--

LOCK TABLES `job_languages` WRITE;
/*!40000 ALTER TABLE `job_languages` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_levels`
--

DROP TABLE IF EXISTS `job_levels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `job_levels` (
  `job_level_id` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(45) NOT NULL,
  PRIMARY KEY (`job_level_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_levels`
--

LOCK TABLES `job_levels` WRITE;
/*!40000 ALTER TABLE `job_levels` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_levels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_types`
--

DROP TABLE IF EXISTS `job_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `job_types` (
  `job_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`job_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_types`
--

LOCK TABLES `job_types` WRITE;
/*!40000 ALTER TABLE `job_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `job_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `locations` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(45) NOT NULL,
  `post_code` int(11) DEFAULT NULL,
  `city_id` int(11) NOT NULL,
  PRIMARY KEY (`location_id`),
  KEY `fk_locations_cities1_idx` (`city_id`),
  CONSTRAINT `fk_locations_cities1` FOREIGN KEY (`city_id`) REFERENCES `cities` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offers`
--

DROP TABLE IF EXISTS `offers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `offers` (
  `offer_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `salary` int(11) DEFAULT NULL,
  `date` date NOT NULL,
  `location_id` int(11) NOT NULL,
  `job_type_id` int(11) NOT NULL,
  `job_level_id` int(11) NOT NULL,
  `job_language_id` int(11) NOT NULL,
  `job_category_id` int(11) NOT NULL,
  `company_reg_id` int(11) NOT NULL,
  PRIMARY KEY (`offer_id`),
  KEY `fk_offers_locations1_idx` (`location_id`),
  KEY `fk_offers_job_types1_idx` (`job_type_id`),
  KEY `fk_offers_job_levels1_idx` (`job_level_id`),
  KEY `fk_offers_job_languages1_idx` (`job_language_id`),
  KEY `fk_offers_job_category1_idx` (`job_category_id`),
  KEY `fk_offers_companies1_idx` (`company_reg_id`),
  CONSTRAINT `fk_offers_companies1` FOREIGN KEY (`company_reg_id`) REFERENCES `companies` (`company_reg_id`),
  CONSTRAINT `fk_offers_job_category1` FOREIGN KEY (`job_category_id`) REFERENCES `job_category` (`job_category_id`),
  CONSTRAINT `fk_offers_job_languages1` FOREIGN KEY (`job_language_id`) REFERENCES `job_languages` (`job_language_id`),
  CONSTRAINT `fk_offers_job_levels1` FOREIGN KEY (`job_level_id`) REFERENCES `job_levels` (`job_level_id`),
  CONSTRAINT `fk_offers_job_types1` FOREIGN KEY (`job_type_id`) REFERENCES `job_types` (`job_type_id`),
  CONSTRAINT `fk_offers_locations1` FOREIGN KEY (`location_id`) REFERENCES `locations` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers`
--

LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
/*!40000 ALTER TABLE `offers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registrations`
--

DROP TABLE IF EXISTS `registrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `registrations` (
  `registration_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(40) NOT NULL,
  `password` varchar(15) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `picture_url` longtext,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`registration_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `password` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registrations`
--

LOCK TABLES `registrations` WRITE;
/*!40000 ALTER TABLE `registrations` DISABLE KEYS */;
INSERT INTO `registrations` VALUES (1,'niki@abv.bg','12345','0895050091',NULL,0),(2,'tedi@abv.bg','1234','0895020304',NULL,0),(3,'tea@gmail.com','qwerty','0885602435',NULL,1),(4,'billa@abv.bg','billa123','0878989897',NULL,0),(5,'microsoft@gmail.com','micro789','0886512345',NULL,1),(6,'chavo@abv.bg','messi10','0875263149',NULL,0),(7,'mtel@abv.bg','1234','088',NULL,0);
/*!40000 ALTER TABLE `registrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `use_reg_id` int(11) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `is_admin` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`use_reg_id`),
  KEY `fk_users_registrations1_idx` (`use_reg_id`),
  CONSTRAINT `fk_users_registrations1` FOREIGN KEY (`use_reg_id`) REFERENCES `registrations` (`registration_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Nikolay','Galchev',1),(2,'Teodora','Topuzova',0),(3,'Tea','Mea',0),(6,'Chavdar','Tonchev',1);
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

-- Dump completed on 2019-03-04 17:47:13
