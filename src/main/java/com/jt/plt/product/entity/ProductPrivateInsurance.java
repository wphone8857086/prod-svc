package com.jt.plt.product.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_product_private_insurance")
public class ProductPrivateInsurance {
	/**
	 * 自增id
	 */
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 产品编码
	 */
	@Column(name = "product_code")
	private String productCode;
	/**
	 * 险种编码
	 */
	@Column(name = "risk_code")
    private String riskCode;
	/**
	 * 险种名称
	 */
    @Column(name = "risk_name")
    private String riskName;
    /**
     * 主附加险标识,M-主险，A-附加
     */
    @Column(name = "risk_flag")
    private String riskFlag;
    /**
	 * 险种必选标识（附加险必选标识）
	 */
    @Column(name = "is_must")
    private String isMust;
	/**
	 * 宽限期
	 */
	@Column(name = "period_grace")
	private String periodGrace;
	/**
	 * 观察期
	 */
	@Column(name = "probation")
	private String probation;
	/**
	 * 等待期
	 */
	@Column(name = "waiting_period")
	private String waitingPeriod;
	/**
	 * 犹豫期
	 */
	@Column(name = "hesitation_period")
	private String hesitationPeriod;
	/**
	 * 投保年龄
	 */
	@Column(name = "issue_age")
	private String issueAge;
	/**
	 * 缴费方式：0-趸交,1-年交,2-半年交,3-季交,4-月交
	 */
	@Column(name = "pay_type")
	private String payType;
	
	
	@Column(name = "create_id")
	private String createId;
    @Column(name = "create_time")
	private Date createTime;
    @Column(name = "modify_time")
    private String modifyTime;
	public String getRiskFlag() {
		return riskFlag;
	}
	public void setRiskFlag(String riskFlag) {
		this.riskFlag = riskFlag;
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
	public String getIsMust() {
		return isMust;
	}
	public void setIsMust(String isMust) {
		this.isMust = isMust;
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
	/**
	 * 宽限期
	 */
	public String getPeriodGrace() {
		return periodGrace;
	}
	/**
	 * 宽限期
	 */
	public void setPeriodGrace(String periodGrace) {
		this.periodGrace = periodGrace;
	}
	/**
	 * 观察期
	 */
	public String getProbation() {
		return probation;
	}
	/**
	 * 观察期
	 */
	public void setProbation(String probation) {
		this.probation = probation;
	}
	/**
	 * 等待期
	 */
	public String getWaitingPeriod() {
		return waitingPeriod;
	}
	/**
	 * 等待期
	 */
	public void setWaitingPeriod(String waitingPeriod) {
		this.waitingPeriod = waitingPeriod;
	}
	/**
	 * 犹豫期
	 */
	public String getHesitationPeriod() {
		return hesitationPeriod;
	}
	/**
	 * 犹豫期
	 */
	public void setHesitationPeriod(String hesitationPeriod) {
		this.hesitationPeriod = hesitationPeriod;
	}
	/**
	 * 投保年龄
	 */
	public String getIssueAge() {
		return issueAge;
	}
	/**
	 * 投保年龄
	 */
	public void setIssueAge(String issueAge) {
		this.issueAge = issueAge;
	}
	/**
	 * 缴费方式：0-趸交,1-年交,2-半年交,3-季交,4-月交
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * 缴费方式：0-趸交,1-年交,2-半年交,3-季交,4-月交
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
}
