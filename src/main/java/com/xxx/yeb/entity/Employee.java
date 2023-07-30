package com.xxx.yeb.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.access.prepost.PostInvocationAdviceProvider;

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
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 员工编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工姓名
     */
    @Excel(name = "员工姓名")               // 使用EasyPOI导出到excel表中使用的注解，name字段表示excel中的列名
    private String name;

    /**
     * 性别
     */
    @Excel(name = "性别")
    private String gender;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "出生日期",width = 20,format = "yyyy-MM-dd")
    private LocalDate birthday;

    /**
     * 身份证号
     */
    @TableField("idCard")
    @Excel(name = "身份证号")
    private String idCard;

    /**
     * 婚姻状况
     */
    @Excel(name = "婚姻状况")
    private String wedlock;

    /**
     * 民族
     */
    @TableField("nationId")
    private Integer nationId;

    /**
     * 籍贯
     */
    @TableField("nativePlace")
    @Excel(name = "籍贯")
    private String nativePlace;

    /**
     * 政治面貌
     */
    @TableField("politicId")
    private Integer politicId;

    /**
     * 邮箱
     */
    @Excel(name = "邮箱")
    private String email;

    /**
     * 电话号码
     */
    @Excel(name = "电话号码")
    private String phone;

    /**
     * 联系地址
     */
    @Excel(name = "联系地址")
    private String address;

    /**
     * 所属部门
     */
    @TableField("departmentId")
    private Integer departmentId;

    /**
     * 职称ID
     */
    @TableField("jobLevelId")
    private Integer jobLevelId;

    /**
     * 职位ID
     */
    @TableField("posId")
    private Integer posId;

    /**
     * 聘用形式
     */
    @TableField("engageForm")
    @Excel(name = "聘用方式")
    private String engageForm;

    /**
     * 最高学历
     */
    @TableField("tiptopDegree")
    @Excel(name = "最好学历")
    private String tiptopDegree;

    /**
     * 所属专业
     */
    @Excel(name = "所属专业")
    private String specialty;

    /**
     * 毕业院校
     */
    @Excel(name = "毕业院校")
    private String school;

    /**
     * 入职日期
     */
    @TableField("beginDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "入职日期")
    private LocalDate beginDate;

    /**
     * 在职状态
     */
    @TableField("workState")
    @Excel(name = "在职状态")
    private String workState;

    /**
     * 工号
     */
    @TableField("workID")
    @Excel(name = "工号")
    private String workID;

    /**
     * 合同期限
     */
    @TableField("contractTerm")
    @Excel(name = "合同期限",suffix = "年")
    private Double contractTerm;

    /**
     * 转正日期
     */
    @TableField("conversionTime")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "转正日期")
    private LocalDate conversionTime;

    /**
     * 离职日期
     */
    @TableField("notWorkDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "离职日期")
    private LocalDate notWorkDate;

    /**
     * 合同起始日期
     */
    @TableField("beginContract")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "合同起始日期")
    private LocalDate beginContract;

    /**
     * 合同终止日期
     */
    @TableField("endContract")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    @Excel(name = "合同终止日期")
    private LocalDate endContract;

    /**
     * 工龄
     */
    @TableField("workAge")
    @Excel(name = "工龄")
    private Integer workAge;

    /**
     * 工资账套ID
     */
    @TableField("salaryId")
    private Integer salaryId;


    @ApiModelProperty(value = "民族")
    @TableField(exist = false)
    @ExcelEntity(name = "民族")                 // 当参数是对象时使用的注解，同时需要跳转到对象类添加@Excel注解
    private Nation nation;

    @ApiModelProperty(value = "政治面貌")
    @TableField(exist = false)
    @ExcelEntity(name = "政治面貌")
    private PoliticsStatus politicsStatus;

    @ApiModelProperty(value = "部门")
    @TableField(exist = false)
    @ExcelEntity(name = "部门")
    private Department department;

    @ApiModelProperty(value = "职称")
    @TableField(exist = false)
    @ExcelEntity(name = "职称")
    private Joblevel joblevel;

    @ApiModelProperty(value = "职位")
    @TableField(exist = false)
    @ExcelEntity(name = "职位")
    private Position position;

    @ApiModelProperty(value = "账套")
    @TableField(exist = false)
    private Salary salary;

}






