-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: course_acl
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
-- Current Database: `course_acl`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `course_acl` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `course_acl`;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '会员id',
  `username` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '密码',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机',
  `ware_id` bigint NOT NULL DEFAULT '0' COMMENT '仓库id（默认为：0为平台用户）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uname` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`id`, `username`, `password`, `name`, `phone`, `ware_id`, `create_time`, `update_time`, `is_deleted`) VALUES (1,'admin','96e79218965eb72c92a549dd5a330112','admin',NULL,0,'2021-05-31 10:08:43','2021-05-31 10:08:56',0),(2,'shangguigu','96e79218965eb72c92a549dd5a330112','shangguigu',NULL,0,'2021-06-01 00:46:22','2021-11-30 22:20:29',0),(3,'chengdu','96e79218965eb72c92a549dd5a330112','chengdu',NULL,1,'2021-06-18 09:18:29','2021-06-18 09:20:08',0),(4,'atguigu','e10adc3949ba59abbe56e057f20f883e','游客',NULL,0,'2021-09-27 01:37:39','2021-09-27 01:39:14',0);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_login_log`
--

DROP TABLE IF EXISTS `admin_login_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_login_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `admin_id` bigint DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_agent` varchar(100) DEFAULT NULL COMMENT '浏览器登录类型',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='后台用户登录日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_login_log`
--

LOCK TABLES `admin_login_log` WRITE;
/*!40000 ALTER TABLE `admin_login_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_login_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_role`
--

DROP TABLE IF EXISTS `admin_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT '角色id',
  `admin_id` bigint NOT NULL DEFAULT '0' COMMENT '用户id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_user_id` (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COMMENT='用户角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_role`
--

LOCK TABLES `admin_role` WRITE;
/*!40000 ALTER TABLE `admin_role` DISABLE KEYS */;
INSERT INTO `admin_role` (`id`, `role_id`, `admin_id`, `create_time`, `update_time`, `is_deleted`) VALUES (1,1,1,'2021-05-31 10:09:02','2021-05-31 10:09:02',0),(2,2,2,'2021-06-01 00:53:12','2022-10-28 12:20:26',0),(3,3,3,'2021-06-18 09:18:37','2021-06-18 09:18:37',0),(4,4,4,'2021-09-27 01:37:45','2021-09-27 01:37:45',0),(5,4,2,'2021-11-30 22:21:40','2021-11-30 22:21:40',0);
/*!40000 ALTER TABLE `admin_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `pid` bigint NOT NULL DEFAULT '0' COMMENT '所属上级',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `code` varchar(50) DEFAULT NULL COMMENT '名称code',
  `to_code` varchar(100) DEFAULT NULL COMMENT '跳转页面code',
  `type` tinyint NOT NULL DEFAULT '0' COMMENT '类型(1:菜单,2:按钮)',
  `status` tinyint DEFAULT NULL COMMENT '状态(0:禁止,1:正常)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`),
  KEY `idx_pid` (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` (`id`, `pid`, `name`, `code`, `to_code`, `type`, `status`, `create_time`, `update_time`, `is_deleted`) VALUES (1,0,'全部数据',NULL,NULL,1,NULL,'2021-05-31 10:05:37','2021-09-27 05:37:59',0),(2,1,'权限管理','Acl',NULL,1,NULL,'2021-05-31 10:05:37','2021-05-31 11:36:53',0),(3,2,'用户管理','User',NULL,1,NULL,'2021-05-31 10:05:37','2021-05-31 11:36:58',0),(4,2,'角色管理','Role',NULL,1,NULL,'2021-05-31 10:05:37','2021-05-31 11:37:02',0),(5,2,'菜单管理','Permission',NULL,1,NULL,'2021-05-31 10:05:37','2021-05-31 11:37:05',0),(6,3,'分配角色','btn.User.assgin',NULL,2,NULL,'2021-05-31 10:05:37','2021-06-01 00:35:35',0),(7,3,'添加','btn.User.add',NULL,2,NULL,'2021-05-31 10:05:37','2021-06-01 00:34:29',0),(8,3,'修改','btn.User.update',NULL,2,NULL,'2021-05-31 10:05:37','2021-06-01 00:34:45',0),(9,3,'删除','btn.User.remove',NULL,2,NULL,'2021-05-31 10:05:37','2021-06-01 00:34:38',0),(10,4,'修改','btn.Role.update',NULL,2,NULL,'2021-05-31 10:05:37','2021-06-01 00:36:20',0),(11,4,'分配权限','btn.Role.assgin','RoleAuth',2,NULL,'2021-05-31 10:05:37','2021-06-01 00:36:56',0),(12,4,'添加','btn.Role.add',NULL,2,NULL,'2021-05-31 10:05:37','2021-06-01 00:36:08',0),(13,4,'删除','btn.Role.remove',NULL,2,NULL,'2021-05-31 10:05:37','2021-06-01 00:36:32',0),(14,4,'角色权限','role.acl',NULL,2,NULL,'2021-05-31 10:05:37','2022-10-28 12:20:34',0),(15,5,'查看','btn.permission.list',NULL,2,NULL,'2021-05-31 10:05:37','2021-05-31 11:32:52',0),(16,5,'添加','btn.Permission.add',NULL,2,NULL,'2021-05-31 10:05:37','2021-06-01 00:37:39',0),(17,5,'修改','btn.Permission.update',NULL,2,NULL,'2021-05-31 10:05:37','2021-06-01 00:37:47',0),(18,5,'删除','btn.Permission.remove',NULL,2,NULL,'2021-05-31 10:05:37','2021-06-01 00:37:54',0),(19,1,'订单管理','Order',NULL,1,NULL,'2021-06-18 08:38:51','2021-06-18 08:48:22',0),(20,19,'订单列表','OrderInfo','',1,NULL,'2021-06-18 08:39:21','2021-06-18 08:42:36',0);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(20) NOT NULL DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(20) DEFAULT NULL COMMENT '角色编码',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `role_name`, `role_code`, `remark`, `create_time`, `update_time`, `is_deleted`) VALUES (1,'系统管理员','SYSTEM',NULL,'2021-05-31 10:09:18','2021-05-31 10:09:18',0),(2,'平台管理员',NULL,NULL,'2021-06-01 00:38:40','2021-06-18 09:13:17',0),(3,'运营管理员',NULL,NULL,'2021-06-18 09:12:21','2021-11-30 22:21:14',0),(4,'游客',NULL,NULL,'2021-09-27 01:37:13','2021-09-27 01:37:13',0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_permission`
--

DROP TABLE IF EXISTS `role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL DEFAULT '0',
  `permission_id` bigint NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_permission_id` (`permission_id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb3 COMMENT='角色权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_permission`
--

LOCK TABLES `role_permission` WRITE;
/*!40000 ALTER TABLE `role_permission` DISABLE KEYS */;
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`, `create_time`, `update_time`, `is_deleted`) VALUES (3,4,1,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(4,4,2,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(5,4,3,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(6,4,6,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(7,4,7,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(8,4,8,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(9,4,9,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(10,4,4,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(11,4,10,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(12,4,11,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(13,4,12,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(14,4,13,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(15,4,5,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(16,4,15,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(17,4,16,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(18,4,17,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(19,4,18,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(20,4,19,'2021-11-30 22:25:27','2021-11-30 22:25:27',0),(21,4,20,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(22,4,23,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(23,4,24,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(24,4,25,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(25,4,27,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(26,4,30,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(27,4,31,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(28,4,32,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(29,4,28,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(30,4,26,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(31,4,29,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(32,4,36,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(33,4,40,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(34,4,41,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(35,4,42,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(36,4,43,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(37,4,45,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(38,4,46,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(39,4,47,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(40,4,48,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(41,4,49,'2021-11-30 22:25:28','2021-11-30 22:25:28',0),(42,4,50,'2021-11-30 22:25:28','2021-11-30 22:25:28',0);
/*!40000 ALTER TABLE `role_permission` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-03 11:05:38
