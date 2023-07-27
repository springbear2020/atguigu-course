-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: course_vod
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
-- Current Database: `course_vod`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `course_vod` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `course_vod`;

--
-- Table structure for table `chapter`
--

DROP TABLE IF EXISTS `chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chapter` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` bigint NOT NULL DEFAULT '0' COMMENT '课程ID',
  `title` varchar(50) NOT NULL COMMENT '章节名称',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '显示排序',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter`
--

LOCK TABLES `chapter` WRITE;
/*!40000 ALTER TABLE `chapter` DISABLE KEYS */;
INSERT INTO `chapter` VALUES (85,10,'一、基础语法',1,'2022-10-28 13:07:56','2022-10-28 13:07:56',0),(86,10,'二、组件化',2,'2022-10-28 13:08:05','2022-10-28 13:08:05',0),(87,10,'三、脚手架',3,'2022-10-28 13:08:16','2022-10-28 13:08:16',0),(88,10,'四、vuex 插件',4,'2022-10-28 13:08:27','2022-10-28 13:08:27',0),(89,10,'五、vue-router 插件',5,'2022-10-28 13:08:37','2022-10-28 13:09:25',0);
/*!40000 ALTER TABLE `chapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` bigint NOT NULL DEFAULT '0' COMMENT '课程id',
  `teacher_id` bigint NOT NULL DEFAULT '0' COMMENT '讲师id',
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '会员id',
  `nickname` varchar(50) DEFAULT NULL COMMENT '会员昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '会员头像',
  `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_teacher_id` (`teacher_id`),
  KEY `idx_member_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `teacher_id` bigint NOT NULL DEFAULT '0' COMMENT '课程讲师ID',
  `subject_id` bigint NOT NULL DEFAULT '0' COMMENT '课程专业ID',
  `subject_parent_id` bigint NOT NULL DEFAULT '0' COMMENT '课程专业父级ID',
  `title` varchar(50) NOT NULL COMMENT '课程标题',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '课程销售价格，设置为0则可免费观看',
  `lesson_num` int unsigned NOT NULL DEFAULT '0' COMMENT '总课时',
  `duration_sum` int NOT NULL DEFAULT '0' COMMENT '视频总时长（秒）',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程封面图片路径',
  `buy_count` bigint unsigned NOT NULL DEFAULT '0' COMMENT '销售数量',
  `view_count` bigint unsigned NOT NULL DEFAULT '0' COMMENT '浏览数量',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '课程状态 0未发布 1已发布',
  `publish_time` datetime DEFAULT NULL COMMENT '课程发布时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`),
  KEY `idx_subject_id` (`subject_id`),
  KEY `idx_teacher_id` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,2,15,14,'Spark',0.00,2,100000,'https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/5866d3352bb046db987561fca52ae234.png',1679,34634,1,'2021-11-05 11:30:14','2021-11-04 19:05:16','2022-10-28 13:02:04',0),(2,4,2,1,'Java 基础',998.00,2,100000,'https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/3a886d0ebc3b4fddb0c9af3f1ad0abf0.png',5999,10048,1,'2022-10-24 19:33:05','2021-11-04 19:05:16','2022-10-28 13:00:44',0),(10,1,4,3,'Vue',9999.00,100,0,'https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/56ff3ac7b80e42d196d2ea39cad97fa3.png',0,0,1,'2022-10-28 21:17:02','2022-10-23 01:28:31','2022-10-28 13:17:02',0),(12,6,2,1,'SpringMVC',365.00,0,0,'https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/6967fff1544d4b629af1e61b6526d8d9.png',0,0,1,'2022-10-23 09:36:49','2022-10-23 01:36:34','2022-10-28 13:02:19',0),(14,5,2,1,'JavaWeb',0.00,3,0,'https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/f7b592e6bb9d49f288cea7693e0fcf01.png',0,0,1,'2022-10-28 20:05:36','2022-10-28 12:04:07','2022-10-28 12:49:07',0),(15,2,0,1,'MyBatis',0.00,4,0,'https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/5d69442a75fc4ef49ed8bd09e8478cf4.png',0,0,1,'2022-10-28 20:40:13','2022-10-28 12:40:06','2022-10-28 12:40:12',0);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_collect`
--

DROP TABLE IF EXISTS `course_collect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_collect` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` bigint NOT NULL DEFAULT '0' COMMENT '课程讲师ID',
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '会员ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程收藏';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_collect`
--

LOCK TABLES `course_collect` WRITE;
/*!40000 ALTER TABLE `course_collect` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_collect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_description`
--

DROP TABLE IF EXISTS `course_description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_description` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` bigint DEFAULT NULL,
  `description` text COMMENT '课程简介',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程简介';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_description`
--

LOCK TABLES `course_description` WRITE;
/*!40000 ALTER TABLE `course_description` DISABLE KEYS */;
INSERT INTO `course_description` VALUES (1,1,'Spark是一个通用的大数据分析引擎,具有高性能、易用和普遍性等特点。 架构 Spark架构如下图所示,基于Spark Core构建了Spark SQL、Spark Streaming、MLlib和Graphx四个主要组成。','2021-11-04 19:29:33','2022-10-28 13:00:54',0),(2,2,'本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。','2021-11-04 19:30:07','2022-10-28 13:18:57',0),(10,10,'Vue 是一套用于构建用户界面的渐进式框架。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注视图层，不仅易于上手，还便于与第三方库或既有项目整合。','2022-10-23 01:28:31','2022-10-28 12:22:33',0),(12,12,'Spring MVC 属于 SpringFrameWork 的后续产品，已经融合在 Spring Web Flow 里面。Spring 框架提供了构建 Web 应用程序的全功能 MVC 模块。使用 Spring 可插入的 MVC 架构，从而在使用 Spring 进行 WEB 开发时，可以选择使用 Spring 的Spring MVC 框架或集成其他 MVC 开发框架。','2022-10-23 01:36:34','2022-10-28 13:22:07',0),(14,14,'Java Web 是用 Java 技术来解决相关 web 互联网领域的技术栈。web 包括：web 服务端和 web 客户端两部分。Java 在客户端的应用有Java Applet，不过使用得很少，Java 在服务器端的应用非常的丰富，比如 Servlet，JSP、第三方框架等等。Java 技术对 Web 领域的发展注入了强大的动力。','2022-10-28 12:04:07','2022-10-28 12:04:07',0),(15,15,'MyBatis 本是 Apache 的一个开源项目 iBatis，2010 年这个项目由 apache software foundation 迁移到了 google code，并且改名为 MyBatis。2013 年 11 月迁移到 Github。iBATI S一词来源于 “internet” 和 “abatis” 的组合，是一个基于 Java 的持久层框架。','2022-10-28 12:40:06','2022-10-28 12:40:06',0);
/*!40000 ALTER TABLE `course_description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '主键',
  `title` varchar(10) NOT NULL COMMENT '类别名称',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父ID',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程科目';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (0,'Spring',1,15,'2022-10-28 12:35:27','2022-10-28 12:35:27',0),(1,'后端开发',0,1,'2019-09-29 07:47:25','2019-09-29 07:47:25',0),(2,'Java',1,1,'2019-09-29 07:47:25','2019-09-29 07:47:25',0),(3,'前端开发',0,3,'2019-09-29 07:47:25','2019-09-29 07:47:25',0),(4,'JavaScript',3,4,'2019-09-29 07:47:25','2019-09-29 07:47:25',0),(14,'大数据',0,11,'2019-09-29 07:47:25','2019-09-29 07:47:25',0),(22,'HTML/CSS',3,3,'2019-09-30 08:19:22','2022-10-28 12:35:06',0),(23,'Spark',14,14,'2022-10-28 12:37:11','2022-10-28 12:37:11',0);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '讲师姓名',
  `intro` varchar(500) NOT NULL DEFAULT '' COMMENT '讲师简介',
  `career` varchar(500) DEFAULT NULL COMMENT '讲师资历,一句话说明讲师',
  `level` int unsigned NOT NULL COMMENT '头衔 1高级讲师 2首席讲师',
  `avatar` varchar(255) DEFAULT NULL COMMENT '讲师头像',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `join_date` date DEFAULT NULL COMMENT '入驻时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='讲师';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'张老师','曾就职中兴网信科技有限公司，任智慧城市方向研发组长。曾多次参与：中国移动ERP系统、万盛数据可视化、济宁大数据环境污染物侦测系统、智慧沈河等大型项目的研发工作，拥有丰富的开发经验。热衷于web开发，熟练H5、CSS3、JavaScript、React、Vue等前端技术；熟练Java，MySQL、MongoDB、Express、NodeJS等后端技术；对前后端主流框架的理论与实际应用均有深入的研究；授课认真细致，对知识把握精准，擅长把知识的难点变成易懂的例子；传授给学生技术的同时，会把丰富的开发经验带到课堂，深受学生喜爱。','曾就职中兴网信科技有限公司，任智慧城市方向研发组长。曾多次参与：中国移动ERP系统、万盛数据可视化、济宁大数据环境污染物侦测系统、智慧沈河等大型项目的研发工作，拥有丰富的开发经验。热衷于web开发，熟练H5、CSS3、JavaScript、React、Vue等前端技术；熟练Java，MySQL、MongoDB、Express、NodeJS等后端技术；对前后端主流框架的理论与实际应用均有深入的研究；授课认真细致，对知识把握精准，擅长把知识的难点变成易懂的例子；传授给学生技术的同时，会把丰富的开发经验带到课堂，深受学生喜爱。',2,'https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/f5d036cf9a264e6fbc1032f802d97b6f.jpg',0,'2021-11-02','2021-11-04 19:18:36','2022-10-22 08:27:44',0),(2,'李老师','具有多年项目开发和教育培训经验，曾先后任职于致远协创、用友科技等知名企业，历任项目经理、架构师等，精通JavaEE、Android、 C++、C#等开发语言，主持并参与Android SDK开发，熟练掌握JavaEE技术及架构体系、SpringMVC、Spring、 Mybatis、Redis、Struts2、Hibernate等框架。','\n具有多年项目开发和教育培训经验，曾先后任职于致远协创、用友科技等知名企业，历任项目经理、架构师等，精通JavaEE、Android、 C++、C#等开发语言，主持并参与Android SDK开发，熟练掌握JavaEE技术及架构体系、SpringMVC、Spring、 Mybatis、Redis、Struts2、Hibernate等框架。',1,'https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/48085cc69a8d4eaa84be38dc83a36437.jpg',0,'2021-11-02','2021-11-04 19:18:51','2022-10-28 13:22:46',0),(4,'雷老师','具有多年软件开发经验，熟悉 Java、C/C++等多种开发语言，对主流框架Spring、Struts2、Hibernate、MyBatis、SpringMVC等具有丰富的开发经验。曾参与大型物联网系统（智能物流）、智慧城市系统的开发，并主持开发某社交软件的移动端（Android/iOS）、服务端（openfire）、桌面端（swing）、网页端（webIM），对即时通讯、图像处理、流媒体领域等有深入研究。','具有多年软件开发经验，熟悉 Java、C/C++等多种开发语言，对主流框架Spring、Struts2、Hibernate、MyBatis、SpringMVC等具有丰富的开发经验。曾参与大型物联网系统（智能物流）、智慧城市系统的开发，并主持开发某社交软件的移动端（Android/iOS）、服务端（openfire）、桌面端（swing）、网页端（webIM），对即时通讯、图像处理、流媒体领域等有深入研究。',2,'https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/59c29e916b664a9d9aa52b75bf25467c.jpg',3,'2021-11-02','2021-11-07 21:51:21','2022-10-28 13:22:46',0),(5,'钱老师',' 尚硅谷高级讲师，具有多年的教学和项目开发经验。曾参与国家电网内蒙古供电企业一体化管理信息系统的研发、国土资源局的国土资源非空间数据服务系统的研发、赛迪时代公司内部项目通用基础组件的设计与研发，并为新华南方等众多IT企业储备人才及广东、山东等多所高校大学生进行实训授课。精通Oracle、MySQL、SSH、MyBatis、JBPM等众多框架，现专注于JavaSE技术的研究、教学和推广。“细心，耐心，用心”是一贯坚持的工作态度，追求“润物细无声”的教育方式。','尚硅谷高级讲师，具有多年的教学和项目开发经验。曾参与国家电网内蒙古供电企业一体化管理信息系统的研发、国土资源局的国土资源非空间数据服务系统的研发、赛迪时代公司内部项目通用基础组件的设计与研发，并为新华南方等众多IT企业储备人才及广东、山东等多所高校大学生进行实训授课。精通Oracle、MySQL、SSH、MyBatis、JBPM等众多框架，现专注于JavaSE技术的研究、教学和推广。“细心，耐心，用心”是一贯坚持的工作态度，追求“润物细无声”的教育方式。',1,'https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/401d899f15574a3f87d123b2305d14b0.jpg',5,'2021-11-01','2021-11-22 05:26:58','2022-10-28 12:30:45',0),(6,'杨老师','具多年软件开发经验，曾参与开发《国家电网仓储自动化系统》等项目。对JavaEE 技术体系结构、原理有深刻理解，精通jQuery等JavaScript框架，以及多种主流Java框架，Maven 等构建工具。 课堂教学讲究点点滴滴，层次明确，深入浅出，以学生学习到真正的技术为目标，结合生活、实际、技能、原理等多个方面，为学生剖析各个知识点。','具多年软件开发经验，曾参与开发《国家电网仓储自动化系统》等项目。对JavaEE 技术体系结构、原理有深刻理解，精通jQuery等JavaScript框架，以及多种主流Java框架，Maven 等构建工具。 课堂教学讲究点点滴滴，层次明确，深入浅出，以学生学习到真正的技术为目标，结合生活、实际、技能、原理等多个方面，为学生剖析各个知识点。',2,'https://course-1308741720.cos.ap-chongqing.myqcloud.com/2022/10/28/ea6e2f93e3aa4c41afda98afe818a200.jpg',2,'2022-10-28','2022-10-28 12:30:17','2022-10-28 12:30:45',0);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` bigint NOT NULL DEFAULT '0' COMMENT '课程ID',
  `chapter_id` bigint NOT NULL DEFAULT '0' COMMENT '章节ID',
  `title` varchar(50) NOT NULL COMMENT '节点名称',
  `video_source_id` varchar(100) DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) DEFAULT NULL COMMENT '原始文件名称',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `play_count` bigint unsigned NOT NULL DEFAULT '0' COMMENT '播放次数',
  `is_free` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否可以试听：0收费 1免费',
  `duration` float NOT NULL DEFAULT '0' COMMENT '视频时长（秒）',
  `size` bigint unsigned NOT NULL DEFAULT '0' COMMENT '视频源文件大小（字节）',
  `version` bigint unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_chapter_id` (`chapter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程视频';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (57,10,85,'1.1 概述','387702307638353553','demo.mp4',1,0,0,0,0,1,0,'2022-10-28 13:10:22','2022-10-28 13:10:22',0),(58,10,85,'1.2 入门案例',NULL,NULL,2,0,1,0,0,1,0,'2022-10-28 13:10:31','2022-10-28 13:10:31',0),(59,10,85,'1.3 el 与 data',NULL,NULL,3,0,0,0,0,1,0,'2022-10-28 13:10:48','2022-10-28 13:11:46',0),(60,10,86,'2.1 概述',NULL,NULL,1,0,1,0,0,1,0,'2022-10-28 13:10:59','2022-10-28 13:10:59',0),(61,10,86,'2.2 组件的定义和使用',NULL,NULL,2,0,0,0,0,1,0,'2022-10-28 13:11:06','2022-10-28 13:11:06',0),(62,10,86,'2.3 VueComponent',NULL,NULL,3,0,0,0,0,1,0,'2022-10-28 13:11:16','2022-10-28 13:11:16',0);
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_visitor`
--

DROP TABLE IF EXISTS `video_visitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_visitor` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` bigint DEFAULT NULL COMMENT '课程id',
  `video_id` bigint NOT NULL DEFAULT '0' COMMENT '视频id',
  `user_id` varchar(50) NOT NULL DEFAULT '0' COMMENT '来访者用户id',
  `nick_name` varchar(100) DEFAULT NULL COMMENT '昵称',
  `join_time` varchar(30) DEFAULT NULL COMMENT '进入时间',
  `leave_time` varchar(30) DEFAULT NULL COMMENT '离开的时间',
  `duration` bigint DEFAULT NULL COMMENT '用户停留的时间(单位：秒)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频来访者记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_visitor`
--

LOCK TABLES `video_visitor` WRITE;
/*!40000 ALTER TABLE `video_visitor` DISABLE KEYS */;
INSERT INTO `video_visitor` VALUES (1,10,37,'1','晴天','2022-10-20 18:58:23.67','2022-10-29 18:58:23.67',4,'2021-11-22 05:39:27','2022-10-28 13:27:11',0),(2,10,38,'1','晴天','2022-10-21 18:58:23.67','2022-10-29 18:58:23.67',696,'2021-11-22 05:39:32','2022-10-28 13:27:11',0),(3,10,26,'1','晴天','2022-10-22 18:58:23.67','2022-10-29 18:58:23.67',5,'2021-11-23 02:09:47','2022-10-28 13:27:11',0),(4,10,38,'24','简','2022-10-23 18:58:23.67','2022-10-29 18:58:23.67',2,'2021-11-23 02:14:53','2022-10-28 13:27:11',0),(5,10,37,'27','******','2022-10-24 18:58:23.67','2022-10-29 18:58:23.67',3,'2021-11-23 02:54:29','2022-10-28 13:27:11',0),(6,10,37,'1','晴天','2022-10-25 18:58:23.67','2022-10-29 18:58:23.67',33,'2021-11-23 02:55:12','2022-10-28 13:27:11',0),(7,10,38,'1','晴天','2022-10-26 18:58:23.67','2022-10-29 18:58:23.67',706,'2021-11-23 02:55:13','2022-10-28 13:27:11',0),(8,10,37,'1','晴天','2022-10-27 18:58:23.67','2022-10-29 18:58:23.67',13,'2021-11-23 02:56:13','2022-10-28 13:27:11',0),(9,10,39,'1','晴天','2022-10-28 18:58:23.67','2022-10-29 18:58:23.67',313,'2021-11-23 02:56:21','2022-10-28 13:27:11',0),(10,10,40,'1','晴天','2022-10-24 18:58:23.67','2022-10-29 18:58:23.67',202,'2021-11-23 02:56:32','2022-10-28 13:27:56',0),(11,10,40,'1','晴天','2022-10-25 18:58:23.67','2022-10-29 18:58:23.67',114,'2021-11-23 02:58:23','2022-10-28 13:27:56',0),(12,10,40,'1','晴天','2022-10-22 18:58:23.67','2022-10-29 18:58:23.67',115,'2021-11-23 18:11:58','2022-10-28 13:27:56',0),(13,10,38,'1','晴天','2022-10-23 18:58:23.67','2022-10-29 18:58:23.67',943,'2021-11-25 17:12:12','2022-10-28 13:27:56',0),(14,10,15,'1','晴天','2022-10-29 18:58:23.67','2022-10-29 18:58:23.67',304,'2021-11-26 01:07:25','2022-10-28 13:27:11',0),(15,10,30,'1','晴天','2022-10-27 18:58:23.67','2022-10-29 18:58:23.67',2,'2021-11-26 01:14:35','2022-10-28 13:27:56',0),(16,10,37,'29',NULL,'2022-10-27 18:58:23.67','2022-10-29 18:58:23.67',3,'2021-11-26 02:38:39','2022-10-28 13:27:56',0),(17,10,38,'1','晴天','2022-10-23 18:58:23.67','2022-10-29 18:58:23.67',944,'2021-11-26 16:02:41','2022-10-28 13:27:56',0),(18,10,33,'1','晴天','2022-10-22 18:58:23.67','2022-10-29 18:58:23.67',11,'2021-11-30 21:49:36','2022-10-28 13:27:56',0),(19,10,33,'1','晴天','2022-10-23 18:58:23.67','2022-10-29 18:58:23.67',6,'2021-11-30 21:49:36','2022-10-28 13:27:56',0),(20,10,33,'1','晴天','2022-10-26 18:58:23.67','2022-10-29 18:58:23.67',1,'2021-11-30 21:49:37','2022-10-28 13:27:56',0),(21,10,34,'1','晴天','2022-10-25 18:58:23.67','2022-10-29 18:58:23.67',13,'2021-11-30 21:52:02','2022-10-28 13:27:56',0),(22,10,38,'1','晴天','2022-10-22 18:58:23.67','2022-10-29 18:58:23.67',1,'2021-12-16 01:26:31','2022-10-28 13:27:56',0),(23,10,37,'27','我是','2022-10-24 18:58:23.67','2022-10-29 18:58:23.67',95,'2021-12-28 03:42:31','2022-10-28 13:27:56',0);
/*!40000 ALTER TABLE `video_visitor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `video_visitor_max_view`
--

DROP TABLE IF EXISTS `video_visitor_max_view`;
/*!50001 DROP VIEW IF EXISTS `video_visitor_max_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `video_visitor_max_view` AS SELECT 
 1 AS `max_id`,
 1 AS `course_id`,
 1 AS `video_id`,
 1 AS `user_id`*/;
SET character_set_client = @saved_cs_client;

--
-- Current Database: `course_vod`
--

USE `course_vod`;

--
-- Final view structure for view `video_visitor_max_view`
--

/*!50001 DROP VIEW IF EXISTS `video_visitor_max_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`shangguigu`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `video_visitor_max_view` AS select max(`video_visitor`.`id`) AS `max_id`,`video_visitor`.`course_id` AS `course_id`,`video_visitor`.`video_id` AS `video_id`,`video_visitor`.`user_id` AS `user_id` from `video_visitor` group by `video_visitor`.`course_id`,`video_visitor`.`video_id`,`video_visitor`.`user_id` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-29  8:05:10
