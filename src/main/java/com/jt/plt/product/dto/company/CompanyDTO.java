package com.jt.plt.product.dto.company;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
public class CompanyDTO {
	private String code;
	private String name;
	@JsonInclude(value = Include.NON_NULL)
	private List<RiskDTO> list;
	
}
