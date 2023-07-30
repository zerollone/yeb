package com.xxx.yeb.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xxx.yeb.config.CustomAuthorityDeserializerConfig;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Admin implements Serializable, UserDetails { // UserDetails是什么？有什么用？与springSecurity结合，判断登录是否有效

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 住宅电话
     */
    private String telephone;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 是否启用
     */
    @Getter(AccessLevel.NONE)          // 不启动lombok的自动get方法，因为下面重写了该方法
    private Boolean enabled;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户头像
     */
    @TableField("userFace")
    private String userFace;

    /**
     * 备注
     */
    private String remark;

    /**
     * 角色
     */
    @ApiModelProperty(value = "角色")
    @TableField(exist = false)
    private List<Role> roles;


    /**
     * 权限
     * @return
     */
    @Override
    @JsonDeserialize(using = CustomAuthorityDeserializerConfig.class)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return simpleGrantedAuthorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
