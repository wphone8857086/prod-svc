package com.jt.plt.product.dto.lifeapp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author 可达鸭
 * 描述:责任视图
 * @date 2018年1月12日 下午4:19:08
 */
@ApiModel
public class InsuranceLiabilityVO {
	@ApiModelProperty("责任名称")
	private String insuranceLiabilityName;
	@ApiModelProperty("责任类别")
	private String insuranceLiabilityType;
	
	public String getInsuranceLiabilityType() {
		return insuranceLiabilityType;
	}

	public void setInsuranceLiabilityType(String insuranceLiabilityType) {
		this.insuranceLiabilityType = insuranceLiabilityType;
	}

	public String getInsuranceLiabilityName() {
		return insuranceLiabilityName;
	}

	public void setInsuranceLiabilityName(String insuranceLiabilityName) {
		this.insuranceLiabilityName = insuranceLiabilityName;
	}
}
