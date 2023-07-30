package com.xxx.yeb.mapper;

import com.xxx.yeb.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> getMenuByAdminId(Integer id);

    List<Menu> getAll();

    List<Menu> getMenusWithRoles();

    List<Menu> getAllMenus();
}
