package com.jt.plt.product.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_insurance_liability")
public class InsuranceLiability implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    /**
	     * 责任编码
	     */
	    @Column(name = "insurance_liability_code")
	    private String insuranceLiabilityCode;

	    /**
	     * 责任名称
	     */
	    @Column(name = "insurance_liability_name")
	    private String insuranceLiabilityName;

	    /**
	     * 责任类型(0-可选；1-必选)，表示该责任对所属方案来说是可选或者必选
	     */
	    @Column(name = "insurance_liability_type")
	    private String insuranceLiabilityType;

	    /**
	     * 责任描述
	     */
	    @Column(name = "insurance_liability_desc")
	    private String insuranceLiabilityDesc;

	    /**
	     * 所属险种编码
	     */
	    @Column(name = "risk_code")
	    private String riskCode;

	    /**
	     * 所属险种名称
	     */
	    @Column(name = "risk_name")
	    private String riskName;

	    /**
	     * 创建时间
	     */
	    @Column(name = "create_time")
	    private Date createTime;

	    /**
	     * 创建人
	     */
	    @Column(name = "create_user")
	    private String createUser;

	    /**
	     * 修改时间
	     */
	    @Column(name = "modify_time")
	    private Date modifyTime;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    /**
	     * 获取责任编码
	     *
	     * @return insurance_liability_code - 责任编码
	     */
	    public String getInsuranceLiabilityCode() {
	        return insuranceLiabilityCode;
	    }

	    /**
	     * 设置责任编码
	     *
	     * @param
	     */
	    public void setInsuranceLiabilityCode(String insuranceLiabilityCode) {
	        this.insuranceLiabilityCode = insuranceLiabilityCode;
	    }

	    /**
	     * 获取责任名称
	     *
	     * @return insurance_liability_name - 责任名称
	     */
	    public String getInsuranceLiabilityName() {
	        return insuranceLiabilityName;
	    }

	    /**
	     * 设置责任名称
	     *
	     * @param insuranceLiabilityName 责任名称
	     */
	    public void setInsuranceLiabilityName(String insuranceLiabilityName) {
	        this.insuranceLiabilityName = insuranceLiabilityName;
	    }

	    /**
	     * 获取责任类型(0-可选；1-必选)，表示该责任对所属方案来说是可选或者必选
	     *
	     * @return insurance_liability_type - 责任类型(0-可选；1-必选)，表示该责任对所属方案来说是可选或者必选
	     */
	    public String getInsuranceLiabilityType() {
	        return insuranceLiabilityType;
	    }

	    /**
	     * 设置责任类型(0-可选；1-必选)，表示该责任对所属方案来说是可选或者必选
	     *
	     * @param insuranceLiabilityType 责任类型(0-可选；1-必选)，表示该责任对所属方案来说是可选或者必选
	     */
	    public void setInsuranceLiabilityType(String insuranceLiabilityType) {
	        this.insuranceLiabilityType = insuranceLiabilityType;
	    }

	    /**
	     * 获取责任描述
	     *
	     * @return insurance_liability_desc - 责任描述
	     */
	    public String getInsuranceLiabilityDesc() {
	        return insuranceLiabilityDesc;
	    }

	    /**
	     * 设置责任描述
	     *
	     * @param insuranceLiabilityDesc 责任描述
	     */
	    public void setInsuranceLiabilityDesc(String insuranceLiabilityDesc) {
	        this.insuranceLiabilityDesc = insuranceLiabilityDesc;
	    }

	    /**
	     * 获取所属险种
	     *
	     * @return risk_code - 所属险种
	     */
	    public String getRiskCode() {
	        return riskCode;
	    }


	    /**
	     * 设置所属险种
	     *
	     * @param riskCode 所属险种
	     */
	    public void setRiskCode(String riskCode) {
	        this.riskCode = riskCode;
	    }

	    /**
	     * 获取所属险种名称
	     *
	     * @return risk_name - 所属险种名称
	     */
	    public String getRiskName() {
	        return riskName;
	    }

	    /**
	     * 设置所属险种名称
	     *
	     * @param riskName 所属险种名称
	     */
	    public void setRiskName(String riskName) {
	        this.riskName = riskName;
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

	    /**
	     * 获取创建人
	     *
	     * @return create_user - 创建人
	     */
	    public String getCreateUser() {
	        return createUser;
	    }

	    /**
	     * 设置创建人
	     *
	     * @param createUser 创建人
	     */
	    public void setCreateUser(String createUser) {
	        this.createUser = createUser;
	    }

	    /**
	     * 获取修改时间
	     *
	     * @return modify_time - 修改时间
	     */
	    public Date getModifyTime() {
	        return modifyTime;
	    }

	    /**
	     * 设置修改时间
	     *
	     * @param modifyTime 修改时间
	     */
	    public void setModifyTime(Date modifyTime) {
	        this.modifyTime = modifyTime;
	    }

	    @Override
	    public String toString() {
	        return "InsuranceLiability{" +
	                "id=" + id +
	                ", insuranceLiabilityCode='" + insuranceLiabilityCode + '\'' +
	                ", insuranceLiabilityName='" + insuranceLiabilityName + '\'' +
	                ", insuranceLiabilityType='" + insuranceLiabilityType + '\'' +
	                ", insuranceLiabilityDesc='" + insuranceLiabilityDesc + '\'' +
	                ", riskCode='" + riskCode + '\'' +
	                ", riskName='" + riskName + '\'' +
	                ", createTime=" + createTime +
	                ", createUser='" + createUser + '\'' +
	                ", modifyTime=" + modifyTime +
	                '}';
	    }
}