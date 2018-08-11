package com.jt.plt.product.mapper;

import org.apache.ibatis.annotations.Param;

import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.entity.ProductRuleInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRuleInfoMapper extends MyMapper<ProductRuleInfo> {
	
	List<ProductRuleInfo> selectByProductCode(String productCode);
	List<ProductRuleInfo> selectByProductCodeAndTypeCodeWithRate(@Param("productCode")String productCode,
			@Param("typeCode")String typeCode);
	List<ProductRuleInfo> selectByProductCodeAndTypeCode(@Param("productCode")String productCode,
			@Param("typeCode")String typeCode);
	/**
	 * 
	 * @param productCode
	 * @return
	 * 描述：产品下面规则所属的险种编码（寿险）
	 */
	List<String> selectTypeCodeByProductWithRate(String productCode);
}
