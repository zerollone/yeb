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
    public class EmployeeRemove implements Serializable {

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
     * 调动后部门
     */
      @TableField("afterDepId")
    private Integer afterDepId;

      /**
     * 调动后职位
     */
      @TableField("afterJobId")
    private Integer afterJobId;

      /**
     * 调动日期
     */
      @TableField("removeDate")
    private LocalDate removeDate;

      /**
     * 调动原因
     */
      private String reason;

      /**
     * 备注
     */
      private String remark;


}
