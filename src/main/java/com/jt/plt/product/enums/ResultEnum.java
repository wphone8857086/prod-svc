package com.jt.plt.product.enums;



/**
 * 
 * @author 可达鸭
 * 描述: 状态码和简单描述枚举
 * @date 2018年1月15日 上午10:40:25
 */
public enum ResultEnum implements BaseEnum {
	/**
	 * 成功
	 */
    SUCCESS(200, "成功"),
    /**
	 * 请求路径错误
	 */
    URL_ERROR(404,"请求路径错误"),
    /**
	 * 参数不合法
	 */
    PARAM_ERROR(400, "参数不合法"),
    /**
	 * 请求不合法
	 */
    REQUEST_ERROR(405,"请求不合法"),
    /**
	 * 服务器异常
	 */
    SERVER_ERROR(500,"服务器异常"),
    /**
	 * 参数为空
	 */
    PARAMETER_IS_NULL(1,"参数为空"),
    /**
	 * 数据存储失败
	 */
    SAVE_FAILED(2,"数据存储失败"),
    /**
	 * 数据修改失败
	 */
    UPATE_FAILED(3,"数据修改失败"),
    /**
	 * 添加失败
	 */
    ADD_FAILED(4,"添加失败！"),
    /**
	 * 参数已存在
	 */
    ID_IS_EXIST(5,"ID已存在"),
    /**
	 * 没有满足条件的数据
	 */
    NO_DATA(6,"没有满足条件的数据"),
    /**
	 * 参数为空
	 */
    PARAMETER_IS_EXIST(7,"参数已存在"),
    /**
	 * 上传文件为空
	 */
    FILE_IS_NULL(8,"上传文件为空"),
    /**
	 * 文件上传失败
	 */
    FILE_UP_FAILED(9,"文件上传失败"),
    /**
     * 文件上传失败
     */
    DELETE_FAILED(10,"删除失败"),
	/**
	 *  类型转换异常
	 */
	CAST_EXCEPTION(11,"类型转换异常"),
    
    ;
	
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	public Integer getRc() {
		return code;
	}
}
