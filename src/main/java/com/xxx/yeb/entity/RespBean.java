package com.xxx.yeb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回对象
 */
@Data
@NoArgsConstructor      // 无参构造
@AllArgsConstructor     // 全参构造
public class RespBean {
    private long code;
    private String message;
    private Object obj;

    /**
     * 成功后返回结果
     *
     * @param message
     * @return
     */
    public static RespBean success(String message){
        return new RespBean(200,message,null);
    }

    /**
     * 成功后返回结果，带有对象数据
     * @param message
     * @param obj
     * @return
     */
    public static RespBean success(String message,Object obj){
        return new RespBean(200,message,obj);
    }

    /**
     * 失败后返回结果
     * @param message
     * @return
     */
    public static RespBean error(String message){
        return  new RespBean(500,message,null);
    }

    /**
     * 失败后返回结果，带有对象信息
     * @param message
     * @return
     */
    public static RespBean error(String message, Object obj){
        return  new RespBean(500,message,obj);
    }
}
