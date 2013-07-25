-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: localhost:1234
-- Generation Time: Jul 17, 2013 at 11:42 AM
-- Server version: 5.5.32
-- PHP Version: 5.4.17

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `cars`
--
CREATE DATABASE IF NOT EXISTS `cars` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `cars`;

-- --------------------------------------------------------

--
-- Table structure for table `body style`
--

CREATE TABLE IF NOT EXISTS `body style` (
  `ID` int(11) NOT NULL,
  `String` varchar(15) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `body style`
--

INSERT INTO `body style` (`ID`, `String`) VALUES
(0, 'Other'),
(1, 'Sedan'),
(2, 'Coupe'),
(3, 'Wagon'),
(4, 'SUV'),
(5, 'Hatchback'),
(6, 'Convertible'),
(7, 'Minivan'),
(8, 'Pickup'),
(9, 'Roadster'),
(10, 'Sport Utility'),
(11, '');

-- --------------------------------------------------------

--
-- Table structure for table `cars`
--

CREATE TABLE IF NOT EXISTS `cars` (
  `id` int(11) NOT NULL,
  `Classification` varchar(256) DEFAULT NULL,
  `Engine` varchar(256) DEFAULT NULL,
  `Fuel` int(11) DEFAULT NULL,
  `BodyStyle` int(11) DEFAULT NULL,
  `Transmission` int(11) DEFAULT NULL,
  `DriveTrain` int(11) DEFAULT NULL,
  `Seating` int(11) DEFAULT NULL,
  `MPGCity` int(11) DEFAULT NULL,
  `MPGHighway` int(11) DEFAULT NULL,
  `MSRP` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cars`
--

INSERT INTO `cars` (`id`, `Classification`, `Engine`, `Fuel`, `BodyStyle`, `Transmission`, `DriveTrain`, `Seating`, `MPGCity`, `MPGHighway`, `MSRP`) VALUES
(1, 'Acura | ILX | 2014 | 2.0L', '4-Cyl, VTEC, 2.0 Liter', 1, 1, 1, 1, 5, 24, 35, 27795),
(2, 'Acura | ILX | 2014 | 2.4L', '4-Cyl, VTEC, 2.4 Liter', 1, 1, 2, 1, 5, 22, 31, 30095),
(3, 'Acura | ILX | 2013 | 2.0L', '4-Cyl, VTEC, 2.0 Liter', 1, 1, 1, 1, 5, 24, 35, 26795),
(4, 'Acura | ILX | 2013 | Hybrid', '4-Cyl, Hybrid, VTEC, 1.5 Liter', 3, 1, 1, 1, 5, 39, 38, 29795),
(5, 'Acura | ILX | 2013 | 2.4L', '4-Cyl, VTEC, 2.4 Liter', 1, 1, 2, 1, 5, 22, 31, 30095),
(6, 'Acura | MDX | 2014 | Base', 'V6, i-VTEC, 3.5 Liter', 1, 10, 1, 3, 7, 20, 28, 43185),
(7, 'Acura | MDX | 2013 | Base', 'V6, VTEC, 3.7 Liter', 1, 10, 1, 5, 7, 16, 21, 44175),
(8, 'Acura | MDX | 2012 | Base', 'V6, VTEC, 3.7 Liter', 1, 10, 1, 5, 7, 16, 21, 43925),
(9, 'Acura | RDX | 2014 | Base', 'V6, 3.5 Liter', 1, 10, 1, 1, 5, 20, 28, 35415),
(10, 'Acura | RDX | 2013 | Base', 'V6, 3.5 Liter', 1, 10, 1, 1, 5, 20, 28, 35215),
(11, 'Acura | RDX | 2012 | Base', '4-Cyl, Turbo, 2.3 Liter', 1, 10, 1, 1, 5, 19, 24, 33780),
(12, 'Acura | RDX | 2012 | SH-AWD', '4-Cyl, Turbo, 2.3 Liter', 1, 10, 1, 5, 5, 17, 22, 35780),
(13, 'Acura | RLX | 2014 | Base', 'V6, i-VTEC, 3.5 Liter', 1, 1, 1, 1, 5, 20, 31, 49345),
(14, 'Acura | TL | 2013 | Base', 'V6, VTEC, 3.5 Liter', 1, 1, 1, 1, 5, 20, 29, 36800),
(15, 'Acura | TL | 2013 | Special Edition', 'V6, VTEC, 3.5 Liter', 1, 1, 1, 1, 5, 20, 29, 38300),
(16, 'Acura | TL | 2013 | SH-AWD', 'V6, VTEC, 3.7 Liter', 1, 1, 1, 5, 5, 18, 26, 40350),
(17, 'Acura | TL | 2012 | Base', 'V6, VTEC, 3.5 Liter', 1, 1, 1, 1, 5, 20, 29, 36600),
(18, 'Acura | TL | 2012 | SH-AWD', 'V6, VTEC, 3.7 Liter', 1, 1, 1, 5, 5, 18, 26, 40150),
(19, 'Acura | TSX | 2013 | Sedan | Base', '4-Cyl, VTEC, 2.4 Liter', 1, 1, 1, 1, 5, 22, 31, 31405),
(20, 'Acura | TSX | 2013 | Wagon | Base', '4-Cyl, VTEC, 2.4 Liter', 1, 3, 1, 1, 5, 22, 30, 32755),
(21, 'Acura | TSX | 2012 | Sedan | Base', '4-Cyl, VTEC, 2.4 Liter', 1, 1, 1, 1, 5, 22, 31, 30905),
(22, 'Acura | TSX | 2012 | Wagon | Base', '4-Cyl, VTEC, 2.4 Liter', 1, 3, 1, 1, 5, 22, 30, 32255),
(23, 'Acura | ZDX | 2013 | Base', 'V6, VTEC, 3.7 Liter', 1, 10, 1, 5, 5, 16, 23, 51815),
(24, 'Acura | ZDX | 2012 | Base', 'V6, VTEC, 3.7 Liter', 1, 10, 1, 5, 5, 16, 23, 47015);

-- --------------------------------------------------------

--
-- Table structure for table `drive train`
--

CREATE TABLE IF NOT EXISTS `drive train` (
  `ID` int(1) NOT NULL,
  `String` varchar(3) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `drive train`
--

INSERT INTO `drive train` (`ID`, `String`) VALUES
(1, 'FWD'),
(2, 'RWD'),
(3, 'AWD'),
(4, '4WD');

-- --------------------------------------------------------

--
-- Table structure for table `fuel`
--

CREATE TABLE IF NOT EXISTS `fuel` (
  `ID` int(11) NOT NULL,
  `String` varchar(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `fuel`
--

INSERT INTO `fuel` (`ID`, `String`) VALUES
(1, 'Gas'),
(2, 'Diesel'),
(3, 'Hybrid'),
(4, 'Electric');

-- --------------------------------------------------------

--
-- Table structure for table `transmission`
--

CREATE TABLE IF NOT EXISTS `transmission` (
  `ID` int(11) NOT NULL,
  `String` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `transmission`
--

INSERT INTO `transmission` (`ID`, `String`) VALUES
(1, 'Automatic'),
(2, 'Manual'),
(3, '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
