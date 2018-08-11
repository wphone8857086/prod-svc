package com.jt.plt.product.mapper;


import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.dto.company.AddRisk;
import com.jt.plt.product.entity.ProductRiskRel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRiskRelMapper extends MyMapper<ProductRiskRel> {
	
	int deleteByProductCode(String productCode);
	
	List<ProductRiskRel> selectByProductCode(String productCode);
	List<AddRisk> selectAddRiskByProductCode(String productCode);
}