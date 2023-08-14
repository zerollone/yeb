package com.xxx.yeb.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.yeb.entity.Menu;
import com.xxx.yeb.entity.MenuRole;
import com.xxx.yeb.entity.Position;
import com.xxx.yeb.entity.RespBean;
import com.xxx.yeb.service.MenuRoleService;
import com.xxx.yeb.service.MenuService;
import com.xxx.yeb.service.PositionService;
import com.xxx.yeb.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Autowired
    private PositionService positionService;

    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/")
    public List<Position> getAllPosition(){
        return positionService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/")
    public RespBean insert(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public RespBean update(@RequestBody Position position){
        if (positionService.updateById(position)){
            return RespBean.success("信息更改成功！");
        }
        return RespBean.error("信息更改失败！");
    }

    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean delete(@PathVariable Integer id){
        if (positionService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "批量删除职位")
    @DeleteMapping("/")
    public RespBean deleteMore(Integer[] ids){
        if (positionService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }
}

