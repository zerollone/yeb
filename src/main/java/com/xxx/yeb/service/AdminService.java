package com.xxx.yeb.service;

import com.xxx.yeb.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.yeb.entity.Menu;
import com.xxx.yeb.entity.RespBean;
import com.xxx.yeb.entity.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
public interface AdminService extends IService<Admin> {
    RespBean login(String username, String password,String Code, HttpServletRequest request);

    Admin getAdminByUsername(String username);

    List<Role> getRolesByUserId(Integer userId);

    List<Admin> getAllAdmins(String keywords);

    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    RespBean updateAdminPassword(String oldPass, String pass, Integer adminId);
}
