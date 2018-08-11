package com.jt.plt.product.mapper;

import org.springframework.stereotype.Repository;

import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.entity.TempProductInfo;

@Repository
public interface TempProductInfoMapper extends MyMapper<TempProductInfo> {
	TempProductInfo selectByProductCode(String productCode);
}