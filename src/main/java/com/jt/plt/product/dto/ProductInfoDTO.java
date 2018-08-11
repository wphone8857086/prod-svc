package com.jt.plt.product.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.jt.plt.product.dto.program.ProgramInfoBean;
import com.jt.plt.product.dto.rule.RuleDTO;
import com.jt.plt.product.vo.FactorVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
  * 描述：产品详情数据类
  * 类名称：ProductInfoDTO
  * 作者： wangyang
  * 版本：1.0
  * 修改：
  * 创建日期：2018-04-02
  * 版权：江泰保险经纪股份有限公司
  */
@ApiModel("产品详情")
public class ProductInfoDTO {
    /**
     * 产品编码
     */
	@ApiModelProperty("产品编码")
    private String code;

    /**
     * 产品名称
     */
	@ApiModelProperty("产品名称")
    private String name;
	@ApiModelProperty("产品类别 00-责任险,01-寿险类,以此类推”，非空字段")
	private String productType;
    /**
     * 产品描述
     */
	@ApiModelProperty("产品描述")
    private String description;
    /**
     * 是否含有因子
     */
	@ApiModelProperty("是否含有因子（0-是，1-否）")
    private String hasFactors;
    /**
     * 因子集合
     */
	@ApiModelProperty("因子集合(hasFactors为1时因子集合必须存在)")
    @JsonInclude(value = Include.NON_NULL)
    private List<Factor> factorList;

	private List<FactorVO> factorVOList;
    /**
     * 图片地址
     */
	@ApiModelProperty("图片地址")
    @JsonInclude(value = Include.NON_NULL)
    private String logoUrl;

    /**
     * 承保公司列表
     */
	@ApiModelProperty("承保公司列表")
    @JsonInclude(value = Include.NON_NULL)
    private List<InusureCompanyDTO> inusureCompanyList;

    /**
     * 销售区域名称列表, 逗号分隔的城市列表
     */
	@ApiModelProperty("销售区域名称列表, 逗号分隔的城市列表")
    @JsonInclude(value = Include.NON_NULL)
    private String onsaleAreas;

    /**
     *  条款文件名
     */
	@ApiModelProperty("条款文件名")
    @JsonInclude(value = Include.NON_NULL)
    private String clauseFileName;

    /**
     * 条款文件下载url
     */
	@ApiModelProperty("条款文件下载url")
    @JsonInclude(value = Include.NON_NULL)
    private String clauseFileUrl;
	/**
	 * 是否还有附加险 0-不含附加险，1-含有附加险
	 */
	@ApiModelProperty("是否还有附加险 0-不含附加险，1-含有附加险")
	private String hasAttachRisk;
    /**
     * 方案列表
     */
	@ApiModelProperty("方案列表")
    @JsonInclude(value = Include.NON_NULL)
    private List<ProgramInfoBean> programList;
	/**
     * 销售属性列表
     */
	@ApiModelProperty("销售属性列表")
    @JsonInclude(value = Include.NON_NULL)
	private List<RuleDTO> ruleList;

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public List<RuleDTO> getRuleList() {
		return ruleList;
	}

	public void setRuleList(List<RuleDTO> ruleList) {
		this.ruleList = ruleList;
	}

	/**
	 * @return the hasAttachRisk
	 */
	public String getHasAttachRisk() {
		return hasAttachRisk;
	}

	/**
	 * @param hasAttachRisk the hasAttachRisk to set
	 */
	public void setHasAttachRisk(String hasAttachRisk) {
		this.hasAttachRisk = hasAttachRisk;
	}


	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	public List<ProgramInfoBean> getProgramList() {
		return programList;
	}

	public void setProgramList(List<ProgramInfoBean> programList) {
		this.programList = programList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public List<InusureCompanyDTO> getInusureCompanyList() {
		return inusureCompanyList;
	}

	public void setInusureCompanyList(List<InusureCompanyDTO> inusureCompanyList) {
		this.inusureCompanyList = inusureCompanyList;
	}

    /**
     * 销售区域名称列表, 逗号分隔的城市列表
     */
	public String getOnsaleAreas() {
		return onsaleAreas;
	}

    /**
     * 销售区域名称列表, 逗号分隔的城市列表
     */
	public void setOnsaleAreas(String onsaleAreas) {
		this.onsaleAreas = onsaleAreas;
	}

    /**
     *  条款文件名
     */
	public String getClauseFileName() {
		return clauseFileName;
	}

    /**
     *  条款文件名
     */
	public void setClauseFileName(String clauseFileName) {
		this.clauseFileName = clauseFileName;
	}

	public String getClauseFileUrl() {
		return clauseFileUrl;
	}

	public void setClauseFileUrl(String clauseFileUrl) {
		this.clauseFileUrl = clauseFileUrl;
	}

	/**
     * 是否含有因子
     */
	public String getHasFactors() {
		return hasFactors;
	}
	/**
     * 是否含有因子
     */
	public void setHasFactors(String hasFactors) {
		this.hasFactors = hasFactors;
	}
	 /**
     * 因子集合
     */
	public List<Factor> getFactorList() {
		return factorList;
	}
	 /**
     * 因子集合
     */
	public void setFactorList(List<Factor> factorList) {
		this.factorList = factorList;
	}

	public List<FactorVO> getFactorVOList() {
		return factorVOList;
	}

	public void setFactorVOList(List<FactorVO> factorVOList) {
		this.factorVOList = factorVOList;
	}
}
