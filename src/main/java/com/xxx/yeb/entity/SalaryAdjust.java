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
    public class SalaryAdjust implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 员工ID
     */
      private Integer eid;

      /**
     * 调薪日期
     */
      @TableField("asDate")
    private LocalDate asDate;

      /**
     * 调前薪资
     */
      @TableField("beforeSalary")
    private Integer beforeSalary;

      /**
     * 调后薪资
     */
      @TableField("afterSalary")
    private Integer afterSalary;

      /**
     * 调薪原因
     */
      private String reason;

      /**
     * 备注
     */
      private String remark;


}
