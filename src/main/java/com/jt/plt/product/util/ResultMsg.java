package com.jt.plt.product.util;

import com.jt.plt.product.enums.ResultEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author 可达鸭
 * 描述: 响应消息体工具类
 * @date 2017年11月23日 下午1:03:55
 */
public class ResultMsg {
	 /** 错误码. */
	@ApiModelProperty("返回码")
    private Integer code;

    /** 提示信息. */
	@ApiModelProperty("提示信息")
    private String msg;

	public ResultMsg(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public ResultMsg(ResultEnum resultEnum) {
		super();
		this.code = resultEnum.getCode();
		this.msg = resultEnum.getMessage();
	}
	public ResultMsg(ResultEnum resultEnum,String msg) {
		super();
		this.code = resultEnum.getCode();
		this.msg = resultEnum.getMessage()+msg;
	}
	public ResultMsg() {
		super();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
