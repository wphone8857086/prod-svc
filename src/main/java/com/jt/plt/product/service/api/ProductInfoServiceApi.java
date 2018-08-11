package com.jt.plt.product.service.api;
/**
 * 
 * @author 可达鸭
 * 描述:方案
 * @date 2018年4月13日 上午10:28:51
 */

import com.jt.plt.product.dto.Factor;
import com.jt.plt.product.dto.InusureCompanyDTO;
import com.jt.plt.product.dto.ProductInfoDTO;
import com.jt.plt.product.dto.ProductSimpleInfo;
import com.jt.plt.product.dto.channel.Query;
import com.jt.plt.product.dto.lifeapp.RiskParam;
import com.jt.plt.product.dto.program.ProgramInfoBean;
import com.jt.plt.product.dto.rule.RuleDTO;
import com.jt.plt.product.vo.FactorVO;
import org.springframework.http.ResponseEntity;

import java.util.List;

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

	/**
	 * 寿险因子列表
	 * @return
	 */
	List<FactorVO> findFactors(String productCode);

	List<ProductSimpleInfo> getProductList();

	List<RuleDTO> findruleByProductCode(String productCode);

	List<String> findProgramByRule(String rules);
	/**
	 * 
	 * @param productCode
	 * @return
	 * 描述：寿险责任险产品详情
	 */
	ResponseEntity<?> findProductDetails(String productCode);

	//List<ProgramInfoBean> findProgramInfos(String productCode);
	
	ResponseEntity<?> findMainProduct();

	public ResponseEntity<?> findAddProduct(List<String> list);

	ResponseEntity<?> findProductByChannelCode(String channelCode);

	ResponseEntity<?> findLiabilityByCode(List<RiskParam> list);

	ResponseEntity<?> findAllCompany();

	public ResponseEntity<?> findProductListByCond(Query query);

	/**
	 * 省市县 地区查询
	 * @return
	 */
	ResponseEntity<?> selectArea();

}
