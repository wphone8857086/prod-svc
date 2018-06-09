package com.jt.plt.product.dto.program;

public class RateInfoBean {
	/**
	 * 下限限额值
	 */
	private Double min;
	/**
	 * 上限限额值
	 */
	private Double max;
	/**
	 * 运算符标识 1.() 2.[) 3.(] 4.[]
	 */
	private String signCode;
	/**
	 * 费率
	 */
	private Double rate;
	/**
	 * @return the min
	 * 下限限额值
	 */
	public Double getMin() {
		return min;
	}
	/**
	 * @param 下限限额值
	 */
	public void setMin(Double min) {
		this.min = min;
	}
	/**
	 * @return the max
	 * 上限限额值
	 */
	public Double getMax() {
		return max;
	}
	/**
	 * @param 上限限额值
	 */
	public void setMax(Double max) {
		this.max = max;
	}
	/**
	 * @return the signCode
	 *  运算符标识 1.() 2.[) 3.(] 4.[]
	 */
	public String getSignCode() {
		return signCode;
	}
	/**
	 * @param  运算符标识 1.() 2.[) 3.(] 4.[]
	 */
	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}
	/**
	 * @return the rate
	 * 费率
	 */
	public Double getRate() {
		return rate;
	}
	/**
	 * @param 费率
	 */
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
}
