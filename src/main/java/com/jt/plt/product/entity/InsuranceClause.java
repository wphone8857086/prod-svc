package com.jt.plt.product.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_insurance_clause")
public class InsuranceClause {
    /**
     * 条款id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC") 
    private Long id;

    /**
     * 条款名称，可以是电子文档的文件名
     */
    @Column(name = "insurance_clause_name")
    private String insuranceClauseName;

    /**
     * 条款电子版存放地址
     */
    @Column(name = "insurance_clause_url")
    private String insuranceClauseUrl;

    /**
     * 所属类型：1-险种,2-责任
     */
    @Column(name = "their_type")
    private String theirType;

    /**
     * 所属id，根据their不同值不同
     */
    @Column(name = "their_id")
    private String theirId;

    /**
     * 状态：0-失效，1-生效
     */
    private String status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;
	/**
     * 
     * @return
     * 描述：自增id
     */
 
    public Long getId() {
		return id;
	}
    /**
     * 
     * @param insuranceClauseId
     * 描述：自增id
     */
	public void setId(Long id) {
		this.id = id;
	}

	/**
     * 获取条款名称，可以是电子文档的文件名
     *
     * @return insurance_clause_name - 条款名称，可以是电子文档的文件名
     */
    public String getInsuranceClauseName() {
        return insuranceClauseName;
    }

    /**
     * 设置条款名称，可以是电子文档的文件名
     *
     * @param insuranceClauseName 条款名称，可以是电子文档的文件名
     */
    public void setInsuranceClauseName(String insuranceClauseName) {
        this.insuranceClauseName = insuranceClauseName;
    }

    /**
     * 获取条款电子版存放地址
     *
     * @return insurance_clause_url - 条款电子版存放地址
     */
    public String getInsuranceClauseUrl() {
        return insuranceClauseUrl;
    }

    /**
     * 设置条款电子版存放地址
     *
     * @param insuranceClauseUrl 条款电子版存放地址
     */
    public void setInsuranceClauseUrl(String insuranceClauseUrl) {
        this.insuranceClauseUrl = insuranceClauseUrl;
    }

    /**
     * 获取所属类型：1-险种,2-责任
     *
     * @return their_type - 所属类型：1-险种,2-责任
     */
    public String getTheirType() {
        return theirType;
    }

    /**
     * 设置所属类型：1-险种,2-责任
     *
     * @param theirType 所属类型：1-险种,2-责任
     */
    public void setTheirType(String theirType) {
        this.theirType = theirType;
    }

    /**
     * 获取所属id，根据their不同值不同
     *
     * @return their_id - 所属id，根据their不同值不同
     */
    public String getTheirId() {
        return theirId;
    }

    /**
     * 设置所属id，根据their不同值不同
     *
     * @param theirId 所属id，根据their不同值不同
     */
    public void setTheirId(String theirId) {
        this.theirId = theirId;
    }

    /**
     * 获取状态：0-失效，1-生效
     *
     * @return status - 状态：0-失效，1-生效
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：0-失效，1-生效
     *
     * @param status 状态：0-失效，1-生效
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
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

	@Override
	public String toString() {
		return "InsuranceClause [id=" + id + ", insuranceClauseName="
				+ insuranceClauseName + ", insuranceClauseUrl=" + insuranceClauseUrl + ", theirType=" + theirType
				+ ", theirId=" + theirId + ", status=" + status + ", createTime=" + createTime + "]";
	}
    
}