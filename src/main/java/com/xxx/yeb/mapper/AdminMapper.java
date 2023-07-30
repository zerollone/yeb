package com.xxx.yeb.mapper;

import com.xxx.yeb.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxx.yeb.entity.Menu;
import org.apache.ibatis.annotations.Param;
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
public interface AdminMapper extends BaseMapper<Admin> {

    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);
}
