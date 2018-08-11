package com.jt.plt.product.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
@ApiModel("产品简略信息")
public class ProductSimpleInfo {
	@ApiModelProperty("产品编码")
	private String code;
	@ApiModelProperty("产品名称")
	private String name;
	@ApiModelProperty("产品状态（状态为已上架的产品可查看详情）--新建产品   产品待提交	1\r\n" + 
			"		编辑产品	产品待提交	1\r\n" + 
			"		产品提交 	运营待编辑	2\r\n" + 
			"		运营编辑	运营待提审	2\r\n" + 
			"		运营提审 	待审核	3\r\n" + 
			"		产品审核	审核中	3\r\n" + 
			"		审核退回	退回配置	4\r\n" + 
			"		审核退回	退回运营	5\r\n" + 
			"		审核发布	已上架	6\r\n" + 
			"		产品过期	已下架	7")
	private String status;
}
