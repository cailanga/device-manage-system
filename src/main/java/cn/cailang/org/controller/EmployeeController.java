package cn.cailang.org.controller;

import cn.cailang.auth.annotation.JiaXinPermission;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.FileUtils;
import cn.cailang.base.utils.PageList;
import cn.cailang.org.domain.Employee;
import cn.cailang.org.query.EmployeeQuery;
import cn.cailang.org.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * @ClassName: EmployeeController
 * @Description: 员工控制层
 * @Author: 3299903308@qq.com
 * @Date: 2023/4/23 16:13
 * @Version 1.0
 **/
@JiaXinPermission(name = "员工管理",description = "员工管理权限")
@Api(value = "员工管理",description="员工相关的CRUD功能")
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    /**
     * 通过id查询员工信息
     * @param id 员工id
     * @return AjaxResult对象
     */
    @ApiOperation("通过id查询员工信息")
    @ApiImplicitParam(name = "id", value = "员工ID", required = true, dataType = "Long",paramType = "path")
    @GetMapping("/{id}")
//    @JiaXinPermission(name = "通过id查询员工信息",description = "通过id查询员工信息")
    public AjaxResult selectById(
            @ApiParam(name = "主键",required = true)
            @PathVariable("id") Long id) {
        try {
            Employee employee = employeeService.selectById(id);
            return AjaxResult.me().setResultObject(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    /**
     * 查询所有员工信息
     * @return AjaxResult对象
     */
    @ApiOperation("查询所有员工信息")
    @GetMapping
//    @JiaXinPermission(name = "查询所有员工信息",description = "查询所有员工信息")
    public AjaxResult selectAll() {
        try {
            List<Employee> employees = employeeService.selectAll();
            return AjaxResult.me().setResultObject(employees);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    /**
     * 根据id删除员工信息
     * @param id 员工id
     * @return AjaxResult对象
     */
    @ApiOperation("根据id删除员工信息")
    @ApiImplicitParam(name = "id", value = "员工ID", required = true, dataType = "Long",paramType = "path")
    @DeleteMapping("/{id}")
    @JiaXinPermission(name = "删除员工信息权限",description = "删除员工信息权限")
    public AjaxResult deleteById(
            @ApiParam(name = "主键",required = true)
            @PathVariable("id") Long id){
        try {
            employeeService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    @ApiOperation("根据ids批量删除员工信息")
    @PatchMapping
    @JiaXinPermission(name = "批量删除员工信息权限",description = "批量删除员工信息权限")
    public AjaxResult batchDelete(
            @ApiParam(name = "id集合",required = true)
             @RequestBody List<Long> ids){
        try {
            employeeService.batchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("操作失败："+e.getMessage()).setSuccess(false);
        }
    }

    /**
     * 新增或修改员工信息
     * @param employee 员工信息
     * @return AjaxResult对象
     */
    @ApiOperation("新增或修改员工信息")
    @PutMapping
    @JiaXinPermission(name = "新增或修改员工信息权限",description = "新增或修改员工信息权限")
    public AjaxResult addOrUpdate(@RequestBody Employee employee){
        try {
            if (Objects.nonNull(employee.getId())){
                //id不为空，则是修改操作
                employeeService.update(employee);
            }else {
                //新增操作
                employeeService.insert(employee);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    /**
     * 根据关键字进行分页查询
     * @param query 查询条件对象
     * @return AjaxResult对象
     */
    @ApiOperation("根据关键字进行分页查询")
    @PostMapping
    @JiaXinPermission(name = "根据关键字进行分页查询权限",description = "根据关键字进行分页查询权限")
    public AjaxResult queryDataByKeyword(@RequestBody EmployeeQuery query){
        try {
            PageList<Employee> pageList = employeeService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
    }

    /**
     * 头像图片文件上传
     * @param file 上传的文件对象
     * @return AjaxResult
     */
//    @ApiOperation("头像图片文件上传")
//    @JiaXinPermission(name = "头像图片文件上传",description = "头像图片文件上传")
    @PostMapping("/uploadHeadImage")
    public AjaxResult uploadHeadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        // 判断文件是否为空
        if (file.isEmpty()) {
            return AjaxResult.me().setMessage("上传文件不能为空。").setSuccess(false);
        }

        // 获取上传文件的名称
        String fileName = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        fileName = uuid + "-" + fileName;
        try {
            //将文件写在target下，让上传后即可访问
            byte[] bytes = file.getBytes();
            URL systemResource = EmployeeController.class.getClassLoader().getResource("static/upload/headImage");
            if (Objects.isNull(systemResource)){
                //递归创建文件夹
                FileUtils.createFolder(EmployeeController.class.getClassLoader().getResource("").getPath()+"/static/upload/headImage");
            }
            String path1 = EmployeeController.class.getClassLoader().getResource("").getPath()+"/static/upload/headImage";//systemResource.getPath();
            File file1 = new File(path1+"/"+fileName);
            try (OutputStream out = new FileOutputStream(file1)) {
                out.write(bytes);
            }
            // 获取项目根目录下的 static 目录路径
            String imageUrl = System.getProperty("user.dir")+"/src/main/resources/static/upload/headImage/";

            // 将上传文件保存到指定目录中
            File destDir = new File(imageUrl);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            File destFile = new File(destDir, fileName);
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败："+e.getMessage());
        }
        return AjaxResult.me().setResultObject("/api/upload/headImage/"+fileName);
    }

    /**
     * 取消时将上传的头像删除
     * @param map 文件名map
     * @return AjaxResult
     */
//    @ApiOperation("头像图片删除")
//    @JiaXinPermission(name = "头像图片删除",description = "头像图片删除")
    @PostMapping("/deleteHeadImage")
    public AjaxResult deleteFile(@RequestBody Map map){
        String fileName = (String) map.get("fileName");
        if (StringUtils.isEmpty(fileName)){
            return AjaxResult.me().setSuccess(false).setMessage("文件名不能为空");
        }
        //取到文件名
        fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        //获取target下的图片
        String path1 = ClassLoader.getSystemResource("static/upload/headImage").getPath()+"/"+fileName;
        File file = new File(path1);
        if (file.exists()) {
            file.delete();
        }
        //获取项目中resources下的图片
        String imageUrl = System.getProperty("user.dir")+"/src/main/resources/static/upload/headImage/"+fileName;
        File file1 = new File(imageUrl);
        if (file1.exists()) {
            file1.delete();
        }
        return AjaxResult.me();
    }

    /**
     * 设置员工角色
     * @param map 员工以及对应的角色ID
     * @return AjaxResult
     */
    @PostMapping("/setEmployeeRole")
    @ApiOperation("设置员工角色")
    @JiaXinPermission(name = "设置员工角色权限",description = "设置员工角色权限")
    public AjaxResult setEmployeeRole(@RequestBody Map map){
        try {
            Long employeeId = Long.parseLong(map.get("employeeId").toString());
            List<Long> roleIds = (List<Long>) map.get("roleIds");
            employeeService.setEmployeeRole(employeeId,roleIds);
            return AjaxResult.me();
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage(e.getMessage());
        }

    }

    /**
     * 根据员工id获取其角色ids
     * @param employeeId 员工id
     * @return AjaxResult
     */
    @ApiOperation("根据员工id获取其角色ids")
//    @JiaXinPermission(name = "根据员工id获取其角色ids",description = "根据员工id获取其角色ids")
    @GetMapping("/getEmpRolesByEmplId/{employeeId}")
    public AjaxResult getEmpRolesByEmplId(@PathVariable("employeeId")Long employeeId){
        try {
            List<Long> roleIds = employeeService.getEmpRolesByEmplId(employeeId);
            return AjaxResult.me().setResultObject(roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage(e.getMessage()).setSuccess(false);
        }

    }
}
