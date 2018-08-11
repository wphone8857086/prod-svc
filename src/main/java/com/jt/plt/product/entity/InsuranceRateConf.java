package com.jt.plt.product.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_insurance_rate_conf")
public	class InsuranceRateConf {
	@Id
    @GeneratedValue(generator = "JDBC") 
    @Column(name = "id")
	private Long id;
	/**
	 * 寿险费率表编码
	 */
	@Column(name = "rate_code")
	private String rateCode;
	/**
	 * 产品编码
	 */
	@Column(name = "product_code")
	private String productCode;
	/**
	 * 产品名称
	 */
	@Column(name = "product_name")
	private String productName;
	/**
	 * 险种编码
	 */
	@Column(name = "risk_code")
	private String riskCode;
	/**
	 * 险种编码
	 */
	@Column(name = "risk_name")
	private String riskName;
	/**
	 *基本保险金额  默认单位元
	 */
	@Column(name = "basic_insurance_amount")
	private Double basicInsuranceAmount;
	
	/**
	 * 币种,0-人民币
	 */
	@Column(name = "currency")
	private String currency;
	/**
	 * 费率
	 */
	@Column(name = "rate")
	private Double rate;
	
	/**
	 * 状态
	 */
	@Column(name = "status")
	private Double status;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	public String getRateCode() {
		return rateCode;
	}
	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getRiskName() {
		return riskName;
	}
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	public Double getBasicInsuranceAmount() {
		return basicInsuranceAmount;
	}
	public void setBasicInsuranceAmount(Double basicInsuranceAmount) {
		this.basicInsuranceAmount = basicInsuranceAmount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getStatus() {
		return status;
	}
	public void setStatus(Double status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
