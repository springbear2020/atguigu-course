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
-- Current Database: `course_activigy`
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COMMENT='优惠券信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_info`
--

LOCK TABLES `coupon_info` WRITE;
/*!40000 ALTER TABLE `coupon_info` DISABLE KEYS */;
INSERT INTO `coupon_info` (`id`, `coupon_type`, `coupon_name`, `amount`, `condition_amount`, `start_time`, `end_time`, `range_type`, `rule_desc`, `publish_count`, `per_limit`, `use_count`, `receive_count`, `expire_time`, `publish_status`, `create_time`, `update_time`, `is_deleted`) VALUES (1,1,'天猫双 “11” 11.11 元券',11.11,0.00,'2022-11-11','2022-12-31',1,'全场通用，无限制',100,1,0,0,'2022-12-31 00:00:00',1,'2021-06-06 10:29:14','2022-10-30 07:24:29',0),(2,1,'劳动节 5.1 元券',5.10,0.00,'2022-05-01','2022-12-31',1,'全场通用，无限制',100,1,0,0,'2022-12-31 00:00:00',1,'2021-08-17 03:35:56','2022-10-30 07:24:29',0),(3,2,'10.24 程序员节 10.24 元券',10.24,0.00,'2022-10-24','2022-12-31',1,'全场通用，无限制',100,1,0,0,'2022-11-11 00:00:00',1,'2021-09-27 22:14:38','2022-10-30 13:59:26',0),(4,1,'中秋佳节 8.15 元券',8.15,0.00,'2022-08-15','2022-12-31',1,'全场通用，无限制',100,1,0,0,'2022-10-01 00:00:00',1,'2021-09-27 22:50:17','2022-10-30 13:59:26',0),(6,2,'元旦节 1.1 元券',1.10,0.00,'2022-01-01','2022-12-31',1,'全场通用，无限制',100,1,0,0,'2022-05-01 00:00:00',0,'2022-10-30 07:18:39','2022-10-30 13:59:26',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb3 COMMENT='优惠券领用表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_use`
--

LOCK TABLES `coupon_use` WRITE;
/*!40000 ALTER TABLE `coupon_use` DISABLE KEYS */;
INSERT INTO `coupon_use` (`id`, `coupon_id`, `user_id`, `order_id`, `coupon_status`, `get_time`, `using_time`, `used_time`, `expire_time`, `create_time`, `update_time`, `is_deleted`) VALUES (42,3,34,14,'2','2022-10-31 08:27:11','2022-10-31 08:28:59','2022-10-31 08:28:59','2022-11-11 00:00:00','2022-10-31 00:27:10','2022-10-31 00:28:59',0),(43,4,34,NULL,'1','2022-10-31 08:57:06',NULL,NULL,'2022-10-01 00:00:00','2022-10-31 00:57:06','2022-10-31 00:57:06',0),(44,4,34,15,'2','2022-10-31 08:57:19','2022-10-31 08:57:27','2022-10-31 08:57:27','2022-10-01 00:00:00','2022-10-31 00:57:19','2022-10-31 00:57:26',0),(45,2,34,NULL,'1','2022-11-03 11:00:40',NULL,NULL,'2022-12-31 00:00:00','2022-11-03 03:00:40','2022-11-03 03:00:40',0);
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

-- Dump completed on 2022-11-03 11:05:53
