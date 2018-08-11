package com.jt.plt.product.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.dto.company.CompanyDTO;
import com.jt.plt.product.entity.CompanyInfo;

@Repository
public interface CompanyInfoMapper extends MyMapper<CompanyInfo> {
	List<CompanyDTO> selectAllCompany();
}