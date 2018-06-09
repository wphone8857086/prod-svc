package com.jt.plt.product.entity.calPremium;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 案件系统赔案数据结果
 * @Auther: wephone
 * @Date: 2018/6/4 13:55
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司 
 */
@Data
public class CaseSystemResult {

    /**
     * 报案号
     */
    private String reportNumber;

    /**
     * 案件号
     */
    private String  caseNo;

    /**
     * 报案人
     */
    private String informant;

    /**
     * 报案人电话
     */
    private String informanttel;

    /**
     * 报案时间
     */
    private Date reportTime;

    /**
     * 事故类型说明
     */
    private String accidentTypeInfo;

    /**
     * 出险时间
     */
    private Date riskTime;

    /**
     * 事故报损金额
     */
    private Double loss;

    /**
     * 索赔金额
     */
    private Double askamount;

    /**
     * 估损金额
     */
    private  Double evelamount;

    /**
     * 定损金额
     */
    private Double fixamount;

    /**
     * 赔款金额
     */
    private Double caseamount;

    /**
     * 服务单位
     */
    private String organ;
    /**
     *业务类别
     */
    private String channelAttributes;
    /**
     * 保险公司报案号
     */
    private String inscompReportNo;

    /**
     * 结案类型
     */
    private String endcasetype;
    /**
     * 支付赔款日期
     */
    private Date payClaimDate;
    /**
     * 免赔额
     */
    private String deductibleExcess;

    /**
     * 实际赔付金额
     */
    private Double actualAmountToPay;

    /**
     * 险类
     */
    private String kindCode;
    /**
     * 险种
     */
    private String riskCode;

    /**
     * 投保单号
     */
    private String policyHolderNum;

    /**
     * 保险起期
     */
    private Date startTime;

    /**
     * 保险止期
     */
    private Date stopTime;

    /**
     * 保单号
     */
    private String policyHolderName;

    /**
     * 结案时间
     */
    private Date endCaseTime;

    /**
     * 保险公司估损金额
     */
    private Double inscompEvelamount;

    /**
     * 公估估损金额
     */
    private Double gEvelamount;
    /**
     * 其他估损金额
     */
    private Double otherEvelamount;

    /**
     * 院方赔付比率
     */
    private String hospitalPayRate;

    /**
     * 案件环节
     */
    private String caseNode;

    /**
     * 请求编码
     */
    private String uuid;

    /**
     * 批次号
     */
    private String batchNo;

    /**
     * 查询状态
     */
    private String state;





}
