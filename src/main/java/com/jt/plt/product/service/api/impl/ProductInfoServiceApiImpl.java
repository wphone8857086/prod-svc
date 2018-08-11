package com.jt.plt.product.service.api.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.plt.product.dto.Factor;
import com.jt.plt.product.dto.InusureCompanyDTO;
import com.jt.plt.product.dto.ProductInfoDTO;
import com.jt.plt.product.dto.ProductSimpleInfo;
import com.jt.plt.product.dto.channel.LifeProductDTO;
import com.jt.plt.product.dto.channel.Query;
import com.jt.plt.product.dto.company.AddRisk;
import com.jt.plt.product.dto.company.AddRiskDTO;
import com.jt.plt.product.dto.company.CompanyDTO;
import com.jt.plt.product.dto.company.RiskDTO;
import com.jt.plt.product.dto.lifeapp.ClauseFileInfo;
import com.jt.plt.product.dto.lifeapp.LifeProductDetailsDTO;
import com.jt.plt.product.dto.lifeapp.ProductSimpleInfoDTO;
import com.jt.plt.product.dto.lifeapp.RiskClause;
import com.jt.plt.product.dto.lifeapp.RiskDetails;
import com.jt.plt.product.dto.lifeapp.RiskParam;
import com.jt.plt.product.dto.program.LiabilityInfoBean;
import com.jt.plt.product.dto.program.LimitInfoBean;
import com.jt.plt.product.dto.program.ProgramInfoBean;
import com.jt.plt.product.dto.program.RateInfoBean;
import com.jt.plt.product.dto.program.ValueBean;
import com.jt.plt.product.dto.rule.RuleDTO;
import com.jt.plt.product.entity.Area;
import com.jt.plt.product.entity.ConfCoefficient;
import com.jt.plt.product.entity.FloatRate;
import com.jt.plt.product.entity.InsuranceClause;
import com.jt.plt.product.entity.InsuranceLiability;
import com.jt.plt.product.entity.InsuranceProgram;
import com.jt.plt.product.entity.LiabilityLimit;
import com.jt.plt.product.entity.LiabilityLimitRela;
import com.jt.plt.product.entity.LiabilityLimitValues;
import com.jt.plt.product.entity.ProductInfo;
import com.jt.plt.product.entity.ProductPrivateInsurance;
import com.jt.plt.product.entity.ProductRuleInfo;
import com.jt.plt.product.entity.ProductRuleRel;
import com.jt.plt.product.entity.RiskInfo;
import com.jt.plt.product.entity.TempProductInfo;
import com.jt.plt.product.enums.ProductConfigBeanEnum;
import com.jt.plt.product.mapper.AreaMapper;
import com.jt.plt.product.mapper.CompanyInfoMapper;
import com.jt.plt.product.mapper.ConfCoefficientMapper;
import com.jt.plt.product.mapper.FloatRateMapper;
import com.jt.plt.product.mapper.InsuranceClauseMapper;
import com.jt.plt.product.mapper.InsuranceCompanyRelaMapper;
import com.jt.plt.product.mapper.InsuranceFactorRelaMapper;
import com.jt.plt.product.mapper.InsuranceLiabilityMapper;
import com.jt.plt.product.mapper.InsuranceProgramMapper;
import com.jt.plt.product.mapper.LiabilityLimitMapper;
import com.jt.plt.product.mapper.LiabilityLimitRelaMapper;
import com.jt.plt.product.mapper.LiabilityLimitValuesMapper;
import com.jt.plt.product.mapper.ProductInfoMapper;
import com.jt.plt.product.mapper.ProductPrivateInsuranceMapper;
import com.jt.plt.product.mapper.ProductRiskRelMapper;
import com.jt.plt.product.mapper.ProductRuleInfoMapper;
import com.jt.plt.product.mapper.ProductRuleRelMapper;
import com.jt.plt.product.mapper.RiskInfoMapper;
import com.jt.plt.product.mapper.TempProductInfoMapper;
import com.jt.plt.product.service.api.ProductInfoServiceApi;
import com.jt.plt.product.util.EntityUtils;
import com.jt.plt.product.util.GenericDataResponse;
import com.jt.plt.product.util.GenericListResponse;
import com.jt.plt.product.util.GenericResponse;
import com.jt.plt.product.util.JsonUtil;
import com.jt.plt.product.util.MyPageInfo;
import com.jt.plt.product.util.PageInfoUtil;
import com.jt.plt.product.util.ReturnCode;
import com.jt.plt.product.vo.AcceptInsuranceInfoVO;
import com.jt.plt.product.vo.AreaVM;
import com.jt.plt.product.vo.ConfCoefficientVO;
import com.jt.plt.product.vo.FactorVO;
import com.jt.plt.product.vo.riskinfo.InsuranLiabilityVO;
import com.jt.plt.product.vo.riskinfo.RiskTempVO;
import com.jt.plt.product.vo.riskinfo.TempProductInfoVO;

import tk.mybatis.mapper.entity.Example;

@Service
public class ProductInfoServiceApiImpl implements ProductInfoServiceApi {
	private final Logger log = LoggerFactory.getLogger(ProductInfoServiceApiImpl.class);
	@Autowired 
	private ProductInfoMapper productInfoMapper;
	@Autowired
	private InsuranceProgramMapper insuranceProgramMapper;
	@Autowired
	private InsuranceLiabilityMapper insuranceLiabilityMapper;
	@Autowired
	private InsuranceCompanyRelaMapper insuranceCompanyRelaMapper;
	@Autowired
	private InsuranceFactorRelaMapper insuranceFactorRelaMapper;
	@Autowired
	private ConfCoefficientMapper confCoefficientMapper;
	@Autowired
	private FloatRateMapper floatRateMapper;
	@Autowired
	private LiabilityLimitRelaMapper liabilityLimitRelaMapper;
	@Autowired
	private LiabilityLimitValuesMapper liabilityLimitValuesMapper;
	@Autowired
	private LiabilityLimitMapper liabilityLimitMapper;
	@Autowired
	private ProductRuleInfoMapper productRuleInfoMapper;
	@Autowired
	private ProductRuleRelMapper productRuleRelMapper;
	@Autowired
	private RiskInfoMapper riskInfoMapper;
	@Autowired
	private ProductPrivateInsuranceMapper productPrivateInsuranceMapper;
	@Autowired
	private InsuranceClauseMapper insuranceClauseMapper;
	@Autowired
	private ProductRiskRelMapper productRiskRelMapper;
	@Autowired
	private TempProductInfoMapper tempProductInfoMapper;
	@Autowired
	private CompanyInfoMapper companyInfoMapper;

	@Autowired
	AreaMapper areaMapper;

	/**
	 * 
	 * @param productCode
	 * @return
	 * 描述：承保属性信息
	 */
	@Override
	@Transactional(readOnly = true)
	public List<InusureCompanyDTO> findInusureCompanyInfo(String productCode) {
		List<AcceptInsuranceInfoVO> list = insuranceCompanyRelaMapper.findAcceptInsuranceInfoVOById(productCode);
		InusureCompanyDTO inusureCompanyDTO = null;
		List<InusureCompanyDTO> icdList = new ArrayList<>();
		for (AcceptInsuranceInfoVO acceptInsuranceInfoVO : list) {
			inusureCompanyDTO = new InusureCompanyDTO();
			inusureCompanyDTO.setCode(acceptInsuranceInfoVO.getCompanyCode());
			inusureCompanyDTO.setName(acceptInsuranceInfoVO.getCompanyName());
			inusureCompanyDTO.setPartyRole(acceptInsuranceInfoVO.getRelaType());
			switch (acceptInsuranceInfoVO.getRelaType()) {
			case ReturnCode.STATUS_CODE_1:
				//inusureCompanyDTO.setPartyRole("承保");
				inusureCompanyDTO.setShareType(acceptInsuranceInfoVO.getAttributeValueCode());
				inusureCompanyDTO.setShareScale(acceptInsuranceInfoVO.getAttributeValues());
				switch (acceptInsuranceInfoVO.getAttributeValueCode()) {
				case ReturnCode.STATUS_CODE_1:
					//inusureCompanyDTO.setShareType("主承");
					//inusureCompanyDTO.setShareScale(acceptInsuranceInfoVO.getAttributeValues());
					break;
				case ReturnCode.STATUS_CODE_2:
					//inusureCompanyDTO.setShareType("共保");
					//inusureCompanyDTO.setShareScale(acceptInsuranceInfoVO.getAttributeValues());
					break;
				case ReturnCode.STATUS_CODE_3:
					//inusureCompanyDTO.setShareType("独立承保");
					//inusureCompanyDTO.setShareScale(acceptInsuranceInfoVO.getAttributeValues());
					break;
				default:
					break;
				}
				break;
			case ReturnCode.STATUS_CODE_2:
				//inusureCompanyDTO.setPartyRole("出单");
				inusureCompanyDTO.setInsuringType(acceptInsuranceInfoVO.getAttributeValueCode());
				inusureCompanyDTO.setInsuringAreaCode(acceptInsuranceInfoVO.getAreaCode());
				switch (acceptInsuranceInfoVO.getAttributeValueCode()) {
				case ReturnCode.STATUS_Y:
					//inusureCompanyDTO.setInsuringType("出单");
					//inusureCompanyDTO.setInsuringAreaCode(acceptInsuranceInfoVO.getAreaCode());
					//TODO 区域名称
					//inusureCompanyDTO.setInsuringAreaName(null);
					break;
				case ReturnCode.STATUS_N:
					//inusureCompanyDTO.setInsuringType("跟单");
					break;
				default:
					break;
				}
				break;
			case ReturnCode.STATUS_CODE_3:
				//inusureCompanyDTO.setPartyRole("解付");
				inusureCompanyDTO.setPayType(acceptInsuranceInfoVO.getAttributeValueCode());
				inusureCompanyDTO.setPayScale(acceptInsuranceInfoVO.getAttributeValues());
				switch (acceptInsuranceInfoVO.getAttributeValueCode()) {
				case ReturnCode.STATUS_CODE_4:
					//inusureCompanyDTO.setPayType("一对一解付");
					//inusureCompanyDTO.setPayScale(acceptInsuranceInfoVO.getAttributeValues());
					break;
				case ReturnCode.STATUS_CODE_5:
					//inusureCompanyDTO.setPayType("一对多解付");
					//inusureCompanyDTO.setPayScale(acceptInsuranceInfoVO.getAttributeValues());
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
			icdList.add(inusureCompanyDTO);
		}
		return icdList;
	}
	@Override
	@Transactional(readOnly = true)
	public List<Factor> findFactorInfo(String productCode) {
		 List<Factor> fList = insuranceFactorRelaMapper.findFactorInfo(productCode);
		 if(fList!=null&&fList.size()>0) {
			 Example confCoefficientExample = new Example(ConfCoefficient.class);
			 ConfCoefficient confCoefficient = null;
			 for (Factor factor : fList) {
				 confCoefficientExample.createCriteria().andEqualTo("status", ReturnCode.STATUS_CODE_1).andEqualTo("factorRelaId", factor.getFactorCode());
				 List<ConfCoefficient> confCoefficientList = confCoefficientMapper.selectByExample(confCoefficientExample);
				 if(confCoefficientList!=null&&confCoefficientList.size()>0) {
					 confCoefficient = confCoefficientList.get(0);
					 if(StringUtils.equals(confCoefficient.getOperatorType(), ReturnCode.STATUS_CODE_2)) {
						 factor.setType(confCoefficient.getOperatorType());
					 }
				 }
			}
			 return fList;
		 }else {
			 //TODO 因子为空 处理
			 return null;
		 }
	}
	/**
	 * 
	 * @param productCode
	 * @return
	 * 描述：寿险查询产品险种基本信息
	 */
	public List<RiskDetails> findRiskBaseInfo(String productCode){
		List<String> riskCodeList = productPrivateInsuranceMapper.selectRiskCodeByProductCode(productCode);
		List<RiskDetails> riskInfoList = new ArrayList<>();
		for (String riskCode : riskCodeList) {
			RiskDetails riskDetails = new RiskDetails();
			RiskInfo riskInfo = riskInfoMapper.selectByRiskCode(riskCode);
			ProductPrivateInsurance productPrivateInsurance = productPrivateInsuranceMapper.selectByProductCodeAndRiskCode(productCode, riskCode);
			riskDetails.setCode(productPrivateInsurance.getRiskCode());
			riskDetails.setName(productPrivateInsurance.getRiskName());
			riskDetails.setHesitationPeriod(StringUtils.isEmpty(productPrivateInsurance.getHesitationPeriod())?"":productPrivateInsurance.getHesitationPeriod());
			riskDetails.setIsMust(productPrivateInsurance.getIsMust());
			riskDetails.setRiskShortFlag(riskInfo.getRiskShortFlag());
			riskDetails.setIssueAge(productPrivateInsurance.getIssueAge());
			riskDetails.setPayType(productPrivateInsurance.getPayType());
			riskDetails.setPeriodGrace(StringUtils.isEmpty(productPrivateInsurance.getPeriodGrace())?"":productPrivateInsurance.getPeriodGrace());
			riskDetails.setProbation(StringUtils.isEmpty(productPrivateInsurance.getProbation())?"":productPrivateInsurance.getProbation());
			riskDetails.setRiskFlag(productPrivateInsurance.getRiskFlag());
			riskDetails.setWaitingPeriod(StringUtils.isEmpty(productPrivateInsurance.getWaitingPeriod())?"":productPrivateInsurance.getWaitingPeriod());
			//险种条款
			List<InsuranceClause> insuranceClauseList = insuranceClauseMapper.selectByTheirId(riskCode);
			List<RiskClause> riskClauseList = new ArrayList<>();
			RiskClause riskClause =null;
			for (InsuranceClause insuranceClause : insuranceClauseList) {
				riskClause = new RiskClause();
				riskClause.setName(insuranceClause.getInsuranceClauseName());
				riskClause.setUrl(insuranceClause.getInsuranceClauseUrl());
				riskClauseList.add(riskClause);
			}
			riskDetails.setRiskClauseList(riskClauseList);
			//险种费率规则
			List<ProductRuleInfo> rroductRuleInfoList = productRuleInfoMapper.selectByProductCodeAndTypeCodeWithRate(productCode, riskCode);
			if(rroductRuleInfoList!=null&&rroductRuleInfoList.size()>0) {
				List<RuleDTO> ruleRateList = new ArrayList<>();
				for (ProductRuleInfo productRuleInfo : rroductRuleInfoList) {
					RuleDTO ruleDTO = new RuleDTO();
					ruleDTO.setRuleId(productRuleInfo.getRuleId());
					ruleDTO.setRuleCode(productRuleInfo.getRuleCode());
					ruleDTO.setRuleName(productRuleInfo.getRuleName());
					ruleDTO.setRuleType(productRuleInfo.getRuleType());
					ruleDTO.setTypeCode(productRuleInfo.getTypeCode());
					ruleDTO.setValue(productRuleInfo.getRuleValue());
					ruleDTO.setValueAndUnit(productRuleInfo.getValueUnit());
					ruleRateList.add(ruleDTO);
				}
				riskDetails.setRuleRateList(ruleRateList);
			}
			
			//险种规则(与费率无关规则)
			List<ProductRuleInfo> productRuleInfoList2 = productRuleInfoMapper.selectByProductCodeAndTypeCode(productCode, riskCode);
			if(productRuleInfoList2!=null&&productRuleInfoList2.size()>0) {
				List<RuleDTO> ruleList = new ArrayList<>();
				for (ProductRuleInfo productRuleInfo : productRuleInfoList2) {
					RuleDTO ruleDTO = new RuleDTO();
					ruleDTO.setRuleId(productRuleInfo.getRuleId());
					ruleDTO.setRuleCode(productRuleInfo.getRuleCode());
					ruleDTO.setRuleName(productRuleInfo.getRuleName());
					ruleDTO.setRuleType(productRuleInfo.getRuleType());
					ruleDTO.setTypeCode(productRuleInfo.getTypeCode());
					ruleDTO.setValue(productRuleInfo.getRuleValue());
					ruleDTO.setValueAndUnit(productRuleInfo.getValueUnit());
					ruleList.add(ruleDTO);
				}
				
				riskDetails.setRuleList(ruleList);
			}
			riskInfoList.add(riskDetails);
		}
		return riskInfoList;
	}
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<?> findProductDetails(String productCode){
		ProductInfoDTO piDTO = this.findProductInfo(productCode);
        if(piDTO==null) {
        	return GenericResponse.ng("沒有满足条件的数据");
        }
        switch (piDTO.getProductType()) {
		case "00"://责任险
			//因子集合
			List<FactorVO> factors = this.findFactors(productCode);
			if(factors !=null && factors.size( ) > 0) {
	            piDTO.setHasFactors("1");
	            piDTO.setFactorVOList(factors);
	        }else {
	            piDTO.setHasFactors("0");
	        }
	        List<ProgramInfoBean> programList = this.findProgramInfo(productCode);
	        boolean mark = false;
	        for (ProgramInfoBean programInfoBean : programList) {
	        	List<LiabilityInfoBean> liabilityList = programInfoBean.getLiabilityList();
	        	for (LiabilityInfoBean liabilityInfoBean : liabilityList) {
	        		if(StringUtils.equals(liabilityInfoBean.getRiskFlag(), "A")) {
						mark = true;
						break;
					}
				}
			}
	        if(mark) {
	        	piDTO.setHasAttachRisk(ReturnCode.STATUS_CODE_1);
	        }else {
	        	piDTO.setHasAttachRisk(ReturnCode.STATUS_CODE_0);
	        }
	        piDTO.setProgramList(this.findProgramInfo(productCode));
	        List<InusureCompanyDTO> icList = this.findInusureCompanyInfo(productCode);
	        if(icList!=null&&icList.size()>0) {
	        	piDTO.setInusureCompanyList(icList);
	        }
	        List<RuleDTO> ruleList = this.findruleByProductCode(productCode);
	        if(ruleList!=null&&ruleList.size()>0) {
	        	piDTO.setRuleList(ruleList);
	        }
	        return GenericDataResponse.okWithData(piDTO);
		case "01"://寿险
			LifeProductDetailsDTO lifeProductDetailsDTO = new LifeProductDetailsDTO();
			//寿险 险种信息
			List<RiskDetails> riskInfoList = this.findRiskBaseInfo(productCode);
			lifeProductDetailsDTO.setRiskInfoList(riskInfoList);
			//产品的基本信息
			ProductInfo pi = productInfoMapper.selectByProductCode(productCode);
			lifeProductDetailsDTO.setCode(pi.getProductCode());
			lifeProductDetailsDTO.setName(pi.getProductName());
			lifeProductDetailsDTO.setProductDesc(pi.getProductDesc());
			lifeProductDetailsDTO.setIsRecord(pi.getIsRecord());
			lifeProductDetailsDTO.setIsOptimalTax(pi.getIsOptimalTax());
			lifeProductDetailsDTO.setRiskClass("产品所属险类");
			lifeProductDetailsDTO.setAgreementCode(pi.getAgreementCode());
			//TODO 产品条款
			List<InsuranceClause> insuranceClauseList = insuranceClauseMapper.selectByTheirId(pi.getProductCode());
			List<ClauseFileInfo> clauseList = new ArrayList<>();
			ClauseFileInfo clause =null;
			for (InsuranceClause insuranceClause : insuranceClauseList) {
				clause = new ClauseFileInfo();
				clause.setName(insuranceClause.getInsuranceClauseName());
				clause.setUrl(insuranceClause.getInsuranceClauseUrl());
				clauseList.add(clause);
			}
			lifeProductDetailsDTO.setClauseList(clauseList);
			lifeProductDetailsDTO.setLogoUrl("20180809/4cd35591-b5a8-4494-8003-00581a71d81d.png");
			lifeProductDetailsDTO.setOnsaleAreas("");
			//承保公司
			List<InusureCompanyDTO> inusureCompanyList= this.findInusureCompanyInfo(productCode);
			if(inusureCompanyList!=null&&inusureCompanyList.size()>0) {
				lifeProductDetailsDTO.setInusureCompanyList(inusureCompanyList);
			}
			//因子集合
			List<FactorVO> factors2 = this.findFactors(productCode);
			if(factors2 !=null && factors2.size( ) > 0) {
	            piDTO.setHasFactors("1");
	            piDTO.setFactorVOList(factors2);
	        }else {
	            piDTO.setHasFactors("0");
	        }
			return GenericDataResponse.okWithData(lifeProductDetailsDTO);
		default:
			return GenericResponse.ng("沒有满足条件的数据");
		}
	}
    /**
	 * 省市县 地区查询
	 * @return
	 */
	@Override
	public ResponseEntity<?> selectArea() {
		List<Area> areas = areaMapper.selectAll();
		List<AreaVM> areaVMS = parseTree(areas);
		if(areaVMS != null && areaVMS.size() > 0 ){
			return GenericListResponse.listNoCount(areaVMS);
		}
		return null;
	}
	 /**
     * 将资源集合封装为树
     */
    public List<AreaVM> parseTree(List<Area> areas) {
        List<AreaVM> list = EntityUtils.entity2VMList(areas, AreaVM.class);
        Map<String, AreaVM> map = new HashMap<String, AreaVM>();
        List<AreaVM> root = new ArrayList<AreaVM>();
        for (AreaVM resouceVM : list) {
            resouceVM.setChildren(new ArrayList<AreaVM>());
            map.put(resouceVM.getRegionCode(), resouceVM);
        }
        for (AreaVM resourceVM : list) {
            AreaVM parentResource = map.get(resourceVM.getParentRegionCode());
            if (parentResource == null) {
                root.add(resourceVM);
                continue;
            }
            parentResource.getChildren().add(resourceVM);
        }
        return root;
    }


	@Override
	public ProductInfoDTO findProductInfo(String productCode) {
		ProductInfo pi = productInfoMapper.selectByProductCode(productCode);
		//产品不存在
		if(pi==null) {
			log.error(ProductConfigBeanEnum.NO_DATA_PRODUCT.getMessage());
			return null;
		}else {
			//产品未上架
			if(!StringUtils.contains(pi.getProductStatus(), ReturnCode.STATUS_CODE_6)) {
				log.error("产品状态有误！状态应该为6-已上架，现在为"+pi.getProductStatus());
				return null;
			}
			ProductInfoDTO productInfoDTO = new ProductInfoDTO();
			productInfoDTO.setCode(pi.getProductCode());
			productInfoDTO.setName(pi.getProductName());
			productInfoDTO.setDescription(pi.getProductDesc());
			productInfoDTO.setOnsaleAreas("");
			productInfoDTO.setClauseFileName("横琴优康保终身重大疾病保险_保险条款.pdf");
			productInfoDTO.setClauseFileUrl("20180807/d7e57b56-da30-4cc1-887e-a64ba909cb30.pdf");
			productInfoDTO.setLogoUrl("20180809/5c3bd14b-0510-40db-9930-d8753db89777.jpg");
			productInfoDTO.setProductType(pi.getProductType());
			return productInfoDTO;
		}	
	}
	/**
	 * 
	 * @param productCode
	 * @return
	 * 描述：方案详情
	 */
	/*@Override
	@Transactional(readOnly = true)
	public List<ProgramInfoBean> findProgramInfo(String productCode) {
		ProductInfo pi = productInfoMapper.selectByProductCode(productCode);
		//产品不存在
		if(pi==null) {
			log.error(ProductConfigBeanEnum.NO_DATA_PRODUCT.getMessage());
			return null;
		}else {
			if(!StringUtils.equals(pi.getProductStatus(), ReturnCode.STATUS_CODE_6)) {//产品未上架
				log.error("产品状态有误！状态应该为6-已上架，现在为"+pi.getProductStatus());
				return null;
			}
			List<ProgramInfoBean> programInfoList = new ArrayList<>();
			ProgramInfoBean programInfoBean =null;
			List<InsuranceProgram> programList = insuranceProgramMapper.selectByProductCode(pi.getProductCode());
			for (InsuranceProgram insuranceProgram : programList) {
				programInfoBean = new ProgramInfoBean();
				//通过方案编码查询限额 并 取得对应险种
				List<LiabilityLimitRela> relaList = liabilityLimitRelaMapper.selectByProgramCode(insuranceProgram.getProgramCode());
				List<String> liabilityCodeList = new ArrayList<>();
				Map<String,String> riskMap = new HashMap<>();
				for (LiabilityLimitRela liabilityLimitRela : relaList) {
					liabilityCodeList.add(liabilityLimitRela.getLiabilityCode());
					InsuranceLiability insuranceLiability = insuranceLiabilityMapper.selectByInsuranceLiabilityCode(liabilityLimitRela.getLiabilityCode());
					riskMap.put(insuranceLiability.getRiskCode(), insuranceLiability.getRiskName());
					liabilityLimitRela.setRiskCode(insuranceLiability.getRiskCode());
					liabilityLimitRela.setRiskName(insuranceLiability.getRiskName());
				}
				RiskInfoBean riskInfoBean = null;
				List<RiskInfoBean> riskInfoList = null;
				switch (insuranceProgram.getBasicPremiumType()) {
				//固定
				case ReturnCode.STATUS_CODE_1:
					
					riskInfoList = new ArrayList<>();
					for (String riksCode : riskMap.keySet()) {
						riskInfoBean = new RiskInfoBean();
						riskInfoBean.setCode(riksCode);
						riskInfoBean.setName(riskMap.get(riksCode));
						List<LiabilityInfoBean> liabilityList = new ArrayList<>();
						LiabilityInfoBean liabilityInfoBean = null;
						Map<String,LiabilityLimitRela> liabilityMap = new HashMap<>();
						for (LiabilityLimitRela liabilityLimitRela : relaList) {
							if(StringUtils.equals(liabilityLimitRela.getRiskCode(), riksCode)) {
								liabilityMap.put(liabilityLimitRela.getLiabilityCode(), liabilityLimitRela);
							}
						}
							//责任
							for (String liabilityCode : liabilityMap.keySet()) {
								liabilityInfoBean = new LiabilityInfoBean();
								liabilityInfoBean.setCode(liabilityMap.get(liabilityCode).getLiabilityCode());
								liabilityInfoBean.setName(liabilityMap.get(liabilityCode).getLiabilityName());
								LimitInfoBean limitInfoBean = null;
								List<LimitInfoBean> limitList = new ArrayList<>();
								for (LiabilityLimitRela liabilityLimitRela : relaList) {
									if(StringUtils.equals(liabilityLimitRela.getLiabilityCode(), liabilityCode)) {
										limitInfoBean = new LimitInfoBean();
										limitInfoBean.setCode(liabilityLimitRela.getLimitCode());
										limitInfoBean.setIsFloat(ReturnCode.STATUS_CODE_0);
										limitInfoBean.setName(liabilityLimitRela.getLimitName());
										limitInfoBean.setPremium(null);
										limitInfoBean.setValue(liabilityLimitRela.getLimitValues());
										limitInfoBean.setValueCode(liabilityLimitRela.getLimitValuesCode());
										limitList.add(limitInfoBean);
									}
								}
								liabilityInfoBean.setLimitList(limitList);
								liabilityList.add(liabilityInfoBean);
							}
							riskInfoBean.setLiabilityList(liabilityList);
							riskInfoList.add(riskInfoBean);
						}
					programInfoBean.setRiskList(riskInfoList);
					programInfoBean.setCode(insuranceProgram.getProgramCode());
					programInfoBean.setFixFloatFlag(insuranceProgram.getBasicPremiumType());
					programInfoBean.setRiskFlag(insuranceProgram.getRiskFlag());
					programInfoBean.setPremium(insuranceProgram.getBasicPremium());
					programInfoList.add(programInfoBean);
					break;
				//浮动
				case ReturnCode.STATUS_CODE_3:
					riskInfoList = new ArrayList<>();
					for (String riksCode : riskMap.keySet()) {
						riskInfoBean = new RiskInfoBean();
						riskInfoBean.setCode(riksCode);
						riskInfoBean.setName(riskMap.get(riksCode));
						List<LiabilityInfoBean> liabilityList = new ArrayList<>();
						LiabilityInfoBean liabilityInfoBean = null;
						Map<String,LiabilityLimitRela> liabilityMap = new HashMap<>();
						for (LiabilityLimitRela liabilityLimitRela : relaList) {
							if(StringUtils.equals(liabilityLimitRela.getRiskCode(), riksCode)) {
								liabilityMap.put(liabilityLimitRela.getLiabilityCode(), liabilityLimitRela);
							}
						}
							//责任
							for (String liabilityCode : liabilityMap.keySet()) {
								liabilityInfoBean = new LiabilityInfoBean();
								liabilityInfoBean.setCode(liabilityMap.get(liabilityCode).getLiabilityCode());
								liabilityInfoBean.setName(liabilityMap.get(liabilityCode).getLiabilityName());
								LimitInfoBean limitInfoBean = null;
								List<LimitInfoBean> limitList = new ArrayList<>();
								Map<String,LiabilityLimitRela> limitMap = new HashMap<>();
								for (LiabilityLimitRela liabilityLimitRela : relaList) {
									if(StringUtils.equals(liabilityLimitRela.getLiabilityCode(), liabilityCode)) {
										limitMap.put(liabilityLimitRela.getLimitCode(), liabilityLimitRela);
									}
								}
								for (String limitCode : limitMap.keySet()) {
									limitInfoBean = new LimitInfoBean();
									limitInfoBean.setCode(limitCode);
									limitInfoBean.setName(limitMap.get(limitCode).getLimitName());
									limitInfoBean.setIsFloat(ReturnCode.STATUS_CODE_1);
									List<FloatRate> floatRateList = floatRateMapper.selectByprogramCodeAndLimitCode(insuranceProgram.getProgramCode(),limitCode);
									RateInfoBean rateInfoBean = null;
									List<RateInfoBean> rateInfoList = new ArrayList<>();
									for (FloatRate floatRate : floatRateList) {
										 rateInfoBean = new RateInfoBean();
										 rateInfoBean.setMax(floatRate.getMaxLimitValue());
										 rateInfoBean.setMin(floatRate.getMinLimitValue());
										 rateInfoBean.setRate(floatRate.getRate());
										 switch (floatRate.getMaxScopeSign()) {
										case ReturnCode.CODE0:
											switch (floatRate.getMinScopeSign()) {
											case ReturnCode.CODE0://()
												 rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_1);
												break;
											case ReturnCode.CODE1://[)
												 rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_2);
												break;
											default:
												break;
											}
											break;
										case ReturnCode.CODE1:
											switch (floatRate.getMinScopeSign()) {
											case ReturnCode.CODE0://(]
												 rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_3);
												break;
											case ReturnCode.CODE1://[]
												 rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_4);
												break;
											default:
												break;
											}
											break;
										default:
											break;
										}
										 rateInfoList.add(rateInfoBean);
									}
									limitInfoBean.setRateList(rateInfoList);
									limitList.add(limitInfoBean);
								}
								liabilityInfoBean.setLimitList(limitList);
								liabilityList.add(liabilityInfoBean);
							}
							riskInfoBean.setLiabilityList(liabilityList);
							riskInfoList.add(riskInfoBean);
						}
					programInfoBean.setRiskList(riskInfoList);
					programInfoBean.setCode(insuranceProgram.getProgramCode());
					programInfoBean.setFixFloatFlag(insuranceProgram.getBasicPremiumType());
					programInfoBean.setRiskFlag(insuranceProgram.getRiskFlag());
					programInfoBean.setPremium(insuranceProgram.getBasicPremium());
					programInfoList.add(programInfoBean);
					break;
				//固定加浮动
				case ReturnCode.STATUS_CODE_4:
					riskInfoList = new ArrayList<>();
					for (String riksCode : riskMap.keySet()) {
						riskInfoBean = new RiskInfoBean();
						riskInfoBean.setCode(riksCode);
						riskInfoBean.setName(riskMap.get(riksCode));
						List<LiabilityInfoBean> liabilityList = new ArrayList<>();
						LiabilityInfoBean liabilityInfoBean = null;
						Map<String,LiabilityLimitRela> liabilityMap = new HashMap<>();
						for (LiabilityLimitRela liabilityLimitRela : relaList) {
							if(StringUtils.equals(liabilityLimitRela.getRiskCode(), riksCode)) {
								liabilityMap.put(liabilityLimitRela.getLiabilityCode(), liabilityLimitRela);
							}
						}
							//责任
							for (String liabilityCode : liabilityMap.keySet()) {
								liabilityInfoBean = new LiabilityInfoBean();
								liabilityInfoBean.setCode(liabilityMap.get(liabilityCode).getLiabilityCode());
								liabilityInfoBean.setName(liabilityMap.get(liabilityCode).getLiabilityName());
								LimitInfoBean limitInfoBean = null;
								List<LimitInfoBean> limitList = new ArrayList<>();
								Map<String,LiabilityLimitRela> limitMap = new HashMap<>();
								for (LiabilityLimitRela liabilityLimitRela : relaList) {
									if(liabilityLimitRela.getPremium()!=null) {
										if(StringUtils.equals(liabilityLimitRela.getLiabilityCode(), liabilityCode)) {
											limitInfoBean = new LimitInfoBean();
											limitInfoBean.setCode(liabilityLimitRela.getLimitCode());
											limitInfoBean.setIsFloat(ReturnCode.STATUS_CODE_0);
											limitInfoBean.setName(liabilityLimitRela.getLimitName());
											limitInfoBean.setPremium(liabilityLimitRela.getPremium());
											limitInfoBean.setValue(liabilityLimitRela.getLimitValues());
											limitInfoBean.setValueCode(liabilityLimitRela.getLimitValuesCode());
											limitList.add(limitInfoBean);
										}
									}else {
										if(StringUtils.equals(liabilityLimitRela.getLiabilityCode(), liabilityCode)) {
											limitMap.put(liabilityLimitRela.getLimitCode(), liabilityLimitRela);
										}
									}
								}
								
								//有浮动
								if(limitMap.size()>0) {
									for (LiabilityLimitRela liabilityLimitRela : relaList) {
										for (String limitCode : limitMap.keySet()) {
											if(StringUtils.equals(liabilityLimitRela.getLimitCode(), limitCode)) {
												limitInfoBean = new LimitInfoBean();
												limitInfoBean.setCode(limitCode);
												limitInfoBean.setIsFloat(ReturnCode.STATUS_CODE_1);
												limitInfoBean.setName(liabilityLimitRela.getLimitName());
												List<FloatRate> floatRateList = floatRateMapper.selectByprogramCodeAndLimitCode(insuranceProgram.getProgramCode(),limitCode);
												RateInfoBean rateInfoBean = null;
												List<RateInfoBean> rateInfoList = new ArrayList<>();
												for (FloatRate floatRate : floatRateList) {
													 rateInfoBean = new RateInfoBean();
													 rateInfoBean.setMax(floatRate.getMaxLimitValue());
													 rateInfoBean.setMin(floatRate.getMinLimitValue());
													 rateInfoBean.setRate(floatRate.getRate());
													 switch (floatRate.getMaxScopeSign()) {
													case ReturnCode.CODE0:
														switch (floatRate.getMinScopeSign()) {
														case ReturnCode.CODE0://()
															 rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_1);
															break;
														case ReturnCode.CODE1://[)
															 rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_2);
															break;
														default:
															break;
														}
														break;
													case ReturnCode.CODE1:
														switch (floatRate.getMinScopeSign()) {
														case ReturnCode.CODE0://(]
															 rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_3);
															break;
														case ReturnCode.CODE1://[]
															 rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_4);
															break;
														default:
															break;
														}
														break;
													default:
														break;
													}
													 rateInfoList.add(rateInfoBean);
												}
												limitInfoBean.setRateList(rateInfoList);
											}
										}
									}
								}
								liabilityInfoBean.setLimitList(limitList);
								liabilityList.add(liabilityInfoBean);
							}
							riskInfoBean.setLiabilityList(liabilityList);
							riskInfoList.add(riskInfoBean);
						}
					programInfoBean.setRiskList(riskInfoList);
					programInfoBean.setCode(insuranceProgram.getProgramCode());
					programInfoBean.setFixFloatFlag(insuranceProgram.getBasicPremiumType());
					programInfoBean.setRiskFlag(insuranceProgram.getRiskFlag());
					programInfoBean.setPremium(insuranceProgram.getBasicPremium());
					programInfoList.add(programInfoBean);
					break;
				default:
					break;
				}
			}
			return programInfoList;
		}
	}*/
	/**
	 * 
	 * @param productCode
	 * @return
	 * 描述： 产品销售属性
	 */
	@Override
	@Transactional(readOnly = true)
	public List<RuleDTO> findruleByProductCode(String productCode) {
		List<ProductRuleInfo> list = productRuleInfoMapper.selectByProductCode(productCode);
		if(list==null||list.size()<1) {
			return null;
		}
		List<RuleDTO> ruleList = new ArrayList<>();
		RuleDTO ruleDTO = null;
		for (ProductRuleInfo productRuleInfo : list) {
			ruleDTO = new RuleDTO();
			ruleDTO.setRuleId(productRuleInfo.getRuleId());
			ruleDTO.setRuleName(productRuleInfo.getRuleName());
			ruleDTO.setValue(productRuleInfo.getRuleValue());
			ruleList.add(ruleDTO);
		}
		return ruleList;
	}
    /**
     * @param productCode
     * @return 描述：方案详情
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProgramInfoBean> findProgramInfo(String productCode) {
        ProductInfo pi = productInfoMapper.selectByProductCode(productCode);
        //产品不存在
        if (pi == null) {
            log.error(ProductConfigBeanEnum.NO_DATA_PRODUCT.getMessage());
            return null;
        } else {
            if (!StringUtils.equals(pi.getProductStatus(), ReturnCode.STATUS_CODE_6)) {//产品未上架
                log.error("产品状态有误！状态应该为6-已上架，现在为" + pi.getProductStatus());
                return null;
            }
            List<ProgramInfoBean> programInfoList = new ArrayList<>();
            ProgramInfoBean programInfoBean = null;
            List<InsuranceProgram> programList = insuranceProgramMapper.selectByProductCode(pi.getProductCode());
            for (InsuranceProgram insuranceProgram : programList) {
                programInfoBean = new ProgramInfoBean();
                //通过方案编码查询限额 并 取得对应险种
                List<LiabilityLimitRela> relaList = liabilityLimitRelaMapper.selectByProgramCode(insuranceProgram.getProgramCode());
                Map<String, LiabilityLimitRela> liabilityMap = null;
                List<LiabilityInfoBean> liabilityList = null;
                switch (insuranceProgram.getBasicPremiumType()) {
                    //固定
                    case ReturnCode.STATUS_CODE_1:
                        liabilityList = new ArrayList<>();
                        LiabilityInfoBean liabilityInfoBean = null;
                        liabilityMap = new HashMap<>();
                        for (LiabilityLimitRela liabilityLimitRela : relaList) {
                            liabilityMap.put(liabilityLimitRela.getLiabilityCode(), liabilityLimitRela);
                        }
                        //责任
                        for (String liabilityCode : liabilityMap.keySet()) {
                            liabilityInfoBean = new LiabilityInfoBean();
                            InsuranceLiability insuranceLiability = insuranceLiabilityMapper.selectByInsuranceLiabilityCode(liabilityCode);
                            liabilityInfoBean.setCode(liabilityMap.get(liabilityCode).getLiabilityCode());
                            liabilityInfoBean.setName(liabilityMap.get(liabilityCode).getLiabilityName());
                            liabilityInfoBean.setRiskCode(insuranceLiability.getRiskCode());
                            liabilityInfoBean.setRiskName(insuranceLiability.getRiskName());
                            LimitInfoBean limitInfoBean = null;
                            List<LimitInfoBean> limitList = new ArrayList<>();
                            for (LiabilityLimitRela liabilityLimitRela : relaList) {
                                if (StringUtils.equals(liabilityLimitRela.getLiabilityCode(), liabilityCode)) {
                                    limitInfoBean = new LimitInfoBean();
                                    limitInfoBean.setCode(liabilityLimitRela.getLimitCode());
                                    limitInfoBean.setIsFloat(ReturnCode.STATUS_CODE_0);
                                    limitInfoBean.setName(liabilityLimitRela.getLimitName());
                                    limitInfoBean.setPremium(null);
                                    limitInfoBean.setValue(liabilityLimitRela.getLimitValues());
                                    limitInfoBean.setValueCode(liabilityLimitRela.getLimitValuesCode());
                                    limitList.add(limitInfoBean);
                                }
                            }
                            liabilityInfoBean.setLimitList(limitList);
                            liabilityList.add(liabilityInfoBean);
                        }
                        programInfoBean.setLiabilityList(liabilityList);
                        //programInfoBean.setRiskList(riskInfoList);
                        programInfoBean.setCode(insuranceProgram.getProgramCode());
                        programInfoBean.setFixFloatFlag(insuranceProgram.getBasicPremiumType());
                        programInfoBean.setPremium(insuranceProgram.getBasicPremium());
                        programInfoList.add(programInfoBean);
                        break;
                    //浮动
                    case ReturnCode.STATUS_CODE_3:
                        liabilityList = new ArrayList<>();
                        liabilityMap = new HashMap<>();
                        for (LiabilityLimitRela liabilityLimitRela : relaList) {
                            liabilityMap.put(liabilityLimitRela.getLiabilityCode(), liabilityLimitRela);
                        }
                        //责任
                        for (String liabilityCode : liabilityMap.keySet()) {
                            liabilityInfoBean = new LiabilityInfoBean();
                            liabilityInfoBean.setCode(liabilityMap.get(liabilityCode).getLiabilityCode());
                            liabilityInfoBean.setName(liabilityMap.get(liabilityCode).getLiabilityName());
                            InsuranceLiability insuranceLiability = insuranceLiabilityMapper.selectByInsuranceLiabilityCode(liabilityCode);
                            liabilityInfoBean.setRiskCode(insuranceLiability.getRiskCode());
                            liabilityInfoBean.setRiskName(insuranceLiability.getRiskName());
                            LimitInfoBean limitInfoBean = null;
                            List<LimitInfoBean> limitList = new ArrayList<>();
                            Map<String, LiabilityLimitRela> limitMap = new HashMap<>();
                            for (LiabilityLimitRela liabilityLimitRela : relaList) {
                                if (StringUtils.equals(liabilityLimitRela.getLiabilityCode(), liabilityCode)) {
                                    limitMap.put(liabilityLimitRela.getLimitCode(), liabilityLimitRela);
                                }
                            }
                            for (String limitCode : limitMap.keySet()) {
                                limitInfoBean = new LimitInfoBean();
                                limitInfoBean.setCode(limitCode);
                                limitInfoBean.setName(limitMap.get(limitCode).getLimitName());
                                LiabilityLimit limit = liabilityLimitMapper.selectByLiabilityLimitCode(limitCode);
                                limitInfoBean.setMark(limit.getMark());
                                List<LiabilityLimitValues> valueList = liabilityLimitValuesMapper.selectByLimitCode(limitCode);
                                List<ValueBean> vlaueList = new ArrayList<>();
                                ValueBean vlaueBean = null;
                                for (LiabilityLimitValues liabilityLimitValues : valueList) {
                                    vlaueBean = new ValueBean();
                                    vlaueBean.setCode(liabilityLimitValues.getLiabilityLimitValuesCode());
                                    if (StringUtils.equals(liabilityLimitValues.getLiabilityLimitValuesType(), ReturnCode.STATUS_CODE_1)) {
                                        vlaueBean.setValue(String.valueOf(liabilityLimitValues.getLiabilityLimitValues()));
                                    } else {
                                        vlaueBean.setValue(liabilityLimitValues.getLimitValuesDesc());
                                    }
                                    vlaueList.add(vlaueBean);
                                }
                                limitInfoBean.setValueList(vlaueList);
                                limitInfoBean.setIsFloat(ReturnCode.STATUS_CODE_1);
                                List<FloatRate> floatRateList = floatRateMapper.selectByprogramCodeAndLimitCode(insuranceProgram.getProgramCode(), limitCode);
                                RateInfoBean rateInfoBean = null;
                                List<RateInfoBean> rateInfoList = new ArrayList<>();
                                for (FloatRate floatRate : floatRateList) {
                                    rateInfoBean = new RateInfoBean();
                                    rateInfoBean.setMax(floatRate.getMaxLimitValue());
                                    rateInfoBean.setMin(floatRate.getMinLimitValue());
                                    rateInfoBean.setRate(floatRate.getRate());
                                    switch (floatRate.getMaxScopeSign()) {
                                        case ReturnCode.CODE0:
                                            switch (floatRate.getMinScopeSign()) {
                                                case ReturnCode.CODE0://()
                                                    rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_1);
                                                    break;
                                                case ReturnCode.CODE1://[)
                                                    rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_2);
                                                    break;
                                                default:
                                                    break;
                                            }
                                            break;
                                        case ReturnCode.CODE1:
                                            switch (floatRate.getMinScopeSign()) {
                                                case ReturnCode.CODE0://(]
                                                    rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_3);
                                                    break;
                                                case ReturnCode.CODE1://[]
                                                    rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_4);
                                                    break;
                                                default:
                                                    break;
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                    rateInfoList.add(rateInfoBean);
                                }
                                limitInfoBean.setRateList(rateInfoList);
                                limitList.add(limitInfoBean);
                            }
                            liabilityInfoBean.setLimitList(limitList);
                            liabilityList.add(liabilityInfoBean);
                        }
                        programInfoBean.setLiabilityList(liabilityList);
                        programInfoBean.setCode(insuranceProgram.getProgramCode());
                        programInfoBean.setFixFloatFlag(insuranceProgram.getBasicPremiumType());
                        programInfoBean.setPremium(insuranceProgram.getBasicPremium());
                        programInfoList.add(programInfoBean);
                        break;
                    //固定加浮动
                    case ReturnCode.STATUS_CODE_4:
                        liabilityMap = new HashMap<>();
                        liabilityList = new ArrayList<>();
                        for (LiabilityLimitRela liabilityLimitRela : relaList) {
                            liabilityMap.put(liabilityLimitRela.getLiabilityCode(), liabilityLimitRela);
                        }
                        //责任
                        for (String liabilityCode : liabilityMap.keySet()) {
                            liabilityInfoBean = new LiabilityInfoBean();
                            liabilityInfoBean.setCode(liabilityMap.get(liabilityCode).getLiabilityCode());
                            liabilityInfoBean.setName(liabilityMap.get(liabilityCode).getLiabilityName());
                            LimitInfoBean limitInfoBean = null;
                            List<LimitInfoBean> limitList = new ArrayList<>();
                            Map<String, LiabilityLimitRela> limitMap = new HashMap<>();
                            for (LiabilityLimitRela liabilityLimitRela : relaList) {
                                if (liabilityLimitRela.getPremium() != null) {
                                    if (StringUtils.equals(liabilityLimitRela.getLiabilityCode(), liabilityCode)) {
                                        limitInfoBean = new LimitInfoBean();
                                        limitInfoBean.setCode(liabilityLimitRela.getLimitCode());
                                        limitInfoBean.setIsFloat(ReturnCode.STATUS_CODE_0);
                                        limitInfoBean.setName(liabilityLimitRela.getLimitName());
                                        limitInfoBean.setPremium(liabilityLimitRela.getPremium());
                                        limitInfoBean.setValue(liabilityLimitRela.getLimitValues());
                                        limitInfoBean.setValueCode(liabilityLimitRela.getLimitValuesCode());
                                        limitList.add(limitInfoBean);
                                    }
                                } else {
                                    if (StringUtils.equals(liabilityLimitRela.getLiabilityCode(), liabilityCode)) {
                                        limitMap.put(liabilityLimitRela.getLimitCode(), liabilityLimitRela);
                                    }
                                }
                            }
                            //有浮动
                            if (limitMap.size() > 0) {
                                for (LiabilityLimitRela liabilityLimitRela : relaList) {
                                    for (String limitCode : limitMap.keySet()) {
                                        if (StringUtils.equals(liabilityLimitRela.getLimitCode(), limitCode)) {
                                            limitInfoBean = new LimitInfoBean();
                                            limitInfoBean.setCode(limitCode);
                                            List<LiabilityLimitValues> valueList = liabilityLimitValuesMapper.selectByLimitCode(limitCode);
                                            List<ValueBean> vlaueList = new ArrayList<>();
                                            ValueBean vlaueBean = null;
                                            for (LiabilityLimitValues liabilityLimitValues : valueList) {
                                                vlaueBean = new ValueBean();
                                                vlaueBean.setCode(liabilityLimitValues.getLiabilityLimitValuesCode());
                                                if (StringUtils.equals(liabilityLimitValues.getLiabilityLimitValuesType(), ReturnCode.STATUS_CODE_1)) {
                                                    vlaueBean.setValue(String.valueOf(liabilityLimitValues.getLiabilityLimitValues()));
                                                } else {
                                                    vlaueBean.setValue(liabilityLimitValues.getLimitValuesDesc());
                                                }
                                                vlaueList.add(vlaueBean);
                                            }
                                            limitInfoBean.setValueList(vlaueList);
                                            limitInfoBean.setIsFloat(ReturnCode.STATUS_CODE_1);
                                            limitInfoBean.setName(liabilityLimitRela.getLimitName());
                                            List<FloatRate> floatRateList = floatRateMapper.selectByprogramCodeAndLimitCode(insuranceProgram.getProgramCode(), limitCode);
                                            RateInfoBean rateInfoBean = null;
                                            List<RateInfoBean> rateInfoList = new ArrayList<>();
                                            for (FloatRate floatRate : floatRateList) {
                                                rateInfoBean = new RateInfoBean();
                                                rateInfoBean.setMax(floatRate.getMaxLimitValue());
                                                rateInfoBean.setMin(floatRate.getMinLimitValue());
                                                rateInfoBean.setRate(floatRate.getRate());
                                                switch (floatRate.getMaxScopeSign()) {
                                                    case ReturnCode.CODE0:
                                                        switch (floatRate.getMinScopeSign()) {
                                                            case ReturnCode.CODE0://()
                                                                rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_1);
                                                                break;
                                                            case ReturnCode.CODE1://[)
                                                                rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_2);
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                        break;
                                                    case ReturnCode.CODE1:
                                                        switch (floatRate.getMinScopeSign()) {
                                                            case ReturnCode.CODE0://(]
                                                                rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_3);
                                                                break;
                                                            case ReturnCode.CODE1://[]
                                                                rateInfoBean.setSignCode(ReturnCode.STATUS_CODE_4);
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                        break;
                                                    default:
                                                        break;
                                                }
                                                rateInfoList.add(rateInfoBean);
                                            }
                                            limitInfoBean.setRateList(rateInfoList);
                                        }
                                    }
                                }
                            }
                            liabilityInfoBean.setLimitList(limitList);
                            liabilityList.add(liabilityInfoBean);
                        }
                        programInfoBean.setLiabilityList(liabilityList);
                        programInfoBean.setCode(insuranceProgram.getProgramCode());
                        programInfoBean.setFixFloatFlag(insuranceProgram.getBasicPremiumType());
                        programInfoBean.setPremium(insuranceProgram.getBasicPremium());
                        programInfoList.add(programInfoBean);
                        break;
                    default:
                        break;
                }
            }
            return programInfoList;
        }
    }




    @Override
    public List<FactorVO> findFactors(String productCode) {
        List<FactorVO> factorVOS = new ArrayList<>();
        FactorVO factorVO;
        List<Factor> factors = insuranceFactorRelaMapper.findFactorInfo(productCode);
        List<ConfCoefficientVO> confCoefficientVOS = new ArrayList<>();
        ConfCoefficientVO confCoefficientVO;
        if (factors != null && factors.size() > 0) {
            for (Factor factor : factors) {
                factorVO = new FactorVO();
                factorVO.setFactorCode(factor.getFactorCode());
                factorVO.setFactorName(factor.getFactorName());
                Example confCoefficientExample = new Example(ConfCoefficient.class);
                confCoefficientExample.createCriteria().andEqualTo("factorRelaId", factor.getFactorRelaId());
                List<ConfCoefficient> confCoefficients = confCoefficientMapper.selectByExample(confCoefficientExample);
                if (confCoefficients != null && confCoefficients.size() > 0) {
                    for (ConfCoefficient confCoefficient : confCoefficients) {
                        confCoefficientVO = new ConfCoefficientVO();
                        if (confCoefficient.getOperatorType().equals("1")) {
                            confCoefficientVO.setConfCode(confCoefficient.getFactorMax().toString());
                            confCoefficientVO.setConfName(confCoefficient.getOperatorDesc());
                            confCoefficientVOS.add(confCoefficientVO);
                        }
                    }
                }
                factorVO.setConfCoefficientVOS(confCoefficientVOS);
                factorVOS.add(factorVO);
            }
            return factorVOS;
        }

        return null;
    }


    @Override
    @Transactional(readOnly = true)
    public List<String> findProgramByRule(String rules) {
        List<ProductRuleRel> list = productRuleRelMapper.selectByRuleValue(rules);
        if (list == null || list.size() < 1) {
            return null;
        }
        List<String> progList = new ArrayList<>();
        for (ProductRuleRel rel : list) {
            progList.add(rel.getRuleValuesParam());
        }
        return progList;
    }

    @Override
    public List<ProductSimpleInfo> getProductList() {
        return productInfoMapper.selectSimpleInfoList();
    }
	@Override
	public ResponseEntity<?> findMainProduct() {
		List<String> productCodeList = productInfoMapper.selectProductByType("01");
		//查询所有的承保公司
		List<CompanyDTO> companyList = insuranceCompanyRelaMapper.selectByProductCode(productCodeList);
		if(companyList==null||companyList.size()<1) {
			return GenericResponse.ng("没有满足条件的数据");
		}
		for (CompanyDTO companyDTO : companyList) {
			List<RiskDTO> list = insuranceCompanyRelaMapper.selectRiskByCompanyCode(companyDTO.getCode());
			List<RiskDTO> riskDTOList = new ArrayList<>();
			for (RiskDTO riskDTO : list) {
				if(riskDTO==null) {
					continue;
				}
				if(!StringUtils.isEmpty(riskDTO.getCode())){
					riskDTOList.add(riskDTO);
				}
			}
			companyDTO.setList(riskDTOList);
		}
		return GenericListResponse.listAndCount(companyList,companyList.size());
	}
	@Override
	public ResponseEntity<?> findAddProduct(List<String> productCodeList) {
		List<AddRiskDTO> list = new ArrayList<>();
		for (String productCode : productCodeList) {
			AddRiskDTO addRiskDTO = new AddRiskDTO();
			List<AddRisk> addRiskList = productRiskRelMapper.selectAddRiskByProductCode(productCode);
			addRiskDTO.setList(addRiskList);
			addRiskDTO.setProductCode(productCode);
			list.add(addRiskDTO);
		}
		if(list.size()<1) {
			return GenericResponse.ng("没有满足条件的数据");
		}
		return GenericListResponse.listAndCount(list,list.size());
	}
	@Override
	public ResponseEntity<?> findProductByChannelCode(String channelCode){
		List<ProductSimpleInfoDTO> list = productInfoMapper.selectByChannelCode(channelCode);
		return GenericListResponse.listAndCount(list,list.size());
	}
	@Override
	public ResponseEntity<?> findLiabilityByCode(List<RiskParam> list){
		List<RiskTempVO> riskTempVOList = new ArrayList<>();
		for (RiskParam riskParam : list) {
			TempProductInfo temp = tempProductInfoMapper.selectByProductCode(riskParam.getProductCode());
			if(temp==null) {
				break;
			}
			String productInfo = temp.getProductInfo();
			TempProductInfoVO tempInfo = JsonUtil.json2Object(productInfo, TempProductInfoVO.class);
			List<RiskTempVO> riskList = tempInfo.getRiskList();
			for (RiskTempVO riskTempVO : riskList) {
				if(StringUtils.equals(riskParam.getRiskCode(), riskTempVO.getCode())) {
					riskTempVO.setProductCode(temp.getProductCode());
					List<InsuranLiabilityVO> liabilityList = riskTempVO.getLiabilityList();
					for (InsuranLiabilityVO insuranLiabilityVO : liabilityList) {
						InsuranceLiability insuranceLiability = insuranceLiabilityMapper.selectByInsuranceLiabilityCode(insuranLiabilityVO.getInsuranceLiabilityCode());
						insuranLiabilityVO.setLabelId(insuranceLiability.getLabelId()==null?1:insuranceLiability.getLabelId());
						insuranLiabilityVO.setLiabilityLabel(StringUtils.isEmpty(insuranceLiability.getLiabilityLabel())?"":insuranceLiability.getLiabilityLabel());
						insuranLiabilityVO.setInsuranceLiabilityDesc(insuranceLiability.getInsuranceLiabilityDesc());
					}
					riskTempVOList.add(riskTempVO);
				}
			}
		}
		if(riskTempVOList.size()<1) {
			return GenericResponse.ng("没有满足条件的数据");
		}
		return GenericListResponse.listAndCount(riskTempVOList,riskTempVOList.size());
	}
	@Override
	public ResponseEntity<?> findAllCompany(){
		List<CompanyDTO> list = companyInfoMapper.selectAllCompany();
		return GenericListResponse.listAndCount(list,list.size());
	}
	@Override
	public ResponseEntity<?> findProductListByCond(Query query) {
		MyPageInfo<LifeProductDTO> list = null;
		PageInfo<LifeProductDTO> pageInfo = null;
		Date date = new Date();
		try {
			if(query!=null) {
				pageInfo = PageHelper.startPage(query.getPn() ,query.getPs()).doSelectPageInfo(() -> productInfoMapper.findProductListByCond(query));
				if(pageInfo!=null&&pageInfo.getSize()>0) {
					list = PageInfoUtil.copyInfo(pageInfo);
					list.getList().forEach(e->{
						e.setListDate(date);
						e.setCompanyLogo("20180809/5c3bd14b-0510-40db-9930-d8753db89777.jpg");
						e.setProductLogo("20180809/4cd35591-b5a8-4494-8003-00581a71d81d.png");
					});
				}else {
					return GenericResponse.ng("没有满足条件的数据");
				}
			}else {
				pageInfo = PageHelper.startPage(1 ,10).doSelectPageInfo(() -> productInfoMapper.findProductList());
				pageInfo.getList().forEach(e->{
					e.setListDate(date);
					e.setCompanyLogo("20180809/5c3bd14b-0510-40db-9930-d8753db89777.jpg");
					e.setProductLogo("20180809/4cd35591-b5a8-4494-8003-00581a71d81d.png");
				});
				list = PageInfoUtil.copyInfo(pageInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return GenericResponse.ng("服务器错误");
		}
		return GenericDataResponse.okWithData(list);
	}
	
}
