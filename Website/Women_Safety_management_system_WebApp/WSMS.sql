/*
SQLyog - Free MySQL GUI v5.19
Host - 5.5.29 : Database - db_Women_safety_management_system
*********************************************************************
Server version : 5.5.29
*/


SET NAMES utf8;

SET SQL_MODE='';

create database if not exists `db_Women_safety_management_system`;

USE `db_Women_safety_management_system`;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Table structure for table `h_hospital` */

DROP TABLE IF EXISTS `h_hospital`;

CREATE TABLE `h_hospital` (
  `H_code` int(10) NOT NULL AUTO_INCREMENT,
  `Hospital_name` varchar(150) NOT NULL,
  `Address1` varchar(100) NOT NULL,
  `Address2` varchar(100) NOT NULL,
  `Area` varchar(100) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `pin` varchar(50) NOT NULL,
  `land_line` varchar(20) NOT NULL,
  `cell_no1` varchar(10) NOT NULL,
  `cell_no2` varchar(10) NOT NULL,
  `remarks` varchar(500) NOT NULL,
  `latitude` varchar(50) NOT NULL,
  `logitude` varchar(50) NOT NULL,
  `dist` double DEFAULT NULL,
  PRIMARY KEY (`H_code`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `h_hospital` */

insert into `h_hospital` (`H_code`,`Hospital_name`,`Address1`,`Address2`,`Area`,`city`,`state`,`pin`,`land_line`,`cell_no1`,`cell_no2`,`remarks`,`latitude`,`logitude`,`dist`) values (11,'defr','b1fdxfcgfdgfv','c1jxfcghscedfgbtrhbt','d1ytxtfyvgzdascew','egcvsced','swdxsce','116543','45227672','0000000000','9886692401','9886692401','9886692401','9886692401',NULL),(13,'htyhbefb','ngcvygvyg vhb','gvujvgvbh','jxfyhvjhgy','gcmhgcf','mjbhftyghbj','458786','null','7895142364','null','good','12.916308','77.603777',NULL);

/*Table structure for table `m_admin` */

DROP TABLE IF EXISTS `m_admin`;

CREATE TABLE `m_admin` (
  `s_no` int(6) NOT NULL AUTO_INCREMENT,
  `admin_id` varchar(40) NOT NULL,
  `admin_password` varchar(40) NOT NULL,
  `admin_name` varchar(50) NOT NULL,
  PRIMARY KEY (`s_no`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `m_admin` */

insert into `m_admin` (`s_no`,`admin_id`,`admin_password`,`admin_name`) values (1,'admin','admin','palani Kannan');

/*Table structure for table `m_emergency_call_no` */

DROP TABLE IF EXISTS `m_emergency_call_no`;

CREATE TABLE `m_emergency_call_no` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `emg_police_no` varchar(40) DEFAULT NULL,
  `emg_fire_no` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `m_emergency_call_no` */

insert into `m_emergency_call_no` (`id`,`emg_police_no`,`emg_fire_no`) values (1,'8123858487','8123858487');

/*Table structure for table `m_police_station` */

DROP TABLE IF EXISTS `m_police_station`;

CREATE TABLE `m_police_station` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `locname` varchar(100) NOT NULL,
  `latitude` varchar(20) NOT NULL,
  `langitude` varchar(20) NOT NULL,
  `cell` varchar(20) NOT NULL,
  `dist` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `m_police_station` */

insert into `m_police_station` (`id`,`locname`,`latitude`,`langitude`,`cell`,`dist`) values (1,'marathalli road','12.917195','77.589174','8951848917',1739.93807225744),(2,'1 st block','12.939293','77.586198','8951848917',871.763272132582),(3,'Yediyur','12.931877','77.576929','7760433598',723.585455087557),(4,'Jayanagar 3rd Block','12.931149','77.583305','9886692401',86.6756785604779),(5,'Pepe Jeans, 3rd block','12.931969','77.583761','8951848917',20.0322111701089);

/*Table structure for table `m_user` */

DROP TABLE IF EXISTS `m_user`;

CREATE TABLE `m_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `otp` varchar(100) DEFAULT NULL,
  `status` varchar(120) DEFAULT 'false',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `m_user` */

insert into `m_user` (`id`,`username`,`password`,`address`,`phone`,`email`,`otp`,`status`) values (1,'shalini','shalini','bangalore','8123848587','shanunamgowda@gmail.com','787212','true'),(2,'abhi','abhi','bangalore','8123848587','das.ovishek@gmail.com','736985','true');

/*Table structure for table `p_police_station` */

DROP TABLE IF EXISTS `p_police_station`;

CREATE TABLE `p_police_station` (
  `code` int(10) NOT NULL AUTO_INCREMENT,
  `police_station_name` varchar(50) NOT NULL,
  `Address1` varchar(50) NOT NULL,
  `Address2` varchar(50) NOT NULL,
  `Area` varchar(50) NOT NULL,
  `City` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `pin` varchar(10) NOT NULL,
  `land_line` varchar(15) NOT NULL,
  `cell_no1` varchar(20) NOT NULL,
  `cell_no2` varchar(20) NOT NULL,
  `remarks` varchar(150) NOT NULL,
  `latitude` varchar(20) NOT NULL,
  `logitude` varchar(20) NOT NULL,
  `dist` double DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `p_police_station` */

insert into `p_police_station` (`code`,`police_station_name`,`Address1`,`Address2`,`Area`,`City`,`state`,`pin`,`land_line`,`cell_no1`,`cell_no2`,`remarks`,`latitude`,`logitude`,`dist`) values (2,'begur police station','bommanahalli','Begur','Begur','Bangalore','Karnataka','560011','45227672','9886692401','9886692401','good','12.877628','77.534755',NULL),(8,'kjfhgafu','dvkuhsdv','ihhcgbi','iuhcj','iuhckjh','iuhevig','657542','null','5717964875','null','veshv','12.928109','77.599611',NULL);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
