package com.jt.plt.product.entity.calPremium;
/**   
 * 描述：寿险保费计算公式接收值定义对象
 * 作者： wephone   
 * 创建日期： 2018年7月18日 上午11:31:58
 * 版权：江泰保险经纪股份有限公司
 */


import lombok.Data;

import java.util.List;
@Data
public class FormulaDTO {
	

	/**
	 * 产品编码
	 */
	private String productCode;


	/**
	 * 寿险集合对象
	 */
	private List<LifeInsuranceVO> lifeInsuranceVOS;

	/**
	 * 保费计算因子
	 */
	private  List<FactorInfo> factorInfos;


	/**
	 * 保单序号
	 */
	private Integer policyNum;



}
