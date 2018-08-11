package com.jt.plt.product.entity.calPremium;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description:
 * @author: wephone
 * @Date: 2018/7/18 11:15
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司
 */
@Data
public class LifeInsuranceVO {

    /**
     * 销售属性字符串
     */
    private String salesRules;

    /**
     * 险种编码
     */
    private String riskCode;

    /**
     * 用户购买金额
     */
    private BigDecimal userPurchaseAmount;

    /**
     * 方案编码
     */
    private String programCode;

    /**
     * 险种份数
     */
    private BigDecimal riskCopies;


}
