/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50149
Source Host           : localhost:3306
Source Database       : person_management

Target Server Type    : MYSQL
Target Server Version : 50149
File Encoding         : 65001

Date: 2018-06-18 23:24:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `authority`
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `authority_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`authority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of authority
-- ----------------------------

-- ----------------------------
-- Table structure for `examine`
-- ----------------------------
DROP TABLE IF EXISTS `examine`;
CREATE TABLE `examine` (
  `examine_id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(128) DEFAULT NULL,
  `hobby` varchar(128) DEFAULT NULL,
  `introduction` varchar(256) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `qq` varchar(16) DEFAULT NULL,
  `reason` varchar(256) DEFAULT NULL,
  `we_chat` varchar(32) DEFAULT NULL,
  `mail` varchar(64) DEFAULT NULL,
  `nickname` varchar(32) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  PRIMARY KEY (`examine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of examine
-- ----------------------------
INSERT INTO `examine` VALUES ('1', '123', '123', '123', '321', '123', '123', '123', '444', '123', '123', '123', '1');
INSERT INTO `examine` VALUES ('2', 'undefined', 'undefined', 'undefined', 'undefined', 'undefined', 'undefined', 'undefined', 'undefined', 'undefined', 'undefined', 'undefined', null);
INSERT INTO `examine` VALUES ('3', '342', '234', '23', '234', '234', '234', '234', '423', '2342', '234', '234', null);
INSERT INTO `examine` VALUES ('4', '346', '336', '346', '346', '346', '346', '346', '364', '346', '346', '346', null);
INSERT INTO `examine` VALUES ('5', '2352', '2352', '2352', '2352', '235', '2532', '2352', '2352', '235', '2352', '23523', null);
INSERT INTO `examine` VALUES ('6', '2352', '2352', '2352', '2352', '235', '2532', '2352', '2352', '235', '2352', '23523', null);
INSERT INTO `examine` VALUES ('7', '2532', '35', '235', '2523', '532', '352', '52', '25', '5252', '2352', '5325', null);
INSERT INTO `examine` VALUES ('8', '2532', '35', '235', '2523', '532', '352', '52', '25', '5252', '2352', '5325', null);
INSERT INTO `examine` VALUES ('9', '2532', '35', '235', '2523', '532', '352', '52', '25', '5252', '2352', '5325', null);
INSERT INTO `examine` VALUES ('10', '26', '3636', '236', '234', '523', '62', '2362', '263', '362', '236', '236', null);
INSERT INTO `examine` VALUES ('11', '8568', '858', '56', '346', '34637', '56', '458', '5685', '75648', '5347', '347', null);
INSERT INTO `examine` VALUES ('12', '', '58', '', '5685', '858', '', '658', '58', '', '58', '568', null);

-- ----------------------------
-- Table structure for `frozen`
-- ----------------------------
DROP TABLE IF EXISTS `frozen`;
CREATE TABLE `frozen` (
  `frozen_id` int(11) NOT NULL AUTO_INCREMENT,
  `end_date` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`frozen_id`),
  KEY `FKsy5xroaf61e5yooc2vufl2t5l` (`user_id`),
  CONSTRAINT `FKsy5xroaf61e5yooc2vufl2t5l` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of frozen
-- ----------------------------

-- ----------------------------
-- Table structure for `manager`
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `manager_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`manager_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(128) DEFAULT NULL,
  `hobby` varchar(128) DEFAULT NULL,
  `introduction` varchar(256) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `password` varchar(16) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `qq` varchar(16) DEFAULT NULL,
  `we_chat` varchar(32) DEFAULT NULL,
  `nickname` varchar(32) DEFAULT NULL,
  `mail` varchar(64) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', null, '军军', '军军军军', '123', '123', null, null, null, '哈哈', null, null);
INSERT INTO `user` VALUES ('3', '124', '125', '125', '123', '123', '123', '123', '123', '123', '123', null);
INSERT INTO `user` VALUES ('4', '463', '', '46', '634', '346', '346', '346', '3463', '346', '346', null);

-- ----------------------------
-- Table structure for `user_authority`
-- ----------------------------
DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority` (
  `user_id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL,
  UNIQUE KEY `UK_r26d2qfcm6jm4jykhho6kn3u6` (`authority_id`),
  KEY `FKpqlsjpkybgos9w2svcri7j8xy` (`user_id`),
  CONSTRAINT `FKgvxjs381k6f48d5d2yi11uh89` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`authority_id`),
  CONSTRAINT `FKpqlsjpkybgos9w2svcri7j8xy` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_authority
-- ----------------------------
