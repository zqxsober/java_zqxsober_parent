package com.zqxsober.common.generator.generator;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

/**
 * @Author: zqxsober
 * @Description: MySqlTypeConvertCustom 类
 * @Date: 2021-12-30 10:16
 */
public class MySqlTypeConvertCustom extends MySqlTypeConvert implements ITypeConvert {

    final String tinyint = "tinyint(1)";

    @Override
    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
        String t = fieldType.toLowerCase();
        if (t.contains(tinyint)) {
            return DbColumnType.INTEGER;
        }
        return super.processTypeConvert(globalConfig, fieldType);
    }
}
