package com.jt.plt.product.util;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
* 描述：计算工具类
* 类名称：MathUtil.java
* 作者： wangyang  
* 版本：1.0 
* 修改： 2018/5/11 16:00
* 创建日期： 2018/5/11 16:00
* 版权：江泰保险经纪股份有限公司
*/
public class MathUtil {
    /**
     * 转换Object对象为BigDecimal类型
     * @param value 被转换的值
     * @return
     */
    public static BigDecimal getBigDecimal(Object value ) {
        BigDecimal ret = null;
        if( value != null ) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof String ) {
                ret = new BigDecimal( (String) value );
            } else if( value instanceof BigInteger) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
            }
        }
        return ret;
    }
}
