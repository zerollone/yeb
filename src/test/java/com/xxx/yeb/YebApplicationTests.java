package com.xxx.yeb;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxx.yeb.entity.Menu;
import com.xxx.yeb.entity.MenuRole;
import com.xxx.yeb.mapper.AdminMapper;
import com.xxx.yeb.mapper.MenuMapper;
import com.xxx.yeb.mapper.MenuRoleMapper;
import com.xxx.yeb.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class YebApplicationTests {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private MenuRoleMapper menuRoleMapper;

    @Test
    void contextLoads() {
    }
    @Test
    void testDB(){
        List<Menu> list = menuMapper.getAll();
        System.out.println(list);
    }

    @Test
    void testInsert(){
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",3));
        Integer[] integers = new Integer[]{7};
        Integer res = menuRoleMapper.insertRecord(3,integers);
        System.out.println(res);
    }

    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminMapper adminMapper;
    @Test
    void testAdmin(){
        System.out.println(adminMapper.getAllAdmins(1,"华"));
    }

}
