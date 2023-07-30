package com.xxx.yeb.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class MailLog implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 消息id
     */
        @TableId("msgId")
      private String msgId;

      /**
     * 接收员工id
     */
      private Integer eid;

      /**
     * 状态（0:消息投递中 1:投递成功 2:投递失败）
     */
      private Integer status;

      /**
     * 路由键
     */
      @TableField("routeKey")
    private String routeKey;

      /**
     * 交换机
     */
      private String exchange;

      /**
     * 重试次数
     */
      private Integer count;

      /**
     * 重试时间
     */
      @TableField("tryTime")
    private LocalDateTime tryTime;

      /**
     * 创建时间
     */
      @TableField("createTime")
    private LocalDateTime createTime;

      /**
     * 更新时间
     */
      @TableField("updateTime")
    private LocalDateTime updateTime;


}
