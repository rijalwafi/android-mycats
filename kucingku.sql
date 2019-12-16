-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 06, 2019 at 05:10 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kucingku`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_catfood`
--

CREATE TABLE `tb_catfood` (
  `id_cat_food` int(11) NOT NULL,
  `cat_food` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_catfood`
--

INSERT INTO `tb_catfood` (`id_cat_food`, `cat_food`) VALUES
(1, 'Whiskas'),
(2, 'Proplan'),
(3, 'Royal Canin');

-- --------------------------------------------------------

--
-- Table structure for table `tb_cat_colour`
--

CREATE TABLE `tb_cat_colour` (
  `id_cat_colour` int(11) NOT NULL,
  `cat_colour` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_cat_colour`
--

INSERT INTO `tb_cat_colour` (`id_cat_colour`, `cat_colour`) VALUES
(1, 'white/black'),
(2, 'black'),
(3, 'brown'),
(4, 'yellow');

-- --------------------------------------------------------

--
-- Table structure for table `tb_cat_gender`
--

CREATE TABLE `tb_cat_gender` (
  `id_cat_gender` int(11) NOT NULL,
  `cat_gender` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_cat_gender`
--

INSERT INTO `tb_cat_gender` (`id_cat_gender`, `cat_gender`) VALUES
(1, 'male'),
(2, 'female');

-- --------------------------------------------------------

--
-- Table structure for table `tb_give_food`
--

CREATE TABLE `tb_give_food` (
  `id_give_food` int(11) NOT NULL,
  `id_type_cat` int(11) DEFAULT NULL,
  `id_cat_food` int(11) DEFAULT NULL,
  `amount_kg` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_give_food`
--

INSERT INTO `tb_give_food` (`id_give_food`, `id_type_cat`, `id_cat_food`, `amount_kg`) VALUES
(1, 1, 2, '1'),
(2, 2, 2, '1'),
(3, 2, 2, '1'),
(4, 4, 3, '1'),
(5, 4, 3, '1'),
(6, 1, 2, '2'),
(7, 3, 1, '3'),
(8, 5, 3, '3'),
(9, 5, 3, '1');

-- --------------------------------------------------------

--
-- Table structure for table `tb_list_cat`
--

CREATE TABLE `tb_list_cat` (
  `id_list_cat` int(11) NOT NULL,
  `id_user` int(100) DEFAULT NULL,
  `cat_name` varchar(100) DEFAULT NULL,
  `cat_Image` varchar(100) DEFAULT NULL,
  `id_type_cat` int(11) DEFAULT NULL,
  `id_cat_gender` int(11) DEFAULT NULL,
  `id_cat_food` int(11) DEFAULT NULL,
  `id_cat_colour` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_list_cat`
--

INSERT INTO `tb_list_cat` (`id_list_cat`, `id_user`, `cat_name`, `cat_Image`, `id_type_cat`, `id_cat_gender`, `id_cat_food`, `id_cat_colour`) VALUES
(16, 8, 'anton', NULL, 4, 1, 2, 2),
(17, 8, 'agan', NULL, 3, 2, 1, 3),
(18, 8, 'dondin', NULL, 2, 1, 3, 2),
(19, 8, 'dragon', NULL, 1, 1, 1, 1),
(20, 9, 'daku', NULL, 3, 1, 3, 4),
(21, 9, 'nona', NULL, 3, 2, 1, 2),
(22, 8, 'Antonio', NULL, 1, 2, 1, 3),
(23, 8, 'Logan', NULL, 4, 2, 3, 3),
(65, 8, 'zon', NULL, 2, 1, 3, 1),
(67, 8, 'Log', NULL, 1, 1, 3, 3),
(68, 8, 'jaka', NULL, 1, 2, 3, 2),
(69, 7, 'Gorgon', NULL, 3, 2, 1, 3),
(70, 7, 'Drag', NULL, 1, 1, 3, 1),
(71, 7, 'mamat', NULL, 1, 1, 3, 1),
(72, 7, 'Dian', NULL, 1, 2, 3, 1),
(73, 7, 'Doni', NULL, 1, 1, 3, 1),
(74, 10, 'sifa', NULL, 1, 2, 3, 1),
(75, 10, 'sant', NULL, 4, 2, 3, 1),
(76, 7, 'Dian', NULL, 1, 2, 3, 1),
(77, 7, 'Doni', NULL, 1, 1, 3, 1),
(78, 8, 'Blacky', NULL, 5, 1, 3, 1),
(79, 7, 'Mart', NULL, 5, 1, 3, 2),
(80, 7, 'Mart', NULL, 5, 2, 3, 2),
(81, 8, 'lulu', NULL, 5, 2, 2, 3),
(82, 8, 'lulu', NULL, 5, 2, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tb_typecat`
--

CREATE TABLE `tb_typecat` (
  `id_type_cat` int(11) NOT NULL,
  `cat_type` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_typecat`
--

INSERT INTO `tb_typecat` (`id_type_cat`, `cat_type`) VALUES
(1, 'Angora'),
(2, 'Sphinx'),
(3, 'Persian'),
(4, 'Mainecoon'),
(5, 'Scottish Fold');

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id_user` int(10) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`id_user`, `username`, `password`, `address`) VALUES
(7, 'rijalwafi', '$2y$10$KTrBD0EoQ4bKMSob9MKnu.39rWa9gl4YXp7kZYADiyfe8kpXFPScm', 'bekasi'),
(8, 'anton', '$2y$10$a87MraXEL6daThHB8Y2Q..PVQJe5tldde7gpkwFer/f1YVhroMCYe', 'jakarta'),
(9, 'dewa19', '$2y$10$l9jvfB8Gu/HucSYFqzHPPeXziA7wHjThcLX1gmItsCe7o5TgsrI4S', 'bandung'),
(10, 'dian', '$2y$10$0d6qjdhhGaIJsGTF4cAqnOJqeks59vy3gwqtgRvPZ7z/CB4oag1z6', 'cimahi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_catfood`
--
ALTER TABLE `tb_catfood`
  ADD PRIMARY KEY (`id_cat_food`);

--
-- Indexes for table `tb_cat_colour`
--
ALTER TABLE `tb_cat_colour`
  ADD PRIMARY KEY (`id_cat_colour`);

--
-- Indexes for table `tb_cat_gender`
--
ALTER TABLE `tb_cat_gender`
  ADD PRIMARY KEY (`id_cat_gender`);

--
-- Indexes for table `tb_give_food`
--
ALTER TABLE `tb_give_food`
  ADD PRIMARY KEY (`id_give_food`),
  ADD KEY `id_cat_food` (`id_cat_food`),
  ADD KEY `id_type_cat` (`id_type_cat`);

--
-- Indexes for table `tb_list_cat`
--
ALTER TABLE `tb_list_cat`
  ADD PRIMARY KEY (`id_list_cat`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_type_cat` (`id_type_cat`),
  ADD KEY `id_cat_gender` (`id_cat_gender`),
  ADD KEY `id_cat_food` (`id_cat_food`),
  ADD KEY `id_cat_colour` (`id_cat_colour`);

--
-- Indexes for table `tb_typecat`
--
ALTER TABLE `tb_typecat`
  ADD PRIMARY KEY (`id_type_cat`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_catfood`
--
ALTER TABLE `tb_catfood`
  MODIFY `id_cat_food` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_cat_colour`
--
ALTER TABLE `tb_cat_colour`
  MODIFY `id_cat_colour` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tb_cat_gender`
--
ALTER TABLE `tb_cat_gender`
  MODIFY `id_cat_gender` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tb_give_food`
--
ALTER TABLE `tb_give_food`
  MODIFY `id_give_food` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tb_list_cat`
--
ALTER TABLE `tb_list_cat`
  MODIFY `id_list_cat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=83;

--
-- AUTO_INCREMENT for table `tb_typecat`
--
ALTER TABLE `tb_typecat`
  MODIFY `id_type_cat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id_user` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_give_food`
--
ALTER TABLE `tb_give_food`
  ADD CONSTRAINT `tb_give_food_ibfk_1` FOREIGN KEY (`id_cat_food`) REFERENCES `tb_catfood` (`id_cat_food`),
  ADD CONSTRAINT `tb_give_food_ibfk_2` FOREIGN KEY (`id_type_cat`) REFERENCES `tb_typecat` (`id_type_cat`);

--
-- Constraints for table `tb_list_cat`
--
ALTER TABLE `tb_list_cat`
  ADD CONSTRAINT `tb_list_cat_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `tb_user` (`id_user`),
  ADD CONSTRAINT `tb_list_cat_ibfk_2` FOREIGN KEY (`id_type_cat`) REFERENCES `tb_typecat` (`id_type_cat`),
  ADD CONSTRAINT `tb_list_cat_ibfk_3` FOREIGN KEY (`id_cat_gender`) REFERENCES `tb_cat_gender` (`id_cat_gender`),
  ADD CONSTRAINT `tb_list_cat_ibfk_4` FOREIGN KEY (`id_cat_food`) REFERENCES `tb_catfood` (`id_cat_food`),
  ADD CONSTRAINT `tb_list_cat_ibfk_5` FOREIGN KEY (`id_cat_colour`) REFERENCES `tb_cat_colour` (`id_cat_colour`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
