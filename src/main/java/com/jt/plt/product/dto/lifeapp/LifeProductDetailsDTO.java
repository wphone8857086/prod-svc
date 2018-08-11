package com.jt.plt.product.dto.lifeapp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.jt.plt.product.dto.Factor;
import com.jt.plt.product.dto.InusureCompanyDTO;
import com.jt.plt.product.dto.program.ProgramInfoBean;
import com.jt.plt.product.dto.rule.RuleDTO;
import com.jt.plt.product.entity.ProductPrivateInsurance;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author 可达鸭
 * 描述:
 * @date 2018年7月27日 下午4:50:33
 */
@Data
public class LifeProductDetailsDTO {
	private String code;
	private String name;
	private String agreementCode;
	private String productDesc;
	private String isRecord;
	private String isOptimalTax;
	//private String riskClassId;
	private String riskClass;
	private List<RiskDetails> riskInfoList;
	/**
     * 是否含有因子
     */
	@ApiModelProperty("是否含有因子（0-是，1-否）")
	@JsonInclude(value = Include.NON_NULL)
    private String hasFactors;
    /**
     * 因子集合
     */
	@ApiModelProperty("因子集合(hasFactors为1时因子集合必须存在)")
    @JsonInclude(value = Include.NON_NULL)
    private List<Factor> factorList;
    /**
     * 图片地址
     */
	@ApiModelProperty("图片地址")
    @JsonInclude(value = Include.NON_NULL)
    private String logoUrl;

    /**-
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
	 * 条款文件信息
	 */
	private List<ClauseFileInfo> clauseList; 
	/**
	 * 属性集合
	 */
	private List<AttrInfo> attrList;
	
}
