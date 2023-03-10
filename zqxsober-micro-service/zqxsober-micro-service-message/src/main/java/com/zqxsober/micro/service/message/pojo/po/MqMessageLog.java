package com.zqxsober.micro.service.message.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 消息队列记录表
 * </p>
 *
 * @author zqxsober
 * @since 2022-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mq_message_log")
public class MqMessageLog extends Model<MqMessageLog> {

    private static final long serialVersionUID=1L;

    /**
     * 主键唯一
     */
      @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 消息ID
     */
    @TableField("msg_id")
    private String msgId;

    /**
     * 发送状态 0-待发送，1-已发送，2已消费
     */
    @TableField("status")
    private Integer status;

    /**
     * 消息类型 0-普通消息，1-延时消息
     */
    @TableField("type")
    private Integer type;

    /**
     * 延时时间
     */
    @TableField("delay_time")
    private Integer delayTime;

    /**
     *  发送状态 0-否，1-是 
     */
    @TableField("already_dead")
    private Integer alreadyDead;

    /**
     *  重试次数 
     */
    @TableField("retry_times")
    private Integer retryTimes;

    /**
     *  主题 
     */
    @TableField("topic")
    private String topic;

    /**
     *  组 
     */
    @TableField("group")
    private String group;

    /**
     *  消息json 
     */
    @TableField("msg_content")
    private String msgContent;

    /**
     *  备注 
     */
    @TableField("remark")
    private String remark;

    /**
     *  创建时间 
     */
    @TableField("create_time")
    private Date createTime;

    /**
     *  创建人 
     */
    @TableField("create_user")
    private String createUser;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 是否删除，0未删除，1已删除
     */
    @TableField("deleted")
    private Integer deleted;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
