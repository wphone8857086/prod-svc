package com.jt.plt.product.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.entity.InsuranceClause;

@Repository
public interface InsuranceClauseMapper extends MyMapper<InsuranceClause> {
	List<InsuranceClause> selectByTheirId(String riskCode);
}