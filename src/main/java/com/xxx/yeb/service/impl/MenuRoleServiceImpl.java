package com.xxx.yeb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.yeb.entity.MenuRole;
import com.xxx.yeb.entity.RespBean;
import com.xxx.yeb.mapper.MenuRoleMapper;
import com.xxx.yeb.service.MenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements MenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;
    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    @Override
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if (mids == null || mids.length == 0){
            return RespBean.success("更新成功！");
        }
        Integer result = menuRoleMapper.insertRecord(rid,mids);
        if (result == mids.length){
            return RespBean.success("更新成功！");
        }
        return RespBean.error("更新失败！");
    }
}
