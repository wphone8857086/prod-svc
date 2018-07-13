package com.jt.plt.product.entity.calPremium;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 险种保费视图
 * @Auther: wephone
 * @Date: 2018/6/13 14:01
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
@Data
public class RiskPremium {

    /**
     * 险种编码
     */
    private String riskCode;

    /**
     * 险种保费
     */
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private BigDecimal riskPremiuml;

    /**
     * 限额保费
     */
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private  List<LimitPremium> limitPremiums;
}
