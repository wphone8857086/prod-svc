package com.jt.plt.product.vo.riskinfo;

import java.util.List;
/**
 * 
 * @author 可达鸭
 * 描述:产品限额信息
 * @date 2018年5月8日 下午4:57:54
 */
public class TempProductInfoVO {
	private Long id;
	/**
	 * 产品编码
	 */
	private String productCode;
	/**
	 * 包含险种集合
	 */
	private List<RiskTempVO> riskList;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * @return the riskList
	 */
	public List<RiskTempVO> getRiskList() {
		return riskList;
	}
	/**
	 * @param riskList the riskList to set
	 */
	public void setRiskList(List<RiskTempVO> riskList) {
		this.riskList = riskList;
	}
}
