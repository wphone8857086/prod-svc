package com.jt.plt.product.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 责任视图包装类
 * 
 * @author wephone
 *
 */
public class LiabilityListVO {

	private String insuranceLiabilityId;

	private String insuranceLiabilityName;

	private String riskName;

	private String createTime;

	private String createUser;

	public String getInsuranceLiabilityId() {
		return insuranceLiabilityId;
	}

	public void setInsuranceLiabilityId(String insuranceLiabilityId) {
		this.insuranceLiabilityId = insuranceLiabilityId;
	}

	public String getInsuranceLiabilityName() {
		return insuranceLiabilityName;
	}

	public void setInsuranceLiabilityName(String insuranceLiabilityName) {
		this.insuranceLiabilityName = insuranceLiabilityName;
	}

	public String getRiskName() {
		return riskName;
	}

	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createTime = simpleDateFormat.format(createTime);
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

}
