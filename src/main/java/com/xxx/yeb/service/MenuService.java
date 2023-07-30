package com.xxx.yeb.service;

import com.xxx.yeb.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
public interface MenuService extends IService<Menu> {

    List<Menu> getMenuByAdminId();

    List<Menu> getMenusWithRoles();

    List<Menu> getAllMenus();
}
