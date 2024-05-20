package com.zqxsober.micro.service.product.pojo.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author: zqxsober
 * @Description: SysOrganizationHierarchy 类
 * @Date: 2024-04-19 11:21
 */
@Data
public class SysOrganizationHierarchy {
    /**
     * 主键 primary key
     */
    private Integer id;

    /**
     * 层级名称
     */
    private String hierarchyName;

    /**
     * 上级id
     */
    private Integer parentId;

    /**
     * 层级路径（逗号分隔）上级在前下级在后 1，2，3，4
     */
    private String hierarchyPath;

    /**
     * 排序
     */
    private Integer hierarchySort;

    /**
     * 录入用户
     */
    private String inuser;

    /**
     * 录入时间
     */
    private Date intime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除 0-未删除，1-已删除
     */
    private Integer isDeleted;
}
