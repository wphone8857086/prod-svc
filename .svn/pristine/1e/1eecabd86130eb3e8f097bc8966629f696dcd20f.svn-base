package com.jt.plt.product.dto;


import com.jt.plt.product.enums.BaseEnum;

/**
 * 描述：
 * 类名称：统一返回的消息包装类
 * 作者： wangyang
 * 版本：1.0
 * 修改：
 * 创建日期：
 * 版权：江泰保险经纪股份有限公司
 */
public class ResultDTO<T> {

   /** 错误码. */
   private Integer rc;

   /** 提示信息. */
   private String msg;

   /** 具体内容. */
   private T data;

   public Integer getRc() {
       return rc;
   }

   public void setRc(Integer rc) {
       this.rc = rc;
   }

   public String getMsg() {
       return msg;
   }

   public void setMsg(String msg) {
       this.msg = msg;
   }

   public T getData() {
       return data;
   }

   public void setData(T data) {
       this.data = data;
   }

   public ResultDTO(BaseEnum resultEnum) {
       super();
       this.rc = resultEnum.getRc();
       this.msg = resultEnum.getMessage();
   }

   public ResultDTO(BaseEnum resultEnum, T data) {
       super();
       this.rc = resultEnum.getRc();
       this.msg = resultEnum.getMessage();
       this.data = data;
   }

   public ResultDTO() {
       super();
   }

   public ResultDTO(String msg) {
       super();
       this.msg = msg;
   }
}
