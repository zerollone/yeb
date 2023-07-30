package com.xxx.yeb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xxx.yeb.entity.Employee;
import com.xxx.yeb.entity.RespBean;
import com.xxx.yeb.entity.RespPageBean;
import com.xxx.yeb.entity.Salary;
import com.xxx.yeb.service.EmployeeService;
import com.xxx.yeb.service.SalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工账套
 */
@RestController
@RequestMapping("/salary/sobcfg")
public class SalaryStaffController {

    @Autowired
    private SalaryService salaryService;
    @Autowired
    private EmployeeService employeeService;

    @ApiOperation("获取所有工资账套")
    @GetMapping("/selectSalary")
    public List<Salary> selectSalary(){
        return salaryService.list();
    }

    @ApiOperation(value = "获取所有员工账套")
    @GetMapping("/getAllEmployeeWithSalary")
    public RespPageBean getAllEmployeeWithSalary(@RequestParam(defaultValue = "1") Integer currentPage,
                                                 @RequestParam(defaultValue = "10") Integer size){
        return employeeService.getAllEmployeeWithSalary(currentPage, size);
    }

    @ApiOperation(value = "更新员工账套")
    @PostMapping("/updateEmployeeSalary")
    public RespBean updateEmployeeSalary(Integer eid, Integer sid){
        if (employeeService.update(new UpdateWrapper<Employee>().set("salaryId", sid).eq("id",eid))){
            return RespBean.success("员工账套更新成功！");
        }
        return RespBean.error("员工账套更新失败！");
    }
}
