package com.jt.plt.product.dto.program;

import java.util.List;

import javax.persistence.Transient;

public class LiabilityInfoBean {
	/**
	 * 责任编码
	 */
	private String code;
	/**
	 * 责任名称
	 */
	private String name;
	private String riskCode;
	private String riskName;
	private String groupCode;
	/**
	 * 限额集合
	 */
	@Transient
	private List<LimitInfoBean> limitList;
	/**
	 * @return the riskCode
	 */
	public String getRiskCode() {
		return riskCode;
	}
	/**
	 * @param riskCode the riskCode to set
	 */
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	/**
	 * @return the riskName
	 */
	public String getRiskName() {
		return riskName;
	}
	/**
	 * @param riskName the riskName to set
	 */
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	/**
	 * @return the code
	 * 责任编码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param 责任编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the limitList
	 * 限额集合
	 */
	public List<LimitInfoBean> getLimitList() {
		return limitList;
	}
	/**
	 * @param 限额集合
	 */
	public void setLimitList(List<LimitInfoBean> limitList) {
		this.limitList = limitList;
	}
	/**
	 * @return the name
	 * 责任名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param 责任名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
}
