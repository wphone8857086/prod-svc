package com.jt.plt.product.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_insurance_program")
public class InsuranceProgram implements Serializable {
private static final long serialVersionUID = -1L;
    /**
     * 序号，自增长
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 产品方案id
     */
    @Column(name = "program_code")
    private String programCode;

    /**
     * 方案名称
     */
    @Column(name = "program_name")
    private String programName;

    /**
     * 方案描述
     */
    @Column(name = "program_desc")
    private String programDesc;

    /**
     * 所属产品代码
     */
    @Column(name = "product_code")
    private String productCode;

    @Column(name = "product_name")
    private String productName;

    /**
     * 基础保费计算类型：0-无，计价在产品上，1-固定基础保费（例如2000元），2-固定费率（保额*费率）,3-浮动费率（根据保额区间浮动）
     */
    @Column(name = "basic_premium_type")
    private String basicPremiumType;

    /**
     * 方案基础保费
     */
    @Column(name = "basic_premium")
    private Double basicPremium;
    /**
	 * 主附加险标识,M-主险，A-附加
	 */
    @Column(name = "risk_flag")
	private String riskFlag;
	/**
	 * 主附加险标识,M-主险，A-附加
	 */
	public String getRiskFlag() {
		return riskFlag;
	}
	/**
	 * 主附加险标识,M-主险，A-附加
	 */
	public void setRiskFlag(String riskFlag) {
		this.riskFlag = riskFlag;
	}
    /**
     * 状态
     */
    private String status;

    /**
     * 生效时间
     */
    @Column(name = "valid_time")
    private Date validTime;

    /**
     * 失效时间
     */
    @Column(name = "unvalid_time")
    private Date unvalidTime;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    /**
     * 创建时间
     */
    @Column(name = "create_user")
    private String createUser;
    /**
     * 基础保费测算公式
     */
    @Column(name = "basic_premium_factor")
    private String basicPremiumFactor;

    /**
     * 分组id
     */
    @Column(name = "liability_group_id")
    private String liabilityGroupId;

    /**
     * 分组名称
     */
    @Column(name = "liability_group_name")
    private String liabilityGroupName;

    /**
     * 显示序号
     */
    @Column(name = "display_no")
    private Integer displayNo;

    /**
     * 显示标识,0-不显示,1-显示
     */
    @Column(name = "is_display")
    private String isDisplay;

    /**
     * 扩展字段
     */
    private String reserved;

    /**
     * 获取序号，自增长
     *
     * @return insurance_program_id - 序号，自增长
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置序号，自增长
     *
     * @param id 序号，自增长
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取产品方案编码
     *
     * @return programCode - 产品方案编码
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * 设置产品方案编码
     *
     * @param programCode 产品方案编码
     */
    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    /**
     * 获取方案名称
     *
     * @return program_name - 方案名称
     */
    public String getProgramName() {
        return programName;
    }

    /**
     * 设置方案名称
     *
     * @param programName 方案名称
     */
    public void setProgramName(String programName) {
        this.programName = programName;
    }

    /**
     * 获取方案描述
     *
     * @return program_desc - 方案描述
     */
    public String getProgramDesc() {
        return programDesc;
    }

    /**
     * 设置方案描述
     *
     * @param programDesc 方案描述
     */
    public void setProgramDesc(String programDesc) {
        this.programDesc = programDesc;
    }

    /**
     * 获取所属产品代码
     *
     * @return product_code - 所属产品代码
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置所属产品代码
     *
     * @param productCode 所属产品代码
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 产品名称
     * @return product_name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 产品名称
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取基础保费计算类型：0-无，计价在产品上，1-固定基础保费（例如2000元），2-固定费率（保额*费率）,3-浮动费率（根据保额区间浮动）
     *
     * @return basic_premium_type - 基础保费计算类型：0-无，计价在产品上，1-固定基础保费（例如2000元），2-固定费率（保额*费率）,3-浮动费率（根据保额区间浮动）
     */
    public String getBasicPremiumType() {
        return basicPremiumType;
    }

    /**
     * 设置基础保费计算类型：0-无，计价在产品上，1-固定基础保费（例如2000元），2-固定费率（保额*费率）,3-浮动费率（根据保额区间浮动）
     *
     * @param basicPremiumType 基础保费计算类型：0-无，计价在产品上，1-固定基础保费（例如2000元），2-固定费率（保额*费率）,3-浮动费率（根据保额区间浮动）
     */
    public void setBasicPremiumType(String basicPremiumType) {
        this.basicPremiumType = basicPremiumType;
    }

    /**
     * 基础保费
     * @return basic_premium
     */
    public Double getBasicPremium() {
        return basicPremium;
    }

    /**
     * 基础保费
     * @param basicPremium
     */
    public void setBasicPremium(Double basicPremium) {
        this.basicPremium = basicPremium;
    }

    /**
     * 获取状态
     *
     * @return status - 状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取生效时间
     *
     * @return valid_time - 生效时间
     */
    public Date getValidTime() {
        return validTime;
    }

    /**
     * 设置生效时间
     *
     * @param validTime 生效时间
     */
    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    /**
     * 获取失效时间
     *
     * @return unvalid_time - 失效时间
     */
    public Date getUnvalidTime() {
        return unvalidTime;
    }

    /**
     * 设置失效时间
     *
     * @param unvalidTime 失效时间
     */
    public void setUnvalidTime(Date unvalidTime) {
        this.unvalidTime = unvalidTime;
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

    /**
     * 获取创建时间
     *
     * @return create_user - 创建时间
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建时间
     *
     * @param createUser 创建时间
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     *
     * @return basic_premium_factor
     */
    public String getBasicPremiumFactor() {
        return basicPremiumFactor;
    }

    /**
     * @param basicPremiumFactor
     */
    public void setBasicPremiumFactor(String basicPremiumFactor) {
        this.basicPremiumFactor = basicPremiumFactor;
    }

    /**
     * 获取分组id
     *
     * @return liability_group_id - 分组id
     */
    public String getLiabilityGroupId() {
        return liabilityGroupId;
    }

    /**
     * 设置分组id
     *
     * @param liabilityGroupId 分组id
     */
    public void setLiabilityGroupId(String liabilityGroupId) {
        this.liabilityGroupId = liabilityGroupId;
    }

    /**
     * @return liability_group_name
     */
    public String getLiabilityGroupName() {
        return liabilityGroupName;
    }

    /**
     * @param liabilityGroupName
     */
    public void setLiabilityGroupName(String liabilityGroupName) {
        this.liabilityGroupName = liabilityGroupName;
    }

    /**
     * 获取显示序号
     *
     * @return display_no - 显示序号
     */
    public Integer getDisplayNo() {
        return displayNo;
    }

    /**
     * 设置显示序号
     *
     * @param displayNo 显示序号
     */
    public void setDisplayNo(Integer displayNo) {
        this.displayNo = displayNo;
    }

    /**
     * 获取显示标识,0-不显示,1-显示
     *
     * @return is_display - 显示标识,0-不显示,1-显示
     */
    public String getIsDisplay() {
        return isDisplay;
    }

    /**
     * 设置显示标识,0-不显示,1-显示
     *
     * @param isDisplay 显示标识,0-不显示,1-显示
     */
    public void setIsDisplay(String isDisplay) {
        this.isDisplay = isDisplay;
    }

    /**
     * 获取扩展字段
     *
     * @return reserved - 扩展字段
     */
    public String getReserved() {
        return reserved;
    }

    /**
     * 设置扩展字段
     *
     * @param reserved 扩展字段
     */
    public void setReserved(String reserved) {
        this.reserved = reserved;
    }

    @Override
    public String toString() {
        return "InsuranceProgram{" +
                "id=" + id +
                ", programCode='" + programCode + '\'' +
                ", programName='" + programName + '\'' +
                ", programDesc='" + programDesc + '\'' +
                ", productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", basicPremiumType='" + basicPremiumType + '\'' +
                ", basicPremium=" + basicPremium +
                ", status='" + status + '\'' +
                ", validTime=" + validTime +
                ", unvalidTime=" + unvalidTime +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", createUser='" + createUser + '\'' +
                ", basicPremiumFactor='" + basicPremiumFactor + '\'' +
                ", liabilityGroupId='" + liabilityGroupId + '\'' +
                ", liabilityGroupName='" + liabilityGroupName + '\'' +
                ", displayNo=" + displayNo +
                ", isDisplay='" + isDisplay + '\'' +
                ", reserved='" + reserved + '\'' +
                '}';
    }
}