package com.jt.plt.product.mapper;

import org.springframework.stereotype.Repository;

import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.entity.CompanyRelaAttribute;

@Repository
public interface CompanyRelaAttributeMapper extends MyMapper<CompanyRelaAttribute> {
	Integer delByProductCode(String productCode);
}