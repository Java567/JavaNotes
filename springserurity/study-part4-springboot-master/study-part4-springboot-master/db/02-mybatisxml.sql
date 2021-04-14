/*
 Navicat MySQL Data Transfer

 Source Server         : localhost-MAMP
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost
 Source Database       : 02-mybatisxml

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : utf-8

 Date: 01/06/2020 18:35:31 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(45) COLLATE utf8_bin NOT NULL,
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `course`
-- ----------------------------
BEGIN;
INSERT INTO `course` VALUES ('1', '高等数学', '1'), ('2', '离散数学', '2'), ('3', '软件工程', '3'), ('4', '大学英语', '4'), ('5', '计算机导论', '5'), ('6', 'Java高级语言', '0');
COMMIT;

-- ----------------------------
--  Table structure for `course_student_mid`
-- ----------------------------
DROP TABLE IF EXISTS `course_student_mid`;
CREATE TABLE `course_student_mid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) DEFAULT NULL,
  `student_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `course_id_idx` (`course_id`),
  KEY `student_id_idx` (`student_id`),
  CONSTRAINT `course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Records of `course_student_mid`
-- ----------------------------
BEGIN;
INSERT INTO `course_student_mid` VALUES ('29', '1', '1'), ('30', '1', '13'), ('31', '1', '3'), ('32', '1', '4'), ('33', '1', '6'), ('34', '2', '1'), ('35', '2', '4'), ('36', '2', '6'), ('37', '2', '8'), ('38', '2', '12'), ('39', '3', '11'), ('40', '3', '15'), ('41', '3', '1'), ('42', '3', '3');
COMMIT;

-- ----------------------------
--  Table structure for `grade`
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `grade_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '年级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='年级信息表';

-- ----------------------------
--  Records of `grade`
-- ----------------------------
BEGIN;
INSERT INTO `grade` VALUES ('1', '大一'), ('2', '大二'), ('3', '大三'), ('4', '大四');
COMMIT;

-- ----------------------------
--  Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `student_name` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '学生姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(1) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `addr` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '家庭住址',
  `grade_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `grade_id` (`grade_id`),
  CONSTRAINT `grade_id` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='学生信息表';

-- ----------------------------
--  Records of `student`
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES ('1', '小乔', '18', '女', '安徽', '2'), ('3', '刘备', '35', '男', '当时的蜀地-荆州', '4'), ('4', '张飞', '18', '男', '河北', '2'), ('6', '关于', '38', '男', '山西', '4'), ('7', '赵子龙', '28', '男', '长白山赵子龙也。。。', '3'), ('8', '岳飞', '28', '男', '河北', '2'), ('11', '张三111', '32', '男', '广西', '2'), ('12', '小芳', '15', '女', '广东', '1'), ('13', '王五55', '29', '男', '合肥', '3'), ('14', '小美22', '23', '女', '黑龙江', '4'), ('15', '小莲44', '19', '女', '内蒙古', '3');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
