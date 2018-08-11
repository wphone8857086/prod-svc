package com.jt.plt.product.mapper;

import java.util.List;

import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.dto.company.CompanyDTO;
import com.jt.plt.product.dto.company.RiskDTO;
import com.jt.plt.product.entity.InsuranceCompanyRela;
import com.jt.plt.product.vo.AcceptInsuranceInfoVO;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceCompanyRelaMapper extends MyMapper<InsuranceCompanyRela> {
	
	public Integer addInsuranceCompanyRela(InsuranceCompanyRela icr);
	
	public List<AcceptInsuranceInfoVO> findAcceptInsuranceInfoVOById(String productCode);
	public List<CompanyDTO> selectByProductCode(List<String> list);
	public List<RiskDTO> selectRiskByCompanyCode(String companyCode);
	
}