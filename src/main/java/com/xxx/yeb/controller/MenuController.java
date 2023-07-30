package com.xxx.yeb.controller;


import com.xxx.yeb.entity.Menu;
import com.xxx.yeb.service.AdminService;
import com.xxx.yeb.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/system/config")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "通过用户id查询菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenuByAdminId(){
        return menuService.getMenuByAdminId();
    }
}

