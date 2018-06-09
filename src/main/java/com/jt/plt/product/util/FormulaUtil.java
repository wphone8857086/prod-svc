package com.jt.plt.product.util;

import org.apache.commons.jexl3.JexlContext;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.MapContext;
import org.apache.commons.jexl3.internal.Engine;


import java.math.BigDecimal;
import java.util.Map;


/**
 * 描述：Gson Util 
 * 作者： wephone 
 * 创建日期： 2018年3月8日 下午2:21:18 
 * 版权：江泰保险经纪股份有限公司
 */
public class FormulaUtil {

	// 将Json数据解析成相应的映射对象
	/*public static <T> T parseJsonWithGson(String jsonData, Class<T> type) {
		Gson gson = new Gson();
		T result = gson.fromJson(jsonData, type);
		return result;
	}*/
    
	// 判断string类型能否转为double
	@SuppressWarnings("unused")
	public static boolean pandun(String str) {
		boolean ret = true;
		try {
			double d = Double.parseDouble(str);
			ret = true;
		} catch (Exception ex) {
			ret = false;
		}
		return ret;
	}
	//记录保费计算公式中因子的个数
	
	public static int countFactor(String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '*') {
				count++;
			}
		}
		return count;
	}

	/**
	 * 判断字符串是否以字符串a开头
	 */

	public static  Boolean  serarchA(String str){
		Boolean flag = true;
		String a = "[";
		String substring = str.substring(0, 1);
		if(a.equals(substring)){
			flag =true;
		}else{
			 flag= false;
		}
		return  flag;
	}
	/**
	 * 将字符串转换成可执行代码
	 *
	 * @param jexlExp
	 * @param map
	 * @return
	 */
	public static Object convertToCode(String jexlExp, Map<String, BigDecimal> map) {
		// JexlEngine 创建表达式
		JexlEngine jexl = new Engine();
		JexlExpression e = jexl.createExpression(jexlExp);
		// JexlContext 存放参数
		JexlContext jc = new MapContext();
		// 获取map集合的所有键的set集合
		for (String key : map.keySet()) {
			jc.set(key, map.get(key));
		}
		// 对这个JexlExpression对象求值，传入执行JEXL表达式的上下文环境对象
		return e.evaluate(jc);
	}

}
