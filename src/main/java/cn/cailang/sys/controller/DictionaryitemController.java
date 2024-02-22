package cn.cailang.sys.controller;

import cn.cailang.sys.service.IDictionaryitemService;
import cn.cailang.sys.domain.DictionaryItem;
import cn.cailang.sys.query.DictionaryitemQuery;
import cn.cailang.base.utils.AjaxResult;
import cn.cailang.base.utils.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dictionaryitem")
public class DictionaryitemController {
    @Autowired
    public IDictionaryitemService dictionaryitemService;


    /**
     * 保存和修改公用的
     * @param dictionaryitem  传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody DictionaryItem dictionaryitem){
        try {
            if( dictionaryitem.getId()!=null){
                    dictionaryitemService.update(dictionaryitem);
            }
            else{
                    dictionaryitemService.insert(dictionaryitem);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("保存对象失败！"+e.getMessage());
        }
    }
    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @DeleteMapping(value="/{id}")
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            dictionaryitemService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }

    /**
    * 批量删除对象信息
    * @param ids
    * @return
    */
    @PatchMapping()
    public AjaxResult batchDelete(@RequestBody List<Long> ids){
        try {
                dictionaryitemService.batchDelete(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("删除对象失败！"+e.getMessage());
        }
    }
	
    //获取用户
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id")Long id)
    {
        try {
            DictionaryItem dictionaryitem = dictionaryitemService.selectById(id);
            return AjaxResult.me().setResultObject(dictionaryitem);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取一个失败！"+e.getMessage());
        }
    }

    @GetMapping("/sn/{sn}")
    public AjaxResult getBySn(@PathVariable("sn")String sn)
    {
        try {
            List<DictionaryItem> dictionaryitems = dictionaryitemService.selectBySn(sn);
            return AjaxResult.me().setResultObject(dictionaryitems);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取一个失败！"+e.getMessage());
        }
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping
    public AjaxResult list(){

        try {
            List<DictionaryItem> list = dictionaryitemService.selectAll();
            return AjaxResult.me().setResultObject(list);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取所有失败！"+e.getMessage());
        }
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public AjaxResult json(@RequestBody DictionaryitemQuery query)
    {
        try {
            PageList<DictionaryItem> pageList = dictionaryitemService.pageList(query);
            return AjaxResult.me().setResultObject(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！"+e.getMessage());
        }
    }
}
