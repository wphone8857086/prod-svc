package com.jt.plt.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.entity.ProductPrivateInsurance;

public interface ProductPrivateInsuranceMapper extends MyMapper<ProductPrivateInsurance> {
	List<ProductPrivateInsurance> selectByProductCode(String productCode);
	ProductPrivateInsurance selectByProductCodeAndRiskCode(@Param("productCode")String productCode,
			@Param("riskCode")String riskCode);
	List<String> selectRiskCodeByProductCode(String productCode);
}
