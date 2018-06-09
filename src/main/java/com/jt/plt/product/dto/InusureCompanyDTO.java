package com.jt.plt.product.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
  * 描述：保险公司数据类
  * 类名称：InusureCompanyDTO
  * 作者： wangyang
  * 版本：1.0
  * 修改：
  * 创建日期：2018-04-02
  * 版权：江泰保险经纪股份有限公司
  */
public class InusureCompanyDTO {
    /**
     * 承保公司ID
     */
    private Integer id;

    /**
     * 承保公司名称
     */
    @JsonInclude(value = Include.NON_NULL)
    private String name;

    /**
     * 承保公司Logo地址
     */
	@JsonInclude(value = Include.NON_NULL)
    private String logoUrl;

    /**
     * 承保类型
     */
	@JsonInclude(value = Include.NON_NULL)
    private String partyRole;
    /**
     * 承保类型 主承，共保，独立承保，
     */
	@JsonInclude(value = Include.NON_NULL)
    private String shareType;
    /**
     * 承保比例
     */
	@JsonInclude(value = Include.NON_NULL)
    private String shareScale;
    /**
     * 解付类型 一对一解付 一对多解付
     */
	@JsonInclude(value = Include.NON_NULL)
    private String payType;
    /**
     * 解付比例
     */
	@JsonInclude(value = Include.NON_NULL)
    private String payScale;
    /**
     * 出单类型
     */
	@JsonInclude(value = Include.NON_NULL)
    private String insuringType;
    /**
     * 出单区域编码
     */
	@JsonInclude(value = Include.NON_NULL)
    private String insuringAreaCode;
    /**
     * 出单区域名称
     */
	@JsonInclude(value = Include.NON_NULL)
    private String insuringAreaName;
    /**
     * 计算比例
     */
	@JsonInclude(value = Include.NON_NULL)
    private String balanceScale;
    
    public String getShareType() {
		return shareType;
	}
	/**
     * 承保类型
     */
	public String getPartyRole() {
		return partyRole;
	}
	 /**
     * 承保类型
     */
	public void setPartyRole(String partyRole) {
		this.partyRole = partyRole;
	}
	 /**
     * 承保比例
     */
	public String getShareScale() {
		return shareScale;
	}
	 /**
     * 承保比例
     */
	public void setShareScale(String shareScale) {
		this.shareScale = shareScale;
	}
	  /**
     * 解付类型 一对一解付 一对多解付
     */
	public String getPayType() {
		return payType;
	}
	  /**
     * 解付类型 一对一解付 一对多解付
     */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	/**
     * 解付比例
     */
	public String getPayScale() {
		return payScale;
	}
	/**
     * 解付比例
     */
	public void setPayScale(String payScale) {
		this.payScale = payScale;
	}
	/**
     * 出单类型
     */
	public String getInsuringType() {
		return insuringType;
	}
	/**
     * 出单类型
     */
	public void setInsuringType(String insuringType) {
		this.insuringType = insuringType;
	}
	/**
     * 出单区域编码
     */
	public String getInsuringAreaCode() {
		return insuringAreaCode;
	}
	/**
     * 出单区域编码
     */
	public void setInsuringAreaCode(String insuringAreaCode) {
		this.insuringAreaCode = insuringAreaCode;
	}
	/**
     * 出单区域名称
     */
	public String getInsuringAreaName() {
		return insuringAreaName;
	}
	/**
     * 出单区域名称
     */
	public void setInsuringAreaName(String insuringAreaName) {
		this.insuringAreaName = insuringAreaName;
	}
	/**
     * 结算比例（佣金）
     */
	public String getBalanceScale() {
		return balanceScale;
	}
	/**
     * 结算比例（佣金）
     */
	public void setBalanceScale(String balanceScale) {
		this.balanceScale = balanceScale;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

}
