package com.xxx.yeb.service;

import com.xxx.yeb.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.yeb.entity.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
public interface DepartmentService extends IService<Department> {

    List<Department> getAllDepartment();

    RespBean add(Department department);

    RespBean del(Integer id);
}
