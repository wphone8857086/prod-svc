package com.jt.plt.product.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author 可达鸭
 * 描述:产品配置回显视图类
 * @date 2018年3月8日 上午10:14:08
 */
public class AcceptInsuranceInfoVO {
	/**
	 *关系主键ID
	 */
	@JsonInclude(value = Include.NON_NULL)
	public Integer relaId;
	/**
	 *属性主键ID
	 */
	@JsonInclude(value = Include.NON_NULL)
	private Integer attributeValueId;
	/**
	 * 关系类型，1-承保,2-出单,3-解付
	 */
	private String relaType;
	/**
	 * 出单区域
	 */
	@JsonInclude(value = Include.NON_NULL)
	private String areaCode;
	/**
	 * 承保公司ID
	 */
	private String companyCode;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 产品编码
	 */
	private String productCode;
	/**
	 * 属性编码 承保类型,1-主承，2-共保，3-独立承保,出单类型，Y-出单,N-跟单,解付类型  4-one对one , 5-one对many
	 */
	@JsonInclude(value = Include.NON_NULL)
	private String attributeValueCode;
	/**
	 * 属性名称
	 */
	@JsonInclude(value = Include.NON_NULL)
	private String attributeValueName;
	/**
	 * 属性值  承保比例 或者 出单区域编码 或者 解付比例
	 */
	@JsonInclude(value = Include.NON_NULL)
	private String attributeValues;
	/**
	 *关系主键ID
	 */
	public Integer getRelaId() {
		return relaId;
	}
	/**
	 *关系主键ID
	 */
	public void setRelaId(Integer relaId) {
		this.relaId = relaId;
	}
	/**
	 *属性主键ID
	 */
	public Integer getAttributeValueId() {
		return attributeValueId;
	}
	/**
	 *属性主键ID
	 */
	public void setAttributeValueId(Integer attributeValueId) {
		this.attributeValueId = attributeValueId;
	}
	/**
	 * 关系类型，1-承保,2-出单,3-解付
	 */
	public String getRelaType() {
		return relaType;
	}
	/**
	 * 关系类型，1-承保,2-出单,3-解付
	 */
	public void setRelaType(String relaType) {
		this.relaType = relaType;
	}
	/**
	 * 出单区域
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * 出单区域
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	/**
	 * 公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * 公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	 * 属性编码 承保类型,1-主承，2-共保，3-独立承保,出单类型，Y-出单,N-跟单,解付类型  4-one对one , 5-one对many
	 */
	public String getAttributeValueCode() {
		return attributeValueCode;
	}
	/**
	 * 属性编码 承保类型,1-主承，2-共保，3-独立承保,出单类型，Y-出单,N-跟单,解付类型  4-one对one , 5-one对many
	 */
	public void setAttributeValueCode(String attributeValueCode) {
		this.attributeValueCode = attributeValueCode;
	}
	/**
	 * 属性名称
	 */
	public String getAttributeValueName() {
		return attributeValueName;
	}
	/**
	 * 属性名称
	 */
	public void setAttributeValueName(String attributeValueName) {
		this.attributeValueName = attributeValueName;
	}
	/**
	 * 属性值
	 */
	public String getAttributeValues() {
		return attributeValues;
	}
	/**
	 * 属性值
	 */
	public void setAttributeValues(String attributeValues) {
		this.attributeValues = attributeValues;
	}
	
}
