package com.xxx.yeb.mapper;

import com.xxx.yeb.entity.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
@Repository
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    Integer insertRecord(@Param("rid") Integer rid, @Param("mids") Integer[] mids);
}
