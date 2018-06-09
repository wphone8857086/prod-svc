package com.jt.plt.product.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Table(name = "t_liability_limit")
public class LiabilityLimit implements Serializable {

    private static final long serialVersionUID = -1L;
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 责任限额code
     */
    @Column(name = "liability_limit_code")
    private String liabilityLimitCode;

    /**
     * 责任限额名称
     */
    @Column(name = "liability_limit_name")
    private String liabilityLimitName;

    /**
     * 责任限额描述
     */
    @Column(name = "liability_limit_desc")
    private String liabilityLimitDesc;

    /**
     * 所属责任编码
     */
    @Column(name = "insurance_liability_code")
    private String insuranceLiabilityCode;

    /**
     * 责任名称
     */
    @Column(name = "insurance_liability_name")
    private String insuranceLiabilityName;

    /**
     * 责任生效起期
     */
    @Column(name = "limit_begin_date")
    private Date limitBeginDate;

    /**
     * 责任生效止期
     */
    @Column(name = "limit_end_date")
    private Date limitEndDate;

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
     * 标记
     * 对应的限额值是否参与上下限浮动 ：0-不参与， 1-参与上限，2-参与下限
     */
    @Column(name= "mark")
    private String mark;
    

   

	



    /**
     * 获取责任限额名称
     *
     * @return liability_limit_name - 责任限额名称
     */
    public String getLiabilityLimitName() {
        return liabilityLimitName;
    }

    /**
     * 设置责任限额名称
     *
     * @param liabilityLimitName 责任限额名称
     */
    public void setLiabilityLimitName(String liabilityLimitName) {
        this.liabilityLimitName = liabilityLimitName;
    }

    /**
     * 获取责任限额描述
     *
     * @return liability_limit_desc - 责任限额描述
     */
    public String getLiabilityLimitDesc() {
        return liabilityLimitDesc;
    }

    /**
     * 设置责任限额描述
     *
     * @param liabilityLimitDesc 责任限额描述
     */
    public void setLiabilityLimitDesc(String liabilityLimitDesc) {
        this.liabilityLimitDesc = liabilityLimitDesc;
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
     * 获取责任生效起期
     *
     * @return limit_begin_date - 责任生效起期
     */
    public Date getLimitBeginDate() {
        return limitBeginDate;
    }

    /**
     * 设置责任生效起期
     *
     * @param limitBeginDate 责任生效起期
     */
    public void setLimitBeginDate(Date limitBeginDate) {
        this.limitBeginDate = limitBeginDate;
    }

    /**
     * 获取责任生效止期
     *
     * @return limit_end_date - 责任生效止期
     */
    public Date getLimitEndDate() {
        return limitEndDate;
    }

    /**
     * 设置责任生效止期
     *
     * @param limitEndDate 责任生效止期
     */
    public void setLimitEndDate(Date limitEndDate) {
        this.limitEndDate = limitEndDate;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLiabilityLimitCode() {
        return liabilityLimitCode;
    }

    public void setLiabilityLimitCode(String liabilityLimitCode) {
        this.liabilityLimitCode = liabilityLimitCode;
    }

    public String getInsuranceLiabilityCode() {
        return insuranceLiabilityCode;
    }

    public void setInsuranceLiabilityCode(String insuranceLiabilityCode) {
        this.insuranceLiabilityCode = insuranceLiabilityCode;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}