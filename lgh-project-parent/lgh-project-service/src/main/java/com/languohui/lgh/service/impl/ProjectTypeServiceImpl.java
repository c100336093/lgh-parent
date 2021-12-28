package com.languohui.lgh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.languohui.lgh.domain.ProjectType;
import com.languohui.lgh.mapper.ProjectTypeMapper;
import com.languohui.lgh.service.IProjectTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2021-12-28
 */
@Service
public class ProjectTypeServiceImpl extends ServiceImpl<ProjectTypeMapper, ProjectType> implements IProjectTypeService {

    @Override
    public List<ProjectType> loadTreeData() {
        List<ProjectType> projecttypes = loadTreeDataLoop();
        return projecttypes;
    }

    public List<ProjectType> loadTreeDataLoop(){
        //初始化一个集合放一级
        List<ProjectType> firstTypes = new ArrayList<>();
        //查询所有类型
        List<ProjectType> projecttypes = baseMapper.selectList(null);
        //创建一个Map,将ProjectType数据存到map中，key使用id，value就是ProjectType
        Map<Integer,ProjectType> map = new HashMap<>();
        for (ProjectType projecttype : projecttypes) {
            map.put(projecttype.getId(),projecttype);
        }
        //循环projecttypes，分配一级类型和非一级类型
        for (ProjectType projecttype : projecttypes) {
            if(projecttype.getPid().intValue() == 0){
                firstTypes.add(projecttype);
            }else{
                ProjectType parent = map.get(projecttype.getPid());
                if(parent != null){
                    parent.getChildren().add(projecttype);
                }
            }
        }
        return firstTypes;
    }
}
