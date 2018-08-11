package com.jt.plt.product.dto.lifeapp;

import java.util.List;

import lombok.Data;

/**
 * 
 * @author 可达鸭
 * 描述:产品属性标签
 * @date 2018年7月27日 下午4:15:33
 */
@Data
public class AttrInfo {
	/**
	 * 属性名称
	 */
	private String name;
	/**
	 * 属性分组id
	 */
	private String groupId;
	/**
	 * 属性分组名称
	 */
	private String groupName;
	/**
	 * 属性值集合
	 */
	private List<String> list; 
}
