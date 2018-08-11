package com.jt.plt.product.dto.program;

import java.util.List;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("责任")
public class LiabilityInfoBean {
	/**
	 * 责任编码
	 */
	@ApiModelProperty("责任编码")
	private String code;
	/**
	 * 责任名称
	 */
	@ApiModelProperty("责任名称")
	private String name;
	@ApiModelProperty("所属险种编码")
	@JsonInclude(value = Include.NON_NULL)
	private String riskCode;
	/**
	 *  主险-M/附加险标记-A
	 */
	@ApiModelProperty("方案所属险种类型   主险-M/附加险标记-A")
	@JsonInclude(value = Include.NON_NULL)
	private String riskFlag;
	@ApiModelProperty("所属险种名称")
	@JsonInclude(value = Include.NON_NULL)
	private String riskName;
	@ApiModelProperty("所属分组")
	@JsonInclude(value = Include.NON_NULL)
	private String groupCode;
	/**
	 * 限额集合
	 */
	@ApiModelProperty("限额集合")
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
	/**
	 * @return the riskFlag
	 *   主险-M/附加险标记-A
	 */
	public String getRiskFlag() {
		return riskFlag;
	}
	/**
	 *  主险-M/附加险标记-A
	 * @param   主险-M/附加险标记-A
	 */
	public void setRiskFlag(String riskFlag) {
		this.riskFlag = riskFlag;
	}
}
