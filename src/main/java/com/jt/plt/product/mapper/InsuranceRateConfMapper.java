package com.jt.plt.product.mapper;


import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.entity.InsuranceRateConf;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InsuranceRateConfMapper extends MyMapper<InsuranceRateConf> {
	InsuranceRateConf selectByRateCode(String rateCode);
	List<InsuranceRateConf> selectByProductCode(String productCode);
	int delByProductCode(String productCode);
	//List<LifeRateVO> selectRateInfoByProductCode(String productCode);
}
