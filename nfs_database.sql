-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: May 02, 2021 at 07:30 PM
-- Server version: 8.0.18
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nfs_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE IF NOT EXISTS `admin` (
  `AdminID` int(11) NOT NULL,
  `UserName` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `Password` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`AdminID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`AdminID`, `UserName`, `Password`) VALUES
(2, 'Mevan', 'Mevan123'),
(1, 'Pat', 'Pat123');

-- --------------------------------------------------------

--
-- Table structure for table `analytics`
--

DROP TABLE IF EXISTS `analytics`;
CREATE TABLE IF NOT EXISTS `analytics` (
  `BrandID` int(11) NOT NULL,
  `QuestionID` int(11) NOT NULL,
  `Answer1Count` int(11) NOT NULL,
  `Answer2Count` int(11) NOT NULL,
  `Answer3Count` int(11) NOT NULL,
  `Answer4Count` int(11) NOT NULL,
  KEY `QuestionID` (`QuestionID`),
  KEY `BrandID` (`BrandID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `analytics`
--

INSERT INTO `analytics` (`BrandID`, `QuestionID`, `Answer1Count`, `Answer2Count`, `Answer3Count`, `Answer4Count`) VALUES
(1, 1000, 1, 0, 0, 13),
(1, 1001, 0, 1, 0, 21),
(1, 1002, 0, 0, 1, 13),
(1, 1003, 0, 0, 1, 13),
(1, 1004, 0, 0, 0, 13),
(2, 1000, 13, 17, 17, 21),
(2, 1001, 13, 18, 20, 15),
(2, 1002, 15, 1, 0, 7),
(2, 1003, 12, 14, 15, 15),
(2, 1004, 12, 14, 14, 15),
(3, 1000, 17, 0, 0, 17),
(3, 1001, 23, 0, 1, 25),
(3, 1002, 16, 0, 1, 18),
(3, 1003, 0, 0, 1, 20),
(3, 1004, 16, 1, 0, 19),
(4, 1000, 13, 14, 15, 15),
(4, 1001, 24, 22, 23, 24),
(4, 1002, 13, 13, 14, 14),
(4, 1003, 22, 23, 24, 20),
(4, 1004, 22, 13, 16, 15);

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
CREATE TABLE IF NOT EXISTS `brand` (
  `BrandID` int(11) NOT NULL,
  `BrandName` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`BrandID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`BrandID`, `BrandName`) VALUES
(1, 'Apixaban'),
(2, 'Lenalidomide'),
(3, 'Etanercept'),
(4, 'Nexium');

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
CREATE TABLE IF NOT EXISTS `questions` (
  `QuestionID` int(11) NOT NULL,
  `Question` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `Answer1` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `Answer2` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `Answer3` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `Answer4` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`QuestionID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`QuestionID`, `Question`, `Answer1`, `Answer2`, `Answer3`, `Answer4`) VALUES
(1000, 'Comparison between our products and competitors’ products ?', 'Very Good', 'Good', 'Poor', 'Very Poor'),
(1001, 'How do you rate our delivery System ?', 'Very Good', 'Good', 'Poor', 'Very Poor'),
(1002, 'What would you think of the quality of our packaging ?', 'Very Good', 'Good', 'Poor', 'Very Poor'),
(1003, 'Rate your overall satisfaction with the products you used from our Company ?', 'Very Good', 'Good', 'Poor', 'Very Poor'),
(1004, 'How do you rate our Customer Care ?', 'Very Good', 'Good', 'Poor', 'Very Poor');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
