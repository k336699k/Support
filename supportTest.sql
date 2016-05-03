-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.22-log - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры для таблица support_test.customers
CREATE TABLE IF NOT EXISTS `customers` (
  `custmer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `phone_model` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`custmer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица support_test.inquiries
CREATE TABLE IF NOT EXISTS `inquiries` (
  `inquiry_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `rezalt` int(11) DEFAULT NULL,
  `customer_id` bigint(20) NOT NULL,
  `topic_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`inquiry_id`),
  KEY `FK_nj2h7u2fe4y9d3pesxh4oryad` (`customer_id`),
  KEY `FK_8wlgg5dagl0j323os3uuv38ys` (`topic_id`),
  CONSTRAINT `FK_8wlgg5dagl0j323os3uuv38ys` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`),
  CONSTRAINT `FK_nj2h7u2fe4y9d3pesxh4oryad` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`custmer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица support_test.topics
CREATE TABLE IF NOT EXISTS `topics` (
  `topic_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.


-- Дамп структуры для таблица support_test.users
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Экспортируемые данные не выделены.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
