-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.17-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema papfood
--

CREATE DATABASE IF NOT EXISTS papfood;
USE papfood;

--
-- Definition of table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE `commande` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `id_customer` int(10) unsigned DEFAULT NULL,
  `date_ordering` datetime DEFAULT NULL,
  `date_delivered` datetime DEFAULT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `FK_commande_1` (`id_customer`),
  CONSTRAINT `FK_commande_1` FOREIGN KEY (`id_customer`) REFERENCES `person` (`Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `commande`
--

/*!40000 ALTER TABLE `commande` DISABLE KEYS */;
/*!40000 ALTER TABLE `commande` ENABLE KEYS */;


--
-- Definition of table `commande_product`
--

DROP TABLE IF EXISTS `commande_product`;
CREATE TABLE `commande_product` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_commande` int(10) unsigned DEFAULT NULL,
  `id_product` int(10) unsigned DEFAULT NULL,
  `quantity` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_order_product_2` (`id_product`),
  KEY `FK_order_product_1` (`id_commande`) USING BTREE,
  CONSTRAINT `FK_order_product_1` FOREIGN KEY (`Id`) REFERENCES `commande` (`Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_order_product_2` FOREIGN KEY (`id_product`) REFERENCES `product` (`Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `commande_product`
--

/*!40000 ALTER TABLE `commande_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `commande_product` ENABLE KEYS */;


--
-- Definition of table `commande_service`
--

DROP TABLE IF EXISTS `commande_service`;
CREATE TABLE `commande_service` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_commande` int(10) unsigned DEFAULT NULL,
  `id_service` int(10) unsigned DEFAULT NULL,
  `quantity` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_commande_service_1` (`id_commande`),
  KEY `FK_commande_service_2` (`id_service`),
  CONSTRAINT `FK_commande_service_1` FOREIGN KEY (`id_commande`) REFERENCES `commande` (`Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_commande_service_2` FOREIGN KEY (`id_service`) REFERENCES `service` (`Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `commande_service`
--

/*!40000 ALTER TABLE `commande_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `commande_service` ENABLE KEYS */;


--
-- Definition of table `comments`
--

DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_author` int(10) unsigned DEFAULT NULL,
  `date_posted` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `content` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comments`
--

/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;


--
-- Definition of table `comments_product`
--

DROP TABLE IF EXISTS `comments_product`;
CREATE TABLE `comments_product` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_comment` int(10) unsigned DEFAULT NULL,
  `id_product` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comments_product`
--

/*!40000 ALTER TABLE `comments_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments_product` ENABLE KEYS */;


--
-- Definition of table `comments_service`
--

DROP TABLE IF EXISTS `comments_service`;
CREATE TABLE `comments_service` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_comment` int(10) unsigned DEFAULT NULL,
  `id_service` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comments_service`
--

/*!40000 ALTER TABLE `comments_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments_service` ENABLE KEYS */;


--
-- Definition of table `connection`
--

DROP TABLE IF EXISTS `connection`;
CREATE TABLE `connection` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `login_time` datetime DEFAULT NULL,
  `person_id` int(10) unsigned DEFAULT NULL,
  `person_id_ip_address` varchar(45) DEFAULT NULL,
  `person_type` varchar(45) DEFAULT NULL,
  `logout_time` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_connexion_1` (`person_id`) USING BTREE,
  CONSTRAINT `FK_connexion_1` FOREIGN KEY (`person_id`) REFERENCES `person` (`Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `connection`
--

/*!40000 ALTER TABLE `connection` DISABLE KEYS */;
/*!40000 ALTER TABLE `connection` ENABLE KEYS */;


--
-- Definition of table `evaluation`
--

DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE `evaluation` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_person` int(10) unsigned DEFAULT NULL,
  `id_jury` int(10) unsigned DEFAULT NULL,
  `note` double DEFAULT NULL,
  `comments` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_evaluation_2` (`id_person`),
  CONSTRAINT `FK_evaluation_1` FOREIGN KEY (`id_person`) REFERENCES `person` (`Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_evaluation_2` FOREIGN KEY (`id_person`) REFERENCES `person` (`Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `evaluation`
--

/*!40000 ALTER TABLE `evaluation` DISABLE KEYS */;
/*!40000 ALTER TABLE `evaluation` ENABLE KEYS */;


--
-- Definition of table `images_links_person`
--

DROP TABLE IF EXISTS `images_links_person`;
CREATE TABLE `images_links_person` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `link` varchar(100) NOT NULL,
  `id_person` int(10) unsigned NOT NULL,
  `utility` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_images_links_person_1` (`id_person`),
  CONSTRAINT `FK_images_links_person_1` FOREIGN KEY (`id_person`) REFERENCES `person` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `images_links_person`
--

/*!40000 ALTER TABLE `images_links_person` DISABLE KEYS */;
/*!40000 ALTER TABLE `images_links_person` ENABLE KEYS */;


--
-- Definition of table `images_links_product`
--

DROP TABLE IF EXISTS `images_links_product`;
CREATE TABLE `images_links_product` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `link` varchar(100) NOT NULL,
  `id_product` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_images_links_product_1` (`id_product`),
  CONSTRAINT `FK_images_links_product_1` FOREIGN KEY (`id_product`) REFERENCES `product` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `images_links_product`
--

/*!40000 ALTER TABLE `images_links_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `images_links_product` ENABLE KEYS */;


--
-- Definition of table `images_links_service`
--

DROP TABLE IF EXISTS `images_links_service`;
CREATE TABLE `images_links_service` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `link` varchar(100) NOT NULL,
  `id_service` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_images_links_service_1` (`id_service`),
  CONSTRAINT `FK_images_links_service_1` FOREIGN KEY (`id_service`) REFERENCES `service` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `images_links_service`
--

/*!40000 ALTER TABLE `images_links_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `images_links_service` ENABLE KEYS */;


--
-- Definition of table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code_invoice` varchar(45) DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  `delivered_date` datetime DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `header_message` varchar(45) DEFAULT NULL,
  `footer_message` varchar(45) DEFAULT NULL,
  `legal_message` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `invoice`
--

/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;


--
-- Definition of table `invoice_person`
--

DROP TABLE IF EXISTS `invoice_person`;
CREATE TABLE `invoice_person` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_invoice` int(10) unsigned DEFAULT NULL,
  `id_person` int(10) unsigned DEFAULT NULL,
  `person_name` varchar(45) DEFAULT NULL,
  `person_address` varchar(45) DEFAULT NULL,
  `person_surname` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_invoice_person_1` (`id_invoice`),
  KEY `FK_invoice_person_2` (`id_person`),
  CONSTRAINT `FK_invoice_person_1` FOREIGN KEY (`id_invoice`) REFERENCES `invoice` (`Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_invoice_person_2` FOREIGN KEY (`id_person`) REFERENCES `person` (`Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `invoice_person`
--

/*!40000 ALTER TABLE `invoice_person` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice_person` ENABLE KEYS */;


--
-- Definition of table `invoice_product`
--

DROP TABLE IF EXISTS `invoice_product`;
CREATE TABLE `invoice_product` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_invoice` int(10) unsigned DEFAULT NULL,
  `id_product` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_invoice_product_1` (`id_invoice`),
  KEY `FK_invoice_product_2` (`id_product`),
  CONSTRAINT `FK_invoice_product_1` FOREIGN KEY (`id_invoice`) REFERENCES `invoice` (`Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_invoice_product_2` FOREIGN KEY (`id_product`) REFERENCES `product` (`Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `invoice_product`
--

/*!40000 ALTER TABLE `invoice_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice_product` ENABLE KEYS */;


--
-- Definition of table `invoice_service`
--

DROP TABLE IF EXISTS `invoice_service`;
CREATE TABLE `invoice_service` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_invoice` int(10) unsigned DEFAULT NULL,
  `id_service` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_invoice_service_1` (`id_invoice`),
  KEY `FK_invoice_service_2` (`id_service`),
  CONSTRAINT `FK_invoice_service_1` FOREIGN KEY (`id_invoice`) REFERENCES `invoice` (`Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_invoice_service_2` FOREIGN KEY (`id_service`) REFERENCES `service` (`Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `invoice_service`
--

/*!40000 ALTER TABLE `invoice_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice_service` ENABLE KEYS */;


--
-- Definition of table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_sender` int(10) unsigned DEFAULT NULL,
  `id_receiver` int(10) unsigned DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `sent_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `receive_date` datetime DEFAULT NULL,
  `read_date` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `message`
--

/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` (`Id`,`id_sender`,`id_receiver`,`content`,`sent_date`,`receive_date`,`read_date`) VALUES 
 (1,1,1,NULL,'2017-04-16 17:30:10',NULL,NULL);
/*!40000 ALTER TABLE `message` ENABLE KEYS */;


--
-- Definition of table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `second_name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `second_surname` varchar(45) DEFAULT NULL,
  `profession` varchar(45) DEFAULT NULL,
  `date_inscription` datetime DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `tel_number` varchar(45) DEFAULT NULL,
  `facebook_id` varchar(45) DEFAULT NULL,
  `twitter_id` varchar(45) DEFAULT NULL,
  `instagram_id` varchar(45) DEFAULT NULL,
  `linkedin_id` varchar(45) DEFAULT NULL,
  `account_picture` varchar(100) DEFAULT NULL,
  `street_name` varchar(45) DEFAULT NULL,
  `city_name` varchar(45) DEFAULT NULL,
  `country_name` varchar(45) DEFAULT NULL,
  `postal_code` varchar(45) DEFAULT NULL,
  `last_connection` int(10) unsigned DEFAULT NULL,
  `function` varchar(45) DEFAULT NULL,
  `street_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_person_1` (`last_connection`),
  CONSTRAINT `FK_person_1` FOREIGN KEY (`last_connection`) REFERENCES `connection` (`Id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `person`
--

/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`Id`,`name`,`second_name`,`surname`,`second_surname`,`profession`,`date_inscription`,`password`,`email`,`phone_number`,`tel_number`,`facebook_id`,`twitter_id`,`instagram_id`,`linkedin_id`,`account_picture`,`street_name`,`city_name`,`country_name`,`postal_code`,`last_connection`,`function`,`street_number`) VALUES 
 (4,'Ferchichi','','Youssef','','Etudiant','2017-05-11 14:46:15','Mv/kkZ9zfH5PPpGBcXCexCRn+MFgD1E9ZvwDpEqi5B9mYjQbb6S/CQ==','youssfi@youssfi.com','0645987410','','','','','',NULL,'Routes des dolines','Tunis','Tunisie','06560',NULL,'pro','24'),
 (5,'Chahi',NULL,NULL,NULL,NULL,'2017-05-11 14:51:25','EF/bVWAcovZ7ZowTjXZAoCTD6Adfxz7UGxqyf4a+BsbYD5MzwztviA==','chahi@chahi.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Perculiar',NULL),
 (6,'Test',NULL,NULL,NULL,NULL,'2017-05-11 14:52:35','JSGbupuA0QCK+LHnGnk0TNKRkImGdK9mkb5s8oSn7ygb5ZT3wQkWNA==','test@test.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Perculiar',NULL),
 (7,'Moi',NULL,NULL,NULL,NULL,'2017-05-11 14:54:33','lBBemA30KHx4FjfKqCUmKxiNdV7G74zPNmpynJdGGwn+xYtsH0Zn7w==','moi@moi.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Perculiar',NULL),
 (8,'Maf',NULL,NULL,NULL,NULL,'2017-05-11 15:02:13','36Vj1g1hM0Wf/DT3Ai2M+shexh13J/OFbt3tU243VibMfJzo5QAQUw==','maf@maf.com',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'Perculiar',NULL);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;


--
-- Definition of table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `id_provider` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_product_1` (`id_provider`),
  CONSTRAINT `FK_product_1` FOREIGN KEY (`id_provider`) REFERENCES `person` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`Id`,`code`,`name`,`description`,`price`,`id_provider`) VALUES 
 (1,'11/05/2017pro4','Ratatouille','Ratatouille méditérannéenne',10,4);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;


--
-- Definition of table `product_component`
--

DROP TABLE IF EXISTS `product_component`;
CREATE TABLE `product_component` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_componed` int(10) unsigned DEFAULT NULL,
  `id_component` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_product_component_1` (`id_componed`),
  KEY `FK_product_component_2` (`id_component`),
  CONSTRAINT `FK_product_component_1` FOREIGN KEY (`id_componed`) REFERENCES `product` (`Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_product_component_2` FOREIGN KEY (`id_component`) REFERENCES `product` (`Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_component`
--

/*!40000 ALTER TABLE `product_component` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_component` ENABLE KEYS */;


--
-- Definition of table `product_service`
--

DROP TABLE IF EXISTS `product_service`;
CREATE TABLE `product_service` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_product` int(10) unsigned DEFAULT NULL,
  `id_service` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_product_service_1` (`id_product`),
  KEY `FK_product_service_2` (`id_service`),
  CONSTRAINT `FK_product_service_1` FOREIGN KEY (`id_product`) REFERENCES `product` (`Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_product_service_2` FOREIGN KEY (`id_service`) REFERENCES `service` (`Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_service`
--

/*!40000 ALTER TABLE `product_service` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_service` ENABLE KEYS */;


--
-- Definition of table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `id_provider` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_service_1` (`id_provider`),
  CONSTRAINT `FK_service_1` FOREIGN KEY (`id_provider`) REFERENCES `person` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `service`
--

/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` (`Id`,`code`,`name`,`description`,`price`,`id_provider`) VALUES 
 (1,'11/05/2017ser4','Cuisine Japonaise','Je vous propose de faire de la cuisine japonaise pour 100 euros. En bref, chef privé!',100,4),
 (2,'13/05/2017ser4','Cuisine indienne','Je vous fais à manger indien pour rien du tout!',75,4);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;


--
-- Definition of table `service_component`
--

DROP TABLE IF EXISTS `service_component`;
CREATE TABLE `service_component` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_service` int(10) unsigned DEFAULT NULL,
  `id_component` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_service_component_1` (`id_service`),
  KEY `FK_service_component_2` (`id_component`),
  CONSTRAINT `FK_service_component_1` FOREIGN KEY (`id_service`) REFERENCES `service` (`Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_service_component_2` FOREIGN KEY (`id_component`) REFERENCES `service` (`Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `service_component`
--

/*!40000 ALTER TABLE `service_component` DISABLE KEYS */;
/*!40000 ALTER TABLE `service_component` ENABLE KEYS */;


--
-- Definition of table `service_product`
--

DROP TABLE IF EXISTS `service_product`;
CREATE TABLE `service_product` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_service` int(10) unsigned DEFAULT NULL,
  `id_product` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_service_product_1` (`id_service`),
  KEY `FK_service_product_2` (`id_product`),
  CONSTRAINT `FK_service_product_1` FOREIGN KEY (`id_service`) REFERENCES `service` (`Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_service_product_2` FOREIGN KEY (`id_product`) REFERENCES `product` (`Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `service_product`
--

/*!40000 ALTER TABLE `service_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `service_product` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
