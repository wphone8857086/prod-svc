package com.jt.plt.product.util;

import com.jt.plt.product.enums.ResultEnum;

import io.swagger.annotations.ApiModel;
@ApiModel(subTypes=ResultMsg.class,value="响应数据")
public class ResultVO<T> extends ResultMsg{

    /** 具体内容. */
    private T data;


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


	public ResultVO() {
		super();
	}

	public ResultVO(ResultEnum resultEnum, T data) {
		super(resultEnum.getCode(), resultEnum.getMessage());
		this.data = data;
	}
	public ResultVO(T data) {
		this.data = data;
	}
	public ResultVO(ResultEnum resultEnum) {
		super(resultEnum.getCode(), resultEnum.getMessage());
	}
	
}
