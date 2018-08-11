package com.jt.plt.product.util;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author 可达鸭
 * 描述:通用分页信息实体类
 * @param <T>
 * @date 2018年1月26日 上午10:32:52
 */
@ApiModel(value="data")
public class MyPageInfo<T> {
	/**
	 * 总条数
	 */
	@ApiModelProperty(value="总条数")
	private Long total;
	/**
	 * 当前页码
	 */
	@ApiModelProperty(value="当前页码")
	private Integer currentPage;
	/**
	 * 每页条数
	 */
	@ApiModelProperty(value="每页条数")
	private Integer pageSize;
	/**
	 * 数据
	 */
	@ApiModelProperty(value="数据")
	private List<T> list;
	/**
	 * 
	 * @return 返回总条数
	 * 描述：总条数
	 */
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	/**
	 * 
	 * @return
	 * 描述：当前页码
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * 
	 * @return
	 * 描述：每页条数
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * 
	 * @return
	 * 描述：数据
	 */
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}
