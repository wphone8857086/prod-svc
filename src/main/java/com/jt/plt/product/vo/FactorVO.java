package com.jt.plt.product.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description: 
 * @author: wephone
 * @Date: 2018/8/2 17:37
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
@Data
public class FactorVO {

    private String factorCode;

    private String factorName;

    private List<ConfCoefficientVO> confCoefficientVOS;
}
