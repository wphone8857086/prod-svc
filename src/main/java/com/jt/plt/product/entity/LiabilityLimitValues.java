package com.jt.plt.product.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_liability_limit_values")
public class LiabilityLimitValues implements Serializable {

    private static final long serialVersionUID = -1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 限额值编码
     */

    @Column(name = "liability_limit_values_code")
    private String liabilityLimitValuesCode;

    /**
     * 限额值类型:1-数值,2-文字描述，3-自定义
     */
    @Column(name = "liability_limit_values_type")
    private String liabilityLimitValuesType;

    /**
     * 限额值
     */
    @Column(name = "liability_limit_values")
    private Double liabilityLimitValues;

    /**
     * 责任限额id
     */
    @Column(name = "liability_limit_code")
    private String liabilityLimitCode;

    /**
     * 限额名称
     */
    @Column(name = "liability_limit_name")
    private String liabilityLimitName;

    /**
     * 状态：0-失效，1-生效
     */
    private String status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 限额值文本
     */
    @Column(name = "limit_values_desc")
    private String limitValuesDesc;

    /**
     * 获取限额值文本
     */
    public String getLimitValuesDesc() {
        return limitValuesDesc;
    }

    /**
     * 限额值文本
     */
    public void setLimitValuesDesc(String limitValuesDesc) {
        this.limitValuesDesc = limitValuesDesc;
    }

    /**
     * 获取限额值编码
     *
     * @return liability_limit_values_code - 限额值id
     */
    public String getLiabilityLimitValuesCode() {
        return liabilityLimitValuesCode;
    }


    /**
     * 获取限额值编码
     *
     * @param liabilityLimitValuesCode
     */
    public void setLiabilityLimitValuesCode(String liabilityLimitValuesCode) {
        this.liabilityLimitValuesCode = liabilityLimitValuesCode;
    }

    /**
     * 获取限额值类型:1-数值,2-文字描述，3-自定义
     *
     * @return liability_limit_values_type - 限额值类型:1-数值,2-文字描述，3-自定义
     */
    public String getLiabilityLimitValuesType() {
        return liabilityLimitValuesType;
    }

    /**
     * 设置限额值类型:1-数值,2-文字描述，3-自定义
     *
     * @param liabilityLimitValuesType 限额值类型:1-数值,2-文字描述，3-自定义
     */
    public void setLiabilityLimitValuesType(String liabilityLimitValuesType) {
        this.liabilityLimitValuesType = liabilityLimitValuesType;
    }

    /**
     * 获取限额值
     *
     * @return liability_limit_values - 限额值
     */
    public Double getLiabilityLimitValues() {
        return liabilityLimitValues;
    }

    /**
     * 设置限额值
     *
     * @param liabilityLimitValues 限额值
     */
    public void setLiabilityLimitValues(Double liabilityLimitValues) {
        this.liabilityLimitValues = liabilityLimitValues;
    }

    /**
     * 获取责任限额id
     *
     * @return liability_limit_id - 责任限额id
     */
    public String getLiabilityLimitCode() {
        return liabilityLimitCode;
    }

    /**
     * 设置责任限额id
     *
     * @param liabilityLimitCode 责任限额id
     */
    public void setLiabilityLimitCode(String liabilityLimitCode) {
        this.liabilityLimitCode = liabilityLimitCode;
    }


    /**
     * 获取限额名称
     *
     * @return liability_limit_name - 限额名称
     */
    public String getLiabilityLimitName() {
        return liabilityLimitName;
    }

    /**
     * 设置限额名称
     *
     * @param liabilityLimitName 限额名称
     */
    public void setLiabilityLimitName(String liabilityLimitName) {
        this.liabilityLimitName = liabilityLimitName;
    }

    /**
     * 获取状态：0-失效，1-生效
     *
     * @return status - 状态：0-失效，1-生效
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：0-失效，1-生效
     *
     * @param status 状态：0-失效，1-生效
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