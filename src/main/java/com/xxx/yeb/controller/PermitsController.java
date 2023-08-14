package com.xxx.yeb.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.yeb.entity.Menu;
import com.xxx.yeb.entity.MenuRole;
import com.xxx.yeb.entity.RespBean;
import com.xxx.yeb.entity.Role;
import com.xxx.yeb.service.MenuRoleService;
import com.xxx.yeb.service.MenuService;
import com.xxx.yeb.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限组
 */
@RestController
@RequestMapping("/system/basic/permits")
public class PermitsController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuRoleService menuRoleService;

    @ApiOperation(value = "获取所有角色")
    @GetMapping("/")
    public List<Role> select(){
        return roleService.list();
    }

    @ApiOperation(value = "添加角色")
    @PostMapping("/")
    public RespBean add(@RequestBody Role role){
        if (!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_" + role.getName());
        }
        if (roleService.save(role)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean delete(@PathVariable Integer rid){
        if (roleService.removeById(rid)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error("删除失败！");
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @ApiOperation(value = "根据角色id查询菜单id")
    @GetMapping("/mid/{rid}")
    public List<Integer> getMidByRid(@PathVariable Integer rid){
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid",rid)).stream().map(MenuRole::getMid).collect(Collectors.toList());
    }

    @ApiOperation(value = "更新角色菜单")
    @PutMapping("/updateMenuRole")
    public RespBean updateMenuRole(Integer rid, Integer[] mids){
        return menuRoleService.updateMenuRole(rid, mids);
    }
}
