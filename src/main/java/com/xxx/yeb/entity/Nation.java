package com.xxx.yeb.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

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
public class Nation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 民族
     */
    @Excel(name = "民族")
    @NonNull
    private String name;


}
