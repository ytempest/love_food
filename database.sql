/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.17 : Database - love_food
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`love_food` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `love_food`;

/*Table structure for table `accessories` */

DROP TABLE IF EXISTS `accessories`;

CREATE TABLE `accessories` (
  `acc_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cook_id` bigint(20) DEFAULT NULL,
  `acc_name` varchar(40) DEFAULT NULL,
  `acc_amount` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`acc_id`),
  KEY `FK_cookbook_use_accessories` (`cook_id`),
  CONSTRAINT `FK_cookbook_use_accessories` FOREIGN KEY (`cook_id`) REFERENCES `cookbook` (`cook_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Table structure for table `activity` */

DROP TABLE IF EXISTS `activity`;

CREATE TABLE `activity` (
  `act_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `act_image_url` varchar(500) DEFAULT NULL,
  `act_title` varchar(80) DEFAULT NULL,
  `act_desc` varchar(800) DEFAULT NULL,
  `act_start_time` datetime DEFAULT NULL,
  `act_finish_time` datetime DEFAULT NULL,
  `act_request` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`act_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Table structure for table `admin_info` */

DROP TABLE IF EXISTS `admin_info`;

CREATE TABLE `admin_info` (
  `admin_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_account` varchar(20) DEFAULT NULL,
  `admin_pwd` varchar(32) DEFAULT NULL,
  `admin_name` char(20) DEFAULT NULL,
  `admin_sex` char(1) DEFAULT NULL,
  `admin_phone` char(11) DEFAULT NULL,
  `admin_email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `award_activity` */

DROP TABLE IF EXISTS `award_activity`;

CREATE TABLE `award_activity` (
  `awa_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `act_id` bigint(20) DEFAULT NULL,
  `part_id` bigint(20) DEFAULT NULL,
  `prize_id` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`awa_id`),
  KEY `FK_activity_have_winner` (`act_id`),
  KEY `FK_activity_win_award` (`prize_id`),
  KEY `FK_user_win_prize` (`part_id`),
  CONSTRAINT `FK_activity_have_winner` FOREIGN KEY (`act_id`) REFERENCES `activity` (`act_id`),
  CONSTRAINT `FK_activity_win_award` FOREIGN KEY (`prize_id`) REFERENCES `prizes` (`prize_id`),
  CONSTRAINT `FK_user_win_prize` FOREIGN KEY (`part_id`) REFERENCES `partakes_activity` (`part_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Table structure for table `collection` */

DROP TABLE IF EXISTS `collection`;

CREATE TABLE `collection` (
  `collect_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cook_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`collect_id`),
  KEY `FK_cookbook_collect_collection` (`cook_id`),
  KEY `FK_user_collect_collection` (`user_id`),
  CONSTRAINT `FK_cookbook_collect_collection` FOREIGN KEY (`cook_id`) REFERENCES `cookbook` (`cook_id`),
  CONSTRAINT `FK_user_collect_collection` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Table structure for table `comments` */

DROP TABLE IF EXISTS `comments`;

CREATE TABLE `comments` (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `topic_id` bigint(20) DEFAULT NULL,
  `comment_content` varchar(800) DEFAULT NULL,
  `comment_time` datetime DEFAULT NULL,
  `comment_from_user` bigint(20) DEFAULT NULL,
  `comment_to_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FK_topic_have_comment` (`topic_id`),
  CONSTRAINT `FK_topic_have_comment` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Table structure for table `cookbook` */

DROP TABLE IF EXISTS `cookbook`;

CREATE TABLE `cookbook` (
  `cook_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cook_group` varchar(32) DEFAULT NULL,
  `cook_type` varchar(32) DEFAULT NULL,
  `cook_image_url` varchar(500) DEFAULT NULL,
  `cook_user_id` bigint(20) DEFAULT NULL,
  `cook_title` varchar(60) DEFAULT NULL,
  `cook_desc` varchar(800) DEFAULT NULL,
  `cook_publish_time` datetime DEFAULT NULL,
  PRIMARY KEY (`cook_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

/*Table structure for table `mainmaterials` */

DROP TABLE IF EXISTS `mainmaterials`;

CREATE TABLE `mainmaterials` (
  `main_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cook_id` bigint(20) DEFAULT NULL,
  `main_name` varchar(40) DEFAULT NULL,
  `main_amount` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`main_id`),
  KEY `FK_cookbook_use_material` (`cook_id`),
  CONSTRAINT `FK_cookbook_use_material` FOREIGN KEY (`cook_id`) REFERENCES `cookbook` (`cook_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

/*Table structure for table `partakes_activity` */

DROP TABLE IF EXISTS `partakes_activity`;

CREATE TABLE `partakes_activity` (
  `part_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `act_id` bigint(20) DEFAULT NULL,
  `cook_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`part_id`),
  KEY `FK_activity_in_partake` (`act_id`),
  KEY `FK_cookbook_in_part` (`cook_id`),
  KEY `FK_user_in_part` (`user_id`),
  CONSTRAINT `FK_activity_in_partake` FOREIGN KEY (`act_id`) REFERENCES `activity` (`act_id`),
  CONSTRAINT `FK_cookbook_in_part` FOREIGN KEY (`cook_id`) REFERENCES `cookbook` (`cook_id`),
  CONSTRAINT `FK_user_in_part` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `prizes` */

DROP TABLE IF EXISTS `prizes`;

CREATE TABLE `prizes` (
  `prize_id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `prize_name` varchar(40) DEFAULT NULL,
  `prize_prize` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`prize_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `procedures` */

DROP TABLE IF EXISTS `procedures`;

CREATE TABLE `procedures` (
  `proce_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cook_id` bigint(20) DEFAULT NULL,
  `proce_no` tinyint(4) DEFAULT NULL,
  `proce_image_url` varchar(500) DEFAULT NULL,
  `proce_desc` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`proce_id`),
  KEY `FK_cookbook_do_procedure` (`cook_id`),
  CONSTRAINT `FK_cookbook_do_procedure` FOREIGN KEY (`cook_id`) REFERENCES `cookbook` (`cook_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Table structure for table `replys` */

DROP TABLE IF EXISTS `replys`;

CREATE TABLE `replys` (
  `reply_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) DEFAULT NULL,
  `reply_content` varchar(800) DEFAULT NULL,
  `reply_time` datetime DEFAULT NULL,
  `reply_from_user` bigint(20) DEFAULT NULL,
  `reply_to_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`reply_id`),
  KEY `FK_comment_hava_reply` (`comment_id`),
  CONSTRAINT `FK_comment_hava_reply` FOREIGN KEY (`comment_id`) REFERENCES `comments` (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Table structure for table `topic_images` */

DROP TABLE IF EXISTS `topic_images`;

CREATE TABLE `topic_images` (
  `image_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `topic_id` bigint(20) DEFAULT NULL,
  `image_url` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `FK_topic_contain_image` (`topic_id`),
  CONSTRAINT `FK_topic_contain_image` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Table structure for table `topics` */

DROP TABLE IF EXISTS `topics`;

CREATE TABLE `topics` (
  `topic_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `topic_title` varchar(80) DEFAULT NULL,
  `topic_content` varchar(1000) DEFAULT NULL,
  `topic_publish_time` datetime DEFAULT NULL,
  PRIMARY KEY (`topic_id`),
  KEY `FK_user_publish_topic` (`user_id`),
  CONSTRAINT `FK_user_publish_topic` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_account` char(30) DEFAULT NULL,
  `user_pwd` char(32) DEFAULT NULL,
  `user_head_url` varchar(500) DEFAULT NULL,
  `user_sex` char(4) DEFAULT '男',
  `user_birth` date DEFAULT NULL,
  `user_phone` char(11) DEFAULT NULL,
  `user_email` varchar(50) DEFAULT NULL,
  `user_qq` char(15) DEFAULT NULL,
  `user_register_time` datetime NOT NULL,
  `user_status` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_account` (`user_account`)
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8;

/*Table structure for table `zans` */

DROP TABLE IF EXISTS `zans`;

CREATE TABLE `zans` (
  `zan_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `topic_id` bigint(20) DEFAULT NULL,
  `zan_from_user` bigint(20) DEFAULT NULL,
  `zan_to_user` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`zan_id`),
  KEY `FK_topic_record_zan` (`topic_id`),
  CONSTRAINT `FK_topic_record_zan` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
