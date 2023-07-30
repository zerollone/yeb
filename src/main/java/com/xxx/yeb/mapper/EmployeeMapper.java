package com.xxx.yeb.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxx.yeb.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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
public interface EmployeeMapper extends BaseMapper<Employee> {

    IPage<Employee> getEmployee(Page<Employee> page, @Param("employee") Employee employee, @Param("beginDateScope") LocalDate[] beginDateScope);

    List<Employee> exportEmployee(Integer id);

    IPage<Employee> getAllEmployeeWithSalary(Page<Employee> page);
}
