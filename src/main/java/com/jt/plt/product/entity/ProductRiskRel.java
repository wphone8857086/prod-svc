package com.jt.plt.product.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "product_risk_rel")
public class ProductRiskRel {
    /**
     * 记录序号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 产品编码
     */
    @Column(name = "product_code")
    private String productCode;

    /**
     * 险种编码
     */
    @Column(name = "risk_code")
    private String riskCode;

    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 险种名称
     */
    @Column(name = "risk_name")
    private String riskName;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 获取记录序号
     *
     * @return id - 记录序号
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置记录序号
     *
     * @param id 记录序号
     */
    public void setId(Long id) {
        this.id = id;
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

    /**
     * 获取险种编码
     *
     * @return riskCode - 险种编码
     */
    public String getRiskCode() {
        return riskCode;
    }

    /**
     * 设置险种编码
     *
     * @param riskCode 险种编码
     */
    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    /**
     * 获取产品名称
     *
     * @return product_name - 产品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置产品名称
     *
     * @param productName 产品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
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