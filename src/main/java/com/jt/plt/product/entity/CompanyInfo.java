package com.jt.plt.product.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
@Table(name = "t_company_info")
public class CompanyInfo {
    /**
     * 保险公司id
     */
	@ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    /**
     * 保险公司编码
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "company_code")
    private String companyCode;

    /**
     * 保险公司名称
     */
	@ApiModelProperty(value="保险公司名称")
    @Column(name = "company_name")
    private String companyName;

    /**
     * 保险公司简称
     */
	@ApiModelProperty(value="保险公司简称")
    @Column(name = "company_short")
    private String companyShort;

    /**
     * 保险公司父编码  0-无父级公司
     */
	@ApiModelProperty(value="保险公司父编码  0-无父级公司")
    @Column(name = "pre_company_code")
    private String preCompanyCode;

    /**
     * 父公司名称
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "pre_company_name")
    private String preCompanyName;

    /**
     * 保险公司级别
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "company_level")
    private String companyLevel;

    /**
     * 保险公司图标
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "company_logo")
    private String companyLogo;

    /**
     * 保险公司简介url
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "company_introduce_url")
    private String companyIntroduceUrl;

    /**
     * 所在省份
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "province_code")
    private String provinceCode;

    /**
     * 所在市
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "city_code")
    private String cityCode;

    /**
     * 所在县
     */
	@ApiModelProperty(hidden = true)
    private String countryCode;

    /**
     * 详细地址
     */
	@ApiModelProperty(hidden = true)
    private String address;

    /**
     * 所在省名称
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 说在市名称
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "city_name")
    private String cityName;

    /**
     * 所在县名称
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "country_name")
    private String countryName;

    /**
     * 状态：0-失效，1-有效
     */
	@ApiModelProperty(hidden = true)
    private String status;

    /**
     * 创建时间
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "create_user")
    private String createUser;
    
    /**
     * 是否为总公司 Y——是 N——不是
     */
	@ApiModelProperty("是否为总公司 Y——是 N——不是")
    @Transient
    private String isParent;
    /**
     * 
     * @return
     * 描述：公司id
     */
    public Long getId() {
		return id;
	}
    /**
     * 
     * @return
     * 描述：公司id
     */
	public void setId(Long id) {
		this.id = id;
	}

	public String getIsParent() {
		return isParent;
	}

	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}

	/**
     * 获取保险公司编码
     *
     * @return 保险公司编码
     */
    public String getCompanyCode() {
        return companyCode;
    }

    /**
     * 获取保险公司编码
     *
     * @param 获取保险公司编码
     */
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    /**
     * 获取保险公司名称
     *
     * @return company_name - 保险公司名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 设置保险公司名称
     *
     * @param companyName 保险公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取保险公司简称
     *
     * @return company_short - 保险公司简称
     */
    public String getCompanyShort() {
        return companyShort;
    }

    /**
     * 设置保险公司简称
     *
     * @param companyShort 保险公司简称
     */
    public void setCompanyShort(String companyShort) {
        this.companyShort = companyShort;
    }

    /**
     * 获取保险公司父编码
     *
     * @return pre_company_Code - 保险公司父编码
     */
    public String getPreCompanyCode() {
        return preCompanyCode;
    }

    /**
     * 设置保险公司父编码
     *
     * @param preCompanyCode 保险公司父编码
     */
    public void setPreCompanyCode(String preCompanyCode) {
        this.preCompanyCode = preCompanyCode;
    }

    /**
     * 获取父公司名称
     *
     * @return pre_company_name - 父公司名称
     */
    public String getPreCompanyName() {
        return preCompanyName;
    }

    /**
     * 设置父公司名称
     *
     * @param preCompanyName 父公司名称
     */
    public void setPreCompanyName(String preCompanyName) {
        this.preCompanyName = preCompanyName;
    }

    /**
     * 获取保险公司级别
     *
     * @return company_level - 保险公司级别
     */
    public String getCompanyLevel() {
        return companyLevel;
    }

    /**
     * 设置保险公司级别
     *
     * @param companyLevel 保险公司级别
     */
    public void setCompanyLevel(String companyLevel) {
        this.companyLevel = companyLevel;
    }

    /**
     * 获取保险公司图标
     *
     * @return company_logo - 保险公司图标
     */
    public String getCompanyLogo() {
        return companyLogo;
    }

    /**
     * 设置保险公司图标
     *
     * @param companyLogo 保险公司图标
     */
    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    /**
     * 获取保险公司简介url
     *
     * @return company_introduce_url - 保险公司简介url
     */
    public String getCompanyIntroduceUrl() {
        return companyIntroduceUrl;
    }

    /**
     * 设置保险公司简介url
     *
     * @param companyIntroduceUrl 保险公司简介url
     */
    public void setCompanyIntroduceUrl(String companyIntroduceUrl) {
        this.companyIntroduceUrl = companyIntroduceUrl;
    }

    /**
     * 获取所在省份
     *
     * @return province_code - 所在省份
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * 设置所在省份
     *
     * @param provinceCode 所在省份
     */
    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    /**
     * 获取所在市
     *
     * @return city_code - 所在市
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 设置所在市
     *
     * @param cityCode 所在市
     */
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    /**
     * 获取所在县
     *
     * @return country_code - 所在县
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * 设置所在县
     *
     * @param countryCode 所在县
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * 获取详细地址
     *
     * @return address - 详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置详细地址
     *
     * @param address 详细地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取所在省名称
     *
     * @return province_name - 所在省名称
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置所在省名称
     *
     * @param provinceName 所在省名称
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * 获取说在市名称
     *
     * @return city_name - 说在市名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置说在市名称
     *
     * @param cityName 说在市名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 获取所在县名称
     *
     * @return country_name - 所在县名称
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * 设置所在县名称
     *
     * @param countryName 所在县名称
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * 获取状态：0-失效，1-有效
     *
     * @return status - 状态：0-失效，1-有效
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：0-失效，1-有效
     *
     * @param status 状态：0-失效，1-有效
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