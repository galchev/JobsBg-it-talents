-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: jobs-bg
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications`
--

LOCK TABLES `applications` WRITE;
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
INSERT INTO `applications` VALUES (2,'1999-01-01',18,73),(7,'2019-03-09',19,73);
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
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES (1,'Sofia',1),(2,'Vratsa',1),(3,'Dobrinishte',1),(4,'Varna',1),(5,'Burgas',1),(6,'Plovdiv',1),(7,'Stara Zagora',1),(8,'Veliko Tarnovo',1),(9,'Berlin',8),(10,'Munich',8),(11,'Frankfurt',8),(12,'Bremen',8),(13,'Paris',3),(14,'Marseile',3),(15,'Moscow',5),(16,'St Peterburg',5),(17,'Madrid',14),(18,'Barcelona',14),(19,'Lisbon',13),(20,'London',9),(21,'Manchester',9),(22,'Rome',7),(23,'Milano',7),(24,'Bukurest',2),(25,'Chicago',4),(26,'New York',4),(27,'Athens',6),(28,'Solun',6),(29,'Amsterdam',10),(30,'Beijing',11),(31,'Seoul',12),(32,'Tokio',12);
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
INSERT INTO `companies` VALUES (4,'BILLA','www.Billa.bg',10152356),(5,'Microsoft','www.microsoft.com',54214568),(7,'Mtel','www.mtel.bg',12343531),(32,'Kaufland','www.kaufland.com',84516239),(33,'Java','www.javaee.com',84515487),(36,'Ikea','www.ikea.com',84515400),(38,'Marlboro','www.marlboro.com',84533400),(40,'Fifa','www.fifa.com',84537898),(43,'Fifa','www.fifa.com',84537899),(59,'Fifa','www.fifa.com',84537822),(63,'Fifa','www.fifa.com',84537821),(65,'Fifa','www.fifa.com',84537820),(66,'BMW','www.bmw.com',84537824),(69,'Fifa','www.fifa.com',84537812),(71,'Fifa','www.lacalut.com',84537810),(72,'Fifa','www.lacalut.com',84537800),(74,'GOlf','www.golf.com',84537550);
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `countries`
--

LOCK TABLES `countries` WRITE;
/*!40000 ALTER TABLE `countries` DISABLE KEYS */;
INSERT INTO `countries` VALUES (1,'Bulgaria'),(11,'China'),(3,'France'),(8,'Germany'),(6,'Greece'),(7,'Italy'),(12,'Japan'),(10,'Netherlands'),(13,'Portugal'),(2,'Romania'),(5,'Russia'),(14,'Spain'),(9,'UK'),(4,'USA');
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_category`
--

LOCK TABLES `job_category` WRITE;
/*!40000 ALTER TABLE `job_category` DISABLE KEYS */;
INSERT INTO `job_category` VALUES (1,'Automobiles'),(2,'Architectures'),(3,'Business'),(4,'Design'),(5,'IT'),(6,'Marketing'),(7,'Media'),(8,'Law'),(9,'Tourism'),(10,'Security'),(11,'Restaurants'),(12,'Sport'),(13,'Accounting'),(14,'Transport'),(15,'HR');
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_languages`
--

LOCK TABLES `job_languages` WRITE;
/*!40000 ALTER TABLE `job_languages` DISABLE KEYS */;
INSERT INTO `job_languages` VALUES (1,'Bulgarian'),(2,'English'),(3,'German'),(4,'French'),(5,'Italian'),(6,'Russian'),(7,'Spanish'),(8,'Turkish'),(9,'Chinese'),(10,'Japan'),(11,'Arabic'),(12,'Estonian'),(13,'Greek');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_levels`
--

LOCK TABLES `job_levels` WRITE;
/*!40000 ALTER TABLE `job_levels` DISABLE KEYS */;
INSERT INTO `job_levels` VALUES (1,'Management'),(2,'Experts'),(3,'Administration'),(4,'Junior'),(5,'Regular');
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_types`
--

LOCK TABLES `job_types` WRITE;
/*!40000 ALTER TABLE `job_types` DISABLE KEYS */;
INSERT INTO `job_types` VALUES (1,'Permanent'),(2,'Temporary'),(3,'Intership'),(4,'Full time'),(5,'Part time'),(6,'Suitable for students'),(7,'Less expirienced');
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (1,'Mall Bulgaria 2',3000,1),(2,'Infiniti tower',3000,1),(3,'Atanas Popadin 26',1000,3),(4,'Rechka 3',1000,2),(5,'Hristo botev 77',3000,4),(6,'Ivan Vazov',3000,5),(7,'Karl Marks 17',2000,6),(8,'Berlin 122',5000,9),(9,'Munich 5',5200,10),(10,'Old Trafford',4400,21),(11,'Stamford Bridge',6000,20),(12,'Giuzeppe Meaza',1200,23),(13,'Palomaris 2',2200,28),(14,'Green Street 29',2400,29);
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
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offers`
--

LOCK TABLES `offers` WRITE;
/*!40000 ALTER TABLE `offers` DISABLE KEYS */;
INSERT INTO `offers` VALUES (7,'JAVAEE',2000,'2012-01-01',1,2,1,1,1,5),(8,'JAVA',2100,'2012-01-01',1,2,1,1,2,5),(9,'JAVA Enterprise',2500,'2012-01-01',1,1,2,2,1,5),(10,'PHP Junior',200,'2016-01-01',1,1,1,1,1,5),(11,'PHP Senior',1200,'2016-01-01',1,1,1,1,1,5),(12,'PHP Regular',2800,'2016-01-01',1,1,2,2,1,5),(13,'PHP',2900,'2016-01-01',1,1,1,1,1,5),(14,'PYTHON Junior',1400,'2016-09-09',1,1,1,1,1,5),(15,'PYTHON Trainee',7000,'2016-09-09',1,1,1,1,1,5),(16,'PYTHON Senior',3000,'2016-09-09',1,1,1,1,2,5),(18,'JAVASCRIPT',1000,'2016-09-09',1,1,1,1,1,5),(19,'Driver',1900,'2019-03-08',1,2,1,1,1,66),(21,'TV tester',1000,'2019-03-08',2,1,1,2,2,65),(22,'Machine mechanic',90000,'2019-03-08',2,1,1,1,1,65),(25,'Toster Tester',1000,'2019-03-09',1,1,1,1,1,66),(26,'Mechanic',1000,'2019-03-09',1,1,1,1,1,66),(27,'Deliver',2000,'2019-03-09',1,1,1,1,1,74);
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
  `password` varchar(100) NOT NULL,
  `phone_number` varchar(20) NOT NULL,
  `picture_url` longtext,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`registration_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `password` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registrations`
--

LOCK TABLES `registrations` WRITE;
/*!40000 ALTER TABLE `registrations` DISABLE KEYS */;
INSERT INTO `registrations` VALUES (1,'niki@abv.bg','b1b3773a05c0ed0176787a4f1574ff0075f7521e','0895050091','https://img1.freepng.fr/20171220/izw/manchester-united-logo-png-5a3aaf6e355af8.4303753515137954382186.jpg',0),(2,'tedi@abv.bg','7110eda4d09e062aa5e4a390b0a572ac0d2c0220','0895020304','data:image/jpeg;base64,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:image/jpeg;base64,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:image/jpeg;base64,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data:image/jpeg;base64,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:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMTEhUTExMVFRUWFxUYFxgVFRcVFRcXFRUWFhUVFxUYHSB1hrP1ErxcsEtLJCVZhggVy5ZGYI3',1),(3,'tea@gmail.com','sad12e773a05c0ed0176787a4f1574ff0075f7521e','0885602435','https://d3i6fh83elv35t.cloudfront.net/static/2018/11/fat-bears_GettyImages-966223700-1200x800.jpg',0),(4,'billa@abv.bg','bfad027f55b09a81e0159d5552aa67239f27e286','0885200200','https://pngimage.net/wp-content/uploads/2018/05/billa-logo-png-3.png',1),(5,'microsoft@gmail.com','924200b9e598da36f75170e8c21bdee78b3d4ae9','0886512345','https://cdn.vox-cdn.com/thumbor/NeSo4JAqv-fFJCIhb5K5eBqvXG4=/7x0:633x417/1200x800/filters:focal(7x0:633x417)/cdn.vox-cdn.com/assets/1311169/mslogo.jpg',1),(6,'chavo@abv.bg','b1b3773a05c0ed0176787a4f1574ff0075f7521e','0875263149','https://i.pinimg.com/originals/95/5a/0a/955a0a92aa01cb9cdec6c6a222eca1da.jpg',0),(7,'mtel@abv.bg','22trybe5d64b0e216796e834f52d61fd0b70332fc','0887985620','https://www.a1.bg/mtel-stava-A1/web/images/layout/frames/frame-2-1.jpg',1),(29,'mitko@abv.bg','922200b9e598da36f75170e8c21bdee78b3d4ae9','0878989897','https://natureconservancy-h.assetsadobe.com/is/image/content/dam/tnc/nature/en/photos/tnc_36722630_Full.jpg?crop=0,0,6549,4912&wid=580&hei=435&scl=11.291954022988506',0),(30,'venci@abv.bg','71417b376b21e69bdddb63be60681c6c3ef8d630','0875293819','https://natureconservancy-h.assetsadobe.com/is/image/content/dam/tnc/nature/en/photos/tnc_36722630_Full.jpg?crop=0,0,6549,4912&wid=580&hei=435&scl=11.291954022988506',1),(31,'gerka@abv.bg','12121249e598da36f75170e8c21bdee78b3d4ae9','0897203069','https://www.hellomagazine.com/imagenes/brides/2018051548607/geri-halliwell-wedding-throwback-photo/0-240-660/geri-halliwell-t.jpg',0),(32,'kaufland@abv.bg','b612bf0d3652247cbb3e283b59a6abe4c90aa562','0878642513','http://kurabiinica.bg/wp-content/uploads/2017/03/Kaufland.jpg',0),(33,'java@abv.bg','554b70543d0cb28bfe02852aa89a490915e1a860','0878642575','https://cdn.vox-cdn.com/thumbor/FDD76YJZJFPyNUfT3ZBHcnMA0Ec=/43x0:593x367/1200x800/filters:focal(43x0:593x367)/cdn.vox-cdn.com/uploads/chorus_image/image/48667835/dbgxt2rvpd26udoyzcqn.0.0.jpg',0),(34,'violina@abv.bg','61117b376b21e69bdddb63he60612131c3ef8d630','0894502547','https://www.polidesign.net/sites/default/files/corsi/corsi_1.png',0),(35,'georgi@abv.bg','2b165b565499cb1b9d5a20ea3ebef723a1808884','0875456554','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQlTFM3yg_47oLYVSwuQQ9cSWb6XLZ1-xCDiOz0mMyUdHorNOXq',0),(36,'ikea@abv.bg','e65267498588d07c7d5c9aace5aa6b4fe2f04d1a','0878654870','data:image/png;base64,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:image/png;base64,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',0),(37,'pesho@abv.bg','554b70543d0cb28bfe02852aa89a490915e1a860','0875456550','https://1.bp.blogspot.com/-wBDaQp0Pop0/VuE8RqomvWI/AAAAAAAAAJA/sz6jSvGnTUY/s1600/redbooken250.jpg',0),(38,'marlboro@abv.bg','224b702131d0cb28bfe02852aa89a490915e1a860','0878004870','https://images.saucey.com/84441d70-2b10-4839-b89e-9bc23e4abb25.tfss-17382e5b-aec3-4580-b0ee-f213c6d907c3-TB-MARL-REG.jpg',0),(39,'milen@abv.bg','b612bf0d3652247cbb3e283b59a6abe4c90aa562','0875541550','https://images.saucey.com/84441d70-2b10-4839-b89e-9bc23e4abb25.tfss-17382e5b-aec3-4580-b0ee-f213c6d907c3-TB-MARL-REG.jpg',0),(40,'fifa@abv.bg','122bf0d3652247cbb3e283b59a6abe4c90aa562465','0895450852','https://cdn.ghanasoccernet.com/2018/06/FIFA.jpg',0),(41,'stavalistringa@abv.bg','6367c48dd193d56ea7b0baad25b19455e529f5ee','0895050090','',1),(43,'pes2019a@abv.bg','22trybe5d64b0e216796e834f52d61fd0b70332fc','0895450852','https://cdn.ghanasoccernet.com/2018/06/FIFA.jpg',0),(48,'pes2019@abv.bg','20eabe5d64b0e216796e834f52d61fd0b70332fc','0895450852','https://cdn.ghanasoccernet.com/2018/06/FIFA.jpg',0),(59,'asdasdasd@abv.bg','3012be5d64b0e216796e834f52d61fd0b70332fc','0895450832','',1),(61,'barsa_messi@abv.bg','55ccc31f9728ca5605053e531ec45e2cb8a306ac','0895050081','https://e00-marca.uecdn.es/assets/multimedia/imagenes/2018/02/25/15195623621345.jpg',0),(63,'lgtv@abv.bg','0d7ae190d0f294752674a6069ee39384cdd35c65','0895450832','https://visihow.com/images/a/a8/Control_an_LG_TV_with_Your_Smartphone_59626.jpg',0),(65,'samsung@abv.bg','85e71cb1dc91e6ca6da41f968bf1271fe87e088f','0895450832','https://www.boostmobile.com/content/dam/boostmobile/en/products/phones/samsung/galaxy-s8-plus/black/device-front.png.transform/pdpCarousel/image.jpg',0),(66,'bmw@abv.bg','667dd592239117ff273b27cf7dcc3a5164719167','0895450856','https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/BMW.svg/1200px-BMW.svg.png',0),(69,'razer@abv.bg','f2aff59b36ce03148c70bc70970330fe48adf2f4','0895450832','https://pbs.twimg.com/profile_images/1096424346917122048/l0ycED5x_400x400.png',0),(71,'lacalut@abv.bg','fcd5bf5a15aab8d025e1ed027d06abb365952b7f','0895450832','https://www.pharmeden.co.uk/images/lacalut-aktiv-toothpaste-50ml-p4916-6619_image.jpg',0),(72,'logitech@abv.bg','18df4b14d2aa178b91444c9e4a6cd3c1f18bd728','0895450832','https://images-na.ssl-images-amazon.com/images/I/81QIMJAup%2BL._SY450_.jpg',0),(73,'chacho@abv.bg','5b2062bccbffe0e2d97e26a9f17c496957304f8c','0879883333','https://cdn1.wine-searcher.net/images/labels/59/96/chacho-aguardiente-en-fuego-usa-10825996.jpg',0),(74,'golf@abv.bg','beb0e378db82195e2ad581404ad7f8224c9b921b','0895450832','https://www.revell.de/out/pictures/master/product/1/05694_smpw_vw_golf_gti_pirelli_1983.jpg',0);
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
INSERT INTO `users` VALUES (1,'Nikolay','Galchev',1),(2,'Teodora','Topuzova',0),(3,'Tea','Mea',0),(6,'Chavdar','Tonchev',1),(29,'Dimitur','Galchev',0),(30,'Vencislav','Vurbanov',0),(31,'Gergana','Aikova',0),(34,'Violina','Zasheva',0),(35,'Georgi','Georgiev',0),(37,'Petur','Ivanov',0),(39,'Milen','Vulchev',0),(41,'Lubomir','Boqnov',0),(61,'Chavdar','Tonchev',0),(73,'Chavdar','Tonchev',0);
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

-- Dump completed on 2019-03-10 13:35:24
