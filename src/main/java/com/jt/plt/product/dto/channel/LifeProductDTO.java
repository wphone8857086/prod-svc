package com.jt.plt.product.dto.channel;

import java.util.Date;

import lombok.Data;

/**
 * 
 * @author 可达鸭
 * 描述:主要针对 寿险pc端产品列表
 * @date 2018年8月10日 上午9:31:28
 */
@Data
public class LifeProductDTO {
	/**
	 * 产品编码
	 */
	private String productCode;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 公司编码
	 */
	private String companyCode;
	/**
	 * 公司名称
	 */
	private String companyName;
	
	private String companyLogo;
	
	private String productLogo;
	/**
	 * 状态 上架 下架
	 */
	private String productStatus;
	/**
	 * 发布开始日期
	 */
	private Date listDate;
	/**
	 * 下架开始日期
	 */
	private Date disDate;
	
}
