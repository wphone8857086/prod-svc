package com.jt.plt.product.entity.calPremium;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 
 * @author: wephone
 * @Date: 2018/7/25 20:04
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
@Data
public class CountPremiumBean extends FormulaBean{



	/**
	 * 产品编码
	 */
	private String productCode;

	/**
	 * 保费计算因子
	 */
	private  List<FactorInfo> factorInfos;

    /**
     * 方案集合
     */
	private List<InsuranceVO> insuranceVOS;

	/**
	 * 续保状态 0-续保 1-新保
	 */
	private String renewalStatus;

	/**
	 * 保单号
	 */
	private String policyNo;

   /**续保情况：0-首年投保；1-续保1年；2-续保2年；3-续保3年；依此类推 --*/
	private Integer renewal;

	/**
	 * 上年度保险费
	 */
	 private BigDecimal lastPremium;

	/**
	 * 出单公司编号
	 */
	private String inscomp;

	/**
	 * 渠道编码
	 */
     private String channelNo;

	/**
	 * 保单序号
	 */
	private Integer policyNum;

}
