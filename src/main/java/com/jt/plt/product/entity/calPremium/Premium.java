package com.jt.plt.product.entity.calPremium;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * @Description: 保费视图
 * @Auther: wephone
 * @Date: 2018/6/13 17:44
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
@Data
public class Premium {
    /**
     * 总保费
     */
    private BigDecimal totalPremium;

    /**
     *
     */
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private List<OnePremium> singlePremiums;

}
