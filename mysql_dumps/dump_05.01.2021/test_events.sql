-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_bk` int DEFAULT NULL,
  `league_id` int DEFAULT NULL,
  `id_team_first` int DEFAULT NULL,
  `id_team_second` int DEFAULT NULL,
  `win_first` double DEFAULT NULL,
  `draw` double DEFAULT NULL,
  `win_second` double DEFAULT NULL,
  `win_first_or_draw` double DEFAULT NULL,
  `win_first_or_second` double DEFAULT NULL,
  `win_second_or_draw` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_bk_idx` (`id_bk`),
  KEY `league_id_idx` (`league_id`),
  KEY `first_team_id_idx` (`id_team_first`),
  KEY `second_team_id_idx` (`id_team_second`),
  CONSTRAINT `first_team_id` FOREIGN KEY (`id_team_first`) REFERENCES `teams` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `id_bk` FOREIGN KEY (`id_bk`) REFERENCES `bookmakers` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `league_id` FOREIGN KEY (`league_id`) REFERENCES `leagues` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `second_team_id` FOREIGN KEY (`id_team_second`) REFERENCES `teams` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=167 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-05 18:03:14
