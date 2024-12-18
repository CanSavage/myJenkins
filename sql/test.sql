USE `pipeline`;

CREATE TABLE `workflows` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL COMMENT '工作流名称',
  `status` VARCHAR(20) DEFAULT '待审批' COMMENT '状态 (待审批、运行中、已完成)',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE `workflow_tasks` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT,
  `workflow_id` BIGINT NOT NULL COMMENT '关联的工作流ID',
  `name` VARCHAR(100) NOT NULL COMMENT '任务名称',
  `status` VARCHAR(20) DEFAULT '待执行' COMMENT '任务状态 (待执行、运行中、已完成)',
  `executor` VARCHAR(50) COMMENT '任务的执行者',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(90) DEFAULT NULL,
  `password` varchar(90) DEFAULT NULL,
  `email` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user` */
