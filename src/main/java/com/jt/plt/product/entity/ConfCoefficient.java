package com.jt.plt.product.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author wephone
 */
@Table(name = "t_conf_coefficient")
public class ConfCoefficient implements Serializable {

    private static final long serialVersionUID = -1L;
	/**
	 * 序号
	 */
	@Id
	@Column(name = "rela_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer relaId;

	/**
	 * 因子关系id
	 */
	@Column(name = "factor_rela_id")
     private Long factorRelaId;


	/**
	 * 因子编码
	 */
	@Column(name = "factor_code")
	private String factorCode;

	/**
	 * 调整因子上限
	 */
	@Column(name = "factor_max")
	private Double factorMax;

	/**
	 * 调整因子下限
	 */
	@Column(name = "factor_min")
	private Double factorMin;

	
	/**
	 * 因子取值
	 */
	@Column(name = "factor_values")
	private String factorValues;

	/**
	 * 区间运算符类型 1- =a 2-方案 3- ( ) 4-( ] 5-[ ) 6-[ ] 7-(-∞, a) 8-(-∞, a] 9-(a, +∞) 10-[a, +∞)
	 */
	@Column(name = "operator_type")
	private String operatorType;

	/**
	 * 区间范围文本描述
	 * operator_desc
	 */
	@Column(name = "operator_desc")
	private String operatorDesc;

	/**
	 * 方案编码
	 */
	@Column(name = "program_code")
	private String programCode;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 修改时间
	 */
	@Column(name = "modify_time")
	private Date modifyTime;

	/**
	 * 状态，0-失效，1-生效
	 */
	private String status;

	@Column(name = "basic_premium_factor_rela_id")
	private String basicPremiumFactorRelaId;


	public Integer getRelaId() {
		return relaId;
	}

	public Long getFactorRelaId() {
		return factorRelaId;
	}

	public void setFactorRelaId(Long factorRelaId) {
		this.factorRelaId = factorRelaId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}


	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public void setRelaId(Integer relaId) {
		this.relaId = relaId;
	}
	/**
	 * 区间运算符类型 1- =a 2-方案 3- ( ) 4-( ] 5-[ ) 6-[ ] 7-(-∞, a) 8-(-∞, a] 9-(a, +∞) 10-[a, +∞)
	 */
	public String getOperatorType() {
		return operatorType;
	}
	/**
	 * 区间运算符类型 1- =a 2-方案 3- ( ) 4-( ] 5-[ ) 6-[ ] 7-(-∞, a) 8-(-∞, a] 9-(a, +∞) 10-[a, +∞)
	 */
	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}



	public String getOperatorDesc() {
		return operatorDesc;
	}

	public void setOperatorDesc(String operatorDesc) {
		this.operatorDesc = operatorDesc;
	}


	/**
	 * 获取因子编码
	 * @return factor_code - 因子id
	 */
	public String getFactorCode() {
		return factorCode;
	}


	/**
	 * 设置因子编码
	 * @param factorCode
	 *
	 */
	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}

	/**
	 * 获取调整因子上限
	 *
	 * @return factor_max - 调整因子上限
	 */
	public Double getFactorMax() {
		return factorMax;
	}

	/**
	 * 设置调整因子上限
	 *
	 * @param factorMax
	 *            调整因子上限
	 */
	public void setFactorMax(Double factorMax) {
		this.factorMax = factorMax;
	}

	/**
	 * 获取调整因子下限
	 *
	 * @return factor_min - 调整因子下限
	 */
	public Double getFactorMin() {
		return factorMin;
	}

	/**
	 * 设置调整因子下限
	 *
	 * @param factorMin
	 *            调整因子下限
	 */
	public void setFactorMin(Double factorMin) {
		this.factorMin = factorMin;
	}

	/**
	 * 获取因子取值
	 *
	 * @return factor_values - 因子取值
	 */
	public String getFactorValues() {
		return factorValues;
	}

	/**
	 * 设置因子取值
	 *
	 * @param factorValues
	 *            因子取值
	 */
	public void setFactorValues(String factorValues) {
		this.factorValues = factorValues;
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
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取修改时间
	 *
	 * @return modify_time - 修改时间
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * 设置修改时间
	 *
	 * @param modifyTime
	 *            修改时间
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 获取状态，0-失效，1-生效
	 *
	 * @return status - 状态，0-失效，1-生效
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 设置状态，0-失效，1-生效
	 *
	 * @param status
	 *            状态，0-失效，1-生效
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return basic_premium_factor_rela_id
	 */
	public String getBasicPremiumFactorRelaId() {
		return basicPremiumFactorRelaId;
	}

	/**
	 * @param basicPremiumFactorRelaId
	 */
	public void setBasicPremiumFactorRelaId(String basicPremiumFactorRelaId) {
		this.basicPremiumFactorRelaId = basicPremiumFactorRelaId;
	}


}