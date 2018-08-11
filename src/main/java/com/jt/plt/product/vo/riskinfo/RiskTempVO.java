package com.jt.plt.product.vo.riskinfo;

import java.util.List;
/**
 * 
 * @author 可达鸭
 * 描述:临时险种视图
 * @date 2018年5月29日 下午1:45:46
 */
public class RiskTempVO {
	private String productCode;
	private String code;
	private String name;
	private String riskFlag;
	/**
	 * 包含责任
	 */
	private List<InsuranLiabilityVO> liabilityList;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the riskFlag
	 */
	public String getRiskFlag() {
		return riskFlag;
	}
	/**
	 * @param riskFlag the riskFlag to set
	 */
	public void setRiskFlag(String riskFlag) {
		this.riskFlag = riskFlag;
	}
	/**
	 * @return the liabilityList
	 */
	public List<InsuranLiabilityVO> getLiabilityList() {
		return liabilityList;
	}
	/**
	 * @param liabilityList the liabilityList to set
	 */
	public void setLiabilityList(List<InsuranLiabilityVO> liabilityList) {
		this.liabilityList = liabilityList;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
}
