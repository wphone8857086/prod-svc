package com.jt.plt.product.entity.calPremium;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 
 * @author: wephone
 * @Date: 2018/7/25 20:08
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
@Data
public class InsuranceVO {


	/**
	 * 参保份数
	 */
	private BigDecimal count;

	/**
	 * 方案编码
	 */
    private String programCode;

    /**
	 * 附加险浮动保费
	 */
	private List<RiskBean> riskBeans;

}
