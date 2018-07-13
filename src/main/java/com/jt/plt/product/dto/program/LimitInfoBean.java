package com.jt.plt.product.dto.program;

import java.util.List;
/**
 * 
 * @author 可达鸭
 * 描述:限额实体
 * @date 2018年5月29日 上午11:27:12
 */
public class LimitInfoBean {
	/**
	 * 限额编码
	 */
	private String code;
	/**
	 * 限额名称
	 */
	private String name;
	/**
     * 标记
     * 对应的限额值是否参与上下限浮动 ：0-不参与， 1-参与上限，2-参与下限
     */
	private String mark;
	/**
	 * 限额值编码
	 */
	private String valueCode;
	/**
	 * 限额值(固定限额值)
	 */
	private String value;
	/**
	 * 浮动限额值集合
	 */
	private List<ValueBean> valueList;
	/**
	 * 保费（固定加浮动时存在）
	 */
	private Double premium;
	/**
	 * 是否浮动 0-不浮动 1-浮动
	 */
	private String isFloat;
	/**
	 * 浮动费率（浮动时存在）
	 */
	private List<RateInfoBean> rateList;
	/**
     * 标记
     * 对应的限额值是否参与上下限浮动 ：0-不参与， 1-参与上限，2-参与下限
     */
	public String getMark() {
		return mark;
	}
	/**
     * 标记
     * 对应的限额值是否参与上下限浮动 ：0-不参与， 1-参与上限，2-参与下限
     */
	public void setMark(String mark) {
		this.mark = mark;
	}
	/**
	 * 浮动限额值集合
	 */
	public List<ValueBean> getValueList() {
		return valueList;
	}
	/**
	 * 浮动限额值集合
	 */
	public void setValueList(List<ValueBean> valueList) {
		this.valueList = valueList;
	}
	/**
	 * @return the code
	 * 限额编码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param 限额编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 * 限额名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param 限额名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the valuecode
	 * 限额值编码
	 */
	public String getValueCode() {
		return valueCode;
	}
	/**
	 * @param 限额值编码
	 */
	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}
	/**
	 * @return the value
	 * 限额值(固定限额值)
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 限额值(固定限额值)
	 * @param 
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the isFloat
	 *  0-不浮动 1-浮动
	 */
	public String getIsFloat() {
		return isFloat;
	}
	/**
	 * 0-不浮动 1-浮动
	 * 
	 */
	public void setIsFloat(String isFloat) {
		this.isFloat = isFloat;
	}
	/**
	 * @return the rateList
	 * 浮动费率集合（浮动时存在）
	 */
	public List<RateInfoBean> getRateList() {
		return rateList;
	}
	/**
	 * @param 浮动费率集合（浮动时存在）
	 */
	public void setRateList(List<RateInfoBean> rateList) {
		this.rateList = rateList;
	}
	/**
	 * @return the premium
	 * 保费
	 */
	public Double getPremium() {
		return premium;
	}
	/**
	 * @param 保费
	 */
	public void setPremium(Double premium) {
		this.premium = premium;
	}
}
