package com.jt.plt.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_factor")
public class Factor implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * 因子编码
     */
    @Column(name = "factor_code")
    private String factorCode;

    /**
     * 因子名称
     */
    @Column(name = "factor_name")
    private String factorName;

    /**
     * 因子描述
     */
    @Column(name = "factor_desc")
    private String factorDesc;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "modify_time")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private Date modifyTime;

    /**
     * 创建人
     */
    @Column(name = "create_user")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String createUser;

    /**
     * 修改人
     */
    @Column(name = "modify_user")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String modifyUser;

    /**
     * 因子代码名称
     * @return
     */
    @Column(name = "factor_code_name")
    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String factorCodeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFactorCodeName() {
        return factorCodeName;
    }

    public void setFactorCodeName(String factorCodeName) {
        this.factorCodeName = factorCodeName;
    }

    /**
     * 获取因子编码
     *
     * @return factor_code - 因子id
     */
    public String getFactorCode() {
        return factorCode;
    }


    /**
     * 设置因子编码
     *
     * @param factorCode
     */
    public void setFactorCode(String factorCode) {
        this.factorCode = factorCode;
    }


    /**
     * 获取因子名称
     *
     * @return factor_name - 因子名称
     */
    public String getFactorName() {
        return factorName;
    }

    /**
     * 设置因子名称
     *
     * @param factorName 因子名称
     */
    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    /**
     * 获取因子描述
     *
     * @return factor_desc - 因子描述
     */
    public String getFactorDesc() {
        return factorDesc;
    }

    /**
     * 设置因子描述
     *
     * @param factorDesc 因子描述
     */
    public void setFactorDesc(String factorDesc) {
        this.factorDesc = factorDesc;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return modify_time - 修改时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 设置修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取创建人
     *
     * @return create_user - 创建人
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * 设置创建人
     *
     * @param createUser 创建人
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取修改人
     *
     * @return modify_user - 修改人
     */
    public String getModifyUser() {
        return modifyUser;
    }

    /**
     * 设置修改人
     *
     * @param modifyUser 修改人
     */
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }




    
    
}