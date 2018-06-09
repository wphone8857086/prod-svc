package com.jt.plt.product.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

/**
 * 
 * @author 可达鸭
 * 描述:浮动方案费率表
 * @date 2018年5月8日 下午4:08:23
 */
@Table(name = "t_float_rate")
public class FloatRate implements Serializable {

    private static final long serialVersionUID = -1L;
    /**
     * 自增id
     */
	@JsonInclude(value = Include.NON_NULL)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * 方案id（浮动）
     */
	@JsonInclude(value = Include.NON_NULL)
    @Column(name = "program_code")
    private String programCode;

    /**
     * 限额id
     */
	@JsonInclude(value = Include.NON_NULL)
    @Column(name = "limit_code")
    private String limitCode;

    /**
     * 最小值区间符号  0-（   1-[
     */
    @Column(name = "min_scope_sign")
    private Integer minScopeSign;

    /**
     * 最小限额值
     */
    @Column(name = "min_limit_value")
    private Double minLimitValue;

    /**
     * 最小值区间符号  0-）   1-]
     */
    @Column(name = "max_scope_sign")
    private Integer maxScopeSign;

    /**
     * 最大值
     */
    @Column(name = "max_limit_value")
    private Double maxLimitValue;

    /**
     * 费率
     */
    private Double rate;

    /**
     * 获取自增id
     *
     * @return id - 自增id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增id
     *
     * @param id 自增id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取方案id（浮动）
     *
     * @return program_code - 方案id（浮动）
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * 设置方案id（浮动）
     *
     * @param programCode 方案id（浮动）
     */
    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    /**
     * 获取限额id
     *
     * @return limit_code - 限额id
     */
    public String getLimitCode() {
        return limitCode;
    }

    /**
     * 设置限额id
     *
     * @param limitCode 限额id
     */
    public void setLimitCode(String limitCode) {
        this.limitCode = limitCode;
    }

    /**
     * 获取最小值区间符号  0-（   1-[
     *
     * @return min_scope_sign - 最小值区间符号  0-（   1-[
     */
    public Integer getMinScopeSign() {
        return minScopeSign;
    }

    /**
     * 设置最小值区间符号  0-（   1-[
     *
     * @param minScopeSign 最小值区间符号  0-（   1-[
     */
    public void setMinScopeSign(Integer minScopeSign) {
        this.minScopeSign = minScopeSign;
    }

    /**
     * 获取最小限额值
     *
     * @return min_limit_value - 最小限额值
     */
    public Double getMinLimitValue() {
        return minLimitValue;
    }

    /**
     * 设置最小限额值
     *
     * @param minLimitValue 最小限额值
     */
    public void setMinLimitValue(Double minLimitValue) {
        this.minLimitValue = minLimitValue;
    }

    /**
     * 获取最小值区间符号  0-）   1-]
     *
     * @return max_scope_sign - 最小值区间符号  0-）   1-]
     */
    public Integer getMaxScopeSign() {
        return maxScopeSign;
    }

    /**
     * 设置最小值区间符号  0-）   1-]
     *
     * @param maxScopeSign 最小值区间符号  0-）   1-]
     */
    public void setMaxScopeSign(Integer maxScopeSign) {
        this.maxScopeSign = maxScopeSign;
    }

    /**
     * 获取最大值
     *
     * @return max_limit_value - 最大值
     */
    public Double getMaxLimitValue() {
        return maxLimitValue;
    }

    /**
     * 设置最大值
     *
     * @param maxLimitValue 最大值
     */
    public void setMaxLimitValue(Double maxLimitValue) {
        this.maxLimitValue = maxLimitValue;
    }

    /**
     * 获取费率
     *
     * @return rate - 费率
     */
    public Double getRate() {
        return rate;
    }

    /**
     * 设置费率
     *
     * @param rate 费率
     */
    public void setRate(Double rate) {
        this.rate = rate;
    }
}