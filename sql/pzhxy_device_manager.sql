/*
 Navicat Premium Data Transfer

 Source Server         : work
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : pzhxy_device_manager

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 12/03/2024 20:07:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_auth_employee_role
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_employee_role`;
CREATE TABLE `t_auth_employee_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_auth_employee_role
-- ----------------------------
INSERT INTO `t_auth_employee_role` VALUES (1, 1, 1);
INSERT INTO `t_auth_employee_role` VALUES (2, 2, 2);
INSERT INTO `t_auth_employee_role` VALUES (3, 3, 4);
INSERT INTO `t_auth_employee_role` VALUES (4, 7, 5);

-- ----------------------------
-- Table structure for t_auth_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_menu`;
CREATE TABLE `t_auth_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK24897F76799044`(`parent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_auth_menu
-- ----------------------------
INSERT INTO `t_auth_menu` VALUES (20, '权限模块', '/', 'fa fa-bar-chart', NULL);
INSERT INTO `t_auth_menu` VALUES (21, '权限管理', '/permission', '', 20);
INSERT INTO `t_auth_menu` VALUES (22, '角色管理', '/role', '', 20);
INSERT INTO `t_auth_menu` VALUES (23, '菜单管理', '/menu', '', 20);
INSERT INTO `t_auth_menu` VALUES (25, '组织机构管理', '/', 'fa fa-bar-chart', NULL);
INSERT INTO `t_auth_menu` VALUES (26, '员工管理', '/employee', '', 25);
INSERT INTO `t_auth_menu` VALUES (27, '部门管理', '/department', '', 25);
INSERT INTO `t_auth_menu` VALUES (28, '系统管理', '/', 'fa fa-bar-chart', NULL);
INSERT INTO `t_auth_menu` VALUES (50, '商家管理', '/seller', '', 25);
INSERT INTO `t_auth_menu` VALUES (51, '商家类型', '/sellerType', '', 25);
INSERT INTO `t_auth_menu` VALUES (52, '统计信息', '/', 'fa fa-bar-chart', NULL);
INSERT INTO `t_auth_menu` VALUES (53, '通知管理', '/', 'fa fa-bar-chart', NULL);
INSERT INTO `t_auth_menu` VALUES (54, '系统备份', '/backup', '', 28);
INSERT INTO `t_auth_menu` VALUES (55, '备份记录', '/backupRecord', '', 28);
INSERT INTO `t_auth_menu` VALUES (56, '备份操作日志', '/backupOperatorLog', '', 28);
INSERT INTO `t_auth_menu` VALUES (57, '设备信息统计', '/deviceShow', '', 52);
INSERT INTO `t_auth_menu` VALUES (58, '物资信息统计', '/goodsShow', '', 52);
INSERT INTO `t_auth_menu` VALUES (59, '通知信息', '/notice', '', 53);
INSERT INTO `t_auth_menu` VALUES (61, '待审核通知', '/checkingNotice', '', 53);
INSERT INTO `t_auth_menu` VALUES (62, '通知操作日志', '/noticeOperaterLog', '', 53);
INSERT INTO `t_auth_menu` VALUES (63, '系统接口文档', '/interfaceDoc', '', 28);
INSERT INTO `t_auth_menu` VALUES (64, '数据监控', '/dbMonitor', '', 28);
INSERT INTO `t_auth_menu` VALUES (65, '设备管理', '/', 'fa fa-bar-chart', NULL);
INSERT INTO `t_auth_menu` VALUES (66, '设备', '/device', '', 65);
INSERT INTO `t_auth_menu` VALUES (67, '可领用设备', '/canUseDevice', '', 65);
INSERT INTO `t_auth_menu` VALUES (68, '物资管理', '/', 'fa fa-bar-chart', NULL);
INSERT INTO `t_auth_menu` VALUES (69, '领用记录', '/', 'fa fa-bar-chart', NULL);
INSERT INTO `t_auth_menu` VALUES (70, '个人领用记录', '/useRecordByPerson', '', 69);
INSERT INTO `t_auth_menu` VALUES (71, '领用记录', '/useRecord', '', 69);
INSERT INTO `t_auth_menu` VALUES (72, '物资', '/goods', '', 68);
INSERT INTO `t_auth_menu` VALUES (73, '可领用物资', '/canUseGoods', '', 68);
INSERT INTO `t_auth_menu` VALUES (74, '采购入库', '/device/procure', '', 65);
INSERT INTO `t_auth_menu` VALUES (75, '设备类型', '/deviceType', '', 65);
INSERT INTO `t_auth_menu` VALUES (76, '设备操作日志', '/deviceOperaterLog', '', 65);
INSERT INTO `t_auth_menu` VALUES (77, '待审核设备', '/checkingDevice', '', 65);
INSERT INTO `t_auth_menu` VALUES (78, '采购入库', '/goods/procure', '', 68);
INSERT INTO `t_auth_menu` VALUES (79, '物资类型', '/goodsType', '', 68);
INSERT INTO `t_auth_menu` VALUES (80, '物资操作日志', '/goodsOperaterLog', '', 68);
INSERT INTO `t_auth_menu` VALUES (81, '待审核物资', '/checkingGoods', '', 68);

-- ----------------------------
-- Table structure for t_auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_permission`;
CREATE TABLE `t_auth_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `descs` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级权限的id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5701 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_auth_permission
-- ----------------------------
INSERT INTO `t_auth_permission` VALUES (5577, '部门管理', '/department', '部门管理权限', 'DepartmentController', NULL);
INSERT INTO `t_auth_permission` VALUES (5578, '根据关键字进行分页查询权限', '/department', '根据关键字进行分页查询权限', 'DepartmentController:queryDataByKeyword', 5577);
INSERT INTO `t_auth_permission` VALUES (5579, '新增或修改部门信息权限', '/department', '新增或修改部门信息权限', 'DepartmentController:addOrUpdate', 5577);
INSERT INTO `t_auth_permission` VALUES (5580, '删除部门信息权限', '/department/{id}', '删除部门信息权限', 'DepartmentController:deleteById', 5577);
INSERT INTO `t_auth_permission` VALUES (5581, '批量删除权限', '/department', '批量删除权限', 'DepartmentController:batchDelete', 5577);
INSERT INTO `t_auth_permission` VALUES (5582, '员工管理', '/employee', '员工管理权限', 'EmployeeController', NULL);
INSERT INTO `t_auth_permission` VALUES (5583, '根据关键字进行分页查询权限', '/employee', '根据关键字进行分页查询权限', 'EmployeeController:queryDataByKeyword', 5582);
INSERT INTO `t_auth_permission` VALUES (5584, '新增或修改员工信息权限', '/employee', '新增或修改员工信息权限', 'EmployeeController:addOrUpdate', 5582);
INSERT INTO `t_auth_permission` VALUES (5585, '删除员工信息权限', '/employee/{id}', '删除员工信息权限', 'EmployeeController:deleteById', 5582);
INSERT INTO `t_auth_permission` VALUES (5586, '批量删除员工信息权限', '/employee', '批量删除员工信息权限', 'EmployeeController:batchDelete', 5582);
INSERT INTO `t_auth_permission` VALUES (5587, '设置员工角色权限', '/employee/setEmployeeRole', '设置员工角色权限', 'EmployeeController:setEmployeeRole', 5582);
INSERT INTO `t_auth_permission` VALUES (5588, '菜单管理权限', '/menu', '菜单管理权限', 'MenuController', NULL);
INSERT INTO `t_auth_permission` VALUES (5589, '根据关键字进行分页查询权限', '/menu', '根据关键字进行分页查询权限', 'MenuController:queryDataByKeyword', 5588);
INSERT INTO `t_auth_permission` VALUES (5590, '新增或修改菜单信息权限', '/menu', '新增或修改菜单信息权限', 'MenuController:addOrUpdate', 5588);
INSERT INTO `t_auth_permission` VALUES (5591, '删除菜单信息权限', '/menu/{id}', '删除菜单信息权限', 'MenuController:deleteById', 5588);
INSERT INTO `t_auth_permission` VALUES (5592, '批量删除菜单信息权限', '/menu', '批量删除菜单信息权限', 'MenuController:batchDelete', 5588);
INSERT INTO `t_auth_permission` VALUES (5593, '权限管理权限', '/permission', '权限管理权限', 'PermissionController', NULL);
INSERT INTO `t_auth_permission` VALUES (5594, '根据关键字进行分页查询权限', '/permission/pageList', '根据关键字进行分页查询权限', 'PermissionController:pageList', 5593);
INSERT INTO `t_auth_permission` VALUES (5595, '角色管理权限', '/role', '角色管理权限', 'RoleController', NULL);
INSERT INTO `t_auth_permission` VALUES (5596, '设置权限', '/role/setPermission', '', 'RoleController:setPermission', 5595);
INSERT INTO `t_auth_permission` VALUES (5597, '根据关键字进行分页查询权限', '/role', '根据关键字进行分页查询权限', 'RoleController:queryDataByKeyword', 5595);
INSERT INTO `t_auth_permission` VALUES (5598, '获取权限树权限', '/role/getPermissionTree', '', 'RoleController:getPermissionTree', 5595);
INSERT INTO `t_auth_permission` VALUES (5599, '新增或修改角色信息权限', '/role', '新增或修改角色信息权限', 'RoleController:addOrUpdate', 5595);
INSERT INTO `t_auth_permission` VALUES (5600, '删除角色信息权限', '/role/{id}', '删除角色信息权限', 'RoleController:deleteById', 5595);
INSERT INTO `t_auth_permission` VALUES (5601, '批量删除角色信息权限', '/role', '批量删除角色信息权限', 'RoleController:batchDelete', 5595);
INSERT INTO `t_auth_permission` VALUES (5602, '设置菜单', '/role/setMenu', '', 'RoleController:setMenu', 5595);
INSERT INTO `t_auth_permission` VALUES (5603, '设备管理', '/device', '设备管理权限', 'DevicesController', NULL);
INSERT INTO `t_auth_permission` VALUES (5604, '根据关键字分页查询设备信息权限', '/device', '根据关键字分页查询设备信息权限', 'DevicesController:json', 5603);
INSERT INTO `t_auth_permission` VALUES (5605, '通过部门获取设备信息权限', '/device/byDept', '通过部门获取设备信息权限', 'DevicesController:jsonByDept', 5603);
INSERT INTO `t_auth_permission` VALUES (5606, '根据设备类型获取设备信息权限', '/device/devicesByTypeId/{id}', '根据设备类型获取设备信息权限', 'DevicesController:getByTypeId', 5603);
INSERT INTO `t_auth_permission` VALUES (5607, '设置设备可领用部门权限', '/device/setCanUseDept', '设置设备可领用部门权限', 'DevicesController:setDeviceUseDept', 5603);
INSERT INTO `t_auth_permission` VALUES (5608, '设备信息操作日志权限', '/devicesOperaterLog', '设备信息操作日志权限', 'DevicesOperaterLogController', NULL);
INSERT INTO `t_auth_permission` VALUES (5609, '设备日志关键字分页查询权限', '/devicesOperaterLog', '设备日志关键字分页查询权限', 'DevicesOperaterLogController:json', 5608);
INSERT INTO `t_auth_permission` VALUES (5610, '设备类型管理权限', '/deviceType', '设备类型管理权限', 'DevicesTypeController', NULL);
INSERT INTO `t_auth_permission` VALUES (5611, '设备类型删除权限', '/deviceType/{id}', '设备类型删除权限', 'DevicesTypeController:delete', 5610);
INSERT INTO `t_auth_permission` VALUES (5612, '设备类型关键字分页查询权限', '/deviceType', '设备类型关键字分页查询权限', 'DevicesTypeController:json', 5610);
INSERT INTO `t_auth_permission` VALUES (5613, '设备类型添加或修改权限', '/deviceType', '设备类型添加或修改权限', 'DevicesTypeController:addOrUpdate', 5610);
INSERT INTO `t_auth_permission` VALUES (5614, '设备类型批量删除权限', '/deviceType', '设备类型批量删除权限', 'DevicesTypeController:batchDelete', 5610);
INSERT INTO `t_auth_permission` VALUES (5615, '采购设备管理权限', '/nowarehousingdevices', '采购设备管理权限', 'WarehousingDevicesController', NULL);
INSERT INTO `t_auth_permission` VALUES (5616, '设备日志关键字分页查询权限', '/nowarehousingdevices/{id}', '设备日志关键字分页查询权限', 'WarehousingDevicesController:get', 5615);
INSERT INTO `t_auth_permission` VALUES (5617, '设备删除权限', '/nowarehousingdevices/{id}', '设备删除权限', 'WarehousingDevicesController:delete', 5615);
INSERT INTO `t_auth_permission` VALUES (5618, '待审核设备处理权限', '/nowarehousingdevices/handle', '待审核设备处理权限', 'WarehousingDevicesController:handle', 5615);
INSERT INTO `t_auth_permission` VALUES (5619, '采购设备关键字分页查询权限', '/nowarehousingdevices', '采购设备关键字分页查询权限', 'WarehousingDevicesController:json', 5615);
INSERT INTO `t_auth_permission` VALUES (5620, '采购设备新增或修改权限', '/nowarehousingdevices', '采购设备新增或修改权限', 'WarehousingDevicesController:addOrUpdate', 5615);
INSERT INTO `t_auth_permission` VALUES (5621, '设备批量删除权限', '/nowarehousingdevices', '设备批量删除权限', 'WarehousingDevicesController:batchDelete', 5615);
INSERT INTO `t_auth_permission` VALUES (5622, '设备入库权限', '/nowarehousingdevices/warehousing', '设备入库权限', 'WarehousingDevicesController:batchWarehousing', 5615);
INSERT INTO `t_auth_permission` VALUES (5623, '设备入库待审核信息查询权限', '/nowarehousingdevices/checking', '设备入库待审核信息查询权限', 'WarehousingDevicesController:jsonForChecking', 5615);
INSERT INTO `t_auth_permission` VALUES (5624, '物资管理权限', '/goods', '物资管理权限', 'GoodsController', NULL);
INSERT INTO `t_auth_permission` VALUES (5625, '物资信息关键字分页查询权限', '/goods', '物资信息关键字分页查询权限', 'GoodsController:json', 5624);
INSERT INTO `t_auth_permission` VALUES (5626, '通过部门获取物资信息权限', '/goods/byDept', '通过部门获取物资信息权限', 'GoodsController:jsonByDept', 5624);
INSERT INTO `t_auth_permission` VALUES (5627, '物资信息根据类型获取权限', '/goods/goodsByTypeId/{id}', '物资信息根据获取权限', 'GoodsController:getByTypeId', 5624);
INSERT INTO `t_auth_permission` VALUES (5628, '设置物资可领用部门权限', '/goods/setCanUseDept', '设置物资可领用部门权限', 'GoodsController:setGoodsUseDept', 5624);
INSERT INTO `t_auth_permission` VALUES (5629, '物资操作日志管理权限', '/goodsOperaterLog', '物资操作日志管理权限', 'GoodsOperaterLogController', NULL);
INSERT INTO `t_auth_permission` VALUES (5630, '物资操作日志关键字分页查询权限', '/goodsOperaterLog', '物资操作日志关键字分页查询权限', 'GoodsOperaterLogController:json', 5629);
INSERT INTO `t_auth_permission` VALUES (5631, '物资类型管理权限', '/goodsType', '物资类型管理权限', 'GoodsTypeController', NULL);
INSERT INTO `t_auth_permission` VALUES (5632, '物资类型删除权限', '/goodsType/{id}', '物资类型删除权限', 'GoodsTypeController:delete', 5631);
INSERT INTO `t_auth_permission` VALUES (5633, '物资类型关键字分页获取权限', '/goodsType', '物资类型关键字分页获取权限', 'GoodsTypeController:json', 5631);
INSERT INTO `t_auth_permission` VALUES (5634, '物资类型新增或修改权限', '/goodsType', '物资类型新增或修改权限', 'GoodsTypeController:addOrUpdate', 5631);
INSERT INTO `t_auth_permission` VALUES (5635, '物资类型批量删除权限', '/goodsType', '物资类型批量删除权限', 'GoodsTypeController:batchDelete', 5631);
INSERT INTO `t_auth_permission` VALUES (5636, '采购物资管理权限', '/nowarehousinggoods', '采购物资管理权限', 'WarehousingGoodsController', NULL);
INSERT INTO `t_auth_permission` VALUES (5637, '采购物资删除权限', '/nowarehousinggoods/{id}', '采购物资删除权限', 'WarehousingGoodsController:delete', 5636);
INSERT INTO `t_auth_permission` VALUES (5638, '待审核物资处理权限', '/nowarehousinggoods/handle', '待审核物资处理权限', 'WarehousingGoodsController:handle', 5636);
INSERT INTO `t_auth_permission` VALUES (5639, '采购物资关键字分页查询权限', '/nowarehousinggoods', '采购物资关键字分页查询权限', 'WarehousingGoodsController:json', 5636);
INSERT INTO `t_auth_permission` VALUES (5640, '采购物资新增或修改权限', '/nowarehousinggoods', '采购物资新增或修改权限', 'WarehousingGoodsController:addOrUpdate', 5636);
INSERT INTO `t_auth_permission` VALUES (5641, '采购物资批量删除权限', '/nowarehousinggoods', '采购物资批量删除权限', 'WarehousingGoodsController:batchDelete', 5636);
INSERT INTO `t_auth_permission` VALUES (5642, '采购物资入库权限', '/nowarehousinggoods/warehousing', '采购物资入库权限', 'WarehousingGoodsController:batchWarehousing', 5636);
INSERT INTO `t_auth_permission` VALUES (5643, '待审核物资查询权限', '/nowarehousinggoods/checking', '待审核物资查询权限', 'WarehousingGoodsController:jsonForChecking', 5636);
INSERT INTO `t_auth_permission` VALUES (5644, '通知管理权限', '/notice', '通知管理权限', 'NoticeController', NULL);
INSERT INTO `t_auth_permission` VALUES (5645, '通知删除权限', '/notice/{id}', '通知删除权限', 'NoticeController:delete', 5644);
INSERT INTO `t_auth_permission` VALUES (5646, '通知待审核处理权限', '/notice/handle', '通知待审核处理权限', 'NoticeController:handle', 5644);
INSERT INTO `t_auth_permission` VALUES (5647, '通知关键字分页查询权限', '/notice', '通知关键字分页查询权限', 'NoticeController:json', 5644);
INSERT INTO `t_auth_permission` VALUES (5648, '获取通知可见的角色信息权限', '/notice/noticeRolesByNoticeId/{id}', '获取通知可见的角色信息权限', 'NoticeController:noticeRolesByNoticeId', 5644);
INSERT INTO `t_auth_permission` VALUES (5649, '通知新增或修改权限', '/notice', '通知新增或修改权限', 'NoticeController:addOrUpdate', 5644);
INSERT INTO `t_auth_permission` VALUES (5650, '通知批量删除权限', '/notice', '通知批量删除权限', 'NoticeController:batchDelete', 5644);
INSERT INTO `t_auth_permission` VALUES (5651, '设置通知可见的角色权限', '/notice/setNoticeRole', '设置通知可见的角色权限', 'NoticeController:setNoticeRole', 5644);
INSERT INTO `t_auth_permission` VALUES (5652, '通知待审核查询权限', '/notice/checking', '通知待审核查询权限', 'NoticeController:jsonForChecking', 5644);
INSERT INTO `t_auth_permission` VALUES (5653, '通知操作日志管理权限', '/noticeOperaterLog', '通知操作日志管理权限', 'NoticeOperaterLogController', NULL);
INSERT INTO `t_auth_permission` VALUES (5654, '通知操作日志关键字分页查询权限', '/noticeOperaterLog', '通知操作日志关键字分页查询权限', 'NoticeOperaterLogController:json', 5653);
INSERT INTO `t_auth_permission` VALUES (5655, '系统备份管理权限', '/backup', '系统备份管理权限', 'BackupController', NULL);
INSERT INTO `t_auth_permission` VALUES (5656, '删除备份权限', '/backup/{id}', '删除备份权限', 'BackupController:delete', 5655);
INSERT INTO `t_auth_permission` VALUES (5657, '备份关键字分页查询权限', '/backup', '备份关键字分页查询权限', 'BackupController:json', 5655);
INSERT INTO `t_auth_permission` VALUES (5658, '恢复备份权限', '/backup/restore/{id}', '恢复备份权限', 'BackupController:restore', 5655);
INSERT INTO `t_auth_permission` VALUES (5659, '手动备份权限', '/backup/manual', '手动备份权限', 'BackupController:back', 5655);
INSERT INTO `t_auth_permission` VALUES (5660, '备份操作日志管理权限', '/backupOperaterLog', '备份操作日志管理权限', 'BackupOperatorLogController', NULL);
INSERT INTO `t_auth_permission` VALUES (5661, '备份操作日志关键字分页查询权限', '/backupOperaterLog', '备份操作日志关键字分页查询权限', 'BackupOperatorLogController:json', 5660);
INSERT INTO `t_auth_permission` VALUES (5662, '备份定时任务管理权限', '/task', '备份定时任务管理权限', 'SysScheduledCronController', NULL);
INSERT INTO `t_auth_permission` VALUES (5663, '删除定时任务权限', '/task/{id}', '删除定时任务权限', 'SysScheduledCronController:delete', 5662);
INSERT INTO `t_auth_permission` VALUES (5664, '定时任务列表查看权限', '/task', '定时任务列表查看权限', 'SysScheduledCronController:taskList', 5662);
INSERT INTO `t_auth_permission` VALUES (5665, '更新定时任务权限', '/task', '更新定时任务权限', 'SysScheduledCronController:updateTask', 5662);
INSERT INTO `t_auth_permission` VALUES (5666, '启用或禁用定时任务权限', '/task', '启用或禁用定时任务权限', 'SysScheduledCronController:changeStatusTaskCron', 5662);
INSERT INTO `t_auth_permission` VALUES (5667, '添加定时任务权限', '/task', '添加定时任务权限', 'SysScheduledCronController:changeStatusTaskCron', 5662);
INSERT INTO `t_auth_permission` VALUES (5668, '报废记录管理权限', '/disable', '报废记录管理权限', 'DisableController', NULL);
INSERT INTO `t_auth_permission` VALUES (5669, '报废记录关键字分页查询权限', '/disable', '报废记录关键字分页查询权限', 'DisableController:json', 5668);
INSERT INTO `t_auth_permission` VALUES (5670, '报废记录新增或修改权限', '/disable', '报废记录新增或修改权限', 'DisableController:addOrUpdate', 5668);
INSERT INTO `t_auth_permission` VALUES (5671, '维修记录管理权限', '/maintain', '维修记录管理权限', 'MaintainController', NULL);
INSERT INTO `t_auth_permission` VALUES (5672, '维修记录关键字分页查询权限', '/maintain', '维修记录关键字分页查询权限', 'MaintainController:json', 5671);
INSERT INTO `t_auth_permission` VALUES (5673, '维修记录新增或修改权限', '/maintain', '维修记录新增或修改权限', 'MaintainController:addOrUpdate', 5671);
INSERT INTO `t_auth_permission` VALUES (5674, '物资设备领用管理权限', '/useRecord', '物资设备领用管理权限', 'UseRecordController', NULL);
INSERT INTO `t_auth_permission` VALUES (5675, '物资设备所有领用记录关键词分页查询权限', '/useRecord', '物资设备所有领用记录关键词分页查询权限', 'UseRecordController:json', 5674);
INSERT INTO `t_auth_permission` VALUES (5676, '物资领用权限', '/useRecord/goods', '物资领用权限', 'UseRecordController:addOrUpdateGoods', 5674);
INSERT INTO `t_auth_permission` VALUES (5677, '物资设备个人领用记录查询权限', '/useRecord/operatorId', '物资设备个人领用记录查询权限', 'UseRecordController:listForOperator', 5674);
INSERT INTO `t_auth_permission` VALUES (5678, '设备领用权限', '/useRecord/device', '设备领用权限', 'UseRecordController:addOrUpdateDevice', 5674);
INSERT INTO `t_auth_permission` VALUES (5679, '商家管理权限', '/seller', '商家管理权限', 'SellersController', NULL);
INSERT INTO `t_auth_permission` VALUES (5680, '商家删除权限', '/seller/{id}', '商家删除权限', 'SellersController:delete', 5679);
INSERT INTO `t_auth_permission` VALUES (5681, '商家关键词分页查询权限', '/seller', '商家关键词分页查询权限', 'SellersController:json', 5679);
INSERT INTO `t_auth_permission` VALUES (5682, '商家新增或修改权限', '/seller', '商家新增或修改权限', 'SellersController:addOrUpdate', 5679);
INSERT INTO `t_auth_permission` VALUES (5683, '商家批量删除权限', '/seller', '商家批量删除权限', 'SellersController:batchDelete', 5679);
INSERT INTO `t_auth_permission` VALUES (5684, '商家类型管理权限', '/sellerType', '商家类型管理权限', 'SellersTypeController', NULL);
INSERT INTO `t_auth_permission` VALUES (5685, '商家类型删除权限', '/sellerType/{id}', '商家类型删除权限', 'SellersTypeController:delete', 5684);
INSERT INTO `t_auth_permission` VALUES (5686, '商家类型关键字分页查询权限', '/sellerType', '商家类型关键字分页查询权限', 'SellersTypeController:json', 5684);
INSERT INTO `t_auth_permission` VALUES (5687, '商家类型新增或修改权限', '/sellerType', '商家类型新增或修改权限', 'SellersTypeController:addOrUpdate', 5684);
INSERT INTO `t_auth_permission` VALUES (5688, '商家类型批量删除权限', '/sellerType', '商家类型批量删除权限', 'SellersTypeController:batchDelete', 5684);
INSERT INTO `t_auth_permission` VALUES (5689, '设备统计信息查看权限', '/deviceShow', '设备统计信息查看权限', 'DeviceInfoShowController', NULL);
INSERT INTO `t_auth_permission` VALUES (5690, '设备数量总统计信息查看权限', '/deviceShow/totalDataInfo', '设备数量总统计信息查看权限', 'DeviceInfoShowController:totalDataInfo', 5689);
INSERT INTO `t_auth_permission` VALUES (5691, '近期设备信息查看权限', '/deviceShow/device', '近期设备信息查看权限', 'DeviceInfoShowController:deviceInfo', 5689);
INSERT INTO `t_auth_permission` VALUES (5692, '设备各状态数量查看权限', '/deviceShow/countsWithDevice', '设备各状态数量查看权限', 'DeviceInfoShowController:countsWithDevice', 5689);
INSERT INTO `t_auth_permission` VALUES (5693, '设备价格趋势查看权限', '/deviceShow/devicePriceChange/{deviceId}', '设备价格趋势查看权限', 'DeviceInfoShowController:devicePriceChange', 5689);
INSERT INTO `t_auth_permission` VALUES (5694, '设备类型统计查看权限', '/deviceShow/countsWithType', '设备类型统计查看权限', 'DeviceInfoShowController:getDeviceCountsWithTypeName', 5689);
INSERT INTO `t_auth_permission` VALUES (5695, '物资统计信息查看权限', '/goodsShow', '物资统计信息查看权限', 'GoodsInfoShowController', NULL);
INSERT INTO `t_auth_permission` VALUES (5696, '物资总统计信息查看权限', '/goodsShow/totalDataInfo', '物资总统计信息查看权限', 'GoodsInfoShowController:totalDataInfo', 5695);
INSERT INTO `t_auth_permission` VALUES (5697, '物资价格变化趋势查看权限', '/goodsShow/goodsPriceChange/{goodsId}', '物资价格变化趋势查看权限', 'GoodsInfoShowController:goodsPriceChange', 5695);
INSERT INTO `t_auth_permission` VALUES (5698, '物资使用情况查看权限', '/goodsShow/countsWithGoods', '物资使用情况查看权限', 'GoodsInfoShowController:countsWithGoods', 5695);
INSERT INTO `t_auth_permission` VALUES (5699, '近期物资信息查看权限', '/goodsShow/goods', '近期物资信息查看权限', 'GoodsInfoShowController:goodsInfo', 5695);
INSERT INTO `t_auth_permission` VALUES (5700, '物资类型统计信息查看权限', '/goodsShow/countsWithType', '物资类型统计信息查看权限', 'GoodsInfoShowController:getGoodsCountsWithTypeName', 5695);

-- ----------------------------
-- Table structure for t_auth_role
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_role`;
CREATE TABLE `t_auth_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_auth_role
-- ----------------------------
INSERT INTO `t_auth_role` VALUES (1, '管理员', 'roleAdmin');
INSERT INTO `t_auth_role` VALUES (2, '超级管理员', 'guest');
INSERT INTO `t_auth_role` VALUES (5, '一般用户', '一般用户');
INSERT INTO `t_auth_role` VALUES (6, '6', '6');
INSERT INTO `t_auth_role` VALUES (9, '角色管理员', 'adminxxxxx');
INSERT INTO `t_auth_role` VALUES (13, '1', '1');
INSERT INTO `t_auth_role` VALUES (14, '2', '2');
INSERT INTO `t_auth_role` VALUES (15, '3', '3');

-- ----------------------------
-- Table structure for t_auth_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_role_menu`;
CREATE TABLE `t_auth_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL,
  `menu_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `menu_id`(`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 675 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_auth_role_menu
-- ----------------------------
INSERT INTO `t_auth_role_menu` VALUES (5, 2, 20);
INSERT INTO `t_auth_role_menu` VALUES (6, 2, 21);
INSERT INTO `t_auth_role_menu` VALUES (8, 2, 24);
INSERT INTO `t_auth_role_menu` VALUES (9, 5, 32);
INSERT INTO `t_auth_role_menu` VALUES (10, 5, 33);
INSERT INTO `t_auth_role_menu` VALUES (11, 5, 34);
INSERT INTO `t_auth_role_menu` VALUES (636, 1, 20);
INSERT INTO `t_auth_role_menu` VALUES (637, 1, 21);
INSERT INTO `t_auth_role_menu` VALUES (638, 1, 22);
INSERT INTO `t_auth_role_menu` VALUES (639, 1, 23);
INSERT INTO `t_auth_role_menu` VALUES (640, 1, 25);
INSERT INTO `t_auth_role_menu` VALUES (641, 1, 26);
INSERT INTO `t_auth_role_menu` VALUES (642, 1, 27);
INSERT INTO `t_auth_role_menu` VALUES (643, 1, 50);
INSERT INTO `t_auth_role_menu` VALUES (644, 1, 51);
INSERT INTO `t_auth_role_menu` VALUES (645, 1, 28);
INSERT INTO `t_auth_role_menu` VALUES (646, 1, 54);
INSERT INTO `t_auth_role_menu` VALUES (647, 1, 55);
INSERT INTO `t_auth_role_menu` VALUES (648, 1, 56);
INSERT INTO `t_auth_role_menu` VALUES (649, 1, 63);
INSERT INTO `t_auth_role_menu` VALUES (650, 1, 64);
INSERT INTO `t_auth_role_menu` VALUES (651, 1, 52);
INSERT INTO `t_auth_role_menu` VALUES (652, 1, 57);
INSERT INTO `t_auth_role_menu` VALUES (653, 1, 58);
INSERT INTO `t_auth_role_menu` VALUES (654, 1, 53);
INSERT INTO `t_auth_role_menu` VALUES (655, 1, 59);
INSERT INTO `t_auth_role_menu` VALUES (656, 1, 61);
INSERT INTO `t_auth_role_menu` VALUES (657, 1, 62);
INSERT INTO `t_auth_role_menu` VALUES (658, 1, 65);
INSERT INTO `t_auth_role_menu` VALUES (659, 1, 66);
INSERT INTO `t_auth_role_menu` VALUES (660, 1, 67);
INSERT INTO `t_auth_role_menu` VALUES (661, 1, 74);
INSERT INTO `t_auth_role_menu` VALUES (662, 1, 75);
INSERT INTO `t_auth_role_menu` VALUES (663, 1, 76);
INSERT INTO `t_auth_role_menu` VALUES (664, 1, 77);
INSERT INTO `t_auth_role_menu` VALUES (665, 1, 68);
INSERT INTO `t_auth_role_menu` VALUES (666, 1, 72);
INSERT INTO `t_auth_role_menu` VALUES (667, 1, 73);
INSERT INTO `t_auth_role_menu` VALUES (668, 1, 78);
INSERT INTO `t_auth_role_menu` VALUES (669, 1, 79);
INSERT INTO `t_auth_role_menu` VALUES (670, 1, 80);
INSERT INTO `t_auth_role_menu` VALUES (671, 1, 81);
INSERT INTO `t_auth_role_menu` VALUES (672, 1, 69);
INSERT INTO `t_auth_role_menu` VALUES (673, 1, 70);
INSERT INTO `t_auth_role_menu` VALUES (674, 1, 71);

-- ----------------------------
-- Table structure for t_auth_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_role_permission`;
CREATE TABLE `t_auth_role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NULL DEFAULT NULL,
  `permission_sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限的唯一标识',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKAEE599B74D26E00F`(`role_id`) USING BTREE,
  INDEX `FKAEE599B7C854068F`(`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 651 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_auth_role_permission
-- ----------------------------
INSERT INTO `t_auth_role_permission` VALUES (526, 1, NULL, 'DepartmentController');
INSERT INTO `t_auth_role_permission` VALUES (527, 1, NULL, 'DepartmentController:queryDataByKeyword');
INSERT INTO `t_auth_role_permission` VALUES (528, 1, NULL, 'DepartmentController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (529, 1, NULL, 'DepartmentController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (530, 1, NULL, 'DepartmentController:deleteById');
INSERT INTO `t_auth_role_permission` VALUES (531, 1, NULL, 'EmployeeController');
INSERT INTO `t_auth_role_permission` VALUES (532, 1, NULL, 'EmployeeController:queryDataByKeyword');
INSERT INTO `t_auth_role_permission` VALUES (533, 1, NULL, 'EmployeeController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (534, 1, NULL, 'EmployeeController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (535, 1, NULL, 'EmployeeController:deleteById');
INSERT INTO `t_auth_role_permission` VALUES (536, 1, NULL, 'EmployeeController:setEmployeeRole');
INSERT INTO `t_auth_role_permission` VALUES (537, 1, NULL, 'MenuController');
INSERT INTO `t_auth_role_permission` VALUES (538, 1, NULL, 'MenuController:queryDataByKeyword');
INSERT INTO `t_auth_role_permission` VALUES (539, 1, NULL, 'MenuController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (540, 1, NULL, 'MenuController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (541, 1, NULL, 'MenuController:deleteById');
INSERT INTO `t_auth_role_permission` VALUES (542, 1, NULL, 'PermissionController');
INSERT INTO `t_auth_role_permission` VALUES (543, 1, NULL, 'PermissionController:pageList');
INSERT INTO `t_auth_role_permission` VALUES (544, 1, NULL, 'RoleController');
INSERT INTO `t_auth_role_permission` VALUES (545, 1, NULL, 'RoleController:setPermission');
INSERT INTO `t_auth_role_permission` VALUES (546, 1, NULL, 'RoleController:getPermissionTree');
INSERT INTO `t_auth_role_permission` VALUES (547, 1, NULL, 'RoleController:queryDataByKeyword');
INSERT INTO `t_auth_role_permission` VALUES (548, 1, NULL, 'RoleController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (549, 1, NULL, 'RoleController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (550, 1, NULL, 'RoleController:deleteById');
INSERT INTO `t_auth_role_permission` VALUES (551, 1, NULL, 'RoleController:setMenu');
INSERT INTO `t_auth_role_permission` VALUES (552, 1, NULL, 'DevicesController');
INSERT INTO `t_auth_role_permission` VALUES (553, 1, NULL, 'DevicesController:json');
INSERT INTO `t_auth_role_permission` VALUES (554, 1, NULL, 'DevicesController:getByTypeId');
INSERT INTO `t_auth_role_permission` VALUES (555, 1, NULL, 'DevicesOperaterLogController');
INSERT INTO `t_auth_role_permission` VALUES (556, 1, NULL, 'DevicesOperaterLogController:json');
INSERT INTO `t_auth_role_permission` VALUES (557, 1, NULL, 'DevicesTypeController');
INSERT INTO `t_auth_role_permission` VALUES (558, 1, NULL, 'DevicesTypeController:delete');
INSERT INTO `t_auth_role_permission` VALUES (559, 1, NULL, 'DevicesTypeController:json');
INSERT INTO `t_auth_role_permission` VALUES (560, 1, NULL, 'DevicesTypeController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (561, 1, NULL, 'DevicesTypeController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (562, 1, NULL, 'WarehousingDevicesController');
INSERT INTO `t_auth_role_permission` VALUES (563, 1, NULL, 'WarehousingDevicesController:get');
INSERT INTO `t_auth_role_permission` VALUES (564, 1, NULL, 'WarehousingDevicesController:delete');
INSERT INTO `t_auth_role_permission` VALUES (565, 1, NULL, 'WarehousingDevicesController:handle');
INSERT INTO `t_auth_role_permission` VALUES (566, 1, NULL, 'WarehousingDevicesController:json');
INSERT INTO `t_auth_role_permission` VALUES (567, 1, NULL, 'WarehousingDevicesController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (568, 1, NULL, 'WarehousingDevicesController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (569, 1, NULL, 'WarehousingDevicesController:batchWarehousing');
INSERT INTO `t_auth_role_permission` VALUES (570, 1, NULL, 'WarehousingDevicesController:jsonForChecking');
INSERT INTO `t_auth_role_permission` VALUES (571, 1, NULL, 'GoodsController');
INSERT INTO `t_auth_role_permission` VALUES (572, 1, NULL, 'GoodsController:json');
INSERT INTO `t_auth_role_permission` VALUES (573, 1, NULL, 'GoodsController:getByTypeId');
INSERT INTO `t_auth_role_permission` VALUES (574, 1, NULL, 'GoodsOperaterLogController');
INSERT INTO `t_auth_role_permission` VALUES (575, 1, NULL, 'GoodsOperaterLogController:json');
INSERT INTO `t_auth_role_permission` VALUES (576, 1, NULL, 'GoodsTypeController');
INSERT INTO `t_auth_role_permission` VALUES (577, 1, NULL, 'GoodsTypeController:delete');
INSERT INTO `t_auth_role_permission` VALUES (578, 1, NULL, 'GoodsTypeController:json');
INSERT INTO `t_auth_role_permission` VALUES (579, 1, NULL, 'GoodsTypeController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (580, 1, NULL, 'GoodsTypeController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (581, 1, NULL, 'UseRecordController');
INSERT INTO `t_auth_role_permission` VALUES (582, 1, NULL, 'UseRecordController:json');
INSERT INTO `t_auth_role_permission` VALUES (583, 1, NULL, 'UseRecordController:addOrUpdateGoods');
INSERT INTO `t_auth_role_permission` VALUES (584, 1, NULL, 'UseRecordController:listForOperator');
INSERT INTO `t_auth_role_permission` VALUES (585, 1, NULL, 'UseRecordController:addOrUpdateDevice');
INSERT INTO `t_auth_role_permission` VALUES (586, 1, NULL, 'WarehousingGoodsController');
INSERT INTO `t_auth_role_permission` VALUES (587, 1, NULL, 'WarehousingGoodsController:delete');
INSERT INTO `t_auth_role_permission` VALUES (588, 1, NULL, 'WarehousingGoodsController:handle');
INSERT INTO `t_auth_role_permission` VALUES (589, 1, NULL, 'WarehousingGoodsController:json');
INSERT INTO `t_auth_role_permission` VALUES (590, 1, NULL, 'WarehousingGoodsController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (591, 1, NULL, 'WarehousingGoodsController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (592, 1, NULL, 'WarehousingGoodsController:batchWarehousing');
INSERT INTO `t_auth_role_permission` VALUES (593, 1, NULL, 'WarehousingGoodsController:jsonForChecking');
INSERT INTO `t_auth_role_permission` VALUES (594, 1, NULL, 'NoticeController');
INSERT INTO `t_auth_role_permission` VALUES (595, 1, NULL, 'NoticeController:delete');
INSERT INTO `t_auth_role_permission` VALUES (596, 1, NULL, 'NoticeController:handle');
INSERT INTO `t_auth_role_permission` VALUES (597, 1, NULL, 'NoticeController:json');
INSERT INTO `t_auth_role_permission` VALUES (598, 1, NULL, 'NoticeController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (599, 1, NULL, 'NoticeController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (600, 1, NULL, 'NoticeController:jsonForChecking');
INSERT INTO `t_auth_role_permission` VALUES (601, 1, NULL, 'NoticeController:setNoticeRole');
INSERT INTO `t_auth_role_permission` VALUES (602, 1, NULL, 'NoticeController:noticeByPerson');
INSERT INTO `t_auth_role_permission` VALUES (603, 1, NULL, 'NoticeController:noticeRolesByNoticeId');
INSERT INTO `t_auth_role_permission` VALUES (604, 1, NULL, 'NoticeOperaterLogController');
INSERT INTO `t_auth_role_permission` VALUES (605, 1, NULL, 'NoticeOperaterLogController:json');
INSERT INTO `t_auth_role_permission` VALUES (606, 1, NULL, 'BackupController');
INSERT INTO `t_auth_role_permission` VALUES (607, 1, NULL, 'BackupController:delete');
INSERT INTO `t_auth_role_permission` VALUES (608, 1, NULL, 'BackupController:json');
INSERT INTO `t_auth_role_permission` VALUES (609, 1, NULL, 'BackupController:restore');
INSERT INTO `t_auth_role_permission` VALUES (610, 1, NULL, 'BackupController:back');
INSERT INTO `t_auth_role_permission` VALUES (611, 1, NULL, 'BackupOperatorLogController');
INSERT INTO `t_auth_role_permission` VALUES (612, 1, NULL, 'BackupOperatorLogController:json');
INSERT INTO `t_auth_role_permission` VALUES (613, 1, NULL, 'SysScheduledCronController');
INSERT INTO `t_auth_role_permission` VALUES (614, 1, NULL, 'SysScheduledCronController:delete');
INSERT INTO `t_auth_role_permission` VALUES (615, 1, NULL, 'SysScheduledCronController:taskList');
INSERT INTO `t_auth_role_permission` VALUES (616, 1, NULL, 'SysScheduledCronController:updateTask');
INSERT INTO `t_auth_role_permission` VALUES (617, 1, NULL, 'SysScheduledCronController:changeStatusTaskCron');
INSERT INTO `t_auth_role_permission` VALUES (618, 1, NULL, 'SysScheduledCronController:changeStatusTaskCron');
INSERT INTO `t_auth_role_permission` VALUES (619, 1, NULL, 'DisableController');
INSERT INTO `t_auth_role_permission` VALUES (620, 1, NULL, 'DisableController:json');
INSERT INTO `t_auth_role_permission` VALUES (621, 1, NULL, 'DisableController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (622, 1, NULL, 'MaintainController');
INSERT INTO `t_auth_role_permission` VALUES (623, 1, NULL, 'MaintainController:json');
INSERT INTO `t_auth_role_permission` VALUES (624, 1, NULL, 'MaintainController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (625, 1, NULL, 'SellersController');
INSERT INTO `t_auth_role_permission` VALUES (626, 1, NULL, 'SellersController:delete');
INSERT INTO `t_auth_role_permission` VALUES (627, 1, NULL, 'SellersController:json');
INSERT INTO `t_auth_role_permission` VALUES (628, 1, NULL, 'SellersController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (629, 1, NULL, 'SellersController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (630, 1, NULL, 'SellersTypeController');
INSERT INTO `t_auth_role_permission` VALUES (631, 1, NULL, 'SellersTypeController:delete');
INSERT INTO `t_auth_role_permission` VALUES (632, 1, NULL, 'SellersTypeController:json');
INSERT INTO `t_auth_role_permission` VALUES (633, 1, NULL, 'SellersTypeController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (634, 1, NULL, 'SellersTypeController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (635, 1, NULL, 'DeviceInfoShowController');
INSERT INTO `t_auth_role_permission` VALUES (636, 1, NULL, 'DeviceInfoShowController:countsWithDevice');
INSERT INTO `t_auth_role_permission` VALUES (637, 1, NULL, 'DeviceInfoShowController:deviceInfo');
INSERT INTO `t_auth_role_permission` VALUES (638, 1, NULL, 'DeviceInfoShowController:totalDataInfo');
INSERT INTO `t_auth_role_permission` VALUES (639, 1, NULL, 'DeviceInfoShowController:getDeviceCountsWithTypeName');
INSERT INTO `t_auth_role_permission` VALUES (640, 1, NULL, 'DeviceInfoShowController:devicePriceChange');
INSERT INTO `t_auth_role_permission` VALUES (641, 1, NULL, 'GoodsInfoShowController');
INSERT INTO `t_auth_role_permission` VALUES (642, 1, NULL, 'GoodsInfoShowController:goodsPriceChange');
INSERT INTO `t_auth_role_permission` VALUES (643, 1, NULL, 'GoodsInfoShowController:countsWithGoods');
INSERT INTO `t_auth_role_permission` VALUES (644, 1, NULL, 'GoodsInfoShowController:goodsInfo');
INSERT INTO `t_auth_role_permission` VALUES (645, 1, NULL, 'GoodsInfoShowController:totalDataInfo');
INSERT INTO `t_auth_role_permission` VALUES (646, 1, NULL, 'GoodsInfoShowController:getGoodsCountsWithTypeName');
INSERT INTO `t_auth_role_permission` VALUES (647, 1, NULL, 'DevicesController:setDeviceUseDept');
INSERT INTO `t_auth_role_permission` VALUES (648, 1, NULL, 'DevicesController:jsonByDept');
INSERT INTO `t_auth_role_permission` VALUES (649, 1, NULL, 'GoodsController:jsonByDept');
INSERT INTO `t_auth_role_permission` VALUES (650, 1, NULL, 'GoodsController:setGoodsUseDept');

-- ----------------------------
-- Table structure for t_device
-- ----------------------------
DROP TABLE IF EXISTS `t_device`;
CREATE TABLE `t_device`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片、视频地址',
  `type_id` bigint(20) NULL DEFAULT NULL,
  `sellerId` int(11) NULL DEFAULT NULL,
  `dateofmanufacture` datetime NULL DEFAULT NULL COMMENT '生产日期',
  `qualityguaranteeperiod` int(2) NULL DEFAULT NULL COMMENT '保质期(月)',
  `count` int(11) NULL DEFAULT 0 COMMENT '数量',
  `useCount` int(11) NULL DEFAULT 0 COMMENT '使用数量',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_device
-- ----------------------------
INSERT INTO `t_device` VALUES (1, '设备4', 0.00, '设备4', NULL, 8, 3, '2024-02-08 00:00:00', 2, 1, 1, '2024-03-04 21:40:30');
INSERT INTO `t_device` VALUES (2, '设备5', 0.00, '设备5', NULL, 8, 3, '2024-02-09 00:00:00', 32, 1, 0, '2024-03-03 23:31:15');

-- ----------------------------
-- Table structure for t_device_department
-- ----------------------------
DROP TABLE IF EXISTS `t_device_department`;
CREATE TABLE `t_device_department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deviceId` bigint(20) NOT NULL COMMENT '设备id',
  `deptId` bigint(20) NOT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_device_department
-- ----------------------------

-- ----------------------------
-- Table structure for t_device_no_warehousing
-- ----------------------------
DROP TABLE IF EXISTS `t_device_no_warehousing`;
CREATE TABLE `t_device_no_warehousing`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片、视频地址',
  `type_id` bigint(20) NULL DEFAULT NULL,
  `sellerId` int(11) NULL DEFAULT NULL COMMENT '商家id',
  `dateofmanufacture` datetime NULL DEFAULT NULL COMMENT '生产日期',
  `qualityguaranteeperiod` int(2) NULL DEFAULT NULL COMMENT '保质期(月)',
  `count` int(11) NULL DEFAULT NULL COMMENT '数量',
  `status` int(255) NULL DEFAULT 0 COMMENT '状态（未入库0，审批中1，已入库2，审批未通过-1）',
  `createTime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_device_no_warehousing
-- ----------------------------
INSERT INTO `t_device_no_warehousing` VALUES (1, '设备1', 2000000.00, '设备1', NULL, 7, NULL, NULL, NULL, 1, 0, '2024-02-27 13:48:01');
INSERT INTO `t_device_no_warehousing` VALUES (2, '设备2', 1000000.00, '设备2', NULL, 7, NULL, NULL, NULL, 1, 0, '2024-02-27 13:48:05');
INSERT INTO `t_device_no_warehousing` VALUES (3, '设备3', 1000000.00, '设备3', NULL, 4, NULL, NULL, NULL, 1, 0, '2024-02-27 13:48:07');
INSERT INTO `t_device_no_warehousing` VALUES (5, '设备4', 0.00, '设备4', NULL, 8, 3, '2024-02-08 00:00:00', 2, 1, 2, '2024-03-04 21:32:37');
INSERT INTO `t_device_no_warehousing` VALUES (6, '设备5', 0.00, '设备5', NULL, 8, 3, '2024-02-09 00:00:00', 32, 1, 2, '2024-03-04 23:28:01');

-- ----------------------------
-- Table structure for t_device_operater_log
-- ----------------------------
DROP TABLE IF EXISTS `t_device_operater_log`;
CREATE TABLE `t_device_operater_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作类型：入库，审核...',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `operatorId` int(11) NULL DEFAULT NULL COMMENT '操作人id',
  `operatorName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人名字',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `originId` int(11) NULL DEFAULT NULL COMMENT '审批数据来源提交人',
  `originName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `deviceId` int(11) NULL DEFAULT NULL COMMENT '处理的物品的id',
  `deviceName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_device_operater_log
-- ----------------------------
INSERT INTO `t_device_operater_log` VALUES (1, '入库', '批量入库设备，设备id为5', 1, 'admin', '2024-03-04 21:30:56', NULL, NULL, 5, '设备4');
INSERT INTO `t_device_operater_log` VALUES (2, '审批', 'test', 1, 'admin', '2024-03-04 21:32:38', NULL, NULL, 5, '设备4');
INSERT INTO `t_device_operater_log` VALUES (3, '入库', '批量入库设备，设备id为6', 1, 'admin', '2024-03-04 23:27:50', NULL, NULL, 6, '设备5');
INSERT INTO `t_device_operater_log` VALUES (4, '审批', '', 1, 'admin', '2024-03-04 23:28:02', NULL, NULL, 6, '设备5');

-- ----------------------------
-- Table structure for t_device_type
-- ----------------------------
DROP TABLE IF EXISTS `t_device_type`;
CREATE TABLE `t_device_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_device_type
-- ----------------------------
INSERT INTO `t_device_type` VALUES (1, '设备类型1', '设备类型1', NULL);
INSERT INTO `t_device_type` VALUES (2, '设备类型2', '设备类型2', 1);
INSERT INTO `t_device_type` VALUES (3, '设备类型3', '设备类型3', 1);
INSERT INTO `t_device_type` VALUES (4, '设备类型4', '设备类型4', NULL);
INSERT INTO `t_device_type` VALUES (5, '设备类型5', '设备类型5', 4);
INSERT INTO `t_device_type` VALUES (6, '设备类型6', '设备类型6', 4);
INSERT INTO `t_device_type` VALUES (7, '设备类型7', '设备类型7', 1);
INSERT INTO `t_device_type` VALUES (8, '设备类型8', '设备类型8', 4);

-- ----------------------------
-- Table structure for t_disable
-- ----------------------------
DROP TABLE IF EXISTS `t_disable`;
CREATE TABLE `t_disable`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goodsId` bigint(20) NOT NULL COMMENT '报废设备id',
  `cause` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '报废原因',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `date` datetime NULL DEFAULT NULL COMMENT '报废时间',
  `operatorId` bigint(20) NULL DEFAULT NULL COMMENT '操作者id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '维修记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_disable
-- ----------------------------

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片、视频地址',
  `type_id` bigint(20) NULL DEFAULT NULL,
  `sellerId` int(11) NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL COMMENT '数量',
  `useCount` int(11) NULL DEFAULT 0 COMMENT '使用数量',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES (1, '物资1', 2000000.00, '物资1', NULL, 7, NULL, 1, 1, '2024-03-07 09:07:25');
INSERT INTO `t_goods` VALUES (2, '物资2', 1000000.00, '物资2', NULL, 7, 3, 1, 0, '2024-03-12 19:51:00');

-- ----------------------------
-- Table structure for t_goods_department
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_department`;
CREATE TABLE `t_goods_department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goodsId` bigint(20) NOT NULL COMMENT '物资id',
  `deptId` bigint(20) NOT NULL COMMENT '部门id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_goods_department
-- ----------------------------

-- ----------------------------
-- Table structure for t_goods_no_warehousing
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_no_warehousing`;
CREATE TABLE `t_goods_no_warehousing`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片、视频地址',
  `type_id` bigint(20) NULL DEFAULT NULL,
  `sellerId` int(11) NULL DEFAULT NULL,
  `count` int(11) NULL DEFAULT NULL COMMENT '数量',
  `status` int(11) NULL DEFAULT 0 COMMENT '状态（未入库0，审批中1，已入库2）',
  `createTime` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_goods_no_warehousing
-- ----------------------------
INSERT INTO `t_goods_no_warehousing` VALUES (1, '物资1', 2000000.00, '物资1', NULL, 7, 3, 1, 2, '2024-03-07 09:07:24');
INSERT INTO `t_goods_no_warehousing` VALUES (2, '物资2', 1000000.00, '物资2', NULL, 7, 3, 1, 2, '2024-03-12 19:50:59');
INSERT INTO `t_goods_no_warehousing` VALUES (3, '物资3', 1000000.00, '物资3', NULL, 4, 3, 1, 0, '2024-02-27 13:49:18');
INSERT INTO `t_goods_no_warehousing` VALUES (5, '物资4', 0.00, '物资4', NULL, 7, 3, 1, 0, '2024-02-27 13:49:21');

-- ----------------------------
-- Table structure for t_goods_operater_log
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_operater_log`;
CREATE TABLE `t_goods_operater_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作类型：入库，审核...',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `operatorId` int(11) NULL DEFAULT NULL COMMENT '操作人id',
  `operatorName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人名字',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `originId` int(11) NULL DEFAULT NULL COMMENT '审批数据来源提交人',
  `originName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `goodsId` int(11) NULL DEFAULT NULL COMMENT '处理的物品的id',
  `goodsName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_goods_operater_log
-- ----------------------------
INSERT INTO `t_goods_operater_log` VALUES (1, '入库', '批量入库物资，物资id为1', 1, 'admin', '2024-03-07 09:07:12', NULL, NULL, 1, '物资1');
INSERT INTO `t_goods_operater_log` VALUES (2, '审批', '', 1, 'admin', '2024-03-07 09:07:25', NULL, NULL, 1, '物资1');
INSERT INTO `t_goods_operater_log` VALUES (3, '入库', '批量入库物资，物资id为2', 1, 'admin', '2024-03-12 19:50:53', NULL, NULL, 2, '物资2');
INSERT INTO `t_goods_operater_log` VALUES (4, '审批', '审批通过：', 1, 'admin', '2024-03-12 19:51:00', NULL, NULL, 2, '物资2');

-- ----------------------------
-- Table structure for t_goods_type
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_type`;
CREATE TABLE `t_goods_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_goods_type
-- ----------------------------
INSERT INTO `t_goods_type` VALUES (1, '物资类型1', '物资类型1', NULL);
INSERT INTO `t_goods_type` VALUES (2, '物资类型2', '物资类型2', 1);
INSERT INTO `t_goods_type` VALUES (3, '物资类型3', '物资类型3', 1);
INSERT INTO `t_goods_type` VALUES (4, '物资类型4', '物资类型4', NULL);
INSERT INTO `t_goods_type` VALUES (5, '物资类型5', '物资类型5', 4);
INSERT INTO `t_goods_type` VALUES (6, '物资类型6', '物资类型6', 4);
INSERT INTO `t_goods_type` VALUES (7, '物资类型7', '物资类型7', 1);
INSERT INTO `t_goods_type` VALUES (8, '物资类型8', '物资类型8', 1);

-- ----------------------------
-- Table structure for t_goods_use_record
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_use_record`;
CREATE TABLE `t_goods_use_record`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `useId` int(11) NULL DEFAULT NULL COMMENT '领用人id',
  `goodsId` int(11) NULL DEFAULT NULL COMMENT '领用物品id',
  `count` int(11) NULL DEFAULT NULL COMMENT '领用数量',
  `deptParentId` int(11) NULL DEFAULT NULL COMMENT '领用部门id（第一级）',
  `operatorId` int(11) NULL DEFAULT NULL COMMENT '添加记录人id',
  `deptIdPath` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '二级部门路径',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `type` int(11) NULL DEFAULT NULL COMMENT '1物资，2设备',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_goods_use_record
-- ----------------------------
INSERT INTO `t_goods_use_record` VALUES (1, 1, 1, 1, 1, 1, '/1', NULL, 2);
INSERT INTO `t_goods_use_record` VALUES (2, 1, 1, 1, 1, 1, '/1', NULL, 1);

-- ----------------------------
-- Table structure for t_maintain
-- ----------------------------
DROP TABLE IF EXISTS `t_maintain`;
CREATE TABLE `t_maintain`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goodsId` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '维修设备id',
  `cause` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '维修原因',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `createTime` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `date` datetime NULL DEFAULT NULL COMMENT '维修时间',
  `operatorId` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '操作者id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '维修记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_maintain
-- ----------------------------

-- ----------------------------
-- Table structure for t_notice
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通知标题',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通知内容',
  `operatorId` bigint(20) NULL DEFAULT NULL COMMENT '发布人id',
  `createTime` datetime NULL DEFAULT NULL COMMENT '发布时间',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态：1 未审核 ，2 驳回 ，3 审核通过',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES (2, '通知1', '通知1内容', 1, '2024-03-08 16:41:01', 3);
INSERT INTO `t_notice` VALUES (3, '通知2', '通知2内容', 1, '2024-03-08 22:12:34', 3);

-- ----------------------------
-- Table structure for t_notice_operater_log
-- ----------------------------
DROP TABLE IF EXISTS `t_notice_operater_log`;
CREATE TABLE `t_notice_operater_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作类型：删除，审核...',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `operatorId` int(11) NULL DEFAULT NULL COMMENT '操作人id',
  `operatorName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作人名字',
  `updateTime` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `originId` int(11) NULL DEFAULT NULL COMMENT '审批数据来源提交人',
  `originName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `noticeId` int(11) NULL DEFAULT NULL COMMENT '处理的通知的id',
  `noticeTitle` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_notice_operater_log
-- ----------------------------
INSERT INTO `t_notice_operater_log` VALUES (5, '审批', '11', 1, 'admin', '2024-03-08 17:40:27', NULL, NULL, 2, '通知1');
INSERT INTO `t_notice_operater_log` VALUES (6, '设置通知可见', '通知可见：Notice(id=2, title=通知1, content=通知1内容, operatorId=1, operator=Employee(username=admin, password=null, email=null, headImage=null, age=null, department=null, departmentId=null), createTime=Fri Mar 08 16:41:01 CST 2024, status=3) 可见角色id:[0]', 1, 'admin', '2024-03-08 19:15:14', NULL, NULL, 2, '通知1');
INSERT INTO `t_notice_operater_log` VALUES (7, '设置通知可见', '通知可见：Notice(id=2, title=通知1, content=通知1内容, operatorId=1, operator=Employee(username=admin, password=null, email=null, headImage=null, age=null, department=null, departmentId=null), createTime=Fri Mar 08 16:41:01 CST 2024, status=3) 可见角色id:[0, 1]', 1, 'admin', '2024-03-08 19:15:33', NULL, NULL, 2, '通知1');
INSERT INTO `t_notice_operater_log` VALUES (8, '设置通知可见', '通知可见：Notice(id=2, title=通知1, content=通知1内容, operatorId=1, operator=Employee(username=admin, password=null, email=null, headImage=null, age=null, department=null, departmentId=null), createTime=Fri Mar 08 16:41:01 CST 2024, status=3) 可见角色id:[0]', 1, 'admin', '2024-03-08 19:18:49', NULL, NULL, 2, '通知1');
INSERT INTO `t_notice_operater_log` VALUES (9, '设置通知可见', '通知可见：Notice(id=2, title=通知1, content=通知1内容, operatorId=1, operator=Employee(username=admin, password=null, email=null, headImage=null, age=null, department=null, departmentId=null), createTime=Fri Mar 08 16:41:01 CST 2024, status=3) 可见角色id:[0, 1]', 1, 'admin', '2024-03-08 19:19:00', NULL, NULL, 2, '通知1');
INSERT INTO `t_notice_operater_log` VALUES (10, '审批', '审批通过：', 1, 'admin', '2024-03-08 22:12:59', NULL, NULL, 3, '通知2');
INSERT INTO `t_notice_operater_log` VALUES (11, '设置通知可见', '通知可见：Notice(id=3, title=通知2, content=通知2内容, operatorId=1, operator=Employee(username=admin, password=null, email=null, headImage=null, age=null, department=null, departmentId=null), createTime=Fri Mar 08 22:12:34 CST 2024, status=3) 可见角色id:[5]', 1, 'admin', '2024-03-08 22:13:11', NULL, NULL, 3, '通知2');
INSERT INTO `t_notice_operater_log` VALUES (12, '设置通知可见', '通知可见：Notice(id=3, title=通知2, content=通知2内容, operatorId=1, operator=Employee(username=admin, password=null, email=null, headImage=null, age=null, department=null, departmentId=null), createTime=Fri Mar 08 22:12:34 CST 2024, status=3) 可见角色id:[0]', 1, 'admin', '2024-03-08 22:13:23', NULL, NULL, 3, '通知2');
INSERT INTO `t_notice_operater_log` VALUES (13, '设置通知可见', '通知可见：Notice(id=3, title=通知2, content=通知2内容, operatorId=1, operator=Employee(username=admin, password=null, email=null, headImage=null, age=null, department=null, departmentId=null), createTime=Fri Mar 08 22:12:34 CST 2024, status=3) 可见角色id:[1]', 1, 'admin', '2024-03-08 22:14:32', NULL, NULL, 3, '通知2');

-- ----------------------------
-- Table structure for t_notice_role
-- ----------------------------
DROP TABLE IF EXISTS `t_notice_role`;
CREATE TABLE `t_notice_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `noticeId` bigint(20) NOT NULL COMMENT '通知id',
  `roleId` bigint(20) NOT NULL COMMENT '角色id，如果为0，则是所有角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_notice_role
-- ----------------------------
INSERT INTO `t_notice_role` VALUES (5, 2, 0);
INSERT INTO `t_notice_role` VALUES (6, 2, 1);
INSERT INTO `t_notice_role` VALUES (9, 3, 1);

-- ----------------------------
-- Table structure for t_org_department
-- ----------------------------
DROP TABLE IF EXISTS `t_org_department`;
CREATE TABLE `t_org_department`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `intro` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '简介',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `manager_id` bigint(20) NULL DEFAULT NULL COMMENT '部门经理对应员工表',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级部门',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_org_department
-- ----------------------------
INSERT INTO `t_org_department` VALUES (1, '部门1', '部门介绍', '2022-08-18 15:24:07', '2022-08-18 14:24:11', 1, NULL, '/1', 1);
INSERT INTO `t_org_department` VALUES (2, '部门2', '部门介绍', '2022-08-18 15:24:07', '2022-08-18 14:24:11', 1, 1, '/1/2', 1);
INSERT INTO `t_org_department` VALUES (3, '部门3', '部门介绍', '2022-08-18 15:24:07', '2022-08-18 14:24:11', 1, 1, '/1/3', 1);
INSERT INTO `t_org_department` VALUES (4, '部门4', '部门介绍', '2022-08-18 15:24:07', '2022-08-18 14:24:11', 1, 1, '/1/4', 1);
INSERT INTO `t_org_department` VALUES (5, '部门5', '部门介绍', '2022-08-18 15:24:07', '2022-08-18 14:24:11', 1, 1, '/1/5', 1);
INSERT INTO `t_org_department` VALUES (6, '部门6', '部门介绍', '2022-08-18 15:24:07', '2022-08-18 14:24:11', 1, 1, '/1/6', 1);
INSERT INTO `t_org_department` VALUES (7, '部门7', '部门介绍', '2022-08-18 15:24:07', '2022-08-18 14:24:11', 1, 1, '/1/7', 1);
INSERT INTO `t_org_department` VALUES (18, '部门8', '部门介绍', '2022-08-23 11:32:30', '2022-08-23 11:32:30', NULL, NULL, '/18', 1);
INSERT INTO `t_org_department` VALUES (20, '部门9', '部门介绍', '2022-08-23 11:37:36', '2022-08-23 14:08:59', 1, 2, '/1/2/20', 1);
INSERT INTO `t_org_department` VALUES (21, '部门10', '部门介绍', '2023-04-25 10:23:22', NULL, NULL, NULL, NULL, 1);
INSERT INTO `t_org_department` VALUES (22, '部门11', '部门介绍', '2023-04-25 10:28:16', '2023-04-25 10:28:42', 1, 1, NULL, 0);
INSERT INTO `t_org_department` VALUES (23, '部门12', '部门介绍', '2023-04-25 11:27:22', NULL, 2, 1, NULL, 1);
INSERT INTO `t_org_department` VALUES (24, '部门13', '部门介绍', '2023-04-25 14:22:48', NULL, 3, 5, NULL, 1);
INSERT INTO `t_org_department` VALUES (26, '部门14', '部门介绍', '2023-04-25 14:49:34', NULL, 4, NULL, '/26', 1);
INSERT INTO `t_org_department` VALUES (27, '部门15', '部门介绍', '2023-04-25 14:50:33', '2023-04-25 15:02:11', 5, 26, '/26/27', 1);

-- ----------------------------
-- Table structure for t_org_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_org_employee`;
CREATE TABLE `t_org_employee`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `headImage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `department_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK4AFD4ACE851EFECF`(`department_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_org_employee
-- ----------------------------
INSERT INTO `t_org_employee` VALUES (1, 'admin', '123456', '11@qq.com', '/images/head/avatar.png', 33, 1);
INSERT INTO `t_org_employee` VALUES (2, 'menuAdmin', '123', 'roleAdmin@ronghuanet.com', '/images/head/avatar1.png', 25, 1);
INSERT INTO `t_org_employee` VALUES (3, 'admin1', '123', 'amdin1@ronghuanet.com', '/images/head/avatar2.png', 25, 1);
INSERT INTO `t_org_employee` VALUES (4, 'admin2', '123', 'amdin2@ronghuanet.com', NULL, 25, 2);
INSERT INTO `t_org_employee` VALUES (5, 'admin3', '123', 'amdin3@ronghuanet.com', '/images/head/avatar3.png', 25, 1);
INSERT INTO `t_org_employee` VALUES (6, 'admin4', '123', 'amdin4@ronghuanet.com', '/images/head/avatar3.png', 25, 3);
INSERT INTO `t_org_employee` VALUES (7, 'admin5', '123', 'amdin5@ronghuanet.com', '/images/head/avatar3.png', 25, 1);
INSERT INTO `t_org_employee` VALUES (8, 'admin6', '123', 'amdin6@ronghuanet.com', '/images/head/avatar3.png', 25, 2);
INSERT INTO `t_org_employee` VALUES (9, 'admin7', '123', 'amdin7@ronghuanet.com', NULL, 25, 2);
INSERT INTO `t_org_employee` VALUES (10, 'admin8', '123', 'amdin8@ronghuanet.com', '/images/head/avatar3.png', 25, 1);
INSERT INTO `t_org_employee` VALUES (11, 'admin9', '123', 'amdin9@ronghuanet.com', '/images/head/avatar3.png', 25, 2);
INSERT INTO `t_org_employee` VALUES (12, 'admin10', '123', 'amdin10@ronghuanet.com', '/images/head/avatar3.png', 25, 2);
INSERT INTO `t_org_employee` VALUES (13, 'admin11', '123', 'amdin11@ronghuanet.com', '/images/head/avatar3.png', 25, 1);
INSERT INTO `t_org_employee` VALUES (14, 'admin12', '123', 'amdin12@ronghuanet.com', '/images/head/avatar3.png', 10, 3);
INSERT INTO `t_org_employee` VALUES (15, 'admin13', '123', 'amdin13@ronghuanet.com', '/images/head/avatar3.png', 25, 3);
INSERT INTO `t_org_employee` VALUES (16, 'admin14', '123', 'amdin14@ronghuanet.com', '/images/head/avatar3.png', 25, 3);
INSERT INTO `t_org_employee` VALUES (17, 'admin15', '123', 'amdin15@ronghuanet.com', '/images/head/avatar3.png', 25, 3);
INSERT INTO `t_org_employee` VALUES (18, 'admin16', '123', 'amdin16@ronghuanet.com', '/images/head/avatar3.png', 25, 1);
INSERT INTO `t_org_employee` VALUES (19, 'admin17', '123', 'amdin17@ronghuanet.com', '/images/head/avatar3.png', 25, 1);
INSERT INTO `t_org_employee` VALUES (20, 'admin18', '123', 'amdin18@ronghuanet.com', '/images/head/avatar3.png', 25, 1);
INSERT INTO `t_org_employee` VALUES (21, 'admin19', '123', 'amdin19@ronghuanet.com', '/images/head/avatar3.png', 25, 3);
INSERT INTO `t_org_employee` VALUES (22, 'admin20', '123', 'amdin20@ronghuanet.com', '/images/head/avatar3.png', 25, 3);
INSERT INTO `t_org_employee` VALUES (23, 'admin21', '123', 'amdin21@ronghuanet.com', '/images/head/avatar3.png', 25, 3);
INSERT INTO `t_org_employee` VALUES (24, 'admin22', '123', 'amdin22@ronghuanet.com', '/images/head/avatar3.png', 25, 3);
INSERT INTO `t_org_employee` VALUES (25, 'admin23', '123', 'amdin23@ronghuanet.com', '/images/head/avatar3.png', 25, 3);
INSERT INTO `t_org_employee` VALUES (26, 'admin24', '123', 'amdin24@ronghuanet.com', '/images/head/avatar3.png', 25, 3);
INSERT INTO `t_org_employee` VALUES (27, 'admin25', '123', 'amdin25@ronghuanet.com', '/images/head/avatar3.png', 25, 1);
INSERT INTO `t_org_employee` VALUES (28, 'admin26', '123', 'amdin26@ronghuanet.com', '/images/head/avatar3.png', 25, 3);
INSERT INTO `t_org_employee` VALUES (29, 'admin27', '123', 'amdin27@ronghuanet.com', '/images/head/avatar3.png', 25, 1);
INSERT INTO `t_org_employee` VALUES (30, 'admin28', '123', 'amdin28@ronghuanet.com', '/images/head/avatar3.png', 25, 3);
INSERT INTO `t_org_employee` VALUES (31, 'admin29', '123', 'amdin29@ronghuanet.com', '/images/head/avatar3.png', 25, 1);
INSERT INTO `t_org_employee` VALUES (32, 'admin30', '123', 'amdin30@ronghuanet.com', '/images/head/avatar3.png', 25, 1);
INSERT INTO `t_org_employee` VALUES (33, '', '123456', '', NULL, 34, NULL);
INSERT INTO `t_org_employee` VALUES (34, '', '123456', '', NULL, 34, NULL);

-- ----------------------------
-- Table structure for t_seller
-- ----------------------------
DROP TABLE IF EXISTS `t_seller`;
CREATE TABLE `t_seller`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `resource` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片、视频地址',
  `type_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_seller
-- ----------------------------
INSERT INTO `t_seller` VALUES (1, '商家1', 2000000.00, '商家1.....', NULL, 1);
INSERT INTO `t_seller` VALUES (2, '商家2', 1000000.00, '商家2....', NULL, 1);
INSERT INTO `t_seller` VALUES (3, '商家3', 1000000.00, '商家3', NULL, 4);

-- ----------------------------
-- Table structure for t_seller_type
-- ----------------------------
DROP TABLE IF EXISTS `t_seller_type`;
CREATE TABLE `t_seller_type`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_seller_type
-- ----------------------------
INSERT INTO `t_seller_type` VALUES (1, '商家1', '商家1.....', NULL);
INSERT INTO `t_seller_type` VALUES (2, '商家2', '商家2.。。。', 1);
INSERT INTO `t_seller_type` VALUES (3, '商家3', '商家3.。。。', 1);
INSERT INTO `t_seller_type` VALUES (4, '商家4', '商家4', NULL);
INSERT INTO `t_seller_type` VALUES (5, '商家5', '商家5....', 4);
INSERT INTO `t_seller_type` VALUES (6, '商家6', '商家6....', 4);

-- ----------------------------
-- Table structure for t_sys_backup
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_backup`;
CREATE TABLE `t_sys_backup`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `backupFileName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备份文件名',
  `backupTime` datetime NULL DEFAULT NULL COMMENT '备份时间',
  `operatorId` bigint(20) NULL DEFAULT NULL COMMENT '备份人',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 42 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_backup
-- ----------------------------
INSERT INTO `t_sys_backup` VALUES (41, '2024-02-28_04-46-56_pzhxy_device_manager.sql', '2024-02-28 04:46:57', 1, 'test');

-- ----------------------------
-- Table structure for t_sys_backup_operator_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_backup_operator_log`;
CREATE TABLE `t_sys_backup_operator_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `time` datetime NULL DEFAULT NULL COMMENT '操作时间',
  `operatorId` bigint(20) NULL DEFAULT NULL COMMENT '操作人',
  `operatorName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 90 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_backup_operator_log
-- ----------------------------
INSERT INTO `t_sys_backup_operator_log` VALUES (1, '禁用CRON:0 0 2 * * ?', '2024-02-27 19:44:55', 1, '禁用CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (2, '更新CRON:0 0 2 * * ?->0 0 2 * * ?', '2024-02-27 19:46:41', 1, '更新CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (3, '添加CRON:0 0 2 * * ?', '2024-02-27 19:51:56', 1, '添加CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (4, '更新CRON:0 0 2 * * ?->0 0 2 * * ?', '2024-02-27 19:54:27', 1, '更新CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (5, '添加CRON:0 0 20 * * ?', '2024-02-27 19:58:55', 1, '添加CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (6, '启用CRON:0 0 20 * * ?', '2024-02-27 19:59:40', 1, '启用CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (7, '禁用CRON:0 0 20 * * ?', '2024-02-27 20:05:29', 1, '禁用CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (8, '添加CRON:0 0 20 * * ?', '2024-02-27 20:08:50', 1, '添加CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (9, 'test', '2024-02-27 20:14:13', 1, '手动备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (10, '更新CRON:0 19 20 * * ?->0 19 20 * * ?', '2024-02-27 20:17:40', 1, '更新CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (11, '启用CRON:0 19 20 * * ?', '2024-02-27 20:17:48', 1, '启用CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (12, '更新CRON:0 0/5 * * * ?->0 0/5 * * * ?', '2024-02-27 20:30:39', 1, '更新CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (13, '更新CRON:0 0/5 * * * ?->0 0/2 * * * ?', '2024-02-27 20:32:12', 1, '更新CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (14, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 21:12:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (15, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 21:13:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (16, '更新CRON:0 0/1 * * * ?->0 0/5 * * * ?', '2024-02-27 21:13:57', 1, '更新CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (17, '系统定时任务备份,执行规则：0 0/5 * * * ?', '2024-02-27 21:14:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (18, '系统定时任务备份,执行规则：0 0/5 * * * ?', '2024-02-27 21:15:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (19, '系统定时任务备份,执行规则：0 0/5 * * * ?', '2024-02-27 21:20:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (20, '禁用CRON:0 0/5 * * * ?', '2024-02-27 21:21:05', 1, '禁用CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (21, '添加CRON:0 0/1 * * * ?', '2024-02-27 21:27:34', 1, '添加CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (22, '启用CRON:0 0/1 * * * ?', '2024-02-27 21:49:07', 1, '启用CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (23, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:14:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (24, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:15:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (25, '系统定时任务备份,执行规则：0 0/5 * * * ?', '2024-02-27 22:15:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (26, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:16:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (27, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:17:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (28, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:18:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (29, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:19:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (30, '启用CRON:0 0/5 * * * ?', '2024-02-27 22:19:33', 1, '启用CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (31, '系统定时任务备份,执行规则：0 0/5 * * * ?', '2024-02-27 22:20:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (32, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:20:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (33, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:21:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (34, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:22:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (35, '添加CRON:10 0/1 * * * ?', '2024-02-27 22:22:16', 1, '添加CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (36, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:23:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (37, '系统定时任务备份,执行规则：10 0/1 * * * ?', '2024-02-27 22:23:10', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (38, '禁用CRON:10 0/1 * * * ?', '2024-02-27 22:23:54', 1, '禁用CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (39, '禁用CRON:0 0/1 * * * ?', '2024-02-27 22:23:55', 1, '禁用CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (40, '禁用CRON:0 0/5 * * * ?', '2024-02-27 22:23:56', 1, '禁用CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (41, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:24:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (42, '系统定时任务备份,执行规则：10 0/1 * * * ?', '2024-02-27 22:24:10', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (43, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:25:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (44, '系统定时任务备份,执行规则：0 0/5 * * * ?', '2024-02-27 22:25:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (45, '系统定时任务备份,执行规则：10 0/1 * * * ?', '2024-02-27 22:25:10', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (46, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:26:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (47, '系统定时任务备份,执行规则：10 0/1 * * * ?', '2024-02-27 22:26:10', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (48, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:27:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (49, '系统定时任务备份,执行规则：10 0/1 * * * ?', '2024-02-27 22:27:10', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (50, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:28:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (51, '系统定时任务备份,执行规则：10 0/1 * * * ?', '2024-02-27 22:28:10', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (52, '启用CRON:0 0/1 * * * ?', '2024-02-27 22:31:20', 1, '启用CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (53, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:41:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (54, '启用CRON:10 0/1 * * * ?', '2024-02-27 22:41:46', 1, '启用CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (55, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:42:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (56, '系统定时任务备份,执行规则：10 0/1 * * * ?', '2024-02-27 22:42:10', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (57, '系统定时任务备份,执行规则：0 0/1 * * * ?', '2024-02-27 22:43:00', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (58, '系统定时任务备份,执行规则：10 0/1 * * * ?', '2024-02-27 22:43:10', -1, '系统备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (59, '禁用CRON:0 0/1 * * * ?', '2024-02-27 22:43:18', 1, '禁用CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (60, '禁用CRON:10 0/1 * * * ?', '2024-02-27 22:43:19', 1, '禁用CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (61, 'test', '2024-02-27 23:06:21', 1, '手动备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (62, 'admin删除了备份文件：2024-02-27_22-43-00_pzhxy_device_manager.sql', '2024-02-27 23:58:04', 1, '删除备份文件');
INSERT INTO `t_sys_backup_operator_log` VALUES (63, 'admin删除了备份文件：2024-02-27_22-43-00_pzhxy_device_manager.sql', '2024-02-28 00:00:26', 1, '删除备份文件');
INSERT INTO `t_sys_backup_operator_log` VALUES (64, 'admin删除了定时任务,任务信息：null', '2024-02-28 00:04:07', 1, '删除定时任务');
INSERT INTO `t_sys_backup_operator_log` VALUES (65, 'test', '2024-02-28 00:04:29', 1, '手动备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (66, 'admin删除了定时任务,任务信息：null', '2024-02-28 00:04:57', 1, '删除定时任务');
INSERT INTO `t_sys_backup_operator_log` VALUES (67, 'admin根据备份文件：D:/device_manager_system_backup/2024-02-28_00-04-28_pzhxy_device_manager.sql进行了数据库恢复', '2024-02-28 00:05:06', 1, '根据备份文件恢复');
INSERT INTO `t_sys_backup_operator_log` VALUES (68, '添加CRON:0 0/1 * * * ?', '2024-02-28 00:09:32', 1, '添加CRON表达式');
INSERT INTO `t_sys_backup_operator_log` VALUES (69, 'admin根据备份文件：2024-02-28_00-04-28_pzhxy_device_manager.sql进行了数据库恢复', '2024-02-28 00:10:15', 1, '根据备份文件恢复');
INSERT INTO `t_sys_backup_operator_log` VALUES (70, 'admin根据备份文件：2024-02-28_00-04-28_pzhxy_device_manager.sql进行了数据库恢复', '2024-02-28 00:11:55', 1, '根据备份文件恢复');
INSERT INTO `t_sys_backup_operator_log` VALUES (71, 'admin根据备份文件：2024-02-28_00-04-28_pzhxy_device_manager.sql进行了数据库恢复', '2024-02-28 00:15:34', 1, '根据备份文件恢复失败');
INSERT INTO `t_sys_backup_operator_log` VALUES (72, 'admin根据备份文件：2024-02-28_00-04-28_pzhxy_device_manager.sql进行了数据库恢复', '2024-02-28 00:19:31', 1, '根据备份文件恢复失败');
INSERT INTO `t_sys_backup_operator_log` VALUES (73, 'admin根据备份文件：2024-02-28_00-04-28_pzhxy_device_manager.sql进行了数据库恢复', '2024-02-28 00:24:44', 1, '根据备份文件恢复失败');
INSERT INTO `t_sys_backup_operator_log` VALUES (74, 'admin根据备份文件：2024-02-28_00-04-28_pzhxy_device_manager.sql进行了数据库恢复', '2024-02-28 00:28:22', 1, '根据备份文件恢复失败');
INSERT INTO `t_sys_backup_operator_log` VALUES (75, 'admin根据备份文件：2024-02-28_00-04-28_pzhxy_device_manager.sql进行了数据库恢复', '2024-02-28 00:31:41', 1, '根据备份文件恢复失败');
INSERT INTO `t_sys_backup_operator_log` VALUES (76, 'admin根据备份文件：2024-02-28_00-04-28_pzhxy_device_manager.sql进行了数据库恢复', '2024-02-28 01:11:23', 1, '根据备份文件恢复失败');
INSERT INTO `t_sys_backup_operator_log` VALUES (77, 'admin根据备份文件：2024-02-28_00-04-28_pzhxy_device_manager.sql进行了数据库恢复', '2024-02-28 01:13:24', 1, '根据备份文件恢复失败');
INSERT INTO `t_sys_backup_operator_log` VALUES (78, 'admin根据备份文件：2024-02-28_00-04-28_pzhxy_device_manager.sql进行了数据库恢复', '2024-02-28 01:16:47', 1, '根据备份文件恢复失败');
INSERT INTO `t_sys_backup_operator_log` VALUES (79, 'admin根据备份文件：2024-02-28_00-04-28_pzhxy_device_manager.sql进行了数据库恢复', '2024-02-28 01:26:55', 1, '根据备份文件恢复失败');
INSERT INTO `t_sys_backup_operator_log` VALUES (80, 'test', '2024-02-28 01:33:09', 1, '手动备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (81, 'admin根据备份文件：2024-02-28_01-33-08_pzhxy_device_manager.sql进行了数据库恢复', '2024-02-28 01:33:18', 1, '根据备份文件恢复失败');
INSERT INTO `t_sys_backup_operator_log` VALUES (82, '', '2024-02-28 01:38:58', 1, '手动备份');
INSERT INTO `t_sys_backup_operator_log` VALUES (83, 'admin根据备份文件：2024-02-28_01-38-58_pzhxy_device_manager.sql进行了数据库恢复', '2024-02-28 04:46:03', 1, '根据备份文件恢复成功');
INSERT INTO `t_sys_backup_operator_log` VALUES (84, 'admin删除了备份文件：2024-02-27_22-43-10_pzhxy_device_manager.sql', '2024-02-28 04:46:36', 1, '删除备份文件');
INSERT INTO `t_sys_backup_operator_log` VALUES (85, 'admin删除了备份文件：2024-02-27_23-06-20_pzhxy_device_manager.sql', '2024-02-28 04:46:38', 1, '删除备份文件');
INSERT INTO `t_sys_backup_operator_log` VALUES (86, 'admin删除了备份文件：2024-02-28_00-04-28_pzhxy_device_manager.sql', '2024-02-28 04:46:40', 1, '删除备份文件');
INSERT INTO `t_sys_backup_operator_log` VALUES (87, 'admin删除了备份文件：2024-02-28_01-33-08_pzhxy_device_manager.sql', '2024-02-28 04:46:42', 1, '删除备份文件');
INSERT INTO `t_sys_backup_operator_log` VALUES (88, 'admin删除了备份文件：2024-02-28_01-38-58_pzhxy_device_manager.sql', '2024-02-28 04:46:44', 1, '删除备份文件');
INSERT INTO `t_sys_backup_operator_log` VALUES (89, 'test', '2024-02-28 04:46:57', 1, '手动备份');

-- ----------------------------
-- Table structure for t_sys_scheduledcron
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_scheduledcron`;
CREATE TABLE `t_sys_scheduledcron`  (
  `cronId` int(11) NOT NULL AUTO_INCREMENT,
  `cronKey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '定时任务完整类名',
  `cronExpression` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'corn表达式',
  `taskExplain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `taskStatus` int(1) NULL DEFAULT NULL COMMENT '状态：1：正常，2:停用',
  PRIMARY KEY (`cronId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_scheduledcron
-- ----------------------------
INSERT INTO `t_sys_scheduledcron` VALUES (12, 'cn.cailang.sys.Scheduled.DBBackupTask@2', '10 0/1 * * * ?', 'test', 2);
INSERT INTO `t_sys_scheduledcron` VALUES (13, 'cn.cailang.sys.Scheduled.DBBackupTask', '0 0/1 * * * ?', 'test', 2);

SET FOREIGN_KEY_CHECKS = 1;
