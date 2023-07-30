package com.xxx.yeb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
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
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * url
     */
    private String url;

    /**
     * path
     */
    private String path;

    /**
     * 组件
     */
    private String component;

    /**
     * 菜单名
     */
    private String name;

    /**
     * 图标
     */
    @TableField("iconCls")
    private String iconCls;

    /**
     * 是否保持激活
     */
    @TableField("keepAlive")
    private Boolean keepAlive;

    /**
     * 是否要求权限
     */
    @TableField("requireAuth")
    private Boolean requireAuth;

    /**
     * 父id
     */
    @TableField("parentId")
    private Integer parentId;

    /**
     * 是否启用
     */
    private Boolean enabled;

    @ApiModelProperty(value = "子菜单")
    @TableField(exist = false)
    private List<Menu> children;

    /**
     * 根据访问的url判断角色
     * 根据菜单里url查询那些角色有这个权限
     */
    @ApiModelProperty(value = "角色列表")
    @TableField(exist = false)
    private List<Role> roles;





}
