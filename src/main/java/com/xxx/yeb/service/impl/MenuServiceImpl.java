package com.xxx.yeb.service.impl;

import com.xxx.yeb.entity.Admin;
import com.xxx.yeb.entity.Menu;
import com.xxx.yeb.mapper.MenuMapper;
import com.xxx.yeb.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 通过用户id查询菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenuByAdminId() {
        return menuMapper.getMenuByAdminId(((Admin)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

    /**
     * 根据角色获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusWithRoles() {
        return menuMapper.getMenusWithRoles();
    }

    /**
     * 查询所有菜单
     * @return
     */
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

}
