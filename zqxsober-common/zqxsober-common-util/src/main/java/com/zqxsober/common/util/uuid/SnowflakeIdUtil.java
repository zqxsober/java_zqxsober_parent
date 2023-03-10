package com.zqxsober.common.util.uuid;

import cn.hutool.core.lang.Snowflake;

/**
 * @Author: zqxsober
 * @Description: SnowflakeIdUtil 类
 * @Date: 2022-08-09 09:36
 */
public class SnowflakeIdUtil {

    /**
     * 获取下一个id-long类型
     * @return      下一个id
     */
    public static long nextLongId() {
        Snowflake snowflake = new Snowflake();
        return snowflake.nextId();
    }

    /**
     * 获取下一个id-String类型
     * @return      下一个id
     */
    public static String nextStrId() {
        Snowflake snowflake = new Snowflake();
        return snowflake.nextIdStr();
    }
}
