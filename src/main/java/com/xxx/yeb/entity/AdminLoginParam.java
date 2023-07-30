package com.xxx.yeb.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户登录实体类，接收前端用户登录信息，不使用Admin类的原因是Admin有很多其他东西用不上
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AdminLogin对象",description = "")      // swagger注解
public class AdminLoginParam {

    @ApiModelProperty(value = "用户名", required = true)   // swagger注解，required 表示为是否为必填项
    private String username;
    @ApiModelProperty(value = "密码", required = true)   // swagger注解，required 表示为是否为必填项
    private String password;
    @ApiModelProperty(value = "验证码", required = true)
    private String code;
}
