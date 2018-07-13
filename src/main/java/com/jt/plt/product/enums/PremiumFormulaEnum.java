package com.jt.plt.product.enums;
/**   
 * 描述：保费计算公式枚举类
 * 作者： wephone   
 * 创建日期： 2018年3月13日 上午11:20:17
 * 版权：江泰保险经纪股份有限公司
 */
public enum PremiumFormulaEnum {


	/**
	 * 产品编码数据库中不存在
	 */
	FAILED_PRODUCT_CODE(30703,"产品编码数据库中不存在"),

	/**
	 * 因子编码数据库不存在
	 */
	IS_NULL_FACTOR_RELA_ID(30704,"因子编码数据库不存在"),

	/**
	 * 因子编码类型不对
	 */
	IS_FAILD_FACTOR_RELA_ID(30705,"因子编码类型不对"),
	/**
	 * 因子费率输入值不符和要求
	 */
	IS_FAIL_FACTOR_VALUES(30706,"因子费率输入值不符和要求"),



	/**
	 * 计算公式与因子数量不匹配
	 */
	IS_FAILD_FORMULA_FACTORS(30712,"保费计算因子传值数量不匹配"),

	/**
	 * 产品方案id错误
	 */
	IS_FAILD_PRODUCTPROGRAMCODE(30713,"产品方案编码错误"),
	/**
	 * 没有匹配的方案数据值
	 */
	IS_FAILD_PROGRAMVALUES(30714,"没有匹配的方案数据值"),

	/**
	 * 当前产品没有配置保费计算公式
	 */
	IS_FAILD_PRODUCT_FORMULA(30715,"当前产品没有配置保费计算公式"),

	/**
	 * 含有未定义的变量
	 */
     IS_HAS_UNDEFINED_VARIABLE(30716,"含有未定义的变量"),

	/**
	 * 保费计算公式不能为空
	 */
	PARAMETER_IS_NULL_PREMIUM_DESIGN(10701,"保费计算公式不能为空"),
	/**
	 * 因子编码信息对象不能为空
	 */
	PARAMETER_IS_NULL_PREMIUMFORMULAVO(10702,"因子编码信息对象不能为空"),
	/**
	 * 因子编码不能为空
	 */
	PARAMETER_IS_NULL_FACTORRELAID(10703,"因子编码不能为空"),


	
	/**
	 * 保费计算公式因子数量有误
	 */
	FAILED_PREMIUMFORMULA(10707,"保费计算公式因子数量有误"),
	/**
	 * 产品编码不能为空
	 */
	PARAMETER_IS_NULL_PRODUCT_CODE(10708,"产品编码不能为空"),

	/**
	 * 方案编码不能为空
	 */
	PARAMETER_IS_NULL_PROGRAM_CODE(10709,"方案编码不能为空"),
	/**
	 * 主险参保份数不能为空
	 */
	PARAMETER_IS_NULL_NUMBEROFPEOPLE(10710,"主险参保份数不能为空"),
	/**
	 * 因子信息对象不能为空
	 */
	PARAMETER_IS_NULL_FACTORINFOS(10711,"因子信息对象不能为空"),
	/**
	 * 区间运算符类型不能为空
	 */
	PARAMETER_IS_NULL_OPERATOR_TYPE(10712,"区间运算符类型不能为空"),

	/**
	 * 区间运算符类型为2时，方案id不能为空
	 */
	PARAMETER_IS_NULL_PRODUCTPROGRAM_ID(10713,"区间运算符类型为2时，方案id不能为空"),

	/**
	 * 区间运算符类型为1时，区间文本描述不能为空
	 */
	PARAMETER_IS_NULL_OPERATOR_DESC(10714,"区间运算符类型为1时，区间文本描述不能为空"),

	/**
	 * 因子输入具体值不能为空
	 */
	PARAMETER_IS_NULL_STRING_FACTOR(10715,"因子输入具体值不能为空"),



	/**
	 * 主险浮动保费集合不能为空
	 */
	IS_NULL_FLOATPREMIUMS(10722,"主险浮动保费集合不能为空"),


	/**
	 * 限额值id不能为空
	 */
	IS_NULL_LIABILITYLIMITVALUESID(10723,"限额值编码不能为空"),


	/**
	 * 限额ID不能为空
	 */
	IS_NULL_LIMITID(10734,"限额编码不能为空"),
	/**
	 * 限额值ID数据库不存在
	 */
	IS_NULL_LIMITVALUES_ID(10735,"限额值编码数据库不存在"),
	/**
	 * 方案编码不能为空
	 */
	PARAMETER_IS_NULL_PRODUCTPROGRAM_CODE(10736,"主险方案编码不能为空"),

	/**
	 * 附加险方案编码不能为空
	 */
	IS_NULL_ADDITIONPROGRAMCODE(10737,"附加险方案编码不能为空"),
	/**
	 * 附加险参保份数不能为空
	 */
	IS_NULL_ADDITONCOUNT(10738,"附加险参保份数不能为空"),

	/**
	 * 续保状态不能为空
	 */
	IS_NULL_RENENWALSTATUS(10739,"续保状态不能为空"),

	/**
	 * 续保状态参数输入错误
	 */
	IS_FAILD_RENENWALSTATUS(10739,"续保状态参数输入错误"),

	/**
	 * 保单号不能为空
	 */
	IS_NULL_POLICYNO(10740,"保单号不能为空"),

	/**
	 * 续保年限不能为空
	 */
	IS_NULL_RENEWAL(10741,"续保年限不能为空"),
	/**
	 * 渠道号不能为空
	 */
	IS_NULL_CHANNELNO(10742,"渠道号不能为空"),

	/**
	 * 上年保单保费不能为空
	 */
	IS_NULL_LASTPREMIUM(10743,"上年保单保费不能为空"),

	/**
	 * 出单公司编号不能为空
	 */
	IS_NULL_INSCOMP(10744,"出单公司编号不能为空"),
	/**
	 * 因子编码对应的区间值为空
	 */
	IS_NULL_CONFCOEFFICIENTS(10745,"因子编码对应的区间值为空"),
	/**
	 * 因子编码错误，查询不到对应因子信息
	 */
	IS_FAILD_FACTORCODE(10746,"因子编码错误，查询不到对应因子信息"),

	/**
	 * 因子关系id不能为空
	 */
	IS_NULL_FACTORRELA_ID(10747,"因子关系id不能为空"),

	/**
	 * 险种编码不能为空
	 */
	IS_NULL_RISKCODE(10748,"险种编码不能为空"),
	/**
	 * 险种组合不能为空
	 */
	IS_NULL_RISKBEAN(10749,"险种组合不能为空"),

	/**
	 * 主险浮动保费集合不能为空
	 */
	IS_NULL_ADDTITIONFLOATPREMIUMS(10750,"附加险浮动保费集合不能为空"),

	/**
	 * 基本险险种编码输入错误
	 */
	IS_FAILD_BASIC_RISK(10751,"基本险险种编码输入错误"),
	/**
	 * 基本险险种编码输入错误
	 */
	IS_FAILD_ADDTITON_RISK(10752,"基本险险种编码输入错误"),
	;



	private Integer code;

	private String message;

	PremiumFormulaEnum(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
