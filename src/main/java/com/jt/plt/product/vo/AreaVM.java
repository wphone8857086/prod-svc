package com.jt.plt.product.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description: 
 * @author: wephone
 * @Date: 2018/8/6 17:04
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
@Data
public class AreaVM {
    private String regionCode;

    private String regionName;

    private Integer regionLevel;


    private String parentRegionCode;

    private List<AreaVM> children;
}
