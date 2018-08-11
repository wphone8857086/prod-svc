package com.jt.plt.product.util;

import com.github.pagehelper.PageInfo;

/**
 * 
 * @author 可达鸭
 * 描述:分页工具
 * @date 2018年1月26日 上午10:39:04
 */
public class PageInfoUtil {
	public static<T> MyPageInfo<T> copyInfo(PageInfo<T> pageInfo) {
		MyPageInfo<T> myPageInfo = null;
		if(myPageInfo==null) {
			myPageInfo = new MyPageInfo<T>();
		}
		myPageInfo.setCurrentPage(pageInfo.getPageNum());
		myPageInfo.setList(pageInfo.getList());
		myPageInfo.setPageSize(pageInfo.getPageSize());
		myPageInfo.setTotal(pageInfo.getTotal());
		return myPageInfo;
	}
}
