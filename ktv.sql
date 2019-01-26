/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : ktv

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-06-09 17:09:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `name` varchar(20) NOT NULL,
  `room` int(10) NOT NULL,
  `money` double(30,2) NOT NULL,
  `time` double(10,1) NOT NULL,
  KEY `name_FK` (`name`),
  KEY `room_FK` (`room`),
  CONSTRAINT `name_FK` FOREIGN KEY (`name`) REFERENCES `users` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('李华', '205', '400.00', '2.0');
INSERT INTO `record` VALUES ('马云', '305', '3000.00', '10.0');
INSERT INTO `record` VALUES ('马化腾', '303', '3000.00', '10.0');
INSERT INTO `record` VALUES ('李华', '101', '1000.00', '10.0');
INSERT INTO `record` VALUES ('李小龙', '203', '400.00', '2.0');
INSERT INTO `record` VALUES ('李小龙', '203', '400.00', '2.0');
INSERT INTO `record` VALUES ('李小龙', '203', '400.00', '2.0');
INSERT INTO `record` VALUES ('李小龙', '203', '1800.00', '9.0');
INSERT INTO `record` VALUES ('李小龙', '301', '900.00', '3.0');
INSERT INTO `record` VALUES ('李小龙', '302', '900.00', '3.0');
INSERT INTO `record` VALUES ('马云', '105', '500.00', '5.0');
INSERT INTO `record` VALUES ('马云', '105', '100.00', '1.0');
INSERT INTO `record` VALUES ('马云', '203', '800.00', '4.0');
INSERT INTO `record` VALUES ('马云', '305', '300.00', '1.0');
INSERT INTO `record` VALUES ('马化腾', '201', '200.00', '1.0');
INSERT INTO `record` VALUES ('马化腾', '301', '300.00', '1.0');
INSERT INTO `record` VALUES ('马化腾', '303', '300.00', '1.0');
INSERT INTO `record` VALUES ('马化腾', '203', '200.00', '1.0');
INSERT INTO `record` VALUES ('新垣结衣', '303', '600.00', '2.0');
INSERT INTO `record` VALUES ('新垣结衣', '201', '400.00', '2.0');
INSERT INTO `record` VALUES ('石原里美', '205', '200.00', '1.0');
INSERT INTO `record` VALUES ('石原里美', '305', '600.00', '2.0');
INSERT INTO `record` VALUES ('石原里美', '201', '400.00', '2.0');
INSERT INTO `record` VALUES ('石原里美', '202', '400.00', '2.0');
INSERT INTO `record` VALUES ('李小龙', '303', '300.00', '1.2');
INSERT INTO `record` VALUES ('李小龙', '201', '200.00', '1.0');
INSERT INTO `record` VALUES ('李小龙', '303', '300.00', '1.0');
INSERT INTO `record` VALUES ('李小龙', '205', '400.00', '2.0');
INSERT INTO `record` VALUES ('石原里美', '305', '600.00', '2.0');
INSERT INTO `record` VALUES ('李白', '201', '200.00', '1.0');
INSERT INTO `record` VALUES ('新垣结衣', '305', '600.00', '2.0');
INSERT INTO `record` VALUES ('李白', '303', '300.00', '1.0');
INSERT INTO `record` VALUES ('新垣结衣', '201', '400.00', '2.0');
INSERT INTO `record` VALUES ('李白', '201', '400.00', '2.0');
INSERT INTO `record` VALUES ('李白', '305', '900.00', '3.0');
INSERT INTO `record` VALUES ('小白', '303', '600.00', '2.0');
INSERT INTO `record` VALUES ('小白', '305', '600.00', '2.0');
INSERT INTO `record` VALUES ('小智', '301', '600.00', '2.0');
INSERT INTO `record` VALUES ('小智', '301', '300.00', '1.0');
INSERT INTO `record` VALUES ('123', '305', '600.00', '2.0');
INSERT INTO `record` VALUES ('123', '104', '200.00', '2.0');
INSERT INTO `record` VALUES ('123', '102', '100.00', '1.0');
INSERT INTO `record` VALUES ('1', '122', '400.00', '4.0');
INSERT INTO `record` VALUES ('1', '231', '1000.00', '5.0');
INSERT INTO `record` VALUES ('李白', '302', '900.00', '3.0');
INSERT INTO `record` VALUES ('鬼使白', '313', '900.00', '3.0');
INSERT INTO `record` VALUES ('鬼使白', '388', '900.00', '3.0');
INSERT INTO `record` VALUES ('鬼使白', '231', '600.00', '3.0');
INSERT INTO `record` VALUES ('鬼使白', '369', '1200.00', '4.0');
INSERT INTO `record` VALUES ('456', '133', '300.00', '3.0');
INSERT INTO `record` VALUES ('789', '249', '600.00', '3.0');
INSERT INTO `record` VALUES ('789', '210', '600.00', '3.0');
INSERT INTO `record` VALUES ('789', '390', '600.00', '2.0');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `roomName` int(10) NOT NULL,
  `price` double(10,2) NOT NULL,
  `roomType` varchar(10) NOT NULL,
  `book` bit(1) DEFAULT b'0',
  PRIMARY KEY (`roomName`),
  KEY `roomType_FK` (`roomType`),
  KEY `roomPrice_FK` (`price`),
  CONSTRAINT `roomPrice_FK` FOREIGN KEY (`price`) REFERENCES `roomclass` (`roomPrice`),
  CONSTRAINT `roomType_FK` FOREIGN KEY (`roomType`) REFERENCES `roomclass` (`roomType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('101', '100.00', '小房', '');
INSERT INTO `room` VALUES ('102', '100.00', '小房', '\0');
INSERT INTO `room` VALUES ('103', '100.00', '小房', '');
INSERT INTO `room` VALUES ('104', '100.00', '小房', '\0');
INSERT INTO `room` VALUES ('105', '100.00', '小房', '\0');
INSERT INTO `room` VALUES ('108', '100.00', '小房', '\0');
INSERT INTO `room` VALUES ('119', '100.00', '小房', '\0');
INSERT INTO `room` VALUES ('121', '100.00', '小房', '\0');
INSERT INTO `room` VALUES ('122', '100.00', '小房', '\0');
INSERT INTO `room` VALUES ('133', '100.00', '小房', '\0');
INSERT INTO `room` VALUES ('139', '100.00', '小房', '\0');
INSERT INTO `room` VALUES ('150', '100.00', '小房', '\0');
INSERT INTO `room` VALUES ('166', '100.00', '小房', '\0');
INSERT INTO `room` VALUES ('188', '100.00', '小房', '\0');
INSERT INTO `room` VALUES ('201', '200.00', '中房', '\0');
INSERT INTO `room` VALUES ('202', '200.00', '中房', '\0');
INSERT INTO `room` VALUES ('203', '200.00', '中房', '');
INSERT INTO `room` VALUES ('204', '200.00', '中房', '\0');
INSERT INTO `room` VALUES ('205', '200.00', '中房', '');
INSERT INTO `room` VALUES ('210', '200.00', '中房', '\0');
INSERT INTO `room` VALUES ('220', '200.00', '中房', '\0');
INSERT INTO `room` VALUES ('230', '200.00', '中房', '\0');
INSERT INTO `room` VALUES ('231', '200.00', '中房', '\0');
INSERT INTO `room` VALUES ('249', '200.00', '中房', '\0');
INSERT INTO `room` VALUES ('254', '200.00', '中房', '\0');
INSERT INTO `room` VALUES ('256', '200.00', '中房', '\0');
INSERT INTO `room` VALUES ('274', '200.00', '中房', '\0');
INSERT INTO `room` VALUES ('279', '200.00', '中房', '\0');
INSERT INTO `room` VALUES ('287', '200.00', '中房', '\0');
INSERT INTO `room` VALUES ('301', '300.00', '大房', '');
INSERT INTO `room` VALUES ('302', '300.00', '大房', '\0');
INSERT INTO `room` VALUES ('303', '300.00', '大房', '\0');
INSERT INTO `room` VALUES ('304', '300.00', '大房', '\0');
INSERT INTO `room` VALUES ('305', '300.00', '大房', '');
INSERT INTO `room` VALUES ('308', '300.00', '大房', '\0');
INSERT INTO `room` VALUES ('309', '300.00', '大房', '\0');
INSERT INTO `room` VALUES ('311', '300.00', '大房', '\0');
INSERT INTO `room` VALUES ('312', '300.00', '大房', '\0');
INSERT INTO `room` VALUES ('330', '300.00', '大房', '\0');
INSERT INTO `room` VALUES ('333', '300.00', '大房', '\0');
INSERT INTO `room` VALUES ('350', '300.00', '大房', '\0');
INSERT INTO `room` VALUES ('369', '300.00', '大房', '\0');
INSERT INTO `room` VALUES ('378', '300.00', '大房', '\0');
INSERT INTO `room` VALUES ('379', '300.00', '大房', '\0');
INSERT INTO `room` VALUES ('388', '300.00', '大房', '\0');
INSERT INTO `room` VALUES ('390', '300.00', '大房', '\0');

-- ----------------------------
-- Table structure for roomclass
-- ----------------------------
DROP TABLE IF EXISTS `roomclass`;
CREATE TABLE `roomclass` (
  `roomType` varchar(10) NOT NULL DEFAULT '',
  `roomPrice` double(10,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`roomType`),
  KEY `roomPrice` (`roomPrice`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of roomclass
-- ----------------------------
INSERT INTO `roomclass` VALUES ('小房', '100.00');
INSERT INTO `roomclass` VALUES ('中房', '200.00');
INSERT INTO `roomclass` VALUES ('大房', '300.00');

-- ----------------------------
-- Table structure for service
-- ----------------------------
DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `userName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`userName`),
  UNIQUE KEY `userName_UNIQUE` (`userName`),
  CONSTRAINT `user_FK` FOREIGN KEY (`userName`) REFERENCES `users` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of service
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `name` varchar(12) NOT NULL,
  `password` varchar(12) NOT NULL,
  `roomNumber` int(5) unsigned NOT NULL DEFAULT '0',
  `money` double(20,2) DEFAULT '0.00',
  `time` double(10,1) NOT NULL DEFAULT '0.0',
  `isThrough` bit(1) NOT NULL DEFAULT b'0',
  `sex` varchar(3) DEFAULT '男',
  `tele` varchar(20) DEFAULT '',
  `Id` varchar(20) DEFAULT '',
  `address` varchar(50) DEFAULT '',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '1', '0', '8600.00', '0.0', '\0', '男', '123', '00', '445');
INSERT INTO `users` VALUES ('123', '123', '0', '5700.00', '0.0', '\0', '男', '', null, null);
INSERT INTO `users` VALUES ('2', '2', '205', '1000.00', '2.0', '', '男', '', null, null);
INSERT INTO `users` VALUES ('233', '233', '0', '2000.00', '0.0', '\0', '男', '', null, null);
INSERT INTO `users` VALUES ('33', '33', '0', '0.00', '0.0', '\0', '男', '', null, null);
INSERT INTO `users` VALUES ('456', '456', '0', '700.00', '0.0', '\0', '男', '', '', '');
INSERT INTO `users` VALUES ('789', '789', '0', '800.00', '0.0', '\0', '男', '', '', '');
INSERT INTO `users` VALUES ('两仪式', '123', '0', '0.00', '0.0', '\0', '女', '', null, null);
INSERT INTO `users` VALUES ('两仪未那', '123456', '0', '0.00', '0.0', '\0', '女', '', null, null);
INSERT INTO `users` VALUES ('宫本武藏', '123', '0', '0.00', '0.0', '\0', '男', '', null, null);
INSERT INTO `users` VALUES ('小刚', '123456', '0', '100.00', '0.0', '\0', '男', '', null, null);
INSERT INTO `users` VALUES ('小智', '123456', '301', '410.00', '1.0', '', '男', '', null, null);
INSERT INTO `users` VALUES ('小泽玛利亚', '123456', '0', '0.00', '0.0', '\0', '女', '', null, null);
INSERT INTO `users` VALUES ('小白', '123456789', '0', '9942.00', '0.0', '\0', '男', '', null, null);
INSERT INTO `users` VALUES ('小黑', '123456', '305', '8710.00', '2.0', '', '男', '', null, null);
INSERT INTO `users` VALUES ('弱音', '123', '0', '0.00', '0.0', '\0', '女', '', '', '');
INSERT INTO `users` VALUES ('新垣结衣', '123456', '0', '300.00', '0.0', '\0', '女', '', null, null);
INSERT INTO `users` VALUES ('李华', '123456', '101', '500.00', '2.0', '', '男', '', null, null);
INSERT INTO `users` VALUES ('李小龙', '123456', '0', '1300.00', '0.0', '\0', '男', '', null, null);
INSERT INTO `users` VALUES ('李白', '123456', '0', '200.00', '0.0', '\0', '男', '', null, null);
INSERT INTO `users` VALUES ('桃谷绘里香', '123456', '0', '0.00', '0.0', '\0', '女', '', null, null);
INSERT INTO `users` VALUES ('波多野结衣', '123456', '0', '0.00', '0.0', '\0', '女', '', null, null);
INSERT INTO `users` VALUES ('石原里美', '123456', '0', '100.00', '0.0', '\0', '女', '', null, null);
INSERT INTO `users` VALUES ('老王', '123456', '103', '4700.00', '3.5', '', '男', '', null, null);
INSERT INTO `users` VALUES ('臙条巴', '123', '0', '0.00', '0.0', '\0', '男', '', null, null);
INSERT INTO `users` VALUES ('花泽香菜', '123456', '0', '0.00', '0.0', '\0', '女', '', null, null);
INSERT INTO `users` VALUES ('马云', '123456', '0', '99.00', '0.0', '\0', '男', '', null, null);
INSERT INTO `users` VALUES ('马化腾', '123456789', '203', '8888.00', '1.0', '', '男', '', null, null);
INSERT INTO `users` VALUES ('鬼使白', '123', '0', '0.00', '0.0', '\0', '男', '', null, null);
INSERT INTO `users` VALUES ('鬼使黑', '123', '0', '2000.00', '0.0', '\0', '男', '', null, null);
INSERT INTO `users` VALUES ('黑桐干也', '123', '0', '0.00', '0.0', '\0', '男', '', null, null);
INSERT INTO `users` VALUES ('黑桐鲜花', '123', '0', '0.00', '0.0', '\0', '女', '', null, null);
