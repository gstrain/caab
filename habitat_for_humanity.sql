-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 17, 2017 at 01:39 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `habitat_for_humanity`
--

-- --------------------------------------------------------

--
-- Table structure for table `actor`
--

CREATE TABLE `actor` (
  `actor_id` int(9) NOT NULL,
  `address_id` int(9) NOT NULL,
  `relation_id` int(9) NOT NULL,
  `actor_type` enum('P','O') NOT NULL COMMENT 'P = person, O = Organization'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `actor`
--

INSERT INTO `actor` (`actor_id`, `address_id`, `relation_id`, `actor_type`) VALUES
(1, 2, 1, 'P'),
(2, 3, 2, 'P'),
(3, 2, 3, 'O'),
(4, 3, 4, 'O'),
(5, 1, 5, 'P'),
(6, 3, 2, 'P');

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE `address` (
  `address_id` int(9) NOT NULL,
  `street` varchar(120) DEFAULT NULL,
  `number` varchar(10) DEFAULT NULL,
  `apartment_no` varchar(6) DEFAULT NULL,
  `city` varchar(120) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `zipcode` varchar(9) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`address_id`, `street`, `number`, `apartment_no`, `city`, `state`, `zipcode`) VALUES
(1, 'County rd 1300 N', '1443', NULL, 'roanoke', 'illinois', '61561'),
(2, 'crossings drive', '124', '4', 'normal', 'il', '61760'),
(3, 'candy lane', '12', 'b', 'London', NULL, NULL),
(4, 'empty lot street', '', '', 'hiroshima', NULL, NULL),
(5, 'candy lane', '', '', 'London', '', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

CREATE TABLE `class` (
  `class_id` int(9) NOT NULL,
  `class_name` varchar(120) NOT NULL,
  `class_desc` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`class_id`, `class_name`, `class_desc`) VALUES
(1, 'poor', ''),
(2, 'homeless', ''),
(3, 'middle', ''),
(4, 'single mother', ''),
(5, 'disability', ''),
(6, 'high class', '(very rich)');

-- --------------------------------------------------------

--
-- Table structure for table `construction_status`
--

CREATE TABLE `construction_status` (
  `cstatus_id` int(9) NOT NULL,
  `cstatus` varchar(120) NOT NULL,
  `cstatus_description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `construction_status`
--

INSERT INTO `construction_status` (`cstatus_id`, `cstatus`, `cstatus_description`) VALUES
(1, 'processing', ''),
(2, 'broke ground', ''),
(3, 'under construction', ''),
(4, 'utilities ', 'structure is built, electrical, plumbing, etc is being put in.'),
(5, 'vaccant', 'house is complete, but no one has moved in.'),
(6, 'occupied', 'house is complete, and a family is living inside.');

-- --------------------------------------------------------

--
-- Table structure for table `family`
--

CREATE TABLE `family` (
  `family_id` int(9) NOT NULL,
  `class_id` int(9) NOT NULL,
  `milestone_id` int(9) NOT NULL,
  `equity_hrs` double NOT NULL,
  `income` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `family`
--

INSERT INTO `family` (`family_id`, `class_id`, `milestone_id`, `equity_hrs`, `income`) VALUES
(1, 4, 2, 10, 26000);

-- --------------------------------------------------------

--
-- Table structure for table `house`
--

CREATE TABLE `house` (
  `house_id` int(9) NOT NULL,
  `address_id` int(9) NOT NULL,
  `property_id` int(9) NOT NULL,
  `family_id` int(9) DEFAULT NULL,
  `construction_cost` double NOT NULL,
  `size` int(30) NOT NULL,
  `bedrooms` int(2) NOT NULL,
  `bathrooms` double NOT NULL,
  `cstatus_id` int(9) NOT NULL,
  `house_style` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `house`
--

INSERT INTO `house` (`house_id`, `address_id`, `property_id`, `family_id`, `construction_cost`, `size`, `bedrooms`, `bathrooms`, `cstatus_id`, `house_style`) VALUES
(1, 3, 1, 1, 1200000, 23000, 12, 4, 6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `house_contribution`
--

CREATE TABLE `house_contribution` (
  `contribution_id` int(9) NOT NULL,
  `actor_id` int(9) NOT NULL,
  `house_id` int(9) NOT NULL,
  `involment_desc` varchar(120) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `house_contribution`
--

INSERT INTO `house_contribution` (`contribution_id`, `actor_id`, `house_id`, `involvement_desc`) VALUES
(1, 1, 1, NULL),
(2, 2, 1, NULL),
(3, 2, 2, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `house_style`
--

CREATE TABLE `house_style` (
  `style_id` int(9) NOT NULL,
  `style` varchar(120) NOT NULL,
  `style_desc` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `house_style`
--

INSERT INTO `house_style` (`style_id`, `style`, `style_desc`) VALUES
(1, 'apartment', NULL),
(2, 'duplex', NULL),
(3, 'split level', NULL),
(4, 'ranch', NULL),
(5, 'mansion', NULL),
(6, 'town house', NULL),
(7, 'bungalo', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `log_id` int(9) NOT NULL,
  `family_id` int(9) DEFAULT NULL,
  `contact_id` int(9) NOT NULL COMMENT 'actor_id',
  `house_id` int(9) DEFAULT NULL,
  `property_id` int(9) DEFAULT NULL,
  `reason` varchar(120) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `notes` text,
  `status` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `log`
--

INSERT INTO `log` (`log_id`, `family_id`, `contact_id`, `house_id`, `property_id`, `reason`, `date`, `notes`, `status`) VALUES
(1, NULL, 2, NULL, 1, 'is broken', '2017-10-16 11:42:53', 'this property is broken and previously it was not, please fix', 'processing');

-- --------------------------------------------------------

--
-- Table structure for table `milestone`
--

CREATE TABLE `milestone` (
  `milestone_id` int(9) NOT NULL,
  `milestone` varchar(120) NOT NULL,
  `milestone_desc` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `milestone`
--

INSERT INTO `milestone` (`milestone_id`, `milestone`, `milestone_desc`) VALUES
(1, 'moved in', ''),
(2, 'had a child', ''),
(3, 'payed us back', 'finally payed us back for the house we provided them! '),
(4, '10+ years', 'been living here for 10+ years! Wow!');

-- --------------------------------------------------------

--
-- Table structure for table `organization`
--

CREATE TABLE `organization` (
  `orginization_id` int(9) NOT NULL,
  `name` varchar(120) NOT NULL,
  `contact_id` int(9) NOT NULL COMMENT 'person_id'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orginization`
--

INSERT INTO `orginization` (`orginization_id`, `name`, `contact_id`) VALUES
(3, 'FrontierAirlines', 1),
(4, 'loudbaron.co', 5);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE `person` (
  `person_id` int(9) NOT NULL,
  `family_id` int(9) DEFAULT NULL,
  `first` varchar(20) NOT NULL,
  `middle` varchar(20) DEFAULT NULL,
  `last` varchar(20) NOT NULL,
  `email` varchar(120) NOT NULL,
  `home_phone` varchar(20) DEFAULT NULL,
  `cell_phone` varchar(20) DEFAULT NULL,
  `work_phone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`person_id`, `family_id`, `first`, `middle`, `last`, `email`, `home_phone`, `cell_phone`, `work_phone`) VALUES
(1, NULL, 'Andrew', 'John', 'Fehr', 'ajfehr@ilstu.edu', '309-229-6771', NULL, NULL),
(2, 1, 'Abe', NULL, 'Ramseyer', 'aramsey@ilstu.edu', NULL, NULL, NULL),
(5, 2, 'Harry', 'and', 'Griffen', 'whatupwhatup@boo.com', NULL, '123-456-1223', NULL),
(6, 1, 'Abe\'s', 'illegitimate', 'child', 'tuttut@gmail.com', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `property`
--

CREATE TABLE `property` (
  `property_no` int(9) NOT NULL,
  `address_id` int(9) NOT NULL,
  `zone_id` int(9) NOT NULL,
  `owner_id` int(9) NOT NULL COMMENT 'actor_id ',
  `pstatus_id` int(9) NOT NULL DEFAULT '1',
  `appraised_value` double DEFAULT NULL,
  `appraised_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `taxes` double DEFAULT NULL,
  `notes` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `property`
--

INSERT INTO `property` (`property_no`, `address_id`, `zone_id`, `owner_id`, `pstatus_id`, `appraised_value`, `appraised_date`, `taxes`, `notes`) VALUES
(1, 5, 1, 2, 2, 423000, '2017-10-12 19:15:27', 80000, NULL),
(2, 4, 2, 4, 1, 46000.3, '2017-10-12 19:15:27', 12000, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `property-status`
--

CREATE TABLE `property-status` (
  `pstatus_id` int(9) NOT NULL,
  `pstatus` varchar(120) NOT NULL,
  `pstatus_desc` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `property-status`
--

INSERT INTO `property-status` (`pstatus_id`, `pstatus`, `pstatus_desc`) VALUES
(1, 'vacant', NULL),
(2, 'filled', NULL),
(3, 'processing', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `relation-type`
--

CREATE TABLE `relation-type` (
  `relation_id` int(9) NOT NULL,
  `relation_name` varchar(120) NOT NULL,
  `relation_desc` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `relation-type`
--

INSERT INTO `relation-type` (`relation_id`, `relation_name`, `relation_desc`) VALUES
(1, 'tennant', NULL),
(2, 'private property owner', NULL),
(3, 'vendor', NULL),
(4, 'orginization', NULL),
(5, 'volunteer', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `zone`
--

CREATE TABLE `zone` (
  `zone_id` int(9) NOT NULL,
  `zone_info` varchar(120) DEFAULT 'none',
  `zone_desc` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `zone`
--

INSERT INTO `zone` (`zone_id`, `zone_info`, `zone_desc`) VALUES
(1, 'first zone', NULL),
(2, 'second zone', NULL),
(3, 'danger zone', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `actor`
--
ALTER TABLE `actor`
  ADD PRIMARY KEY (`actor_id`);

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`address_id`);

--
-- Indexes for table `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`class_id`);

--
-- Indexes for table `construction_status`
--
ALTER TABLE `construction_status`
  ADD PRIMARY KEY (`cstatus_id`);

--
-- Indexes for table `family`
--
ALTER TABLE `family`
  ADD PRIMARY KEY (`family_id`);

--
-- Indexes for table `house`
--
ALTER TABLE `house`
  ADD PRIMARY KEY (`house_id`);

--
-- Indexes for table `house_contribution`
--
ALTER TABLE `house_contribution`
  ADD PRIMARY KEY (`contribution_id`);

--
-- Indexes for table `house_style`
--
ALTER TABLE `house_style`
  ADD PRIMARY KEY (`style_id`);

--
-- Indexes for table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`log_id`);

--
-- Indexes for table `milestone`
--
ALTER TABLE `milestone`
  ADD PRIMARY KEY (`milestone_id`);

--
-- Indexes for table `orginization`
--
ALTER TABLE `orginization`
  ADD PRIMARY KEY (`orginization_id`);

--
-- Indexes for table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`person_id`);

--
-- Indexes for table `property`
--
ALTER TABLE `property`
  ADD PRIMARY KEY (`property_no`),
  ADD UNIQUE KEY `address_id` (`address_id`);

--
-- Indexes for table `property-status`
--
ALTER TABLE `property-status`
  ADD PRIMARY KEY (`pstatus_id`);

--
-- Indexes for table `relation-type`
--
ALTER TABLE `relation-type`
  ADD PRIMARY KEY (`relation_id`);

--
-- Indexes for table `zone`
--
ALTER TABLE `zone`
  ADD PRIMARY KEY (`zone_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `actor`
--
ALTER TABLE `actor`
  MODIFY `actor_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `address_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `class`
--
ALTER TABLE `class`
  MODIFY `class_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `construction_status`
--
ALTER TABLE `construction_status`
  MODIFY `cstatus_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `family`
--
ALTER TABLE `family`
  MODIFY `family_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `house`
--
ALTER TABLE `house`
  MODIFY `house_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `house_contribution`
--
ALTER TABLE `house_contribution`
  MODIFY `contribution_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `house_style`
--
ALTER TABLE `house_style`
  MODIFY `style_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `log`
--
ALTER TABLE `log`
  MODIFY `log_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `milestone`
--
ALTER TABLE `milestone`
  MODIFY `milestone_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `property`
--
ALTER TABLE `property`
  MODIFY `property_no` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `property-status`
--
ALTER TABLE `property-status`
  MODIFY `pstatus_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `relation-type`
--
ALTER TABLE `relation-type`
  MODIFY `relation_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `zone`
--
ALTER TABLE `zone`
  MODIFY `zone_id` int(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
