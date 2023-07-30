package com.xxx.yeb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxx.yeb.entity.Employee;
import com.xxx.yeb.entity.RespBean;
import com.xxx.yeb.entity.RespPageBean;
import com.xxx.yeb.mapper.EmployeeMapper;
import com.xxx.yeb.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 获取所有员工，分页获得
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    @Override
    public RespPageBean getEmployee(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        // 开启分页
        Page<Employee> page = new Page<>(currentPage,size);
        IPage<Employee> employeeByPage = employeeMapper.getEmployee(page, employee, beginDateScope);
        return new RespPageBean(employeeByPage.getTotal(),employeeByPage.getRecords());
    }

    /**
     * 获取最大工号
     * @return
     */
    @Override
    public RespBean getMaxWorkId() {
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));
        String format = String.format("%08d", Integer.parseInt(maps.get(0).get("max(workID)").toString()) + 1);
        return RespBean.success(null,format);
    }

    /**
     * 添加员工信息
     * @param employee
     * @return
     */
    @Override
    public RespBean addEmployee(Employee employee) {
        // 处理合同期限，保留两位小数
        LocalDate beginContract = employee.getBeginContract();
        LocalDate endContract = employee.getEndContract();
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days/365.00)));
        if (employeeMapper.insert(employee) == 1){
            return RespBean.success("新员工添加成功！");
        }
        return RespBean.error("新员工添加失败！");
    }

    /**
     * 查询员工并导出到excel表
     * @param id
     * @return
     */
    @Override
    public List<Employee> exportEmployee(Integer id) {
        return employeeMapper.exportEmployee(id);
    }

    /**
     * 获取所有员工账套
     * @param currentPage
     * @param size
     * @return
     */
    @Override
    public RespPageBean getAllEmployeeWithSalary(Integer currentPage, Integer size) {
        // 开启分页
        Page<Employee> page = new Page<>(currentPage,size);
        IPage<Employee> employeeIPage = employeeMapper.getAllEmployeeWithSalary(page);
        RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(),employeeIPage.getRecords());
        return respPageBean;
    }
}
