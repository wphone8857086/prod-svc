package com.jt.plt.product.entity.calPremium;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description: 限额保费视图
 * @Auther: wephone
 * @Date: 2018/6/13 13:56
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
@Data
public class LimitPremium {

    /**
	 * 限额编码
	 */
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
	private String limitCode;

    /**
     * 限额保费
     */
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private BigDecimal limitPremium;

}
