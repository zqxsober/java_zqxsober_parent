package com.zqxsober.gateway.factory;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 权限的网关校验过滤器（针对于部分路由，非全局过滤器）
 * <p>
 * ps: GatewayFilterFactory结尾的过滤器可以在application.yml中配置
 *
 * @author zqxsoebr
 * @Date 2023年09月21日16:56:55
 */

/**
 * @Author: zqxsober
 * @Description: AppApplication 类，GatewayFilterFactory结尾的过滤器可以在application.yml中配置
 * 权限的网关校验过滤器（针对于部分路由，非全局过滤器）
 * @Date: 2023年09月21日16:56:55
 */
@Component
public class PermissionGatewayFilterFactory extends AbstractGatewayFilterFactory {


    private static final String SWAGGER_PATH = "api-docs";

    @Override
    public GatewayFilter apply(Object config) {

        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 1,获取当前请求路径，如果为登录请求，则直接放行
            String requestPath = request.getPath().toString();
            if (StrUtil.containsAny(requestPath, SWAGGER_PATH)) {
                return chain.filter(exchange.mutate().request(request).build());
            }

            // 2,放行request
            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    public static void main(String[] args) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        String password = UUID.fastUUID().toString(false);
        System.out.println(password);
        encryptor.setPassword(password);
        String zqxsober = encryptor.encrypt("zqxsober");
        System.out.println(zqxsober);
        String str = "zqxsober";
        System.out.println(SHA256(str));
    }

    public static String SHA256(String str) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    public static String byte2Hex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        String temp;
        for (byte aByte : bytes) {
            temp = Integer.toHexString(aByte & 0xFF);
            if (temp.length() == 1) {
                builder.append("0");
            }
            builder.append(temp);
        }
        return builder.toString();
    }
}
