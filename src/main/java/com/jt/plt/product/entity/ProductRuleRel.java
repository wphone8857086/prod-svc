package com.jt.plt.product.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "t_product_rule_rel")
@Data
public class ProductRuleRel {
    /**
     * 规则关系id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 对应产品id
     */
    @Column(name = "product_code")
    private String productCode;


    @Column(name = "type_code")
    private String typeCode;

    /**
     * 对应规则id
     */
    @Column(name = "rule_id")
    private Long ruleId;

    @Column(name = "rule_name")
    private String ruleName;

    /**
     * 对应类别编码  00-产品编码   01-险种编码  02-责任编码 03-费率配置的险种编码
     */
    @Column(name = "rule_type")
    private String ruleType;

    /**
     * 规则类别：01-产品类规则，02-方案类规则，03-责任限额规则，04-投保类规则，05-批单类规则,06-保单类规则
     */
    @Column(name = "rule_sort")
    private String ruleSort;

    /**
     * 规则值参数
     */
    @Column(name = "rule_values_param")
    private String ruleValuesParam;

    /**
     * 状态:0-失效，1-有效
     */
    private String status;

    /**
     * 对应的值id
     */
    @Column(name = "rule_conf_values_id")
    private Long ruleConfValuesId;

    /**
     * 对应值的内容
     */
    @Column(name = "rule_conf_value")
    private String ruleConfValue;

    /**
     * 是否是联动规则，Y-是，N-否。例如教责险中，学校类型与年级是联动的参数
     */
    @Column(name = "is_link_rule")
    private String isLinkRule;

    /**
     * 联动规则id
     */
    @Column(name = "link_rule_id")
    private Long linkRuleId;
    /**
     * 销售规则信息 （K-V形式存值）
     */
    @Column(name = "rule_info")
    private String ruleInfo;
    /**
     * 方案对应销售规则，规则之间使用逗号隔开并按照关联ruleId由小到大排序拼接
     */
    @Column(name = "rule_value")
    private String ruleValue;
    /**
     * 方案对应销售规则，规则之间使用逗号隔开并按照关联ruleId由小到大排序拼接
     */
    public String getRuleValue() {
		return ruleValue;
	}
    /**
     * 方案对应销售规则，规则之间使用逗号隔开并按照关联ruleId由小到大排序拼接
     */
	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}
	/**
     * 
     * @return
     * 描述：销售规则信息 （K-V形式存值）
     */
    public String getRuleInfo() {
		return ruleInfo;
	}
    /**
     * 
     * @param ruleInfo
     * 描述：销售规则信息 （K-V形式存值）
     */
	public void setRuleInfo(String ruleInfo) {
		this.ruleInfo = ruleInfo;
	}

	public String getRuleConfValue() {
        return ruleConfValue;
    }

    public void setRuleConfValue(String ruleConfValue) {
        this.ruleConfValue = ruleConfValue;
    }

    /**
     * 获取规则关系id
     *
     * @return product_rule_rel_id - 规则关系id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置规则关系id
     *
     * @param productRuleRelId 规则关系id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取对应产品id
     *
     * @return product_code - 对应产品id
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置对应产品id
     *
     * @param productCode 对应产品id
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }



    /**
     * 获取对应规则id
     *
     * @return rule_id - 对应规则id
     */
    public Long getRuleId() {
        return ruleId;
    }

    /**
     * 设置对应规则id
     *
     * @param ruleId 对应规则id
     */
    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    /**
     * @return rule_name
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * @param ruleName
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    /**
     * 获取规则类型：0,check规则；1，save规则
     *
     * @return rule_type - 规则类型：0,check规则；1，save规则
     */
    public String getRuleType() {
        return ruleType;
    }

    /**
     * 设置规则类型：0,check规则；1，save规则
     *
     * @param ruleType 规则类型：0,check规则；1，save规则
     */
    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    /**
     * 获取规则类别：01-产品类规则，02-方案类规则，03-责任限额规则，04-投保类规则，05-批单类规则,06-保单类规则
     *
     * @return rule_sort - 规则类别：01-产品类规则，02-方案类规则，03-责任限额规则，04-投保类规则，05-批单类规则,06-保单类规则
     */
    public String getRuleSort() {
        return ruleSort;
    }

    /**
     * 设置规则类别：01-产品类规则，02-方案类规则，03-责任限额规则，04-投保类规则，05-批单类规则,06-保单类规则
     *
     * @param ruleSort 规则类别：01-产品类规则，02-方案类规则，03-责任限额规则，04-投保类规则，05-批单类规则,06-保单类规则
     */
    public void setRuleSort(String ruleSort) {
        this.ruleSort = ruleSort;
    }

    /**
     * 获取规则值参数
     *
     * @return rule_values_param - 规则值参数
     */
    public String getRuleValuesParam() {
        return ruleValuesParam;
    }

    /**
     * 设置规则值参数
     *
     * @param ruleValuesParam 规则值参数
     */
    public void setRuleValuesParam(String ruleValuesParam) {
        this.ruleValuesParam = ruleValuesParam;
    }

    /**
     * 获取状态:0-失效，1-有效
     *
     * @return status - 状态:0-失效，1-有效
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态:0-失效，1-有效
     *
     * @param status 状态:0-失效，1-有效
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取对应的值id
     *
     * @return rule_conf_values_id - 对应的值id
     */
    public Long getRuleConfValuesId() {
        return ruleConfValuesId;
    }

    /**
     * 设置对应的值id
     *
     * @param ruleConfValuesId 对应的值id
     */
    public void setRuleConfValuesId(Long ruleConfValuesId) {
        this.ruleConfValuesId = ruleConfValuesId;
    }

    /**
     * 获取是否是联动规则，Y-是，N-否。例如教责险中，学校类型与年级是联动的参数
     *
     * @return is_link_rule - 是否是联动规则，Y-是，N-否。例如教责险中，学校类型与年级是联动的参数
     */
    public String getIsLinkRule() {
        return isLinkRule;
    }

    /**
     * 设置是否是联动规则，Y-是，N-否。例如教责险中，学校类型与年级是联动的参数
     *
     * @param isLinkRule 是否是联动规则，Y-是，N-否。例如教责险中，学校类型与年级是联动的参数
     */
    public void setIsLinkRule(String isLinkRule) {
        this.isLinkRule = isLinkRule;
    }

    /**
     * 获取联动规则id
     *
     * @return link_rule_id - 联动规则id
     */
    public Long getLinkRuleId() {
        return linkRuleId;
    }

    /**
     * 设置联动规则id
     *
     * @param linkRuleId 联动规则id
     */
    public void setLinkRuleId(Long linkRuleId) {
        this.linkRuleId = linkRuleId;
    }

	@Override
	public String toString() {
		return "ProductRuleRel [id=" + id + ", productCode=" + productCode + ", ruleId=" + ruleId + ", ruleName="
				+ ruleName + ", ruleType=" + ruleType + ", ruleSort=" + ruleSort + ", ruleValuesParam="
				+ ruleValuesParam + ", status=" + status + ", ruleConfValuesId=" + ruleConfValuesId + ", ruleConfValue="
				+ ruleConfValue + ", isLinkRule=" + isLinkRule + ", linkRuleId=" + linkRuleId + "]";
	}

}