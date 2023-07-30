package com.xxx.yeb.mapper;

import com.xxx.yeb.entity.Role;
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
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRolesByUserId(Integer userId);
}
