/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50512
Source Host           : localhost:3306
Source Database       : blog_common

Target Server Type    : MYSQL
Target Server Version : 50512
File Encoding         : 65001

Date: 2011-08-10 07:56:23
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `topic_at_user`
-- ----------------------------
DROP TABLE IF EXISTS `topic_at_user`;
CREATE TABLE `topic_at_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL,
  `tid` bigint(20) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of topic_at_user
-- ----------------------------
