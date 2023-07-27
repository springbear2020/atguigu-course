-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: course_live
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `course_live`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `course_live` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `course_live`;

--
-- Table structure for table `live_course`
--

DROP TABLE IF EXISTS `live_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `live_course` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_id` bigint DEFAULT NULL COMMENT '分类Id',
  `course_name` varchar(64) NOT NULL DEFAULT '' COMMENT '直播名称',
  `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '直播开始时间',
  `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '直播结束时间',
  `cover` varchar(255) DEFAULT NULL,
  `course_id` bigint NOT NULL DEFAULT '0' COMMENT '课程id',
  `teacher_id` bigint DEFAULT NULL COMMENT '主播老师id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='直播课程表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live_course`
--

LOCK TABLES `live_course` WRITE;
/*!40000 ALTER TABLE `live_course` DISABLE KEYS */;
INSERT INTO `live_course` (`id`, `subject_id`, `course_name`, `start_time`, `end_time`, `cover`, `course_id`, `teacher_id`, `create_time`, `update_time`, `is_deleted`) VALUES (9,NULL,'Vue','2022-11-01 12:44:22','2022-11-01 12:47:25','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/56ff3ac7b80e42d196d2ea39cad97fa3.png',3184287,1,'2022-11-01 12:47:55','2022-11-01 12:47:55',0),(10,NULL,'Java 基础','2022-11-01 12:48:56','2022-11-01 12:54:01','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/11/01/e73f4c093d954764a5fa60299a5154b5.png',3184289,4,'2022-11-01 12:49:44','2022-11-01 12:49:44',0),(11,NULL,'MyBatis','2022-11-02 12:44:20','2022-11-02 12:55:23','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/11/02/18768b97e0f24de196cdce797e5f98af.png',3185665,6,'2022-11-02 12:45:07','2022-11-02 12:47:58',0);
/*!40000 ALTER TABLE `live_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `live_course_account`
--

DROP TABLE IF EXISTS `live_course_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `live_course_account` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `live_course_id` bigint NOT NULL DEFAULT '0' COMMENT '直播课程id',
  `live_account` varchar(20) DEFAULT NULL COMMENT '主播账号（欢拓系统的主播id）',
  `live_password` varchar(32) DEFAULT NULL COMMENT '主播密码',
  `live_key` varchar(64) NOT NULL DEFAULT '' COMMENT '主播登录秘钥',
  `admin_key` varchar(32) NOT NULL DEFAULT '' COMMENT '助教登录秘钥',
  `user_key` varchar(32) NOT NULL DEFAULT '' COMMENT '学生登录秘钥',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='直播课程账号表（受保护信息）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live_course_account`
--

LOCK TABLES `live_course_account` WRITE;
/*!40000 ALTER TABLE `live_course_account` DISABLE KEYS */;
INSERT INTO `live_course_account` (`id`, `live_course_id`, `live_account`, `live_password`, `live_key`, `admin_key`, `user_key`, `create_time`, `update_time`, `is_deleted`) VALUES (9,9,'697503','123123','1707','4012','9422','2022-11-01 12:47:55','2022-11-01 12:47:55',0),(10,10,'697709','123123','2467','4886','7358','2022-11-01 12:49:44','2022-11-01 12:49:44',0),(11,11,'698041','123123','2918','4036','8396','2022-11-02 12:45:07','2022-11-02 12:47:44',1);
/*!40000 ALTER TABLE `live_course_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `live_course_config`
--

DROP TABLE IF EXISTS `live_course_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `live_course_config` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `live_course_id` bigint NOT NULL DEFAULT '0' COMMENT '直播课程id',
  `page_view_mode` tinyint NOT NULL DEFAULT '0' COMMENT '界面模式 1全屏模式 0二分屏 2课件模式',
  `number_enable` tinyint NOT NULL DEFAULT '0' COMMENT '是否开启 观看人数 0否 1是',
  `store_enable` tinyint NOT NULL DEFAULT '0' COMMENT '商城是否开启 0未开启 1开启',
  `store_type` tinyint NOT NULL DEFAULT '1' COMMENT '1商品列表,2商城链接,3商城二维码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='直播课程配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live_course_config`
--

LOCK TABLES `live_course_config` WRITE;
/*!40000 ALTER TABLE `live_course_config` DISABLE KEYS */;
INSERT INTO `live_course_config` (`id`, `live_course_id`, `page_view_mode`, `number_enable`, `store_enable`, `store_type`, `create_time`, `update_time`, `is_deleted`) VALUES (8,9,1,1,1,1,'2022-11-02 13:59:53','2022-11-02 13:59:53',0),(9,10,1,1,1,1,'2022-11-02 14:11:11','2022-11-02 14:11:11',0),(10,11,1,1,1,1,'2022-11-02 14:11:18','2022-11-02 14:11:18',0);
/*!40000 ALTER TABLE `live_course_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `live_course_description`
--

DROP TABLE IF EXISTS `live_course_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `live_course_description` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `live_course_id` bigint DEFAULT NULL,
  `description` text COMMENT '课程简介',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程简介';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live_course_description`
--

LOCK TABLES `live_course_description` WRITE;
/*!40000 ALTER TABLE `live_course_description` DISABLE KEYS */;
INSERT INTO `live_course_description` (`id`, `live_course_id`, `description`, `create_time`, `update_time`, `is_deleted`) VALUES (20,9,'Vue 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。','2022-11-01 12:47:55','2022-11-01 12:47:55',0),(21,10,'本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。','2022-11-01 12:49:44','2022-11-01 12:49:44',0),(22,11,'MyBatis 本是 Apache 的一个开源项目 iBatis，2010 年这个项目由 apache software foundation 迁移到了 google code，并且改名为 MyBatis。2013 年 11 月迁移到 Github。iBATI S一词来源于 “internet” 和 “abatis” 的组合，是一个基于 Java 的持久层框架。','2022-11-02 12:45:07','2022-11-02 12:47:44',1);
/*!40000 ALTER TABLE `live_course_description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `live_course_goods`
--

DROP TABLE IF EXISTS `live_course_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `live_course_goods` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `live_course_id` bigint NOT NULL DEFAULT '0' COMMENT '直播课程id',
  `goods_id` bigint NOT NULL DEFAULT '0' COMMENT '推荐点播课程id',
  `name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `img` varchar(255) DEFAULT NULL COMMENT '图片',
  `price` decimal(10,0) DEFAULT NULL COMMENT '商品现价',
  `original_price` decimal(10,0) DEFAULT NULL COMMENT '商品原价',
  `tab` tinyint DEFAULT NULL COMMENT '商品标签',
  `url` varchar(255) DEFAULT NULL COMMENT '商品链接',
  `put_away` varchar(255) DEFAULT NULL COMMENT '商品状态：0下架，1上架，2推荐',
  `pay` tinyint DEFAULT NULL COMMENT '购买模式(1,链接购买 2,二维码购买)',
  `qrcode` varchar(255) DEFAULT NULL COMMENT '商品二维码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='直播课程关联推荐表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live_course_goods`
--

LOCK TABLES `live_course_goods` WRITE;
/*!40000 ALTER TABLE `live_course_goods` DISABLE KEYS */;
INSERT INTO `live_course_goods` (`id`, `live_course_id`, `goods_id`, `name`, `img`, `price`, `original_price`, `tab`, `url`, `put_away`, `pay`, `qrcode`, `create_time`, `update_time`, `is_deleted`) VALUES (12,9,1,'Spark','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/5866d3352bb046db987561fca52ae234.png',10,10,1,'http://glkt-api.atguigu.cn/#/courseInfo/1',NULL,1,'','2022-11-02 13:59:53','2022-11-02 14:10:27',1),(13,9,2,'Java 基础','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/3a886d0ebc3b4fddb0c9af3f1ad0abf0.png',0,0,1,'http://glkt-api.atguigu.cn/#/courseInfo/2',NULL,1,'','2022-11-02 13:59:53','2022-11-02 14:10:27',1),(14,9,10,'Vue','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/56ff3ac7b80e42d196d2ea39cad97fa3.png',67,67,1,'http://glkt-api.atguigu.cn/#/courseInfo/10',NULL,1,'','2022-11-02 13:59:53','2022-11-02 14:10:27',1),(15,9,12,'SpringMVC','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/6967fff1544d4b629af1e61b6526d8d9.png',100,100,1,'http://glkt-api.atguigu.cn/#/courseInfo/12',NULL,1,'','2022-11-02 13:59:53','2022-11-02 14:10:27',1),(16,9,14,'JavaWeb','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/f7b592e6bb9d49f288cea7693e0fcf01.png',0,0,1,'http://glkt-api.atguigu.cn/#/courseInfo/14',NULL,1,'','2022-11-02 13:59:53','2022-11-02 14:10:27',1),(17,9,15,'MyBatis','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/5d69442a75fc4ef49ed8bd09e8478cf4.png',0,0,1,'http://glkt-api.atguigu.cn/#/courseInfo/15',NULL,1,'','2022-11-02 13:59:53','2022-11-02 14:10:27',1),(18,9,14,'JavaWeb','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/f7b592e6bb9d49f288cea7693e0fcf01.png',0,0,1,'http://glkt-api.atguigu.cn/#/courseInfo/14',NULL,1,'','2022-11-02 13:59:53','2022-11-02 14:10:47',1),(19,9,15,'MyBatis','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/5d69442a75fc4ef49ed8bd09e8478cf4.png',0,0,1,'http://glkt-api.atguigu.cn/#/courseInfo/15',NULL,1,'','2022-11-02 13:59:53','2022-11-02 14:10:47',1),(20,9,10,'Vue','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/56ff3ac7b80e42d196d2ea39cad97fa3.png',67,67,1,'http://glkt-api.atguigu.cn/#/courseInfo/10',NULL,1,'','2022-11-02 14:10:47','2022-11-02 14:10:57',1),(21,9,1,'Spark','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/5866d3352bb046db987561fca52ae234.png',10,10,1,'http://glkt-api.atguigu.cn/#/courseInfo/1',NULL,1,'','2022-11-02 14:10:47','2022-11-02 14:10:57',1),(22,9,10,'Vue','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/56ff3ac7b80e42d196d2ea39cad97fa3.png',67,67,1,'http://glkt-api.atguigu.cn/#/courseInfo/10',NULL,1,'','2022-11-02 14:10:47','2022-11-02 14:11:25',1),(23,9,1,'Spark','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/5866d3352bb046db987561fca52ae234.png',10,10,1,'http://glkt-api.atguigu.cn/#/courseInfo/1',NULL,1,'','2022-11-02 14:10:47','2022-11-02 14:11:25',1),(24,10,1,'Spark','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/5866d3352bb046db987561fca52ae234.png',10,10,1,'http://glkt-api.atguigu.cn/#/courseInfo/1',NULL,1,'','2022-11-02 14:11:11','2022-11-02 14:12:01',1),(25,10,10,'Vue','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/56ff3ac7b80e42d196d2ea39cad97fa3.png',67,67,1,'http://glkt-api.atguigu.cn/#/courseInfo/10',NULL,1,'','2022-11-02 14:11:11','2022-11-02 14:12:01',1),(26,10,1,'Spark','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/5866d3352bb046db987561fca52ae234.png',10,10,1,'http://glkt-api.atguigu.cn/#/courseInfo/1',NULL,1,'','2022-11-02 14:11:18','2022-11-02 14:12:01',1),(27,10,10,'Vue','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/56ff3ac7b80e42d196d2ea39cad97fa3.png',67,67,1,'http://glkt-api.atguigu.cn/#/courseInfo/10',NULL,1,'','2022-11-02 14:11:18','2022-11-02 14:12:01',1),(28,9,10,'Vue','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/56ff3ac7b80e42d196d2ea39cad97fa3.png',67,67,1,'http://glkt-api.atguigu.cn/#/courseInfo/10',NULL,1,'','2022-11-02 14:10:47','2022-11-02 14:11:30',1),(29,9,1,'Spark','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/5866d3352bb046db987561fca52ae234.png',10,10,1,'http://glkt-api.atguigu.cn/#/courseInfo/1',NULL,1,'','2022-11-02 14:10:47','2022-11-02 14:11:30',1),(30,9,10,'Vue','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/56ff3ac7b80e42d196d2ea39cad97fa3.png',67,67,1,'http://glkt-api.atguigu.cn/#/courseInfo/10',NULL,1,'','2022-11-02 14:10:47','2022-11-02 14:10:47',0),(31,9,1,'Spark','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/5866d3352bb046db987561fca52ae234.png',10,10,1,'http://glkt-api.atguigu.cn/#/courseInfo/1',NULL,1,'','2022-11-02 14:10:47','2022-11-02 14:10:47',0),(32,10,1,'Spark','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/5866d3352bb046db987561fca52ae234.png',10,10,1,'http://glkt-api.atguigu.cn/#/courseInfo/1',NULL,1,'','2022-11-02 14:11:11','2022-11-02 14:11:11',0),(33,10,10,'Vue','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/56ff3ac7b80e42d196d2ea39cad97fa3.png',67,67,1,'http://glkt-api.atguigu.cn/#/courseInfo/10',NULL,1,'','2022-11-02 14:11:11','2022-11-02 14:11:11',0),(34,11,1,'Spark','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/5866d3352bb046db987561fca52ae234.png',10,10,1,'http://glkt-api.atguigu.cn/#/courseInfo/1',NULL,1,'','2022-11-02 14:12:19','2022-11-02 14:12:19',0),(35,11,10,'Vue','https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/56ff3ac7b80e42d196d2ea39cad97fa3.png',67,67,1,'http://glkt-api.atguigu.cn/#/courseInfo/10',NULL,1,'','2022-11-02 14:12:19','2022-11-02 14:12:19',0);
/*!40000 ALTER TABLE `live_course_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `live_visitor`
--

DROP TABLE IF EXISTS `live_visitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `live_visitor` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `live_course_id` bigint NOT NULL DEFAULT '0' COMMENT '直播课程id',
  `course_name` varchar(100) DEFAULT NULL,
  `user_id` varchar(50) NOT NULL DEFAULT '0' COMMENT '来访者用户id',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `join_time` varchar(30) DEFAULT NULL COMMENT '进入时间',
  `leave_time` varchar(30) DEFAULT NULL COMMENT '离开的时间',
  `location` varchar(50) DEFAULT NULL COMMENT '用户地理位置',
  `duration` bigint DEFAULT NULL COMMENT '用户停留的时间(单位：秒)',
  `duration_time` varchar(30) DEFAULT NULL COMMENT '用户停留时间(时分秒)',
  `live_visitor_id` varchar(50) DEFAULT NULL COMMENT '平台来访者id，去重使用',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_live_visitor_id` (`live_visitor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='直播来访者记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live_visitor`
--

LOCK TABLES `live_visitor` WRITE;
/*!40000 ALTER TABLE `live_visitor` DISABLE KEYS */;
/*!40000 ALTER TABLE `live_visitor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-03 11:06:48
