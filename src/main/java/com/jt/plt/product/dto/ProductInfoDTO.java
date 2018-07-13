package com.jt.plt.product.dto;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.jt.plt.product.dto.program.ProgramInfoBean;

/**
  * 描述：产品详情数据类
  * 类名称：ProductInfoDTO
  * 作者： wangyang
  * 版本：1.0
  * 修改：
  * 创建日期：2018-04-02
  * 版权：江泰保险经纪股份有限公司
  */
public class ProductInfoDTO {
    /**
     * 产品编码
     */
    private String code;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品描述
     */
    private String description;
    /**
     * 是否含有因子
     */
    private String hasFactors;
    /**
     * 因子集合
     */
    @JsonInclude(value = Include.NON_NULL)
    private List<Factor> factorList;
    /**
     * 图片地址
     */
    @JsonInclude(value = Include.NON_NULL)
    private String logoUrl;

    /**
     * 承保公司列表
     */
    @JsonInclude(value = Include.NON_NULL)
    private List<InusureCompanyDTO> inusureCompanyList;

    /**
     * 销售区域名称列表, 逗号分隔的城市列表
     */
    @JsonInclude(value = Include.NON_NULL)
    private String onsaleAreas;

    /**
     *  条款文件名
     */
    @JsonInclude(value = Include.NON_NULL)
    private String clauseFileName;

    /**
     * 条款文件下载url
     */
    @JsonInclude(value = Include.NON_NULL)
    private String clauseFileUrl;
	/**
	 * 是否还有附加险 0-不含附加险，1-含有附加险
	 */
	private String hasAttachRisk;
    /**
     * 方案列表
     */
    @JsonInclude(value = Include.NON_NULL)
    private List<ProgramInfoBean> progromList;
    

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

	/**
	 * @return the progList
	 */
	public List<ProgramInfoBean> getProgromList() {
		return progromList;
	}

	/**
	 * @param progList the progList to set
	 */
	public void setProgromList(List<ProgramInfoBean> progromList) {
		this.progromList = progromList;
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
	
}
