package com.jt.plt.product.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.io.Serializable;

@Table(name = "t_liability_limit_rela")
public class LiabilityLimitRela implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 子增主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	/**
     * 所属方案id
     */
    @Column(name = "program_code")
    private String programCode;

    /**
     * 方案名称
     */
    @Column(name = "program_name")
    private String programName;

    /**
     * 所属责任编码
     */
    @Column(name = "liability_code")
    private String liabilityCode;

    /**
     * 所属责任名称
     */
    @Column(name = "liability_name")
    private String liabilityName;

    /**
     * 限额编码
     */
    @Column(name = "limit_code")
    private String limitCode;

    /**
     * 限额名称
     */
    @Column(name = "limit_name")
    private String limitName;

    /**
     * 限额值编码
     */
    @Column(name = "limit_values_code")
    private String limitValuesCode;

    /**
     * 限额值或者限额描述
     */
    @Column(name = "limit_values")
    private String limitValues;

    /**
     * 状态：0-失效,1-生效
     */
    private String status;
    /**
     * 限额值对应保费    等方案为固定加浮动时 存在
     */
    private Double premium;
    /**
    * 是否参与保费计算 （0-不参与 ，1-参与）
    */
     @Column(name = "calculation_type")
    private Integer calculationType;
     /**
      * 建立险种和责任的关联关系
      */
     @Transient
     private String riskCode;
     @Transient
     private String riskName;
    /**
	 * @return the riskCode
	 */
	public String getRiskCode() {
		return riskCode;
	}
	/**
	 * @param riskCode the riskCode to set
	 */
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	/**
	 * @return the riskName
	 */
	public String getRiskName() {
		return riskName;
	}
	/**
	 * @param riskName the riskName to set
	 */
	public void setRiskName(String riskName) {
		this.riskName = riskName;
	}
	/**
     * 是否参与保费计算 （0-不参与 ，1-参与）
     */
    public Integer getCalculationType() {
		return calculationType;
	}
    /**
     * 是否参与保费计算 （0-不参与 ，1-参与）
     */
	public void setCalculationType(Integer calculationType) {
		this.calculationType = calculationType;
	}
	/**
     * 限额值对应保费    等方案为固定加浮动时 存在
     */
    public Double getPremium() {
		return premium;
	}
    /**
     * 限额值对应保费    等方案为固定加浮动时 存在
     */
	public void setPremium(Double premium) {
		this.premium = premium;
	}
	/**
     * 主键
     *
     * @return rela_id - 序号id
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     *
     * @param relaId 序号id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取所属方案编码
     *
     * @return product_program_id - 所属方案id
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * 设置所属方案编码
     *
     * @param productProgramId 所属方案id
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
     * 获取所属责任id
     *
     * @return liability_id - 所属责任id
     */
    public String getLiabilityCode() {
        return liabilityCode;
    }

    /**
     * 设置所属责任id
     *
     * @param liabilityId 所属责任id
     */
    public void setLiabilityCode(String liabilityCode) {
        this.liabilityCode = liabilityCode;
    }

    /**
     * 获取所属责任名称
     *
     * @return liability_name - 所属责任名称
     */
    public String getLiabilityName() {
        return liabilityName;
    }

    /**
     * 设置所属责任名称
     *
     * @param liabilityName 所属责任名称
     */
    public void setLiabilityName(String liabilityName) {
        this.liabilityName = liabilityName;
    }

    /**
     * 获取限额id
     *
     * @return limit_id - 限额id
     */
    public String getLimitCode() {
        return limitCode;
    }

    /**
     * 设置限额id
     *
     * @param limitId 限额id
     */
    public void setLimitCode(String limitCode) {
        this.limitCode = limitCode;
    }

    /**
     * 获取限额名称
     *
     * @return limit_name - 限额名称
     */
    public String getLimitName() {
        return limitName;
    }

    /**
     * 设置限额名称
     *
     * @param limitName 限额名称
     */
    public void setLimitName(String limitName) {
        this.limitName = limitName;
    }

    /**
     * 获取限额值id
     *
     * @return limit_values_id - 限额值id
     */
    public String getLimitValuesCode() {
        return limitValuesCode;
    }

    /**
     * 设置限额值id
     *
     * @param limitValuesId 限额值id
     */
    public void setLimitValuesCode(String limitValuesCode) {
        this.limitValuesCode = limitValuesCode;
    }

    /**
     * 获取限额值或者限额描述
     *
     * @return limit_values - 限额值或者限额描述
     */
    public String getLimitValues() {
        return limitValues;
    }

    /**
     * 设置限额值或者限额描述
     *
     * @param limitValues 限额值或者限额描述
     */
    public void setLimitValues(String limitValues) {
        this.limitValues = limitValues;
    }

    /**
     * 获取状态：0-失效,1-生效
     *
     * @return status - 状态：0-失效,1-生效
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：0-失效,1-生效
     *
     * @param status 状态：0-失效,1-生效
     */
    public void setStatus(String status) {
        this.status = status;
    }
}