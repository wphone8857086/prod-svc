package com.jt.plt.product.dto;
/**
 * 
 * @author 可达鸭
 * 描述:寿险基础信息
 * @date 2018年7月23日 上午9:04:37
 */
public class LifeInsuranceBaseInfoBean {
	
	private String productCode;
	/**
	 * 宽限期
	 */
	private String periodGrace;
	/**
	 * 观察期
	 */
	private String probation;
	/**
	 * 等待期
	 */
	private String waitingPeriod;
	/**
	 * 犹豫期
	 */
	private String hesitationPeriod;
	/**
	 * 投保年龄
	 */
	private String issueAge;
	/**
	 * 缴费方式：0-趸交,1-年交,2-半年交,3-季交,4-月交
	 */
	private String payType;
	/**
	 * 免赔额信息
	 */
	private String deductible;
	/**
	 * 特别约定
	 */
	private String promise;
	/**
	 * 险种编码
	 */
	private String riskCode;
	/**
	 * 险种名称
	 */
	private String riskName;
	/**
	 * 是否必选险种（附加险）
	 */
	private String isMust;
	/**
	 * 主附加险标识,M-主险，A-附加
	 */
	private String riskFlag;
	public String getPromise() {
		return promise;
	}
	public void setPromise(String promise) {
		this.promise = promise;
	}
	
	public String getRiskFlag() {
		return riskFlag;
	}
	public void setRiskFlag(String riskFlag) {
		this.riskFlag = riskFlag;
	}
	public String getIsMust() {
		return isMust;
	}
	public void setIsMust(String isMust) {
		this.isMust = isMust;
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
	/**
	 * 免赔额信息
	 */
	public String getDeductible() {
		return deductible;
	}
	/**
	 * 免赔额信息
	 */
	public void setDeductible(String deductible) {
		this.deductible = deductible;
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
}
