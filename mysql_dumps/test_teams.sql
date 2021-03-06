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
-- Table structure for table `teams`
--

DROP TABLE IF EXISTS `teams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teams` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teams`
--

LOCK TABLES `teams` WRITE;
/*!40000 ALTER TABLE `teams` DISABLE KEYS */;
INSERT INTO `teams` VALUES (1,'Саутгемптон'),(2,'Ливерпуль'),(3,'Шеффилд Юнайтед'),(4,'Ньюкасл Юнайтед'),(5,'Бернли'),(6,'Манчестер Юнайтед'),(7,'Вулверхэмптон Уондерерс'),(8,'Эвертон'),(9,'Манчестер Сити'),(10,'Брайтон энд Хоув Альбион'),(11,'Астон Вилла'),(12,'Тоттенхэм Хотспур'),(13,'Арсенал'),(14,'Кристал Пэлас'),(15,'Фулхэм'),(16,'Челси'),(17,'Вест Бромвич Альбион'),(18,'Вест Хэм Юнайтед'),(19,'Лидс Юнайтед'),(20,'Лестер Сити'),(21,'Валенсия'),(22,'Кадис'),(23,'Атлетик Бильбао'),(24,'Барселона'),(25,'Сельта'),(26,'Вильярреал'),(27,'Севилья'),(28,'Реал Сосьедад'),(29,'Атлетико Мадрид'),(30,'Гранада'),(31,'Осасуна'),(32,'Реал Мадрид'),(33,'Леванте'),(34,'Эйбар'),(35,'Алавес'),(36,'Эльче'),(37,'Хетафе'),(38,'Вальядолид'),(39,'Уэска'),(40,'Бетис'),(41,'Санта-Клара'),(42,'Бенфика'),(43,'Жил Висенте'),(44,'Белененсиш'),(45,'Портимоненше'),(46,'Фаренсе'),(47,'Насьонал Мадейра'),(48,'Спортинг Лиссабон'),(49,'Спортинг Брага'),(50,'Маритиму'),(51,'Тондела'),(52,'Риу Ави'),(53,'Фамаликан'),(54,'Порту'),(55,'Морейренше'),(56,'Витория Гимарайнш'),(57,'Боавишта'),(58,'Пасуш ди Феррейра');
/*!40000 ALTER TABLE `teams` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-04 17:38:13
