package com.jt.plt.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 可达鸭
 * 描述:产品信息配置表
 * @date 2018年3月2日 上午9:50:55
 */
@Table(name = "t_product_info")
@Data
public class ProductInfo implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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
     * 产品描述
     */
    @Column(name = "product_desc")
    private String productDesc;

    /**
     * 险类ID
     */
    @Column(name = "product_risk_class_id")
    private String productRiskClassId;
    /**
     * 保费计算类型：0-固定，1-浮动
     */
    @Column(name = "product_premium_type")
    private Integer productPremiumType;

    /**
     * 基础保费，如果保费计算类型=1，基础保费可以为0
     */
    @Column(name = "product_premium")
    private Float productPremium;

    /**
     * 保费计算公式id
     */
    @Column(name = "product_desgin_id")
    private Integer productDesginId;

    /**
     * 	新建产品	产品待提交	1
		编辑产品	产品待提交	1
		产品提交 	运营待编辑	2
		运营编辑	运营待提审	2
		运营提审 	待审核	3
		产品审核	审核中	3
		审核退回	退回配置	4
		审核退回	退回运营	5
		审核发布	已上架	6
		产品过期	已下架	7

     */
    @Column(name = "product_status")
    private String productStatus;
	/**
	 * 产品渠道编码
	 */
    @Column(name = "product_channel_code")
    private String productChannelCode;
    /**
     * 产品渠道名称
     */
    @Column(name = "product_channel_name")
    private String productChannelName;
    /**
     * 保费公式id
     */
    @Column(name = "premium_formula_id")
    private Integer premiumFormulaId;
    /**
     * 免赔额信息编码
     */
    @Column(name = "deductible_info_no")
    private Integer deductibleInfoNo;
    /**
     * 特殊条款编码
     * 
     */
    @Column(name = "special_clause_code")
    private Integer specialClauseCode;
    /**
     * 产品类型:“00-责任险,01-寿险类,以此类推”，非空字段。
     */
    @Column(name = "product_type")
    private String productType;
    /**
     * 产品配置模板字段,每款产品配置完成后系统\n生成html模板页，便于产品详情回显以及产品复制
     */
    @Column(name = "product_template_url")
    private String productTemplateUrl;
    /**
     * 保险公司协议号,对接时某些保险公司需要，非必填字段
     */
    @Column(name = "agreement_code")
    private String agreementCode;
    @Column(name = "create_user")
    private String createUser;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "is_record")
    private String isRecord;
    @Column(name = "is_optimal_tax")
    private String isOptimalTax;
    

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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
     * 获取产品描述
     *
     * @return product_desc - 产品描述
     */
    public String getProductDesc() {
        return productDesc;
    }

    /**
     * 设置产品描述
     *
     * @param productDesc 产品描述
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    /**
     * 获取险类ID
     *
     * @return product_risk_class_id - 险类ID
     */
    public String getProductRiskClassId() {
        return productRiskClassId;
    }

    /**
     * 设置险类ID
     *
     * @param productRiskClassId 险类ID
     */
    public void setProductRiskClassId(String productRiskClassId) {
        this.productRiskClassId = productRiskClassId;
    }

    /**
     * 获取保费计算类型：0-固定，1-浮动
     *
     * @return product_premium_type - 保费计算类型：0-固定，1-浮动
     */
    public Integer getProductPremiumType() {
        return productPremiumType;
    }

    /**
     * 设置保费计算类型：0-固定，1-浮动
     *
     * @param productPremiumType 保费计算类型：0-固定，1-浮动
     */
    public void setProductPremiumType(Integer productPremiumType) {
        this.productPremiumType = productPremiumType;
    }

    /**
     * 获取基础保费，如果保费计算类型=1，基础保费可以为0
     *
     * @return product_premium - 基础保费，如果保费计算类型=1，基础保费可以为0
     */
    public Float getProductPremium() {
        return productPremium;
    }

    /**
     * 设置基础保费，如果保费计算类型=1，基础保费可以为0
     *
     * @param productPremium 基础保费，如果保费计算类型=1，基础保费可以为0
     */
    public void setProductPremium(Float productPremium) {
        this.productPremium = productPremium;
    }

    /**
     * 获取保费计算公式id
     *
     * @return product_desgin_id - 保费计算公式id
     */
    public Integer getProductDesginId() {
        return productDesginId;
    }

    /**
     * 设置保费计算公式id
     *
     * @param productDesginId 保费计算公式id
     */
    public void setProductDesginId(Integer productDesginId) {
        this.productDesginId = productDesginId;
    }

    /**
     * 	新建产品	产品待提交	1
		编辑产品	产品待提交	1
		产品提交 	运营待编辑	2
		运营编辑	运营待提审	2
		运营提审 	待审核	3
		产品审核	审核中	3
		审核退回	退回配置	4
		审核退回	退回运营	5
		审核发布	已上架	6
		产品过期	已下架	7

     */
    public String getProductStatus() {
        return productStatus;
    }

    /**
     * 	新建产品	产品待提交	1
		编辑产品	产品待提交	1
		产品提交 	运营待编辑	2
		运营编辑	运营待提审	2
		运营提审 	待审核	3
		产品审核	审核中	3
		审核退回	退回配置	4
		审核退回	退回运营	5
		审核发布	已上架	6
		产品过期	已下架	7

     */
    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * @return product_channel_code
     */
    public String getProductChannelCode() {
        return productChannelCode;
    }

    /**
     * @param productChannelCode
     */
    public void setProductChannelCode(String productChannelCode) {
        this.productChannelCode = productChannelCode;
    }

    /**
     * @return product_channel_name
     */
    public String getProductChannelName() {
        return productChannelName;
    }

    /**
     * @param productChannelName
     */
    public void setProductChannelName(String productChannelName) {
        this.productChannelName = productChannelName;
    }
    
	public Integer getPremiumFormulaId() {
		return premiumFormulaId;
	}

	public void setPremiumFormulaId(Integer premiumFormulaId) {
		this.premiumFormulaId = premiumFormulaId;
	}

	public Integer getDeductibleInfoNo() {
		return deductibleInfoNo;
	}

	public void setDeductibleInfoNo(Integer deductibleInfoNo) {
		this.deductibleInfoNo = deductibleInfoNo;
	}

	public Integer getSpecialClauseCode() {
		return specialClauseCode;
	}

	public void setSpecialClauseCode(Integer specialClauseCode) {
		this.specialClauseCode = specialClauseCode;
	}
	
}