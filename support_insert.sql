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
-- Дамп данных таблицы support.customers: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` (`custmer_id`, `name`, `phone`, `phone_model`) VALUES
	(1, 'Dima', '+375296473542', 'sdf'),
	(2, 'fgg', 'gh', 'hhj');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;

-- Дамп данных таблицы support.inquiries: ~9 rows (приблизительно)
/*!40000 ALTER TABLE `inquiries` DISABLE KEYS */;
INSERT INTO `inquiries` (`inquiry_id`, `create_date`, `description`, `rezalt`, `customer_id`, `topic_id`) VALUES
	(1, '2016-05-01 22:43:55', '10', 1, 1, 1),
	(2, '2016-05-01 22:44:13', '1', 1, 2, 1),
	(3, '2016-05-01 00:00:00', 'ываыва34345авпвп', 0, 2, 2),
	(4, '2016-05-01 00:00:00', 'ываыва34345авпвп', 0, 2, 2),
	(5, '2016-05-01 00:00:00', 'ываыва34345авпвп', 0, 2, 2),
	(6, '2016-05-01 00:00:00', 'ываыва34345авпвп', 0, 2, 2),
	(7, '2016-05-01 00:00:00', 'ываыва34345авпвп', 0, 2, 2),
	(8, '2016-05-01 00:00:00', 'ываыва34345авпвп', 0, 2, 2),
	(9, '2016-05-01 00:00:00', 'ываыва34345авпвп', 1, 2, 2);
/*!40000 ALTER TABLE `inquiries` ENABLE KEYS */;

-- Дамп данных таблицы support.topics: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `topics` DISABLE KEYS */;
INSERT INTO `topics` (`topic_id`, `name`) VALUES
	(1, 'dfg'),
	(2, 'aaaa');
/*!40000 ALTER TABLE `topics` ENABLE KEYS */;

-- Дамп данных таблицы support.users: ~1 rows (приблизительно)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`user_id`, `email`, `name`, `password`, `role`, `status`, `username`) VALUES
	(1, 'fgg@mail.ru', '111', '123456', 'ROLE_ADMIN', 'OPENED', 'aaa');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
