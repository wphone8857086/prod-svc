package com.jt.plt.product.service.api;
/**
 * 
 * @author 可达鸭
 * 描述:方案
 * @date 2018年4月13日 上午10:28:51
 */

import java.util.List;

import com.jt.plt.product.dto.Factor;
import com.jt.plt.product.dto.InusureCompanyDTO;
import com.jt.plt.product.dto.ProductInfoDTO;
import com.jt.plt.product.dto.program.ProgramInfoBean;

public interface ProductInfoServiceApi {
	public ProductInfoDTO findProductInfo(String productCode);
	
	public List<ProgramInfoBean> findProgramInfo(String productCode);
	
	public List<InusureCompanyDTO> findInusureCompanyInfo(String productCode);
	/**
	 * 
	 * @param productCode
	 * @return
	 * 描述：因子列表
	 */
	public List<Factor> findFactorInfo(String productCode);

	//List<ProgramInfoBean> findProgramInfos(String productCode);
}
