package com.xxx.yeb.service;

import com.xxx.yeb.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.yeb.entity.RespBean;
import com.xxx.yeb.entity.RespPageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
public interface EmployeeService extends IService<Employee> {

    RespPageBean getEmployee(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    RespBean getMaxWorkId();

    RespBean addEmployee(Employee employee);

    List<Employee> exportEmployee(Integer id);

    RespPageBean getAllEmployeeWithSalary(Integer currentPage, Integer size);
}
