/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50538
Source Host           : localhost:3306
Source Database       : study

Target Server Type    : MYSQL
Target Server Version : 50538
File Encoding         : 65001

Date: 2019-01-02 22:04:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bespoke`
-- ----------------------------
DROP TABLE IF EXISTS `bespoke`;
CREATE TABLE `bespoke` (
  resourceId varchar(255)  NOT NULL,
		vaccinumId  varchar(2000)     null,
		vaccinumName  varchar(2000)     null,
		vaccinumType  varchar(2000)     null,
		userId  varchar(2000)     null,
		userName  varchar(2000)     null,
		authorId  varchar(2000)     null,
		authorName  varchar(2000)     null,
		authorContext  varchar(2000)     null,
		accusationType  varchar(2000)     null,
		creatorId  varchar(254)     null,
		creatorName  varchar(254)     null,
		updaterId  varchar(32)     null,
		updaterName  varchar(254)     null,
		updateTime    datetime   null,
		createTime    datetime   null,
  PRIMARY KEY (`resourceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of bespoke
-- ----------------------------
