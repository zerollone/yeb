package com.xxx.yeb.controller;


import com.xxx.yeb.entity.RespBean;
import com.xxx.yeb.entity.Salary;
import com.xxx.yeb.service.SalaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
@RequestMapping("/salary/sob")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @ApiOperation(value = "获取所有工资账套")
    @GetMapping("/getAllSalary")
    public List<Salary> getAllSalary(){
        return salaryService.list();
    }

    @ApiOperation(value = "添加工资账套")
    @PostMapping("/addSalary")
    public RespBean addSalary(@RequestBody Salary salary){
        salary.setCreateDate(LocalDateTime.now());
        if (salaryService.save(salary)){
            return RespBean.success("账套添加成功！");
        }
        return RespBean.error("账套添加失败！");
    }

    @ApiOperation(value = "删除工资账套")
    @GetMapping("/delSalary/{id}")
    public RespBean delSalary(@PathVariable Integer id){
        if (salaryService.removeById(id)){
            return RespBean.success("账套删除成功！");
        }
        return RespBean.error("账套删除失败！");
    }

    @ApiOperation(value = "更新工资账套")
    @PostMapping("/updateSalary")
    public RespBean updateSalary(@RequestBody Salary salary){
        if (salaryService.updateById(salary)){
            return RespBean.success("账套更新成功！");
        }
        return RespBean.error("账套更新失败！");
    }
}

