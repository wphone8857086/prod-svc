package com.jt.plt.product.dto.channel;

import java.util.Date;

import lombok.Data;

@Data
public class Query {
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
	 * 状态 上架 下架
	 */
	private String productStatus;
	/**
	 * 发布开始日期
	 */
	private Date listStart;
	/**
	 * 发布结束日期
	 */
	private Date listEnd;
	/**
	 * 下架开始日期
	 */
	private Date disStart;
	/**
	 * 下架结束日期
	 */
	private Date disEnd;
	/**
	 * 渠道编码
	 */
	private String channel;
	/**
	 * 页数
	 */
	private Integer pn=1;
	/**
	 * 页面大小
	 */
	private Integer ps=10;
}
