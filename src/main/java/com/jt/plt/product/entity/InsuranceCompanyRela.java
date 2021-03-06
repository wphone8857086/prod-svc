package com.jt.plt.product.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_insurance_company_rela")
public class InsuranceCompanyRela implements Serializable {

    private static final long serialVersionUID = -1L;
    /**
     * 关系id
     */
    @Id
    @Column(name = "rela_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 保险公司名称
     */
    @Column(name = "company_name")
    private String companyName;

    /**
     * 关系类型，1-承保,2-出单,3-解付,4-结算
     */
    @Column(name = "rela_type")
    private String relaType;

    /**
     * 状态,0-失效,1-启用
     */
    private String status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取关系id
     *
     * @return rela_id - 关系id
     */
    public Integer getRelaId() {
        return relaId;
    }

    /**
     * 设置关系id
     *
     * @param relaId 关系id
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
     * 获取保险公司名称
     *
     * @return company_name - 保险公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置保险公司名称
     *
     * @param companyName 保险公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取关系类型，1-承保,2-出单,3-解付,4-结算
     *
     * @return rela_type - 关系类型，1-承保,2-出单,3-解付,4-结算
     */
    public String getRelaType() {
        return relaType;
    }

    /**
     * 设置关系类型，1-承保,2-出单,3-解付,4-结算
     *
     * @param relaType 关系类型，1-承保,2-出单,3-解付,4-结算
     */
    public void setRelaType(String relaType) {
        this.relaType = relaType;
    }

    /**
     * 获取状态,0-失效,1-启用
     *
     * @return status - 状态,0-失效,1-启用
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态,0-失效,1-启用
     *
     * @param status 状态,0-失效,1-启用
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