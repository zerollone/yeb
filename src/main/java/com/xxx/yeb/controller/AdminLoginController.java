package com.xxx.yeb.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.xxx.yeb.entity.Admin;
import com.xxx.yeb.entity.AdminLoginParam;
import com.xxx.yeb.entity.RespBean;
import com.xxx.yeb.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Principal;

/**
 * 登录
 */
@Api(tags = "AdminLoginController")     // swagger注解
@RestController
public class AdminLoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @ApiOperation(value = "登录之后放回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }

    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if (principal == null){
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUsername(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRolesByUserId(admin.getId()));
        return admin;
    }

    @ApiOperation(value = "注销登录")   // 补进，后端也要将用户的相应信息删除
    @GetMapping("/logout")
    public RespBean logout(){
        return RespBean.success("注销成功！");
    }

    @ApiOperation(value = "验证码")
    @GetMapping(value = "/captcha", produces = "image/jpeg")
    public void captcha(HttpServletRequest request, HttpServletResponse response){
        // 定义response输出类型为image/jpeg类型
        response.setDateHeader("Expires",0);
        // set standard HTTP/1.1 no-cache headers
        response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
        // set IE extended HTTP/1.1 no-cache headers (user addHeader)
        response.addHeader("Cache-Control","post-check=0, pre-check=0");
        // set standard HTTP/1.1 no-cache header
        response.setHeader("Pragma","no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");

        // -------------- 生成验证码 --------------
        // 获取验证码文本内容
        String text = defaultKaptcha.createText();
        System.out.println("验证码内容" + text);
        // 将验证码文本内容放入session
        request.getSession().setAttribute("captcha",text);
        // 根据文本验证码内容创建图形验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            // 输出流输出图片，格式为jpg
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    @PostMapping("/abc")
    public void abc(HttpServletRequest request){
        System.out.println("///-----------**************--------------");
        System.out.println(request.getSession().getAttribute("captcha"));
    }
}
