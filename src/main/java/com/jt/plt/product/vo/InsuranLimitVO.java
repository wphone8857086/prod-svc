package com.jt.plt.product.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.jt.plt.product.entity.FloatRate;


/**   
 * 描述：限额视图
 * 作者： wephone   
 * 创建日期： 2018年3月2日 下午2:31:13
 * 版权：江泰保险经纪股份有限公司
 */
public class InsuranLimitVO {
	 /**
     * 责任限额id
     */
    @JsonInclude(value = Include.NON_NULL)
    private String liabilityLimitId;
    private String liabilityLimitCode;
    /**
     * 责任限额名称
     */
    private String liabilityLimitName;
   
    /**
     * 限额值视图
     */
    @JsonInclude(value = Include.NON_NULL)
	private List<LimitValuesVO> limitValues;
	/**
	 * 浮动限额值视图
	 */
    @JsonInclude(value =Include.NON_NULL)
	private List<FloatLimitValuesVO> floatLimitValuesVO;
    /**
     * 浮动费率集合
     */
    @JsonInclude(value =Include.NON_NULL)
    private List<FloatRate> floatRateList;
	/**
	 * 浮动限额值视图
	 */
	public List<FloatLimitValuesVO> getFloatLimitValuesVO() {
		return floatLimitValuesVO;
	}
	/**
	 * 浮动限额值视图
	 */
	public void setFloatLimitValuesVO(List<FloatLimitValuesVO> floatLimitValuesVO) {
		this.floatLimitValuesVO = floatLimitValuesVO;
	}
	public String getLiabilityLimitId() {
		return liabilityLimitId;
	}
	public void setLiabilityLimitId(String liabilityLimitId) {
		this.liabilityLimitId = liabilityLimitId;
	}
	public String getLiabilityLimitName() {
		return liabilityLimitName;
	}
	public void setLiabilityLimitName(String liabilityLimitName) {
		this.liabilityLimitName = liabilityLimitName;
	}
	public List<LimitValuesVO> getLimitValues() {
		return limitValues;
	}
	public void setLimitValues(List<LimitValuesVO> limitValues) {
		this.limitValues = limitValues;
	}
	public List<FloatRate> getFloatRateList() {
		return floatRateList;
	}
	public void setFloatRateList(List<FloatRate> floatRateList) {
		this.floatRateList = floatRateList;
	}
	public String getLiabilityLimitCode() {
		return liabilityLimitCode;
	}
	public void setLiabilityLimitCode(String liabilityLimitCode) {
		this.liabilityLimitCode = liabilityLimitCode;
	}
}
