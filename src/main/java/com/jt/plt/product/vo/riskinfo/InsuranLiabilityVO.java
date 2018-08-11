package com.jt.plt.product.vo.riskinfo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.jt.plt.product.vo.InsuranLimitVO;


/**   
 * 描述：责任视图
 * 作者： wephone   
 * 创建日期： 2018年3月2日 下午2:29:09
 * 版权：江泰保险经纪股份有限公司
 */
public class InsuranLiabilityVO {
    
	
	 /**
     * 责任编码
     */
	@JsonIgnore
    private String insuranceLiabilityId;
	private String insuranceLiabilityCode;
	
    /**
     * 责任名称
     */
    private String insuranceLiabilityName;
    private String insuranceLiabilityDesc;
    
    private Integer labelId;
    
    private String liabilityLabel;
    /**
	 * 该责任是否参与保费计算 0.不参与 1.参与
	 */
    @JsonInclude(value=Include.NON_NULL)
	private Integer computeSigns;
    /**
     * 限额视图
     */
 	private List<InsuranLimitVO> limitList;
    public String getInsuranceLiabilityDesc() {
		return insuranceLiabilityDesc;
	}
	public void setInsuranceLiabilityDesc(String insuranceLiabilityDesc) {
		this.insuranceLiabilityDesc = insuranceLiabilityDesc;
	}
	public String getInsuranceLiabilityCode() {
		return insuranceLiabilityCode;
	}
	public void setInsuranceLiabilityCode(String insuranceLiabilityCode) {
		this.insuranceLiabilityCode = insuranceLiabilityCode;
	}
	public Integer getLabelId() {
		return labelId;
	}
	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}
	public String getLiabilityLabel() {
		return liabilityLabel;
	}
	public void setLiabilityLabel(String liabilityLabel) {
		this.liabilityLabel = liabilityLabel;
	}
	/**
   	 * 该责任是否参与保费计算 0.不参与 1.参与
   	 */
	public Integer getComputeSigns() {
		return computeSigns;
	}
	 /**
	 * 该责任是否参与保费计算 0.不参与 1.参与
	 */
	public void setComputeSigns(Integer computeSigns) {
		this.computeSigns = computeSigns;
	}

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
	public List<InsuranLimitVO> getLimitList() {
		return limitList;
	}
	public void setLimitList(List<InsuranLimitVO> limitList) {
		this.limitList = limitList;
	}

 	
}
