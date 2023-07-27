-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: course_activigy
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
-- Current Database: `course_activity`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `course_activity` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `course_activity`;

--
-- Table structure for table `coupon_info`
--

DROP TABLE IF EXISTS `coupon_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `coupon_type` tinyint NOT NULL DEFAULT '1' COMMENT '购物券类型 1 注册卷 2：推荐赠送卷',
  `coupon_name` varchar(100) DEFAULT NULL COMMENT '优惠卷名字',
  `amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `condition_amount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '使用门槛 0->没门槛',
  `start_time` date DEFAULT NULL COMMENT '可以领取的开始日期',
  `end_time` date DEFAULT NULL COMMENT '可以领取的结束日期',
  `range_type` tinyint NOT NULL DEFAULT '1' COMMENT '使用范围[1->全场通用]',
  `rule_desc` varchar(200) DEFAULT NULL COMMENT '规则描述',
  `publish_count` int NOT NULL DEFAULT '1' COMMENT '发行数量',
  `per_limit` int NOT NULL DEFAULT '1' COMMENT '每人限领张数',
  `use_count` int NOT NULL DEFAULT '0' COMMENT '已使用数量',
  `receive_count` int NOT NULL DEFAULT '0' COMMENT '领取数量',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `publish_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '发布状态[0-未发布，1-已发布]',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '删除标记（0:不可用 1:可用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COMMENT='优惠券信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_info`
--

LOCK TABLES `coupon_info` WRITE;
/*!40000 ALTER TABLE `coupon_info` DISABLE KEYS */;
INSERT INTO `coupon_info` (`id`, `coupon_type`, `coupon_name`, `amount`, `condition_amount`, `start_time`, `end_time`, `range_type`, `rule_desc`, `publish_count`, `per_limit`, `use_count`, `receive_count`, `expire_time`, `publish_status`, `create_time`, `update_time`, `is_deleted`) VALUES (1,1,'双十一新用户注册赠送100元券',100.00,0.00,'2021-06-03','2022-07-10',2,'双十一新用户注册赠送100元卷，全程通用，没有限制',100,1,0,3,'2021-07-02 00:00:00',1,'2021-06-06 10:29:14','2022-10-28 12:18:07',0),(2,1,'双十二新用户推荐赠送500元券',600.00,0.00,'2021-08-04','2022-10-06',2,'双十一推荐课程，新用户购买并支付，赠送推荐人600福利卷，全程通用，没有限制',100,1,0,4,'2022-08-26 00:00:00',1,'2021-08-17 03:35:56','2022-10-28 12:18:07',0),(3,2,'国庆新用户注册赠送100元券',100.00,0.00,'2021-09-05','2022-10-06',1,'国庆新用户注册赠送100元卷，全程通用，没有限制',100,1,0,4,'2021-10-07 00:00:00',1,'2021-09-27 22:14:38','2022-10-28 12:18:07',0),(4,1,'国庆推荐课程并购买500福利券',500.00,0.00,'2021-09-27','2022-09-23',1,'双十一推荐课程，新用户购买并支付，赠送推荐人500福利卷，全程通用，没有限制',100,1,0,25,'2022-09-30 00:00:00',1,'2021-09-27 22:50:17','2022-10-28 12:18:07',0),(5,2,'新用户推荐1000元券',0.00,0.00,'2020-10-05','2022-10-29',1,'推荐新用户注册并观看直播课程，无限制使用',1000,1,0,0,'2022-10-31 00:00:00',0,'2022-10-28 13:42:40','2022-10-28 13:43:08',0);
/*!40000 ALTER TABLE `coupon_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_use`
--

DROP TABLE IF EXISTS `coupon_use`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon_use` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `coupon_id` bigint DEFAULT NULL COMMENT '购物券ID',
  `user_id` bigint DEFAULT NULL COMMENT '用户ID',
  `order_id` bigint DEFAULT NULL COMMENT '订单ID',
  `coupon_status` varchar(10) DEFAULT NULL COMMENT '购物券状态（1：未使用 2：已使用）',
  `get_time` datetime DEFAULT NULL COMMENT '获取时间',
  `using_time` datetime DEFAULT NULL COMMENT '使用时间',
  `used_time` datetime DEFAULT NULL COMMENT '支付时间',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_deleted` tinyint NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb3 COMMENT='优惠券领用表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_use`
--

LOCK TABLES `coupon_use` WRITE;
/*!40000 ALTER TABLE `coupon_use` DISABLE KEYS */;
INSERT INTO `coupon_use` (`id`, `coupon_id`, `user_id`, `order_id`, `coupon_status`, `get_time`, `using_time`, `used_time`, `expire_time`, `create_time`, `update_time`, `is_deleted`) VALUES (1,1,1,NULL,'0','2021-11-10 09:02:23',NULL,NULL,'2021-07-02 00:00:00','2021-11-10 01:02:23','2021-11-21 23:59:00',0),(2,1,1,NULL,'0','2021-11-10 09:02:23',NULL,NULL,'2022-07-02 00:00:00','2021-11-10 01:02:28','2021-11-21 23:59:02',0),(5,2,1,4,'1','2021-11-12 16:37:00','2021-11-23 18:57:27',NULL,'2022-09-30 00:00:00','2021-11-12 00:36:58','2022-10-28 13:44:32',0),(9,2,1,1,'1','2021-11-22 14:50:05','2021-11-22 21:38:48','2021-11-22 21:39:12','2022-09-30 00:00:00','2021-11-22 06:50:05','2022-10-28 13:44:32',0),(27,4,24,NULL,'0','2021-11-23 18:14:01',NULL,NULL,'2022-09-30 00:00:00','2021-11-23 02:14:01','2021-11-23 02:14:01',0),(28,5,25,NULL,'0','2021-11-23 18:49:01',NULL,NULL,'2022-09-30 00:00:00','2021-11-23 02:49:00','2022-10-28 13:44:32',0),(29,4,26,NULL,'0','2021-11-23 18:49:03',NULL,NULL,'2022-09-30 00:00:00','2021-11-23 02:49:03','2021-11-23 02:49:03',0),(30,2,27,5,'1','2021-11-23 18:50:03','2021-11-23 18:57:52',NULL,'2022-09-30 00:00:00','2021-11-23 02:50:02','2022-10-28 13:44:32',0),(31,4,28,NULL,'0','2021-11-23 18:52:49',NULL,NULL,'2022-09-30 00:00:00','2021-11-23 02:52:49','2021-11-23 02:52:49',0),(32,3,29,NULL,'0','2021-11-26 08:57:40',NULL,NULL,'2022-09-30 00:00:00','2021-11-25 16:57:39','2022-10-28 13:44:32',0),(33,4,29,NULL,'0','2021-11-26 18:33:04',NULL,NULL,'2022-09-30 00:00:00','2021-11-26 02:33:03','2021-11-26 02:33:03',0),(34,3,30,NULL,'0','2021-11-26 18:34:12',NULL,NULL,'2022-09-30 00:00:00','2021-11-26 02:34:11','2022-10-28 13:44:32',0),(35,5,31,NULL,'0','2021-11-28 16:46:53',NULL,NULL,'2022-09-30 00:00:00','2021-11-28 00:46:53','2022-10-28 13:44:32',0),(36,4,32,NULL,'0','2021-12-28 12:29:18',NULL,NULL,'2022-09-30 00:00:00','2021-12-28 04:29:17','2021-12-28 04:29:17',0),(37,3,33,NULL,'0','2022-01-05 14:35:16',NULL,NULL,'2022-09-30 00:00:00','2022-01-05 06:35:15','2022-10-28 13:44:32',0);
/*!40000 ALTER TABLE `coupon_use` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-29  8:01:41
