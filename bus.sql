/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50711
Source Host           : localhost:3306
Source Database       : bus

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2017-04-21 22:11:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bus_admin`
-- ----------------------------
DROP TABLE IF EXISTS `bus_admin`;
CREATE TABLE `bus_admin` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_admin
-- ----------------------------
INSERT INTO `bus_admin` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for `bus_line`
-- ----------------------------
DROP TABLE IF EXISTS `bus_line`;
CREATE TABLE `bus_line` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `bus_name` varchar(32) NOT NULL,
  `full_name` tinytext NOT NULL,
  `first_stop` varchar(32) DEFAULT NULL,
  `last_stop` varchar(32) DEFAULT NULL,
  `deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus_line
-- ----------------------------
INSERT INTO `bus_line` VALUES ('1', '160', '160路', '净月潭', '长春站', '0');
INSERT INTO `bus_line` VALUES ('2', '281', '281路', '东方万达城', '长春站', '0');
INSERT INTO `bus_line` VALUES ('4', '306', '306路', '市政府', '长春站', '0');
INSERT INTO `bus_line` VALUES ('5', '164', '164路', '欧亚卖场', '长信学院', '0');
INSERT INTO `bus_line` VALUES ('6', '88', '88路', '卫星广场', '长春站北口', '0');
INSERT INTO `bus_line` VALUES ('7', '218', '218路A线', '光华学院', '朝阳公园', '0');
INSERT INTO `bus_line` VALUES ('8', '124', '124路', '富锋', '太阳城', '0');
INSERT INTO `bus_line` VALUES ('9', '222', '222路', '高新开发区', '长春站', '0');
INSERT INTO `bus_line` VALUES ('10', '80', '80路', '体育场', '体育场', '0');
INSERT INTO `bus_line` VALUES ('11', '110', '110路', '华正批发', '后水泉', '0');
INSERT INTO `bus_line` VALUES ('12', '54', '54路', '西安大路', '工农大路', '0');
INSERT INTO `bus_line` VALUES ('13', '17', '17路', '般若寺', '中海水岸春城', '0');
INSERT INTO `bus_line` VALUES ('14', '17', '17路', '般若寺', '中海水岸春城', '1');
INSERT INTO `bus_line` VALUES ('15', '17', '17路', '般若寺', '中海水岸春城', '1');
INSERT INTO `bus_line` VALUES ('16', '17', '17路', '般若寺', '中海水岸春城', '1');

-- ----------------------------
-- Table structure for `line`
-- ----------------------------
DROP TABLE IF EXISTS `line`;
CREATE TABLE `line` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `bus_name` varchar(32) NOT NULL,
  `full_name` tinytext NOT NULL,
  `first_stop` varchar(32) DEFAULT NULL,
  `last_stop` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of line
-- ----------------------------
INSERT INTO `line` VALUES ('1', '160', '160路', '净月潭', '长春站');
INSERT INTO `line` VALUES ('2', '281', '281路', '东方万达城', '长春站');
INSERT INTO `line` VALUES ('4', '306', '306路', '市政府', '长春站');
INSERT INTO `line` VALUES ('5', '164', '164路', '欧亚卖场', '长信学院');
INSERT INTO `line` VALUES ('6', '88', '88路', '卫星广场', '长春站北口');
INSERT INTO `line` VALUES ('7', '218', '218路A线', '光华学院', '朝阳公园');
INSERT INTO `line` VALUES ('8', '124', '124路', '富锋', '太阳城');
INSERT INTO `line` VALUES ('9', '222', '222路', '高新开发区', '长春站');
INSERT INTO `line` VALUES ('10', '80', '80路', '体育场', '体育场');
INSERT INTO `line` VALUES ('11', '110', '110路', '华正批发', '后水泉');
INSERT INTO `line` VALUES ('12', '54', '54路', '西安大路', '工农大路');
