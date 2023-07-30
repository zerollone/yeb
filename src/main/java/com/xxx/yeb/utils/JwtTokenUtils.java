package com.xxx.yeb.utils;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken 工具类
 */
@Component
public class JwtTokenUtils {

    // JWT荷载用户名
    private static final String CLAIM_KEY_USERNAME = "sub";
    // JWT创建时间
    private static final String CLAIM_KEY_CREATED = "created";
    // JWT密钥
    @Value("${jwt.secret}")
    private String secret;
    // JWT失效时间
    @Value("${jwt.expiration}")
    private Long expiration;


    /**
     * 1、根据用户信息生成token
     *
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        // 准备荷载
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 2、从token中获取登录的用户名
     *
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            // 从token中获取荷载
            Claims claims = getClaimsFromToken(token);
            // 得到用户名
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 3、验证token是否有效
     *
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 4、判断token是否可以被刷新，过期就刷新
     *
     * @param token
     * @return
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 5、刷新token，根据原token生成新的时间
     *
     * @param token
     * @return
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }


    /**
     * 判断token是否失效，不在指定的时间段内
     *
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        return expireDate.before(new Date());
    }

    /**
     * 从token中获取生成时间
     *
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }


    /**
     * 从token中获取荷载
     *
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret)     // 密钥
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 根据荷载生成 JWT Token
     *
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)      // 设置荷载
                .setExpiration(generateExpirationDate())        // 失效时间
                .signWith(SignatureAlgorithm.HS512, secret)      // 还有 ES512
                .compact();
    }

    /**
     * 生成Token失效时间
     *
     * @return
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

}
