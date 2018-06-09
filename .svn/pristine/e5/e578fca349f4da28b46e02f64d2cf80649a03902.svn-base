package com.jt.plt.product.vo;

import java.util.List;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


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
	@JsonInclude(value = Include.NON_NULL)
    private String insuranceLiabilityId;

    /**
     * 责任名称
     */
    private String insuranceLiabilityName;
    /**
	 * 该责任是否参与保费计算 0.不参与 1.参与
	 */
    @JsonInclude(value=Include.NON_NULL)
	private Integer computeSigns;
    /**
     * 限额视图
     */
    @Transient
 	private List<InsuranLimitVO> liabilityLimitVo;
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

	public List<InsuranLimitVO> getLiabilityLimitVo() {
		return liabilityLimitVo;
	}

	public void setLiabilityLimitVo(List<InsuranLimitVO> liabilityLimitVo) {
		this.liabilityLimitVo = liabilityLimitVo;
	}
 	
	
 	
}
