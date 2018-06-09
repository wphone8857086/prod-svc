package com.jt.plt.product.entity;

/**
* 描述：缓存回调封装类
* 类名称：CallRsult.java
* 作者： wangyang
* 版本：1.0
* 修改： 2018/5/14 13:53
* 创建日期： 2018/5/14 13:53
* 版权：江泰保险经纪股份有限公司
*/
public class CallRsult<T> {

    private boolean isAllowCache;

    private T result;

    public boolean isAllowCache() {
        return isAllowCache;
    }

    public void setAllowCache(boolean allowCache) {
        isAllowCache = allowCache;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setResultAndAllowCached(T result){
        this.result = result;
        isAllowCache= true;
    }
}
