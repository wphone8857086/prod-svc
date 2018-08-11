package com.jt.plt.product.dto.company;

import java.util.List;

import lombok.Data;
@Data
public class AddRiskDTO {
	private String productCode;
	private List<AddRisk> list;
}
