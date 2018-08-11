package com.jt.plt.product.entity;

import java.util.Date;
import javax.persistence.*;
/**
 * 
 * @author 可达鸭
 * 描述:产品临时信息表
 * @date 2018年3月23日 下午3:47:11
 */
@Table(name = "t_temp_product_info")
public class TempProductInfo {
	@Id
	@GeneratedValue(generator = "JDBC") 
	private Long id;
    /**
     * 产品编码
     */
    
    @Column(name = "product_code")
    private String productCode;

    /**
     * 创建时间
     */
    @Column(name = "creat_time")
    private Date creatTime;

    /**
     * 产品的临时信息（险种，责任，限额，限额值）
     */
    @Column(name = "product_info")
    private String productInfo;

    /**
	 * @return the id
	 * 主键
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param 主键
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
     * 获取产品ID
     *
     * @return product_id - 产品ID
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置产品ID
     *
     * @param productId 产品ID
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取创建时间
     *
     * @return creat_time - 创建时间
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * 设置创建时间
     *
     * @param creatTime 创建时间
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * 获取产品的临时信息（险种，责任，限额，限额值）
     *
     * @return product_info - 产品的临时信息（险种，责任，限额，限额值）
     */
    public String getProductInfo() {
        return productInfo;
    }

    /**
     * 设置产品的临时信息（险种，责任，限额，限额值）
     *
     * @param productInfo 产品的临时信息（险种，责任，限额，限额值）
     */
    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }
}