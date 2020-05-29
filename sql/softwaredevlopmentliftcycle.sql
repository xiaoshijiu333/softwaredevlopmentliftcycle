/*
Navicat MySQL Data Transfer

Source Server         : fei
Source Server Version : 80016
Source Host           : localhost:3306
Source Database       : softwaredevlopmentliftcycle

Target Server Type    : MYSQL
Target Server Version : 80016
File Encoding         : 65001

Date: 2020-05-29 16:17:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bug
-- ----------------------------
DROP TABLE IF EXISTS `bug`;
CREATE TABLE `bug` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '缺陷id，自增',
  `bug_name` varchar(100) NOT NULL COMMENT '缺陷名称',
  `bug_desc` varchar(100) NOT NULL COMMENT '缺陷描述',
  `test_name` varchar(50) NOT NULL COMMENT '来源测试人员姓名',
  `dev_name` varchar(50) NOT NULL COMMENT '所属开发人员姓名',
  `project_id` int(11) NOT NULL COMMENT '所属项目id',
  `bug_status` int(11) NOT NULL COMMENT '缺陷状态，0新建，1关闭，2挂起',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `delete_time` varchar(255) DEFAULT '' COMMENT '删除时间，默认空串',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of bug
-- ----------------------------
INSERT INTO `bug` VALUES ('2', '查询接口分页有误', '点击第二页数据不刷新', '周宇', '郭鱼', '12', '3', '2020-05-16 14:48:55', '');
INSERT INTO `bug` VALUES ('3', '手机验证码登录失败', '其他登录方式都可以，但是验证码登录不行', '周宇', '郭鱼', '12', '2', '2020-05-18 16:23:02', '');
INSERT INTO `bug` VALUES ('4', 'xxxxx', 'xxxxxxxxxx', '周宇', '郭鱼', '12', '2', '2020-05-21 20:28:20', '');
INSERT INTO `bug` VALUES ('5', 'qq登录失败', 'qq登录失败', '周宇', '郭鱼', '17', '0', '2020-05-25 23:07:52', '');
INSERT INTO `bug` VALUES ('6', 'bugbug', 'bugbugbug', '周宇', '郭鱼', '18', '3', '2020-05-26 15:42:39', '');
INSERT INTO `bug` VALUES ('7', '登录方式出错', '111111', '周宇', '郭鱼', '19', '3', '2020-05-27 14:37:13', '');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `comment_context` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论内容',
  `user_id` int(11) NOT NULL COMMENT '评论人ID',
  `project_id` int(11) NOT NULL COMMENT '项目ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `delete_time` varchar(255) DEFAULT '' COMMENT '删除时间，默认空串',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('1', '<p>分析的挺详细的</p>', '1', '12', '2020-05-14 01:02:54', '');
INSERT INTO `comment` VALUES ('2', '<p>我也觉得分析的挺详细的</p>', '2', '12', '2020-05-14 01:09:42', '');
INSERT INTO `comment` VALUES ('3', '<p>需求分析做得挺详细的</p>', '1', '17', '2020-05-25 22:57:21', '');
INSERT INTO `comment` VALUES ('4', '<p>我也觉得还行</p>', '2', '17', '2020-05-25 23:00:04', '');
INSERT INTO `comment` VALUES ('5', '<p>你是猪</p>', '1', '18', '2020-05-26 15:34:41', '');
INSERT INTO `comment` VALUES ('6', '<p>你也是猪</p>', '2', '18', '2020-05-26 15:39:19', '');
INSERT INTO `comment` VALUES ('7', '<p>不错</p>', '1', '19', '2020-05-27 14:32:35', '');
INSERT INTO `comment` VALUES ('8', '<p>1</p>', '2', '19', '2020-05-27 14:34:55', '');

-- ----------------------------
-- Table structure for deploy
-- ----------------------------
DROP TABLE IF EXISTS `deploy`;
CREATE TABLE `deploy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `deploy_domin` varchar(100) NOT NULL,
  `deploy_server_site` varchar(100) NOT NULL COMMENT '服务器地址',
  `deploy_database_site` varchar(100) NOT NULL COMMENT '数据库地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `project_id` int(11) NOT NULL COMMENT '项目id',
  `delete_time` varchar(255) DEFAULT '' COMMENT '删除时间，默认空串',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of deploy
-- ----------------------------
INSERT INTO `deploy` VALUES ('1', 'www.guo.com', '124.124.124.124', '422.422.422.422', '2020-05-26 15:57:05', '18', '20200514210912');
INSERT INTO `deploy` VALUES ('2', 'www.guo.com', '124.124.124.124', '422.422.422.422', '2020-05-26 15:57:15', '18', '20200514210912');
INSERT INTO `deploy` VALUES ('3', 'com.yu.com', '124.124.124.124', '422.4224.422.422', '2020-05-26 16:06:06', '18', '');
INSERT INTO `deploy` VALUES ('4', 'www.fei.com', '47.122.144.122', '47.125.125.124', '2020-05-27 14:40:12', '19', '');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `message_title` varchar(100) NOT NULL COMMENT '消息标题',
  `message_status` int(11) NOT NULL COMMENT '消息状态，0未读，1已读',
  `receive_id` int(11) NOT NULL COMMENT '接收人id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `delete_time` varchar(255) DEFAULT '' COMMENT '删除时间，默认空串',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '《某某城市智慧交通项目建设》项目已经流转到了《测试阶段》', '1', '1', '2020-05-18 14:53:21', '20200527000446368');
INSERT INTO `message` VALUES ('2', '《某某城市智慧交通项目建设》项目已经流转到了《测试阶段》', '1', '2', '2020-05-18 14:53:21', '20200527001036957');
INSERT INTO `message` VALUES ('3', '《某某城市智慧交通项目建设》项目已经流转到了《测试阶段》', '1', '3', '2020-05-18 14:53:21', '20200527000529972');
INSERT INTO `message` VALUES ('4', '《某某城市智慧交通项目建设》项目已经流转到了《测试阶段》', '1', '4', '2020-05-18 14:53:21', '20200527001310475');
INSERT INTO `message` VALUES ('5', '《周宇》给你拉去了一个缺陷《手机验证码登录失败》', '1', '2', '2020-05-18 16:23:02', '20200527001037610');
INSERT INTO `message` VALUES ('6', '《郭鱼》返回了缺陷《手机验证码登录失败》待检查', '1', '3', '2020-05-18 16:27:14', '20200527000530572');
INSERT INTO `message` VALUES ('7', '《周宇》检查并返回了缺陷《手机验证码登录失败》为不通过', '1', '2', '2020-05-18 16:27:59', '20200527001038124');
INSERT INTO `message` VALUES ('8', '《周宇》给你拉取了一个缺陷《xxxxx》', '1', '2', '2020-05-21 20:28:21', '20200527001038612');
INSERT INTO `message` VALUES ('9', '《郭鱼》返回了缺陷《xxxxx》待检查', '1', '3', '2020-05-21 20:29:07', '20200527000532386');
INSERT INTO `message` VALUES ('10', '《周宇》检查并返回了缺陷《xxxxx》为不通过', '1', '2', '2020-05-21 20:30:22', '20200527001039178');
INSERT INTO `message` VALUES ('11', '《某某公司上班考勤项目建设》项目已经流转到了《研发阶段》', '1', '1', '2020-05-25 22:57:31', '20200527000447179');
INSERT INTO `message` VALUES ('12', '《某某公司上班考勤项目建设》项目已经流转到了《研发阶段》', '1', '2', '2020-05-25 22:57:31', '20200527001039731');
INSERT INTO `message` VALUES ('13', '《某某公司上班考勤项目建设》项目已经流转到了《研发阶段》', '1', '3', '2020-05-25 22:57:31', '20200527000856671');
INSERT INTO `message` VALUES ('14', '《某某公司上班考勤项目建设》项目已经流转到了《研发阶段》', '1', '4', '2020-05-25 22:57:31', '20200527001310955');
INSERT INTO `message` VALUES ('15', '《某某公司上班考勤项目建设》项目已经流转到了《研发阶段》', '1', '8', '2020-05-25 22:57:31', '20200527001526243');
INSERT INTO `message` VALUES ('16', '《某某公司上班考勤项目建设》项目已经流转到了《研发阶段》', '1', '10', '2020-05-25 22:57:31', '20200527001107101');
INSERT INTO `message` VALUES ('17', '《某某公司上班考勤项目建设》项目已经流转到了《测试阶段》', '1', '1', '2020-05-25 23:00:14', '20200527000447764');
INSERT INTO `message` VALUES ('18', '《某某公司上班考勤项目建设》项目已经流转到了《测试阶段》', '1', '2', '2020-05-25 23:00:14', '20200527001040594');
INSERT INTO `message` VALUES ('19', '《某某公司上班考勤项目建设》项目已经流转到了《测试阶段》', '1', '3', '2020-05-25 23:00:14', '20200527000857667');
INSERT INTO `message` VALUES ('20', '《某某公司上班考勤项目建设》项目已经流转到了《测试阶段》', '1', '4', '2020-05-25 23:00:14', '20200527001311393');
INSERT INTO `message` VALUES ('21', '《某某公司上班考勤项目建设》项目已经流转到了《测试阶段》', '1', '8', '2020-05-25 23:00:14', '20200527001525492');
INSERT INTO `message` VALUES ('22', '《某某公司上班考勤项目建设》项目已经流转到了《测试阶段》', '1', '10', '2020-05-25 23:00:14', '20200527001107525');
INSERT INTO `message` VALUES ('23', '《某某公司上班考勤项目建设》项目已经流转到了《测试阶段》', '1', '1', '2020-05-25 23:04:30', '20200527000448276');
INSERT INTO `message` VALUES ('24', '《某某公司上班考勤项目建设》项目已经流转到了《测试阶段》', '1', '2', '2020-05-25 23:04:30', '20200527001041044');
INSERT INTO `message` VALUES ('25', '《某某公司上班考勤项目建设》项目已经流转到了《测试阶段》', '1', '3', '2020-05-25 23:04:30', '20200527000858227');
INSERT INTO `message` VALUES ('26', '《某某公司上班考勤项目建设》项目已经流转到了《测试阶段》', '1', '4', '2020-05-25 23:04:30', '');
INSERT INTO `message` VALUES ('27', '《某某公司上班考勤项目建设》项目已经流转到了《测试阶段》', '1', '8', '2020-05-25 23:04:30', '20200527001527427');
INSERT INTO `message` VALUES ('28', '《某某公司上班考勤项目建设》项目已经流转到了《测试阶段》', '1', '10', '2020-05-25 23:04:30', '20200527001107936');
INSERT INTO `message` VALUES ('29', '《周宇》给你拉取了一个缺陷《qq登录失败》', '1', '2', '2020-05-25 23:07:53', '20200527001042706');
INSERT INTO `message` VALUES ('30', '《xxxxx》项目已经流转到了《研发阶段》', '1', '1', '2020-05-26 15:34:46', '20200527000448931');
INSERT INTO `message` VALUES ('31', '《xxxxx》项目已经流转到了《研发阶段》', '1', '2', '2020-05-26 15:34:46', '20200527001050781');
INSERT INTO `message` VALUES ('32', '《xxxxx》项目已经流转到了《研发阶段》', '1', '3', '2020-05-26 15:34:46', '20200527000858779');
INSERT INTO `message` VALUES ('33', '《xxxxx》项目已经流转到了《研发阶段》', '1', '4', '2020-05-26 15:34:46', '20200527001312260');
INSERT INTO `message` VALUES ('34', '《xxxxx》项目已经流转到了《研发阶段》', '1', '8', '2020-05-26 15:34:46', '20200527001528714');
INSERT INTO `message` VALUES ('35', '《xxxxx》项目已经流转到了《研发阶段》', '1', '10', '2020-05-26 15:34:46', '20200527001108436');
INSERT INTO `message` VALUES ('36', '《xxxxx》项目已经流转到了《测试阶段》', '1', '1', '2020-05-26 15:39:50', '20200527000449811');
INSERT INTO `message` VALUES ('37', '《xxxxx》项目已经流转到了《测试阶段》', '1', '2', '2020-05-26 15:39:50', '20200527001051164');
INSERT INTO `message` VALUES ('38', '《xxxxx》项目已经流转到了《测试阶段》', '1', '3', '2020-05-26 15:39:50', '20200527000859863');
INSERT INTO `message` VALUES ('39', '《xxxxx》项目已经流转到了《测试阶段》', '1', '4', '2020-05-26 15:39:50', '20200527001312876');
INSERT INTO `message` VALUES ('40', '《xxxxx》项目已经流转到了《测试阶段》', '1', '8', '2020-05-26 15:39:50', '20200527001529244');
INSERT INTO `message` VALUES ('41', '《xxxxx》项目已经流转到了《测试阶段》', '1', '10', '2020-05-26 15:39:50', '20200527001109628');
INSERT INTO `message` VALUES ('42', '《周宇》给你拉取了一个缺陷《bugbug》', '1', '2', '2020-05-26 15:42:40', '');
INSERT INTO `message` VALUES ('43', '《郭鱼》返回了缺陷《bugbug》待检查', '1', '3', '2020-05-26 15:44:08', '');
INSERT INTO `message` VALUES ('44', '《xxxxx》项目已经流转到了《发布阶段》', '1', '1', '2020-05-26 15:46:11', '');
INSERT INTO `message` VALUES ('45', '《xxxxx》项目已经流转到了《发布阶段》', '1', '2', '2020-05-26 15:46:11', '');
INSERT INTO `message` VALUES ('46', '《xxxxx》项目已经流转到了《发布阶段》', '1', '3', '2020-05-26 15:46:11', '');
INSERT INTO `message` VALUES ('47', '《xxxxx》项目已经流转到了《发布阶段》', '1', '4', '2020-05-26 15:46:11', '20200527001313913');
INSERT INTO `message` VALUES ('48', '《xxxxx》项目已经流转到了《发布阶段》', '1', '8', '2020-05-26 15:46:11', '');
INSERT INTO `message` VALUES ('49', '《xxxxx》项目已经流转到了《发布阶段》', '1', '10', '2020-05-26 15:46:11', '');
INSERT INTO `message` VALUES ('50', '《xxxxx》项目已经发布上线了', '1', '1', '2020-05-26 16:06:07', '20200527000452724');
INSERT INTO `message` VALUES ('51', '《xxxxx》项目已经发布上线了', '1', '2', '2020-05-26 16:06:07', '');
INSERT INTO `message` VALUES ('52', '《xxxxx》项目已经发布上线了', '1', '3', '2020-05-26 16:06:07', '');
INSERT INTO `message` VALUES ('53', '《xxxxx》项目已经发布上线了', '1', '4', '2020-05-26 16:06:07', '');
INSERT INTO `message` VALUES ('54', '《xxxxx》项目已经发布上线了', '1', '8', '2020-05-26 16:06:07', '');
INSERT INTO `message` VALUES ('55', '《xxxxx》项目已经发布上线了', '1', '10', '2020-05-26 16:06:07', '');
INSERT INTO `message` VALUES ('56', '《某某学校新官网键设》项目已经流转到了《研发阶段》', '1', '1', '2020-05-27 14:32:43', '');
INSERT INTO `message` VALUES ('57', '《某某学校新官网键设》项目已经流转到了《研发阶段》', '1', '2', '2020-05-27 14:32:43', '');
INSERT INTO `message` VALUES ('58', '《某某学校新官网键设》项目已经流转到了《研发阶段》', '1', '3', '2020-05-27 14:32:43', '');
INSERT INTO `message` VALUES ('59', '《某某学校新官网键设》项目已经流转到了《研发阶段》', '0', '4', '2020-05-27 14:32:43', '');
INSERT INTO `message` VALUES ('60', '《某某学校新官网键设》项目已经流转到了《研发阶段》', '0', '8', '2020-05-27 14:32:43', '');
INSERT INTO `message` VALUES ('61', '《某某学校新官网键设》项目已经流转到了《研发阶段》', '1', '10', '2020-05-27 14:32:43', '');
INSERT INTO `message` VALUES ('62', '《某某学校新官网键设》项目已经流转到了《测试阶段》', '1', '1', '2020-05-27 14:35:26', '');
INSERT INTO `message` VALUES ('63', '《某某学校新官网键设》项目已经流转到了《测试阶段》', '1', '2', '2020-05-27 14:35:26', '');
INSERT INTO `message` VALUES ('64', '《某某学校新官网键设》项目已经流转到了《测试阶段》', '1', '3', '2020-05-27 14:35:26', '');
INSERT INTO `message` VALUES ('65', '《某某学校新官网键设》项目已经流转到了《测试阶段》', '0', '4', '2020-05-27 14:35:26', '');
INSERT INTO `message` VALUES ('66', '《某某学校新官网键设》项目已经流转到了《测试阶段》', '0', '8', '2020-05-27 14:35:26', '');
INSERT INTO `message` VALUES ('67', '《某某学校新官网键设》项目已经流转到了《测试阶段》', '1', '10', '2020-05-27 14:35:26', '');
INSERT INTO `message` VALUES ('68', '《周宇》给你拉取了一个缺陷《登录方式出错》', '1', '2', '2020-05-27 14:37:13', '');
INSERT INTO `message` VALUES ('69', '《郭鱼》返回了缺陷《登录方式出错》待检查', '1', '3', '2020-05-27 14:37:49', '');
INSERT INTO `message` VALUES ('70', '《某某学校新官网键设》项目已经流转到了《发布阶段》', '1', '1', '2020-05-27 14:39:08', '');
INSERT INTO `message` VALUES ('71', '《某某学校新官网键设》项目已经流转到了《发布阶段》', '0', '2', '2020-05-27 14:39:08', '');
INSERT INTO `message` VALUES ('72', '《某某学校新官网键设》项目已经流转到了《发布阶段》', '0', '3', '2020-05-27 14:39:08', '');
INSERT INTO `message` VALUES ('73', '《某某学校新官网键设》项目已经流转到了《发布阶段》', '0', '4', '2020-05-27 14:39:08', '');
INSERT INTO `message` VALUES ('74', '《某某学校新官网键设》项目已经流转到了《发布阶段》', '0', '8', '2020-05-27 14:39:08', '');
INSERT INTO `message` VALUES ('75', '《某某学校新官网键设》项目已经流转到了《发布阶段》', '1', '10', '2020-05-27 14:39:08', '');
INSERT INTO `message` VALUES ('76', '《某某学校新官网键设》项目已经发布上线了', '0', '1', '2020-05-27 14:40:13', '');
INSERT INTO `message` VALUES ('77', '《某某学校新官网键设》项目已经发布上线了', '0', '2', '2020-05-27 14:40:13', '');
INSERT INTO `message` VALUES ('78', '《某某学校新官网键设》项目已经发布上线了', '0', '3', '2020-05-27 14:40:13', '');
INSERT INTO `message` VALUES ('79', '《某某学校新官网键设》项目已经发布上线了', '0', '4', '2020-05-27 14:40:13', '');
INSERT INTO `message` VALUES ('80', '《某某学校新官网键设》项目已经发布上线了', '0', '8', '2020-05-27 14:40:13', '');
INSERT INTO `message` VALUES ('81', '《某某学校新官网键设》项目已经发布上线了', '1', '10', '2020-05-27 14:40:13', '');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '项目主键id，自增',
  `project_name` varchar(255) NOT NULL COMMENT '项目名称',
  `project_state` int(11) NOT NULL COMMENT '项目状态，0，1，2，3依次类推',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `delete_time` varchar(50) DEFAULT '' COMMENT '删除时间，默认空串',
  `project_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '项目说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '某某学校新官网项目建设', '2', '2020-04-21 22:36:00', '20200512150614148', '此次该学校新官网建设是在老官网的基础上进行新的迭代');
INSERT INTO `project` VALUES ('2', '本公司员工线上打卡项目建设', '1', '2020-04-14 22:36:48', '', '为了优化员工智能化打卡，提高工作效率');
INSERT INTO `project` VALUES ('3', '某某学校学生选课系统项目建设', '2', '2020-04-16 22:37:21', '', '优化之前的选课系统，为了能承受更大的并发量');
INSERT INTO `project` VALUES ('4', '某某学校毕业论文系统项目建设', '3', '2020-04-18 22:37:59', '', '优化之前的毕业论文系统');
INSERT INTO `project` VALUES ('12', '某某城市智慧交通项目建设', '1', '2020-04-24 17:12:12', '', '智慧交通项目属于一个新型项目，是城市智能化建设道路上的重要一个环节');
INSERT INTO `project` VALUES ('14', 'xxxxxx', '0', '2020-05-12 14:59:21', '20200512150614148', 'zzzzzzzzzzz');
INSERT INTO `project` VALUES ('15', 'yyyyyyyyyy', '0', '2020-05-12 20:35:00', '', 'yyyyyyyyyyyyyyy');
INSERT INTO `project` VALUES ('16', 'xxxxxx', '4', '2020-05-16 23:00:35', '20200512150614148', 'xxxxxxxxxxxxxx');
INSERT INTO `project` VALUES ('17', '某某公司上班考勤项目建设', '2', '2020-05-25 22:31:00', '', '拥有该公司上班打开，请假等等功能');
INSERT INTO `project` VALUES ('18', 'xxxxx', '4', '2020-05-26 15:30:40', '', 'xxxxxxxxxxxxxxxxxxxx');
INSERT INTO `project` VALUES ('19', '某某学校新官网键设', '4', '2020-05-27 14:31:39', '', 'xxxxxxx');

-- ----------------------------
-- Table structure for project_file
-- ----------------------------
DROP TABLE IF EXISTS `project_file`;
CREATE TABLE `project_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `project_id` int(11) NOT NULL COMMENT '项目id',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '项目关联文件的url',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '项目关联文件的name',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `delete_time` varchar(50) DEFAULT '' COMMENT '删除时间，默认空串',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of project_file
-- ----------------------------
INSERT INTO `project_file` VALUES ('3', '12', 'K:/softwarefiles/智慧城市项目需求文档.docx', '智慧城市项目需求文档.docx', '2020-04-24 17:12:28', '');
INSERT INTO `project_file` VALUES ('4', '14', 'K:/softwarefiles/xxxxxx.docx', 'xxxxxx.docx', '2020-05-12 15:01:52', '');
INSERT INTO `project_file` VALUES ('5', '15', 'K:/softwarefiles/yyyyy.docx', 'yyyyy.docx', '2020-05-12 20:36:03', '20200512214040891');
INSERT INTO `project_file` VALUES ('6', '15', 'K:/softwarefiles/yyyyy.docx', 'yyyyy.docx', '2020-05-12 21:40:41', '');
INSERT INTO `project_file` VALUES ('7', '1', 'K:/softwarefiles/某某学校新官网项目建设.docx', '某某学校新官网项目建设.docx', '2020-05-15 17:30:23', '');
INSERT INTO `project_file` VALUES ('8', '16', 'K:/softwarefiles/zzzzzzz.docx', 'zzzzzzz.docx', '2020-05-16 23:01:28', '');
INSERT INTO `project_file` VALUES ('9', '17', 'K:/softwarefiles/上班考勤项目需求文件.docx', '上班考勤项目需求文件.docx', '2020-05-25 22:32:45', '20200525223406417');
INSERT INTO `project_file` VALUES ('10', '17', 'K:/softwarefiles/上班考勤项目需求文件.docx', '上班考勤项目需求文件.docx', '2020-05-25 22:34:06', '');
INSERT INTO `project_file` VALUES ('11', '18', 'K:/softwarefiles/王飞16032220（修改）.docx', '王飞+16032220（修改）.docx', '2020-05-26 15:31:08', '');
INSERT INTO `project_file` VALUES ('12', '19', 'K:/softwarefiles/某某学校新官网建设需求文档.docx', '某某学校新官网建设需求文档.docx', '2020-05-27 14:31:51', '');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `create_time` datetime NOT NULL,
  `delete_time` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'manager', '2020-04-19 22:52:13', '');
INSERT INTO `role` VALUES ('2', 'dev', '2020-04-19 22:52:27', '');
INSERT INTO `role` VALUES ('3', 'test', '2020-04-19 22:52:38', '');
INSERT INTO `role` VALUES ('4', 'pro', '2020-04-19 22:52:50', '');
INSERT INTO `role` VALUES ('5', 'superadmin', '2020-04-19 22:53:14', '');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `task_name` varchar(100) NOT NULL COMMENT '任务名称',
  `dev_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '开发姓名，冗余',
  `dev_id` int(11) NOT NULL COMMENT '研发人员id',
  `project_id` int(11) NOT NULL COMMENT '项目id',
  `create_time` datetime NOT NULL COMMENT '开始时间',
  `pre_cost` int(11) NOT NULL COMMENT '预计花费时间',
  `end_time` datetime DEFAULT NULL COMMENT '完成时间',
  `delete_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '删除时间，默认空串',
  `task_desc` varchar(255) NOT NULL COMMENT '任务描述，备注信息等',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '用户登录注册接口', '郭鱼', '2', '12', '2020-05-15 00:37:00', '2', null, '', '用户登陆和注册的后端接口');
INSERT INTO `task` VALUES ('2', '该城市所有街道查询接口', '郭鱼', '2', '12', '2020-05-14 20:54:29', '2', null, '', '该查询接口需要包括筛选、分页功能');
INSERT INTO `task` VALUES ('3', '上传文件接口', '郭鱼', '2', '12', '2020-05-14 21:02:22', '1', null, '', '接受上传的文件，保存到服务器并返回url');
INSERT INTO `task` VALUES ('4', '下载文件接口', '郭鱼', '2', '12', '2020-05-14 21:03:26', '1', null, '', '根据前端传入的url下载该文件到本地');
INSERT INTO `task` VALUES ('5', 'xxxx', '郭鱼', '2', '12', '2020-05-14 21:09:12', '1', null, '20200514212511630', 'xxxxxxxx');
INSERT INTO `task` VALUES ('6', '数据导出为Excel接口', '郭鱼', '2', '12', '2020-05-14 21:09:12', '5', null, '', '数据导出为Excel，设置为通用接口');
INSERT INTO `task` VALUES ('7', 'vvvvvv', '郭鱼', '2', '12', '2020-05-15 00:38:04', '3', null, '20200515003817964', 'vvvvvvvvvvv');
INSERT INTO `task` VALUES ('8', '修改该街道信息接口', '小菜鸡', '4', '12', '2020-05-15 15:14:59', '1', null, '', '修改接口，post请求');
INSERT INTO `task` VALUES ('9', '登录接口', '郭鱼', '2', '17', '2020-05-25 23:01:48', '2', null, '', '要实现多种登录方式');
INSERT INTO `task` VALUES ('10', 'xxxx', '郭鱼', '2', '17', '2020-05-25 23:02:34', '2', null, '20200525230243060', 'xxxxxxxxxxxxxxxx');
INSERT INTO `task` VALUES ('11', '111111', '郭鱼', '2', '18', '2020-05-26 15:35:58', '2', null, '20200526153613158', 'aaaaaaaaaaaaaaa');
INSERT INTO `task` VALUES ('12', '123123', '郭鱼', '2', '18', '2020-05-26 15:36:30', '2', null, '', 'sfcytghunjjijn');
INSERT INTO `task` VALUES ('13', '登录接口', '郭鱼', '2', '19', '2020-05-27 14:33:47', '1', null, '', 'xxxxx');

-- ----------------------------
-- Table structure for testcase
-- ----------------------------
DROP TABLE IF EXISTS `testcase`;
CREATE TABLE `testcase` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id，自增',
  `case_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '测试用例名称',
  `test_name` varchar(50) NOT NULL DEFAULT '' COMMENT '测试人员姓名',
  `dev_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属测试人员名称',
  `pre_cost` int(11) NOT NULL COMMENT '预计花费时间，单位分钟',
  `project_id` int(11) NOT NULL COMMENT '项目id',
  `case_status` int(11) NOT NULL COMMENT '测试用例状态，0新建，1通过，2不通过',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `delete_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '删除时间，默认空串',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  `case_desc` varchar(255) NOT NULL COMMENT '测试用例说明，备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of testcase
-- ----------------------------
INSERT INTO `testcase` VALUES ('1', '所有街道查询接口测试', '周宇', '郭鱼', '30', '12', '1', '2020-05-15 20:42:05', '', null, '该接口包括查询，分页，筛选功能，请注意');
INSERT INTO `testcase` VALUES ('2', 'xxxxxx', '周宇', '郭鱼', '12', '12', '0', '2020-05-15 20:35:52', '20200515203629344', null, 'xxxxxxxxxxxxxxxxxx');
INSERT INTO `testcase` VALUES ('3', '登录接口检测', '周宇', '郭鱼', '30', '12', '1', '2020-05-18 16:11:17', '', null, '需要测试多种登陆方式');
INSERT INTO `testcase` VALUES ('4', 'xxxxxx', '周宇', '郭鱼', '40', '12', '2', '2020-05-21 20:28:01', '', null, 'xxxxxxxxxx');
INSERT INTO `testcase` VALUES ('5', '登录接口测试', '周宇', '郭鱼', '60', '17', '2', '2020-05-25 23:05:00', '', null, '包括多种登陆方式的测试');
INSERT INTO `testcase` VALUES ('6', '1212', '周宇', '郭鱼', '60', '18', '2', '2020-05-26 15:41:11', '', null, 'ftcvhubnkn');
INSERT INTO `testcase` VALUES ('7', '登录接口测试', '周宇', '郭鱼', '60', '19', '2', '2020-05-27 14:36:28', '', null, 'xxxxx');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识符id，自增',
  `phone` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `sex` int(11) DEFAULT '2' COMMENT '性别，0男，1女，2未知',
  `head_photo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `delete_time` varchar(50) DEFAULT '' COMMENT '删除时间，默认空字符串',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '17376505547', '王飞', '123456', '0', 'b.jpg', '2020-04-16 22:58:08', '');
INSERT INTO `user` VALUES ('2', '13372401174', '郭鱼', '123456', '1', 'cap.jpg', '2020-04-22 21:36:07', '');
INSERT INTO `user` VALUES ('3', '18119879606', '周宇', '123456', '2', 'e.jpg', '2020-04-22 21:37:55', '');
INSERT INTO `user` VALUES ('4', '18325339652', '菜鸡', '123456', '0', 'a.jpg', '2020-05-15 15:02:48', '');
INSERT INTO `user` VALUES ('5', '18325306830', '峰哥', '123456', '2', 'c.jpg', '2020-05-17 15:40:49', '');
INSERT INTO `user` VALUES ('6', '17376505541', '鲁班', '123456', '2', null, '2020-05-17 19:45:16', '20200517195330317');
INSERT INTO `user` VALUES ('7', '17376505548', '后裔', '123456', '2', null, '2020-05-17 19:48:18', '20200517195339468');
INSERT INTO `user` VALUES ('8', '17376521145', '伽罗', '123456', '2', 'e.jpg', '2020-05-17 19:53:11', '');
INSERT INTO `user` VALUES ('9', '17356214523', '后裔', '123456', '2', null, '2020-05-17 19:54:28', '20200517195432204');
INSERT INTO `user` VALUES ('10', '13372401175', '猪哥', '123456', '2', 'e.jpg', '2020-05-25 22:30:31', '');

-- ----------------------------
-- Table structure for user_project
-- ----------------------------
DROP TABLE IF EXISTS `user_project`;
CREATE TABLE `user_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户项目关联表主键id，自增',
  `user_id` int(11) NOT NULL COMMENT '关联的用户id',
  `project_id` int(11) NOT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `delete_time` varchar(255) DEFAULT '' COMMENT '删除时间，默认空串',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_project
-- ----------------------------
INSERT INTO `user_project` VALUES ('1', '1', '1', '2020-04-21 22:40:01', '20200515172659447');
INSERT INTO `user_project` VALUES ('2', '1', '2', '2020-04-21 22:40:15', '');
INSERT INTO `user_project` VALUES ('3', '1', '3', '2020-04-21 22:40:31', '');
INSERT INTO `user_project` VALUES ('4', '1', '4', '2020-04-21 22:40:38', '');
INSERT INTO `user_project` VALUES ('17', '1', '12', '2020-04-24 17:12:28', '');
INSERT INTO `user_project` VALUES ('18', '2', '12', '2020-04-24 17:12:28', '');
INSERT INTO `user_project` VALUES ('19', '3', '12', '2020-04-24 17:12:28', '');
INSERT INTO `user_project` VALUES ('21', '1', '14', '2020-05-12 15:01:52', '');
INSERT INTO `user_project` VALUES ('22', '1', '15', '2020-05-12 20:36:03', '20200512214040465');
INSERT INTO `user_project` VALUES ('24', '1', '15', '2020-05-12 21:40:40', '');
INSERT INTO `user_project` VALUES ('25', '2', '15', '2020-05-12 21:40:40', '');
INSERT INTO `user_project` VALUES ('26', '3', '15', '2020-05-12 21:40:40', '');
INSERT INTO `user_project` VALUES ('27', '4', '12', '2020-05-15 15:07:12', '');
INSERT INTO `user_project` VALUES ('28', '1', '1', '2020-05-15 17:26:59', '20200515173023433');
INSERT INTO `user_project` VALUES ('29', '3', '1', '2020-05-15 17:26:59', '20200515173023433');
INSERT INTO `user_project` VALUES ('30', '1', '1', '2020-05-15 17:30:23', '');
INSERT INTO `user_project` VALUES ('31', '3', '1', '2020-05-15 17:30:23', '');
INSERT INTO `user_project` VALUES ('32', '1', '16', '2020-05-16 23:01:28', '');
INSERT INTO `user_project` VALUES ('33', '2', '16', '2020-05-16 23:01:28', '');
INSERT INTO `user_project` VALUES ('34', '3', '16', '2020-05-16 23:01:28', '');
INSERT INTO `user_project` VALUES ('35', '4', '16', '2020-05-16 23:01:28', '');
INSERT INTO `user_project` VALUES ('36', '1', '17', '2020-05-25 22:32:45', '20200525223406060');
INSERT INTO `user_project` VALUES ('37', '2', '17', '2020-05-25 22:32:45', '20200525223406060');
INSERT INTO `user_project` VALUES ('38', '3', '17', '2020-05-25 22:32:45', '20200525223406060');
INSERT INTO `user_project` VALUES ('39', '10', '17', '2020-05-25 22:32:45', '20200525223406060');
INSERT INTO `user_project` VALUES ('40', '4', '17', '2020-05-25 22:32:45', '20200525223406060');
INSERT INTO `user_project` VALUES ('41', '1', '17', '2020-05-25 22:34:06', '');
INSERT INTO `user_project` VALUES ('42', '2', '17', '2020-05-25 22:34:06', '');
INSERT INTO `user_project` VALUES ('43', '3', '17', '2020-05-25 22:34:06', '');
INSERT INTO `user_project` VALUES ('44', '4', '17', '2020-05-25 22:34:06', '');
INSERT INTO `user_project` VALUES ('45', '8', '17', '2020-05-25 22:34:06', '');
INSERT INTO `user_project` VALUES ('46', '10', '17', '2020-05-25 22:34:06', '');
INSERT INTO `user_project` VALUES ('47', '1', '18', '2020-05-26 15:31:08', '');
INSERT INTO `user_project` VALUES ('48', '2', '18', '2020-05-26 15:31:08', '');
INSERT INTO `user_project` VALUES ('49', '3', '18', '2020-05-26 15:31:08', '');
INSERT INTO `user_project` VALUES ('50', '4', '18', '2020-05-26 15:31:08', '');
INSERT INTO `user_project` VALUES ('51', '8', '18', '2020-05-26 15:31:08', '');
INSERT INTO `user_project` VALUES ('52', '10', '18', '2020-05-26 15:31:08', '');
INSERT INTO `user_project` VALUES ('53', '1', '19', '2020-05-27 14:31:51', '');
INSERT INTO `user_project` VALUES ('54', '2', '19', '2020-05-27 14:31:51', '');
INSERT INTO `user_project` VALUES ('55', '3', '19', '2020-05-27 14:31:51', '');
INSERT INTO `user_project` VALUES ('56', '4', '19', '2020-05-27 14:31:51', '');
INSERT INTO `user_project` VALUES ('57', '8', '19', '2020-05-27 14:31:51', '');
INSERT INTO `user_project` VALUES ('58', '10', '19', '2020-05-27 14:31:51', '');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '唯一标识符id，自增',
  `user_id` int(11) NOT NULL COMMENT '关联的用户id',
  `role_id` int(11) NOT NULL COMMENT '关联的角色id',
  `role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '冗余，角色名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `delete_time` varchar(255) DEFAULT '' COMMENT '删除时间，默认空串',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1', 'manager', '2020-04-19 23:14:36', '');
INSERT INTO `user_role` VALUES ('2', '2', '2', 'dev', '2020-04-22 21:36:37', '');
INSERT INTO `user_role` VALUES ('3', '3', '3', 'test', '2020-04-22 21:38:11', '');
INSERT INTO `user_role` VALUES ('4', '4', '2', 'dev', '2020-05-15 15:12:59', '');
INSERT INTO `user_role` VALUES ('5', '5', '5', 'superadmin', '2020-05-17 15:41:19', '');
INSERT INTO `user_role` VALUES ('6', '8', '2', 'dev', '2020-05-17 19:53:11', '');
INSERT INTO `user_role` VALUES ('7', '9', '1', 'manager', '2020-05-17 19:54:28', '20200517195432283');
INSERT INTO `user_role` VALUES ('8', '10', '4', 'pro', '2020-05-25 22:30:31', '');
