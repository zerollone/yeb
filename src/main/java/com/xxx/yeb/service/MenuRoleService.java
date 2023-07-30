package com.xxx.yeb.service;

import com.xxx.yeb.entity.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.yeb.entity.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
public interface MenuRoleService extends IService<MenuRole> {

    RespBean updateMenuRole(Integer rid, Integer[] mids);
}
