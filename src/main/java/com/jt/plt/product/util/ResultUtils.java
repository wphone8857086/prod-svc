package com.jt.plt.product.util;

import com.jt.plt.product.enums.PremiumFormulaEnum;
import com.jt.plt.product.enums.ProductConfigBeanEnum;
import com.jt.plt.product.enums.ResultEnum;

/**
 * 
 * @author 可达鸭
 * 描述: 响应体工具类
 * @date 2017年11月23日 下午1:03:55
 */
public class ResultUtils {
		
	 	public static <T> ResultVO<T> success(T data) {
	        return new ResultVO<T>(ResultEnum.SUCCESS, data);
	    }
	    public static ResultMsg warnMsg(String msg) {
	    	ResultMsg resultMsg = new ResultMsg();
	    	resultMsg.setCode(500);
	    	resultMsg.setMsg(msg);
	        return resultMsg;
	    }
	    public static ResultMsg warnMsg(ResultEnum resultEnum) {
	    	ResultMsg resultMsg = new ResultMsg();
	    	resultMsg.setCode(resultEnum.getCode());
	    	resultMsg.setMsg(resultEnum.getMessage());
	    	return resultMsg;
	    }

		public static ResultMsg warnMsg(PremiumFormulaEnum premiumformulaEnum) {
			ResultMsg resultMsg = new ResultMsg();
	    	resultMsg.setCode(premiumformulaEnum.getCode());
	    	resultMsg.setMsg(premiumformulaEnum.getMessage());
	    	return resultMsg;
		}
		public static ResultMsg warnMsg(ProductConfigBeanEnum productConfigBeanEnum) {
			ResultMsg resultMsg = new ResultMsg();
			resultMsg.setCode(productConfigBeanEnum.getCode());
			resultMsg.setMsg(productConfigBeanEnum.getMessage());
			return resultMsg;
		}
}
