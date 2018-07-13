package com.jt.plt.product.entity.calPremium;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description: 
 * @Auther: wephone
 * @Date: 2018/6/12 17:42
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
@Data
public class Program {

    /**
     * 方案编码
     */
    private String programCode;

    /**
     * 参保份数
     */
    private BigDecimal count;
    /**
     * 限额计算集合
     */
    private List<FloatPremium> floatPremiums;


}
