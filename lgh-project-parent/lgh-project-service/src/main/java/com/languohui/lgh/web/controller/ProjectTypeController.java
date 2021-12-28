package com.languohui.lgh.web.controller;

import com.languohui.lgh.service.IProjectTypeService;
import com.languohui.lgh.domain.ProjectType;
import com.languohui.lgh.query.ProjectTypeQuery;
import com.languohui.basic.util.AjaxResult;
import com.languohui.basic.util.PageList;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projectType")
public class ProjectTypeController {
    @Autowired
    public IProjectTypeService projectTypeService;

    /**
    * 保存和修改公用的
    * @param projectType  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody ProjectType projectType){
        try {
            if(projectType.getId()!=null){
                projectTypeService.updateById(projectType);
            }else{
                projectTypeService.save(projectType);
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
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            projectTypeService.removeById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ProjectType get(@PathVariable("id")Long id)
    {
        return projectTypeService.getById(id);
    }


    /**
    * 查看所有信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<ProjectType> list(){

        return projectTypeService.list(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/page",method = RequestMethod.POST)
    public PageList<ProjectType> page(@RequestBody ProjectTypeQuery query)
    {
        Page<ProjectType> page = projectTypeService.page(new Page<ProjectType>(query.getPage(), query.getRows()));
        return new PageList<>(page.getTotal(),page.getRecords());
    }
    /**
     * 加载项目类型树
     * */
    @GetMapping("/treeData")
    public List<ProjectType> treeData(){
        return projectTypeService.loadTreeData();
    }
}
