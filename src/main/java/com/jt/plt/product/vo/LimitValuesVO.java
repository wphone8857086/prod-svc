package com.jt.plt.product.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**   
 * 描述：限额值视图
 * 作者： wephone   
 * 创建日期： 2018年3月2日 下午2:32:55
 * 版权：江泰保险经纪股份有限公司
 */
public class LimitValuesVO {
	/**
     * 限额值id
     */
	@JsonInclude(value =Include.NON_NULL)
    private Long liabilityLimitValuesId;
	private String liabilityLimitValuesCode;

    /**
     * 限额值类型:1-数值,2-文字描述，3-自定义
     */
    @JsonInclude(value =Include.NON_NULL)
    private String liabilityLimitValuesType;
    
    @JsonInclude(value=Include.NON_NULL)
    private String liabilityLimitValuesDesc;
    /**
     * 限额值
     */
    @JsonInclude(value=Include.NON_NULL)
    private String liabilityLimitValues;
    /**
     * 费率
     */
    @JsonInclude(value =Include.NON_NULL)
    private Double rate;
    /**
	 * 1-参与保费计算 0-不参与保费计算
	 */
	private String calculationType;
	/**
	 * 1-参与保费计算 0-不参与保费计算
	 */
	public String getCalculationType() {
		return calculationType;
	}
	/**
	 * 1-参与保费计算 0-不参与保费计算
	 */
	public void setCalculationType(String calculationType) {
		this.calculationType = calculationType;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getLiabilityLimitValuesCode() {
		return liabilityLimitValuesCode;
	}
	public void setLiabilityLimitValuesCode(String liabilityLimitValuesCode) {
		this.liabilityLimitValuesCode = liabilityLimitValuesCode;
	}
	public String getLiabilityLimitValuesDesc() {
		return liabilityLimitValuesDesc;
	}
	public void setLiabilityLimitValuesDesc(String liabilityLimitValuesDesc) {
		this.liabilityLimitValuesDesc = liabilityLimitValuesDesc;
	}
	@Override
	public String toString() {
		return "LiabilityLimitValuesVO [liabilityLimitValuesId=" + liabilityLimitValuesId
				+ ", liabilityLimitValuesType=" + liabilityLimitValuesType + ", liabilityLimitValues="
				+ liabilityLimitValues + "]";
	}

	public Long getLiabilityLimitValuesId() {
		return liabilityLimitValuesId;
	}

	public void setLiabilityLimitValuesId(Long liabilityLimitValuesId) {
		this.liabilityLimitValuesId = liabilityLimitValuesId;
	}

	public String getLiabilityLimitValuesType() {
		return liabilityLimitValuesType;
	}

	public void setLiabilityLimitValuesType(String liabilityLimitValuesType) {
		this.liabilityLimitValuesType = liabilityLimitValuesType;
	}

	public String getLiabilityLimitValues() {
		return liabilityLimitValues;
	}

	public void setLiabilityLimitValues(String liabilityLimitValues) {
		this.liabilityLimitValues = liabilityLimitValues;
	}
}
