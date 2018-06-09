package com.jt.plt.product.enums;
/**
 * 
 * @author 可达鸭
 * 描述:产品配置
 * @date 2018年3月13日 下午4:18:32
 */
public enum ProductConfigBeanEnum {
	/**
	 * 产品名称不能为空
	 */
	PARAMETER_IS_NULL_PRODUCT_NAME(10801,"产品名称不能为空"),
	/**
	 * 产品描述不能为空
	 */
	PARAMETER_IS_NULL_PRODUCT_DESC(10802,"产品描述不能为空"),
	/**
	 * 保费计算类型不能为空
	 */
	PARAMETER_IS_NULL_PRODUCT_PREMIUM_TYPE(10803,"保费计算类型不能为空"),
	/**
	 * 责任ID不能为空
	 */
	PARAMETER_IS_NULL_LIABILITY_ID(10804,"责任ID不能为空"),
	/**
	 * 限额ID不能为空
	 */
	PARAMETER_IS_NULL_LIMIT_ID(10805,"限额ID不能为空"),
	/**
	 * 限额值ID不能为空
	 */
	PARAMETER_IS_NULL_LIMIT_VALUES_ID(10806,"限额值ID不能为空"),
	/**
	 * 至少必须有一个方案
	 */
	PARAMETER_IS_NULL_PROGRAM(10807,"至少必须有一个方案"),
	/**
	 * 基础保费不能为空
	 */
	PARAMETER_IS_NULL_BASIC_PREMIUM(10809,"基础保费不能为空"),
	/**
	 * 方案下至少必须对应一个限额值
	 */
	PARAMETER_IS_NULL_PROGRAM_LIABILITY(10810,"方案下至少必须对应一个限额值"),
	/**
	 * 区间标识符不能为空
	 */
	PARAMETER_IS_NULL_SIGN_ID(10811,"区间标识符不能为空"),
	/**
	 * 费率不能为空
	 */
	PARAMETER_IS_NULL_RATE(10812,"费率不能为空"),
	/**
	 * 最大限额值ID不能为空 
	 */
	PARAMETER_IS_NULL_MAX_LIMIT_VALUES_ID(10813,"最大限额值ID不能为空"),
	/**
	 * 最小限额值ID不能为空 
	 */
	PARAMETER_IS_NULL_MIN_LIMIT_VALUES_ID(10814,"最小限额值ID不能为空"),
	/**
	 * 是否参与保费计算标识不能为空
	 */
	PARAMETER_IS_NULL_COMPUTE_SIGNS(10815,"是否参与保费计算标识不能为空"),
	/**
	 * 产品编码不能为空
	 */
	PARAMETER_IS_NULL_PRODUCT_CODE(10816,"产品编码不能为空"),
	/**
	 * 责任限额不能为空
	 */
	PARAMETER_IS_NULL_LIMIT(10817,"责任限额不能为空"),
	
	/**
	 * 责任不存在
	 */
	NO_DATA_LIABILITY(80801,"责任不存在"),
	/**
	 * 限额不存在
	 */
	NO_DATA_LIMIT(80802,"限额不存在"),
	/**
	 * 限额值不存在
	 */
	NO_DATA_LIMIT_VALUES(80803,"限额值不存在"),
	/**
	 * 修改或添加的产品不存在
	 */
	NO_DATA_PRODUCT_INFO(80804,"修改或添加的产品不存在"),
	/**
	 * 最大限额值不存在
	 */
	NO_DATA_MAX_LIMIT_VALUES_ID(80805,"最大限额值不存在"),
	/**
	 * 最小限额值不存在
	 */
	NO_DATA_MIN_LIMIT_VALUES_ID(80806,"最小限额值不存在"),
	/**
	 * 险种不存在
	 */
	NO_DATA_RISK_INFO(80807,"险种不存在"),
	/**
	 * 产品名称已存在
	 */
	PARAMETER_IS_EXIST_PRODUCT_NAME(90101,"产品名称已存在"),
	/**
	 * 险类名称已经存在
	 */
	PARAMETER_IS_EXIST_INSURANCE_CLASSES_NAME(90102,"险类名称已经存在"),
	/**
	 * 产品编码已存在
	 */
	PARAMETER_IS_EXIST_PRODUCT_CODE(90103,"产品编码已存在"),
	/**
	 * 父类险类不存在
	 */
	NO_DATA_PARENT_ID(80101,"父类险类不存在"),
	/**
	 * 险类不存在
	 */
	NO_DATA_INSURANCE_CLASSES_ID(80102,"险类不存在"),
	/**
	 * 产品不存在
	 */
	NO_DATA_PRODUCT(80805,"产品不存在"),
	/**
	 * 保费计算类型不正确
	 */
	PARAM_ERROR_PRODUCT_PREMIUM_TYPE(40801, "保费计算类型不正确"),
	/**
	 * 基础保费参数不合法
	 */
	PARAM_ERROR_BASIC_PREMIUM(40802, "基础保费参数不合法"),
	/**
	 * 险种和责任无对应关系
	 */
	PARAM_ERROR_RISK_LIABILITY_NO_MAPPING(40803, "险种和责任无对应关系"),
	/**
	 * 责任和限额无对应关系
	 */
	PARAM_ERROR_LIABILITY_LIMIT_NO_MAPPING(40804, "责任和限额无对应关系"),
	/**
	 * 限额和限额值无对应关系
	 */
	PARAM_ERROR_LIMIT_LIMIT_VALUE_NO_MAPPING(40805, "限额和限额值无对应关系"),
	/**
	 * 区间标识符ID不合法
	 */
	PARAM_ERROR_SIGN_ID(40806, "区间标识符ID不合法"),
	/**
	 * 费率不能小于零
	 */
	PARAM_ERROR_RATE(40807, "费率不能小于零"),
	/**
	 * 最大限额值不能小于最小限额值
	 */
	PARAM_ERROR_MIN_MAX_LIMIT_VALUES(40808, "最大限额值不能小于最小限额值"),
	/**
	 * 限额值不是数值类型
	 */
	PARAM_ERROR_LIMIT_VALUES_NOT_NUM(40809,"限额值不是数值类型"),
	/**
	 * 是否参与保费计算标识不合法（0-不参与，1-参与）
	 */
	PARAM_ERROR_COMPUTE_SIGNS(40810,"是否参与保费计算标识不合法（0-不参与，1-参与）"),
	/**
	 * 不参与保费计算的责任不能有限额存在
	 */
	PARAM_ERROR_IS_COMPUTE_SIGNS(40811,"不参与保费计算的责任不能有限额存在"),
	/**
	 * 产品名称超出规定长度
	 */
	PARAM_ERROR_PRODUCT_NAME_LENGTH(40812,"产品名称超出规定长度"),
	/**
	 * 产品编码超出规定长度
	 */
	PARAM_ERROR_PRODUCT_CODE_LENGTH(40813,"产品编码超出规定长度"),
	/**
	 * 最大限额值和最小限额值不属于同一限额
	 */
	PARAM_ERROR_MAX_MIN_NO_MAPPING(40814,"最大限额值和最小限额值不属于同一限额"),
	/**
	 * 自定义区间，请使用'[]'区间
	 */
	PARAM_ERROR_SIGN_ID_TYPE(40815,"自定义区间，请使用'[]'区间"),
	/**
	 * 限额值配置有误
	 */
	PARAM_ERROR_LIMIT_VALUES(40815,"限额值配置有误！"),
	;
	private Integer code;

    private String message;

    ProductConfigBeanEnum(Integer code, String message) {
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
