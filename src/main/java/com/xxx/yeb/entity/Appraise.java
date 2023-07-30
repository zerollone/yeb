package com.xxx.yeb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
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
    public class Appraise implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 员工id
     */
      private Integer eid;

      /**
     * 考评日期
     */
      @TableField("appDate")
    private LocalDate appDate;

      /**
     * 考评结果
     */
      @TableField("appResult")
    private String appResult;

      /**
     * 考评内容
     */
      @TableField("appContent")
    private String appContent;

      /**
     * 备注
     */
      private String remark;


}
