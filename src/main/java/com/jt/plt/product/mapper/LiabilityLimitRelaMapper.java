package com.jt.plt.product.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.entity.LiabilityLimitRela;

@Repository
public interface LiabilityLimitRelaMapper extends MyMapper<LiabilityLimitRela> {
	List<LiabilityLimitRela> selectByProgramCode(String programCode);
}