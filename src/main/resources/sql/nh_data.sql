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

 Date: 26/12/2022 16:21:34
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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for nh_archives_text
-- ----------------------------
DROP TABLE IF EXISTS `nh_archives_text`;
CREATE TABLE `nh_archives_text` (
  `id` int NOT NULL AUTO_INCREMENT,
  `daid` int DEFAULT NULL,
  `damc` varchar(255) DEFAULT NULL,
  `dayw` mediumtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
-- Records of nh_archremark
-- ----------------------------
BEGIN;
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of nh_classes
-- ----------------------------
BEGIN;
INSERT INTO `nh_classes` VALUES (1, 'A', '南海综合', -1, '');
INSERT INTO `nh_classes` VALUES (2, 'A11', '自然概况', 1, '');
INSERT INTO `nh_classes` VALUES (3, 'A11.1', '南海地理、地貌、地质', 2, '');
INSERT INTO `nh_classes` VALUES (4, 'A11.2', '南海水文、气象', 2, '');
INSERT INTO `nh_classes` VALUES (5, 'A11.3', '南海海产、物产', 2, '');
INSERT INTO `nh_classes` VALUES (6, 'A11.4', '南海矿产、油气', 2, '');
INSERT INTO `nh_classes` VALUES (7, 'A12', '国家安全和战略研究', 1, '');
INSERT INTO `nh_classes` VALUES (8, 'A13', '国际政治和外交', 1, '');
INSERT INTO `nh_classes` VALUES (9, 'A13.1', '中国与周边国家关系', 8, '');
INSERT INTO `nh_classes` VALUES (10, 'A13.2', '各国对南海诸岛的主权论述', 8, '');
INSERT INTO `nh_classes` VALUES (11, 'A13.21', '南中国海主权', 10, '');
INSERT INTO `nh_classes` VALUES (12, 'A13.22', '南海各小岛主权', 10, '');
INSERT INTO `nh_classes` VALUES (13, 'A14', '国际法、条约、海洋法', 1, '');
INSERT INTO `nh_classes` VALUES (14, 'A14.1', '国际法', 13, '');
INSERT INTO `nh_classes` VALUES (15, 'A14.2', '条约', 13, '');
INSERT INTO `nh_classes` VALUES (16, 'A14.3', '海洋法', 13, '');
INSERT INTO `nh_classes` VALUES (17, 'A14.21', '旧金山会议', 15, '');
INSERT INTO `nh_classes` VALUES (18, 'A14.22', '对日和约', 15, '');
INSERT INTO `nh_classes` VALUES (19, 'A14.23', '开罗会议', 15, '');
INSERT INTO `nh_classes` VALUES (20, 'A14.24', '波茨坦公告', 15, '');
INSERT INTO `nh_classes` VALUES (21, 'A14.25', '中法界务', 15, '');
INSERT INTO `nh_classes` VALUES (22, 'A14.26', '美菲协定', 15, '');
INSERT INTO `nh_classes` VALUES (23, 'A14.27', '雅尔达秘密协定', 15, '');
INSERT INTO `nh_classes` VALUES (24, 'A14.28', '中英条约和四国宣言', 15, '');
INSERT INTO `nh_classes` VALUES (25, 'A14.29', '中苏同盟条约', 15, '');
INSERT INTO `nh_classes` VALUES (26, 'A15', '疆域', 1, '');
INSERT INTO `nh_classes` VALUES (27, 'A15.1', '海疆', 26, '');
INSERT INTO `nh_classes` VALUES (28, 'A15.11', '领海海界', 27, '');
INSERT INTO `nh_classes` VALUES (29, 'A15.12', '海防', 27, '');
INSERT INTO `nh_classes` VALUES (30, 'A15.11.1', '国内规划领海界线', 28, '');
INSERT INTO `nh_classes` VALUES (31, 'A15.11.2', '海洋渔业规划', 28, '');
INSERT INTO `nh_classes` VALUES (32, 'A15.11.3', '中菲领海海界', 28, '');
INSERT INTO `nh_classes` VALUES (33, 'A15.2', '边界', 26, '');
INSERT INTO `nh_classes` VALUES (34, 'A15.21', '新蒙边界', 33, '');
INSERT INTO `nh_classes` VALUES (35, 'A15.22', '疆界会议', 33, '');
INSERT INTO `nh_classes` VALUES (36, 'A15.3', '海疆界地图', 26, '');
INSERT INTO `nh_classes` VALUES (37, 'A15.31', '地图审查', 36, '');
INSERT INTO `nh_classes` VALUES (38, 'A15.32', '海疆界图', 36, '');
INSERT INTO `nh_classes` VALUES (39, 'A16', '海洋史', 1, '');
INSERT INTO `nh_classes` VALUES (40, 'A16.1', '海洋交通史', 39, '');
INSERT INTO `nh_classes` VALUES (41, 'A16.2', '海洋发展史', 39, '');
INSERT INTO `nh_classes` VALUES (42, 'A16.3', '海洋贸易史', 39, '');
INSERT INTO `nh_classes` VALUES (43, 'A17', '南海诸群岛地图', 1, '');
INSERT INTO `nh_classes` VALUES (44, 'A17.1', '中国出版的地图', 43, '');
INSERT INTO `nh_classes` VALUES (45, 'A17.11', '清代前地图', 44, '');
INSERT INTO `nh_classes` VALUES (46, 'A17.12', '民国地图', 44, '');
INSERT INTO `nh_classes` VALUES (47, 'A17.13', '新中国地图', 44, '');
INSERT INTO `nh_classes` VALUES (48, 'A17.2', '日本出版的地图', 43, '');
INSERT INTO `nh_classes` VALUES (49, 'A17.3', '越南出版的地图', 43, '');
INSERT INTO `nh_classes` VALUES (50, 'A17.4', '西方出版的地图', 43, '');
INSERT INTO `nh_classes` VALUES (51, 'A17.5', '前苏联及东欧诸国的南海地图', 43, '');
INSERT INTO `nh_classes` VALUES (52, 'B', '北部湾', -1, '');
INSERT INTO `nh_classes` VALUES (53, 'B11', '海南岛', 52, '');
INSERT INTO `nh_classes` VALUES (54, 'B11.1', '海南自然情况', 53, '');
INSERT INTO `nh_classes` VALUES (55, 'B11.2', '海南历史方志', 53, '');
INSERT INTO `nh_classes` VALUES (56, 'B11.3', '海南岛军事与海防', 53, '');
INSERT INTO `nh_classes` VALUES (57, 'B11.4', '海南岛特别行政区', 53, '');
INSERT INTO `nh_classes` VALUES (58, 'B12', '琉璃群岛', 52, '');
INSERT INTO `nh_classes` VALUES (59, 'B12.1', '台湾及中南半岛', 58, '');
INSERT INTO `nh_classes` VALUES (60, 'B12.11', '史料', 59, '');
INSERT INTO `nh_classes` VALUES (61, 'B12.12', '经济', 59, '');
INSERT INTO `nh_classes` VALUES (62, 'B12.2', '澎湖列岛', 58, '');
INSERT INTO `nh_classes` VALUES (63, 'B12.3', '钓鱼台问题', 58, '');
INSERT INTO `nh_classes` VALUES (64, 'B13', '其他环北部湾地区', 52, '');
INSERT INTO `nh_classes` VALUES (65, 'B14', '中越北部湾地区陆界与海界问题', 52, '');
INSERT INTO `nh_classes` VALUES (66, 'C', '东沙群岛', -1, '');
INSERT INTO `nh_classes` VALUES (67, 'C11', '东沙建设', 66, '');
INSERT INTO `nh_classes` VALUES (68, 'C11.1', '灯塔', 67, '');
INSERT INTO `nh_classes` VALUES (69, 'C11.2', '机场', 67, '');
INSERT INTO `nh_classes` VALUES (70, 'C11.3', '基地', 67, '');
INSERT INTO `nh_classes` VALUES (71, 'C11.4', '观象台建设', 67, '');
INSERT INTO `nh_classes` VALUES (72, 'C11.5', '无线电台', 67, '');
INSERT INTO `nh_classes` VALUES (73, 'C12', '东沙防务', 66, '');
INSERT INTO `nh_classes` VALUES (74, 'C12.1', '海军巡弋', 73, '');
INSERT INTO `nh_classes` VALUES (75, 'C12.2', '后勤支援', 73, '');
INSERT INTO `nh_classes` VALUES (76, 'D', '西沙群岛', -1, '');
INSERT INTO `nh_classes` VALUES (77, 'D11', '自然概况', 76, '');
INSERT INTO `nh_classes` VALUES (78, 'D11.1', '地理、地貌、地质', 77, '');
INSERT INTO `nh_classes` VALUES (79, 'D11.2', '水文、气象', 77, '');
INSERT INTO `nh_classes` VALUES (80, 'D11.3', '海产、物产', 77, '');
INSERT INTO `nh_classes` VALUES (81, 'D11.4', '油气资源与生产', 77, '');
INSERT INTO `nh_classes` VALUES (82, 'D12', '西沙建设', 76, '');
INSERT INTO `nh_classes` VALUES (83, 'D12.1', '气象台、灯塔', 82, '');
INSERT INTO `nh_classes` VALUES (84, 'D12.2', '国营磷矿区', 82, '');
INSERT INTO `nh_classes` VALUES (85, 'D12.3', '鸟粪事', 82, '');
INSERT INTO `nh_classes` VALUES (86, 'D12.4', '华侨建设', 82, '');
INSERT INTO `nh_classes` VALUES (87, 'D13', '西沙防务', 76, '');
INSERT INTO `nh_classes` VALUES (88, 'D14', '西沙主权', 76, '');
INSERT INTO `nh_classes` VALUES (89, 'D14.1', '中日部分', 88, '');
INSERT INTO `nh_classes` VALUES (90, 'D14.2', '中法部分', 88, '');
INSERT INTO `nh_classes` VALUES (91, 'D14.21', '法占九小岛', 90, '');
INSERT INTO `nh_classes` VALUES (92, 'D14.22', '法舰觊觎西沙', 90, '');
INSERT INTO `nh_classes` VALUES (93, 'D14.3', '越南部分', 88, '');
INSERT INTO `nh_classes` VALUES (94, 'D14.31', '越共西沙之战', 93, '');
INSERT INTO `nh_classes` VALUES (95, 'D14.32', '越南侵占西沙史实', 93, '');
INSERT INTO `nh_classes` VALUES (96, 'D14.4', '中共主权声明', 88, '');
INSERT INTO `nh_classes` VALUES (97, 'D14.5', '国民政府主权声明', 88, '');
INSERT INTO `nh_classes` VALUES (98, 'D15', '地理位置及范围', 76, '');
INSERT INTO `nh_classes` VALUES (99, 'F', '南沙群岛', -1, '');
INSERT INTO `nh_classes` VALUES (100, 'F11', '自然概况', 99, '');
INSERT INTO `nh_classes` VALUES (101, 'F11.1', '地理、地貌、地质', 100, '');
INSERT INTO `nh_classes` VALUES (102, 'F11.2', '水文、气象', 100, '');
INSERT INTO `nh_classes` VALUES (103, 'F11.3', '海产、物产', 100, '');
INSERT INTO `nh_classes` VALUES (104, 'F11.4', '油气、矿气资源', 100, '');
INSERT INTO `nh_classes` VALUES (105, 'F12', '南沙建设', 99, '');
INSERT INTO `nh_classes` VALUES (106, 'F12.1', '南沙开发计划', 105, '');
INSERT INTO `nh_classes` VALUES (107, 'F13', '南沙防务', 99, '');
INSERT INTO `nh_classes` VALUES (108, 'F13.1', '南沙海军巡弋', 107, '');
INSERT INTO `nh_classes` VALUES (109, 'F14', '南沙主权', 99, '');
INSERT INTO `nh_classes` VALUES (110, 'F14.1', '中越部分', 109, '');
INSERT INTO `nh_classes` VALUES (111, 'F14.11', '开发南沙群岛事', 110, '');
INSERT INTO `nh_classes` VALUES (112, 'F14.12', '越南攻占、入侵南沙史料', 110, '');
INSERT INTO `nh_classes` VALUES (113, 'F14.13', '南沙划入版图事', 110, '');
INSERT INTO `nh_classes` VALUES (114, 'F14.14', '越南对南沙外交', 110, '');
INSERT INTO `nh_classes` VALUES (115, 'F14.2', '中菲部分', 109, '');
INSERT INTO `nh_classes` VALUES (116, 'F14.21', '科洛马事件', 115, '');
INSERT INTO `nh_classes` VALUES (117, 'F14.22', '人道王国事', 115, '');
INSERT INTO `nh_classes` VALUES (118, 'F14.23', '南沙国旗事', 115, '');
INSERT INTO `nh_classes` VALUES (119, 'F14.24', '菲舰入侵', 115, '');
INSERT INTO `nh_classes` VALUES (120, 'F14.25', '中菲渔业协定', 115, '');
INSERT INTO `nh_classes` VALUES (121, 'F14.26', '米特拉事件', 115, '');
INSERT INTO `nh_classes` VALUES (122, 'F14.27', '克鲁玛事件与“自由地国”', 115, '');
INSERT INTO `nh_classes` VALUES (123, 'F14.28', '马克仕记者招待会', 115, '');
INSERT INTO `nh_classes` VALUES (124, 'F14.29', '中菲渔船', 115, '');
INSERT INTO `nh_classes` VALUES (125, 'F14.2a', '南沙群岛事', 115, '');
INSERT INTO `nh_classes` VALUES (126, 'F14.3', '中法部分', 109, '');
INSERT INTO `nh_classes` VALUES (127, 'F14.31', '中法界务', 126, '');
INSERT INTO `nh_classes` VALUES (128, 'F14.4', '中日部分', 109, '');
INSERT INTO `nh_classes` VALUES (129, 'F14.41', '买卖南沙群岛磷矿事', 128, '');
INSERT INTO `nh_classes` VALUES (130, 'F14.42', '日营南沙经过', 128, '');
INSERT INTO `nh_classes` VALUES (131, 'F14.5', '国民政府对南沙主权', 109, '');
INSERT INTO `nh_classes` VALUES (132, 'F14.6', '中共对南沙主权', 109, '');
INSERT INTO `nh_classes` VALUES (133, 'F15', '地理位置及范围', 99, '');
INSERT INTO `nh_classes` VALUES (134, 'F15.1', '南沙诸岛地图', 133, '');
INSERT INTO `nh_classes` VALUES (135, 'F16', '南沙史料', 99, '');
INSERT INTO `nh_classes` VALUES (136, 'F16.1', '南沙群岛说贴、资料', 135, '');
INSERT INTO `nh_classes` VALUES (137, 'F16.2', '南沙地志', 135, '');
INSERT INTO `nh_classes` VALUES (138, 'F16.3', '麦蕴瑜资料', 135, '');
INSERT INTO `nh_classes` VALUES (139, 'G', '西南沙群岛', -1, '');
INSERT INTO `nh_classes` VALUES (140, 'G11', '进驻西南沙群岛', 139, '');
INSERT INTO `nh_classes` VALUES (141, 'G11.1', '开发计划、会议筹备', 140, '');
INSERT INTO `nh_classes` VALUES (142, 'G11.2', '探测勘察', 140, '');
INSERT INTO `nh_classes` VALUES (143, 'G11.21', '勘察自然情况', 142, '');
INSERT INTO `nh_classes` VALUES (144, 'G11.22', '调查人为情况', 142, '');
INSERT INTO `nh_classes` VALUES (145, 'G11.3', '西南沙防务', 140, '');
INSERT INTO `nh_classes` VALUES (146, 'G11.31', '海军巡弋', 145, '');
INSERT INTO `nh_classes` VALUES (147, 'G11.32', '后勤支援', 145, '');
INSERT INTO `nh_classes` VALUES (148, 'G12', '西南沙群岛撤退', 139, '');
INSERT INTO `nh_classes` VALUES (149, 'G13', '西南沙主权之争执', 139, '');
INSERT INTO `nh_classes` VALUES (150, 'G13.1', '越南外交', 149, '');
INSERT INTO `nh_classes` VALUES (151, 'G13.2', '中共外交', 149, '');
INSERT INTO `nh_classes` VALUES (152, 'G13.3', '国民政府外交', 149, '');
INSERT INTO `nh_classes` VALUES (153, 'G13.4', '美国保沙运动', 149, '');
INSERT INTO `nh_classes` VALUES (154, 'G14', '西南沙群岛地理位置及范围', 139, '');
INSERT INTO `nh_classes` VALUES (155, 'G14.1', '岛屿地名', 154, '');
INSERT INTO `nh_classes` VALUES (156, 'G14.2', '地图、地理范围', 154, '');
INSERT INTO `nh_classes` VALUES (157, 'H', '新南群岛', -1, '');
INSERT INTO `nh_classes` VALUES (158, 'H11', '自然资源', 157, '');
INSERT INTO `nh_classes` VALUES (159, 'H12', '主权', 157, '');
INSERT INTO `nh_classes` VALUES (160, 'H13', '地理位置及范围', 157, '');
INSERT INTO `nh_classes` VALUES (161, 'I', '其他', -1, '');
INSERT INTO `nh_classes` VALUES (162, 'I11', '东海', 161, '');
INSERT INTO `nh_classes` VALUES (163, 'I12', '黄海', 161, '');
INSERT INTO `nh_classes` VALUES (164, 'I13', '大陆地区', 161, '');
INSERT INTO `nh_classes` VALUES (165, 'I13.1', '南鹏岛', 164, '');
INSERT INTO `nh_classes` VALUES (166, 'I13.2', '长山八岛', 164, '');
INSERT INTO `nh_classes` VALUES (167, 'I13.3', '小横琴岛', 164, '');
INSERT INTO `nh_classes` VALUES (168, 'I13.4', '秦皇岛', 164, '');
INSERT INTO `nh_classes` VALUES (169, 'I14', '与南海关系稍远的主题', 161, '');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of nh_fullquerylog
-- ----------------------------
BEGIN;
INSERT INTO `nh_fullquerylog` VALUES (1, '1672030337259', '司令', '2022-12-26 12:52:17', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (2, '1672030455759', '岛', '2022-12-26 12:54:16', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (3, '1672030518269', '岛', '2022-12-26 12:55:18', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (4, '1672031136117', '岛', '2022-12-26 13:05:36', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (5, '1672031143400', '司令', '2022-12-26 13:05:43', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (6, '1672031147867', '司', '2022-12-26 13:05:48', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (7, '1672031157293', '岛', '2022-12-26 13:05:57', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (8, '1672031184647', '关', '2022-12-26 13:06:25', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (9, '1672031214975', '很', '2022-12-26 13:06:55', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (10, '1672031226226', '呈', '2022-12-26 13:07:06', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (11, '1672031226962', '呈', '2022-12-26 13:07:07', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (12, '1672031227162', '呈', '2022-12-26 13:07:07', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (13, '1672031401446', '海军', '2022-12-26 13:10:01', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (14, '1672031726020', '海军', '2022-12-26 13:15:26', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (15, '1672032064698', '司令', '2022-12-26 13:21:05', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (16, '1672032453621', '岛', '2022-12-26 13:27:34', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (17, '1672032456951', '日', '2022-12-26 13:27:37', 'tzh');
INSERT INTO `nh_fullquerylog` VALUES (18, '1672032459444', '岛', '2022-12-26 13:27:39', 'tzh');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=474 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of nh_org
-- ----------------------------
BEGIN;
INSERT INTO `nh_org` VALUES (1, '清政府', -1, 'A', '清政府');
INSERT INTO `nh_org` VALUES (2, '外务部', 1, '01', '外务部');
INSERT INTO `nh_org` VALUES (3, '和会司', 2, '01', '和会司');
INSERT INTO `nh_org` VALUES (4, '考公司', 2, '02', '考公司');
INSERT INTO `nh_org` VALUES (5, '榷算司', 2, '03', '榷算司');
INSERT INTO `nh_org` VALUES (6, '庶务司', 2, '04', '庶务司');
INSERT INTO `nh_org` VALUES (7, '北洋政府', -1, 'B', '北洋政府');
INSERT INTO `nh_org` VALUES (8, '国务院', 7, '01', '国务院');
INSERT INTO `nh_org` VALUES (9, '财政部', 7, '02', '财政部');
INSERT INTO `nh_org` VALUES (10, '外交部', 7, '03', '外交部');
INSERT INTO `nh_org` VALUES (11, '海军部', 7, '04', '海军部');
INSERT INTO `nh_org` VALUES (12, '内务部', 7, '05', '内务部');
INSERT INTO `nh_org` VALUES (13, '秘书厅', 8, '01', '秘书厅');
INSERT INTO `nh_org` VALUES (14, '法制局', 8, '02', '法制局');
INSERT INTO `nh_org` VALUES (15, '铨叙局', 8, '03', '铨叙局');
INSERT INTO `nh_org` VALUES (16, '总务厅', 9, '01', '总务厅');
INSERT INTO `nh_org` VALUES (17, '赋税司', 9, '02', '赋税司');
INSERT INTO `nh_org` VALUES (18, '会计司', 9, '03', '会计司');
INSERT INTO `nh_org` VALUES (19, '泉币司', 9, '04', '泉币司');
INSERT INTO `nh_org` VALUES (20, '公债司', 9, '05', '公债司');
INSERT INTO `nh_org` VALUES (21, '库藏司', 9, '06', '库藏司');
INSERT INTO `nh_org` VALUES (22, '盐务署', 9, '07', '盐务署');
INSERT INTO `nh_org` VALUES (23, '烟酒署', 9, '08', '烟酒署');
INSERT INTO `nh_org` VALUES (24, '印花税处', 9, '09', '印花税处');
INSERT INTO `nh_org` VALUES (25, '官产处', 9, '10', '官产处');
INSERT INTO `nh_org` VALUES (26, '总务厅', 10, '01', '总务厅');
INSERT INTO `nh_org` VALUES (27, '政务司', 10, '02', '政务司');
INSERT INTO `nh_org` VALUES (28, '交际司', 10, '03', '交际司');
INSERT INTO `nh_org` VALUES (29, '外政司', 10, '04', '外政司');
INSERT INTO `nh_org` VALUES (30, '通商司', 10, '05', '通商司');
INSERT INTO `nh_org` VALUES (31, '条约司', 10, '06', '条约司');
INSERT INTO `nh_org` VALUES (32, '庶政司', 10, '07', '庶政司');
INSERT INTO `nh_org` VALUES (33, '参事处', 11, '01', '参事处（参事厅）');
INSERT INTO `nh_org` VALUES (34, '总务厅', 11, '02', '总务厅');
INSERT INTO `nh_org` VALUES (35, '机要科', 34, '01', '机要科');
INSERT INTO `nh_org` VALUES (36, '编纂科', 34, '02', '编纂科');
INSERT INTO `nh_org` VALUES (37, '统计科', 34, '03', '统计科');
INSERT INTO `nh_org` VALUES (38, '庶务科', 34, '04', '庶务科');
INSERT INTO `nh_org` VALUES (39, '副官处', 34, '05', '副官处');
INSERT INTO `nh_org` VALUES (40, '视察室', 34, '06', '视察室');
INSERT INTO `nh_org` VALUES (41, '军衡司', 11, '03', '军衡司');
INSERT INTO `nh_org` VALUES (42, '任官科', 41, '01', '任官科');
INSERT INTO `nh_org` VALUES (43, '赏赉科', 41, '02', '赏赉科');
INSERT INTO `nh_org` VALUES (44, '考核科', 41, '03', '考核科');
INSERT INTO `nh_org` VALUES (45, '司法科', 41, '04', '司法科');
INSERT INTO `nh_org` VALUES (46, '典制科', 41, '05', '典制科');
INSERT INTO `nh_org` VALUES (47, '军务司', 11, '04', '军务司');
INSERT INTO `nh_org` VALUES (48, '典制科', 47, '01', '典制科');
INSERT INTO `nh_org` VALUES (49, '军事科', 47, '02', '军事科');
INSERT INTO `nh_org` VALUES (50, '测绘科', 47, '03', '测绘科');
INSERT INTO `nh_org` VALUES (51, '医务科', 47, '04', '医务科');
INSERT INTO `nh_org` VALUES (52, '电政科', 47, '05', '电政科');
INSERT INTO `nh_org` VALUES (53, '军械司', 11, '05', '军械司');
INSERT INTO `nh_org` VALUES (54, '兵器科', 53, '01', '兵器科');
INSERT INTO `nh_org` VALUES (55, '舰政科', 53, '02', '舰政科');
INSERT INTO `nh_org` VALUES (56, '机器科', 53, '03', '机器科');
INSERT INTO `nh_org` VALUES (57, '设备科', 53, '04', '设备科');
INSERT INTO `nh_org` VALUES (59, '军需司', 11, '06', '军需司');
INSERT INTO `nh_org` VALUES (60, '司计科', 59, '01', '司计科');
INSERT INTO `nh_org` VALUES (61, '经理科', 59, '02', '经理科');
INSERT INTO `nh_org` VALUES (62, '储备科', 59, '03', '储备科');
INSERT INTO `nh_org` VALUES (63, '稽核科', 59, '04', '稽核科');
INSERT INTO `nh_org` VALUES (64, '军学司', 11, '07', '军学司');
INSERT INTO `nh_org` VALUES (65, '航海科', 64, '01', '航海科');
INSERT INTO `nh_org` VALUES (66, '轮机科', 64, '02', '轮机科');
INSERT INTO `nh_org` VALUES (67, '士兵科', 64, '03', '士兵科');
INSERT INTO `nh_org` VALUES (68, '编译科', 64, '04', '编译科');
INSERT INTO `nh_org` VALUES (69, '军法司', 11, '08', '军法司');
INSERT INTO `nh_org` VALUES (70, '审检科', 69, '01', '审检科');
INSERT INTO `nh_org` VALUES (71, '法学科', 69, '02', '法学科');
INSERT INTO `nh_org` VALUES (72, '典狱科', 69, '03', '典狱科');
INSERT INTO `nh_org` VALUES (73, '技正室', 11, '09', '技正室');
INSERT INTO `nh_org` VALUES (74, '海军总司令处', 11, '10', '海军总司令处/海军总司令公署');
INSERT INTO `nh_org` VALUES (75, '总务厅', 12, '01', '总务厅');
INSERT INTO `nh_org` VALUES (76, '参事室', 12, '02', '参事室');
INSERT INTO `nh_org` VALUES (77, '民治司', 12, '03', '民治司');
INSERT INTO `nh_org` VALUES (78, '警政司', 12, '04', '警政司');
INSERT INTO `nh_org` VALUES (79, '职方司', 12, '05', '职方司');
INSERT INTO `nh_org` VALUES (80, '土木司', 12, '06', '土木司');
INSERT INTO `nh_org` VALUES (81, '礼俗司', 12, '07', '礼俗司');
INSERT INTO `nh_org` VALUES (82, '卫生司', 12, '08', '卫生司');
INSERT INTO `nh_org` VALUES (83, '典礼司', 12, '09', '典礼司');
INSERT INTO `nh_org` VALUES (84, '中华民国', -1, 'C', '中华民国');
INSERT INTO `nh_org` VALUES (85, '国民大会', 84, '01', '国民大会');
INSERT INTO `nh_org` VALUES (86, '国民政府', 84, '02', '国民政府/总统府');
INSERT INTO `nh_org` VALUES (87, '筹备委员会', 85, '01', '筹备委员会');
INSERT INTO `nh_org` VALUES (88, '秘书处', 87, '01', '秘书处');
INSERT INTO `nh_org` VALUES (89, '招待处', 87, '02', '招待处');
INSERT INTO `nh_org` VALUES (90, '警卫处', 87, '03', '警卫处');
INSERT INTO `nh_org` VALUES (91, '秘书处', 86, '01', '秘书处');
INSERT INTO `nh_org` VALUES (92, '副官处', 86, '02', '副官处');
INSERT INTO `nh_org` VALUES (93, '印铸局', 86, '03', '印铸局');
INSERT INTO `nh_org` VALUES (94, '参事厅', 86, '04', '参事厅');
INSERT INTO `nh_org` VALUES (95, '文官处', 86, '05', '文官处');
INSERT INTO `nh_org` VALUES (96, '参军处', 86, '06', '参军处');
INSERT INTO `nh_org` VALUES (97, '立法院', 86, '07', '立法院');
INSERT INTO `nh_org` VALUES (98, '行政院', 86, '08', '行政院');
INSERT INTO `nh_org` VALUES (99, '司法院', 86, '09', '司法院');
INSERT INTO `nh_org` VALUES (100, '考试院', 86, '10', '考试院');
INSERT INTO `nh_org` VALUES (101, '监察院', 86, '11', '监察院');
INSERT INTO `nh_org` VALUES (102, '各委员会', 86, '12', '各委员会');
INSERT INTO `nh_org` VALUES (103, '参谋本部', 86, '13', '参谋本部');
INSERT INTO `nh_org` VALUES (104, '训练总监部', 86, '14', '训练总监部');
INSERT INTO `nh_org` VALUES (105, '军事参议院', 86, '15', '军事参议院');
INSERT INTO `nh_org` VALUES (106, '中央研究院', 86, '16', '中央研究院');
INSERT INTO `nh_org` VALUES (107, '主计处', 86, '17', '主计处');
INSERT INTO `nh_org` VALUES (108, '军事委员会', 86, '18', '军事委员会');
INSERT INTO `nh_org` VALUES (109, '6个局', 86, '19', '6个局');
INSERT INTO `nh_org` VALUES (110, '机要室', 86, '20', '机要室');
INSERT INTO `nh_org` VALUES (111, '侍卫室', 86, '21', '侍卫室');
INSERT INTO `nh_org` VALUES (112, '中央研究院', 86, '22', '中央研究院');
INSERT INTO `nh_org` VALUES (113, '国史馆', 86, '23', '国史馆');
INSERT INTO `nh_org` VALUES (114, '国父陵园管理委员会', 86, '24', '国父陵园管理委员会');
INSERT INTO `nh_org` VALUES (115, '稽勋委员会', 86, '25', '稽勋委员会');
INSERT INTO `nh_org` VALUES (116, '战略顾问委员会', 86, '26', '战略顾问委员会');
INSERT INTO `nh_org` VALUES (117, '戡乱建国动员委员会', 86, '27', '戡乱建国动员委员会');
INSERT INTO `nh_org` VALUES (118, '中央银行', 86, '28', '中央银行');
INSERT INTO `nh_org` VALUES (119, '秘书处', 97, '01', '秘书处');
INSERT INTO `nh_org` VALUES (120, '编译处', 97, '02', '编译处');
INSERT INTO `nh_org` VALUES (121, '人事室', 97, '03', '人事室');
INSERT INTO `nh_org` VALUES (122, '会议室', 97, '04', '会议室');
INSERT INTO `nh_org` VALUES (123, '统计室', 97, '05', '统计室');
INSERT INTO `nh_org` VALUES (124, '各委员会', 97, '06', '各委员会');
INSERT INTO `nh_org` VALUES (125, '编纂处', 97, '07', '编纂处');
INSERT INTO `nh_org` VALUES (126, '会计处', 97, '08', '会计处');
INSERT INTO `nh_org` VALUES (127, '图书馆', 97, '09', '图书馆');
INSERT INTO `nh_org` VALUES (128, '总务厅', 103, '01', '总务厅');
INSERT INTO `nh_org` VALUES (129, '第一厅', 103, '02', '第一厅');
INSERT INTO `nh_org` VALUES (130, '第二厅', 103, '03', '第二厅');
INSERT INTO `nh_org` VALUES (131, '第三厅', 103, '04', '第三厅');
INSERT INTO `nh_org` VALUES (132, '边务组', 103, '05', '边务组');
INSERT INTO `nh_org` VALUES (133, '国防设计委员会', 103, '06', '国防设计委员会');
INSERT INTO `nh_org` VALUES (134, '天文研究所', 106, '01', '天文研究所');
INSERT INTO `nh_org` VALUES (135, '物理研究所', 106, '02', '物理研究所');
INSERT INTO `nh_org` VALUES (136, '地质研究所', 106, '03', '地质研究所');
INSERT INTO `nh_org` VALUES (137, '植物研究所', 106, '04', '植物研究所');
INSERT INTO `nh_org` VALUES (138, '气象研究所', 106, '05', '气象研究所');
INSERT INTO `nh_org` VALUES (139, '历史语言研究所', 106, '06', '历史语言研究所');
INSERT INTO `nh_org` VALUES (140, '医学研究所', 106, '07', '医学研究所');
INSERT INTO `nh_org` VALUES (141, '工程研究所', 106, '08', '工程研究所');
INSERT INTO `nh_org` VALUES (142, '心理研究所', 106, '09', '心理研究所');
INSERT INTO `nh_org` VALUES (143, '体质人类学研究所', 106, '10', '体质人类学研究所');
INSERT INTO `nh_org` VALUES (144, '发行局', 118, '01', '发行局');
INSERT INTO `nh_org` VALUES (145, '业务局', 118, '02', '业务局');
INSERT INTO `nh_org` VALUES (146, '国库局', 118, '03', '国库局');
INSERT INTO `nh_org` VALUES (147, '汇兑信托局', 118, '04', '汇兑信托局');
INSERT INTO `nh_org` VALUES (148, '秘书处', 118, '05', '秘书处');
INSERT INTO `nh_org` VALUES (149, '总务处', 118, '06', '总务处');
INSERT INTO `nh_org` VALUES (150, '医务处', 118, '07', '医务处');
INSERT INTO `nh_org` VALUES (151, '人事处', 118, '08', '人事处');
INSERT INTO `nh_org` VALUES (152, '稽核处', 118, '09', '稽核处');
INSERT INTO `nh_org` VALUES (153, '经济研究处', 118, '10', '经济研究处');
INSERT INTO `nh_org` VALUES (154, '县乡银行业务督导处', 118, '11', '县乡银行业务督导处');
INSERT INTO `nh_org` VALUES (155, '金融机构业务检查处', 118, '12', '金融机构业务检查处');
INSERT INTO `nh_org` VALUES (156, '各委员会', 118, '13', '各委员会');
INSERT INTO `nh_org` VALUES (157, '内政部', 98, '01', '内政部');
INSERT INTO `nh_org` VALUES (158, '外交部', 98, '02', '外交部');
INSERT INTO `nh_org` VALUES (159, '军政部', 98, '03', '军政部');
INSERT INTO `nh_org` VALUES (160, '海军部', 98, '04', '海军部');
INSERT INTO `nh_org` VALUES (161, '国防部', 98, '05', '国防部');
INSERT INTO `nh_org` VALUES (162, '财政部', 98, '06', '财政部');
INSERT INTO `nh_org` VALUES (163, '农矿部', 98, '07', '农矿部');
INSERT INTO `nh_org` VALUES (164, '工商部', 98, '08', '工商部');
INSERT INTO `nh_org` VALUES (165, '实业部', 98, '09', '实业部');
INSERT INTO `nh_org` VALUES (166, '经济部', 98, '10', '经济部');
INSERT INTO `nh_org` VALUES (167, '农林部', 98, '11', '农林部');
INSERT INTO `nh_org` VALUES (168, '水利部/水利委员会', 98, '12', '水利部/水利委员会');
INSERT INTO `nh_org` VALUES (169, '教育部', 98, '13', '教育部');
INSERT INTO `nh_org` VALUES (170, '交通部', 98, '14', '交通部');
INSERT INTO `nh_org` VALUES (171, '铁道部', 98, '15', '铁道部');
INSERT INTO `nh_org` VALUES (172, '社会部', 98, '16', '社会部');
INSERT INTO `nh_org` VALUES (173, '地政部', 98, '17', '地政部');
INSERT INTO `nh_org` VALUES (174, '卫生部', 98, '18', '卫生部');
INSERT INTO `nh_org` VALUES (175, '粮食部', 98, '19', '粮食部');
INSERT INTO `nh_org` VALUES (176, '司法行政部', 98, '20', '司法行政部');
INSERT INTO `nh_org` VALUES (177, '主计部', 98, '21', '主计部');
INSERT INTO `nh_org` VALUES (178, '新闻局', 98, '22', '新闻局');
INSERT INTO `nh_org` VALUES (179, '建设委员会', 98, '23', '建设委员会');
INSERT INTO `nh_org` VALUES (180, '资源委员会', 98, '24', '资源委员会');
INSERT INTO `nh_org` VALUES (181, '蒙藏委员会', 98, '25', '蒙藏委员会');
INSERT INTO `nh_org` VALUES (182, '侨务委员会', 98, '26', '侨务委员会');
INSERT INTO `nh_org` VALUES (183, '劳工委员会', 98, '27', '劳工委员会');
INSERT INTO `nh_org` VALUES (184, '禁烟委员会', 98, '28', '禁烟委员会');
INSERT INTO `nh_org` VALUES (185, '秘书处', 98, '29', '秘书处');
INSERT INTO `nh_org` VALUES (186, '政务处', 98, '30', '政务处');
INSERT INTO `nh_org` VALUES (187, '会计处', 98, '31', '会计处');
INSERT INTO `nh_org` VALUES (188, '统计室', 98, '32', '统计室');
INSERT INTO `nh_org` VALUES (189, '人事室', 98, '33', '人事室');
INSERT INTO `nh_org` VALUES (190, '总务司', 157, '01', '总务司');
INSERT INTO `nh_org` VALUES (191, '民政司', 157, '02', '民政司');
INSERT INTO `nh_org` VALUES (192, '户政司', 157, '03', '户政司');
INSERT INTO `nh_org` VALUES (193, '警政司', 157, '04', '警政司');
INSERT INTO `nh_org` VALUES (194, '土地司', 157, '05', '土地司');
INSERT INTO `nh_org` VALUES (195, '方域司', 157, '06', '方域司');
INSERT INTO `nh_org` VALUES (196, '营建司', 157, '07', '营建司');
INSERT INTO `nh_org` VALUES (197, '统计处', 157, '08', '统计处（司）');
INSERT INTO `nh_org` VALUES (198, '社会司', 157, '09', '社会司');
INSERT INTO `nh_org` VALUES (199, '劳工司', 157, '10', '劳工司');
INSERT INTO `nh_org` VALUES (200, '合作司', 157, '11', '合作司');
INSERT INTO `nh_org` VALUES (201, '参事室', 157, '12', '参事室');
INSERT INTO `nh_org` VALUES (202, '国土测绘中心', 157, '13', '国土测绘中心');
INSERT INTO `nh_org` VALUES (203, '亚东司', 158, '01', '亚东司');
INSERT INTO `nh_org` VALUES (204, '亚西司', 158, '02', '亚西司');
INSERT INTO `nh_org` VALUES (205, '欧洲司', 158, '03', '欧洲司');
INSERT INTO `nh_org` VALUES (206, '美洲司', 158, '04', '美洲司');
INSERT INTO `nh_org` VALUES (207, '条约司', 158, '05', '条约司');
INSERT INTO `nh_org` VALUES (208, '情报司', 158, '06', '情报司');
INSERT INTO `nh_org` VALUES (209, '礼宾司', 158, '07', '礼宾司');
INSERT INTO `nh_org` VALUES (210, '总务司', 158, '08', '总务司');
INSERT INTO `nh_org` VALUES (211, '参事厅', 158, '09', '参事厅');
INSERT INTO `nh_org` VALUES (212, '人事处', 158, '10', '人事处');
INSERT INTO `nh_org` VALUES (213, '秘书处', 158, '11', '秘书处');
INSERT INTO `nh_org` VALUES (214, '机要室', 158, '12', '机要室');
INSERT INTO `nh_org` VALUES (215, '会计室', 158, '13', '会计室');
INSERT INTO `nh_org` VALUES (216, '统计室', 158, '14', '统计室');
INSERT INTO `nh_org` VALUES (217, '设计考核委员会', 158, '15', '设计考核委员会');
INSERT INTO `nh_org` VALUES (218, '专员公署', 158, '16', '专员公署、驻各地办事处');
INSERT INTO `nh_org` VALUES (219, '各驻外领事馆', 158, '17', '各驻外领事馆');
INSERT INTO `nh_org` VALUES (220, '亚洲司', 158, '18', '亚洲司');
INSERT INTO `nh_org` VALUES (221, '欧美司', 158, '19', '欧美司');
INSERT INTO `nh_org` VALUES (222, '总务厅', 159, '01', '总务厅');
INSERT INTO `nh_org` VALUES (223, '陆军署', 159, '02', '陆军署');
INSERT INTO `nh_org` VALUES (224, '海军署', 159, '03', '海军署');
INSERT INTO `nh_org` VALUES (225, '航空署', 159, '04', '航空署');
INSERT INTO `nh_org` VALUES (226, '军需署', 159, '05', '军需署');
INSERT INTO `nh_org` VALUES (227, '兵工署', 159, '06', '兵工署');
INSERT INTO `nh_org` VALUES (228, '军务署', 159, '07', '军务署');
INSERT INTO `nh_org` VALUES (229, '军医署', 159, '08', '军医署');
INSERT INTO `nh_org` VALUES (230, '人事处', 159, '09', '人事处');
INSERT INTO `nh_org` VALUES (231, '荣誉军人管理处', 159, '10', '荣誉军人管理处');
INSERT INTO `nh_org` VALUES (232, '中央各军事学校毕业生调查处', 159, '11', '中央各军事学校毕业生调查处');
INSERT INTO `nh_org` VALUES (233, '海军处', 159, '12', '海军处');
INSERT INTO `nh_org` VALUES (234, '总务司', 160, '01', '总务司');
INSERT INTO `nh_org` VALUES (235, '军衡司', 160, '02', '军衡司');
INSERT INTO `nh_org` VALUES (236, '军务司', 160, '03', '军务司');
INSERT INTO `nh_org` VALUES (237, '舰政司', 160, '04', '舰政司');
INSERT INTO `nh_org` VALUES (238, '军学司', 160, '05', '军学司');
INSERT INTO `nh_org` VALUES (239, '军械司', 160, '06', '军械司');
INSERT INTO `nh_org` VALUES (240, '海政司', 160, '07', '海政司');
INSERT INTO `nh_org` VALUES (241, '经理处', 160, '08', '经理处');
INSERT INTO `nh_org` VALUES (242, '各舰队司令部', 160, '09', '各舰队司令部');
INSERT INTO `nh_org` VALUES (243, '各要港司令部', 160, '10', '各要港司令部');
INSERT INTO `nh_org` VALUES (244, '海军编译处', 160, '11', '海军编译处');
INSERT INTO `nh_org` VALUES (245, '海道测量局', 160, '12', '海道测量局');
INSERT INTO `nh_org` VALUES (246, '海岸巡防处', 160, '13', '海岸巡防处');
INSERT INTO `nh_org` VALUES (247, '海军陆战队', 160, '14', '海军陆战队');
INSERT INTO `nh_org` VALUES (248, '海军学校', 160, '15', '海军学校');
INSERT INTO `nh_org` VALUES (249, '海军造船所', 160, '16', '海军造船所');
INSERT INTO `nh_org` VALUES (250, '海军航空处', 160, '17', '海军航空处');
INSERT INTO `nh_org` VALUES (251, '海军上海无线电台', 160, '18', '海军上海无线电台');
INSERT INTO `nh_org` VALUES (252, '海军练营', 160, '19', '海军练营');
INSERT INTO `nh_org` VALUES (253, '海军编遣办事处', 160, '20', '海军编遣办事处');
INSERT INTO `nh_org` VALUES (254, '部长办公室', 161, '01', '部长办公室');
INSERT INTO `nh_org` VALUES (255, '预算司', 161, '02', '预算司');
INSERT INTO `nh_org` VALUES (256, '财务司', 161, '03', '财务司');
INSERT INTO `nh_org` VALUES (257, '法规司', 161, '04', '法规司');
INSERT INTO `nh_org` VALUES (258, '人事司', 161, '05', '人事司');
INSERT INTO `nh_org` VALUES (259, '人力司', 161, '06', '人力司');
INSERT INTO `nh_org` VALUES (260, '工业动员司', 161, '07', '工业动员司');
INSERT INTO `nh_org` VALUES (261, '征购司', 161, '08', '征购司');
INSERT INTO `nh_org` VALUES (262, '工程司', 161, '09', '工程司');
INSERT INTO `nh_org` VALUES (263, '第一厅', 161, '10', '第一厅（掌人事）');
INSERT INTO `nh_org` VALUES (264, '第二厅', 161, '11', '第二厅（掌情报）');
INSERT INTO `nh_org` VALUES (265, '第三厅', 161, '12', '第三厅（掌计划作战）');
INSERT INTO `nh_org` VALUES (266, '第四厅', 161, '13', '第四厅（掌补给）');
INSERT INTO `nh_org` VALUES (267, '第五厅', 161, '14', '第五厅（编制与训练）');
INSERT INTO `nh_org` VALUES (268, ' 第六厅', 161, '15', ' 第六厅（掌研究与发展）');
INSERT INTO `nh_org` VALUES (269, '新闻局', 161, '16', '新闻局');
INSERT INTO `nh_org` VALUES (270, '民事局', 161, '17', '民事局');
INSERT INTO `nh_org` VALUES (271, '保安局', 161, '18', '保安局');
INSERT INTO `nh_org` VALUES (272, '预算局', 161, '19', '预算局');
INSERT INTO `nh_org` VALUES (273, '史政局', 161, '20', '史政局');
INSERT INTO `nh_org` VALUES (274, '监察局', 161, '21', '监察局');
INSERT INTO `nh_org` VALUES (275, '兵役局', 161, '22', '兵役局');
INSERT INTO `nh_org` VALUES (276, '测量局', 161, '23', '测量局');
INSERT INTO `nh_org` VALUES (277, '副官局', 161, '24', '副官局');
INSERT INTO `nh_org` VALUES (278, '军法局', 161, '25', '军法局');
INSERT INTO `nh_org` VALUES (279, '预备干部局', 161, '26', '预备干部局');
INSERT INTO `nh_org` VALUES (280, '总务局', 161, '27', '总务局');
INSERT INTO `nh_org` VALUES (281, '保密局', 161, '28', '保密局');
INSERT INTO `nh_org` VALUES (283, '国防科学委员会', 161, '29', '国防科学委员会');
INSERT INTO `nh_org` VALUES (284, '陆军总司令部', 161, '30', '陆军总司令部');
INSERT INTO `nh_org` VALUES (285, '海军总司令部', 161, '31', '海军总司令部');
INSERT INTO `nh_org` VALUES (286, '空军总司令部', 161, '32', '空军总司令部');
INSERT INTO `nh_org` VALUES (287, '联合勤务总司令部', 161, '33', '联合勤务总司令部');
INSERT INTO `nh_org` VALUES (288, '政工局', 161, '34', '政工局');
INSERT INTO `nh_org` VALUES (289, '总务司', 162, '01', '总务司');
INSERT INTO `nh_org` VALUES (290, '秘书处', 162, '02', '秘书处');
INSERT INTO `nh_org` VALUES (291, '人事处', 162, '03', '人事处');
INSERT INTO `nh_org` VALUES (292, '会计处', 162, '04', '会计处');
INSERT INTO `nh_org` VALUES (293, '统计处', 162, '05', '统计处');
INSERT INTO `nh_org` VALUES (294, '参事厅', 162, '06', '参事厅');
INSERT INTO `nh_org` VALUES (295, '视察室', 162, '07', '视察室');
INSERT INTO `nh_org` VALUES (296, '国库署', 162, '08', '国库署');
INSERT INTO `nh_org` VALUES (297, '国税署', 162, '09', '国税署');
INSERT INTO `nh_org` VALUES (298, '关务署', 162, '10', '关务署');
INSERT INTO `nh_org` VALUES (299, '缉私署', 162, '11', '缉私署');
INSERT INTO `nh_org` VALUES (300, '钱币司', 162, '12', '钱币司');
INSERT INTO `nh_org` VALUES (301, '公债司', 162, '13', '公债司');
INSERT INTO `nh_org` VALUES (302, '地方财政司', 162, '14', '地方财政司');
INSERT INTO `nh_org` VALUES (303, '专卖事业司', 162, '15', '专卖事业司');
INSERT INTO `nh_org` VALUES (304, '盐政总局', 162, '16', '盐政总局');
INSERT INTO `nh_org` VALUES (305, '花纱布管制局', 162, '17', '花纱布管制局');
INSERT INTO `nh_org` VALUES (306, '各处银行监理馆办公处', 162, '18', '各处银行监理馆办公处');
INSERT INTO `nh_org` VALUES (307, '全国财务人员训练所', 162, '19', '全国财务人员训练所');
INSERT INTO `nh_org` VALUES (308, '各委员会', 162, '20', '各委员会');
INSERT INTO `nh_org` VALUES (309, '海关总税务司署', 298, '01', '海关总税务司署');
INSERT INTO `nh_org` VALUES (310, '税务部', 309, '01', '税务部');
INSERT INTO `nh_org` VALUES (311, '海务部', 309, '02', '海务部');
INSERT INTO `nh_org` VALUES (312, '总务司', 164, '01', '总务司');
INSERT INTO `nh_org` VALUES (313, '工业司', 164, '02', '工业司');
INSERT INTO `nh_org` VALUES (314, '商业司', 164, '03', '商业司');
INSERT INTO `nh_org` VALUES (315, '劳工司', 164, '04', '劳工司');
INSERT INTO `nh_org` VALUES (316, '参事厅', 165, '01', '参事厅');
INSERT INTO `nh_org` VALUES (317, '秘书厅', 165, '02', '秘书厅');
INSERT INTO `nh_org` VALUES (318, '技监厅', 165, '03', '技监厅');
INSERT INTO `nh_org` VALUES (319, '林垦署', 165, '04', '林垦署');
INSERT INTO `nh_org` VALUES (320, '总务司', 165, '05', '总务司');
INSERT INTO `nh_org` VALUES (321, '农业司', 165, '06', '农业司');
INSERT INTO `nh_org` VALUES (322, '渔牧司', 165, '07', '渔牧司');
INSERT INTO `nh_org` VALUES (323, '工业司', 165, '08', '工业司');
INSERT INTO `nh_org` VALUES (324, '商业司', 165, '09', '商业司');
INSERT INTO `nh_org` VALUES (325, '矿业司', 165, '10', '矿业司');
INSERT INTO `nh_org` VALUES (326, '劳工司', 165, '11', '劳工司');
INSERT INTO `nh_org` VALUES (327, '合作司', 165, '12', '合作司');
INSERT INTO `nh_org` VALUES (328, '统计长办公处', 165, '13', '统计长办公处（室）');
INSERT INTO `nh_org` VALUES (329, '总务司', 166, '01', '总务司');
INSERT INTO `nh_org` VALUES (330, '管制司', 166, '02', '管制司');
INSERT INTO `nh_org` VALUES (331, '农林司', 166, '03', '农林司');
INSERT INTO `nh_org` VALUES (332, '矿业司', 166, '04', '矿业司');
INSERT INTO `nh_org` VALUES (333, '工业司', 166, '05', '工业司');
INSERT INTO `nh_org` VALUES (334, '电业司', 166, '06', '电业司');
INSERT INTO `nh_org` VALUES (335, '商业司', 166, '07', '商业司');
INSERT INTO `nh_org` VALUES (336, '企业司', 166, '08', '企业司');
INSERT INTO `nh_org` VALUES (337, '国际贸易司', 166, '09', '国际贸易司');
INSERT INTO `nh_org` VALUES (338, '水利司', 166, '10', '水利司');
INSERT INTO `nh_org` VALUES (339, '统计处', 166, '11', '统计处（室）');
INSERT INTO `nh_org` VALUES (340, '会计处', 166, '12', '会计处（室）');
INSERT INTO `nh_org` VALUES (341, '人事处', 166, '13', '人事处（室）');
INSERT INTO `nh_org` VALUES (342, '总务室', 167, '01', '总务室');
INSERT INTO `nh_org` VALUES (343, '农事室', 167, '02', '农事室');
INSERT INTO `nh_org` VALUES (344, '农村经济室', 167, '03', '农村经济室');
INSERT INTO `nh_org` VALUES (345, '林业室', 167, '04', '林业室');
INSERT INTO `nh_org` VALUES (346, '渔牧室', 167, '05', '渔牧室');
INSERT INTO `nh_org` VALUES (347, '垦殖司', 167, '06', '垦殖司');
INSERT INTO `nh_org` VALUES (348, '会计室', 167, '07', '会计室（处）');
INSERT INTO `nh_org` VALUES (349, '统计室', 167, '08', '统计室');
INSERT INTO `nh_org` VALUES (350, '人事室', 167, '09', '人事室');
INSERT INTO `nh_org` VALUES (351, '各委员会', 167, '10', '各委员会');
INSERT INTO `nh_org` VALUES (352, '各研究实验所', 167, '11', '各研究实验所');
INSERT INTO `nh_org` VALUES (353, '秘书厅', 168, '01', '秘书厅');
INSERT INTO `nh_org` VALUES (354, '参事厅', 168, '02', '参事厅');
INSERT INTO `nh_org` VALUES (355, '技术厅', 168, '03', '技术厅');
INSERT INTO `nh_org` VALUES (356, '水政司', 168, '04', '水政司');
INSERT INTO `nh_org` VALUES (357, '防洪司', 168, '05', '防洪司');
INSERT INTO `nh_org` VALUES (358, '渠港司', 168, '06', '渠港司');
INSERT INTO `nh_org` VALUES (359, '水文司', 168, '07', '水文司');
INSERT INTO `nh_org` VALUES (360, '器材司', 168, '08', '器材司');
INSERT INTO `nh_org` VALUES (361, '总务司', 168, '09', '总务司');
INSERT INTO `nh_org` VALUES (362, '会计处', 168, '10', '会计处');
INSERT INTO `nh_org` VALUES (363, '统计室', 168, '11', '统计室');
INSERT INTO `nh_org` VALUES (364, '人事室', 168, '12', '人事室');
INSERT INTO `nh_org` VALUES (365, '各委员会', 168, '13', '各委员会');
INSERT INTO `nh_org` VALUES (366, '各水利工程总局', 168, '14', '各水利工程总局/总队');
INSERT INTO `nh_org` VALUES (367, '中央水利实验处', 168, '15', '中央水利实验处');
INSERT INTO `nh_org` VALUES (368, '水利示范工程处', 168, '16', '水利示范工程处');
INSERT INTO `nh_org` VALUES (369, '总务司', 169, '01', '总务司');
INSERT INTO `nh_org` VALUES (370, '高等教育司', 169, '02', '高等教育司');
INSERT INTO `nh_org` VALUES (371, '普通教育司', 169, '03', '普通教育司');
INSERT INTO `nh_org` VALUES (372, '社会教育司', 169, '04', '社会教育司');
INSERT INTO `nh_org` VALUES (373, '蒙藏教育司', 169, '05', '蒙藏教育司');
INSERT INTO `nh_org` VALUES (374, '国际文化教育事业处', 169, '06', '国际文化教育事业处');
INSERT INTO `nh_org` VALUES (375, '人事处', 169, '07', '人事处');
INSERT INTO `nh_org` VALUES (376, '会计处', 169, '08', '会计处');
INSERT INTO `nh_org` VALUES (377, '统计处', 169, '09', '统计处');
INSERT INTO `nh_org` VALUES (378, '参事室', 169, '10', '参事室');
INSERT INTO `nh_org` VALUES (379, '秘书室', 169, '11', '秘书室');
INSERT INTO `nh_org` VALUES (380, '督学室', 169, '12', '督学室');
INSERT INTO `nh_org` VALUES (381, '资料研究室', 169, '13', '资料研究室');
INSERT INTO `nh_org` VALUES (382, '国立编译馆', 169, '14', '国立编译馆');
INSERT INTO `nh_org` VALUES (383, '秘书厅', 170, '01', '秘书厅');
INSERT INTO `nh_org` VALUES (384, '参事厅', 170, '02', '参事厅');
INSERT INTO `nh_org` VALUES (385, '技术厅', 170, '03', '技术厅');
INSERT INTO `nh_org` VALUES (386, '总务司', 170, '04', '总务司');
INSERT INTO `nh_org` VALUES (387, '人事司', 170, '05', '人事司');
INSERT INTO `nh_org` VALUES (388, '财务司', 170, '06', '财务司');
INSERT INTO `nh_org` VALUES (389, '路政司', 170, '07', '路政司');
INSERT INTO `nh_org` VALUES (390, '材料司', 170, '08', '材料司');
INSERT INTO `nh_org` VALUES (391, '邮政司', 170, '09', '邮政（邮电）司');
INSERT INTO `nh_org` VALUES (392, '航政司', 170, '10', '航政司');
INSERT INTO `nh_org` VALUES (393, '会计处', 170, '11', '会计处');
INSERT INTO `nh_org` VALUES (394, '统计处', 170, '12', '统计处');
INSERT INTO `nh_org` VALUES (395, '各种委员会', 170, '13', '各种委员会');
INSERT INTO `nh_org` VALUES (396, '公路总局', 170, '14', '公路总局');
INSERT INTO `nh_org` VALUES (397, '电信总局', 170, '15', '电信总局');
INSERT INTO `nh_org` VALUES (398, '邮政总局', 170, '16', '邮政总局');
INSERT INTO `nh_org` VALUES (399, '释运总管理处', 170, '17', '释运总管理处');
INSERT INTO `nh_org` VALUES (400, '交通警察总局', 170, '18', '交通警察总局');
INSERT INTO `nh_org` VALUES (401, '秘书处', 180, '01', '秘书处/厅');
INSERT INTO `nh_org` VALUES (402, '电业处', 180, '02', '电业处');
INSERT INTO `nh_org` VALUES (403, '工业处', 180, '03', '工业处');
INSERT INTO `nh_org` VALUES (404, '矿业处', 180, '04', '矿业处');
INSERT INTO `nh_org` VALUES (405, '材料处', 180, '05', '材料处');
INSERT INTO `nh_org` VALUES (406, '财务处', 180, '06', '财务处');
INSERT INTO `nh_org` VALUES (407, '业务委员会', 180, '07', '业务委员会');
INSERT INTO `nh_org` VALUES (408, '总务处', 180, '08', '总务处');
INSERT INTO `nh_org` VALUES (409, '会计处', 180, '09', '会计处');
INSERT INTO `nh_org` VALUES (410, '参事室', 180, '10', '参事室');
INSERT INTO `nh_org` VALUES (411, '人事室', 180, '11', '人事室');
INSERT INTO `nh_org` VALUES (412, '统计室', 180, '12', '统计室');
INSERT INTO `nh_org` VALUES (413, '技术室', 180, '13', '技术室');
INSERT INTO `nh_org` VALUES (414, '经济研究室', 180, '14', '经济研究室');
INSERT INTO `nh_org` VALUES (415, '购料室', 180, '15', '购料室');
INSERT INTO `nh_org` VALUES (416, '总务处', 181, '01', '总务处');
INSERT INTO `nh_org` VALUES (417, '蒙事处', 181, '02', '蒙事处');
INSERT INTO `nh_org` VALUES (418, '藏事处', 181, '03', '藏事处');
INSERT INTO `nh_org` VALUES (419, '秘书室', 181, '04', '秘书室');
INSERT INTO `nh_org` VALUES (420, '编译室', 181, '05', '编译室');
INSERT INTO `nh_org` VALUES (421, '调查室', 181, '06', '调查室');
INSERT INTO `nh_org` VALUES (422, '会计室', 181, '07', '会计室');
INSERT INTO `nh_org` VALUES (423, '统计室', 181, '08', '统计室');
INSERT INTO `nh_org` VALUES (424, '人事室', 181, '09', '人事室');
INSERT INTO `nh_org` VALUES (425, '各调查组', 181, '10', '各调查组');
INSERT INTO `nh_org` VALUES (426, '各办事处', 181, '11', '各办事处');
INSERT INTO `nh_org` VALUES (427, '个别机构', -1, 'D', '个别机构');
INSERT INTO `nh_org` VALUES (428, '中国国民党中央委员会', 427, '01', '中国国民党中央委员会');
INSERT INTO `nh_org` VALUES (429, '全国经济委员会', 427, '02', '全国经济委员会');
INSERT INTO `nh_org` VALUES (430, '执行委员会', 427, '03', '执行委员会');
INSERT INTO `nh_org` VALUES (431, '党部', 427, '04', '党部');
INSERT INTO `nh_org` VALUES (432, '敌伪产业处理局', 427, '05', '敌伪产业处理局');
INSERT INTO `nh_org` VALUES (433, '商会', 427, '06', '商会');
INSERT INTO `nh_org` VALUES (434, '工会', 427, '07', '工会');
INSERT INTO `nh_org` VALUES (435, '企业', 427, '08', '企业');
INSERT INTO `nh_org` VALUES (436, '广东', 427, '09', '广东');
INSERT INTO `nh_org` VALUES (437, '青海', 427, '10', '青海');
INSERT INTO `nh_org` VALUES (438, '贵州', 427, '11', '贵州');
INSERT INTO `nh_org` VALUES (439, '台湾', 427, '12', '台湾');
INSERT INTO `nh_org` VALUES (440, '其他', 427, '13', '其他');
INSERT INTO `nh_org` VALUES (441, '湖南省湘潭执行委员会', 430, '01', '湖南省湘潭执行委员会');
INSERT INTO `nh_org` VALUES (442, '江西省执行委员会', 430, '02', '江西省执行委员会');
INSERT INTO `nh_org` VALUES (443, '浙江黄岩县执行委员会', 430, '03', '浙江黄岩县执行委员会');
INSERT INTO `nh_org` VALUES (444, '南昌市执行委员会', 430, '04', '南昌市执行委员会');
INSERT INTO `nh_org` VALUES (445, '中国国民党浙江省瑞安县执行委员会', 430, '05', '中国国民党浙江省瑞安县执行委员会');
INSERT INTO `nh_org` VALUES (446, '中国国民党中山县第二区执监委员', 430, '06', '中国国民党中山县第二区执监委员');
INSERT INTO `nh_org` VALUES (447, '中华海员特别党部上海区第一区分部', 431, '01', '中华海员特别党部上海区第一区分部');
INSERT INTO `nh_org` VALUES (448, '山东省惠民县党部', 431, '02', '山东省惠民县党部');
INSERT INTO `nh_org` VALUES (449, '上海总商会', 433, '01', '上海总商会');
INSERT INTO `nh_org` VALUES (450, '浙江省绍兴县柯桥镇商会', 433, '02', '浙江省绍兴县柯桥镇商会');
INSERT INTO `nh_org` VALUES (451, '绍兴县商会', 433, '03', '绍兴县商会');
INSERT INTO `nh_org` VALUES (452, '上海市第三、四、六区巢丝业产业工会', 434, '01', '上海市第三、四、六区巢丝业产业工会');
INSERT INTO `nh_org` VALUES (453, '浙江鄞县民船船员工会', 434, '02', '浙江鄞县民船船员工会');
INSERT INTO `nh_org` VALUES (454, '中元企业', 435, '01', '中元企业');
INSERT INTO `nh_org` VALUES (455, '台湾肥料有限公司', 435, '02', '台湾肥料有限公司');
INSERT INTO `nh_org` VALUES (456, '广东建设厅', 436, '01', '广东建设厅');
INSERT INTO `nh_org` VALUES (457, '广东省政府', 436, '02', '广东省政府');
INSERT INTO `nh_org` VALUES (458, '广东陆地测量局', 436, '03', '广东陆地测量局');
INSERT INTO `nh_org` VALUES (459, '贵德县党务特派员办事处', 437, '01', '贵德县党务特派员办事处');
INSERT INTO `nh_org` VALUES (460, '贵州省党务指导委员会', 438, '01', '贵州省党务指导委员会');
INSERT INTO `nh_org` VALUES (461, '台湾省行政长官公署', 439, '01', '台湾省行政长官公署');
INSERT INTO `nh_org` VALUES (462, '台湾省政府', 439, '02', '台湾省政府');
INSERT INTO `nh_org` VALUES (463, '台湾警备总司令部', 439, '03', '台湾警备总司令部');
INSERT INTO `nh_org` VALUES (464, '台湾省兵役处', 439, '04', '台湾省兵役处');
INSERT INTO `nh_org` VALUES (465, '台湾省警务处', 439, '05', '台湾省警务处');
INSERT INTO `nh_org` VALUES (466, '台湾省政府农林厅渔业局', 439, '06', '台湾省政府农林厅渔业局');
INSERT INTO `nh_org` VALUES (467, '亚光舆地学社重庆办事处', 440, '01', '亚光舆地学社重庆办事处');
INSERT INTO `nh_org` VALUES (468, '立威部队', 440, '02', '立威部队');
INSERT INTO `nh_org` VALUES (469, '署粤都', 440, '03', '署粤都');
INSERT INTO `nh_org` VALUES (470, '粤都', 440, '04', '粤都');
INSERT INTO `nh_org` VALUES (471, '中山大学', 440, '05', '中山大学');
INSERT INTO `nh_org` VALUES (472, '光复大陆设计研究委员会', 440, '06', '光复大陆设计研究委员会');
INSERT INTO `nh_org` VALUES (473, '亚东关系协会', 440, '07', '亚东关系协会');
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of nh_user
-- ----------------------------
BEGIN;
INSERT INTO `nh_user` VALUES (1, NULL, NULL, NULL, NULL, '1', 'tzh', '有', '1', NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
