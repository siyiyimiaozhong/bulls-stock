DROP DATABASE IF EXISTS `bulls_stock`;

CREATE DATABASE `bulls_stock`
    CHARACTER SET 'utf8'
    COLLATE 'utf8_general_ci';

USE `bulls_stock`;

-- ----------------------------
-- Table structure for t_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `t_oauth_client_details`;
CREATE TABLE `t_oauth_client_details`  (
    `client_id` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
    `resource_ids` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `client_secret` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `scope` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `authorized_grant_types` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `web_server_redirect_uri` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `authorities` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `access_token_validity` int(11) NULL DEFAULT NULL,
    `refresh_token_validity` int(11) NULL DEFAULT NULL,
    `additional_information` varchar(4096) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `autoapprove` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_account_warn_notify_message
-- ----------------------------
DROP TABLE IF EXISTS `t_account_warn_notify_message`;
CREATE TABLE `t_account_warn_notify_message`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `account_id` bigint(20) NULL DEFAULT NULL COMMENT '账户ID',
  `receiver` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收者地址',
  `notify_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通知方式(APP:站内信，EMAIL：邮件，SMS：短信， 多个以逗号分隔)',
  `warn_type` int(11) NOT NULL COMMENT '预警类型 (0: 止损, 1:止盈)',
  `notify_content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通知内容',
  `send_status` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送状态（成功为200， 失败为错误码）',
  `retry_times` int(11) NULL DEFAULT NULL COMMENT '重试次数，默认重发3次',
  `memo` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注，如果失败， 记录异常信息',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `account_warn_notify_sendStatus`(`send_status`) USING BTREE,
  INDEX `account_warn_notify_retryTimes`(`retry_times`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户预警通知记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_account_warn_notify_message
-- ----------------------------

-- ----------------------------
-- Table structure for t_account_warn_setting
-- ----------------------------
DROP TABLE IF EXISTS `t_account_warn_setting`;
CREATE TABLE `t_account_warn_setting`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `account_id` bigint(20) NULL DEFAULT NULL COMMENT '账户ID',
  `stock_id` bigint(20) NULL DEFAULT NULL COMMENT '股票产品ID',
  `warn_type` int(11) NOT NULL COMMENT '预警类型 (0: 止损, 1:止盈)',
  `stop_rate` decimal(8, 2) NULL DEFAULT NULL COMMENT '止盈止损设置比例(单位：%， 例2.28 代表02.28%)',
  `notify_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通知方式(APP:站内信，EMAIL：邮件，SMS：短信， 多个以逗号分隔)',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `account_warn_setting_accountNo`(`account_id`) USING BTREE,
  INDEX `account_warn_setting_stockId`(`stock_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '账户预警设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_account_warn_setting
-- ----------------------------

-- ----------------------------
-- Table structure for t_authority_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_authority_menu`;
CREATE TABLE `t_authority_menu`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `parent_id` bigint(19) NOT NULL COMMENT '上级menu_id',
  `institution_type_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构类型id',
  `menu_code` varchar(160) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单编码',
  `menu_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `menu_uri` varchar(160) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单URI',
  `menu_level` tinyint(3) NOT NULL COMMENT '菜单等级',
  `operation_security` tinyint(3) NOT NULL COMMENT '是否需要操作授权（0不需要（默认） ，1需要）',
  `menu_seq` tinyint(3) NOT NULL COMMENT '位置排序',
  `menu_ico` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` tinyint(3) NOT NULL COMMENT '菜单状态(0无效,1有效)',
  `is_open` tinyint(3) NULL DEFAULT 0 COMMENT '只对2级菜单生效。0闭合，1打开',
  `is_show` tinyint(3) NULL DEFAULT NULL COMMENT '是否显示 (0--否  1--是)',
  `component_src` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件标识（前后分离使用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_authority_menu
-- ----------------------------

-- ----------------------------
-- Table structure for t_authority_menu_operation
-- ----------------------------
DROP TABLE IF EXISTS `t_authority_menu_operation`;
CREATE TABLE `t_authority_menu_operation`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `menu_id` bigint(19) NULL DEFAULT NULL COMMENT '自定义菜单id',
  `operation_name` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作名称',
  `operation_uri` varchar(160) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作URI地址',
  `operation_ico` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作图标',
  `status` tinyint(3) NOT NULL COMMENT '状态（0无效1有效）',
  `operation_resource_url` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限菜单url',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fkmenuId`(`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单操作表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_authority_menu_operation
-- ----------------------------

-- ----------------------------
-- Table structure for t_authority_no_limit_uri
-- ----------------------------
DROP TABLE IF EXISTS `t_authority_no_limit_uri`;
CREATE TABLE `t_authority_no_limit_uri`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `no_limit_uri` varchar(160) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '不限制的uri',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_noLimitUri`(`no_limit_uri`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '开放菜单表（无须权限认证）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_authority_no_limit_uri
-- ----------------------------

-- ----------------------------
-- Table structure for t_authority_role
-- ----------------------------
DROP TABLE IF EXISTS `t_authority_role`;
CREATE TABLE `t_authority_role`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `institution_type_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构类型id',
  `institution_id` bigint(19) NULL DEFAULT NULL COMMENT '对应机构类型下的机构id',
  `role_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `is_admin` tinyint(3) NULL DEFAULT 0 COMMENT '1：机构下的管理员角色，0: 普通角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_authority_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_authority_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_authority_role_menu`;
CREATE TABLE `t_authority_role_menu`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` bigint(19) NOT NULL COMMENT '自定义角色id',
  `menu_id` bigint(19) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_roleId_menuId`(`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_authority_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for t_authority_role_operation
-- ----------------------------
DROP TABLE IF EXISTS `t_authority_role_operation`;
CREATE TABLE `t_authority_role_operation`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` bigint(19) NULL DEFAULT NULL COMMENT '自定义角色id',
  `operation_id` bigint(19) NULL DEFAULT NULL COMMENT '自定义操作id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `fk_operation_role_id`(`operation_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色操作关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_authority_role_operation
-- ----------------------------

-- ----------------------------
-- Table structure for t_authority_user
-- ----------------------------
DROP TABLE IF EXISTS `t_authority_user`;
CREATE TABLE `t_authority_user`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '自定义用户id',
  `user_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户帐号（登录用）',
  `user_password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户姓名',
  `phone` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(24) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登陆IP',
  `create_time` datetime NOT NULL COMMENT '用户创建时间',
  `creator_user_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人的账号',
  `status` tinyint(3) NOT NULL COMMENT '状态（0无效1有效）',
  `is_admin` tinyint(3) NOT NULL DEFAULT 0 COMMENT '是否管理员（0否1是）',
  `remark` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_userAccount`(`user_account`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_authority_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_authority_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_authority_user_role`;
CREATE TABLE `t_authority_user_role`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '序列id',
  `user_id` bigint(19) NULL DEFAULT NULL COMMENT '用户id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  `role_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `institution_typeId` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色机构类型',
  `institution_id` bigint(19) NULL DEFAULT NULL COMMENT '角色对应机构类型下的机构id',
  `institution_name` varchar(46) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色对应的机构名称',
  `create_by` bigint(19) NULL DEFAULT NULL COMMENT '创建人id',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_by` bigint(19) NULL DEFAULT NULL COMMENT '最后跟新人id',
  `last_update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后更新人名称',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_authority_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_company
-- ----------------------------
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `company_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司名称\r\n            ',
  `institution_type_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构类型',
  `contact_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '联系人',
  `contact_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `admin_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员账号',
  `status` tinyint(3) NOT NULL COMMENT '状态(0:有效， 2：禁用）',
  `create_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `last_update_user` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后更新人名称',
  `last_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_accountNo2`(`contact_user`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '公司（交易商）表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_company
-- ----------------------------

-- ----------------------------
-- Table structure for t_institution
-- ----------------------------
DROP TABLE IF EXISTS `t_institution`;
CREATE TABLE `t_institution`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '机构id',
  `institution_type_id` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构类型id',
  `detail_institution_id` bigint(20) NULL DEFAULT NULL COMMENT '机构关联id',
  `detail_institution_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构关联名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_institution
-- ----------------------------

-- ----------------------------
-- Table structure for t_institution_type
-- ----------------------------
DROP TABLE IF EXISTS `t_institution_type`;
CREATE TABLE `t_institution_type`  (
  `institution_type_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '机构类型id',
  `institution_type_name` varchar(48) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构类型名称',
  `sort` tinyint(3) NULL DEFAULT NULL COMMENT '类型排序',
  PRIMARY KEY (`institution_type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构类型预置表（预置机构类型标识）' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_institution_type
-- ----------------------------

-- ----------------------------
-- Table structure for t_seq
-- ----------------------------
DROP TABLE IF EXISTS `t_seq`;
CREATE TABLE `t_seq`  (
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键标识',
  `next_id` bigint(20) NULL DEFAULT NULL COMMENT '下一个序列ID',
  PRIMARY KEY (`code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '序列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_seq
-- ----------------------------

-- ----------------------------
-- Table structure for t_trade_account
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_account`;
CREATE TABLE `t_trade_account`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `account_no` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交易账号',
  `balance` float NULL DEFAULT NULL COMMENT '余额',
  `trade_group_id` bigint(20) NOT NULL COMMENT '账户组ID',
  `active_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '开户时间',
  `status` smallint(6) NOT NULL COMMENT '状态(0:有效， 1：锁定， 2：禁用）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_accountNo`(`account_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户账号表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_trade_account
-- ----------------------------

-- ----------------------------
-- Table structure for t_trade_deal
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_deal`;
CREATE TABLE `t_trade_deal`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键， 唯一标识',
  `trade_order_id` bigint(20) NOT NULL COMMENT '交易订单号',
  `position_id` bigint(20) NOT NULL COMMENT '持仓ID',
  `account_id` bigint(20) NOT NULL COMMENT '交易账号ID',
  `stock_id` bigint(20) NOT NULL COMMENT '股票ID',
  `stock_market` smallint(6) NULL DEFAULT NULL COMMENT '股票市场(0: 上交所， 1：深交所，2：港股， 3：美股）',
  `exec_volume` int(11) NULL DEFAULT NULL COMMENT '成交数量',
  `exec_price` bigint(20) NULL DEFAULT NULL COMMENT '成交价格',
  `exec_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '成交时间',
  `direction` smallint(6) NOT NULL COMMENT '买卖方向',
  `profit` bigint(20) NULL DEFAULT NULL COMMENT '盈亏',
  `commission` bigint(20) NULL DEFAULT NULL COMMENT '佣金',
  `taxes` bigint(20) NULL DEFAULT NULL COMMENT '税费',
  `status` smallint(6) NOT NULL COMMENT '状态(0:有效，1：无效）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `trade_deal_idx_accountId`(`account_id`) USING BTREE,
  INDEX `trade_deal_idx_orderId`(`trade_order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '成交记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_trade_deal
-- ----------------------------

-- ----------------------------
-- Table structure for t_trade_global_config
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_global_config`;
CREATE TABLE `t_trade_global_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置项编号',
  `category` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类编号(BASIC:基础配置， BUSINESS： 业务配置,  SYSTEM：系统项配置)',
  `value` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置项的值',
  `status` smallint(6) NOT NULL COMMENT '状态(0:启用， 1：禁用）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_key`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统全局配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_trade_global_config
-- ----------------------------

-- ----------------------------
-- Table structure for t_trade_group
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_group`;
CREATE TABLE `t_trade_group`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `group_no` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户组编号',
  `group_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户组名称',
  `currency` float NULL DEFAULT NULL COMMENT '交易币种（CNY:人民币， HKD：港币，USD：美元）',
  `company_id` bigint(20) NOT NULL COMMENT '公司ID',
  `company_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公司名称',
  `commission_rate` float NULL DEFAULT NULL COMMENT '佣金比例， 单位：%',
  `taxes_rate` float NULL DEFAULT NULL COMMENT '税费比例， 单位：%',
  `level` smallint(6) NULL DEFAULT NULL COMMENT '账户等级（0：普通，1：VIP）',
  `status` smallint(6) NOT NULL COMMENT '状态(0:启用， 1：禁用）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_groupNo`(`group_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户账户组表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_trade_group
-- ----------------------------

-- ----------------------------
-- Table structure for t_trade_order
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_order`;
CREATE TABLE `t_trade_order`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `account_id` bigint(20) NOT NULL COMMENT '交易账号ID',
  `account_no` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交易账号',
  `stock_id` bigint(20) NOT NULL COMMENT '股票ID\r\n            ',
  `stock_code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '股票编号',
  `stock_name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '股票名称',
  `status` smallint(6) NOT NULL COMMENT '状态(0:待成交, 1:部分成交， 2：完全成交, 3:手工撤单， 4:系统撤单)',
  `type` smallint(6) NOT NULL COMMENT '挂单类型(0:市价单， 1:限价单)',
  `direction` smallint(6) NOT NULL COMMENT '买卖方向',
  `init_volume` int(11) NOT NULL COMMENT '挂单的数量',
  `exec_volume` int(11) NULL DEFAULT NULL COMMENT '成交数量',
  `request_price` bigint(20) NULL DEFAULT NULL COMMENT '请求价格',
  `exec_price` bigint(20) NULL DEFAULT NULL COMMENT '成交价格',
  `exec_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '成交时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `trade_order_idx_account_id`(`account_id`) USING BTREE,
  INDEX `trade_order_idx_stockid`(`stock_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_trade_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_trade_position
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_position`;
CREATE TABLE `t_trade_position`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键， 唯一标识',
  `account_id` bigint(20) NOT NULL COMMENT '交易账号ID',
  `stock_id` bigint(20) NOT NULL COMMENT '股票ID',
  `cost_price` bigint(20) NULL DEFAULT NULL COMMENT '成本价',
  `volume` int(11) NOT NULL COMMENT '持仓数量',
  `stock_market` smallint(6) NULL DEFAULT NULL COMMENT '股票市场(0: 上交所， 1：深交所，2：港股， 3：美股）',
  `avg_price` bigint(20) NOT NULL COMMENT '成交均价',
  `valid_sell_volume` int(11) NULL DEFAULT NULL COMMENT '可卖数量(T+1制度使用)',
  `profit` bigint(20) NULL DEFAULT NULL COMMENT '盈亏(默认两位小数）',
  `exec_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '成交时间',
  `status` smallint(6) NOT NULL COMMENT '状态(0:有效，1：无效）',
  `interest` bigint(20) NULL DEFAULT NULL COMMENT '持仓累计利息(默认两位小数)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `trade_position_idx_accountId`(`account_id`) USING BTREE,
  INDEX `trade_stock_idx_orderId`(`stock_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_trade_position
-- ----------------------------

-- ----------------------------
-- Table structure for t_trade_stock
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_stock`;
CREATE TABLE `t_trade_stock`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '股票编号',
  `category_id` bigint(20) NOT NULL COMMENT '股票分类ID',
  `market_no` smallint(6) NULL DEFAULT NULL COMMENT '股票市场(0: 上交所， 1：深交所，2：港股， 3：美股）',
  `currency` float NULL DEFAULT NULL COMMENT '交易币种（CNY:人民币， HKD：港币，USD：美元）',
  `high_rate` int(11) NULL DEFAULT NULL COMMENT '涨幅',
  `low_rate` int(11) NULL DEFAULT NULL COMMENT '跌幅',
  `unit` int(11) NOT NULL DEFAULT 100 COMMENT '股票单位（默认为：100）',
  `tag` smallint(6) NULL DEFAULT NULL COMMENT '标签(0:普通， 1：热门)',
  `status` smallint(6) NOT NULL COMMENT '状态(0:启用， 1：禁用）',
  `market_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行情来源(JH:聚合数据）',
  `display_order` int(11) NULL DEFAULT NULL COMMENT '显示顺序, 升序排列',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '股票信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_trade_stock
-- ----------------------------

-- ----------------------------
-- Table structure for t_trade_stock_category
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_stock_category`;
CREATE TABLE `t_trade_stock_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `code` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类编号',
  `start_mins` datetime NULL DEFAULT NULL COMMENT '股票交易开始时间',
  `end_mins` datetime NULL DEFAULT NULL COMMENT '股票交易结束时间',
  `commission_rate` bigint(19) NULL DEFAULT NULL COMMENT '佣金比例（支持四位小数）',
  `status` smallint(6) NOT NULL COMMENT '状态(0:启用， 1：禁用）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '股票分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_trade_stock_category
-- ----------------------------

-- ----------------------------
-- Table structure for t_trade_stock_kline
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_stock_kline`;
CREATE TABLE `t_trade_stock_kline`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `stock_id` bigint(20) NOT NULL COMMENT '产品ID',
  `stock_code` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品编号',
  `stock_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `price_open` bigint(20) NULL DEFAULT NULL COMMENT '开盘价',
  `price_high` bigint(20) NULL DEFAULT NULL COMMENT '最高价',
  `price_low` bigint(20) NULL DEFAULT NULL COMMENT '最低价',
  `price_close` bigint(20) NULL DEFAULT NULL COMMENT '收盘价',
  `volume` bigint(20) NULL DEFAULT 100 COMMENT '成交量',
  `amount` bigint(20) NULL DEFAULT NULL COMMENT '成交金额',
  `time` bigint(20) NULL DEFAULT NULL COMMENT '报价时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `trade_stock_kline_idx_stockId_time`(`stock_id`, `time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '股票K线表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_trade_stock_kline
-- ----------------------------

-- ----------------------------
-- Table structure for t_trade_stock_quote_last
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_stock_quote_last`;
CREATE TABLE `t_trade_stock_quote_last`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `stock_id` bigint(20) NOT NULL COMMENT '产品ID',
  `stock_code` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品编号',
  `stock_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `price_bid1` bigint(20) NULL DEFAULT NULL COMMENT '买方报价（一档）',
  `price_bid2` bigint(20) NULL DEFAULT NULL COMMENT '买方报价（二档）',
  `price_bid3` bigint(20) NULL DEFAULT NULL COMMENT '买方报价（三档）',
  `price_bid4` bigint(20) NULL DEFAULT NULL COMMENT '买方报价（四档）',
  `price_bid5` bigint(20) NULL DEFAULT NULL COMMENT '买方报价（五档）',
  `volume_bid1` bigint(20) NULL DEFAULT NULL COMMENT '买方数量（一档）',
  `volume_bid2` bigint(20) NULL DEFAULT NULL COMMENT '买方数量（二档）',
  `volume_bid3` bigint(20) NULL DEFAULT NULL COMMENT '买方数量（三档）',
  `volume_bid4` bigint(20) NULL DEFAULT NULL COMMENT '买方数量（四档）',
  `volume_bid5` bigint(20) NULL DEFAULT NULL COMMENT '买方数量（五档）',
  `price_ask1` bigint(20) NULL DEFAULT NULL COMMENT '卖方报价（一档）',
  `price_ask2` bigint(20) NULL DEFAULT NULL COMMENT '卖方报价（二档）',
  `price_ask3` bigint(20) NULL DEFAULT NULL COMMENT '卖方报价（三档）',
  `price_ask4` bigint(20) NULL DEFAULT NULL COMMENT '卖方报价（四档）',
  `price_ask5` bigint(20) NULL DEFAULT NULL COMMENT '卖方报价（五档）',
  `volume_ask1` bigint(20) NULL DEFAULT NULL COMMENT '卖方数量（一档）',
  `volume_ask2` bigint(20) NULL DEFAULT NULL COMMENT '卖方数量（二档）',
  `volume_ask3` bigint(20) NULL DEFAULT NULL COMMENT '卖方数量（三档）',
  `volume_ask4` bigint(20) NULL DEFAULT NULL COMMENT '卖方数量（四档）',
  `volume_ask5` bigint(20) NULL DEFAULT NULL COMMENT '卖方数量（五档）',
  `last_price` bigint(20) NULL DEFAULT NULL COMMENT '当前价格',
  `volume` bigint(20) NULL DEFAULT NULL COMMENT '当前成交量',
  `amount` bigint(20) NULL DEFAULT NULL COMMENT '当前成交金额',
  `time` bigint(20) NULL DEFAULT NULL COMMENT '报价时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `trade_stock_quote_idx_stockId`(`stock_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '股票分时行情报价表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_trade_stock_quote_last
-- ----------------------------

-- ----------------------------
-- Table structure for t_trade_stock_quote_summary
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_stock_quote_summary`;
CREATE TABLE `t_trade_stock_quote_summary`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `stock_id` bigint(20) NOT NULL COMMENT '产品ID',
  `stock_code` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品编号',
  `stock_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产品名称',
  `open_price` bigint(20) NULL DEFAULT NULL COMMENT '开盘价',
  `close_price` bigint(20) NULL DEFAULT NULL COMMENT '收盘价',
  `open_price_time` bigint(20) NULL DEFAULT NULL COMMENT '开盘时间',
  `close_price_time` bigint(20) NULL DEFAULT NULL COMMENT '收盘时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `trade_stock_quote_summary_idx_stockId`(`stock_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '股票报价概要表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_trade_stock_quote_summary
-- ----------------------------

-- ----------------------------
-- Table structure for t_trade_user
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_user`;
CREATE TABLE `t_trade_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `user_no` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号',
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `user_pwd` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `phone` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `company_id` bigint(20) NULL DEFAULT NULL COMMENT '公司ID',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `last_login_ip` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最近一次用户登陆IP',
  `last_login_time` datetime NULL DEFAULT NULL COMMENT '最近一次登陆时间',
  `status` smallint(6) NOT NULL COMMENT '状态(0:有效， 1：锁定， 2：禁用）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_userNo`(`user_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_trade_user
-- ----------------------------

-- ----------------------------
-- Table structure for t_trade_user_file
-- ----------------------------
DROP TABLE IF EXISTS `t_trade_user_file`;
CREATE TABLE `t_trade_user_file`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键标识',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `biz_type` smallint(6) NULL DEFAULT NULL COMMENT '业务类型（0：身份证， 1：银行卡， 2：信用卡）',
  `file_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件ID',
  `filename` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名称',
  `file_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件类型',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件路径',
  `status` smallint(6) NOT NULL COMMENT '状态(0:有效， 1：无效）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_userId`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户文件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_trade_user_file
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
