package com.jt.plt.product.service;

import com.jt.plt.product.entity.calPremium.CountPremiumBean;
import com.jt.plt.product.entity.calPremium.FormulaBean;
import com.jt.plt.product.entity.calPremium.FormulaDTO;
import com.jt.plt.product.util.ResultMsg;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**   
 * 描述：保费计算公式
 * 作者： wephone   
 * 创建日期： 2018年3月9日 上午10:14:29
 * 版权：江泰保险经纪股份有限公司
 */
@Service
public interface FormulaService {

	ResultMsg countPremium(FormulaBean formulaBean);

	ResponseEntity<?> countPremiums(List<FormulaBean> formulaBeans);

	ResponseEntity<?> newcountPremiums(List<CountPremiumBean> countPremiumBeans);

	/**
	 * 计算寿险保费
	 */
	ResponseEntity<?> countLifePremiums(List<FormulaDTO> formulaDTOS);
}
