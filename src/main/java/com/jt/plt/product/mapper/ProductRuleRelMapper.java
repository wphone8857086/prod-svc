package com.jt.plt.product.mapper;

import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.entity.ProductRuleRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRuleRelMapper extends MyMapper<ProductRuleRel> {
	
	List<ProductRuleRel> selectByProductCode(String productCode);
	
	List<ProductRuleRel> selectByRuleValue(String ruleValue);

	ProductRuleRel selectByTypeCodeAndRuleValue(@Param(value = "typeCode") String typeCode, @Param(value = "ruleValue") String ruleValue);

}