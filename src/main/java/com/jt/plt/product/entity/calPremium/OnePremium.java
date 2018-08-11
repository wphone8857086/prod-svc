package com.jt.plt.product.entity.calPremium;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @Description: 
 * @Auther: wephone
 * @Date: 2018/6/25 10:04
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
@Data
public class OnePremium {


    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 总保费
     */
    private BigDecimal singlePremium;

    /**
     * 险种保费
     */
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Set<RiskPremium> riskPremiums;

    /**
     * 保费计算公式描述
     */
    private String premiumDesignDesc;

    /**
	 * 保单序号
	 */
     @JsonInclude(value = JsonInclude.Include.NON_NULL)
	private Integer policyNum;
}
