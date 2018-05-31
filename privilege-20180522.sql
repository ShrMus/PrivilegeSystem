/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50027
Source Host           : localhost:3306
Source Database       : privilege-20180522

Target Server Type    : MYSQL
Target Server Version : 50027
File Encoding         : 65001

Date: 2018-06-01 04:49:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for privilege
-- ----------------------------
DROP TABLE IF EXISTS `privilege`;
CREATE TABLE `privilege` (
  `privilege_id` int(11) NOT NULL auto_increment,
  `privilege_name` varchar(255) default NULL,
  `privilege_url` varchar(255) default NULL,
  `privilege_parent_id` int(11) default NULL,
  PRIMARY KEY  (`privilege_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_id` int(11) NOT NULL auto_increment,
  `role_name` varchar(255) default NULL,
  `role_description` varchar(255) default NULL,
  PRIMARY KEY  (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role_privilege
-- ----------------------------
DROP TABLE IF EXISTS `role_privilege`;
CREATE TABLE `role_privilege` (
  `role_privilege_id` int(11) NOT NULL auto_increment,
  `role_id` int(11) default NULL,
  `privilege_id` int(11) default NULL,
  PRIMARY KEY  (`role_privilege_id`),
  KEY `FK_ROLE_PRIVILEGE_ROLE_ID` (`role_id`),
  KEY `FK_ROLE_PRIVILEGE_PRIVILEGE_ID` (`privilege_id`),
  CONSTRAINT `FK_ROLE_PRIVILEGE_PRIVILEGE_ID` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`privilege_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ROLE_PRIVILEGE_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL auto_increment,
  `user_username` varchar(20) default NULL,
  `user_password` varchar(100) default NULL,
  PRIMARY KEY  (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_privilege
-- ----------------------------
DROP TABLE IF EXISTS `user_privilege`;
CREATE TABLE `user_privilege` (
  `user_privilege_id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL,
  `privilege_id` int(11) default NULL,
  PRIMARY KEY  (`user_privilege_id`),
  KEY `FK_USER_PRIVILEGE_USER_ID` (`user_id`),
  KEY `FK_USER_PRIVILEGE_PRIVILEGE_ID` (`privilege_id`),
  CONSTRAINT `FK_USER_PRIVILEGE_PRIVILEGE_ID` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`privilege_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USER_PRIVILEGE_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_role_id` int(11) NOT NULL auto_increment,
  `user_id` int(11) default NULL,
  `role_id` int(11) default NULL,
  PRIMARY KEY  (`user_role_id`),
  KEY `FK_USER_ROLE_USER_ID` (`user_id`),
  KEY `FK_USER_ROLE_ROLE_ID` (`role_id`),
  CONSTRAINT `FK_USER_ROLE_ROLE_ID` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_USER_ROLE_USER_ID` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
