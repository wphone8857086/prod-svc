package com.jt.plt.product.mapper;


import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.dto.Factor;
import com.jt.plt.product.entity.InsuranceFactorRela;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InsuranceFactorRelaMapper extends MyMapper<InsuranceFactorRela> {
	List<Factor> findFactorInfo(String productCode);

	InsuranceFactorRela selectByMap(Map<String,String> map);
}