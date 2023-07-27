> 开发时间：2022.10.17 - 2022.11.04

> 服务端：[atguigu-course-backend](https://github.com/springbear2020/atguigu-course-backend)
> 后台管理系统：[atguigu-course-frontend](https://github.com/springbear2020/atguigu-course-frontend)
> 移动端微信公众号：[atguigu-course-mobile](https://github.com/springbear2020/atguigu-course-mobile)


# 一、快速开始

## 1.1 服务器

1. 克隆仓库：使用 `Git` 克隆仓库或直接下载仓库压缩包到您的计算机：[https://github.com/springbear2020/atguigu-course-backend](https://github.com/springbear2020/atguigu-course-backend)

2. 打开工程：使用 `IntelliJ IDEA` 打开克隆的仓库或解压的工程文件，而后使用 `Maven` 工具更新父工程 `course-backend` 依赖

3. 创建数据库和表：登录 `MySQL`，依据 `course-backend/sql` 目录中的七个 `SQL` 脚本文件，建立七个数据库并导入表和数据

4. 修改必要配置信息：

   - 修改六个 `service-*` 模块中的 MySQL 数据库连接信息，设置你自己的数据库用户名和密码
   - 修改六个 `service-*` 模块中的 `Spring Cloud Nacos` 服务注册中心的 IP 和端口号，设置你自己的 Nacos 信息

   | 模块名           | 监听端口 | Nacos 注册中心 |
   | :--------------- | :------: | :------------: |
   | gateway          |   8888   | 127.0.0.1:8848 |
   | service-vod      |   8081   | 127.0.0.1:8848 |
   | service-order    |   8082   | 127.0.0.1:8848 |
   | service-activity |   8083   | 127.0.0.1:8848 |
   | service-user     |   8084   | 127.0.0.1:8848 |
   | service-wechat   |   8085   | 127.0.0.1:8848 |
   | service-live     |   8086   | 127.0.0.1:8848 |

5. 修改可选的配置信息：

   - `service-live`：修改欢拓云直播平台配置信息。若不修改则后台管理系统中直播课程相关的 CRUD 功能异常

     ```properties
     # course-backend/service/service-live/src/main/resources/application.properties
     # 欢拓云直播平台用户开放 ID
     mtcloud.openId=52255
     # 欢拓云直播平台用户开发 Token
     mtcloud.openToken=21da08fa1d043e3ae399abf045aad8e8
     ```

   - `service-user`：修改微信公众号测试号的应用 ID 信息和应用密钥以及用户同意授权个人信息后的回调地址，注意此回调地址需要为公网地址才能被微信平台识别。若不修改则微信公众号中的相关功能均不可用。需要在微信公众号测试号中配置网页账号授权回调页面域名

     ```properties
     # course-backend/service/service-user/src/main/resources/application.properties
     # 微信公众号测试号应用 ID
     wechat.mpAppId=wx6e925452d38d9b30
     # 微信公众号测试号应用密钥
     wechat.mpAppSecret=fa078dcf0a68aec5aacbafde745f01af
     # 微信公众号用户同意授权个人信息后的回调地址，具体流程参看代码注释：cn.edu.whut.springbear.course.service.user.controller.api.WeChatUserApiController
     wechat.authorizedCallbackUrl=http://course.5gzvip.91tunnel.com/api/user/info
     ```

   - `service-vod`：修改腾讯对象存储的存储空间信息和腾讯云视频点播服务的应用 ID 信息。若不修改则腾讯云相关图片存储服务不可用，课程小节视频不能上传和查看

     ```properties
     # course-backend/service/service-vod/src/main/resources/application.properties
     # 腾讯云对象存储：存储桶名称
     tencent.cloud.bucket=course-1308741720
     # 腾讯云对象存储：存储空间所在地域
     tencent.cloud.region=ap-chongqing
     # 腾讯云对象存储：id
     tencent.cloud.secretId=AKIDhFbktxxDJLG4e5cyIvPgkaEg4SindGgw
     # 腾讯云对象存储：密钥
     tencent.cloud.secretKey=6wcCbkgVAf2EBsMdfy6C7nHbopfJo8OR
     # 腾讯云视频点播：应用 ID
     tencent.vod.appId=1308741720
     ```

   - `course-wechat`：修改腾讯云微信公众号的配置信息以及接口配置信息（需要在微信公众号测试号中配置网页账号授权回调页面域名），若不修改则微信公众号一切功能均将异常；修改微信公众号页面的服务器地址，也即 `course-mobile` 项目的部署地址，此地址需为公网地址以供微信后台调用，如不修改则微信公众号中的查看课程信息功能异常，不能跳转到任何页面

     ```properties
     # 微信公众号测试号应用 ID
     wechat.mpAppId=wx6e925452d38d9b30
     # 微信公众号测试号应用密钥
     wechat.mpAppSecret=fa078dcf0a68aec5aacbafde745f01af
     # 微信公众号测试号接口配置信息 token
     wechat.token=course-backend
     # 微信公众号测试号模板消息模板 ID
     wechat.templateId=4bTafg2x6tI-19bXgSLN9TDYePVFXvzBZCj7p5uToZE
     # 微信公众号页面的服务器地址，也即 course-mobile 项目的部署地址
     course.pageBaseUrl=http://mobile.vipgz4.91tunnel.com
     ```

   注：service-user 和 service-wechat 中用到的域名地址可通过配置内网穿透的方式实现，具体可参看 [Ngrok](https://ngrok.cc/) 内网穿透工具

6. 启动 Nacos 服务注册中心：进入 Nacos 安装 bin 目录，在控制台以 `startup.cmd -m standalone` 命令启动单机版 Nacos

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/874b8123a8414f0c8332a283851067f1.png#pic_center)


7. 启动后台服务：在 IDEA 中批量启动所有后台服务，包括一个网关和六个服务

## 1.2 后台管理

> 确保 1.1 中的一个网关和六个服务正常启动以提供正常的接口功能

1. 克隆仓库：使用 `Git` 克隆仓库或直接下载仓库压缩包到您的计算机：[https://github.com/springbear2020/atguigu-course-frontend](https://github.com/springbear2020/atguigu-course-frontend)
2. 打开工程：使用 `VS Code` 打开克隆的仓库或解压的工程文件，而后在控制台使用 `npm install` 命令安装工程所需依赖
3. 修改配置信息：
   - `.env.development`：修改该文件中的 `VUE_APP_BASE_API` 地址信息为 1.1 步骤中配置的网关地址
   - `store/modules/app.js`：修改该文件 state 中配置的地址信息为 1.1 步骤中配置的网关地址
4. 启动工程：控制台使用 `npm run dev` 命令启动项目，启动成功即可进入管理员后台管理登录页

## 1.3 微信公众号

> 确保 1.1 中的一个网关和六个服务正常启动以提供正常的接口功能
>
> 注：微信公众号相关的配置中 ”坑“ 较多步骤较为繁琐，若有疑问可邮件咨询笔者：springbear2020@163.com

1. 克隆仓库：使用 `Git` 克隆仓库或直接下载仓库压缩包到您的计算机：[https://github.com/springbear2020/atguigu-course-mobile](https://github.com/springbear2020/atguigu-course-mobile)
2. 打开工程：使用 `VS Code` 打开克隆的仓库或解压的工程文件，而后在控制台使用 `npm install` 命令安装工程所需依赖
3. 修改配置信息：
   - `store/index.js`：修改 state 中配置的 baseURL 为内网穿透工具中的域名地址，该域名地址代理监听服务器的网关端口
   - `utils/request.js`：修改 service 中配置的 baseURL 为内网穿透工具中的域名地址，该域名地址代理监听服务器的网关端口
4. 启动工程：控制台使用 `npm run serve` 命令启动项目，启动成功后进行第 5 步
5. 申请微信公众号测试号并在其中配置一系列 1.1 服务器中提到的相关信息，在后台管理系统中同步公众号菜单信息
6. 关注公众号并在手机端测试公众号的相关功能

# 二、系统概述

## 2.1 项目介绍

硅谷课堂是尚硅谷与腾讯云官方合作推出的项目，是一款基于微信公众号 B2C 模式的在线学习平台，该平台包含三大模块：直播、教学与微信消息服务。平台会定期推出直播课程，方便学员与名师之间的交流互动，学员也可以购买教学视频在线学习，分享直播与教学视频获取平台收益，平台支持直播、腾讯云视频点播、微信支付、微信授权登录、微信菜单、微信消息与腾讯云文件存储等一系列功能，为学员构建了一个全方位的在线学习平台。

硅谷课堂项目具有很强的实用性，业务场景贴近实际，技术应用紧跟市场潮流，完全按照市场需求开发。既是对主流 Java 技术的系统性梳理和整合，同时也是各种主流技术实际应用的练兵场，能够帮助 Java 程序员积累项目经验。

## 2.2 系统流程

![在这里插入图片描述](https://img-blog.csdnimg.cn/5e9d9796abb54e0ba5d53c7fd66d0bdb.png#pic_center)


## 2.3 功能架构

![在这里插入图片描述](https://img-blog.csdnimg.cn/4ab2045737494a6ba5ee4c8604b53d91.png#pic_center)


## 2.4 技术架构

![在这里插入图片描述](https://img-blog.csdnimg.cn/50a7f8804b0248d2beb5ebb0fbde8018.png#pic_center)


## 2.5 技术选型

后端技术

| SpringBoot、SpringCloudGateway、SpringCloudAlibabaNacos、SpringCloudFeign、MyBatisPlus、MySQL、EasyExcel、Swagger、JWT、Lombok |
| ------------------------------------------------------------ |

前端技术

| Node.js、Vue.js、Axios、NPM、ElementUI、Vant |
| -------------------------------------------- |

第三方技术

| 腾讯云对象存储、腾讯云视频点播、欢拓云直播、微信公众号 |
| ------------------------------------------------------ |

# 三、项目结构

![在这里插入图片描述](https://img-blog.csdnimg.cn/939f2b006b0f4e4ca99acd118b06667a.png#pic_center)


```
course-backend		父工程，统一依赖管理
	client			客户端，通过 Spring Cloud Feign 提供服务
		activity	营销活动相关服务
		order		订单 api 接口
		user		用户 api 接口
		vod			课程点播 api 接口
	common			通用模块
		model		通用数据模型
		util		通用工具类
	gateway			服务网关，统一管理各种 service 服务，解决跨域问题
	service			提供服务，为前后台提供具体服务
		activity	营销活动相关服务
		live		直播课程相关服务
		order		订单相关服务
		user		用户相关服务
		vod			课程点播相关服务
		wechat		微信公众号相关服务
```

# 四、数据库设计

## 4.1 course_acl

1. admin(id, username, password, name, phone, ware_id)：管理员用户信息
2. admin_login_log(id, admin_id, ip, address, user_agent)：管理员登录记录
3. role(id, role_name, role_code, remark)：角色信息
4. admin_role(id, role_id, admin_id)：管理员角色信息
5. permission(id, pid, name, code, to_code, type, status)：权限操作记录
6. role_permission(id, role_id, permission_id)：角色权限操作记录

## 4.2 course_activity

1. coupon_info(id, coupon_type, coupon_name, amount, condition_amount, start_time, end_time, range_type, rule_desc, publish_count, per_limit, use_count, receive_count, expire_time, publish_status)：优惠券信息
2. coupon_use(id, coupon_id, user_id, order_id, coupon_status, get_time, using_time, used_time, expire_time)：优惠券使用详情

## 4.3 course_live

1. live_course(id, subject_id, course_name, start_time, end_time, cover, course_id, teacher_id)：直播课程信息
2. live_course_description(id, live_course_id, description)：直播课程描述信息
3. live_course_account(id, live_course_id, live_account, live_key, admin_key, user_key)：直播课程账户信息
4. live_course_config(id, live_course_id, page_view_mode, number_enable, store_enable, store_type)：直播课程配置信息
5. live_course_goods(id, live_course_id, goods_id, name, img, price, original_price, tab, url, put_away, pay, qrcode)：直播课程商品信息
6. live_visitor(id, live_course_id, course_name, user_id, nick_name, join_time, leave_time, location, duration, duration_time, live_visitor_id)：直播课程访客信息

## 4.4 course_order

1. order_info(id, user_id, nick_name, phone, origin_amount, coupon_reduce, final_amount, order_status, out_trade_no, trade_body, session_id, province, pay_time, expire_time)：订单信息
2. order_detail(id, course_id, course_name, cover, order_id, user_id, origin_amount, coupon_reduce, final_amount, session_id)：订单详情信息
3. payment_info(id, out_trade_no, order_id, user_id, alipay_trade_no, total_amount, trade_body, payment_type, payment_status, callback_content, callback_time)：订单支付信息

## 4.5 course_user

1. user_info(id, phone, password, name, nick_name, sex, avatar, province, subscribe, open_id, union_id, recommend_id, status)：用户信息
2. user_login_log(id, user_id, ip, city, type)：用户登录记录

## 4.6 course_vod

1. course(id, teacher_id, subject_id, subject_parent_id, title, price, lesson_num, duration_sum, cover, buy_count, view_count, status, publish_time)：课程信息
2. course_description(id, course_id, description)：课程描述信息
3. comment(id, course_id, teacher_id, user_id, nickname, avatar, content)：课程评价信息
4. course_collect(id, course_id, user_id)：课程收藏信息
5. teacher(id, name, intro, career, level, avatar, sort, join_date)：课程讲师信息
6. subject(id, title, parent_id, sort)：课程分类信息
7. chapter(id, course_id, title, sort)：课程章节信息
8. video(id, course_id, chapter_id, title, video_source_id, video_original_name, sort, play_count, is_free, duration, size, version, status)：课程小节视频信息
9. video_visitor(id, course_id, video_id, user_id, nick_name, join_time, leave_time, duration)：课程小节视频访客信息

## 4.7 course_wechat

1. menu(id, parent_id, name, type, url, menu_key, sort)：微信公众号菜单信息

# 五、功能展示

## 5.1 后台管理

### 5.1.1 管理员

1. 管理员登录

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/b70117391212438390113c57658f0c86.png#pic_center)


2. 管理员首页

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/0cfe0dfd5d2c43d4bb889fe690c25f6f.png#pic_center)


### 5.1.2 讲师管理

1. 新增讲师：填写讲师必要信息，上传讲师头像到腾讯云对象存储平台进行图片保存

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/0f5c9cbf71734c8ba2536f1324410ad8.png#pic_center)


2. 删除讲师

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/46a8aa06b9594c9cbde03a94c2612d3a.png#pic_center)


3. 编辑讲师：选定需要编辑的讲师，回显讲师信息，保存后更新讲师信息

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/7ffb09da3f744815a641167459e47026.png#pic_center)


4. 查询讲师：可根据讲师姓名、头衔等信息查询展示讲师

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/c72acefd3edd450eac41fe87a6c8d29a.png#pic_center)


5. 讲师分页：讲师数据分页展示

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/e24e88e8953b451f8e978348ca8f172f.png#pic_center)


### 5.1.3 课程分类管理

1. 导入课程分类：选中保存由课程分类的 excel 文件，上传到服务器，由服务器解析并保存课程分类信息

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/454c4c0c9a654bb79f3cf8d576b17fb9.png#pic_center)


2. 导出课程分类：选定导出课程分类按钮，由服务器处理课程分类信息并写入 excel 文件供下载

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/1e5c6f968ab847d38d8b06ca1c0b819c.png#pic_center)


3. 查询课程分类：课程分类信息 “懒加载” 展示

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/b62baadd5ca742e196ff8eb3d9e66e24.png#pic_center)


### 5.1.4 课程管理

1. 新增课程：填入课程必要信息，选择课程分类信息，上传课程封面

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/7fcf67d0e12948adb4fb4ab1eac93327.png#pic_center)


2. 删除课程

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/fed46f554cca42ac9e2cc69e75afd620.png#pic_center)


3. 编辑课程

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/bc78eb4f2cb4477cbea19b553892dbdc.png#pic_center)


4. 查询课程：可根据课程分类信息、课程名称以及课程讲师信息查询课程

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/621f742bb0264e848062a8635da5d5bc.png#pic_center)


5. 课程分页

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/c032397aed794f15b727b6678298776a.png#pic_center)


6. 课程访客统计

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/2c273bbd83394fbd8a32ff63c83114f4.png#pic_center)


7. 编辑课程大纲：一门课程下有多个章节，一个章节包含多个小节，一个课程小节对应一份课程视频

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/e263a17f6e4b427b9acd413da45caa20.png#pic_center)


8. 新增课程章节

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/6424e713de0a4f1ead590028ab337a86.png#pic_center)




10. 上传课程小节视频：选择课程小节视频，上传到腾讯云视频点播平台

    ![在这里插入图片描述](https://img-blog.csdnimg.cn/5317986abcec4181862b2decbd476db3.png#pic_center)


11. 发布课程：将课程修改为已发布状态

    ![在这里插入图片描述](https://img-blog.csdnimg.cn/6bbef2074ef44cd7938af1498742c934.png#pic_center)


### 5.1.5 优惠券管理

1. 新增优惠券

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/9cd6b513a1f046e3bc27cbbc647bee1d.png#pic_center)


2. 删除优惠券

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/7b406e5942c04dd189f77d108694987c.png#pic_center)


3. 编辑优惠券

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/fa568d8e35074da3ab6ae08a84d892f1.png#pic_center)


4. 优惠券分页

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/29dfacab05b94ef99c8061ee0e783974.png#pic_center)


5. 优惠券详情：展示优惠券及已使用的优惠券信息

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/837fdb313e82440fbc78ac084baaca5d.png#pic_center)


### 5.1.6 公众号菜单管理

1. 添加公众号菜单

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/9ad87c9e2d7c4eaab01d25c88559c429.png#pic_center)


2. 修改公众号菜单

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/abfbd22f371343cdaa2660c7f27e9775.png#pic_center)


3. 同步公众号菜单：同步公众号菜单后最新的菜单信息将同步到微信公众号中

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/32a15ca680b248f4850b7ecc9c6d822f.png#pic_center)


4. 菜单列表

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/0025598fb388496982041e8cd84a67e1.png#pic_center)


### 5.1.7 直播课程管理

### 5.1.8 课程订单管理

1. 订单查询：根据订单号、手机号等信息查询用户的课程订单信息

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/6288f4a78edc4b6691d1762db69ebc0c.png#pic_center)


2. 订单分页

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/78a309c6c12a4bc2b6835ed6560981f1.png#pic_center)


### 5.1.9 直播管理

1. 新增直播

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/1f24946697e24f779bbf4f2a80fd05a4.png#pic_center)


2. 删除直播

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/c7da0c65864949509aca752a37b22d2e.png#pic_center)


3. 编辑直播

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/e4f156ad20b34688b8cfc351f10d0c74.png#pic_center)


4. 直播分页

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/c0bb2104fffa4d6886c39ed655eb1d43.png#pic_center)


5. 直播配置

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/2df1bd713b9b44f295085ffe3a41c0ea.png#pic_center)


6. 直播账号

   ![在这里插入图片描述](https://img-blog.csdnimg.cn/bcb88a1649e84bd787b271569a822624.png#pic_center)


## 5.2 公众号

### 5.2.1 关注

![在这里插入图片描述](https://img-blog.csdnimg.cn/5b261d774eff45ff83afffdd390b3d50.png#pic_center)


### 5.2.2 关于我

![在这里插入图片描述](https://img-blog.csdnimg.cn/86aa1b07389a4891b471de12c3796c9d.jpeg#pic_center)


### 5.2.3 查询课程

![在这里插入图片描述](https://img-blog.csdnimg.cn/7dce1a22360a43f5b40f6decf3258135.jpeg#pic_center)


### 5.2.4 课程分类

![在这里插入图片描述](https://img-blog.csdnimg.cn/62f2cedc25b94189961445c470651c93.jpeg#pic_center)


### 5.2.5 课程列表

![在这里插入图片描述](https://img-blog.csdnimg.cn/b8461fde2b564fe6832c06f923ff24bc.jpeg#pic_center)


### 5.2.6 课程详情

![在这里插入图片描述](https://img-blog.csdnimg.cn/dfcbddc063924679b7461ff7bf9604cf.jpeg#pic_center)


### 5.2.7 视频播放

![在这里插入图片描述](https://img-blog.csdnimg.cn/42180e19fd6a4dabaa977e0ab109037f.jpeg#pic_center)


### 5.2.8 购买课程

![在这里插入图片描述](https://img-blog.csdnimg.cn/e60ae6c970c747289c3c77b99f46bd79.jpeg#pic_center)


### 5.2.9 兑换优惠券

![在这里插入图片描述](https://img-blog.csdnimg.cn/10edf42be1564233ab75b9b2d257bfa9.jpeg#pic_center)


### 5.2.10 不可用优惠券

![在这里插入图片描述](https://img-blog.csdnimg.cn/aaf26d2be9d04d0396df128a1182ba59.jpeg#pic_center)


### 5.2.11 可用优惠券

![在这里插入图片描述](https://img-blog.csdnimg.cn/52132b3954154faa89fd9a0b1ed91726.jpeg#pic_center)


### 5.2.12 支付模拟

![在这里插入图片描述](https://img-blog.csdnimg.cn/b7045a19554747259b5cb607b2fb50c3.jpeg#pic_center)


### 5.2.13 支付成功

![在这里插入图片描述](https://img-blog.csdnimg.cn/a37382201d774f3ca81dd81e6b27080a.jpeg#pic_center)


### 5.2.14 课程购买成功

![在这里插入图片描述](https://img-blog.csdnimg.cn/972751adf1a3455ab89e7de8dd049f59.jpeg#pic_center)


### 5.2.15 支付消息推送

![在这里插入图片描述](https://img-blog.csdnimg.cn/05d23783224c40978a25e685095edb80.jpeg#pic_center)
