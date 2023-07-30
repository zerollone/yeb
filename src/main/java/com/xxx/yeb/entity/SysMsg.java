package com.xxx.yeb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    public class SysMsg implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 消息id
     */
      private Integer mid;

      /**
     * 0表示群发消息
     */
      private Integer type;

      /**
     * 这条消息是给谁的
     */
      private Integer adminid;

      /**
     * 0 未读 1 已读
     */
      private Integer state;


}
