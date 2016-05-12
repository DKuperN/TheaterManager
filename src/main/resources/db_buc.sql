-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.28-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for theatredb
CREATE DATABASE IF NOT EXISTS `theatredb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `theatredb`;


-- Dumping structure for table theatredb.booktickets
CREATE TABLE IF NOT EXISTS `booktickets` (
  `ticketID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL DEFAULT '0',
  `eventID` int(11) NOT NULL DEFAULT '0',
  `placeNumber` int(11) NOT NULL DEFAULT '0',
  `resultPrice` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`ticketID`),
  KEY `FK_bookTickets_users` (`userID`),
  KEY `FK_bookTickets_event` (`eventID`),
  CONSTRAINT `FK_bookTickets_event` FOREIGN KEY (`eventID`) REFERENCES `event` (`eventID`),
  CONSTRAINT `FK_bookTickets_users` FOREIGN KEY (`userID`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table theatredb.event
CREATE TABLE IF NOT EXISTS `event` (
  `eventID` int(11) NOT NULL AUTO_INCREMENT,
  `eventName` varchar(50) NOT NULL DEFAULT '0',
  `eventPlace` varchar(50) NOT NULL DEFAULT '0',
  `eventDate` date NOT NULL,
  `eventStartTime` time NOT NULL DEFAULT '00:00:00',
  `eventEndTime` time NOT NULL DEFAULT '00:00:00',
  `priceForTicket` double NOT NULL DEFAULT '0',
  `rating` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`eventID`),
  KEY `rating` (`rating`),
  CONSTRAINT `FK_event_event_rating` FOREIGN KEY (`rating`) REFERENCES `event_rating` (`ratID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table theatredb.event_rating
CREATE TABLE IF NOT EXISTS `event_rating` (
  `ratID` int(11) NOT NULL AUTO_INCREMENT,
  `rating` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ratID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table theatredb.users
CREATE TABLE IF NOT EXISTS `users` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL DEFAULT '0',
  `userEmail` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
