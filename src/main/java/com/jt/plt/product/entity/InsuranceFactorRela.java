package com.jt.plt.product.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_insurance_factor_rela")
public class InsuranceFactorRela implements Serializable {

    private static final long serialVersionUID = -1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factor_rela_id")
     private Long factorRelaId;
    /**
     * 因子编码
     */
    @Column(name = "factor_code")
    private String factorCode;

    /**
     * 产品编码
     */
    @Column(name = "product_code")
    private String productCode;


    /**
     * 因子类型名称
     */
    @Column(name = "factor_type_name")
    private String factorTypeName;

    /**
     * 状态，0-失效，1-生效
     */
    private String status;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

    @Column(name = "`premium_ formula_id`")
    private Integer premiumFormulaId;





	/**
     * 获取因子编码
     *
     * @return factor_code - 因子编码
     */
    public String getFactorCode() {
        return factorCode;
    }

    /**
     * 设置因子编码
     *
     * @param factorCode 因子编码
     */

     public void setFactorCode(String factorCode) {
        this.factorCode = factorCode;
    }

    /**
     * 获取产品编码
     *
     * @return product_code - 产品编码
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置产品编码
     *
     * @param productCode 产品编码
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Long getFactorRelaId() {
        return factorRelaId;
    }

    public void setFactorRelaId(Long factorRelaId) {
        this.factorRelaId = factorRelaId;
    }

    /**
     * 获取因子类型名称
     *
     * @return factor_name - 因子类型名称
     */
    public String getFactorTypeName() {
        return factorTypeName;
    }

    /**
     * 设置因子类型名称
     *
     * @param factorTypeName 因子类型名称
     */
    public void setFactorTypeName(String factorTypeName) {
        this.factorTypeName = factorTypeName;
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
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
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
     * @return premium_ formula_id
     */
    public Integer getPremiumFormulaId() {
        return premiumFormulaId;
    }

    /**
     * @param premiumFormulaId
     */
    public void setPremiumFormulaId(Integer premiumFormulaId) {
        this.premiumFormulaId = premiumFormulaId;
    }



    
    
}