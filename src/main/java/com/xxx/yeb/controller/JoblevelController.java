package com.xxx.yeb.controller;


import com.xxx.yeb.entity.Joblevel;
import com.xxx.yeb.entity.RespBean;
import com.xxx.yeb.service.JoblevelService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
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
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Autowired
    private JoblevelService joblevelService;

    @ApiOperation(value = "查询所有职称")
    @GetMapping("/")
    public List<Joblevel> select(){
        return joblevelService.list();
    }

    @ApiOperation(value = "添加职称")
    @PostMapping("/")
    public RespBean add(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        if (joblevelService.save(joblevel)){
            return RespBean.success("职称添加成功！");
        }
        return RespBean.error("职称添加失败！");
    }

    @ApiOperation(value = "更改职称信息")
    @PutMapping("/")
    public RespBean update(@RequestBody Joblevel joblevel){
        if (joblevelService.updateById(joblevel)){
            return RespBean.success("职位信息更新成功！");
        }
        return RespBean.error("职位信息更新失败！");
    }

    @ApiOperation(value = "删除职称信息")
    @DeleteMapping("/{id}")
    public RespBean delete(@PathVariable Integer id){
        if (joblevelService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/")
    public RespBean deleteAll(Integer[] ids){
        if (joblevelService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}

