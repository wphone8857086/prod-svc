package com.jt.plt.product.entity.calPremium;

import lombok.Data;

import java.util.List;

/**
 * @Description: 
 * @Auther: wephone
 * @Date: 2018/6/14 15:45
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
@Data
public class RiskBean {

    private String RiskCode;

    private List<FloatPremium> floatPremiums;
}
