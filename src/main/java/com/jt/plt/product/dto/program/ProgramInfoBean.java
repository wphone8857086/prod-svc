package com.jt.plt.product.dto.program;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("方案")
public class ProgramInfoBean {
	/**
	 * 方案编码
	 */
	@ApiModelProperty("方案编码")
	private String code;
	/**
	 * 固定-1/浮动-3/固定加浮动-4
	 */
	@ApiModelProperty("方案类型    固定-1/浮动-3/固定加浮动-4")
	private String fixFloatFlag; 
	/**
	 * 固定保费
	 */
	@ApiModelProperty("固定保费")
	private Double premium;
	/**
	 * 责任集合
	 */
	@ApiModelProperty("责任集合")
	private List<LiabilityInfoBean> liabilityList;
	public List<LiabilityInfoBean> getLiabilityList() {
		return liabilityList;
	}
	public void setLiabilityList(List<LiabilityInfoBean> liabilityList) {
		this.liabilityList = liabilityList;
	}
	/**
	 * @return the premium
	 * 固定保费
	 */
	public Double getPremium() {
		return premium;
	}
	/**
	 * @param 固定保费
	 */
	public void setPremium(Double premium) {
		this.premium = premium;
	}
	/**
	 * @return the code
	 *  方案编码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param  方案编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the fixFloatFlag
	 * 固定-1/浮动-3/固定加浮动-4
	 */
	public String getFixFloatFlag() {
		return fixFloatFlag;
	}
	/**
	 * @param 固定-1/浮动-3/固定加浮动-4
	 */
	public void setFixFloatFlag(String fixFloatFlag) {
		this.fixFloatFlag = fixFloatFlag;
	}
}
