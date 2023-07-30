package com.xxx.yeb.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Joblevel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 职称名称
     */
    @Excel(name = "职称")
    @NonNull
    private String name;

    /**
     * 职称等级
     */
    @TableField("titleLevel")
    private String titleLevel;

    /**
     * 创建时间
     */
    @TableField("createDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private LocalDateTime createDate;

    /**
     * 是否启用
     */
    private Boolean enabled;


}
