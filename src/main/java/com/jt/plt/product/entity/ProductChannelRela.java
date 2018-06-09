package com.jt.plt.product.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_product_channel_rela")
public class ProductChannelRela {
    /**
     * 序号
     */
    @Id
    @Column(name = "rela_id")
    private Integer relaId;

    /**
     * 渠道编码
     */
    @Column(name = "channel_no")
    private String channelNo;

    /**
     * 渠道名称
     */
    @Column(name = "channel_name")
    private String channelName;

    /**
     * 产品编码
     */
    @Column(name = "product_code")
    private String productCode;

    /**
     * 产品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 销售开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 销售截止时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 是否收取手续费
     */
    @Column(name = "is_rate")
    private String isRate;

    /**
     * 手续费类型：0-不收取，1-按照固定比例收；2-按照交易额收；3-按照笔数；4-按照区间收
     */
    @Column(name = "rate_type")
    private String rateType;

    /**
     * 销售状态,Y-在售,N-停售
     */
    @Column(name = "sale_status")
    private String saleStatus;

    /**
     * 销售协议号，对应保险公司给的产品的协议号
     */
    @Column(name = "agreement_no")
    private String agreementNo;

    /**
     * 业务归属机构编码
     */
    @Column(name = "company_code")
    private String companyCode;

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
     * 获取序号
     *
     * @return rela_id - 序号
     */
    public Integer getRelaId() {
        return relaId;
    }

    /**
     * 设置序号
     *
     * @param relaId 序号
     */
    public void setRelaId(Integer relaId) {
        this.relaId = relaId;
    }

    /**
     * 获取渠道编码
     *
     * @return channel_no - 渠道编码
     */
    public String getChannelNo() {
        return channelNo;
    }

    /**
     * 设置渠道编码
     *
     * @param channelNo 渠道编码
     */
    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    /**
     * 获取渠道名称
     *
     * @return channel_name - 渠道名称
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 设置渠道名称
     *
     * @param channelName 渠道名称
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
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
     * 获取销售开始时间
     *
     * @return start_time - 销售开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置销售开始时间
     *
     * @param startTime 销售开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取销售截止时间
     *
     * @return end_time - 销售截止时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置销售截止时间
     *
     * @param endTime 销售截止时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取是否收取手续费
     *
     * @return is_rate - 是否收取手续费
     */
    public String getIsRate() {
        return isRate;
    }

    /**
     * 设置是否收取手续费
     *
     * @param isRate 是否收取手续费
     */
    public void setIsRate(String isRate) {
        this.isRate = isRate;
    }

    /**
     * 获取手续费类型：0-不收取，1-按照固定比例收；2-按照交易额收；3-按照笔数；4-按照区间收
     *
     * @return rate_type - 手续费类型：0-不收取，1-按照固定比例收；2-按照交易额收；3-按照笔数；4-按照区间收
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * 设置手续费类型：0-不收取，1-按照固定比例收；2-按照交易额收；3-按照笔数；4-按照区间收
     *
     * @param rateType 手续费类型：0-不收取，1-按照固定比例收；2-按照交易额收；3-按照笔数；4-按照区间收
     */
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    /**
     * 获取销售状态,Y-在售,N-停售
     *
     * @return sale_status - 销售状态,Y-在售,N-停售
     */
    public String getSaleStatus() {
        return saleStatus;
    }

    /**
     * 设置销售状态,Y-在售,N-停售
     *
     * @param saleStatus 销售状态,Y-在售,N-停售
     */
    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    /**
     * 获取销售协议号，对应保险公司给的产品的协议号
     *
     * @return agreement_no - 销售协议号，对应保险公司给的产品的协议号
     */
    public String getAgreementNo() {
        return agreementNo;
    }

    /**
     * 设置销售协议号，对应保险公司给的产品的协议号
     *
     * @param agreementNo 销售协议号，对应保险公司给的产品的协议号
     */
    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    /**
     * 获取业务归属机构编码
     *
     * @return company_code - 业务归属机构编码
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * 设置业务归属机构编码
     *
     * @param companyCode 业务归属机构编码
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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


   
}