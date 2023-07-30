package com.xxx.yeb.mapper;

import com.xxx.yeb.entity.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.yeb.entity.RespBean;
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
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    Integer updateAdminRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
