package com.jt.plt.product.mapper;

import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.entity.RiskInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RiskInfoMapper extends MyMapper<RiskInfo> {
	/**
	 * 
	 * @param riskCode
	 * @return
	 * 描述：通过险种编码查询
	 */
	RiskInfo selectByRiskCode(String riskCode);
}