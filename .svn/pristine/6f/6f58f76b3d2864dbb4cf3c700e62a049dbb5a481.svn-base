package com.jt.plt.product.entity.calPremium;
/**   
 * 描述：保费计算公式接收值定义对象
 * 作者： wephone   
 * 创建日期： 2018年3月8日 下午1:54:58
 * 版权：江泰保险经纪股份有限公司
 */

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
public class FormulaDTO {
	
    // 人数，产品编码，方案id（主险和附加险）

	/**
	 * 参保份数
	 */
	private BigDecimal count;
	
	/**
	 * 产品编码
	 */
	private String productCode;

	/**
	 * 主险保费
	 */
   private BigDecimal basicPremium;

	/**
	 * 主险浮动保费
	 */
	private List<FloatPremium> floatPremiums;

	/**
	 * 主险浮动方案ID
	 */
    private String progId;

    /**
	 * 附加险 浮动方案ID
	 */
	private String ationProgId;

	/**
	 * 主险因子信息对象
	 */
	private  List<FactorInfo> factorInfos;

	/**
	 * 附加险基础保费
	 */
	private BigDecimal additionPremium;

	/**
	 * 附加险浮动保费
	 */
	private List<FloatPremium> additionFloatPremiums;

	/**
	 * 附加险因子对象
	 */
	private List<FactorInfo> additionFactorInfos;

}
