package com.jt.plt.product.mapper;

import org.springframework.stereotype.Repository;

import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.entity.LiabilityLimit;

import java.util.List;

@Repository
public interface LiabilityLimitMapper extends MyMapper<LiabilityLimit> {
	List<LiabilityLimit> selectByInsuranceLiabilityID(String id);

	LiabilityLimit selectByLiabilityLimitCode(String liabilityLimitCode);
}