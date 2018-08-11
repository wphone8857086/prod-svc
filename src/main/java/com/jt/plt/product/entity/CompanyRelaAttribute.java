package com.jt.plt.product.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_company_rela_attribute")
public class CompanyRelaAttribute {
    /**
     * 属性id
     */
    @Id
    @Column(name = "attribute_value_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attributeValueId;

    /**
     * 属性名称
     */
    @Column(name = "attribute_value_name")
    private String attributeValueName;

    /**
     * 说对应关系id
     */
    @Column(name = "rela_id")
    private Integer relaId;

    /**
     * 产品id
     */
    @Column(name = "product_code")
    private String productCode;

    /**
     * 保险公司id
     */
    @Column(name = "company_code")
    private String companyCode;
    /**
     * 分级拼接的公司id，用于辅助前端回显
     */
    @Column(name = "display_field")
    private String displayField;
    /**
     * 承保属性编码
     */
    @Column(name = "attribute_value_code")
    private String attributeValueCode;
    /**
     * 承保属性属性值
     */
    @Column(name = "attribute_values")
    private String attributeValues;

    /**
     * 状态,0-失效,1-生效
     */
    private String status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    public String getDisplayField() {
		return displayField;
	}

	public void setDisplayField(String displayField) {
		this.displayField = displayField;
	}

	/**
     * 获取属性id
     *
     * @return attribute_value_id - 属性id
     */
    public Integer getAttributeValueId() {
        return attributeValueId;
    }

    /**
     * 设置属性id
     *
     * @param attributeValueId 属性id
     */
    public void setAttributeValueId(Integer attributeValueId) {
        this.attributeValueId = attributeValueId;
    }

    /**
     * 获取属性名称
     *
     * @return attribute_value_name - 属性名称
     */
    public String getAttributeValueName() {
        return attributeValueName;
    }

    /**
     * 设置属性名称
     *
     * @param attributeValueName 属性名称
     */
    public void setAttributeValueName(String attributeValueName) {
        this.attributeValueName = attributeValueName;
    }

    /**
     * 获取说对应关系id
     *
     * @return rela_id - 说对应关系id
     */
    public Integer getRelaId() {
        return relaId;
    }

    /**
     * 设置说对应关系id
     *
     * @param relaId 说对应关系id
     */
    public void setRelaId(Integer relaId) {
        this.relaId = relaId;
    }

    /**
     * 获取产品id
     *
     * @return product_code - 产品id
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置产品id
     *
     * @param productCode 产品id
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
     * 属性值编码
     * @return attribute_value_code
     */
    public String getAttributeValueCode() {
        return attributeValueCode;
    }

    /**
     * 
     * 属性值编码
     * @param attributeValueCode
     */
    public void setAttributeValueCode(String attributeValueCode) {
        this.attributeValueCode = attributeValueCode;
    }

    /**
     * 属性值
     * @return attribute_values
     */
    public String getAttributeValues() {
        return attributeValues;
    }

    /**
     * 属性值
     * @param attributeValues
     */
    public void setAttributeValues(String attributeValues) {
        this.attributeValues = attributeValues;
    }

    /**
     * 获取状态,0-失效,1-生效
     *
     * @return status - 状态,0-失效,1-生效
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态,0-失效,1-生效
     *
     * @param status 状态,0-失效,1-生效
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}