package com.jt.plt.product.entity.calPremium;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 附加险视图
 * @Auther: wephone
 * @Date: 2018/5/29 14:31
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
@Data
public class AdditionInsurance implements Comparable {
     /**
	 * 附加险参保份数
	 */
	private BigDecimal additionCount;

    /**
	 * 附加险方案编码
	 */
	private String ationProgramCode;

	/**
	 * 附加险浮动保费
	 */
	private List<RiskBean> additionRiskBeans;

	 @Override
    public int compareTo(Object comparestu) {
        String compareage=((AdditionInsurance)comparestu).ationProgramCode;
        /* For Ascending orderf*/
        return Integer.parseInt(this.ationProgramCode.substring(ationProgramCode.length()-4,ationProgramCode.length()))
				-Integer.parseInt(compareage.substring(compareage.length()-4,compareage.length()));
    }

}
