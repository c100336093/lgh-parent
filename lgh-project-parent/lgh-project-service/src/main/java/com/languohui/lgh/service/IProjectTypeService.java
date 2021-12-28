package com.languohui.lgh.service;

import com.languohui.lgh.domain.ProjectType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2021-12-28
 */
public interface IProjectTypeService extends IService<ProjectType> {

    List<ProjectType> loadTreeData();
}
