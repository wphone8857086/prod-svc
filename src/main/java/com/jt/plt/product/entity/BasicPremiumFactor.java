package com.jt.plt.product.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_basic_premium_factor")
public class BasicPremiumFactor {
    /**
     * 序号
     */
    @Id
    @Column(name = "rela_id")
    private String relaId;

    /**
     * 方案id
     */
    @Column(name = "product_program_id")
    private String productProgramId;

    /**
     * 责任id
     */
    @Column(name = "Liability_id")
    private String liabilityId;
    
    /**
     * 限额id
     */
    @Column(name = "liability_limit_id")
    private String liabilityLimitId;
    
    @Column(name = "limit_values_id")
    private String limitValuesId;

    @Column(name = "factor_id")
    private String factorId;

    /**
     * 状态，0-失效，1-生效
     */
    private String status;

    @Column(name = "insurance_program_product_rela_id")
    private Integer insuranceProgramProductRelaId;

    /**
     * 获取序号
     *
     * @return rela_id - 序号
     */
    public String getRelaId() {
        return relaId;
    }

    /**
     * 设置序号
     *
     * @param relaId 序号
     */
    public void setRelaId(String relaId) {
        this.relaId = relaId;
    }

    public String getLiabilityLimitId() {
		return liabilityLimitId;
	}

	public void setLiabilityLimitId(String liabilityLimitId) {
		this.liabilityLimitId = liabilityLimitId;
	}

	/**
     * 获取方案id
     *
     * @return product_program_id - 方案id
     */
    public String getProductProgramId() {
        return productProgramId;
    }

    /**
     * 设置方案id
     *
     * @param productProgramId 方案id
     */
    public void setProductProgramId(String productProgramId) {
        this.productProgramId = productProgramId;
    }

    /**
     * 获取责任id
     *
     * @return Liability_id - 责任id
     */
    public String getLiabilityId() {
        return liabilityId;
    }

    /**
     * 设置责任id
     *
     * @param liabilityId 责任id
     */
    public void setLiabilityId(String liabilityId) {
        this.liabilityId = liabilityId;
    }

    /**
     * @return limit_values_id
     */
    public String getLimitValuesId() {
        return limitValuesId;
    }

    /**
     * @param limitValuesId
     */
    public void setLimitValuesId(String limitValuesId) {
        this.limitValuesId = limitValuesId;
    }

    /**
     * @return factor_id
     */
    public String getFactorId() {
        return factorId;
    }

    /**
     * @param factorId
     */
    public void setFactorId(String factorId) {
        this.factorId = factorId;
    }

    /**
     * 获取状态，0-失效，1-生效
     *
     * @return status - 状态，0-失效，1-生效
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态，0-失效，1-生效
     *
     * @param status 状态，0-失效，1-生效
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return insurance_program_product_rela_id
     */
    public Integer getInsuranceProgramProductRelaId() {
        return insuranceProgramProductRelaId;
    }

    /**
     * @param insuranceProgramProductRelaId
     */
    public void setInsuranceProgramProductRelaId(Integer insuranceProgramProductRelaId) {
        this.insuranceProgramProductRelaId = insuranceProgramProductRelaId;
    }
}