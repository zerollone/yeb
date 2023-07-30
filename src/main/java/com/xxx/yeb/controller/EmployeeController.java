package com.xxx.yeb.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.xxx.yeb.entity.*;
import com.xxx.yeb.service.*;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
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
@RequestMapping("/employee/basic/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PoliticsStatusService politicsStatusService;
    @Autowired
    private JoblevelService joblevelService;
    @Autowired
    private NationService nationService;
    @Autowired
    private PositionService positionService;
    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "获取所有员工，分页获得")
    @GetMapping("/select")
    public RespPageBean getEmployee(@RequestParam(defaultValue = "1") Integer currentPage,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    Employee employee,
                                    LocalDate[] beginDateScope){
        return employeeService.getEmployee(currentPage,size,employee,beginDateScope);
    }

    @ApiOperation(value = "获取所有政治面貌")
    @GetMapping("/getPoliticsStatus")
    public List<PoliticsStatus> getPoliticsStatus(){
        return politicsStatusService.list();
    }

    @ApiOperation(value = "获取所有职称")
    @GetMapping("/getJobLevel")
    public List<Joblevel> getJobLevel(){
        return joblevelService.list();
    }

    @ApiOperation(value = "获取所有民族")
    @GetMapping("/getNation")
    public List<Nation> getNation(){
        return nationService.list();
    }

    @ApiOperation(value = "获取所有职位")
    @GetMapping("/getPosition")
    public List<Position> getPosition(){
        return positionService.list();
    }

    @ApiOperation(value = "获取所有部门")
    @GetMapping("/getDepartment")
    public List<Department> getDepartment(){
        return departmentService.getAllDepartment();
    }

    @ApiOperation(value = "获取最大工号")
    @GetMapping("/getMaxWorkId")
    public RespBean getMaxWorkId(){
        return employeeService.getMaxWorkId();
    }

    @ApiOperation(value = "添加员工")
    @PostMapping("/addEmployee")
    public RespBean addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @ApiOperation(value = "更新员工")
    @PostMapping("/updateEmp")
    public RespBean updateEmp(@RequestBody Employee employee){
        if (employeeService.updateById(employee)){
            return RespBean.success("员工信息更新成功！");
        }
        return RespBean.error("员工信息更新失败！");
    }

    @ApiOperation(value = "删除员工")
    @GetMapping("/deleteEmp/{id}")
    public RespBean deleteEmp(@PathVariable Integer id){
        if (employeeService.removeById(id)){
            return RespBean.success("删除员工成功！");
        }
        return RespBean.error("删除员工失败！");
    }

    @ApiOperation(value = "导出员工数据，到excel表")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response){
        List<Employee> employeeList = employeeService.exportEmployee(null);
        /**
         * 导出数据到excel表
         * 第一个参数表示导出后数据表的名字
         * 第二个参数表示excel表中不同表的名字
         * 第三个参数表示excel类型，分为03版（HSSF，后缀为xls）和07版（XSSF，后缀为xlsx）
         */
        ExportParams params = new ExportParams("员工表","员工表", ExcelType.HSSF);
        // 导出工具类
        Workbook workbook = ExcelExportUtil.exportExcel(params,Employee.class,employeeList);
        ServletOutputStream outputStream = null;
        try {
            // 流形式
            response.setHeader("content-type", "application/octet-stream");
            // 防止中文乱码
            response.setHeader("content-disposition","attachment;filename=" + URLEncoder.encode("员工表.xls","UTF-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 不太懂，需要重新梳理，重新看
     * @param file
     * @return
     */
    @ApiOperation(value = "导入员工数据，从excel表")
    @PostMapping("/import")
    public RespBean importEmployee(MultipartFile file){
        ImportParams params = new ImportParams();
        // 去掉标题行
        params.setTitleRows(1);
        List<Nation> nationList = nationService.list();
        List<PoliticsStatus> politicsStatusList = politicsStatusService.list();
        List<Department> departmentList = departmentService.list();
        List<Joblevel> joblevelList = joblevelService.list();
        List<Position> positionList = positionService.list();
        try {
            List<Employee> employeeList = ExcelImportUtil.importExcel(file.getInputStream(), Employee.class, params);
            employeeList.forEach(employee -> {
                // 民族id
                employee.setNationId(nationList.get(nationList.indexOf(new Nation(employee.getNation().getName()))).getId());
                // 政治面貌id
                employee.setPoliticId(politicsStatusList.get(politicsStatusList.indexOf(new PoliticsStatus(employee.getPoliticsStatus().getName()))).getId());
                // 部门id
                employee.setDepartmentId(departmentList.get(departmentList.indexOf(new Department(employee.getDepartment().getName()))).getId());
                // 职称id
                employee.setJobLevelId(joblevelList.get(joblevelList.indexOf(new Joblevel(employee.getJoblevel().getName()))).getId());
                // 职位id
                employee.setPosId(positionList.get(positionList.indexOf(new Position(employee.getPosition().getName()))).getId());
            });
            if (employeeService.saveBatch(employeeList)){
                return RespBean.success("导入成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RespBean.error("导入失败！");
    }
}


















