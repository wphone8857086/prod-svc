package com.jt.plt.product.entity.calPremium;
/**   
 * 描述：保费计算视图
 * 作者： wephone   
 * 创建日期： 2018年3月8日 下午1:54:58
 * 版权：江泰保险经纪股份有限公司
 */
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
@Data
public class FormulaBean {




	/**
	 * 产品编码
	 */
	private String productCode;

	/**
	 * 保费计算因子
	 */
	private  List<FactorInfo> factorInfos;


	/**
	 * 主险参保份数
	 */
	private BigDecimal basicCount;

	/**
	 * 基本险方案编码
	 */
    private String programCode;

	/**
	 * 基本险浮动保费
	 */
	private List<RiskBean> riskBeans;

	/**
	 * 附加险集合
	 */
	private List<AdditionInsurance> additionInsurances;

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
