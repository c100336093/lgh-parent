package com.languohui.lgh.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2021-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_project_type")
public class ProjectType extends Model<ProjectType> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型 1.产品分类 2.项目分类
     */
    private Integer type;

    /**
     * 类型名称
     */
    private String name;

    /**
     * 父级ID
     */
    private Integer pid;

    /**
     * 图片
     */
    private String logo;

    /**
     * 类型描述
     */
    private String description;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否删除 0正常 1 删除
     */
    private Integer isdelete;

    /**
     * 创建时间
     */
    private Long createtime;

    //所有子类型（查询忽略）
    @TableField(exist = false)
    private List<ProjectType> children = new ArrayList<>();


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
