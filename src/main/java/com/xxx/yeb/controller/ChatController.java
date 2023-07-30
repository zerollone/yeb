package com.xxx.yeb.controller;

import com.xxx.yeb.entity.Admin;
import com.xxx.yeb.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 在线聊天
 */
@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "获取所有可聊天的对象")
    @GetMapping("/admin")
    public List<Admin> getAllAdmins(String keywords){
        return adminService.getAllAdmins(keywords);
    }
}
