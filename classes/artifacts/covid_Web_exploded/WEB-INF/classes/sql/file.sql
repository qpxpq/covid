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
DROP TABLE IF EXISTS `t_file`;
CREATE TABLE `t_file` (
  `resourceId` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `fileOldName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `fileNewName` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `filePath` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `fileSuffix` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`resourceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

