package com.xxx.yeb.controller;


import com.xxx.yeb.entity.Department;
import com.xxx.yeb.entity.RespBean;
import com.xxx.yeb.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @ApiOperation("获取所有部门")
    @GetMapping("/select")
    public List<Department> select(){
        return departmentService.getAllDepartment();
    }

    @ApiOperation("添加部门，使用存储过程")
    @PostMapping("/add")
    public RespBean add(@RequestBody Department department){
        return departmentService.add(department);
    }

    @ApiOperation("删除部门，使用存储过程")
    @GetMapping("/del/{id}")
    public RespBean del(@PathVariable Integer id){
        return departmentService.del(id);
    }
}

