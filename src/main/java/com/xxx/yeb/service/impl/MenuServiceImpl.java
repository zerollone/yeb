package com.xxx.yeb.service.impl;

import com.xxx.yeb.entity.Admin;
import com.xxx.yeb.entity.Menu;
import com.xxx.yeb.mapper.MenuMapper;
import com.xxx.yeb.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 通过用户id查询菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenuByAdminId() {
        Integer id = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        // 先从redis中去数据
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + id);
        // redis 中为空则从数据库中查询
        if (CollectionUtils.isEmpty(menus)){
            menus = menuMapper.getMenuByAdminId(id);
            // 将数据存入redis中
            valueOperations.set("menu_" + id, menus);
        }
        return menus;
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
