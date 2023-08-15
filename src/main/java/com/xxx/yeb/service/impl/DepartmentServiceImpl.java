package com.xxx.yeb.service.impl;

import com.baomidou.mybatisplus.generator.config.IFileCreate;
import com.xxx.yeb.entity.Department;
import com.xxx.yeb.entity.RespBean;
import com.xxx.yeb.mapper.DepartmentMapper;
import com.xxx.yeb.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 获取所有部门
     * @return
     */
    @Override
    public List<Department> getAllDepartment() {
        return departmentMapper.getAllDepartment(-1);
    }

    /**
     * 添加部门
     * @param department
     * @return
     */
    @Override
    public RespBean add(Department department) {
        department.setEnabled(true);
        departmentMapper.add(department);
        // 获取存储过程的结果
        if (department.getResult() == 1 ){
            return RespBean.success("添加成功", department);
        }
        return RespBean.error("添加失败！");
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @Override
    public RespBean del(Integer id) {
        // 应该删除存储过程使用了result作为返回值，所以使用Department对象进行删除
        Department department = new Department();
        department.setId(id);
        departmentMapper.del(department);
        if (department.getResult() == -2){
            return RespBean.error("该部门下还有子部门，删除失败！");
        }
        if (department.getResult() == -1){
            return RespBean.error("该部门下还有员工，删除失败！");
        }
        if (department.getResult() == 1){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}
