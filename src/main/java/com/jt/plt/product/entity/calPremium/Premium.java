package com.jt.plt.product.entity.calPremium;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description: 
 * @Auther: wephone
 * @Date: 2018/6/1 14:56
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
@Data
public class Premium {

    /**
     * 总保费
     */
    private BigDecimal sumPremium;

    /**
     * 主险保费
     */
    private BigDecimal basicPremium;

    /**
     * 附加险保费
     */
   private BigDecimal additionPremiums;
}
