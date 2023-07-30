package com.xxx.yeb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    public class AdminRole implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 用户id
     */
      @TableField("adminId")
    private Integer adminId;

      /**
     * 权限id
     */
      private Integer rid;


}
