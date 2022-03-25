/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.19 : Database - warehouse
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`warehouse` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `warehouse`;

/*Table structure for table `growth_allscore` */

DROP TABLE IF EXISTS `growth_allscore`;

CREATE TABLE `growth_allscore` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `userid` int(50) DEFAULT NULL COMMENT '学生id',
  `year` varchar(20) DEFAULT NULL COMMENT '学年',
  `module` double(5,2) DEFAULT '0.00' COMMENT '学年平均成绩',
  `kbook` double(5,2) DEFAULT '0.00' COMMENT '书籍审核成绩',
  `kpaper` double(5,2) DEFAULT '0.00' COMMENT '发表论文审核成绩',
  `kinnovate` double(5,2) DEFAULT '0.00' COMMENT '创新课题研究成绩',
  `kcompetition` double(5,2) DEFAULT '0.00' COMMENT '竞赛审核成绩',
  `kpatent` double(5,2) DEFAULT '0.00' COMMENT '专利审核成绩',
  `kcertificate` double(5,2) DEFAULT '0.00' COMMENT '证书审核成绩',
  `svolunteer` double(5,2) DEFAULT '0.00' COMMENT '志愿服务审核成绩',
  `swork` double(5,2) DEFAULT '0.00' COMMENT '社会工作审核成绩',
  `rhonor` double(5,2) DEFAULT '0.00' COMMENT '荣誉申报审核成绩',
  `k` double(5,2) DEFAULT '0.00' COMMENT '科技综合',
  `s` double(5,2) DEFAULT '0.00' COMMENT '社会实践综合',
  `allscore` double(5,2) DEFAULT '0.00' COMMENT '综合总成绩',
  `fail` varchar(10) DEFAULT NULL COMMENT '挂科数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

/*Data for the table `growth_allscore` */

insert  into `growth_allscore`(`id`,`userid`,`year`,`module`,`kbook`,`kpaper`,`kinnovate`,`kcompetition`,`kpatent`,`kcertificate`,`svolunteer`,`swork`,`rhonor`,`k`,`s`,`allscore`,`fail`) values (35,3,'2021~2022',76.86,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,38.43,'0'),(36,21,'2021~2022',0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL),(37,22,'2021~2022',73.89,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,36.95,'1'),(38,23,'2021~2022',0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL),(39,24,'2021~2022',0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL),(40,25,'2021~2022',0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL),(41,27,'2021~2022',0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL),(42,28,'2021~2022',0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL),(43,29,'2021~2022',0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL),(44,31,'2021~2022',0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL),(45,42,'2021~2022',74.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,37.00,'0'),(46,43,'2021~2022',0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,NULL);

/*Table structure for table `growth_book` */

DROP TABLE IF EXISTS `growth_book`;

CREATE TABLE `growth_book` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `bookname` varchar(30) DEFAULT NULL COMMENT '书籍名字',
  `dotype` varchar(20) DEFAULT NULL COMMENT '出版方式：团队 个人',
  `doscore` double(5,2) DEFAULT NULL COMMENT '自评分数',
  `number` varchar(10) DEFAULT NULL COMMENT '出版书数目',
  `year` varchar(20) DEFAULT NULL COMMENT '学年',
  `text` text COMMENT '备注信息',
  `imgpath` varchar(100) DEFAULT NULL COMMENT '材料图片路径',
  `authortype` varchar(20) DEFAULT NULL COMMENT '主编，副编，参编',
  `userid` int(20) DEFAULT NULL COMMENT '用户id',
  `bookdate` datetime DEFAULT NULL COMMENT '提交时间',
  `ypass` varchar(15) DEFAULT NULL COMMENT '是否通过',
  `why` varchar(500) DEFAULT NULL COMMENT '驳回原因',
  `wordpath` varchar(200) DEFAULT NULL COMMENT '文档路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `growth_book` */

insert  into `growth_book`(`id`,`bookname`,`dotype`,`doscore`,`number`,`year`,`text`,`imgpath`,`authortype`,`userid`,`bookdate`,`ypass`,`why`,`wordpath`) values (30,'全世界','个人',8.00,'1本','2021~2022','个人著作','2022-03-05/0BB96B81ACDF43ABA1E00BCEB2AEC9B6.jpeg','主编',3,'2022-03-05 20:46:00','2',NULL,NULL),(32,'从你的世界路过','个人',8.00,'1本','2021~2022','','2022-03-23/FD00952F957745AEB077624007CD48A2.jpeg','主编',42,'2022-03-23 19:20:52','2',NULL,'D:/GraduateDesign/graduate3/upload/docx/20220323192824_飞控报告 1801061120陈小超.docx');

/*Table structure for table `growth_certificate` */

DROP TABLE IF EXISTS `growth_certificate`;

CREATE TABLE `growth_certificate` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `certificatename` varchar(30) DEFAULT NULL COMMENT '证书名称',
  `certificatetype` varchar(30) DEFAULT NULL COMMENT '证书类型',
  `year` varchar(30) DEFAULT NULL COMMENT '学年',
  `doscore` double(5,2) DEFAULT NULL COMMENT '自评分数',
  `text` varchar(300) DEFAULT NULL COMMENT '备注',
  `imgpath` varchar(300) DEFAULT NULL COMMENT '材料图片路径',
  `userid` int(30) DEFAULT NULL,
  `certificatedate` datetime DEFAULT NULL COMMENT '修改时间',
  `why` varchar(300) DEFAULT NULL COMMENT '驳回理由',
  `ypass` varchar(10) DEFAULT NULL COMMENT '是否通过',
  `wordpath` varchar(300) DEFAULT NULL COMMENT 'word路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `growth_certificate` */

insert  into `growth_certificate`(`id`,`certificatename`,`certificatetype`,`year`,`doscore`,`text`,`imgpath`,`userid`,`certificatedate`,`why`,`ypass`,`wordpath`) values (1,'律师证','其他证书','2021~2022',1.00,'自考律师证','2022-03-23/6126709B446B4DB6901245A64564E442.png',42,'2022-03-23 19:33:54',NULL,'2','D:/GraduateDesign/graduate3/upload/docx/20220324151346_返家乡社会实践总结.docx');

/*Table structure for table `growth_competition` */

DROP TABLE IF EXISTS `growth_competition`;

CREATE TABLE `growth_competition` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `competitionname` varchar(50) DEFAULT NULL COMMENT '竞赛项目名',
  `ctype` varchar(50) DEFAULT NULL COMMENT '竞赛类型（科技、非科技）',
  `competitiontype` varchar(50) DEFAULT NULL COMMENT '竞等级（国家级、省级）',
  `year` varchar(30) DEFAULT NULL COMMENT '学年',
  `doscore` double(5,2) DEFAULT NULL COMMENT '自评分数',
  `text` varchar(300) DEFAULT NULL COMMENT '备注',
  `imgpath` varchar(300) DEFAULT NULL COMMENT '图片路径',
  `authortype` varchar(30) DEFAULT NULL COMMENT '团队个人排名',
  `userid` int(50) DEFAULT NULL,
  `competitiondate` datetime DEFAULT NULL COMMENT '修改时间',
  `why` varchar(300) DEFAULT NULL COMMENT '驳回理由',
  `ypass` varchar(20) DEFAULT NULL COMMENT '是否通过',
  `wordpath` varchar(300) DEFAULT NULL COMMENT 'word文档路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `growth_competition` */

insert  into `growth_competition`(`id`,`competitionname`,`ctype`,`competitiontype`,`year`,`doscore`,`text`,`imgpath`,`authortype`,`userid`,`competitiondate`,`why`,`ypass`,`wordpath`) values (1,'医疗项目','互联网+','国家级','2021~2022',8.00,'','2022-03-23/9BDFDC65E92841298DA9F492CFD41F63.png','第一名',42,'2022-03-23 19:31:46',NULL,'2','D:/GraduateDesign/graduate3/upload/docx/20220324151003_陈小超周总结.docx');

/*Table structure for table `growth_honor` */

DROP TABLE IF EXISTS `growth_honor`;

CREATE TABLE `growth_honor` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `honorname` varchar(30) DEFAULT NULL COMMENT '荣誉项目名',
  `honortype` varchar(30) DEFAULT NULL COMMENT '荣誉级别',
  `year` varchar(30) DEFAULT NULL COMMENT '学年',
  `doscore` double(5,2) DEFAULT NULL COMMENT '自评分数',
  `text` varchar(300) DEFAULT NULL COMMENT '备注',
  `imgpath` varchar(200) DEFAULT NULL COMMENT '图片地址',
  `authortype` varchar(30) DEFAULT NULL COMMENT '团队形式',
  `userid` int(30) DEFAULT NULL,
  `honordate` datetime DEFAULT NULL COMMENT '修改时间',
  `why` varchar(300) DEFAULT NULL COMMENT '驳回原因',
  `ypass` varchar(10) DEFAULT NULL COMMENT '是否通过',
  `wordpath` varchar(300) DEFAULT NULL COMMENT '文档路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `growth_honor` */

insert  into `growth_honor`(`id`,`honorname`,`honortype`,`year`,`doscore`,`text`,`imgpath`,`authortype`,`userid`,`honordate`,`why`,`ypass`,`wordpath`) values (1,'跳绳','院级','2021~2022',1.00,'','2022-03-23/E90455DB61454D8AA0B10F153A623D7B.jpg','团队',42,'2022-03-23 19:35:22',NULL,'2','D:/GraduateDesign/graduate3/upload/docx/20220324152220_文献综述.docx');

/*Table structure for table `growth_innovate` */

DROP TABLE IF EXISTS `growth_innovate`;

CREATE TABLE `growth_innovate` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `innovatename` varchar(50) DEFAULT NULL COMMENT '创新创业训练计划项目或课题研究名字',
  `doscore` double(5,2) DEFAULT NULL COMMENT '自评分数',
  `year` varchar(30) DEFAULT NULL COMMENT '学年',
  `text` varchar(500) DEFAULT NULL COMMENT '备注',
  `imgpath` varchar(200) DEFAULT NULL COMMENT '材料图片地址',
  `authortype` varchar(30) DEFAULT NULL COMMENT '参与度',
  `innovatetype` varchar(30) DEFAULT NULL COMMENT '课题类型（国家级）',
  `progress` varchar(30) DEFAULT NULL COMMENT '课题进度，立项目，结算项目',
  `userid` int(20) DEFAULT NULL,
  `innovatedate` datetime DEFAULT NULL COMMENT '更新时间',
  `why` varchar(300) DEFAULT NULL COMMENT '驳回理由',
  `ypass` varchar(10) DEFAULT NULL COMMENT '是否通过',
  `wordpath` varchar(300) DEFAULT NULL COMMENT 'word',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `growth_innovate` */

insert  into `growth_innovate`(`id`,`innovatename`,`doscore`,`year`,`text`,`imgpath`,`authortype`,`innovatetype`,`progress`,`userid`,`innovatedate`,`why`,`ypass`,`wordpath`) values (1,'软件培训',8.00,'2021~2022','','2022-03-23/B39EA3961ED6472786ACE8B29BA77CC9.png','第一主研','国家级','结项目',42,'2022-03-23 19:30:32',NULL,'2','D:/GraduateDesign/graduate3/upload/docx/20220323193052_陈小超周总结.docx');

/*Table structure for table `growth_modulescore` */

DROP TABLE IF EXISTS `growth_modulescore`;

CREATE TABLE `growth_modulescore` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `general` double(4,2) DEFAULT NULL COMMENT '通识必修课成绩',
  `major` double(4,2) DEFAULT NULL COMMENT '专业必修课成绩',
  `ambit` double(4,2) DEFAULT NULL COMMENT '学科基础必修课',
  `public_base` double(4,2) DEFAULT NULL COMMENT '公共基础课',
  `specialized` double(4,2) DEFAULT NULL COMMENT '专业基础课',
  `practice` double(4,2) DEFAULT NULL COMMENT '集中实践教学环节',
  `spacial_week` double(4,2) DEFAULT NULL COMMENT '专周',
  `userid` int(10) DEFAULT NULL COMMENT '学生id',
  `averagescore` double(4,2) DEFAULT NULL COMMENT '模块平均成绩',
  `create_date` datetime DEFAULT NULL COMMENT '提交时间',
  `year` varchar(10) DEFAULT NULL COMMENT '学年',
  `fail` varchar(10) DEFAULT NULL COMMENT '挂科数目',
  PRIMARY KEY (`id`),
  KEY `FK_ID` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `growth_modulescore` */

insert  into `growth_modulescore`(`id`,`general`,`major`,`ambit`,`public_base`,`specialized`,`practice`,`spacial_week`,`userid`,`averagescore`,`create_date`,`year`,`fail`) values (28,77.00,87.00,88.00,67.00,77.00,76.00,66.00,3,76.86,'2022-03-05 20:45:08','2021~2022','0'),(29,77.00,78.00,56.00,66.00,76.00,76.20,88.00,22,73.89,'2022-03-06 19:31:48','2021~2022','1'),(30,66.00,78.00,66.00,77.00,89.00,66.00,76.00,42,74.00,'2022-03-23 19:19:46','2021~2022','0');

/*Table structure for table `growth_paper` */

DROP TABLE IF EXISTS `growth_paper`;

CREATE TABLE `growth_paper` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `papername` varchar(30) DEFAULT NULL COMMENT '论文名称',
  `number` varchar(30) DEFAULT NULL COMMENT '论文数目',
  `year` varchar(30) DEFAULT NULL COMMENT '学年',
  `text` varchar(500) DEFAULT NULL COMMENT '备注',
  `doscore` double(5,2) DEFAULT NULL COMMENT '自评',
  `imgpath` varchar(200) DEFAULT NULL COMMENT '材料地址',
  `authortype` varchar(100) DEFAULT NULL COMMENT '作者类型',
  `papertype` varchar(100) DEFAULT NULL COMMENT '论文类型',
  `userid` int(30) DEFAULT NULL COMMENT '学生id',
  `paperdate` datetime DEFAULT NULL COMMENT '修改时间',
  `ypass` varchar(10) DEFAULT NULL COMMENT '是否通过',
  `why` varchar(500) DEFAULT NULL COMMENT '驳回理由',
  `wordpath` varchar(300) DEFAULT NULL COMMENT 'word文档路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `growth_paper` */

insert  into `growth_paper`(`id`,`papername`,`number`,`year`,`text`,`doscore`,`imgpath`,`authortype`,`papertype`,`userid`,`paperdate`,`ypass`,`why`,`wordpath`) values (5,'网站如何开发','1篇','2021~2022','很好文章',8.00,'2022-03-23/B210F50AD7CB4D8B8F42EBE8525DDA96.png','第一作者','核心期刊',42,'2022-03-23 19:29:19','2',NULL,'D:/GraduateDesign/graduate3/upload/docx/20220324150549_个人简历.docx');

/*Table structure for table `growth_patent` */

DROP TABLE IF EXISTS `growth_patent`;

CREATE TABLE `growth_patent` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `patentname` varchar(30) DEFAULT NULL COMMENT '专利名称',
  `patenttype` varchar(30) DEFAULT NULL COMMENT '专利类型',
  `year` varchar(30) DEFAULT NULL COMMENT '学年',
  `doscore` double(5,2) DEFAULT NULL COMMENT '自评分数',
  `text` varchar(300) DEFAULT NULL COMMENT '备注',
  `imgpath` varchar(300) DEFAULT NULL COMMENT '图片地址',
  `authortype` varchar(30) DEFAULT NULL COMMENT '作者排名',
  `userid` int(30) DEFAULT NULL,
  `patentdate` datetime DEFAULT NULL COMMENT '修改日期',
  `why` varchar(300) DEFAULT NULL COMMENT '理由',
  `ypass` varchar(30) DEFAULT NULL COMMENT '是否通过',
  `wordpath` varchar(300) DEFAULT NULL COMMENT 'word路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `growth_patent` */

insert  into `growth_patent`(`id`,`patentname`,`patenttype`,`year`,`doscore`,`text`,`imgpath`,`authortype`,`userid`,`patentdate`,`why`,`ypass`,`wordpath`) values (1,'学生管理系统','发明专利','2021~2022',8.00,'','2022-03-23/28ED4248F15646198EBB184A7F21BF9F.jpg','第一发明人',42,'2022-03-23 19:32:30',NULL,'2','D:/GraduateDesign/graduate3/upload/docx/20220324151152_陈小超周总结.docx');

/*Table structure for table `growth_rule` */

DROP TABLE IF EXISTS `growth_rule`;

CREATE TABLE `growth_rule` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL COMMENT '评分标题',
  `contents` text COMMENT '评分细则',
  `createtime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `growth_rule` */

insert  into `growth_rule`(`id`,`type`,`contents`,`createtime`) values (1,'出版书籍','<p>单独出版计8分/本；</p><p>共同出版计3分/本，作为主编加3分，副主编加2分，参编成员加1分。</p>','2022-03-23 18:05:52'),(2,'发表论文','<p>核心期刊：第一作者计8分/篇；第二作者计6分/篇；第三作者计4分/篇；第四及其以后计2分；</p><p>非核心期刊：第一作者计3分/篇，第二作者计2分/篇，第三作者及其以后计1分/篇。</p><p>说明:论文作者单位须为成都工业学院。</p>','2022-03-23 18:08:28'),(3,'创新课题研究','<p>国家级计5分、省级计3分、校级计1分。</p><p>再根据项目或课题的参与度，作为第一主研，加3分/项；作为第二主研，加1分/项；</p><p>作为第三、四、五主研，加0.5分/项。&nbsp;</p><p>&nbsp;说明:1、同一项目与课题不叠加。2、立项项目加一半分值，结项项目加全分。</p>','2022-03-23 18:09:04'),(4,'竞赛获奖','<p>（1）“互联网+”、“挑战杯”等科技、学科类竞赛获奖</p><p>&nbsp;国家级：一等奖计8分，二等奖计6分，三等奖计4分，优秀奖计3分；&nbsp;</p><p>&nbsp;省级：一等奖计6分，二等奖计4分，三等奖计2分，优秀奖计1分；&nbsp;</p><p>&nbsp;校级：一等奖计4分，二等奖计2分，三等奖计1分，优秀奖计0.5分。&nbsp;&nbsp;</p><p>（2）非科技、学科类竞赛获奖&nbsp;</p><p>&nbsp;国家级：一等奖计8分，二等奖计6分，三等奖计4分，优秀奖计2分；&nbsp;</p><p>&nbsp;省级：一等奖计6分，二等奖计4分，三等奖计2分，优秀奖计1分；&nbsp;</p><p>&nbsp;校级：一等奖计3分，二等奖计2分，三等奖计1分，优秀奖计0.5分；&nbsp;</p><p>&nbsp;院级：一等奖计1分，二等奖计0.5分，三等奖计0.3分，优秀奖计0.1分。&nbsp;</p><p>&nbsp;说明:\r\n\r\n（1）获奖后出具证书或正规的证明材料及举办竞赛活动的正式文件后方可加分；具体级别由学院审核确定。 （2）4人（含）以上团队获奖者，按先后排名依次减一分，最终得分不低于1分； \r\n\r\n（3）同一项目获不同级别奖励者按最高级别加分；\r\n\r\n（4）文体类活动校级最高加分限制为4分；\r\n\r\n（5）社团活动获奖按院级计分规则进行加分，最多计三项奖项。</p>','2022-03-23 18:10:33'),(5,'专利申报','<p>发明专利：排名第一计8分/个；排名第二计6分/个；排名第三计4分/个；排名第四2分/个；排名第五及以后1分/个。&nbsp;</p><p>&nbsp;实用新型等专利：排名第一计3分/个，排名第二计2分/个，排名第三计1分/个；排名第四及以后0.5分/个。&nbsp;</p><p>&nbsp;说明：授权专利才可计分。</p>','2022-03-23 18:11:12'),(6,'技能证书','<p>英语六级、计算机三级计5分；</p><p>英语四级、计算机二级、普通话一级计2分。&nbsp;</p><p>教师资格证、会计证、心理咨询证等基础职业资格证书计1分。</p>','2022-03-23 18:18:06'),(7,'社会实践与志愿','<p>社会实践与志愿服务包含参与的社会实践活动、公益与志愿服务活动、社会工作等。</p><p>&nbsp;1、社会实践活动\r\n\r\n国家级，计5分/项；\r\n\r\n省市级，计4分/项；\r\n\r\n校级，计3分/项；\r\n\r\n院级，计2分/项。\r\n\r\n说明：此项内容最高计五分。</p><p>&nbsp;2、公益与志愿服务活动\r\n\r\n省级及以上各类志愿者项目1分/项，校级各类志愿者项目0.5分/项。</p><p>&nbsp;说明：此项内容最高计3分。</p>','2022-03-23 18:12:20'),(8,'校园工作','<p>社会工作包含在班级、学院、学校所担任的职务等，校级职务加分按院级加分。按职务等级累计计分，同一等级内不累计计分，取最高得分。&nbsp;</p><p>&nbsp;（1）院级\r\n\r\n团总支副书记、学生会主席加4分；党支部副书记（学生）、学生会副主席、社团第一负责人加 3分；党支部委员、党员服务站负责人、社团第二负责人、部门正职加 2分；部门副职加1分；干事、党员责任岗成员加 0.5分。&nbsp;</p><p>&nbsp;（2）班级\r\n\r\n班长、团支书、学习委员加 3分；副班长、团支部副书记加2分；其余班干部加1分。</p>','2022-03-23 18:12:48'),(9,'荣誉申报','<p>荣誉指的是申请者获得的各项荣誉（含个人、团体）等。按等级分别计分。</p><p>&nbsp;国家级，计5分/项；\r\n\r\n省市级，计4分/项；\r\n\r\n校级，计2分/项；\r\n\r\n院级，计1分/项。</p><p>&nbsp;团体奖项按实际得分的70%计算，同一荣誉按最高等级计分，不<span>累计加分。</span></p>','2022-03-23 18:13:43');

/*Table structure for table `growth_volunteer` */

DROP TABLE IF EXISTS `growth_volunteer`;

CREATE TABLE `growth_volunteer` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `volunteername` varchar(30) DEFAULT NULL COMMENT '活动项目名',
  `volunteertype` varchar(30) DEFAULT NULL COMMENT '活动项目类型',
  `year` varchar(20) DEFAULT NULL COMMENT '学年',
  `doscore` double(5,2) DEFAULT NULL COMMENT '自评分数',
  `text` varchar(300) DEFAULT NULL COMMENT '备注',
  `imgpath` varchar(200) DEFAULT NULL COMMENT '图片地址',
  `userid` int(30) DEFAULT NULL,
  `volunteerdate` datetime DEFAULT NULL COMMENT '修改日期',
  `why` varchar(300) DEFAULT NULL COMMENT '原因',
  `ypass` varchar(10) DEFAULT NULL COMMENT '是否通过',
  `wordpath` varchar(300) DEFAULT NULL COMMENT 'word文件路径',
  `ctype` varchar(30) DEFAULT NULL COMMENT '等级（国家级）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `growth_volunteer` */

insert  into `growth_volunteer`(`id`,`volunteername`,`volunteertype`,`year`,`doscore`,`text`,`imgpath`,`userid`,`volunteerdate`,`why`,`ypass`,`wordpath`,`ctype`) values (1,'敬老院看望老人','公益与志愿服务活动','2021~2022',1.00,'','2022-03-23/439F4F508C2E425495569E0C350929A2.jpg',42,'2022-03-23 19:34:28',NULL,'2','D:/GraduateDesign/graduate3/upload/docx/20220324151549_陈小超 1801061120.doc','校级');

/*Table structure for table `growth_work` */

DROP TABLE IF EXISTS `growth_work`;

CREATE TABLE `growth_work` (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `worktype` varchar(30) DEFAULT NULL COMMENT '校院干部',
  `ctype` varchar(30) DEFAULT NULL COMMENT '班级干部',
  `year` varchar(30) DEFAULT NULL COMMENT '学年',
  `doscore` double(5,2) DEFAULT NULL COMMENT '自评分数',
  `text` varchar(300) DEFAULT NULL COMMENT '备注',
  `imgpath` varchar(200) DEFAULT NULL COMMENT '图片路径',
  `userid` int(30) DEFAULT NULL,
  `workdate` datetime DEFAULT NULL COMMENT '修改时间',
  `why` varchar(300) DEFAULT NULL COMMENT '驳回理由',
  `ypass` varchar(10) DEFAULT NULL COMMENT '是否通过',
  `wordpath` varchar(300) DEFAULT NULL COMMENT '文件路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `growth_work` */

insert  into `growth_work`(`id`,`worktype`,`ctype`,`year`,`doscore`,`text`,`imgpath`,`userid`,`workdate`,`why`,`ypass`,`wordpath`) values (1,'团总支副书记','团支部副书记','2021~2022',4.00,'','2022-03-23/6540E93D706D46C6BE658A6584B5FDBC.jpg',42,'2022-03-23 19:34:55',NULL,'2','D:/GraduateDesign/graduate3/upload/docx/20220323211952_陈小超周总结.docx');

/*Table structure for table `growth_year` */

DROP TABLE IF EXISTS `growth_year`;

CREATE TABLE `growth_year` (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `year` varchar(30) DEFAULT NULL COMMENT '学年',
  `yeardate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `growth_year` */

insert  into `growth_year`(`id`,`year`,`yeardate`) values (8,'2021~2022','2022-03-05 20:43:43');

/*Table structure for table `sys_grade` */

DROP TABLE IF EXISTS `sys_grade`;

CREATE TABLE `sys_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `open` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  `ordernum` int(11) DEFAULT NULL COMMENT '排序码【为了调试显示顺序】',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_grade` */

insert  into `sys_grade`(`id`,`pid`,`name`,`open`,`remark`,`address`,`available`,`ordernum`,`createtime`) values (1,0,'飞控',1,'所属年级','成都工业学院',1,1,'2021-04-01 14:06:32'),(2,1,'18飞控',0,'第一届','成都郫都校区',1,2,'2021-04-01 14:06:32'),(3,1,'19飞控',0,'第二届','成都郫都校区',1,3,'2021-04-01 14:06:32'),(4,1,'20飞控',0,'第三届','宜宾校区',1,4,'2021-04-01 14:06:32'),(5,2,'18飞控1班',0,'18级1班','郫都区',1,5,'2021-04-01 14:06:32'),(6,2,'18飞控2班',0,'18级2班','郫都区',1,6,'2021-04-01 14:06:32'),(7,3,'19飞控1班',0,'19级1班','郫都区',1,7,'2021-04-01 14:06:32'),(18,4,'20飞控1班',0,'20级1班','宜宾',1,11,'2021-04-01 14:06:32'),(19,4,'20飞控2班',1,'20级2班','宜宾',1,12,'2021-05-12 14:19:23'),(20,2,'18飞控3班',0,'飞控3班','成都郫都区',1,13,'2022-02-17 16:11:36'),(21,3,'19飞控2班',0,'19飞控2班','成都郫都区',1,14,'2022-02-17 16:14:16');

/*Table structure for table `sys_loginfo` */

DROP TABLE IF EXISTS `sys_loginfo`;

CREATE TABLE `sys_loginfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) DEFAULT NULL,
  `loginip` varchar(255) DEFAULT NULL,
  `logintime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1046 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_loginfo` */

insert  into `sys_loginfo`(`id`,`loginname`,`loginip`,`logintime`) values (290,'魏腾飞-weitengfei','127.0.0.1','2021-05-12 14:23:23'),(291,'超级管理员-system','127.0.0.1','2021-05-12 14:23:41'),(292,'超级管理员-system','127.0.0.1','2021-05-12 14:53:04'),(293,'超级管理员-system','127.0.0.1','2021-05-12 14:57:46'),(294,'超级管理员-system','127.0.0.1','2021-05-12 15:40:13'),(296,'超级管理员-system','127.0.0.1','2021-05-12 18:33:24'),(297,'超级管理员-system','127.0.0.1','2021-05-12 19:16:46'),(298,'超级管理员-system','127.0.0.1','2021-05-12 19:19:28'),(299,'超级管理员-system','127.0.0.1','2021-05-12 19:21:44'),(300,'超级管理员-system','127.0.0.1','2021-05-12 19:37:15'),(301,'超级管理员-system','127.0.0.1','2021-05-13 16:11:00'),(302,'超级管理员-system','127.0.0.1','2021-05-13 16:29:52'),(303,'超级管理员-system','127.0.0.1','2021-05-13 16:58:30'),(304,'超级管理员-system','127.0.0.1','2021-05-13 17:06:31'),(305,'超级管理员-system','127.0.0.1','2021-05-13 17:17:06'),(306,'超级管理员-system','127.0.0.1','2021-05-13 17:27:37'),(307,'超级管理员-system','127.0.0.1','2021-05-13 17:37:41'),(308,'超级管理员-system','127.0.0.1','2021-05-13 17:38:42'),(309,'超级管理员-system','127.0.0.1','2021-05-13 18:08:22'),(310,'超级管理员-system','127.0.0.1','2021-05-13 18:26:26'),(311,'超级管理员-system','127.0.0.1','2021-05-13 18:36:53'),(312,'超级管理员-system','127.0.0.1','2021-05-13 18:40:24'),(313,'超级管理员-system','127.0.0.1','2021-05-13 18:41:50'),(314,'超级管理员-system','127.0.0.1','2021-05-13 18:44:14'),(315,'超级管理员-system','127.0.0.1','2021-05-13 18:47:12'),(316,'超级管理员-system','127.0.0.1','2021-05-13 18:49:58'),(317,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 18:50:52'),(318,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 18:51:31'),(319,'超级管理员-system','127.0.0.1','2021-05-13 18:54:45'),(320,'超级管理员-system','127.0.0.1','2021-05-13 19:26:13'),(321,'超级管理员-system','127.0.0.1','2021-05-13 19:32:12'),(322,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 19:34:01'),(323,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 19:49:16'),(324,'超级管理员-system','127.0.0.1','2021-05-13 19:52:22'),(325,'超级管理员-system','127.0.0.1','2021-05-13 19:54:54'),(326,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 20:01:20'),(327,'超级管理员-system','127.0.0.1','2021-05-13 20:18:25'),(328,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 20:37:33'),(329,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 20:43:32'),(330,'超级管理员-system','127.0.0.1','2021-05-13 20:49:47'),(331,'超级管理员-system','127.0.0.1','2021-05-13 21:03:11'),(332,'超级管理员-system','127.0.0.1','2021-05-13 21:07:02'),(333,'超级管理员-system','127.0.0.1','2021-05-13 21:20:33'),(334,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 21:29:59'),(335,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 21:32:44'),(336,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 21:36:54'),(337,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 21:42:01'),(338,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 21:45:55'),(339,'超级管理员-system','127.0.0.1','2021-05-13 21:49:44'),(340,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 22:12:34'),(341,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 22:17:09'),(342,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 22:23:17'),(343,'超级管理员-system','127.0.0.1','2021-05-13 22:28:05'),(344,'超级管理员-system','127.0.0.1','2021-05-13 22:29:22'),(345,'超级管理员-system','127.0.0.1','2021-05-13 22:34:05'),(346,'超级管理员-system','127.0.0.1','2021-05-13 22:41:09'),(347,'超级管理员-system','127.0.0.1','2021-05-13 22:42:46'),(348,'超级管理员-system','127.0.0.1','2021-05-13 22:48:34'),(349,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 22:50:33'),(350,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 22:54:25'),(351,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 22:56:32'),(352,'超级管理员-system','127.0.0.1','2021-05-13 22:59:47'),(353,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 23:01:22'),(354,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 23:02:05'),(355,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-13 23:03:04'),(356,'超级管理员-system','127.0.0.1','2021-05-13 23:05:10'),(357,'超级管理员-system','127.0.0.1','2021-05-13 23:06:29'),(358,'超级管理员-system','127.0.0.1','2021-05-13 23:08:07'),(359,'超级管理员-system','127.0.0.1','2021-05-13 23:12:22'),(360,'超级管理员-system','127.0.0.1','2021-05-13 23:15:34'),(361,'超级管理员-system','127.0.0.1','2021-05-13 23:16:43'),(362,'超级管理员-system','127.0.0.1','2021-05-13 23:18:08'),(363,'超级管理员-system','127.0.0.1','2021-05-13 23:21:14'),(364,'超级管理员-system','127.0.0.1','2021-05-13 23:27:36'),(365,'超级管理员-system','127.0.0.1','2021-05-13 23:28:56'),(366,'超级管理员-system','127.0.0.1','2021-05-13 23:34:10'),(367,'超级管理员-system','127.0.0.1','2021-05-13 23:56:57'),(368,'超级管理员-system','127.0.0.1','2021-05-14 01:46:00'),(369,'超级管理员-system','127.0.0.1','2021-05-15 16:11:40'),(370,'采购员1号-cgy','127.0.0.1','2021-05-15 16:15:44'),(371,'超级管理员-system','127.0.0.1','2021-05-15 16:16:16'),(372,'超级管理员-system','127.0.0.1','2021-05-16 10:15:30'),(373,'超级管理员-system','127.0.0.1','2021-05-16 11:09:21'),(374,'超级管理员-system','127.0.0.1','2021-05-16 12:21:38'),(375,'超级管理员-system','127.0.0.1','2021-05-16 15:43:42'),(376,'超级管理员-system','127.0.0.1','2021-05-16 19:38:27'),(377,'超级管理员-system','127.0.0.1','2021-05-16 22:11:57'),(378,'超级管理员-system','127.0.0.1','2021-05-17 06:03:03'),(379,'超级管理员-system','127.0.0.1','2021-05-17 06:36:21'),(380,'超级管理员-system','127.0.0.1','2021-05-17 15:13:57'),(381,'超级管理员-system','127.0.0.1','2021-05-17 15:53:53'),(382,'超级管理员-system','127.0.0.1','2021-05-17 16:34:13'),(383,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-17 16:42:03'),(384,'超级管理员-system','127.0.0.1','2021-05-17 16:47:09'),(385,'超级管理员-system','127.0.0.1','2021-05-17 16:49:18'),(386,'超级管理员-system','127.0.0.1','2021-05-17 16:52:21'),(387,'超级管理员-system','127.0.0.1','2021-05-17 17:24:13'),(388,'超级管理员-system','127.0.0.1','2021-05-17 17:25:40'),(389,'超级管理员-system','127.0.0.1','2021-05-17 17:27:34'),(390,'超级管理员-system','127.0.0.1','2021-05-17 17:33:25'),(391,'超级管理员-system','127.0.0.1','2021-05-17 17:35:55'),(392,'超级管理员-system','127.0.0.1','2021-05-17 17:40:20'),(393,'超级管理员-system','127.0.0.1','2021-05-17 17:47:38'),(394,'超级管理员-system','127.0.0.1','2021-05-17 17:52:21'),(395,'超级管理员-system','127.0.0.1','2021-05-17 18:02:46'),(396,'超级管理员-system','127.0.0.1','2021-05-17 18:08:01'),(397,'超级管理员-system','127.0.0.1','2021-05-17 18:14:50'),(398,'超级管理员-system','127.0.0.1','2021-05-17 18:29:37'),(399,'超级管理员-system','127.0.0.1','2021-05-17 18:38:52'),(400,'超级管理员-system','127.0.0.1','2021-05-17 18:43:15'),(401,'超级管理员-system','127.0.0.1','2021-05-17 18:45:55'),(402,'超级管理员-system','127.0.0.1','2021-05-17 18:47:14'),(403,'超级管理员-system','127.0.0.1','2021-05-17 18:48:16'),(404,'超级管理员-system','127.0.0.1','2021-05-17 18:51:35'),(405,'超级管理员-system','127.0.0.1','2021-05-18 09:22:04'),(406,'超级管理员-system','127.0.0.1','2021-05-18 10:21:28'),(407,'超级管理员-system','127.0.0.1','2021-05-21 15:26:14'),(408,'超级管理员-system','127.0.0.1','2021-05-22 18:03:36'),(409,'超级管理员-system','127.0.0.1','2021-05-22 18:25:37'),(410,'超级管理员-system','127.0.0.1','2021-05-22 18:33:04'),(411,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-22 18:42:38'),(412,'超级管理员-system','127.0.0.1','2021-05-22 18:43:39'),(413,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-22 19:19:35'),(414,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-22 19:27:28'),(415,'超级管理员-system','127.0.0.1','2021-05-22 19:30:51'),(416,'超级管理员-system','127.0.0.1','2021-05-22 19:35:23'),(417,'超级管理员-system','127.0.0.1','2021-05-22 19:42:34'),(418,'超级管理员-system','127.0.0.1','2021-05-22 19:48:31'),(419,'超级管理员-system','127.0.0.1','2021-05-22 19:51:08'),(420,'超级管理员-system','127.0.0.1','2021-05-22 19:55:49'),(421,'超级管理员-system','127.0.0.1','2021-05-22 20:01:38'),(422,'超级管理员-system','127.0.0.1','2021-05-22 20:03:31'),(423,'超级管理员-system','127.0.0.1','2021-05-22 20:04:39'),(424,'超级管理员-system','127.0.0.1','2021-05-22 20:09:12'),(425,'超级管理员-system','127.0.0.1','2021-05-22 20:10:25'),(426,'超级管理员-system','127.0.0.1','2021-05-22 20:12:15'),(427,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-22 20:13:37'),(428,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-22 20:14:35'),(429,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-23 04:57:22'),(430,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-23 05:03:14'),(431,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-23 05:18:17'),(432,'超级管理员-system','127.0.0.1','2021-05-23 06:06:40'),(433,'超级管理员-system','127.0.0.1','2021-05-23 06:08:49'),(434,'超级管理员-system','127.0.0.1','2021-05-23 06:15:38'),(435,'超级管理员-system','127.0.0.1','2021-05-23 06:17:27'),(436,'超级管理员-system','127.0.0.1','2021-05-23 06:22:20'),(437,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-23 06:23:46'),(438,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-23 06:25:28'),(439,'超级管理员-system','127.0.0.1','2021-05-25 02:52:08'),(440,'独立维-duliwei','127.0.0.1','2021-05-25 02:54:30'),(441,'超级管理员-system','127.0.0.1','2021-05-25 02:55:27'),(442,'采购员1号-cgy','127.0.0.1','2021-05-25 02:57:03'),(443,'独立维-duliwei','127.0.0.1','2021-05-25 02:57:34'),(444,'超级管理员-system','127.0.0.1','2021-05-25 02:57:47'),(445,'独立维-duliwei','127.0.0.1','2021-05-25 02:58:13'),(446,'采购员1号-cgy','127.0.0.1','2021-05-25 02:58:55'),(447,'超级管理员-system','127.0.0.1','2021-05-25 03:01:36'),(448,'独立维-duliwei','127.0.0.1','2021-05-25 03:11:38'),(449,'独立维-duliwei','127.0.0.1','2021-05-25 03:16:13'),(450,'超级管理员-system','127.0.0.1','2021-05-25 03:16:31'),(451,'采购员1号-cgy','127.0.0.1','2021-05-25 03:17:20'),(452,'独立维-duliwei','127.0.0.1','2021-05-25 03:21:19'),(453,'独立维-duliwei','127.0.0.1','2021-05-25 03:21:57'),(454,'独立维-duliwei','127.0.0.1','2021-05-25 03:25:12'),(455,'超级管理员-system','127.0.0.1','2021-05-25 03:32:00'),(456,'采购员1号-cgy','127.0.0.1','2021-05-25 03:34:02'),(457,'超级管理员-system','127.0.0.1','2021-05-25 03:36:55'),(458,'超级管理员-system','127.0.0.1','2021-05-25 03:45:00'),(459,'超级管理员-system','127.0.0.1','2021-05-25 17:28:41'),(460,'超级管理员-system','127.0.0.1','2021-05-25 19:29:16'),(461,'独立维-duliwei','127.0.0.1','2021-05-25 22:01:03'),(462,'超级管理员-system','127.0.0.1','2021-05-25 22:31:10'),(463,'超级管理员-system','127.0.0.1','2021-05-25 22:42:33'),(464,'超级管理员-system','127.0.0.1','2021-05-25 22:48:10'),(465,'超级管理员-system','127.0.0.1','2021-05-25 22:50:52'),(466,'超级管理员-system','127.0.0.1','2021-05-25 22:59:30'),(467,'超级管理员-system','127.0.0.1','2021-05-25 23:33:35'),(468,'超级管理员-system','127.0.0.1','2021-05-25 23:34:58'),(469,'超级管理员-system','127.0.0.1','2021-05-25 23:36:06'),(470,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-25 23:48:18'),(471,'超级管理员-system','127.0.0.1','2021-05-25 23:53:00'),(472,'超级管理员-system','127.0.0.1','2021-05-25 23:55:22'),(473,'超级管理员-system','127.0.0.1','2021-05-26 00:06:57'),(474,'超级管理员-system','127.0.0.1','2021-05-26 00:09:15'),(475,'超级管理员-system','127.0.0.1','2021-05-26 00:11:19'),(476,'超级管理员-system','127.0.0.1','2021-05-26 00:11:54'),(477,'超级管理员-system','127.0.0.1','2021-05-26 00:16:15'),(478,'超级管理员-system','127.0.0.1','2021-05-26 00:17:15'),(479,'超级管理员-system','127.0.0.1','2021-05-26 00:18:50'),(480,'超级管理员-system','127.0.0.1','2021-05-26 00:23:17'),(481,'超级管理员-system','127.0.0.1','2021-05-26 00:24:38'),(482,'超级管理员-system','127.0.0.1','2021-05-26 00:29:39'),(483,'超级管理员-system','127.0.0.1','2021-05-26 00:31:13'),(484,'超级管理员-system','127.0.0.1','2021-05-26 00:32:28'),(485,'超级管理员-system','127.0.0.1','2021-05-26 00:36:25'),(486,'超级管理员-system','127.0.0.1','2021-05-26 00:37:20'),(487,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-26 00:41:14'),(488,'超级管理员-system','127.0.0.1','2021-05-26 00:45:14'),(489,'独立维-duliwei','127.0.0.1','2021-05-26 00:49:20'),(490,'采购员1号-cgy','127.0.0.1','2021-05-26 00:50:02'),(491,'采购员1号-cgy','127.0.0.1','2021-05-26 00:54:49'),(492,'超级管理员-system','127.0.0.1','2021-05-26 01:08:38'),(493,'超级管理员-system','127.0.0.1','2021-05-26 01:19:58'),(494,'超级管理员-system','127.0.0.1','2021-05-26 01:28:23'),(495,'超级管理员-system','127.0.0.1','2021-05-26 01:43:37'),(496,'超级管理员-system','127.0.0.1','2021-05-26 02:22:12'),(497,'独立维-duliwei','127.0.0.1','2021-05-26 02:50:47'),(498,'超级管理员-system','127.0.0.1','2021-05-26 02:55:28'),(499,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-26 03:03:23'),(500,'超级管理员-system','127.0.0.1','2021-05-26 03:07:30'),(501,'超级管理员-system','127.0.0.1','2021-05-26 07:48:37'),(502,'超级管理员-system','127.0.0.1','2021-05-26 07:48:59'),(503,'独立维-duliwei','127.0.0.1','2021-05-26 08:55:04'),(504,'采购员1号-cgy','127.0.0.1','2021-05-26 08:55:23'),(505,'超级管理员-system','127.0.0.1','2021-05-26 08:59:09'),(506,'独立维-duliwei','127.0.0.1','2021-05-26 09:09:14'),(507,'采购员1号-cgy','127.0.0.1','2021-05-26 09:09:29'),(508,'超级管理员-system','0:0:0:0:0:0:0:1','2021-05-26 11:11:13'),(509,'超级管理员-system','127.0.0.1','2021-05-30 20:09:59'),(510,'超级管理员-system','127.0.0.1','2021-06-01 16:16:01'),(511,'超级管理员-system','127.0.0.1','2021-06-04 10:38:41'),(512,'超级管理员-system','127.0.0.1','2021-06-04 11:01:23'),(513,'超级管理员-system','127.0.0.1','2021-06-04 11:45:24'),(514,'超级管理员-system','127.0.0.1','2021-06-04 12:29:30'),(515,'超级管理员-system','127.0.0.1','2021-06-13 22:39:22'),(516,'超级管理员-system','127.0.0.1','2021-08-05 16:06:36'),(517,'超级管理员-system','127.0.0.1','2021-08-05 16:16:35'),(518,'超级管理员-system','0:0:0:0:0:0:0:1','2022-01-15 17:10:55'),(519,'超级管理员-system','0:0:0:0:0:0:0:1','2022-01-15 17:21:21'),(520,'超级管理员-system','0:0:0:0:0:0:0:1','2022-01-16 23:36:44'),(521,'落亦--luoyi','0:0:0:0:0:0:0:1','2022-01-16 23:43:39'),(522,'李四-lisi','0:0:0:0:0:0:0:1','2022-01-16 23:49:42'),(523,'王五-wangwu','0:0:0:0:0:0:0:1','2022-01-16 23:50:12'),(524,'超级管理员-system','0:0:0:0:0:0:0:1','2022-01-16 23:53:16'),(525,'超级管理员-system','0:0:0:0:0:0:0:1','2022-01-17 00:00:07'),(526,'超级管理员-system','0:0:0:0:0:0:0:1','2022-01-17 16:55:27'),(527,'超级管理员-system','0:0:0:0:0:0:0:1','2022-01-17 18:05:22'),(528,'超级管理员-system','0:0:0:0:0:0:0:1','2022-01-17 18:34:07'),(529,'超级管理员-system','0:0:0:0:0:0:0:1','2022-01-17 19:08:14'),(530,'超级管理员-system','0:0:0:0:0:0:0:1','2022-01-17 21:43:22'),(531,'超级管理员-system','0:0:0:0:0:0:0:1','2022-01-21 18:02:40'),(532,'超级管理员-system','0:0:0:0:0:0:0:1','2022-01-23 02:01:51'),(533,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-06 13:31:39'),(534,'赵六-zhaoliu','0:0:0:0:0:0:0:1','2022-02-06 13:33:48'),(535,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-06 13:34:10'),(536,'赵六-zhaoliu','0:0:0:0:0:0:0:1','2022-02-06 13:35:28'),(537,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-06 15:22:37'),(538,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-06 15:23:09'),(539,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-06 19:49:26'),(540,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 11:31:28'),(541,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 11:49:25'),(542,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 13:07:33'),(543,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 13:17:16'),(544,'超级管理员-system','127.0.0.1','2022-02-07 13:17:43'),(545,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 13:22:20'),(546,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 13:24:04'),(547,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 13:33:18'),(548,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 13:33:57'),(549,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 13:36:02'),(550,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 13:37:11'),(551,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 13:38:45'),(552,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 13:40:33'),(553,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 13:44:32'),(554,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 13:55:49'),(555,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 13:57:48'),(556,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 14:04:55'),(557,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 14:16:30'),(558,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 14:16:41'),(559,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 15:24:12'),(560,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 15:25:00'),(561,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 16:31:20'),(562,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 16:35:29'),(563,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 16:36:19'),(564,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 16:48:09'),(565,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-07 18:27:40'),(566,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 10:35:27'),(567,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 12:28:49'),(568,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 13:57:26'),(569,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 14:36:21'),(570,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 14:43:51'),(571,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 14:50:07'),(572,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 15:55:41'),(573,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 16:31:04'),(574,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 16:52:30'),(575,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 17:15:52'),(576,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 17:34:33'),(577,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 18:05:11'),(578,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 18:19:45'),(579,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 18:25:58'),(580,'超级管理员-system','127.0.0.1','2022-02-08 18:50:40'),(581,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 19:28:32'),(582,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 20:17:48'),(583,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 20:30:36'),(584,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 20:57:24'),(585,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 21:04:04'),(586,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-08 21:53:20'),(587,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 00:04:27'),(588,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 00:05:31'),(589,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 00:06:00'),(590,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 11:02:19'),(591,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 11:08:02'),(592,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 13:00:47'),(593,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 13:20:54'),(594,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 13:28:50'),(595,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 13:50:08'),(596,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 14:17:11'),(597,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 14:20:02'),(598,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 14:21:41'),(599,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 14:31:43'),(600,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 14:34:54'),(601,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 15:04:50'),(602,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 15:37:46'),(603,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 15:52:08'),(604,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 16:04:32'),(605,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-09 16:06:13'),(606,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-14 11:01:58'),(607,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-14 11:08:33'),(608,'落亦--luoyi','0:0:0:0:0:0:0:1','2022-02-14 15:15:36'),(609,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-14 15:27:02'),(610,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-14 15:27:34'),(611,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-14 15:29:30'),(612,'落亦--luoyi','0:0:0:0:0:0:0:1','2022-02-14 15:29:51'),(613,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-14 15:44:57'),(614,'落亦--luoyi','0:0:0:0:0:0:0:1','2022-02-14 15:45:13'),(615,'落亦--luoyi','0:0:0:0:0:0:0:1','2022-02-14 15:49:45'),(616,'李四-lisi','0:0:0:0:0:0:0:1','2022-02-14 15:58:18'),(617,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-14 16:53:26'),(618,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-14 16:58:42'),(619,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-14 17:07:46'),(620,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-14 17:08:26'),(621,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-14 17:10:01'),(622,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-14 18:08:41'),(623,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-17 15:06:53'),(624,'超级管理员-system','0:0:0:0:0:0:0:1','2022-02-17 15:22:12'),(625,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-17 15:32:00'),(626,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-17 15:32:28'),(627,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-17 15:45:53'),(628,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-17 15:57:48'),(629,'老严-system','0:0:0:0:0:0:0:1','2022-02-17 16:09:05'),(630,'王老师-system','0:0:0:0:0:0:0:1','2022-02-17 16:29:41'),(631,'王老师-system','0:0:0:0:0:0:0:1','2022-02-17 16:35:45'),(632,'王老师-system','0:0:0:0:0:0:0:1','2022-02-17 16:36:01'),(633,'王老师-system','0:0:0:0:0:0:0:1','2022-02-17 17:20:38'),(634,'王老师-system','0:0:0:0:0:0:0:1','2022-02-17 18:10:42'),(635,'王老师-system','0:0:0:0:0:0:0:1','2022-02-17 19:13:15'),(636,'王老师-system','0:0:0:0:0:0:0:1','2022-02-17 21:28:10'),(637,'王老师-system','0:0:0:0:0:0:0:1','2022-02-17 22:48:30'),(638,'王老师-system','0:0:0:0:0:0:0:1','2022-02-18 10:25:00'),(639,'王老师-system','0:0:0:0:0:0:0:1','2022-02-18 10:59:49'),(640,'王老师-system','0:0:0:0:0:0:0:1','2022-02-18 13:08:39'),(641,'王老师-system','0:0:0:0:0:0:0:1','2022-02-18 16:38:35'),(642,'王老师-system','0:0:0:0:0:0:0:1','2022-02-18 17:15:01'),(643,'王老师-system','0:0:0:0:0:0:0:1','2022-02-18 18:31:19'),(644,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-18 18:34:16'),(645,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-18 19:27:55'),(646,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-18 20:00:04'),(647,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-18 20:40:33'),(648,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-18 23:55:14'),(649,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 10:50:50'),(650,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 11:29:19'),(651,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 12:12:16'),(652,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 13:06:01'),(653,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 15:32:36'),(654,'系统管理员-system','127.0.0.1','2022-02-19 16:25:28'),(655,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 16:37:07'),(656,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 16:53:23'),(657,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 17:06:58'),(658,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 17:24:48'),(659,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 17:51:31'),(660,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 18:16:11'),(661,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 18:29:09'),(662,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 19:21:11'),(663,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 22:48:41'),(664,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 23:31:19'),(665,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 23:33:45'),(666,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-19 23:51:14'),(667,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 13:56:24'),(668,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 14:02:46'),(669,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 14:05:01'),(670,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 14:24:35'),(671,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 14:44:25'),(672,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 15:08:48'),(673,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 16:19:01'),(674,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 16:30:12'),(675,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 16:43:20'),(676,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 16:43:57'),(677,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 18:23:16'),(678,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 19:30:13'),(679,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 19:33:35'),(680,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 19:34:11'),(681,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 19:38:18'),(682,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 19:42:23'),(683,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 19:57:05'),(684,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 20:21:18'),(685,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 20:22:48'),(686,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-20 20:33:28'),(687,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-21 14:02:36'),(688,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-21 14:36:54'),(689,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-21 15:09:57'),(690,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-21 17:23:18'),(691,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-21 17:28:15'),(692,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-21 17:29:40'),(693,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-21 18:27:29'),(694,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-21 18:38:02'),(695,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-21 19:00:26'),(696,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-21 19:10:04'),(697,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-21 19:59:23'),(698,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-21 20:43:29'),(699,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 12:18:57'),(700,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 12:20:41'),(701,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 12:23:57'),(702,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 13:11:52'),(703,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 13:20:05'),(704,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 13:22:09'),(705,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 14:09:41'),(706,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 14:21:23'),(707,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 14:37:20'),(708,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 15:03:41'),(709,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 15:34:30'),(710,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 15:36:33'),(711,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 16:03:27'),(712,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 16:08:09'),(713,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 16:50:50'),(714,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 18:19:06'),(715,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 18:34:11'),(716,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 19:09:16'),(717,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 19:54:35'),(718,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 20:39:08'),(719,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 20:52:23'),(720,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 21:44:24'),(721,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 21:51:56'),(722,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-22 22:31:41'),(723,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-23 15:17:29'),(724,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-23 15:19:32'),(725,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-23 16:48:05'),(726,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-23 16:58:17'),(727,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-23 19:05:18'),(728,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-23 21:30:54'),(729,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-23 21:39:58'),(730,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 11:04:51'),(731,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 13:57:47'),(732,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 14:48:50'),(733,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 16:24:46'),(734,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 16:54:13'),(735,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 17:03:51'),(736,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 17:21:54'),(737,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 18:19:25'),(738,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 18:28:13'),(739,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 18:40:10'),(740,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 18:48:21'),(741,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 18:58:47'),(742,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 19:08:55'),(743,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 19:10:10'),(744,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 19:11:55'),(745,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 19:32:59'),(746,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 20:03:56'),(747,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 20:07:49'),(748,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 20:13:39'),(749,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 20:17:08'),(750,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 20:23:41'),(751,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 20:27:07'),(752,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 21:21:34'),(753,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 22:13:54'),(754,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 22:15:02'),(755,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 22:16:04'),(756,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 22:49:33'),(757,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 22:59:57'),(758,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-24 23:04:48'),(759,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 11:36:51'),(760,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 13:28:36'),(761,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 13:36:28'),(762,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 14:34:54'),(763,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 14:48:26'),(764,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 15:31:02'),(765,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 16:55:52'),(766,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 17:38:20'),(767,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 17:46:45'),(768,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 17:50:25'),(769,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 18:50:09'),(770,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 18:53:15'),(771,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 21:37:42'),(772,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 22:04:59'),(773,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 22:26:35'),(774,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 22:46:53'),(775,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-25 22:47:36'),(776,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-28 09:40:01'),(777,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-28 10:28:31'),(778,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-28 11:27:28'),(779,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-28 11:36:49'),(780,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-28 14:07:20'),(781,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-28 14:27:41'),(782,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-28 15:12:51'),(783,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-28 15:31:16'),(784,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-28 16:28:11'),(785,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-28 16:47:59'),(786,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-28 17:10:05'),(787,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-28 18:54:45'),(788,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-28 19:24:31'),(789,'系统管理员-system','0:0:0:0:0:0:0:1','2022-02-28 19:39:39'),(790,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-01 09:35:25'),(791,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-01 09:56:50'),(792,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-01 10:10:34'),(793,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-01 11:03:20'),(794,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-01 11:08:05'),(795,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-01 11:10:07'),(796,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-01 14:02:52'),(797,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-01 14:30:24'),(798,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-01 14:44:42'),(799,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-01 16:07:14'),(800,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-01 16:44:25'),(801,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-01 18:42:44'),(802,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-01 19:55:42'),(803,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-02 09:22:59'),(804,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-02 10:42:40'),(805,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-02 11:04:49'),(806,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-02 11:20:48'),(807,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-02 13:57:23'),(808,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-02 14:32:35'),(809,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-02 14:35:06'),(810,'张三-1801064101','0:0:0:0:0:0:0:1','2022-03-02 14:35:30'),(811,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-02 14:36:00'),(812,'张三-1801064101','0:0:0:0:0:0:0:1','2022-03-02 14:39:03'),(813,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-02 14:55:26'),(814,'张三-1801064101','0:0:0:0:0:0:0:1','2022-03-02 15:05:35'),(815,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-02 15:06:21'),(816,'张三-1801064101','0:0:0:0:0:0:0:1','2022-03-02 15:08:50'),(817,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-02 15:09:23'),(818,'张三-1801064101','0:0:0:0:0:0:0:1','2022-03-02 15:26:51'),(819,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-02 15:28:23'),(820,'张三-1801064101','0:0:0:0:0:0:0:1','2022-03-02 16:01:18'),(821,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-02 16:01:47'),(822,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-02 16:12:59'),(823,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-02 16:15:28'),(824,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-02 16:16:09'),(825,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-02 16:17:32'),(826,'张三-1801064101','0:0:0:0:0:0:0:1','2022-03-02 16:24:29'),(827,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-02 16:34:47'),(828,'王五-1801064102','0:0:0:0:0:0:0:1','2022-03-02 16:38:35'),(829,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-02 18:30:58'),(830,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-02 19:21:02'),(831,'张三-1801064101','0:0:0:0:0:0:0:1','2022-03-02 19:25:48'),(832,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-02 19:29:26'),(833,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 09:29:59'),(834,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 09:45:43'),(835,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 09:46:27'),(836,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 09:57:05'),(837,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 10:06:40'),(838,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 10:15:39'),(839,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 10:24:38'),(840,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 10:32:39'),(841,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 10:48:51'),(842,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 10:56:53'),(843,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 14:38:38'),(844,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 15:44:19'),(845,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 19:16:34'),(846,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 19:43:38'),(847,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 19:48:28'),(848,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 19:54:26'),(849,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 20:02:04'),(850,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 20:03:15'),(851,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 20:06:02'),(852,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 20:10:33'),(853,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 20:17:04'),(854,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 20:18:05'),(855,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 20:19:28'),(856,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 20:21:30'),(857,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 20:34:49'),(858,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 20:43:20'),(859,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 20:54:13'),(860,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-03 21:27:35'),(861,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 09:18:17'),(862,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 10:22:03'),(863,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 10:40:43'),(864,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 10:45:18'),(865,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 10:53:37'),(866,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 11:11:14'),(867,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 11:21:07'),(868,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 11:36:06'),(869,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 13:47:57'),(870,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 13:53:39'),(871,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 13:56:41'),(872,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 13:59:36'),(873,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 14:02:50'),(874,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 14:15:27'),(875,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 14:37:08'),(876,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 14:39:56'),(877,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 14:51:24'),(878,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 16:17:22'),(879,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 18:33:03'),(880,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 19:48:39'),(881,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-04 21:17:23'),(882,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 09:37:26'),(883,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 09:42:54'),(884,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 09:44:28'),(885,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 09:57:52'),(886,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 10:04:19'),(887,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 10:35:08'),(888,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 11:12:11'),(889,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 11:18:21'),(890,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 11:20:04'),(891,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 11:24:36'),(892,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 14:15:02'),(893,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 14:17:08'),(894,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 14:38:02'),(895,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 14:53:28'),(896,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 15:06:41'),(897,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 15:14:53'),(898,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 16:10:22'),(899,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 16:30:11'),(900,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 16:34:02'),(901,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 16:53:52'),(902,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 18:37:21'),(903,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 19:17:33'),(904,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 19:28:37'),(905,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 19:35:13'),(906,'系统管理员-system','127.0.0.1','2022-03-05 19:42:09'),(907,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 19:48:27'),(908,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 19:55:46'),(909,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 20:03:08'),(910,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 20:08:50'),(911,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 20:17:12'),(912,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 20:23:28'),(913,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 20:42:34'),(914,'张三-1801064101','0:0:0:0:0:0:0:1','2022-03-05 20:44:40'),(915,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-05 20:47:27'),(916,'王五-1801064102','0:0:0:0:0:0:0:1','2022-03-05 20:48:35'),(917,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 20:52:49'),(918,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-05 20:58:38'),(919,'张三-1801064101','0:0:0:0:0:0:0:1','2022-03-05 20:59:04'),(920,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-05 20:59:21'),(921,'王五-1801064102','0:0:0:0:0:0:0:1','2022-03-05 20:59:40'),(922,'王五-1801064102','0:0:0:0:0:0:0:1','2022-03-05 21:03:12'),(923,'王五-1801064102','0:0:0:0:0:0:0:1','2022-03-06 14:21:46'),(924,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-06 14:22:35'),(925,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-06 19:28:39'),(926,'张三丰-1801064103','0:0:0:0:0:0:0:1','2022-03-06 19:31:21'),(927,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-06 19:33:54'),(928,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-06 19:34:48'),(929,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-06 19:39:55'),(930,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-06 21:01:19'),(931,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-07 13:56:02'),(932,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-07 13:57:50'),(933,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-07 13:59:09'),(934,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-07 14:02:04'),(935,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-07 14:31:27'),(936,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-07 14:39:19'),(937,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-12 21:26:04'),(938,'admin-admin','0:0:0:0:0:0:0:1','2022-03-12 21:27:09'),(939,'admin-admin','0:0:0:0:0:0:0:1','2022-03-12 21:29:26'),(940,'admin-admin','0:0:0:0:0:0:0:1','2022-03-12 21:30:18'),(941,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-13 15:03:49'),(942,'admin-admin','0:0:0:0:0:0:0:1','2022-03-13 15:19:20'),(943,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-13 15:20:01'),(944,'管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-13 15:20:54'),(945,'管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-13 15:36:09'),(946,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-13 15:40:14'),(947,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-13 15:43:07'),(948,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-13 15:44:23'),(949,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-13 15:45:14'),(950,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-13 15:45:33'),(951,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-13 15:45:57'),(952,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-13 15:46:24'),(953,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-13 15:47:11'),(954,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-13 15:47:45'),(955,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-13 15:48:30'),(956,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-13 15:49:13'),(957,'admin-admin','0:0:0:0:0:0:0:1','2022-03-13 16:11:25'),(958,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-13 16:11:56'),(959,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-13 16:12:13'),(960,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-13 16:13:08'),(961,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-13 16:17:19'),(962,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-13 16:31:02'),(963,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-13 16:32:26'),(964,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-13 16:32:40'),(965,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-13 16:38:42'),(966,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-13 17:49:18'),(967,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-23 13:40:30'),(968,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-23 13:43:28'),(969,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-23 13:48:37'),(970,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-23 13:49:48'),(971,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-23 14:19:39'),(972,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-23 14:21:40'),(973,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-23 14:26:01'),(974,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-23 14:30:34'),(975,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-23 14:35:43'),(976,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-23 14:36:16'),(977,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-23 14:37:34'),(978,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-23 14:55:50'),(979,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-23 15:00:28'),(980,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-23 15:02:24'),(981,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-23 15:03:50'),(982,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-23 15:04:20'),(983,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-23 15:04:36'),(984,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-23 15:06:16'),(985,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-23 16:17:57'),(986,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-23 16:25:24'),(987,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 17:02:57'),(988,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-23 17:03:19'),(989,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 17:03:31'),(990,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 17:11:11'),(991,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 17:29:32'),(992,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 17:32:54'),(993,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 17:51:24'),(994,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 17:56:32'),(995,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 17:58:19'),(996,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 18:00:30'),(997,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 18:02:25'),(998,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 18:25:52'),(999,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-23 18:32:02'),(1000,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-23 18:40:58'),(1001,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 18:46:12'),(1002,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 18:55:43'),(1003,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 18:59:00'),(1004,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 19:10:43'),(1005,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-23 19:18:09'),(1006,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-23 19:35:35'),(1007,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-23 21:11:43'),(1008,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-23 21:16:55'),(1009,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-23 21:17:12'),(1010,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-23 21:24:43'),(1011,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-23 21:26:03'),(1012,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-23 21:28:31'),(1013,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-23 22:02:53'),(1014,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-23 22:35:15'),(1015,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-23 22:47:06'),(1016,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-23 23:54:14'),(1017,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-24 00:02:55'),(1018,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-24 00:06:38'),(1019,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-24 00:22:53'),(1020,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-24 00:31:13'),(1021,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-24 13:46:43'),(1022,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-24 14:00:12'),(1023,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-24 14:06:05'),(1024,'超级管理员-systemadmin','0:0:0:0:0:0:0:1','2022-03-24 14:10:37'),(1025,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-24 15:05:27'),(1026,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-24 15:27:44'),(1027,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-24 15:28:00'),(1028,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-24 15:28:19'),(1029,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-24 15:32:51'),(1030,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-24 15:36:46'),(1031,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-24 15:41:10'),(1032,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-24 15:42:13'),(1033,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-24 15:43:04'),(1034,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-24 15:43:53'),(1035,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-24 15:45:01'),(1036,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-24 15:46:25'),(1037,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-24 22:05:19'),(1038,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-24 22:08:37'),(1039,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-24 22:12:07'),(1040,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-24 22:33:37'),(1041,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-25 11:06:41'),(1042,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-25 11:10:36'),(1043,'小王-1801061121','0:0:0:0:0:0:0:1','2022-03-25 14:08:54'),(1044,'辅导员-teacher','0:0:0:0:0:0:0:1','2022-03-25 14:10:05'),(1045,'系统管理员-system','0:0:0:0:0:0:0:1','2022-03-25 14:14:07');

/*Table structure for table `sys_notice` */

DROP TABLE IF EXISTS `sys_notice`;

CREATE TABLE `sys_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` text,
  `createtime` datetime DEFAULT NULL,
  `opername` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_notice` */

insert  into `sys_notice`(`id`,`title`,`content`,`createtime`,`opername`) values (61,'明天开学公告','<b><i>明天8:00报道,记得准时喔。</i></b>','2022-01-16 23:42:33','超级管理员'),(73,'明天开学就不要来了','疫情严重，安全第一<img src=\"http://localhost:8089/resources/layui/images/face/2.gif\" alt=\"[哈哈]\"><img src=\"http://localhost:8089/resources/layui/images/face/1.gif\" alt=\"[嘻嘻]\">','2022-02-22 23:01:06','系统管理员'),(74,'国家励志奖学金评定方法','<p class=\"MsoNormal\" style=\"text-align: left;\"><span><font face=\"宋体\">同年级同专业拉通评选，学习成绩和综合考评成绩，原则上应在评选范围内位于前</font>10%。学习成绩排名或综合考评成绩排名超出前10%，但均位于前30%的，必须在参评学年（2019—2020学年）获得1次及以上校级（含校级）以上表彰奖励。参选人员统一算出综合成绩，进行排名，依次获评。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\" style=\"text-align: left;\"><span>学习成绩计算范围为上一学年</span><span>通识</span><span>必修课、专业必修课</span><span>、</span><span>学科基础必修课</span><span>、公共基础、专业基础、集中实践教学环节和专周成绩。</span><span>学习成绩最终取各科成绩平均值，得分</span><span>精确到小数点后两位。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\" style=\"text-align: left;\"><span><font face=\"宋体\">综合成绩的评定标准依据上一学年从学习成绩、学术科研、社会实践和所获荣誉四个方面的表现综合量化考核评分。其中，学习成绩占</font>50%、学术科研与技能占25%、社会实践与志愿服务占15%、荣誉占10%。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\" style=\"text-align: left;\"><b><span><font face=\"宋体\">综合成绩计算公式：</font> </span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\" style=\"text-align: center;\"><font face=\"宋体\" style=\"\"><b>综合成绩</b></font><b>=学习成绩*50%+学术科研与技能*25%+社会实践与志愿服务*15%+荣誉*10%</b><o:p></o:p></p>','2022-02-22 23:12:19','系统管理员'),(75,'学期学习成绩提交指标  占50%','<p class=\"MsoNormal\"><b><span>（</span></b><b><span>一</span></b><b><span>）</span></b><b><span>学习成绩</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span>学习成绩计算范围为上一学年</span><b><span>通识</span><span>必修课、专业必修课</span><span>、</span><span>学科基础必修课</span><span>、公共基础、专业基础、集中实践教学环节和专周成绩</span></b><span>。</span><span>学习成绩最终取各科成绩平均值，得分</span><span>精确到小数点后两位。</span></p>','2022-02-22 23:14:06','系统管理员'),(76,'学术科研与技能评定指标   占25%','<p class=\"MsoNormal\"><b><span>（</span></b><b><span>二</span></b><b><span>）</span></b><b><span>学术科研</span></b><b><span>与技能</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span>学术科研成果为上一学年公开出版的书籍、发表的论文、参与的大创或课题</span><span>、</span><span>申报成功的专利</span><span>等，须结项。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><!--[if !supportLists]--><span>1、</span><!--[endif]--><b><span>出版书籍</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span>单独出版计</span><span>8</span><span><font face=\"宋体\">分</font>/本；共同出版计3分/本</span><span>，</span><span><font face=\"宋体\">作为主编加</font>3分，副主编加2分，参编成员加1分</span><span>。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><!--[if !supportLists]--><span>2、</span><!--[endif]--><b><span>发表论文</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span>核心期刊：第一作者计</span><span>8</span><span><font face=\"宋体\">分</font>/篇；第二作者计</span><span>6</span><span><font face=\"宋体\">分</font>/篇；第三作者计</span><span>4</span><span><font face=\"宋体\">分</font>/篇；</span><span>第四</span><span>及其以后</span><span>计</span><span>2</span><span>分；</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span>非核心期刊</span><span>：</span><span><font face=\"宋体\">第一作者计</font>3分/篇，第二作者计</span><span>2</span><span><font face=\"宋体\">分</font>/篇，第三作者</span><span>及其以后</span><span>计</span><span>1</span><span><font face=\"宋体\">分</font>/篇。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><b><span><font face=\"宋体\">说明</font>:</span></b><span>论文作者单位须为成都工业学院。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><!--[if !supportLists]--><span>3、</span><!--[endif]--><b><span>创新创业训练计划项目或课题研究</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span>国家级计</span><span>5</span><span>分、省级计</span><span>3</span><span>分、校级计</span><span>1</span><span>分。</span><span>再根据项目或课题的参与度，</span><span>作为</span><span>第一主研</span><span>，</span><span><font face=\"宋体\">加</font>3</span><span><font face=\"宋体\">分</font>/项；作为第</span><span>二</span><span>主研，加</span><span>1</span><span><font face=\"宋体\">分</font>/项；作为第</span><span>三、四、五</span><span>主研，加</span><span>0.5</span><span><font face=\"宋体\">分</font>/项。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><b><span><font face=\"宋体\">说明</font>:</span></b><b><span>1、</span></b><span>同一项目与课题不叠加</span><span><font face=\"宋体\">。</font>2、立项项目加一半分值，结项项目加全分。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><!--[if !supportLists]--><span>4、</span><!--[endif]--><b><span>竞赛获奖</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><b><span><font face=\"宋体\">（</font>1）“互联网+”、“挑战杯”等科技、学科类竞赛获奖</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span>国家级：</span><span>一等奖</span><span><font face=\"宋体\">计</font>8</span><span>分，二等奖</span><span><font face=\"宋体\">计</font>6</span><span>分，三等奖</span><span><font face=\"宋体\">计</font>4</span><span>分</span><span><font face=\"宋体\">，优秀奖计</font>3分</span><span>；</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span><font face=\"宋体\">省级：一等奖计</font>6分，二等奖计4分，三等奖计2分，优秀奖计1分；</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span>校级：</span><span>一等奖</span><span><font face=\"宋体\">计</font>4</span><span>分，二等奖</span><span><font face=\"宋体\">计</font>2</span><span>分，三等奖</span><span><font face=\"宋体\">计</font>1</span><span>分</span><span><font face=\"宋体\">，优秀奖计</font>0.5分。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><!--[if !supportLists]--><span>（2）</span><!--[endif]--><b><span>非科技、学科类竞赛获奖</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span>国家级：</span><span>一等奖</span><span>计</span><span>8</span><span>分，二等奖</span><span>计</span><span>6</span><span>分，三等奖</span><span>计</span><span>4</span><span>分</span><span>，优秀奖计</span><span>2</span><span>分</span><span>；</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span>省级：一等奖计</span><span>6</span><span>分，二等奖计</span><span>4</span><span>分，三等奖计</span><span>2</span><span>分，优秀奖计</span><span>1</span><span>分；</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span>校级：</span><span>一等奖</span><span>计</span><span>3</span><span>分，二等奖</span><span>计</span><span>2</span><span>分，三等奖</span><span>计</span><span>1</span><span>分</span><span>，</span><span><font face=\"宋体\">优秀奖计</font>0.5分；</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span><font face=\"宋体\">院级：一等奖计</font>1分，二等奖计0.5分，三等奖计0.3分，优秀奖计0.1分。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><b><span><font face=\"宋体\">说明</font>:</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span><font face=\"宋体\">（</font>1）获奖后出具证书或正规的证明材料及举办竞赛活动的正式文件后方可加分；具体级别由学院审核确定。&nbsp; </span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span><font face=\"宋体\">（</font>2）4人（含）以上团队获奖者，按先后排名依次减一分，最终得分不低于1分；&nbsp;</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><!--[if !supportLists]--><span>（3）</span><!--[endif]--><span>同一项目获不同级别奖励者按最高级别加分</span><span>；</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><!--[if !supportLists]--><span>（4）</span><!--[endif]--><span><font face=\"宋体\">文体类活动校级最高加分限制为</font>4分；</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><!--[if !supportLists]--><span>（5）</span><!--[endif]--><span>社团活动获奖按院级计分规则进行加分，最多计三项奖项。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><!--[if !supportLists]--><span>5、</span><!--[endif]--><b><span>专利</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span><font face=\"宋体\">发明专利：排名第一计</font>8分/个；排名第二计6分/个；排名第三计4分/个；排名第四2分/个；</span><span><font face=\"宋体\">排名第五及以后</font>1分/个。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span>实用新型</span><span>等</span><span><font face=\"宋体\">专利：排名第一计</font>3分/个，排名第二计2分/个，排名第三计1分/个；排名第四</span><span>及以后</span><span>0.5分/个。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><b><span>说明：</span></b><span>授权专利才可计分。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><!--[if !supportLists]--><span>6、</span><!--[endif]--><b><span>技能证书</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span>英语六级、计算机三级计</span><span>5</span><span>分；英语四级、计算机二级、普通话</span><span>一</span><span>级计</span><span>2</span><span>分。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span>教师资格证、会计证、心理咨询证等基础职业资格证书计</span><span>1</span><span>分。</span></p>','2022-02-22 23:14:56','系统管理员'),(77,'社会实践活动评定指标   占15%','<p class=\"MsoNormal\"><b><span>（</span></b><b><span>三</span></b><b><span>）</span></b><b><span>社会实践</span></b><b><span>与志愿服务</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span>社会实践</span><span>与志愿服务</span><span>包含参与的</span><span>社会</span><span>实践活动、公益</span><span>与</span><span>志愿服务</span><span>活动</span><span>、社会工作等。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><b><span>1、社会</span></b><b><span>实践活动</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span><font face=\"宋体\">国家级，计</font>5分/项；</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span><font face=\"宋体\">省市级，计</font>4分/项；</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span><font face=\"宋体\">校级，计</font>3分/项；</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span><font face=\"宋体\">院级，计</font>2分/项。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><b><span>说明：</span></b><span>此项内容最高计五分。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><b><span>2、</span></b><b><span>公益</span></b><b><span>与</span></b><b><span>志愿服务活动</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span><font face=\"宋体\">省级及以上各类志愿者项目</font>1分/项，校级各类志愿者项目0.5分/项。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><b><span>说明：</span></b><span><font face=\"宋体\">此项内容最高计</font>3分。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><b><span>3、</span></b><b><span>社会工作</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span>社会工作包含在班级、</span><span>学院</span><span>、学校所担任的职务等</span><span>，</span><span>校级职务加分按院级加分</span><span>。按职务等级累计计分，同一等级内不累计计分，取最高得分。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><b><span>（</span></b><b><span>1</span></b><b><span>）</span></b><b><span>院</span></b><b><span>级</span></b><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span><font face=\"宋体\">团总支副书记、学生会主席加</font>4分；党支部副书记（学生）、学生会副主席、社团第一负责人加 3分；党支部委员、党员服务站负责人、社团第二负责人、部门正职加 2分；部门副职加1分；干事、党员责任岗成员加 0.5分。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><b><span><font face=\"宋体\">（</font>2）班级</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span><font face=\"宋体\">班长、团支书、学习委员加</font> 3分；副班长、团支部副书记加2分；其余班干部加1分。</span><span><o:p></o:p></span></p>','2022-02-22 23:16:05','系统管理员'),(78,'荣誉申报评定指标    占10%','<p class=\"MsoNormal\"><b><span>（</span></b><b><span>四</span></b><b><span>）</span></b><b><span>荣誉</span></b><b><span><o:p></o:p></span></b></p><p class=\"MsoNormal\"><span>荣誉指的是申请者获得的各项荣誉（含个人、团体）等。按等级分别计分。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span><font face=\"宋体\">国家级，计</font>5分/项；</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span><font face=\"宋体\">省市级，计</font>4分/项；</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span>校级，计</span><span>2</span><span><font face=\"宋体\">分</font>/项；</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span>院级，计</span><span>1</span><span><font face=\"宋体\">分</font>/项。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><span><font face=\"宋体\">团体奖项按实际得分的</font>70%计算</span><span>，同一荣誉按最高等级计分，不累计加分。</span><span><o:p></o:p></span></p><p class=\"MsoNormal\"><b><span>&nbsp;<img src=\"http://localhost:8089/resources/layui/images/face/64.gif\" alt=\"[围观]\"></span></b></p>','2022-02-22 23:17:14','系统管理员');

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL COMMENT '权限类型[menu/permission]',
  `title` varchar(255) DEFAULT NULL,
  `percode` varchar(255) DEFAULT NULL COMMENT '权限编码[只有type= permission才有  user:view]',
  `icon` varchar(255) DEFAULT NULL,
  `href` varchar(255) DEFAULT NULL,
  `target` varchar(255) DEFAULT NULL,
  `open` int(11) DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `available` int(11) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`pid`,`type`,`title`,`percode`,`icon`,`href`,`target`,`open`,`ordernum`,`available`) values (1,0,'menu','学生成长管理评价系统',NULL,'&#xe68e;','','',1,1,1),(2,1,'menu','教师常规管理',NULL,'&#xe663;','','',0,120,1),(13,1,'menu','荣誉申报审核',NULL,'&#xe630;','','',0,130,1),(14,2,'menu','年级管理',NULL,'&#xe770;','/sys/toGradeManager','',0,14,1),(15,28,'menu','菜单管理',NULL,'&#xe663;','/sys/toMenuManager','',0,15,1),(16,28,'menu','权限管理','','&#xe857;','/sys/toPermissionManager','',0,16,1),(17,28,'menu','角色管理','','&#xe650;','/sys/toRoleManager','',0,17,1),(18,2,'menu','学生用户管理','','&#xe612;','/sys/toUserManager','',0,18,1),(19,1,'menu','学生学年成绩',NULL,'&#xe63c;','','',0,102,1),(20,1,'menu','科研技能审核',NULL,'&#xe629;','','',0,119,1),(21,27,'menu','登陆日志',NULL,'&#xe675;','/sys/toLoginfoManager','',0,21,1),(23,1,'menu','社会实践审核',NULL,'&#xe62e;','','',0,127,1),(24,1,'menu','学术科研与技能',NULL,'&#xe60e;','','',0,104,1),(25,1,'menu','社会实践活动',NULL,'&#xe62e;','','',0,111,1),(26,1,'menu','荣誉申报',NULL,'&#xe630;','','',0,115,1),(27,1,'menu','其它管理',NULL,'&#xe628;','','',0,6,1),(28,1,'menu','系统管理',NULL,'&#xe614;','','',0,5,1),(29,1,'permission','待审核数列表','teacher:count',NULL,NULL,NULL,0,91,1),(30,14,'permission','添加年级','grade:create','',NULL,NULL,0,24,1),(31,14,'permission','修改年级','grade:update','',NULL,NULL,0,26,1),(32,14,'permission','删除年级','grade:delete','',NULL,NULL,0,27,1),(34,15,'permission','添加菜单','menu:create','','','',0,29,1),(35,15,'permission','修改菜单','menu:update','',NULL,NULL,0,30,1),(36,15,'permission','删除菜单','menu:delete','',NULL,NULL,0,31,1),(38,16,'permission','添加权限','permission:create','',NULL,NULL,0,33,1),(39,16,'permission','修改权限','permission:update','',NULL,NULL,0,34,1),(40,16,'permission','删除权限','permission:delete','',NULL,NULL,0,35,1),(42,17,'permission','添加角色','role:create','',NULL,NULL,0,37,1),(43,17,'permission','修改角色','role:update','',NULL,NULL,0,38,1),(44,17,'permission','删除角色','role:delete','',NULL,NULL,0,39,1),(46,17,'permission','分配权限','role:selectPermission','',NULL,NULL,0,41,1),(47,18,'permission','添加用户','user:create','',NULL,NULL,0,42,1),(48,18,'permission','修改用户','user:update','',NULL,NULL,0,43,1),(49,18,'permission','删除用户','user:delete','',NULL,NULL,0,44,1),(51,18,'permission','用户分配角色','user:selectRole','',NULL,NULL,0,46,1),(52,18,'permission','重置密码','user:resetPwd',NULL,NULL,NULL,0,47,1),(53,14,'permission','年级查询','grade:view',NULL,NULL,NULL,0,48,1),(54,15,'permission','菜单查询','menu:view',NULL,NULL,NULL,0,49,1),(55,16,'permission','权限查询','permission:view',NULL,NULL,NULL,0,50,1),(56,17,'permission','角色查询','role:view',NULL,NULL,NULL,0,51,1),(57,18,'permission','用户查询','user:view',NULL,NULL,NULL,0,52,1),(58,1,'menu','系统公告',NULL,'&#xe756;','/sys/toNoticeManager','',0,22,1),(73,21,'permission','日志查询','info:view',NULL,NULL,NULL,NULL,65,1),(74,21,'permission','日志删除','info:delete',NULL,NULL,NULL,NULL,66,1),(75,21,'permission','日志批量删除','info:batchdelete',NULL,NULL,NULL,NULL,67,1),(76,22,'permission','公告查询','notice:view',NULL,NULL,NULL,NULL,68,1),(77,22,'permission','公告添加','notice:create',NULL,NULL,NULL,NULL,69,1),(78,22,'permission','公告修改','notice:update',NULL,NULL,NULL,NULL,70,1),(79,22,'permission','公告删除','notice:delete',NULL,NULL,NULL,NULL,71,1),(86,22,'permission','公告查看','notice:viewnotice',NULL,NULL,NULL,NULL,78,1),(119,22,'permission','公告批量删除','notice:batchdelete',NULL,NULL,NULL,0,87,1),(122,27,'menu','缓存管理',NULL,'&#xe630','/sys/toCacheManager','_black',1,88,1),(123,122,'permission','同步缓存','cache:syncCache',NULL,NULL,NULL,0,89,1),(124,122,'permission','清空缓存','cache:removeAllCache',NULL,NULL,NULL,0,90,1),(148,19,'menu','学年成绩提交',NULL,'&#xe63c;','/growth/scoreSubmit','',0,103,1),(150,24,'menu','出版书籍',NULL,'&#xe705;','/growth/bookSubmit','',0,105,1),(151,24,'menu','发表论文',NULL,'&#xe63c;','/growth/paperSubmit','',0,106,1),(152,24,'menu','创新课题研究',NULL,'&#xe66c;','/growth/innovateSubmit','',0,107,1),(153,24,'menu','竞赛获奖',NULL,'&#xe756;','/growth/competitionSubmit','',0,108,1),(154,24,'menu','专利申报',NULL,'&#xe672;','/growth/patentSubmit','',0,109,1),(155,24,'menu','技能证书',NULL,'&#xe630;','/growth/certificateSubmit','',0,110,1),(157,25,'menu','社会实践与志愿',NULL,'&#xe66c;','/growth/volunteerSubmit','',0,112,1),(159,25,'menu','社会工作',NULL,'&#xe6c9;','/growth/workSubmit','',0,114,1),(161,26,'menu','荣誉申报',NULL,'&#xe600;','/growth/honorSubmit','',0,116,1),(163,2,'menu','学生学年成绩',NULL,'&#xe62d;','/growth/scoreManager','',0,118,1),(164,20,'menu','教师书籍审核',NULL,'&#xe705;','/growth/bookChecking','',0,19,1),(167,20,'menu','发表论文审核',NULL,'&#xe63c;','/growth/paperChecking','',0,121,1),(168,20,'menu','创新课题审核',NULL,'&#xe66c;','/growth/innovateCheck','',0,122,1),(170,20,'menu','竞赛获奖审核',NULL,'&#xe756;','/growth/competitionCheck','',1,124,1),(171,20,'menu','专利审核',NULL,'&#xe672;','/growth/patentCheck','',0,125,1),(172,20,'menu','技能证书审核',NULL,'&#xe630;','/growth/certificateCheck','',1,126,1),(174,23,'menu','实践与志愿审核',NULL,'&#xe66c;','/growth/volunteerCheck','',0,128,1),(175,23,'menu','社会工作审核',NULL,'&#xe6c9;','/growth/workCheck','',0,129,1),(177,13,'menu','荣誉申报审核',NULL,'&#xe600;','/growth/honorCheck','',0,131,1),(178,2,'menu','综合成绩排名',NULL,'&#xe656;','/growth/allScore','',0,132,1),(179,2,'menu','评分细则管理',NULL,'&#xe63c;','/growth/rule','',0,133,1);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`remark`,`available`,`createtime`) values (1,'系统管理员','拥有所有菜单，用于系统维护',1,'2021-03-01 14:06:32'),(2,'辅导员','管理学生学年成就审核排名',1,'2021-05-12 14:13:04'),(3,'学生','用于学生提交学年信息',1,'2021-05-12 14:13:30');

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`pid`,`rid`) USING BTREE,
  KEY `sys_role_permission_ibfk_1` (`pid`) USING BTREE,
  KEY `sys_role_permission_ibfk_2` (`rid`) USING BTREE,
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE,
  CONSTRAINT `sys_role_permission_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`rid`,`pid`) values (1,1),(2,1),(3,1),(2,2),(2,13),(1,14),(2,14),(1,15),(1,16),(1,17),(1,18),(2,18),(3,19),(2,20),(1,21),(2,23),(3,24),(3,25),(3,26),(1,27),(1,28),(2,29),(1,30),(2,30),(1,31),(2,31),(1,32),(2,32),(1,34),(1,35),(1,36),(1,38),(1,39),(1,40),(1,42),(1,43),(1,44),(1,46),(1,47),(2,47),(1,48),(2,48),(1,49),(2,49),(1,51),(2,51),(1,52),(2,52),(1,53),(2,53),(1,54),(1,55),(1,56),(1,57),(2,57),(1,58),(2,58),(1,73),(1,74),(1,75),(1,76),(1,77),(1,78),(1,79),(1,86),(1,119),(1,122),(1,123),(1,124),(3,148),(3,150),(3,151),(3,152),(3,153),(3,154),(3,155),(3,157),(3,159),(3,161),(2,163),(2,164),(2,167),(2,168),(2,170),(2,171),(2,172),(2,174),(2,175),(2,177),(2,178),(2,179);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `loginname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '登陆学号',
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `gradeid` int(11) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL,
  `available` int(11) DEFAULT '1' COMMENT '是否可用，0不可用，1可用',
  `type` int(11) DEFAULT NULL COMMENT '用户类型[0超级管理员，1管理员，2普通用户]',
  `imgpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '用户头像地址',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '盐',
  PRIMARY KEY (`id`,`loginname`),
  UNIQUE KEY `sys_user_loginname` (`loginname`) USING BTREE COMMENT '登陆名称唯一',
  KEY `FK_sys_dept_sys_user` (`gradeid`) USING BTREE,
  CONSTRAINT `FK_sys_dept_sys_user` FOREIGN KEY (`gradeid`) REFERENCES `sys_grade` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`name`,`loginname`,`pwd`,`address`,`email`,`sex`,`remark`,`gradeid`,`createdate`,`available`,`type`,`imgpath`,`salt`) values (1,'系统管理员','system','0df03bc8351c2a46b53cbf7183ddf8ff','成都郫都区','3474737272@qq.com',1,'系统管理员',1,'2021-05-15 16:00:00',1,2,'2022-02-07/A8A8683F7B8E4DB8B16AA97A89C82FA8.jpeg','BB9D134CAC734E07B13249D09D0C68A3'),(2,'辅导员','teacher','532ac00e86893901af5f0be6b704dbc7','四川省内江市隆昌','3474737272@qq.com',1,'辅导员',1,'2022-02-10 15:53:39',1,2,'2022-02-07/A8A8683F7B8E4DB8B16AA97A89C82FA8.jpeg','04A93C74C8294AA09A8B974FD1F4ECBB'),(3,'张三','1801064101','532ac00e86893901af5f0be6b704dbc7','四川省自贡市','3474737272@qq.com',1,'好学生！',5,'2022-02-16 15:56:42',1,1,'2022-02-07/A8A8683F7B8E4DB8B16AA97A89C82FA8.jpeg','04A93C74C8294AA09A8B974FD1F4ECBB'),(4,'超级管理员','systemadmin','9f59d30fc6f29dbefafcffc9f6665130','内江隆昌','1844246071@qq.com',1,'21',1,'2022-03-13 15:17:36',1,0,'/images/defaultUserTitle.jpg','AC9FE4A477A941A892A78C718CCE2B32'),(21,'王五','1801064102','52297f32fbfe12f3cdfd52805ad2de2d','内江隆昌','3474737272@qq.com',1,'nice',5,'2022-02-17 00:00:00',1,1,'2022-03-02/4372617D02A343B6B85166AD58DB4BA2.jpg','36BFCDC1F4BF4F4CBB90EF695207960F'),(22,'张三丰','1801064103','28c4b71e6504797a6fe4791b84cbb954','内江隆昌','3474737272@qq.com',1,'好学生！',5,'2022-02-18 11:02:12',1,1,'/images/defaultUserTitle.jpg','069D34F89FD744B99AABFE4402E04BD5'),(23,'李四','1801064104','7002d2455acb78cfe0c3ab9a8bb11d12','内江隆昌','3474737272@qq.com',1,'12',5,'2022-02-18 11:03:20',1,1,'/images/defaultUserTitle.jpg','A45B9BAFC7E14F0FA11597EED3C9369D'),(24,'李思','1801064105','6cccc3c5fad25a4ce1cbbcbe1d8a221c','广西','3474737272@qq.com',0,'很好的一个人！',5,'2022-02-24 18:15:31',1,1,'/images/defaultUserTitle.jpg','F7E51B8CA3EE45BCAF3A0FA77BF05033'),(25,'王五','1801064106','79daf05fa2c58fb4cd147a77e1d4971f','山东','3474737272@qq.com',1,'很诚实',5,'2022-02-24 18:28:28',1,1,'/images/defaultUserTitle.jpg','D056DC73D82D4BAE8139F0B401EB8681'),(27,'徐峰','1801064107','4e3cb20f41f38524f620c8411eaebb6a','北京','3474737272@qq.com',1,'非常幽默',5,'2022-02-24 18:48:46',1,1,'/images/defaultUserTitle.jpg','6B8AB60A2192431A9C5B7BD122E7F7E1'),(28,'阿凡达-','1901064101','ff3c49658723ea5280b54dcca634dc36','外星球！','3474737272@qq.com',0,'不一样的人！',7,'2022-02-25 19:01:56',1,1,'/images/defaultUserTitle.jpg','F5DEB07AC728452096081D6F2CA102B4'),(29,'刘思','1901064102','f62162349c7c031203bbaa9b7e838387','广西','3474737272@qq.com',1,'老实人',7,'2022-02-24 20:13:51',1,1,'/images/defaultUserTitle.jpg','9B180D4E40654216B38F67D04F683C7D'),(31,'王武','1901064103','6c302f038bb996bafbea6752bde16450','内江隆昌','123@11',1,'12',7,'2022-02-24 21:22:47',1,1,'/images/defaultUserTitle.jpg','CE5ACADAA1294935A488B83BB610C57D'),(42,'小王','1801061121','aafee86a6156aa8111d73ed5dc7b8bcd','四川内江隆昌','1844246071@qq.com',1,'无',5,'2022-03-04 14:51:47',1,1,'/images/defaultUserTitle.jpg','D5B2D0516CC242E9AA798B2B28ED75E0'),(43,'小赵','1801061123','ae1990a658df4582462ac84c1964eb7f','四川泸州泸县','1844246071@qq.com',0,'无',6,'2022-03-04 14:51:47',1,1,'/images/defaultUserTitle.jpg','A071A5A42E9C448F98E1A4E78C0AA34C');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `uid` int(11) NOT NULL,
  `rid` int(11) NOT NULL,
  PRIMARY KEY (`uid`,`rid`) USING BTREE,
  KEY `FK_sys_user_role_1` (`rid`) USING BTREE,
  CONSTRAINT `FK_sys_user_role_1` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `FK_sys_user_role_2` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`uid`,`rid`) values (1,1),(2,2),(3,3),(21,3),(22,3),(23,3),(24,3),(25,3),(27,3),(28,3),(29,3),(31,3),(42,3),(43,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
