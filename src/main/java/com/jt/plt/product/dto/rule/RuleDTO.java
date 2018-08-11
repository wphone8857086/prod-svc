package com.jt.plt.product.dto.rule;

import lombok.Data;

@Data
public class RuleDTO {
	private Long ruleId;
	private String ruleCode;
	private String ruleType;
	private String typeCode;
	private String ruleName;
	private String value;
	private String valueAndUnit;
}
