package com.xxx.yeb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.yeb.entity.*;
import com.xxx.yeb.mapper.AdminMapper;
import com.xxx.yeb.mapper.AdminRoleMapper;
import com.xxx.yeb.mapper.RoleMapper;
import com.xxx.yeb.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxx.yeb.utils.JwtTokenUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ws
 * @since 2022-08-10
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;        // 密码工具
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 登录之后返回token
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code) || !captcha.equals(code)){
            return RespBean.error("验证码输入错误，请重新输入");
        }
        // 判断登录
        // 通过给定的用户名从数据源中加载用户的详细信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails == null || !passwordEncoder.matches(password, userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()) {
            return RespBean.error("账号被禁用，请联系管理员！");
        }

        //更新springSecurity登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities()); // 第二参数是密码凭证，一般不放，第三是权限列表
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 生成token
        String token = jwtTokenUtils.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功", tokenMap);
    }

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username).eq("enabled",true));
    }

    /**
     * 根据用户Id查询用户所属角色
     * @param userId
     * @return
     */
    @Override
    public List<Role> getRolesByUserId(Integer userId) {
        return roleMapper.getRolesByUserId(userId);
    }

    /**
     * 获取所有操作员，除去当前登录用户的所有操作员
     * @param keywords
     * @return
     */
    @Override
    public List<Admin> getAllAdmins(String keywords) {
        return adminMapper.getAllAdmins(((Admin)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(),keywords);
    }

    /**
     * 更新操作员的角色
     * @param adminId
     * @param rids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        QueryWrapper<AdminRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("adminId",adminId);
        adminRoleMapper.delete(queryWrapper);
//        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId", adminId));
        // result 得到的结果是受影响的行数的数字
        Integer result = adminRoleMapper.updateAdminRole(adminId,rids);
        if (result == rids.length){
            return RespBean.success("操作员角色更新成功！");
        }
        return RespBean.error("操作员角色更新失败！");
    }

    /**
     * 修改当前登录用户的密码
     * @param oldPass
     * @param pass
     * @param adminId
     * @return
     */
    @Override
    public RespBean updateAdminPassword(String oldPass, String pass, Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldPass,admin.getPassword())){
            admin.setPassword(encoder.encode(pass));
            int result = adminMapper.updateById(admin);
            if (result == 1){
                return RespBean.success("密码修改成功！");
            }
        }
        return RespBean.error("密码修改失败！");
    }


}
