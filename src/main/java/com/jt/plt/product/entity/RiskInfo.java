package com.jt.plt.product.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author 可达鸭
 * 描述:险种实体类
 * @date 2018年1月30日 下午2:55:25
 */
@Table(name = "t_risk_info")
public class RiskInfo {
    /**
     * 险种编码
     */
    @Id
    private Long id;
    /**
     * 险种id
     */
    @Column(name = "risk_code")
    private String riskCode;

    /**
     * 险种名称
     */
    @Column(name = "risk_name")
    private String riskName;

    /**
     * 险种描述
     */
    @Column(name = "risk_desc")
    private String riskDesc;

    /**
     * 险类编码
     */
    @Column(name = "Insurance_classes_code")
    private String insuranceClassesCode;

    /**
     * 主附加险标识,M-主险，A-附加
     */
    @Column(name = "risk_flag")
    private String riskFlag;

    /**
     * 团个险标识，G-团险，A-个人
     */
    @Column(name = "risk_group_flag")
    private String riskGroupFlag;

    /**
     * 长短险标识,L-长期，S-短期
     */
    @Column(name = "risk_short_flag")
    private String riskShortFlag;

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
    @Column(name = "update_time")
    private Date updateTime;
    /**
     * 险种条款文件存储数据的id
     */
    @Transient
    private List<Long> fileIds;
    /**
     *  险种条款文件存储数据的id
	 * @return the fileIds
	 */
	public List<Long> getFileIds() {
		return fileIds;
	}
	/**
	 *  险种条款文件存储数据的id
	 * @param fileIds the fileIds to set
	 */
	public void setFileIds(List<Long> fileIds) {
		this.fileIds = fileIds;
	}
	/**
     * 
     * @return
     * 描述：险种id
     */
    public Long getId() {
		return id;
	}
    /**
     * 
     * @return
     * 描述：险种id
     */
	public void setId(Long id) {
		this.id = id;
	}

	/**
     * 获取险种id
     *
     * @return risk_id - 险种id
     */
    public String getRiskCode() {
        return riskCode;
    }

    /**
     * 设置险种id
     *
     * @param riskId 险种id
     */
    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    /**
     * 获取险种名称
     *
     * @return risk_name - 险种名称
     */
    public String getRiskName() {
        return riskName;
    }

    /**
     * 设置险种名称
     *
     * @param riskName 险种名称
     */
    public void setRiskName(String riskName) {
        this.riskName = riskName;
    }

    /**
     * 获取险种描述
     *
     * @return risk_desc - 险种描述
     */
    public String getRiskDesc() {
        return riskDesc;
    }

    /**
     * 设置险种描述
     *
     * @param riskDesc 险种描述
     */
    public void setRiskDesc(String riskDesc) {
        this.riskDesc = riskDesc;
    }

    /**
     * 获取险类id
     *
     * @return Insurance_classes_id - 险类id
     */
    public String getInsuranceClassesCode() {
        return insuranceClassesCode;
    }

    /**
     * 设置险类id
     *
     * @param insuranceClassesId 险类id
     */
    public void setInsuranceClassesCode(String insuranceClassesCode) {
        this.insuranceClassesCode = insuranceClassesCode;
    }

    /**
     * 获取主附加险标识,M-主险，A-附加
     *
     * @return risk_flag - 主附加险标识,M-主险，A-附加
     */
    public String getRiskFlag() {
        return riskFlag;
    }

    /**
     * 设置主附加险标识,M-主险，A-附加
     *
     * @param riskFlag 主附加险标识,M-主险，A-附加
     */
    public void setRiskFlag(String riskFlag) {
        this.riskFlag = riskFlag;
    }

    /**
     * 获取团个险标识，G-团险，A-个人
     *
     * @return risk_group_flag - 团个险标识，G-团险，A-个人
     */
    public String getRiskGroupFlag() {
        return riskGroupFlag;
    }

    /**
     * 设置团个险标识，G-团险，A-个人
     *
     * @param riskGroupFlag 团个险标识，G-团险，A-个人
     */
    public void setRiskGroupFlag(String riskGroupFlag) {
        this.riskGroupFlag = riskGroupFlag;
    }

    /**
     * 获取长短险标识,L-长期，S-短期
     *
     * @return risk_short_flag - 长短险标识,L-长期，S-短期
     */
    public String getRiskShortFlag() {
        return riskShortFlag;
    }

    /**
     * 设置长短险标识,L-长期，S-短期
     *
     * @param riskShortFlag 长短险标识,L-长期，S-短期
     */
    public void setRiskShortFlag(String riskShortFlag) {
        this.riskShortFlag = riskShortFlag;
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
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
	@Override
	public String toString() {
		return "RiskInfo [riskId=" + riskCode + ", riskName=" + riskName + ", riskDesc=" + riskDesc
				+ ", insuranceClassesId=" + insuranceClassesCode + ", riskFlag=" + riskFlag + ", riskGroupFlag="
				+ riskGroupFlag + ", riskShortFlag=" + riskShortFlag + ", createTime=" + createTime + ", createUser="
				+ createUser + ", updateTime=" + updateTime + "]";
	}
}