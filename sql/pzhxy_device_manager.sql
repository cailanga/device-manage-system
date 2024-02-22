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

 Date: 22/02/2024 14:54:11
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
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

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
INSERT INTO `t_auth_menu` VALUES (29, '系统配置', '/config', '', 28);
INSERT INTO `t_auth_menu` VALUES (30, '数据字典', '/dictionary', '', 28);
INSERT INTO `t_auth_menu` VALUES (31, '数据字典明细', '/dictionaryitem', '', 28);
INSERT INTO `t_auth_menu` VALUES (39, '物资设备', '/', 'fa fa-bar-chart', NULL);
INSERT INTO `t_auth_menu` VALUES (40, '采购入库', '/procure', '', 39);
INSERT INTO `t_auth_menu` VALUES (41, '物资', '/goods', '', 39);
INSERT INTO `t_auth_menu` VALUES (42, '物资类型', '/goodsType', '', 39);
INSERT INTO `t_auth_menu` VALUES (43, '物资操作日志', '/goodsOperaterLog', '', 39);
INSERT INTO `t_auth_menu` VALUES (44, '待审核物资', '/checkingGoods', '', 39);
INSERT INTO `t_auth_menu` VALUES (45, '设备', '/device', '', 39);
INSERT INTO `t_auth_menu` VALUES (46, '设备类型', '/deviceType', '', 39);
INSERT INTO `t_auth_menu` VALUES (47, '设备操作日志', '/devicesOperaterLog', '', 39);
INSERT INTO `t_auth_menu` VALUES (48, '待审核设备', '/checkingDevices', '', 39);
INSERT INTO `t_auth_menu` VALUES (49, '领用记录', '/useRecord', '', 39);
INSERT INTO `t_auth_menu` VALUES (50, '商家管理', '/seller', '', 25);
INSERT INTO `t_auth_menu` VALUES (51, '商家类型', '/sellerType', '', 25);
INSERT INTO `t_auth_menu` VALUES (52, '统计信息', '/', 'fa fa-bar-chart', NULL);
INSERT INTO `t_auth_menu` VALUES (53, '通知管理', '/', 'fa fa-bar-chart', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1560 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_auth_permission
-- ----------------------------
INSERT INTO `t_auth_permission` VALUES (1517, '部门管理', '/department', '部门管理权限', 'DepartmentController', NULL);
INSERT INTO `t_auth_permission` VALUES (1518, '根据关键字进行分页查询', '/department', '根据关键字进行分页查询', 'DepartmentController:queryDataByKeyword', 1517);
INSERT INTO `t_auth_permission` VALUES (1519, '通过id查询部门信息', '/department/{id}', '通过id查询部门信息', 'DepartmentController:selectById', 1517);
INSERT INTO `t_auth_permission` VALUES (1520, '根据id删除部门信息', '/department/{id}', '根据id删除部门信息', 'DepartmentController:deleteById', 1517);
INSERT INTO `t_auth_permission` VALUES (1521, '查询所有部门信息', '/department', '查询所有部门信息', 'DepartmentController:selectAll', 1517);
INSERT INTO `t_auth_permission` VALUES (1522, '批量删除', '/department', '批量删除', 'DepartmentController:batchDelete', 1517);
INSERT INTO `t_auth_permission` VALUES (1523, '新增或修改部门信息', '/department', '新增或修改部门信息', 'DepartmentController:addOrUpdate', 1517);
INSERT INTO `t_auth_permission` VALUES (1524, '查询部门树', '/department/tree', '查询部门树', 'DepartmentController:queryFirstDeptAndChildren', 1517);
INSERT INTO `t_auth_permission` VALUES (1525, '员工管理', '/employee', '员工管理权限', 'EmployeeController', NULL);
INSERT INTO `t_auth_permission` VALUES (1526, '头像图片删除', '/employee/deleteHeadImage', '头像图片删除', 'EmployeeController:deleteFile', 1525);
INSERT INTO `t_auth_permission` VALUES (1527, '根据关键字进行分页查询', '/employee', '根据关键字进行分页查询', 'EmployeeController:queryDataByKeyword', 1525);
INSERT INTO `t_auth_permission` VALUES (1528, '通过id查询员工信息', '/employee/{id}', '通过id查询员工信息', 'EmployeeController:selectById', 1525);
INSERT INTO `t_auth_permission` VALUES (1529, '根据id删除员工信息', '/employee/{id}', '根据id删除员工信息', 'EmployeeController:deleteById', 1525);
INSERT INTO `t_auth_permission` VALUES (1530, '查询所有员工信息', '/employee', '查询所有员工信息', 'EmployeeController:selectAll', 1525);
INSERT INTO `t_auth_permission` VALUES (1531, '根据ids批量删除员工信息', '/employee', '根据ids批量删除员工信息', 'EmployeeController:batchDelete', 1525);
INSERT INTO `t_auth_permission` VALUES (1532, '新增或修改员工信息', '/employee', '新增或修改员工信息', 'EmployeeController:addOrUpdate', 1525);
INSERT INTO `t_auth_permission` VALUES (1533, '设置员工角色', '/employee/setEmployeeRole', '设置员工角色', 'EmployeeController:setEmployeeRole', 1525);
INSERT INTO `t_auth_permission` VALUES (1534, '头像图片文件上传', '/employee/uploadHeadImage', '头像图片文件上传', 'EmployeeController:uploadHeadImage', 1525);
INSERT INTO `t_auth_permission` VALUES (1535, '根据员工id获取其角色ids', '/employee/getEmpRolesByEmplId/{employeeId}', '根据员工id获取其角色ids', 'EmployeeController:getEmpRolesByEmplId', 1525);
INSERT INTO `t_auth_permission` VALUES (1536, '菜单管理', '/menu', '菜单管理权限', 'MenuController', NULL);
INSERT INTO `t_auth_permission` VALUES (1537, '根据关键字进行分页查询', '/menu', '根据关键字进行分页查询', 'MenuController:queryDataByKeyword', 1536);
INSERT INTO `t_auth_permission` VALUES (1538, '根据员工id获取菜单树', '/menu/getMenuTree/{employeeId}', '', 'MenuController:getMenuTreeByEmpId', 1536);
INSERT INTO `t_auth_permission` VALUES (1539, '通过id查询菜单信息', '/menu/{id}', '通过id查询菜单信息', 'MenuController:selectById', 1536);
INSERT INTO `t_auth_permission` VALUES (1540, '根据id删除菜单信息', '/menu/{id}', '根据id删除菜单信息', 'MenuController:deleteById', 1536);
INSERT INTO `t_auth_permission` VALUES (1541, '查询所有菜单信息', '/menu', '查询所有菜单信息', 'MenuController:selectAll', 1536);
INSERT INTO `t_auth_permission` VALUES (1542, '根据ids批量删除菜单信息', '/menu', '根据ids批量删除菜单信息', 'MenuController:batchDelete', 1536);
INSERT INTO `t_auth_permission` VALUES (1543, '新增或修改菜单信息', '/menu', '新增或修改菜单信息', 'MenuController:addOrUpdate', 1536);
INSERT INTO `t_auth_permission` VALUES (1544, '权限管理', '/permission', '权限管理', 'PermissionController', NULL);
INSERT INTO `t_auth_permission` VALUES (1545, '通过员工id获取权限', '/permission/getPermissionsByEmployeeId/{employeeId}', '', 'PermissionController:getPermissionsByEmployeeId', 1544);
INSERT INTO `t_auth_permission` VALUES (1546, '根据关键字进行分页查询', '/permission/pageList', '根据关键字进行分页查询', 'PermissionController:pageList', 1544);
INSERT INTO `t_auth_permission` VALUES (1547, '角色管理', '/role', '角色管理权限', 'RoleController', NULL);
INSERT INTO `t_auth_permission` VALUES (1548, '设置权限', '/role/setPermission', '', 'RoleController:setPermission', 1547);
INSERT INTO `t_auth_permission` VALUES (1549, '根据关键字进行分页查询', '/role', '根据关键字进行分页查询', 'RoleController:queryDataByKeyword', 1547);
INSERT INTO `t_auth_permission` VALUES (1550, '通过角色id获取菜单ids', '/role/getMenuIdsByRoleId/{roleId}', '', 'RoleController:getMenuIdsByRoleId', 1547);
INSERT INTO `t_auth_permission` VALUES (1551, '获取权限树', '/role/getPermissionTree', '', 'RoleController:getPermissionTree', 1547);
INSERT INTO `t_auth_permission` VALUES (1552, '通过角色id获取权限标识', '/role/getPermissionsSnsByRoleId/{roleId}', '', 'RoleController:getPermissionsSnsByRoleId', 1547);
INSERT INTO `t_auth_permission` VALUES (1553, '通过id查询角色信息', '/role/{id}', '通过id查询角色信息', 'RoleController:selectById', 1547);
INSERT INTO `t_auth_permission` VALUES (1554, '根据id删除角色信息', '/role/{id}', '根据id删除角色信息', 'RoleController:deleteById', 1547);
INSERT INTO `t_auth_permission` VALUES (1555, '查询所有角色信息', '/role', '查询所有角色信息', 'RoleController:selectAll', 1547);
INSERT INTO `t_auth_permission` VALUES (1556, '根据ids批量删除角色信息', '/role', '根据ids批量删除角色信息', 'RoleController:batchDelete', 1547);
INSERT INTO `t_auth_permission` VALUES (1557, '获取菜单树', '/role/getMenuTree', '', 'RoleController:getMenuTree', 1547);
INSERT INTO `t_auth_permission` VALUES (1558, '新增或修改角色信息', '/role', '新增或修改角色信息', 'RoleController:addOrUpdate', 1547);
INSERT INTO `t_auth_permission` VALUES (1559, '设置菜单', '/role/setMenu', '', 'RoleController:setMenu', 1547);

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
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_auth_role_menu
-- ----------------------------
INSERT INTO `t_auth_role_menu` VALUES (5, 2, 20);
INSERT INTO `t_auth_role_menu` VALUES (6, 2, 21);
INSERT INTO `t_auth_role_menu` VALUES (8, 2, 24);
INSERT INTO `t_auth_role_menu` VALUES (9, 5, 32);
INSERT INTO `t_auth_role_menu` VALUES (10, 5, 33);
INSERT INTO `t_auth_role_menu` VALUES (11, 5, 34);
INSERT INTO `t_auth_role_menu` VALUES (45, 1, 20);
INSERT INTO `t_auth_role_menu` VALUES (46, 1, 21);
INSERT INTO `t_auth_role_menu` VALUES (47, 1, 22);
INSERT INTO `t_auth_role_menu` VALUES (48, 1, 23);
INSERT INTO `t_auth_role_menu` VALUES (49, 1, 24);
INSERT INTO `t_auth_role_menu` VALUES (50, 1, 25);
INSERT INTO `t_auth_role_menu` VALUES (51, 1, 26);
INSERT INTO `t_auth_role_menu` VALUES (52, 1, 27);
INSERT INTO `t_auth_role_menu` VALUES (53, 1, 50);
INSERT INTO `t_auth_role_menu` VALUES (54, 1, 51);
INSERT INTO `t_auth_role_menu` VALUES (55, 1, 29);
INSERT INTO `t_auth_role_menu` VALUES (56, 1, 28);
INSERT INTO `t_auth_role_menu` VALUES (57, 1, 30);
INSERT INTO `t_auth_role_menu` VALUES (58, 1, 31);
INSERT INTO `t_auth_role_menu` VALUES (59, 1, 39);
INSERT INTO `t_auth_role_menu` VALUES (60, 1, 40);
INSERT INTO `t_auth_role_menu` VALUES (61, 1, 41);
INSERT INTO `t_auth_role_menu` VALUES (62, 1, 42);
INSERT INTO `t_auth_role_menu` VALUES (63, 1, 43);
INSERT INTO `t_auth_role_menu` VALUES (64, 1, 44);
INSERT INTO `t_auth_role_menu` VALUES (65, 1, 45);
INSERT INTO `t_auth_role_menu` VALUES (66, 1, 46);
INSERT INTO `t_auth_role_menu` VALUES (67, 1, 47);
INSERT INTO `t_auth_role_menu` VALUES (68, 1, 48);
INSERT INTO `t_auth_role_menu` VALUES (69, 1, 49);
INSERT INTO `t_auth_role_menu` VALUES (70, 1, 52);
INSERT INTO `t_auth_role_menu` VALUES (71, 1, 53);

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
) ENGINE = InnoDB AUTO_INCREMENT = 282 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_auth_role_permission
-- ----------------------------
INSERT INTO `t_auth_role_permission` VALUES (135, 1, NULL, 'DepartmentController');
INSERT INTO `t_auth_role_permission` VALUES (136, 1, NULL, 'DepartmentController:queryDataByKeyword');
INSERT INTO `t_auth_role_permission` VALUES (137, 1, NULL, 'DepartmentController:selectById');
INSERT INTO `t_auth_role_permission` VALUES (138, 1, NULL, 'DepartmentController:deleteById');
INSERT INTO `t_auth_role_permission` VALUES (139, 1, NULL, 'DepartmentController:selectAll');
INSERT INTO `t_auth_role_permission` VALUES (140, 1, NULL, 'DepartmentController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (141, 1, NULL, 'DepartmentController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (142, 1, NULL, 'DepartmentController:queryFirstDeptAndChildren');
INSERT INTO `t_auth_role_permission` VALUES (143, 1, NULL, 'EmployeeController');
INSERT INTO `t_auth_role_permission` VALUES (144, 1, NULL, 'EmployeeController:deleteFile');
INSERT INTO `t_auth_role_permission` VALUES (145, 1, NULL, 'EmployeeController:queryDataByKeyword');
INSERT INTO `t_auth_role_permission` VALUES (146, 1, NULL, 'EmployeeController:selectById');
INSERT INTO `t_auth_role_permission` VALUES (147, 1, NULL, 'EmployeeController:deleteById');
INSERT INTO `t_auth_role_permission` VALUES (148, 1, NULL, 'EmployeeController:selectAll');
INSERT INTO `t_auth_role_permission` VALUES (149, 1, NULL, 'EmployeeController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (150, 1, NULL, 'EmployeeController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (151, 1, NULL, 'EmployeeController:setEmployeeRole');
INSERT INTO `t_auth_role_permission` VALUES (152, 1, NULL, 'EmployeeController:uploadHeadImage');
INSERT INTO `t_auth_role_permission` VALUES (153, 1, NULL, 'EmployeeController:getEmpRolesByEmplId');
INSERT INTO `t_auth_role_permission` VALUES (154, 1, NULL, 'MenuController');
INSERT INTO `t_auth_role_permission` VALUES (155, 1, NULL, 'MenuController:queryDataByKeyword');
INSERT INTO `t_auth_role_permission` VALUES (156, 1, NULL, 'MenuController:getMenuTreeByEmpId');
INSERT INTO `t_auth_role_permission` VALUES (157, 1, NULL, 'MenuController:selectById');
INSERT INTO `t_auth_role_permission` VALUES (158, 1, NULL, 'MenuController:deleteById');
INSERT INTO `t_auth_role_permission` VALUES (159, 1, NULL, 'MenuController:selectAll');
INSERT INTO `t_auth_role_permission` VALUES (160, 1, NULL, 'MenuController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (161, 1, NULL, 'MenuController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (162, 1, NULL, 'PermissionController');
INSERT INTO `t_auth_role_permission` VALUES (163, 1, NULL, 'PermissionController:getPermissionsByEmployeeId');
INSERT INTO `t_auth_role_permission` VALUES (164, 1, NULL, 'PermissionController:pageList');
INSERT INTO `t_auth_role_permission` VALUES (165, 1, NULL, 'RoleController');
INSERT INTO `t_auth_role_permission` VALUES (166, 1, NULL, 'RoleController:setPermission');
INSERT INTO `t_auth_role_permission` VALUES (167, 1, NULL, 'RoleController:queryDataByKeyword');
INSERT INTO `t_auth_role_permission` VALUES (168, 1, NULL, 'RoleController:getMenuIdsByRoleId');
INSERT INTO `t_auth_role_permission` VALUES (169, 1, NULL, 'RoleController:getPermissionTree');
INSERT INTO `t_auth_role_permission` VALUES (170, 1, NULL, 'RoleController:getPermissionsSnsByRoleId');
INSERT INTO `t_auth_role_permission` VALUES (171, 1, NULL, 'RoleController:selectById');
INSERT INTO `t_auth_role_permission` VALUES (172, 1, NULL, 'RoleController:deleteById');
INSERT INTO `t_auth_role_permission` VALUES (173, 1, NULL, 'RoleController:selectAll');
INSERT INTO `t_auth_role_permission` VALUES (174, 1, NULL, 'RoleController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (175, 1, NULL, 'RoleController:getMenuTree');
INSERT INTO `t_auth_role_permission` VALUES (176, 1, NULL, 'RoleController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (177, 1, NULL, 'RoleController:setMenu');
INSERT INTO `t_auth_role_permission` VALUES (178, 2, NULL, 'DepartmentController');
INSERT INTO `t_auth_role_permission` VALUES (179, 2, NULL, 'DepartmentController:queryDataByKeyword');
INSERT INTO `t_auth_role_permission` VALUES (180, 2, NULL, 'DepartmentController:selectById');
INSERT INTO `t_auth_role_permission` VALUES (181, 2, NULL, 'DepartmentController:deleteById');
INSERT INTO `t_auth_role_permission` VALUES (182, 2, NULL, 'DepartmentController:selectAll');
INSERT INTO `t_auth_role_permission` VALUES (183, 2, NULL, 'DepartmentController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (184, 2, NULL, 'DepartmentController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (185, 2, NULL, 'DepartmentController:queryFirstDeptAndChildren');
INSERT INTO `t_auth_role_permission` VALUES (186, 2, NULL, 'EmployeeController');
INSERT INTO `t_auth_role_permission` VALUES (187, 2, NULL, 'EmployeeController:deleteFile');
INSERT INTO `t_auth_role_permission` VALUES (188, 2, NULL, 'EmployeeController:queryDataByKeyword');
INSERT INTO `t_auth_role_permission` VALUES (189, 2, NULL, 'EmployeeController:selectById');
INSERT INTO `t_auth_role_permission` VALUES (190, 2, NULL, 'EmployeeController:deleteById');
INSERT INTO `t_auth_role_permission` VALUES (191, 2, NULL, 'EmployeeController:selectAll');
INSERT INTO `t_auth_role_permission` VALUES (192, 2, NULL, 'EmployeeController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (193, 2, NULL, 'EmployeeController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (194, 2, NULL, 'EmployeeController:setEmployeeRole');
INSERT INTO `t_auth_role_permission` VALUES (195, 2, NULL, 'EmployeeController:uploadHeadImage');
INSERT INTO `t_auth_role_permission` VALUES (196, 2, NULL, 'EmployeeController:getEmpRolesByEmplId');
INSERT INTO `t_auth_role_permission` VALUES (197, 2, NULL, 'MenuController');
INSERT INTO `t_auth_role_permission` VALUES (198, 2, NULL, 'MenuController:queryDataByKeyword');
INSERT INTO `t_auth_role_permission` VALUES (199, 2, NULL, 'MenuController:getMenuTreeByEmpId');
INSERT INTO `t_auth_role_permission` VALUES (200, 2, NULL, 'MenuController:selectById');
INSERT INTO `t_auth_role_permission` VALUES (201, 2, NULL, 'MenuController:deleteById');
INSERT INTO `t_auth_role_permission` VALUES (202, 2, NULL, 'MenuController:selectAll');
INSERT INTO `t_auth_role_permission` VALUES (203, 2, NULL, 'MenuController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (204, 2, NULL, 'MenuController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (205, 2, NULL, 'PermissionController');
INSERT INTO `t_auth_role_permission` VALUES (206, 2, NULL, 'PermissionController:getPermissionsByEmployeeId');
INSERT INTO `t_auth_role_permission` VALUES (207, 2, NULL, 'PermissionController:pageList');
INSERT INTO `t_auth_role_permission` VALUES (208, 2, NULL, 'RoleController');
INSERT INTO `t_auth_role_permission` VALUES (209, 2, NULL, 'RoleController:setPermission');
INSERT INTO `t_auth_role_permission` VALUES (210, 2, NULL, 'RoleController:queryDataByKeyword');
INSERT INTO `t_auth_role_permission` VALUES (211, 2, NULL, 'RoleController:getMenuIdsByRoleId');
INSERT INTO `t_auth_role_permission` VALUES (212, 2, NULL, 'RoleController:getPermissionTree');
INSERT INTO `t_auth_role_permission` VALUES (213, 2, NULL, 'RoleController:getPermissionsSnsByRoleId');
INSERT INTO `t_auth_role_permission` VALUES (214, 2, NULL, 'RoleController:selectById');
INSERT INTO `t_auth_role_permission` VALUES (215, 2, NULL, 'RoleController:deleteById');
INSERT INTO `t_auth_role_permission` VALUES (216, 2, NULL, 'RoleController:selectAll');
INSERT INTO `t_auth_role_permission` VALUES (217, 2, NULL, 'RoleController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (218, 2, NULL, 'RoleController:getMenuTree');
INSERT INTO `t_auth_role_permission` VALUES (219, 2, NULL, 'RoleController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (220, 2, NULL, 'RoleController:setMenu');
INSERT INTO `t_auth_role_permission` VALUES (271, 5, NULL, 'MenuController');
INSERT INTO `t_auth_role_permission` VALUES (272, 5, NULL, 'MenuController:queryDataByKeyword');
INSERT INTO `t_auth_role_permission` VALUES (273, 5, NULL, 'MenuController:getMenuTreeByEmpId');
INSERT INTO `t_auth_role_permission` VALUES (274, 5, NULL, 'MenuController:selectById');
INSERT INTO `t_auth_role_permission` VALUES (275, 5, NULL, 'MenuController:deleteById');
INSERT INTO `t_auth_role_permission` VALUES (276, 5, NULL, 'MenuController:selectAll');
INSERT INTO `t_auth_role_permission` VALUES (277, 5, NULL, 'MenuController:batchDelete');
INSERT INTO `t_auth_role_permission` VALUES (278, 5, NULL, 'MenuController:addOrUpdate');
INSERT INTO `t_auth_role_permission` VALUES (279, 5, NULL, 'PermissionController');
INSERT INTO `t_auth_role_permission` VALUES (280, 5, NULL, 'PermissionController:getPermissionsByEmployeeId');
INSERT INTO `t_auth_role_permission` VALUES (281, 5, NULL, 'PermissionController:pageList');

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_device
-- ----------------------------
INSERT INTO `t_device` VALUES (1, '天上人间8栋808', 2000000.00, '天上人间8栋808，精装大套丝', NULL, 7, NULL, NULL, NULL, 1, 0);
INSERT INTO `t_device` VALUES (2, '融创公馆101号', 1000000.00, '融创公馆101号', NULL, 7, NULL, NULL, NULL, 1, 0);
INSERT INTO `t_device` VALUES (3, '1111', 1000000.00, '1000000', NULL, 4, NULL, NULL, NULL, 1, 0);
INSERT INTO `t_device` VALUES (4, '11', 11.00, '11', NULL, 7, NULL, '2024-02-06 00:00:00', 11, 1, 0);
INSERT INTO `t_device` VALUES (5, '2221', 0.00, '222', NULL, 8, 3, '2024-02-08 00:00:00', 2, 1, 1);

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_device_no_warehousing
-- ----------------------------
INSERT INTO `t_device_no_warehousing` VALUES (1, '天上人间8栋808', 2000000.00, '天上人间8栋808，精装大套丝', NULL, 7, NULL, NULL, NULL, NULL, 1);
INSERT INTO `t_device_no_warehousing` VALUES (2, '融创公馆101号', 1000000.00, '融创公馆101号', NULL, 7, NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_device_no_warehousing` VALUES (3, '1111', 1000000.00, '1000000', NULL, 4, NULL, NULL, NULL, NULL, 0);
INSERT INTO `t_device_no_warehousing` VALUES (5, '2221', 0.00, '222', NULL, 8, 3, '2024-02-08 00:00:00', 2, 1, 2);
INSERT INTO `t_device_no_warehousing` VALUES (6, '3434', 0.00, '3343', NULL, 8, 3, '2024-02-09 00:00:00', 32, 1, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_device_operater_log
-- ----------------------------
INSERT INTO `t_device_operater_log` VALUES (4, '入库', '批量入库设备，设备d为[1]', 1, 'admin', '2024-02-08 21:28:10', NULL, NULL, 1, '天上人间8栋808');
INSERT INTO `t_device_operater_log` VALUES (5, '入库', '批量入库设备，设备id为1', 1, 'admin', '2024-02-08 21:35:58', NULL, NULL, 1, '天上人间8栋808');
INSERT INTO `t_device_operater_log` VALUES (6, '入库', '批量入库设备，设备id为1', 1, 'admin', '2024-02-08 21:39:16', NULL, NULL, 1, '天上人间8栋808');
INSERT INTO `t_device_operater_log` VALUES (7, '入库', '批量入库设备，设备id为5', 1, 'admin', '2024-02-09 10:49:55', NULL, NULL, 5, '2221');
INSERT INTO `t_device_operater_log` VALUES (8, '审批', '111', 1, 'admin', '2024-02-09 10:50:13', NULL, NULL, 5, '2221');

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
INSERT INTO `t_device_type` VALUES (1, '商品房', '商品房', NULL);
INSERT INTO `t_device_type` VALUES (2, '别墅', '别墅', 1);
INSERT INTO `t_device_type` VALUES (3, '跃层', '跃层', 1);
INSERT INTO `t_device_type` VALUES (4, '公寓', '公寓', NULL);
INSERT INTO `t_device_type` VALUES (5, '住宅公寓', '住宅公寓', 4);
INSERT INTO `t_device_type` VALUES (6, '商办公寓', '商办公寓', 4);
INSERT INTO `t_device_type` VALUES (7, '普通商品房', '普通商品房', 1);
INSERT INTO `t_device_type` VALUES (8, '222', '222', 4);

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES (1, '天上人间8栋808', 2000000.00, '天上人间8栋808，精装大套丝', NULL, 7, NULL, 1, 0);
INSERT INTO `t_goods` VALUES (2, '融创公馆101号', 1000000.00, '融创公馆101号', NULL, 7, NULL, 1, 0);
INSERT INTO `t_goods` VALUES (3, '1111', 1000000.00, '1000000', NULL, 4, NULL, 1, 0);
INSERT INTO `t_goods` VALUES (4, '天上人间8栋808', 2000000.00, '天上人间8栋808，精装大套丝', NULL, 7, NULL, 1, 0);
INSERT INTO `t_goods` VALUES (5, '天上人间8栋808', 2000000.00, '天上人间8栋808，精装大套丝', NULL, 7, NULL, 1, 0);
INSERT INTO `t_goods` VALUES (6, '天上人间8栋808', 2000000.00, '天上人间8栋808，精装大套丝', NULL, 7, NULL, 1, 0);
INSERT INTO `t_goods` VALUES (7, '33334', 0.00, '3333', NULL, 7, NULL, 1, 1);
INSERT INTO `t_goods` VALUES (8, '融创公馆101号', 1000000.00, '融创公馆101号', NULL, 7, NULL, 1, 0);

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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_goods_no_warehousing
-- ----------------------------
INSERT INTO `t_goods_no_warehousing` VALUES (1, '天上人间8栋808', 2000000.00, '天上人间8栋808，精装大套丝', NULL, 7, NULL, 1, 2);
INSERT INTO `t_goods_no_warehousing` VALUES (2, '融创公馆101号', 1000000.00, '融创公馆101号', NULL, 7, NULL, 1, 2);
INSERT INTO `t_goods_no_warehousing` VALUES (3, '1111', 1000000.00, '1000000', NULL, 4, NULL, 1, 0);
INSERT INTO `t_goods_no_warehousing` VALUES (5, '333343', 0.00, '3333', NULL, 7, 3, 1, 2);

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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_goods_operater_log
-- ----------------------------
INSERT INTO `t_goods_operater_log` VALUES (1, '入库', '批量入库物资，物资id为[1]', 1, 'admin', '2024-02-08 18:36:22', NULL, NULL, NULL, NULL);
INSERT INTO `t_goods_operater_log` VALUES (2, '审批', '可以入库', 1, 'admin', NULL, NULL, NULL, 1, '天上人间8栋808');
INSERT INTO `t_goods_operater_log` VALUES (3, '审批', '可以入库', 1, 'admin', '2024-02-08 20:36:40', NULL, NULL, 1, '天上人间8栋808');
INSERT INTO `t_goods_operater_log` VALUES (4, '审批', '111', 1, 'admin', '2024-02-08 21:38:54', NULL, NULL, 1, '天上人间8栋808');
INSERT INTO `t_goods_operater_log` VALUES (5, '入库', '批量入库物资，物资id为5', 1, 'admin', '2024-02-08 23:01:08', NULL, NULL, 5, '33334');
INSERT INTO `t_goods_operater_log` VALUES (6, '审批', '不能入库', 1, 'admin', '2024-02-08 23:03:43', NULL, NULL, 5, '33334');
INSERT INTO `t_goods_operater_log` VALUES (7, '入库', '批量入库物资，物资id为5', 1, 'admin', '2024-02-08 23:04:02', NULL, NULL, 5, '33334');
INSERT INTO `t_goods_operater_log` VALUES (8, '审批', '允许入库', 1, 'admin', '2024-02-08 23:04:18', NULL, NULL, 5, '33334');
INSERT INTO `t_goods_operater_log` VALUES (9, '入库', '批量入库物资，物资id为2', 1, 'admin', '2024-02-10 11:40:43', NULL, NULL, 2, '融创公馆101号');
INSERT INTO `t_goods_operater_log` VALUES (10, '审批', '111', 1, 'admin', '2024-02-10 11:40:59', NULL, NULL, 2, '融创公馆101号');

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
INSERT INTO `t_goods_type` VALUES (1, '商品房', '商品房', NULL);
INSERT INTO `t_goods_type` VALUES (2, '别墅', '别墅', 1);
INSERT INTO `t_goods_type` VALUES (3, '跃层', '跃层', 1);
INSERT INTO `t_goods_type` VALUES (4, '公寓', '公寓', NULL);
INSERT INTO `t_goods_type` VALUES (5, '住宅公寓', '住宅公寓', 4);
INSERT INTO `t_goods_type` VALUES (6, '商办公寓', '商办公寓', 4);
INSERT INTO `t_goods_type` VALUES (7, '普通商品房', '普通商品房', 1);
INSERT INTO `t_goods_type` VALUES (8, '112', '11', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_goods_use_record
-- ----------------------------
INSERT INTO `t_goods_use_record` VALUES (9, 1, 7, 1, 2, 1, '/1/2', '111', 1);
INSERT INTO `t_goods_use_record` VALUES (10, 1, 5, 1, 18, 1, '/18', '111', 2);

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
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_org_employee
-- ----------------------------
INSERT INTO `t_org_employee` VALUES (1, 'admin', '123', 'admin@ronghuanet.com', '/images/head/avatar.png', 34, 1);
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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

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
-- Table structure for t_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_config`;
CREATE TABLE `t_sys_config`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '配置信息的key',
  `value` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '配置信息的值',
  `type` bigint(255) NULL DEFAULT NULL,
  `intro` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_config
-- ----------------------------
INSERT INTO `t_sys_config` VALUES (1, 'key1', 'value1', 1, '');
INSERT INTO `t_sys_config` VALUES (3, 'key2', 'value2', NULL, NULL);
INSERT INTO `t_sys_config` VALUES (4, 'key3', 'value3', NULL, NULL);

-- ----------------------------
-- Table structure for t_sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dictionary`;
CREATE TABLE `t_sys_dictionary`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标识',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态1=启用 0=禁用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `sn`(`sn`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_dictionary
-- ----------------------------
INSERT INTO `t_sys_dictionary` VALUES (1, '标识1', '标题1', '', 1);
INSERT INTO `t_sys_dictionary` VALUES (2, '标识2', '标题2', '', 1);
INSERT INTO `t_sys_dictionary` VALUES (3, '标识3', '标题3', '', 1);
INSERT INTO `t_sys_dictionary` VALUES (4, '标识4', '标题4', '', 1);
INSERT INTO `t_sys_dictionary` VALUES (5, '标识5', '标题5', '', 1);
INSERT INTO `t_sys_dictionary` VALUES (6, '标识6', '标题6', '', 1);

-- ----------------------------
-- Table structure for t_sys_dictionaryitem
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dictionaryitem`;
CREATE TABLE `t_sys_dictionaryitem`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sequence` int(11) NULL DEFAULT NULL,
  `intro` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '数据字典类型id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKA19398F8B4C06BAA`(`parent_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_sys_dictionaryitem
-- ----------------------------
INSERT INTO `t_sys_dictionaryitem` VALUES (1, '标题1', '', 1, '', 1);
INSERT INTO `t_sys_dictionaryitem` VALUES (2, '标题2', NULL, 2, '', 1);
INSERT INTO `t_sys_dictionaryitem` VALUES (3, '标题3', NULL, 3, '', 1);
INSERT INTO `t_sys_dictionaryitem` VALUES (4, '标题4', NULL, 4, '', 1);
INSERT INTO `t_sys_dictionaryitem` VALUES (5, '标题5', NULL, 5, '', 1);

SET FOREIGN_KEY_CHECKS = 1;
