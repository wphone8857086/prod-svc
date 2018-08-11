package com.jt.plt.product.dto.lifeapp;

import java.util.List;

import com.jt.plt.product.dto.rule.RuleDTO;

import lombok.Data;

@Data
public class RiskDetails {
	private String code;
	private String name;
	private String riskFlag;
	private String riskShortFlag;
	private String isMust;
	private String issueAge;
	private String periodGrace;
	private String probation;
	private String waitingPeriod;
	private String hesitationPeriod;
	private String description;
	private String payType;
	private List<RiskClause> riskClauseList;
	/**
	 * 险种对应产品规则
	 */
	private List<RuleDTO> ruleList;
	/**
	 * 险种对应投保要素
	 */
	private List<RuleDTO> ruleRateList;
}
