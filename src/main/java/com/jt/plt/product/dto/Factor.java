package com.jt.plt.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("产品因子")
public class Factor {

	/**
	 * 因子关系id
	 */
	@ApiModelProperty("因子关系id")
	private Long factorRelaId;

	/**
	 * 因子编码
	 */
	@ApiModelProperty("因子编码")
	private String factorCode;

	/**
	 * 因子名称
	 */
	@ApiModelProperty("因子名称")
	private String factorName;

	/**
	 * 因子代码名称
	 */
	@ApiModelProperty("因子代码名称")
	private String factorCodeName;

	/**
	 * 因子类型
	 */
	@ApiModelProperty("因子类型(因子类型为2-'方案类型'时存在)")
	@JsonInclude(value = Include.NON_NULL)
	private String type;

	public Long getFactorRelaId() {
		return factorRelaId;
	}

	public void setFactorRelaId(Long factorRelaId) {
		this.factorRelaId = factorRelaId;
	}

	public String getFactorCode() {
		return factorCode;
	}

	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}

	public String getFactorCodeName() {
		return factorCodeName;
	}

	public void setFactorCodeName(String factorCodeName) {
		this.factorCodeName = factorCodeName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
