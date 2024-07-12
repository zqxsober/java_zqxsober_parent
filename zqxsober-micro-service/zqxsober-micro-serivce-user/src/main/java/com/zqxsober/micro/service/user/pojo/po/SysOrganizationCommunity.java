package com.zqxsober.micro.service.user.pojo.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author: zqxsober
 * @Description: SysOrganizationCommunity 类
 * @Date: 2024-04-19 11:23
 */
@Data
public class SysOrganizationCommunity {

    /**
     * id
     */
    private Integer id;

    /**
     * 项目id
     */
    private String communityId;

    /**
     * 所属组织id
     */
    private Integer orgId;

    /**
     * 组织路径（逗号分隔）上级在前下级在后 100001，100002，100003，100004
     */
    private String orgPath;

    /**
     * 添加人
     */
    private String inuser;

    /**
     * 录入时间
     */
    private Date intime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 是否删除 0-未删除，1-已删除
     */
    private Integer isDeleted;
}
