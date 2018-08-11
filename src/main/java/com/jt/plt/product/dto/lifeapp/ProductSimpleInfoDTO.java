package com.jt.plt.product.dto.lifeapp;
/**
 * 
 * @author 可达鸭
 * 描述:寿险渠道产品信息视图
 * @date 2018年7月27日 下午4:08:26
 */

import lombok.Data;

@Data
public class ProductSimpleInfoDTO {
	/**
	 * 产品编码
	 */
	private String code;
	/**
	 * 产品名称
	 */
	private String name;
	
	private String companyCode;
	
	private String companyName;
}
