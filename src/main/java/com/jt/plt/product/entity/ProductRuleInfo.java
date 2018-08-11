package com.jt.plt.product.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_product_rule_info")
public class ProductRuleInfo {
	/**
	 * 自增id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	/**
	 * 产品编码
	 */
	@Column(name="product_code")
	private String productCode;
	/**
	 * 规则编码
	 */
	@Column(name="rule_id")
	private Long ruleId;
	@Column(name="rule_code")
	private String ruleCode;
	/**
	 * 规则名称
	 */
	@Column(name="rule_name")
	private String ruleName;
	/**
	 * 规则值（多个规则值用','隔开<逗号使用英文半角>；区间规则值存储规则：使用数学区间符号，最大值和最小值用'-'隔开，例：[20-30]）'
	 */
	@Column(name="rule_value")
	private String ruleValue;
	
	@Column(name="value_unit")
	private String valueUnit;
	//规则对应的类型   产品-0  险种-1 责任-2
	private String ruleType;
	//对应类型的编码
	private String typeCode;
	private String creatUser;
	private Date creatDate;
	public String getValueUnit() {
		return valueUnit;
	}
	public void setValueUnit(String valueUnit) {
		this.valueUnit = valueUnit;
	}
	public String getRuleCode() {
		return ruleCode;
	}
	public void setRuleCode(String ruleCode) {
		this.ruleCode = ruleCode;
	}
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getCreatUser() {
		return creatUser;
	}
	public void setCreatUser(String creatUser) {
		this.creatUser = creatUser;
	}
	public Date getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(Date creatDate) {
		this.creatDate = creatDate;
	}
	/**
	 * 自增id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 自增id
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 产品编码
	 */
	public String getProductCode() {
		return productCode;
	}
	/**
	 * 产品编码
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	/**
	 * 规则编码
	 */
	public Long getRuleId() {
		return ruleId;
	}
	/**
	 * 规则编码
	 */
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
	/**
	 * 规则名称
	 */
	public String getRuleName() {
		return ruleName;
	}
	/**
	 * 规则名称
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	/**
	 * 规则值（多个规则值用','隔开<逗号使用英文半角>；区间规则值存储规则：使用数学区间符号，最大值和最小值用'-'隔开，例：[20-30]）'
	 */
	public String getRuleValue() {
		return ruleValue;
	}
	/**
	 * 规则值（多个规则值用','隔开<逗号使用英文半角>；区间规则值存储规则：使用数学区间符号，最大值和最小值用'-'隔开，例：[20-30]）'
	 */
	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
}
