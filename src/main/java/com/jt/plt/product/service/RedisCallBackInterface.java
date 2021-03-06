package com.jt.plt.product.service;

import com.jt.plt.product.entity.CallRsult;

/**
* 描述：缓存回调数据库操作接口
* 类名称：RedisCallBackInterface.java
* 作者： wangyang
* 版本：1.0
* 修改： 2018/5/14 13:52
* 创建日期： 2018/5/14 13:52
* 版权：江泰保险经纪股份有限公司
*/
@FunctionalInterface
public interface RedisCallBackInterface<T, K> {
    CallRsult<T> callback(K key);
}
