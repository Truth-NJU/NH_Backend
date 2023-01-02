/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : nh

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 06/12/2022 15:42:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for nh_archattach
-- ----------------------------
DROP TABLE IF EXISTS `nh_archattach`;
CREATE TABLE `nh_archattach` (
  `id` int NOT NULL AUTO_INCREMENT,
  `daid` int NOT NULL,
  `tpmc` varchar(255) DEFAULT NULL,
  `tplx` varchar(255) DEFAULT NULL,
  `tpcd` int DEFAULT NULL,
  `tpkd` int DEFAULT NULL,
  `tpdx` int DEFAULT NULL,
  `psrq` datetime DEFAULT NULL,
  `px` varchar(255) DEFAULT NULL,
  `tplj` varchar(255) DEFAULT NULL,
  `tplstmc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `daid` (`daid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for nh_archives
-- ----------------------------
DROP TABLE IF EXISTS `nh_archives`;
CREATE TABLE `nh_archives` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bh` varchar(255) DEFAULT NULL,
  `damc` varchar(255) DEFAULT NULL,
  `jm` varchar(255) DEFAULT NULL,
  `wb` varchar(255) DEFAULT NULL,
  `flh` varchar(255) DEFAULT NULL,
  `sy` varchar(255) DEFAULT NULL,
  `gjc` varchar(255) DEFAULT NULL,
  `zrh` varchar(255) DEFAULT NULL,
  `ywdt` varchar(255) DEFAULT NULL,
  `yslh` varchar(255) DEFAULT NULL,
  `xgdy` varchar(255) DEFAULT NULL,
  `gjrw` varchar(255) DEFAULT NULL,
  `szdajh` varchar(255) DEFAULT NULL,
  `fwrq` varchar(255) DEFAULT NULL,
  `swrq` varchar(255) DEFAULT NULL,
  `csrq` varchar(255) DEFAULT NULL,
  `fwjg` varchar(255) DEFAULT NULL,
  `swjg` varchar(255) DEFAULT NULL,
  `gcdw` varchar(255) DEFAULT NULL,
  `hf` int DEFAULT NULL,
  `bclj` varchar(255) DEFAULT NULL,
  `wzjg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for nh_archives_text
-- ----------------------------
DROP TABLE IF EXISTS `nh_archives_text`;
CREATE TABLE `nh_archives_text` (
  `id` int NOT NULL AUTO_INCREMENT,
  `daid` int DEFAULT NULL,
  `damc` varchar(255) DEFAULT NULL,
  `dayw` text(65535) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for nh_archremark
-- ----------------------------
DROP TABLE IF EXISTS `nh_archremark`;
CREATE TABLE `nh_archremark` (
  `id` int NOT NULL AUTO_INCREMENT,
  `archid` int DEFAULT NULL,
  `archname` varchar(255) DEFAULT NULL,
  `remarkname` varchar(255) DEFAULT NULL,
  `remarktime` datetime DEFAULT NULL,
  `remarkcontent` varchar(255) DEFAULT NULL,
  `isview` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for nh_classes
-- ----------------------------
DROP TABLE IF EXISTS `nh_classes`;
CREATE TABLE `nh_classes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `classid` varchar(255) DEFAULT NULL,
  `classcn` varchar(255) DEFAULT NULL,
  `parentid` int DEFAULT NULL,
  `classdes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for nh_fullquerylog
-- ----------------------------
DROP TABLE IF EXISTS `nh_fullquerylog`;
CREATE TABLE `nh_fullquerylog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `querybatch` varchar(255) DEFAULT NULL,
  `hotword` varchar(255) DEFAULT NULL,
  `querytime` datetime DEFAULT NULL,
  `queryman` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for nh_org
-- ----------------------------
DROP TABLE IF EXISTS `nh_org`;
CREATE TABLE `nh_org` (
  `id` int NOT NULL AUTO_INCREMENT,
  `orgname` varchar(255) DEFAULT NULL,
  `parentid` int DEFAULT NULL,
  `orgorder` varchar(255) DEFAULT NULL,
  `orgdes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for nh_user
-- ----------------------------
DROP TABLE IF EXISTS `nh_user`;
CREATE TABLE `nh_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_mobile` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `realName` varchar(255) DEFAULT NULL,
  `user_status` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_pic` varchar(255) DEFAULT NULL,
  `team_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
