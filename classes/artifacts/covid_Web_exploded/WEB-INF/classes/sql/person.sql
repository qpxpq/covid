/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : c_exam

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2019-01-16 12:03:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `person`
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `personId` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `personName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `personNo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cardNo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `personMobile` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `sex` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`personId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('402889e054182b2f0154182bc84d0001', '管理员', 'admin', 'admin', 'admin', '430511199102207035', '18925186343', '男');
INSERT INTO `person` VALUES ('402889e054182b2f015418654051000c', '管理员', '111', 'secretary', 'student', '430511199102207035', '18925186343', '女');
