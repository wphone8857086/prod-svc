package com.jt.plt.product.service;

import com.jt.plt.product.entity.calPremium.FormulaBean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.jt.plt.product.entity.calPremium.FormulaDTO;
import com.jt.plt.product.util.ResultMsg;

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

	ResultMsg countPremiums(List<FormulaBean> formulaBeans);
}
