package com.xxx.yeb.mapper;

import com.xxx.yeb.entity.Department;
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
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartment(Integer parentId);

    void add(Department department);

    void del(Department department);
}
