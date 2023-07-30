package com.xxx.yeb.config;

import com.xxx.yeb.entity.Menu;
import com.xxx.yeb.entity.Role;
import com.xxx.yeb.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 权限控制
 * 根据请求url分析请求所需的角色
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();   // ???

    /**
     * 获取的url存放的object
     * @param o
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取请求的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<Menu> menusWithRoles = menuService.getMenusWithRoles();
        for (Menu menu : menusWithRoles){
            // 判断请求的url与菜单角色是否相配
            if (antPathMatcher.match(menu.getUrl(),requestUrl)){
                String[] strings = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                return SecurityConfig.createList(strings);
            }
        }
        // 没匹配的ur默认登录即可访问，普通用户。
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
