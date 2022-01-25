-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 01, 2021 at 09:44 PM
-- Server version: 5.6.14
-- PHP Version: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `airline`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE IF NOT EXISTS `booking` (
  `User_idUser` int(11) NOT NULL,
  `Flight_idFlight` int(11) NOT NULL,
  `Payment_idPayment` int(11) NOT NULL,
  `bookTime` datetime NOT NULL,
  `seats` int(11) DEFAULT NULL,
  PRIMARY KEY (`User_idUser`,`Flight_idFlight`,`Payment_idPayment`),
  KEY `fk_Booking_Flight1_idx` (`Flight_idFlight`),
  KEY `fk_Booking_Payment1_idx` (`Payment_idPayment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`User_idUser`, `Flight_idFlight`, `Payment_idPayment`, `bookTime`, `seats`) VALUES
(1, 1, 1, '2021-12-02 13:29:06', 2),
(1, 1, 4, '2021-12-01 20:00:03', 0),
(1, 1, 5, '2021-12-01 21:18:27', 1),
(1, 1, 6, '2021-12-01 21:20:37', 1),
(1, 3, 7, '2021-12-01 22:09:36', 1),
(5, 1, 8, '2021-12-01 22:32:08', 1);

-- --------------------------------------------------------

--
-- Table structure for table `flight`
--

CREATE TABLE IF NOT EXISTS `flight` (
  `idFlight` int(11) NOT NULL AUTO_INCREMENT,
  `destination` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `departTime` datetime DEFAULT NULL,
  `duration` time DEFAULT NULL,
  `availSeats` int(11) DEFAULT '40',
  `departLoc` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idFlight`),
  UNIQUE KEY `idFlight_UNIQUE` (`idFlight`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `flight`
--

INSERT INTO `flight` (`idFlight`, `destination`, `price`, `departTime`, `duration`, `availSeats`, `departLoc`) VALUES
(1, 'Cape Town', 1200, '2022-01-13 05:34:21', '02:45:00', 35, 'Johannesburg'),
(2, 'Joburg', 2000, '2021-12-31 05:00:00', '02:15:00', 40, 'Durban'),
(3, 'Joburg', 2000, '2021-12-31 05:00:00', '02:15:00', 39, 'Durban'),
(4, 'Cape Town', 1200, '2021-12-17 06:00:00', '02:45:00', 40, 'Johannesburg'),
(5, 'Cape Town', 1200, '2021-12-17 06:00:00', '02:45:00', 40, 'Johannesburg'),
(6, 'Cape Town', 1200, '2021-12-17 06:00:00', '02:45:00', 40, 'Johannesburg'),
(7, 'Cape Town', 1200, '2022-01-13 05:34:21', '02:45:00', 40, 'Johannesburg'),
(8, 'Cape Town', 1200, '2022-01-13 08:15:10', '02:45:00', 40, 'Johannesburg'),
(9, 'Cape Town', 1200, '2022-01-13 05:34:21', '02:45:00', 40, 'Johannesburg'),
(10, 'Cape Town', 1200, '2022-01-13 05:34:21', '02:45:00', 40, 'Johannesburg'),
(11, 'Cape Town', 1200, '2022-01-13 05:34:21', '02:45:00', 40, 'Johannesburg'),
(12, 'Cape Town', 1200, '2021-12-17 06:00:00', '02:45:00', 40, 'Johannesburg'),
(13, 'Cape Town', 1200, '2022-01-13 05:34:21', '02:45:00', 40, 'Johannesburg'),
(14, 'Cape Town', 1200, '2021-12-17 06:00:00', '02:45:00', 40, 'Johannesburg'),
(15, 'Cape Town', 1200, '2022-01-13 05:34:21', '02:45:00', 40, 'Johannesburg'),
(16, 'Cape Town', 1200, '2022-01-13 08:15:10', '02:45:00', 40, 'Johannesburg'),
(17, 'Cape Town', 1200, '2022-01-13 05:34:21', '02:45:00', 40, 'Johannesburg'),
(18, 'Cape Town', 1200, '2022-01-13 05:34:21', '02:45:00', 40, 'Johannesburg'),
(19, 'Cape Town', 1200, '2022-01-13 05:34:21', '02:45:00', 40, 'Johannesburg'),
(20, 'Cape Town', 1200, '2022-01-13 05:34:21', '02:45:00', 40, 'Johannesburg'),
(21, 'Cape Town', 1200, '2022-01-13 05:34:21', '02:45:00', 0, 'Johannesburg');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `idPayment` int(11) NOT NULL AUTO_INCREMENT,
  `amt` double NOT NULL,
  `paid` set('y','n') NOT NULL DEFAULT 'n',
  PRIMARY KEY (`idPayment`),
  UNIQUE KEY `idPayment_UNIQUE` (`idPayment`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`idPayment`, `amt`, `paid`) VALUES
(1, 2400, 'n'),
(4, 3600, 'n'),
(5, 3600, 'n'),
(6, 1200, 'n'),
(7, 2000, 'n'),
(8, 1200, 'n');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `Name` varchar(45) DEFAULT NULL,
  `Surname` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `Balance` double DEFAULT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `idUser_UNIQUE` (`idUser`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `password_UNIQUE` (`password`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`idUser`, `username`, `password`, `Name`, `Surname`, `email`, `DOB`, `Balance`) VALUES
(1, 'laoshu', 'odilestanley', 'Trent', 'Stanley', 'trentstanley0@gmail.com', '2000-06-14', 10000),
(5, 'OBS', 'ethan', 'Odile', 'Stanley', 'odilestanley0@gmail.com', NULL, 12880);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `fk_Booking_Flight1` FOREIGN KEY (`Flight_idFlight`) REFERENCES `flight` (`idFlight`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Booking_Payment1` FOREIGN KEY (`Payment_idPayment`) REFERENCES `payment` (`idPayment`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Booking_User` FOREIGN KEY (`User_idUser`) REFERENCES `user` (`idUser`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
