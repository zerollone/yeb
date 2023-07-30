package com.xxx.yeb.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 *
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = false, of = "name")
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 部门名称
     */
    @Excel(name = "部门")
    @NonNull
    private String name;

    /**
     * 父id
     */
    @TableField("parentId")
    private Integer parentId;

    /**
     * 路径
     */
    @TableField("depPath")
    private String depPath;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 是否上级
     */
    @TableField("isParent")
    private Boolean isParent;

    @ApiModelProperty("子部门列表")
    @TableField(exist = false)
    private List<Department> children;

    @ApiModelProperty("返回结果，用于存储过程")
    @TableField(exist = false)
    private Integer result;

}
