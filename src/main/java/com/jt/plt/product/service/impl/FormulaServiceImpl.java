package com.jt.plt.product.service.impl;


import com.jt.plt.product.entity.*;
import com.jt.plt.product.entity.calPremium.*;
import com.jt.plt.product.enums.PremiumFormulaEnum;
import com.jt.plt.product.enums.ResultEnum;
import com.jt.plt.product.mapper.*;
import com.jt.plt.product.service.FormulaService;
import com.jt.plt.product.service.RedisCallBackInterface;
import com.jt.plt.product.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.jt.plt.product.util.FormulaCode.*;


/**
 * @Description: 保费计算公式实现类
 * @author: wephone
 * @date: 2018/3/9 15:57
 */

@Slf4j
@Service
public class FormulaServiceImpl implements FormulaService {

    public static String keyProdu = null;

    public static String keyProgram = null;

    public static String keyInfare = null;

    public static String keyPrefor = null;

    public static String keyFloatRate = null;

    public static String keyFloatRateAtion = null;

    public static String keyConf = null;

    public static String keyProgramAtion = null;

    public static String keyPrcare = null;

    public static String keyFactorCode = null;

    public static String keyFactorRelaId = null;


    public static String keyLimitValues = null;

    public static String keyLimitValuesAtion = null;

    // public Premium premium ;
    public Set<RiskPremium> riskPremiums;


    @Autowired
    ConfCoefficientMapper confCoefficientMapper;

    @Autowired
    InsuranceFactorRelaMapper insuranceFactorRelaMapper;

    @Autowired
    BasicPremiumFactorMapper basicPremiumFactorMapper;

    @Autowired
    LiabilityLimitMapper liabilityLimitMapper;

    @Autowired
    LiabilityLimitValuesMapper liabilityLimitValuesMapper;

    @Autowired
    LiabilityLimitRelaMapper liabilityLimitRelaMapper;

    @Autowired
    ProductInfoMapper productInfoMapper;

    @Autowired
    PremiumFormulaMapper premiumFormulaMapper;

    @Autowired
    PremiumCalculateRuleMapper premiumCalculateRuleMapper;

    @Autowired
    FloatRateMapper floatRateMapper;

    @Autowired
    FactorMapper factorMapper;

    @Autowired
    RiskInfoMapper riskInfoMapper;

    @Autowired
    ProductRiskRelMapper productRiskRelMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ProductRuleRelMapper productRuleRelMapper;

    @Autowired
    private InsuranceProgramMapper insuranceProgramMapper;
    @Autowired
    InsuranceLiabilityMapper insuranceLiabilityMapper;

    @Autowired
    InsuranceRateConfMapper insuranceRateConfMapper;


    /**
     * @param formulaBeans 保费计算视图集合
     * @return ResultMsg
     */
    @Override
    public ResponseEntity<?> countPremiums(List<FormulaBean> formulaBeans) {
        long startTime = System.currentTimeMillis();
        Premium premium = new Premium();
        List<OnePremium> singlePremiums = new ArrayList<>();
        riskPremiums = new CopyOnWriteArraySet<>();
        BigDecimal allPremiums = BigDecimal.ZERO;
        OnePremium onePremium;
        for (FormulaBean formulaBean : formulaBeans) {
            onePremium = new OnePremium();

            //字段校验
            ResponseEntity<?> responseEntity = verifyFormulaBean(formulaBean);
            GenericResponse body = (GenericResponse) responseEntity.getBody();
            String rc = body.getCode();
            if (!FormulaCode.STATUS_0000.equals(rc)) {
                return responseEntity;
            }
            GenericDataResponse g = (GenericDataResponse) body;
            String premiumDesign = (String) g.getData();

            // 基本险基础保费
            Map<String, BigDecimal> map = new HashMap<>(16);
            List<ProductRiskRel> productRiskRels = productRiskRelMapper.selectByProductCode(formulaBean.getProductCode());
            for (ProductRiskRel productRiskRel : productRiskRels) {
                RiskInfo riskInfo = riskInfoMapper.selectByRiskCode(productRiskRel.getRiskCode());
                if ("A".equals(riskInfo.getRiskFlag())) {
                    String riskCode = riskInfo.getRiskCode();
                    String str = riskCode.substring(2, riskCode.length());
                    map.put(str, BigDecimal.ZERO);
                }
            }

            ResponseEntity<?> responseEntity2 = basicPremium(formulaBean, map);
            GenericResponse body2 = (GenericResponse) responseEntity2.getBody();
            String rc2 = body2.getCode();
            if (!FormulaCode.STATUS_0000.equals(rc2)) {
                return responseEntity2;
            }
            GenericDataResponse g2 = (com.jt.plt.product.util.GenericDataResponse) body2;
            map = (Map<String, BigDecimal>) g2.getData();


            // 保费计算因子
            ResponseEntity<?> responseEntity3 = factorInfo(formulaBean.getProductCode(), formulaBean.getFactorInfos(), map);
            GenericResponse body3 = (GenericResponse) responseEntity3.getBody();
            String rc3 = body3.getCode();
            if (!FormulaCode.STATUS_0000.equals(rc3)) {
                return responseEntity3;
            }
            GenericDataResponse g3 = (GenericDataResponse) body3;
            map = (Map<String, BigDecimal>) g3.getData();

            //赔付率因子/优惠调整因子
            ResponseEntity<?> responseEntity4 = lossRationFactor(formulaBean, map);
            GenericResponse body4 = (GenericResponse) responseEntity4.getBody();
            String rc4 = body4.getCode();
            if (!FormulaCode.STATUS_0000.equals(rc4)) {
                return responseEntity4;
            }
            GenericDataResponse g4 = (GenericDataResponse) body4;
            map = (Map<String, BigDecimal>) g4.getData();

            //附加险保费
            List<AdditionInsurance> additionInsurances = formulaBean.getAdditionInsurances();
            if (additionInsurances != null && additionInsurances.size() > 0) {
                for (AdditionInsurance additionInsurance : additionInsurances) {
                    ResponseEntity<?> responseEntity5 = additionPremium(additionInsurance, map);
                    GenericResponse body5 = (GenericResponse) responseEntity5.getBody();
                    String rc5 = body5.getCode();
                    if (!FormulaCode.STATUS_0000.equals(rc5)) {
                        return responseEntity5;
                    }
                    GenericDataResponse g5 = (GenericDataResponse) body5;
                    map = (Map<String, BigDecimal>) g5.getData();
                }
            }

            // 保费计算规则
            ResponseEntity<?> responseEntity6 = premiumRule(formulaBean, map, premiumDesign);
            GenericResponse body6 = (GenericResponse) responseEntity6.getBody();
            String rc6 = body6.getCode();
            if (!FormulaCode.STATUS_0000.equals(rc6)) {
                return responseEntity6;
            }
            GenericDataResponse g6 = (GenericDataResponse) body6;
            premiumDesign = (String) g6.getData();


            // 对保费计算公式格式进行校验
            FormulaVerify formulaVerify = new FormulaVerify(premiumDesign);
            if (formulaVerify.checkValid()) {

                BigDecimal singlePremium;
                try {
                    //每单保费
                    singlePremium = MathUtil.getBigDecimal(FormulaUtil.convertToCode(premiumDesign, map));
                } catch (Exception e) {
                    log.error(PremiumFormulaEnum.IS_HAS_UNDEFINED_VARIABLE.getMessage());
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_HAS_UNDEFINED_VARIABLE.getMessage());
                }
                onePremium.setPolicyNum(formulaBean.getPolicyNum());
                onePremium.setSinglePremium(singlePremium.setScale(4, BigDecimal.ROUND_HALF_UP));
                ProductInfo productInfo = productInfoMapper.selectByProductCode(formulaBean.getProductCode());
                PremiumFormula premiumFormula = premiumFormulaMapper.selectByPrimaryKey(productInfo.getProductDesginId());
                onePremium.setPremiumDesignDesc(premiumFormula.getPremiumDesignDesc());
                onePremium.setRiskPremiums(riskPremiums);
                singlePremiums.add(onePremium);
                // 总保费
                allPremiums = allPremiums.add(singlePremium);
            } else {
                return GenericListResponse.ng(FormulaVerify.Msg);
            }
        }
        log.info("******总保费******：" + (allPremiums.setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
        long endTime = System.currentTimeMillis();
        log.info(">>>>>>>>>>>>>>>当前程序耗时：" + (endTime - startTime) + "ms");
        premium.setTotalPremium(allPremiums.setScale(2, BigDecimal.ROUND_HALF_UP));
        premium.setSinglePremiums(singlePremiums);
        return GenericDataResponse.okWithData(premium);
    }

    @Override
    public ResponseEntity<?> newcountPremiums(List<CountPremiumBean> countPremiumBeans) {
        long startTime = System.currentTimeMillis();
        Premium premium = new Premium();
        List<OnePremium> singlePremiums = new ArrayList<>();
        riskPremiums = new CopyOnWriteArraySet<>();
        BigDecimal allPremiums = BigDecimal.ZERO;
        OnePremium onePremium;

        for (CountPremiumBean countPremiumBean : countPremiumBeans) {
            onePremium = new OnePremium();
            //字段校验
            ResponseEntity<?> responseEntity = veryCountPremiumBean(countPremiumBean);
            GenericResponse body = (GenericResponse) responseEntity.getBody();
            String rc = body.getCode();
            if (!FormulaCode.STATUS_0000.equals(rc)) {
                return responseEntity;
            }
            GenericDataResponse g = (GenericDataResponse) body;
            String premiumDesign = (String) g.getData();

            // 方案/险种保费计算
            Map<String, BigDecimal> map = new HashMap<>(16);
            //初始保费计算参数
            List<ProductRiskRel> productRiskRels = productRiskRelMapper.selectByProductCode(countPremiumBean.getProductCode());
            for (ProductRiskRel productRiskRel : productRiskRels) {
                RiskInfo riskInfo = riskInfoMapper.selectByRiskCode(productRiskRel.getRiskCode());
                String riskCode = riskInfo.getRiskCode();
                String str = riskCode.substring(2, riskCode.length());
                map.put(str, BigDecimal.ZERO);
            }
            List<InsuranceVO> insuranceVOS = countPremiumBean.getInsuranceVOS();
            for (InsuranceVO insuranceVO : insuranceVOS) {
                ResponseEntity<?> responseEntity2 = calProPremium(insuranceVO, map);
                GenericResponse body2 = (GenericResponse) responseEntity2.getBody();
                String rc2 = body2.getCode();
                if (!FormulaCode.STATUS_0000.equals(rc2)) {
                    return responseEntity2;
                }
                GenericDataResponse g2 = (com.jt.plt.product.util.GenericDataResponse) body2;
                map = (Map<String, BigDecimal>) g2.getData();
            }


            // 保费计算因子
            ResponseEntity<?> responseEntity3 = factorInfo(countPremiumBean.getProductCode(), countPremiumBean.getFactorInfos(), map);
            GenericResponse body3 = (GenericResponse) responseEntity3.getBody();
            String rc3 = body3.getCode();
            if (!FormulaCode.STATUS_0000.equals(rc3)) {
                return responseEntity3;
            }
            GenericDataResponse g3 = (GenericDataResponse) body3;
            map = (Map<String, BigDecimal>) g3.getData();

            //赔付率因子/优惠调整因子
            ResponseEntity<?> responseEntity4 = lossRationFactor(countPremiumBean, map);
            GenericResponse body4 = (GenericResponse) responseEntity4.getBody();
            String rc4 = body4.getCode();
            if (!FormulaCode.STATUS_0000.equals(rc4)) {
                return responseEntity4;
            }
            GenericDataResponse g4 = (GenericDataResponse) body4;
            map = (Map<String, BigDecimal>) g4.getData();

            // 保费计算规则
            ResponseEntity<?> responseEntity6 = premiumRule(countPremiumBean, map, premiumDesign);
            GenericResponse body6 = (GenericResponse) responseEntity6.getBody();
            String rc6 = body6.getCode();
            if (!FormulaCode.STATUS_0000.equals(rc6)) {
                return responseEntity6;
            }
            GenericDataResponse g6 = (GenericDataResponse) body6;
            premiumDesign = (String) g6.getData();

            // 对保费计算公式格式进行校验
            FormulaVerify formulaVerify = new FormulaVerify(premiumDesign);
            if (formulaVerify.checkValid()) {

                BigDecimal singlePremium;
                try {
                    //每单保费
                    singlePremium = MathUtil.getBigDecimal(FormulaUtil.convertToCode(premiumDesign, map));
                } catch (Exception e) {
                    log.error(PremiumFormulaEnum.IS_HAS_UNDEFINED_VARIABLE.getMessage());
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_HAS_UNDEFINED_VARIABLE.getMessage());
                }
                //保单序号
                onePremium.setPolicyNum(countPremiumBean.getPolicyNum());
                onePremium.setSinglePremium(singlePremium.setScale(4, BigDecimal.ROUND_HALF_UP));
                ProductInfo productInfo = productInfoMapper.selectByProductCode(countPremiumBean.getProductCode());
                PremiumFormula premiumFormula = premiumFormulaMapper.selectByPrimaryKey(productInfo.getProductDesginId());
                onePremium.setPremiumDesignDesc(premiumFormula.getPremiumDesignDesc());
                onePremium.setRiskPremiums(riskPremiums);
                onePremium.setProductCode(countPremiumBean.getProductCode());
                singlePremiums.add(onePremium);
                // 总保费
                allPremiums = allPremiums.add(singlePremium);
            } else {
                return GenericListResponse.ng(FormulaVerify.Msg);
            }

        }

        log.info("******总保费******：" + (allPremiums.setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
        long endTime = System.currentTimeMillis();
        log.info(">>>>>>>>>>>>>>>当前程序耗时：" + (endTime - startTime) + "ms");
        premium.setTotalPremium(allPremiums.setScale(2, BigDecimal.ROUND_HALF_UP));
        premium.setSinglePremiums(singlePremiums);
        return GenericDataResponse.okWithData(premium);

    }

    /**
     * 计算寿险保费
     *
     * @param formulaDTOS
     * @return
     */
    @Override
    public ResponseEntity<?> countLifePremiums(List<FormulaDTO> formulaDTOS) {

        Premium premium = new Premium();
        List<OnePremium> singlePremiums = new ArrayList<>();
        riskPremiums = new CopyOnWriteArraySet<>();
        BigDecimal allPremiums = BigDecimal.ZERO;
        for (FormulaDTO formulaDTO : formulaDTOS) {
            //校验字段
            ResponseEntity<?> responseEntity = verifyFormulaDTO(formulaDTO);
            GenericResponse body = (GenericResponse) responseEntity.getBody();
            String rc = body.getCode();
            if (!FormulaCode.STATUS_0000.equals(rc)) {
                return responseEntity;
            }
            Map<String, BigDecimal> map = new HashMap<>(16);
            //初始保费计算参数
            List<ProductRiskRel> productRiskRels = productRiskRelMapper.selectByProductCode(formulaDTO.getProductCode());
            for (ProductRiskRel productRiskRel : productRiskRels) {
                RiskInfo riskInfo = riskInfoMapper.selectByRiskCode(productRiskRel.getRiskCode());
                String riskCode = riskInfo.getRiskCode();
                String str = riskCode.substring(2, riskCode.length());
                map.put(str, BigDecimal.ZERO);
            }

            // 保费计算因子
            if (formulaDTO.getFactorInfos() != null && formulaDTO.getFactorInfos().size() > 0) {
                ResponseEntity<?> responseEntity3 = factorInfo(formulaDTO.getProductCode(), formulaDTO.getFactorInfos(), map);
                GenericResponse body3 = (GenericResponse) responseEntity3.getBody();
                String rc3 = body3.getCode();
                if (!FormulaCode.STATUS_0000.equals(rc3)) {
                    return responseEntity3;
                }
                GenericDataResponse g3 = (GenericDataResponse) body3;
                map = (Map<String, BigDecimal>) g3.getData();
            }


            //计算每一个险种的保费
            List<LifeInsuranceVO> lifeInsuranceVOS = formulaDTO.getLifeInsuranceVOS();
            RiskPremium riskPremium;
            OnePremium onePremium = new OnePremium();
            for (LifeInsuranceVO lifeInsuranceVO : lifeInsuranceVOS) {
                riskPremium = new RiskPremium();
                //获取当前险种编码截取
                riskPremium.setRiskCode(lifeInsuranceVO.getRiskCode());
                String riskCode = lifeInsuranceVO.getRiskCode();
                String str = riskCode.substring(2, riskCode.length());

                //1.通过销售属性算保费
                if (!StringUtils.isEmpty(lifeInsuranceVO.getSalesRules())) {
                    BigDecimal currentRiskPremium = BigDecimal.ZERO;

                    //查询基础保额
                    Example example = new Example(InsuranceRateConf.class);
                    example.createCriteria().andEqualTo("productCode", formulaDTO.getProductCode())
                            .andEqualTo("riskCode", lifeInsuranceVO.getRiskCode());
                    List<InsuranceRateConf> insuranceRateConfs = insuranceRateConfMapper.selectByExample(example);
                    BigDecimal basicInsuranceAmount = BigDecimal.valueOf(insuranceRateConfs.get(0).getBasicInsuranceAmount());

                    //TODO  判空
                    String salesRules = lifeInsuranceVO.getSalesRules();
                    ProductRuleRel productRuleRel = productRuleRelMapper.selectByTypeCodeAndRuleValue(lifeInsuranceVO.getRiskCode(), salesRules);
                    if (productRuleRel == null) {
                        return GenericListResponse.ng(ResultEnum.NO_DATA.getMessage());
                    }
                    String strParam = productRuleRel.getRuleValuesParam();
                    if ("FA".equals(strParam.substring(2, 4))) {
                        InsuranceProgram insuranceProgram = insuranceProgramMapper.selectByProgramCode(strParam);
                        if (insuranceProgram == null) {
                            return GenericListResponse.ng("没有对应的险种费率");
                        }
                        //当前险种保费
                        if (basicInsuranceAmount != null) {
                            currentRiskPremium = BigDecimal.valueOf(insuranceProgram.getBasicPremium()).multiply(
                                    (lifeInsuranceVO.getUserPurchaseAmount().divide(basicInsuranceAmount)).multiply(lifeInsuranceVO.getRiskCopies()));
                        } else {
                            currentRiskPremium = BigDecimal.valueOf(insuranceProgram.getBasicPremium()).multiply(lifeInsuranceVO.getRiskCopies());
                        }

                    } else if ("FL".equals(strParam.substring(2, 4))) {
                        InsuranceRateConf insuranceRateConf = insuranceRateConfMapper.selectByRateCode(strParam);
                        if (insuranceRateConf == null) {
                            return GenericListResponse.ng("没有对应的险种费率");
                        }
                        if (basicInsuranceAmount != null) {
                            currentRiskPremium = BigDecimal.valueOf(insuranceRateConf.getRate()).multiply(
                                    (lifeInsuranceVO.getUserPurchaseAmount().divide(basicInsuranceAmount)).multiply(lifeInsuranceVO.getRiskCopies()));
                        } else {
                            currentRiskPremium = BigDecimal.valueOf(insuranceRateConf.getRate()).multiply(lifeInsuranceVO.getRiskCopies());
                        }
                    }

                    map.put(str, currentRiskPremium);
                    //记录险种保费 乘以 因子
                    if (formulaDTO.getFactorInfos() != null && formulaDTO.getFactorInfos().size() == 1) {
                        FactorInfo factorInfo = formulaDTO.getFactorInfos().get(0);
                        Factor factor = factorMapper.selectByFactorCode(factorInfo.getFactorCode());
                        riskPremium.setRiskPremiuml(currentRiskPremium.multiply(map.get(factor.getFactorCodeName())));
                    }else{
                        riskPremium.setRiskPremiuml(currentRiskPremium);
                    }


                    //2.固定保额保费
                } else if (!StringUtils.isEmpty(lifeInsuranceVO.getProgramCode())) {
                    InsuranceProgram insuranceProgram = insuranceProgramMapper.selectByProgramCode(lifeInsuranceVO.getProgramCode());
                    BigDecimal decimal = BigDecimal.valueOf(insuranceProgram.getBasicPremium());
                    map.put(str, decimal);
                    //记录险种保费
                    if (formulaDTO.getFactorInfos() != null && formulaDTO.getFactorInfos().size() == 1) {
                        FactorInfo factorInfo = formulaDTO.getFactorInfos().get(0);
                        Factor factor = factorMapper.selectByFactorCode(factorInfo.getFactorCode());
                        riskPremium.setRiskPremiuml(decimal.multiply(map.get(factor.getFactorCodeName())));
                    }else{
                        riskPremium.setRiskPremiuml(decimal);
                    }
                }
                riskPremiums.add(riskPremium);
            }


            //获取计算公式
            ProductInfo productInfo = productInfoMapper.selectByProductCode(formulaDTO.getProductCode());
            //保费计算公式
            String premiumDesign;
            keyPrefor = new StringBuilder().append(PRODUCT).append("preFor_").append(productInfo.getProductDesginId()).toString();
            RedisCallBackInterface<PremiumFormula, String> callback4 = (String key) -> {
                CallRsult callRsult = new CallRsult<String>();
                PremiumFormula premiumFormula = premiumFormulaMapper.selectByPrimaryKey(Integer.parseInt(key));
                if (premiumFormula != null) {
                    log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                    callRsult.setResultAndAllowCached(premiumFormula);
                }
                return callRsult;
            };
            PremiumFormula premiumFormula = MyRedisUtils.excute(productInfo.getProductDesginId().toString(), keyPrefor, callback4);
            if (premiumFormula != null) {
                premiumDesign = premiumFormula.getPremiumDesign().replace("[", "(").replace("]", "");
            } else {
                return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_PRODUCT_FORMULA.getMessage());
            }

            // 对保费计算公式格式进行校验
            FormulaVerify formulaVerify = new FormulaVerify(premiumDesign);
            if (formulaVerify.checkValid()) {

                BigDecimal singlePremium;
                try {
                    //每单保费
                    singlePremium = MathUtil.getBigDecimal(FormulaUtil.convertToCode(premiumDesign, map));
                } catch (Exception e) {
                    log.error(PremiumFormulaEnum.IS_HAS_UNDEFINED_VARIABLE.getMessage());
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_HAS_UNDEFINED_VARIABLE.getMessage());
                }
                //保单·序号
                onePremium.setPolicyNum(formulaDTO.getPolicyNum());
                onePremium.setSinglePremium(singlePremium.setScale(4, BigDecimal.ROUND_HALF_UP));
                onePremium.setPremiumDesignDesc(premiumFormula.getPremiumDesignDesc());
                onePremium.setRiskPremiums(riskPremiums);
                onePremium.setProductCode(formulaDTO.getProductCode());
                singlePremiums.add(onePremium);
                // 总保费
                allPremiums = allPremiums.add(singlePremium);
            } else {
                return GenericListResponse.ng(FormulaVerify.Msg);
            }

        }
        log.info("******总保费***** =======》》》》》" + (allPremiums.setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
        premium.setTotalPremium(allPremiums.setScale(2, BigDecimal.ROUND_HALF_UP));
        premium.setSinglePremiums(singlePremiums);

        return GenericDataResponse.okWithData(premium);
    }

    private static Map<String, BigDecimal> otherMethod(Map<String, BigDecimal> map, ConfCoefficient confCoefficient, String factorCodeName, double parseDouble) {
        Map<String, BigDecimal> map2 = new HashMap<>(16);
        map2.put(A, new BigDecimal(parseDouble));
        String replace = confCoefficient.getFactorValues().replace("[", "(").replace("]", ")");
        BigDecimal factorValues = MathUtil.getBigDecimal(FormulaUtil.convertToCode(replace, map2));
        map.put(factorCodeName, factorValues.setScale(4, BigDecimal.ROUND_HALF_UP));
        return map;
    }

    private Map<String, BigDecimal> otherMethod2(Map<String, BigDecimal> map, BigDecimal lossRation, String factorCodeName, FormulaBean formulaBean) {

        Map<String, String> map3 = new HashMap<>();
        map3.put("productCode", formulaBean.getProductCode());
        map3.put("factorCode", LOSS_FACTOR_CODE);
        InsuranceFactorRela insuranceFactorRela = insuranceFactorRelaMapper.selectByMap(map3);
        Example example = new Example(ConfCoefficient.class);
        example.createCriteria().andEqualTo("factorRelaId", insuranceFactorRela.getFactorRelaId());
        List<ConfCoefficient> confCoefficients = confCoefficientMapper.selectByExample(example);
        double doubleValue = lossRation.doubleValue();
        for (ConfCoefficient confCoefficient : confCoefficients) {
            switch (confCoefficient.getOperatorType()) {
                case "3":
                    if (confCoefficient.getFactorMin() < doubleValue && doubleValue < confCoefficient.getFactorMax()) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            Map<String, BigDecimal> map2 = new HashMap<>(16);
                            map2.put(R, lossRation);
                            String replace = confCoefficient.getFactorValues().replace("[", "(").replace("]", ")");
                            BigDecimal factorValues = BigDecimal.valueOf((Double) FormulaUtil.convertToCode(replace, map2));
                            map.put(factorCodeName, factorValues);
                        }

                    }
                    break;
                case "4":
                    // ( ]
                    if (confCoefficient.getFactorMin() < doubleValue && doubleValue <= confCoefficient.getFactorMax()) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            Map<String, BigDecimal> map2 = new HashMap<>(16);
                            map2.put(R, lossRation);
                            String replace = confCoefficient.getFactorValues().replace("[", "(").replace("]", ")");
                            BigDecimal factorValues = BigDecimal.valueOf((Double) FormulaUtil.convertToCode(replace, map2));
                            map.put(factorCodeName, factorValues);
                        }
                    }
                    break;
                case "5":
                    // [ )
                    if (confCoefficient.getFactorMin() <= doubleValue && doubleValue < confCoefficient.getFactorMax()) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            Map<String, BigDecimal> map2 = new HashMap<>(16);
                            map2.put(R, lossRation);
                            String replace = confCoefficient.getFactorValues().replace("[", "(").replace("]", ")");
                            BigDecimal factorValues = BigDecimal.valueOf((Double) FormulaUtil.convertToCode(replace, map2));
                            map.put(factorCodeName, factorValues);
                        }
                    }
                    break;
                case "6":
                    // [ ]
                    if (confCoefficient.getFactorMin() <= doubleValue && doubleValue <= confCoefficient.getFactorMax()) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            Map<String, BigDecimal> map2 = new HashMap<>(16);
                            map2.put(R, lossRation);
                            String replace = confCoefficient.getFactorValues().replace("[", "(").replace("]", ")");
                            BigDecimal factorValues = BigDecimal.valueOf((Double) FormulaUtil.convertToCode(replace, map2));
                            map.put(factorCodeName, factorValues);
                        }
                    }
                    break;
                case "7":
                    // (-∞, a)
                    if (doubleValue < confCoefficient.getFactorMax()) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            Map<String, BigDecimal> map2 = new HashMap<>(16);
                            map2.put(R, lossRation);
                            String replace = confCoefficient.getFactorValues().replace("[", "(").replace("]", ")");
                            BigDecimal factorValues = BigDecimal.valueOf((Double) FormulaUtil.convertToCode(replace, map2));
                            map.put(factorCodeName, factorValues);
                        }
                    }
                    break;
                case "8":
                    // (-∞, a]
                    if (doubleValue <= confCoefficient.getFactorMax()) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            Map<String, BigDecimal> map2 = new HashMap<>(16);
                            map2.put(R, lossRation);
                            String replace = confCoefficient.getFactorValues().replace("[", "(").replace("]", ")");
                            BigDecimal factorValues = BigDecimal.valueOf((Double) FormulaUtil.convertToCode(replace, map2));
                            map.put(factorCodeName, factorValues);
                        }
                    }
                    break;
                case "9":
                    // (a, +∞)
                    if (confCoefficient.getFactorMin() < doubleValue) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            Map<String, BigDecimal> map2 = new HashMap<>(16);
                            map2.put(R, lossRation);
                            String replace = confCoefficient.getFactorValues().replace("[", "(").replace("]", ")");
                            BigDecimal factorValues = BigDecimal.valueOf((Double) FormulaUtil.convertToCode(replace, map2));
                            map.put(factorCodeName, factorValues);
                        }
                    }
                    break;
                case "10":
                    // [a, +∞)
                    if (confCoefficient.getFactorMin() <= doubleValue) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            Map<String, BigDecimal> map2 = new HashMap<>(16);
                            map2.put(R, lossRation);
                            String replace = confCoefficient.getFactorValues().replace("[", "(").replace("]", ")");
                            BigDecimal factorValues = BigDecimal.valueOf((Double) FormulaUtil.convertToCode(replace, map2));
                            map.put(factorCodeName, factorValues);
                        }
                    }
                    break;
                default:
                    break;
            }

        }
        return map;
    }


    private static Map<String, BigDecimal> switchMethod(Map<String, BigDecimal> map, List<ConfCoefficient> confCoefficients, double parseDouble, String factorCodeName, FactorInfo factorInfo) {
        for (ConfCoefficient confCoefficient : confCoefficients) {
            switch (confCoefficient.getOperatorType()) {
                case "1":
                    // =a    eg:1001 代表 未发生生产安全事故
                    if (parseDouble == confCoefficient.getFactorMax()) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            map = otherMethod(map, confCoefficient, factorCodeName, parseDouble);
                        }
                    }
                    break;
                case "2":
                    // 方案直接定义值
                    if (confCoefficient.getProgramCode().equals(factorInfo.getStringFactor())) {
                        map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                    }
                    break;
                case "3":
                    // ( )  {25万人次 < A < 50万人次  [(A - 25万)/ 25万] * 20%} || 0.2
                    if (confCoefficient.getFactorMin() < parseDouble && parseDouble < confCoefficient.getFactorMax()) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            map = otherMethod(map, confCoefficient, factorCodeName, parseDouble);
                        }
                    }
                    break;
                case "4":
                    // ( ]
                    if (confCoefficient.getFactorMin() < parseDouble && parseDouble <= confCoefficient.getFactorMax()) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            map = otherMethod(map, confCoefficient, factorCodeName, parseDouble);
                        }
                    }
                    break;
                case "5":
                    // [ )
                    if (confCoefficient.getFactorMin() <= parseDouble && parseDouble < confCoefficient.getFactorMax()) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            map = otherMethod(map, confCoefficient, factorCodeName, parseDouble);
                        }
                    }
                    break;
                case "6":
                    // [ ]
                    if (confCoefficient.getFactorMin() <= parseDouble && parseDouble <= confCoefficient.getFactorMax()) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            map = otherMethod(map, confCoefficient, factorCodeName, parseDouble);
                        }
                    }
                    break;
                case "7":
                    // (-∞, a)
                    if (parseDouble < confCoefficient.getFactorMax()) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            map = otherMethod(map, confCoefficient, factorCodeName, parseDouble);
                        }
                    }
                    break;
                case "8":
                    // (-∞, a]
                    if (parseDouble <= confCoefficient.getFactorMax()) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            map = otherMethod(map, confCoefficient, factorCodeName, parseDouble);
                        }
                    }
                    break;
                case "9":
                    // (a, +∞)
                    if (confCoefficient.getFactorMin() < parseDouble) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            map = otherMethod(map, confCoefficient, factorCodeName, parseDouble);
                        }
                    }
                    break;
                case "10":
                    // [a, +∞)
                    if (confCoefficient.getFactorMin() <= parseDouble) {
                        if (FormulaUtil.pandun(confCoefficient.getFactorValues())) {
                            map.put(factorCodeName, new BigDecimal(confCoefficient.getFactorValues()));
                        } else {
                            map = otherMethod(map, confCoefficient, factorCodeName, parseDouble);
                        }
                    }
                    break;
                default:
                    map.put(factorCodeName, BigDecimal.ZERO);
                    break;
            }
        }
        return map;
    }


    /**
     * 保费计算规则
     *
     * @param formulaBean   保费计算视图
     * @param map           map集合
     * @param premiumDesign 原计算公式
     * @return ResultMsg
     */
    @SuppressWarnings("unchecked")
    private ResponseEntity<?> premiumRule(FormulaBean formulaBean, Map<String, BigDecimal> map, String premiumDesign) {
        List<PremiumCalculateRule> premiumCalculateRules;
        keyPrcare = keyProdu + "prcare_" + formulaBean.getProductCode();
        //判断key是否存在
        Boolean flg6 = redisTemplate.hasKey(keyPrcare);
        if (flg6) {
            premiumCalculateRules = (List<PremiumCalculateRule>) redisTemplate.opsForValue().get(keyPrcare);
        } else {
            // 从数据库查询
            Example example2 = new Example(PremiumCalculateRule.class);
            example2.createCriteria().andEqualTo("productCode", formulaBean.getProductCode()).andEqualTo("status", "1");
            premiumCalculateRules = premiumCalculateRuleMapper.selectByExample(example2);
            // 插入缓存
            if (premiumCalculateRules != null && premiumCalculateRules.size() > 0) {
                redisTemplate.opsForValue().set(keyPrcare, premiumCalculateRules);
            }
        }


        if (premiumCalculateRules == null || premiumCalculateRules.size() == 0) {
            log.info("******:----------保费计算不参与自定义规则");
        } else {
            String ruleFormula = premiumCalculateRules.get(0).getCalculateRulePara();
            BigDecimal value;
            try {
                value = (BigDecimal) FormulaUtil.convertToCode(ruleFormula, map);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(PremiumFormulaEnum.IS_HAS_UNDEFINED_VARIABLE.getMessage());
                return GenericListResponse.ng(PremiumFormulaEnum.IS_HAS_UNDEFINED_VARIABLE.getMessage());
            }
            assert value != null;
            for (PremiumCalculateRule premiumCalculateRule : premiumCalculateRules) {
                switch (premiumCalculateRule.getConditionFlag()) {

                    case "01":
                        // 01-大于
                        if (value.doubleValue() > Double.parseDouble(premiumCalculateRule.getCalculateRuleValues())) {
                            premiumDesign = premiumCalculateRule.getCalculateRuleFormula();
                        }
                        break;

                    case "02":
                        // 02-大于等于
                        if (value.doubleValue() >= Double.parseDouble(premiumCalculateRule.getCalculateRuleValues())) {
                            premiumDesign = premiumCalculateRule.getCalculateRuleFormula();
                        }
                        break;

                    case "03":
                        // 03-小于
                        if (value.doubleValue() < Double.parseDouble(premiumCalculateRule.getCalculateRuleValues())) {
                            premiumDesign = premiumCalculateRule.getCalculateRuleFormula();
                        }
                        break;
                    case "04":
                        // 04-小于等于
                        if (value.doubleValue() <= Double.parseDouble(premiumCalculateRule.getCalculateRuleValues())) {
                            premiumDesign = premiumCalculateRule.getCalculateRuleFormula();
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return GenericDataResponse.okWithData(premiumDesign);
    }


    private ResponseEntity<?> calProPremium(InsuranceVO insuranceVO, Map<String, BigDecimal> map) {
        RiskPremium riskPremium;
        BigDecimal proPremium = BigDecimal.ZERO;
        InsuranceProgram insuranceProgram1 = insuranceProgramMapper.selectByProgramCode(insuranceVO.getProgramCode());
        // 判断 保费计算类型 1-固定 3-浮动 4 固定+浮动
        switch (insuranceProgram1.getBasicPremiumType()) {
            case "1":
                proPremium = BigDecimal.valueOf(insuranceProgram1.getBasicPremium()).multiply(insuranceVO.getCount());
                List<RiskBean> riskBeans = insuranceVO.getRiskBeans();

                String riskCode = riskBeans.get(0).getRiskCode();
                String str = riskCode.substring(2, riskCode.length());
                //取险种编码放入参数内
                map.put(str, proPremium);
                if (riskBeans.size() == 1) {
                    riskPremium = new RiskPremium();
                    riskPremium.setRiskCode(riskCode);
                    riskPremium.setRiskPremiuml(proPremium);
                    riskPremiums.add(riskPremium);
                }
                break;
            case "3":
                //方案类型为浮动
                ResponseEntity<?> responseEntity = countFloatPremium(insuranceVO, map);
                GenericResponse body = (GenericResponse) responseEntity.getBody();
                String rc = body.getCode();
                if (!FormulaCode.STATUS_0000.equals(rc)) {
                    return responseEntity;
                }
                GenericDataResponse g = (GenericDataResponse) body;
                map = (Map<String, BigDecimal>) g.getData();
                break;
            case "4":
                //方案类型为固定加浮动
                BigDecimal basicPremium3 = BigDecimal.ZERO;
                List<LiabilityLimitRela> liabilityLimitRelas = liabilityLimitRelaMapper.selectByProgramCode(insuranceVO.getProgramCode());
                for (LiabilityLimitRela liabilityLimitRela : liabilityLimitRelas) {
                    InsuranceLiability insuranceLiability = insuranceLiabilityMapper.selectByInsuranceLiabilityCode(liabilityLimitRela.getLiabilityCode());
                    String riskCode1 = insuranceLiability.getRiskCode();
                    String substring = riskCode1.substring(2, riskCode1.length());
                    //固定部分保费 TODO ····
                    if (liabilityLimitRela.getPremium() != null) {
                        BigDecimal fixedPremium = BigDecimal.valueOf(liabilityLimitRela.getPremium());
                        map.put(substring, fixedPremium);
                        riskPremium = new RiskPremium();
                        riskPremium.setRiskCode(riskCode1);
                        riskPremium.setRiskPremiuml(fixedPremium);
                        riskPremiums.add(riskPremium);
                    }

                }

                List<RiskBean> riskBeans1 = insuranceVO.getRiskBeans();
                for (RiskBean riskBean : riskBeans1) {
                    List<FloatPremium> floatPremiums = riskBean.getFloatPremiums();
                    if (floatPremiums != null && floatPremiums.size() > 0) {
                        ResponseEntity<?> responseEntity1 = countFloatPremium(insuranceVO, map);
                        GenericResponse body1 = (GenericResponse) responseEntity1.getBody();
                        String rc1 = body1.getCode();
                        if (!FormulaCode.STATUS_0000.equals(rc1)) {
                            return responseEntity1;
                        }
                        GenericDataResponse g1 = (GenericDataResponse) body1;
                        map = (Map<String, BigDecimal>) g1.getData();
                    }
                }

                break;
            default:
                return GenericListResponse.ng(ResultEnum.NO_DATA.getMessage());
        }
        return GenericDataResponse.okWithData(map);
    }

    /**
     * 计算主险保费
     *
     * @param formulaBean 保费计算视图
     * @param map         map 集合
     */
    private ResponseEntity<?> basicPremium(FormulaBean formulaBean, Map<String, BigDecimal> map) {
        RiskPremium riskPremium;
        BigDecimal basicPremium = BigDecimal.ZERO;
        InsuranceProgram insuranceProgram1 = insuranceProgramMapper.selectByProgramCode(formulaBean.getProgramCode());
        // 判断 保费计算类型 1-固定 3-浮动 4 固定+浮动
        switch (insuranceProgram1.getBasicPremiumType()) {
            case "1":
                basicPremium = BigDecimal.valueOf(insuranceProgram1.getBasicPremium());
                map.put(BASIC, basicPremium.multiply(formulaBean.getBasicCount()));
                List<RiskBean> riskBeans = formulaBean.getRiskBeans();
                if (riskBeans.size() == 1) {
                    riskPremium = new RiskPremium();
                    riskPremium.setRiskCode(riskBeans.get(0).getRiskCode());
                    riskPremium.setRiskPremiuml(basicPremium.multiply(formulaBean.getBasicCount()));
                    riskPremiums.add(riskPremium);
                }
                break;
            case "3":
                //主险保费类型为浮动
                ResponseEntity<?> responseEntity = countBaseFloatPremium(formulaBean, basicPremium);
                GenericResponse body = (GenericResponse) responseEntity.getBody();
                String rc = body.getCode();
                if (!FormulaCode.STATUS_0000.equals(rc)) {
                    return responseEntity;
                }
                GenericDataResponse g = (GenericDataResponse) body;
                basicPremium = (BigDecimal) g.getData();

                map.put(BASIC, basicPremium.multiply(formulaBean.getBasicCount()));
                break;
            case "4":
                BigDecimal basicPremium3 = BigDecimal.ZERO;
                List<LiabilityLimitRela> liabilityLimitRelas = liabilityLimitRelaMapper.selectByProgramCode(formulaBean.getProgramCode());
                for (LiabilityLimitRela liabilityLimitRela : liabilityLimitRelas) {
                    basicPremium3 = basicPremium3.add(BigDecimal.valueOf(liabilityLimitRela.getPremium()));
                }
                ResponseEntity<?> responseEntity2 = countBaseFloatPremium(formulaBean, basicPremium);
                GenericResponse body2 = (GenericResponse) responseEntity2.getBody();
                String rc2 = body2.getCode();
                if (!FormulaCode.STATUS_0000.equals(rc2)) {
                    return responseEntity2;
                }
                GenericDataResponse g2 = (GenericDataResponse) body2;
                BigDecimal bigDecimal = (BigDecimal) g2.getData();
                basicPremium = basicPremium3.add(bigDecimal);
                map.put(BASIC, basicPremium.multiply(formulaBean.getBasicCount()));
                break;
            default:
                return GenericListResponse.ng(ResultEnum.NO_DATA.getMessage());
        }
        return GenericDataResponse.okWithData(map);
    }

    /**
     * 赔付率因子
     *
     * @param formulaBean 保费计算视图
     * @return ResultMsg
     */
    private ResponseEntity<?> lossRationFactor(FormulaBean formulaBean, Map<String, BigDecimal> map) {

        // 判断是否续保
        if (RENENWAL_STATUS_0.equals(formulaBean.getRenewalStatus())) {


            //根据产品编码来计算相应的赔付率因子
            if (formulaBean.getProductCode().equals(HNKY_PRODUCT_CODE)) {
                //判断续保年限   0-首年投保；1-续保1年；2-续保2年；3-续保3年；依此类推
                Integer renewal = formulaBean.getRenewal();
                // 首年续保
                if (renewal == RENEWAL_0) {
                    BigDecimal lossRation = calculateLossFactor(formulaBean.getPolicyNo(), formulaBean.getChannelNo(), formulaBean.getInscomp(), formulaBean.getLastPremium());

                    map = otherMethod2(map, lossRation, LOSS_FACTOR_PARAM, formulaBean);
                    BigDecimal bigDecimal = map.get(LOSS_FACTOR_PARAM);
                    //未出险车辆优惠调整因子
                    if (bigDecimal.compareTo(BigDecimal.valueOf(LOSS_FACTOR_VALUES)) <= 0) {

                    } else {
                        map.put(DISCOUNT_FACTOR_PARAM, BigDecimal.ZERO);

                    }

                } else if (renewal == RENEWAL_1) {
                    // 第二年续保

                } else if (renewal >= RENEWAL_2) {
                    //连续超过三年续保
                }
            } else if (formulaBean.getProductCode().equals(HNHY_PRODUCT_CODE)) {

            }


        } else {
            map.put(LOSS_FACTOR_PARAM, BigDecimal.ZERO);
            map.put(DISCOUNT_FACTOR_PARAM, BigDecimal.ZERO);
        }
        return GenericDataResponse.okWithData(map);
    }

    /**
     * 计算赔付率
     *
     * @param policyNo    保单号
     * @param via         渠道号
     * @param inscomp     出单公司
     * @param lastPremium 上年度保险费
     * @return 赔付率
     */
    private BigDecimal calculateLossFactor(String policyNo, String via, String inscomp, BigDecimal lastPremium) {
        ConnectCaseSystem conn = new ConnectCaseSystem(UUID.randomUUID(), policyNo, via, inscomp);
        conn.setParameterString();
        BigDecimal sumLossRationFactor = BigDecimal.ZERO;
        try {
            List<CaseSystemResult> caseSystemResults = conn.connectUrl();
            sumLossRationFactor = conn.calculateSumLossRationFactor(caseSystemResults);
            if (sumLossRationFactor.compareTo(BigDecimal.ZERO) == 0 || lastPremium.compareTo(BigDecimal.ZERO) == 0) {
                return BigDecimal.ZERO;
            }
            return sumLossRationFactor.divide(lastPremium, 2, BigDecimal.ROUND_HALF_EVEN);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sumLossRationFactor;


    }

    private ResponseEntity<?> countFloatPremium(InsuranceVO insuranceVO, Map<String, BigDecimal> map) {
        //主险保费类型为浮动
        List<RiskBean> riskBeans = insuranceVO.getRiskBeans();
        RiskPremium riskPremium;
        List<LimitPremium> limitPremiums;
        LimitPremium limitPremium;

        BigDecimal proRiskPremium;
        for (RiskBean riskBean : riskBeans) {

            limitPremiums = new ArrayList<>();
            riskPremium = new RiskPremium();
            proRiskPremium = BigDecimal.ZERO;

            List<FloatPremium> floatPremiums = riskBean.getFloatPremiums();
            if (floatPremiums != null && floatPremiums.size() > 0) {
                //每一个限额的保费
                BigDecimal riskLimitPremium = BigDecimal.ZERO;
                for (FloatPremium floatPremium : floatPremiums) {
                    limitPremium = new LimitPremium();
                    // 查询具体的限额值
                    keyLimitValues = new StringBuilder().append(PRODUCT).append("limitValues_").append(floatPremium.getLiabilityLimitValuesCode()).toString();
                    RedisCallBackInterface<ProductInfo, String> callback6 = (String key) -> {
                        CallRsult callRsult = new CallRsult<String>();
                        LiabilityLimitValues liabilityLimitValues = liabilityLimitValuesMapper.selectByLiabilityLimitValuesCode(key);
                        if (liabilityLimitValues != null) {
                            log.info("key {} 从数据库中查询的数据不为空，缓存数据", key);
                            callRsult.setResultAndAllowCached(liabilityLimitValues);
                        }
                        return callRsult;
                    };
                    LiabilityLimitValues liabilityLimitValues = MyRedisUtils.excute(floatPremium.getLiabilityLimitValuesCode(), keyLimitValues, callback6);
                    keyFloatRate = new StringBuilder().append(PRODUCT).append("limitRela_").append(insuranceVO.getProgramCode()).append(floatPremium.getLiabilityLimitValuesCode()).append(floatPremium.getLimitCode()).toString();
                    RedisCallBackInterface<ProductInfo, Map<String, Object>> callback5 = (Map<String, Object> key) -> {
                        CallRsult callRsult = new CallRsult<String>();
                        FloatRate floatRate = floatRateMapper.findFloatRate(key);
                        if (floatRate != null) {
                            log.info("key {} 从数据库中查询的数据不为空，缓存数据", key);
                            callRsult.setResultAndAllowCached(floatRate);
                        }
                        return callRsult;
                    };
                    Map<String, Object> map2 = new HashMap<>(16);
                    map2.put("programCode", insuranceVO.getProgramCode());
                    map2.put("limitCode", floatPremium.getLimitCode());
                    if (floatPremium.getLiabilityLimitValuesCode() == null) {
                        map2.put("limitValues", floatPremium.getLiabilityLimitValues());
                    } else {
                        map2.put("limitValues", liabilityLimitValues.getLiabilityLimitValues());
                    }
                    FloatRate floatRate = MyRedisUtils.excute(map2, keyFloatRate, callback5);

                    if (liabilityLimitValues == null) {
                        liabilityLimitValues = liabilityLimitValuesMapper.selectByLiabilityLimitValuesCode(floatPremium.getLiabilityLimitValuesCode());
                    }
                    if (floatRate == null) {
                        return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_PROGRAMVALUES.getMessage());
                    } else {
                        if (floatPremium.getLiabilityLimitValuesCode() == null) {
                            riskLimitPremium = riskLimitPremium.add(BigDecimal.valueOf(floatRate.getRate() * floatPremium.getLiabilityLimitValues()));
                        } else {
                            riskLimitPremium = riskLimitPremium.add(BigDecimal.valueOf(floatRate.getRate() * liabilityLimitValues.getLiabilityLimitValues()));
                        }
                    }
                    limitPremium.setLimitCode(floatPremium.getLimitCode());
                    limitPremium.setLimitPremium(riskLimitPremium);
                    limitPremiums.add(limitPremium);
                    //每一个险种的保费
                    proRiskPremium = proRiskPremium.add(riskLimitPremium);
                }
                //险种编码
                String riskCode = riskBean.getRiskCode();
                riskPremium.setRiskCode(riskCode);
                String str = riskCode.substring(2, riskCode.length());
                map.put(str, proRiskPremium);
                riskPremium.setLimitPremiums(limitPremiums);
                riskPremium.setRiskPremiuml(proRiskPremium);
                riskPremiums.add(riskPremium);
            }

        }
        return GenericDataResponse.okWithData(map);

    }

    /**
     * 浮动保费每一个险种保费
     */
    private ResponseEntity<?> calRiskPremium(InsuranceVO insuranceVO, BigDecimal riskLimitPremium, BigDecimal proRiskPremium, List<LimitPremium> limitPremiums, List<FloatPremium> floatPremiums, Map<String, BigDecimal> map, LimitPremium limitPremium) {
        for (FloatPremium floatPremium : floatPremiums) {
            limitPremium = new LimitPremium();
            // 查询具体的限额值
            keyLimitValues = new StringBuilder().append(PRODUCT).append("limitValues_").append(floatPremium.getLiabilityLimitValuesCode()).toString();
            RedisCallBackInterface<ProductInfo, String> callback6 = (String key) -> {
                CallRsult callRsult = new CallRsult<String>();
                LiabilityLimitValues liabilityLimitValues = liabilityLimitValuesMapper.selectByLiabilityLimitValuesCode(key);
                if (liabilityLimitValues != null) {
                    log.info("key {} 从数据库中查询的数据不为空，缓存数据", key);
                    callRsult.setResultAndAllowCached(liabilityLimitValues);
                }
                return callRsult;
            };
            LiabilityLimitValues liabilityLimitValues = MyRedisUtils.excute(floatPremium.getLiabilityLimitValuesCode(), keyLimitValues, callback6);
            keyFloatRate = new StringBuilder().append(PRODUCT).append("limitRela_").append(insuranceVO.getProgramCode()).append(floatPremium.getLiabilityLimitValuesCode()).append(floatPremium.getLimitCode()).toString();
            RedisCallBackInterface<ProductInfo, Map<String, Object>> callback5 = (Map<String, Object> key) -> {
                CallRsult callRsult = new CallRsult<String>();
                FloatRate floatRate = floatRateMapper.findFloatRate(key);
                if (floatRate != null) {
                    log.info("key {} 从数据库中查询的数据不为空，缓存数据", key);
                    callRsult.setResultAndAllowCached(floatRate);
                }
                return callRsult;
            };
            Map<String, Object> map2 = new HashMap<>(16);
            map2.put("programCode", insuranceVO.getProgramCode());
            map2.put("limitCode", floatPremium.getLimitCode());
            if (floatPremium.getLiabilityLimitValuesCode() == null) {
                map2.put("limitValues", floatPremium.getLiabilityLimitValues());
            } else {
                map2.put("limitValues", liabilityLimitValues.getLiabilityLimitValues());
            }
            FloatRate floatRate = MyRedisUtils.excute(map2, keyFloatRate, callback5);

            if (liabilityLimitValues == null) {
                liabilityLimitValues = liabilityLimitValuesMapper.selectByLiabilityLimitValuesCode(floatPremium.getLiabilityLimitValuesCode());
            }
            if (floatRate == null) {
                return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_PROGRAMVALUES.getMessage());
            } else {
                if (floatPremium.getLiabilityLimitValuesCode() == null) {
                    riskLimitPremium = riskLimitPremium.add(BigDecimal.valueOf(floatRate.getRate() * floatPremium.getLiabilityLimitValues()));
                } else {
                    riskLimitPremium = riskLimitPremium.add(BigDecimal.valueOf(floatRate.getRate() * liabilityLimitValues.getLiabilityLimitValues()));
                }
            }
            limitPremium.setLimitCode(floatPremium.getLimitCode());
            limitPremium.setLimitPremium(riskLimitPremium);
            limitPremiums.add(limitPremium);
            //每一个险种的保费
            proRiskPremium = proRiskPremium.add(riskLimitPremium);
        }
        return GenericDataResponse.okWithData(proRiskPremium);
    }


    /**
     * 计算基本险浮动方案保费
     *
     * @param formulaBean  保费计算视图
     * @param basicPremium 基础保费
     * @return ResultMsg
     */
    @SuppressWarnings("unchecked")
    private ResponseEntity<?> countBaseFloatPremium(FormulaBean formulaBean, BigDecimal basicPremium) {
        //主险保费类型为浮动
        List<RiskBean> riskBeans = formulaBean.getRiskBeans();
        RiskPremium riskPremium;
        List<LimitPremium> limitPremiums = new ArrayList<>();
        LimitPremium limitPremium;
        for (RiskBean riskBean : riskBeans) {
            //险种编码
            riskPremium = new RiskPremium();
            riskPremium.setRiskCode(riskBean.getRiskCode());

            List<FloatPremium> floatPremiums = riskBean.getFloatPremiums();
            if (floatPremiums.size() > 0) {
                for (FloatPremium floatPremium : floatPremiums) {
                    limitPremium = new LimitPremium();
                    // 查询具体的限额值
                    keyLimitValues = new StringBuilder().append(PRODUCT).append("limitValues_").append(floatPremium.getLiabilityLimitValuesCode()).toString();
                    RedisCallBackInterface<ProductInfo, String> callback6 = (String key) -> {
                        CallRsult callRsult = new CallRsult<String>();
                        LiabilityLimitValues liabilityLimitValues = liabilityLimitValuesMapper.selectByLiabilityLimitValuesCode(key);
                        if (liabilityLimitValues != null) {
                            log.info("key {} 从数据库中查询的数据不为空，缓存数据", key);
                            callRsult.setResultAndAllowCached(liabilityLimitValues);
                        }
                        return callRsult;
                    };

                    LiabilityLimitValues liabilityLimitValues = MyRedisUtils.excute(floatPremium.getLiabilityLimitValuesCode(), keyLimitValues, callback6);

                    keyFloatRate = new StringBuilder().append(PRODUCT).append("limitRela_").append(formulaBean.getProgramCode()).append(floatPremium.getLiabilityLimitValuesCode()).append(floatPremium.getLimitCode()).toString();

                    RedisCallBackInterface<ProductInfo, Map<String, Object>> callback5 = (Map<String, Object> key) -> {
                        CallRsult callRsult = new CallRsult<String>();
                        FloatRate floatRate = floatRateMapper.findFloatRate(key);
                        if (floatRate != null) {
                            log.info("key {} 从数据库中查询的数据不为空，缓存数据", key);
                            callRsult.setResultAndAllowCached(floatRate);
                        }
                        return callRsult;
                    };
                    Map<String, Object> map2 = new HashMap<>(16);
                    map2.put("programCode", formulaBean.getProgramCode());
                    map2.put("limitCode", floatPremium.getLimitCode());
                    if (floatPremium.getLiabilityLimitValuesCode() == null) {
                        map2.put("limitValues", floatPremium.getLiabilityLimitValues());
                    } else {
                        map2.put("limitValues", liabilityLimitValues.getLiabilityLimitValues());
                    }
                    FloatRate floatRate = MyRedisUtils.excute(map2, keyFloatRate, callback5);

                    if (liabilityLimitValues == null) {
                        liabilityLimitValues = liabilityLimitValuesMapper.selectByLiabilityLimitValuesCode(floatPremium.getLiabilityLimitValuesCode());
                    }
                    if (floatRate == null) {
                        return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_PROGRAMVALUES.getMessage());
                    } else {
                        if (floatPremium.getLiabilityLimitValuesCode() == null) {
                            basicPremium = basicPremium.add(BigDecimal.valueOf(floatRate.getRate() * floatPremium.getLiabilityLimitValues()));
                        } else {
                            basicPremium = basicPremium.add(BigDecimal.valueOf(floatRate.getRate() * liabilityLimitValues.getLiabilityLimitValues()));
                        }
                    }
                    limitPremium.setLimitCode(floatPremium.getLimitCode());
                    limitPremium.setLimitPremium(basicPremium);
                    limitPremiums.add(limitPremium);
                }
            } else {
                return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_FLOATPREMIUMS.getMessage());
            }
            riskPremium.setLimitPremiums(limitPremiums);
            riskPremium.setRiskPremiuml(basicPremium);
            riskPremiums.add(riskPremium);
        }
        return GenericDataResponse.okWithData(basicPremium);
    }

    /**
     * 计算附加险浮动保费
     *
     * @param additionInsurance 附加险对象
     * @return ResultMsg
     */
    @SuppressWarnings("unchecked")
    private ResponseEntity<?> countAdditionFloatPremium(AdditionInsurance additionInsurance) {
        BigDecimal additionPremium = BigDecimal.ZERO;
        List<RiskBean> additionRiskBeans = additionInsurance.getAdditionRiskBeans();
        List<LimitPremium> limitPremiums = new ArrayList<>();
        LimitPremium limitPremium;
        RiskPremium riskPremium;
        for (RiskBean additionRiskBean : additionRiskBeans) {
            //险种编码
            riskPremium = new RiskPremium();
            riskPremium.setRiskCode(additionRiskBean.getRiskCode());

            List<FloatPremium> additionFloatPremiums = additionRiskBean.getFloatPremiums();
            if (additionFloatPremiums != null && additionFloatPremiums.size() > 0) {
                for (FloatPremium additionFloatPremium : additionFloatPremiums) {
                    limitPremium = new LimitPremium();
                    // 查询具体的限额值
                    keyLimitValuesAtion = new StringBuilder().append(PRODUCT).append("limitValues_").append(additionFloatPremium.getLiabilityLimitValuesCode()).toString();
                    RedisCallBackInterface<ProductInfo, String> callback6 = (String key) -> {
                        CallRsult callRsult = new CallRsult<String>();
                        LiabilityLimitValues liabilityLimitValues = liabilityLimitValuesMapper.selectByLiabilityLimitValuesCode(key);
                        if (liabilityLimitValues != null) {
                            log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                            callRsult.setResultAndAllowCached(liabilityLimitValues);
                        }
                        return callRsult;
                    };

                    LiabilityLimitValues liabilityLimitValues = MyRedisUtils.excute(additionFloatPremium.getLiabilityLimitValuesCode(), keyLimitValuesAtion, callback6);


                    keyFloatRateAtion = new StringBuilder().append(PRODUCT).append("limitRela_").append(additionInsurance.getAtionProgramCode()).append(additionFloatPremium.getLiabilityLimitValuesCode()).append(additionFloatPremium.getLimitCode()).toString();
                    RedisCallBackInterface<ProductInfo, Map<String, Object>> callback5 = (Map<String, Object> key) -> {
                        CallRsult callRsult = new CallRsult<String>();
                        FloatRate floatRate = floatRateMapper.findFloatRate(key);
                        if (floatRate != null) {
                            log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                            callRsult.setResultAndAllowCached(floatRate);
                        }
                        return callRsult;
                    };
                    Map<String, Object> map2 = new HashMap<>(16);
                    map2.put("programCode", additionInsurance.getAtionProgramCode());
                    map2.put("limitCode", additionFloatPremium.getLimitCode());
                    if (additionFloatPremium.getLiabilityLimitValuesCode() == null) {
                        map2.put("limitValues", additionFloatPremium.getLiabilityLimitValues());
                    } else {
                        map2.put("limitValues", liabilityLimitValues.getLiabilityLimitValues());
                    }
                    FloatRate floatRate = MyRedisUtils.excute(map2, keyFloatRateAtion, callback5);
                    // 查询具体的限额值
                    if (liabilityLimitValues == null) {
                        liabilityLimitValues = liabilityLimitValuesMapper
                                .selectByLiabilityLimitValuesCode(additionFloatPremium.getLiabilityLimitValuesCode());
                    }

                    if (floatRate == null) {
                        return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_PROGRAMVALUES.getMessage());
                    } else {
                        if (additionFloatPremium.getLiabilityLimitValuesCode() == null) {
                            additionPremium = additionPremium.add(BigDecimal.valueOf(floatRate.getRate() * additionFloatPremium.getLiabilityLimitValues()));
                        } else {
                            additionPremium = additionPremium.add(BigDecimal.valueOf(floatRate.getRate() * liabilityLimitValues.getLiabilityLimitValues()));
                        }
                    }

                    limitPremium.setLimitCode(additionFloatPremium.getLimitCode());
                    limitPremium.setLimitPremium(additionPremium);
                    limitPremiums.add(limitPremium);

                }
            }
            riskPremium.setLimitPremiums(limitPremiums);
            riskPremium.setRiskPremiuml(additionPremium);
            riskPremiums.add(riskPremium);
        }


        return GenericDataResponse.okWithData(additionPremium);
    }


    /**
     * 保费计算因子
     *
     * @param productCode
     * @param factorInfos
     * @param map
     * @return
     */
    @SuppressWarnings("unchecked")
    private ResponseEntity<?> factorInfo(String productCode, List<FactorInfo> factorInfos, Map<String, BigDecimal> map) {
        if (factorInfos == null) {
            log.info("******:----------不含有主险因子");
        } else {
            for (FactorInfo factorInfo : factorInfos) {
                if (factorInfo.getFactorCode().equals(LOSS_FACTOR_CODE) || factorInfo.getFactorCode().equals(DISCOUNT_FACTOR_CODE)) {
                    continue;
                }
                keyFactorCode = new StringBuilder().append(PRODUCT).append("factor_").append(factorInfo.getFactorCode()).toString();
                RedisCallBackInterface<List<ConfCoefficient>, String> callback7 = (String key) -> {
                    CallRsult callRsult = new CallRsult<String>();
                    Factor factor = factorMapper.selectByFactorCode(key);
                    if (factor != null) {
                        log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                        callRsult.setResultAndAllowCached(factor);
                    }
                    return callRsult;
                };
                Factor factor = MyRedisUtils.excute(factorInfo.getFactorCode(), keyFactorCode, callback7);
                if (factor == null) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_FACTORCODE.getMessage());
                }
                //因子参与计算参数名
                String factorCodeName = factor.getFactorCodeName();
                keyFactorRelaId = new StringBuilder().append(PRODUCT).append(factor.getFactorCode()).append(productCode).toString();
                RedisCallBackInterface<ProductInfo, Map<String, String>> callback9 = (Map<String, String> key) -> {
                    CallRsult callRsult = new CallRsult<String>();
                    InsuranceFactorRela insuranceFactorRela = insuranceFactorRelaMapper.selectByMap(key);
                    if (insuranceFactorRela != null) {
                        log.info("key {} 从数据库中查询的数据不为空，缓存数据", key);
                        callRsult.setResultAndAllowCached(insuranceFactorRela);
                    }
                    return callRsult;
                };
                Map<String, String> map3 = new HashMap<>();
                map3.put("productCode", productCode);
                map3.put("factorCode", factorInfo.getFactorCode());
                InsuranceFactorRela insuranceFactorRela = MyRedisUtils.excute(map3, keyFactorRelaId, callback9);


                //因子关系id
                Long factorRelaId = insuranceFactorRela.getFactorRelaId();

                keyConf = new StringBuilder().append(PRODUCT).append("conf_").append(factorRelaId).toString();
                RedisCallBackInterface<List<ConfCoefficient>, String> callback8 = (String key) -> {
                    CallRsult callRsult = new CallRsult<String>();
                    Example example3 = new Example(ConfCoefficient.class);
                    example3.createCriteria().andEqualTo("factorRelaId", Long.valueOf(key));
                    List<ConfCoefficient> confCoefficients = confCoefficientMapper.selectByExample(example3);
                    if (confCoefficients != null) {
                        log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                        callRsult.setResultAndAllowCached(confCoefficients);
                    }
                    return callRsult;
                };

                List<ConfCoefficient> confCoefficients = MyRedisUtils.excute(factorRelaId.toString(), keyConf, callback8);
                if (confCoefficients == null || confCoefficients.size() == 0) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_CONFCOEFFICIENTS.getMessage());
                }

                if (!FormulaUtil.pandun(factorInfo.getStringFactor())) {
                    map.put(factorCodeName, BigDecimal.ZERO);
                } else {
                    double parseDouble = Double.parseDouble(factorInfo.getStringFactor());
                    map = switchMethod(map, confCoefficients, parseDouble, factorCodeName, factorInfo);
                }

                BigDecimal aDouble = map.get(factorCodeName);
                if (aDouble == null) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_FAIL_FACTOR_VALUES.getMessage());
                }
            }

        }
        return GenericDataResponse.okWithData(map);
    }


    /**
     * 计算附加险保费
     *
     * @param additionInsurance 附加险视图
     * @param map               map集合
     * @return ResultMsg
     */
    @SuppressWarnings("unchecked")
    private ResponseEntity<?> additionPremium(AdditionInsurance additionInsurance, Map<String, BigDecimal> map) {
        BigDecimal additionPremium;
        RiskPremium riskPremium;
        List<RiskBean> riskBeans = additionInsurance.getAdditionRiskBeans();
        String riskCode = riskBeans.get(0).getRiskCode();
        InsuranceProgram insuranceProgram1 = insuranceProgramMapper.selectByProgramCode(additionInsurance.getAtionProgramCode());
        // 判断 保费计算类型 0-固定 1-浮动 2 固定+浮动
        if (insuranceProgram1 != null) {
            switch (insuranceProgram1.getBasicPremiumType()) {
                case "1":
                    additionPremium = BigDecimal.valueOf(insuranceProgram1.getBasicPremium());
                    map.put(riskCode.substring(2, riskCode.length()), additionPremium.multiply(additionInsurance.getAdditionCount()));
                    riskPremium = new RiskPremium();
                    riskPremium.setRiskCode(riskBeans.get(0).getRiskCode());
                    riskPremium.setRiskPremiuml(additionPremium.multiply(additionInsurance.getAdditionCount()));
                    riskPremiums.add(riskPremium);

                    break;
                case "3":
                    //保费类型为浮动
                    ResponseEntity<?> responseEntity = countAdditionFloatPremium(additionInsurance);
                    GenericResponse body = (GenericResponse) responseEntity.getBody();
                    String rc = body.getCode();
                    if (!FormulaCode.STATUS_0000.equals(rc)) {
                        return responseEntity;
                    }
                    GenericDataResponse g = (GenericDataResponse) body;
                    additionPremium = (BigDecimal) g.getData();
                    map.put(riskCode.substring(2, riskCode.length()), additionPremium.multiply(additionInsurance.getAdditionCount()));

                    break;
                case "4":
                    BigDecimal basicPremium3 = BigDecimal.ZERO;
                    List<LiabilityLimitRela> liabilityLimitRelas = liabilityLimitRelaMapper.selectByProgramCode(additionInsurance.getAtionProgramCode());
                    for (LiabilityLimitRela liabilityLimitRela : liabilityLimitRelas) {
                        if (liabilityLimitRela.getPremium() != null) {
                            basicPremium3 = basicPremium3.add(BigDecimal.valueOf(liabilityLimitRela.getPremium()));
                        }

                    }
                    ResponseEntity<?> responseEntity2 = countAdditionFloatPremium(additionInsurance);
                    GenericResponse body2 = (GenericResponse) responseEntity2.getBody();
                    String rc2 = body2.getCode();
                    if (!FormulaCode.STATUS_0000.equals(rc2)) {
                        return responseEntity2;
                    }
                    GenericDataResponse g2 = (GenericDataResponse) body2;

                    additionPremium = (BigDecimal) g2.getData();
                    map.put(riskCode.substring(2, riskCode.length()), additionPremium.multiply(additionInsurance.getAdditionCount()));
                    break;
                default:
                    return GenericListResponse.ng(ResultEnum.NO_DATA.getMessage());
            }
        }

        return GenericDataResponse.okWithData(map);
    }

    private ResponseEntity<?> verifyFormulaDTO(FormulaDTO formulaDTO) {
        //产品编码
        if (StringUtils.isEmpty(formulaDTO.getProductCode())) {
            return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_PRODUCT_CODE.getMessage());
        }
        keyProdu = PRODUCT + "product_" + formulaDTO.getProductCode();

        RedisCallBackInterface<ProductInfo, String> callback = (String key) -> {
            CallRsult callRsult = new CallRsult<String>();
            ProductInfo productInfo = productInfoMapper.selectByProductCode(key);
            if (productInfo != null) {
                log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                callRsult.setResultAndAllowCached(productInfo);
            }
            return callRsult;
        };
        ProductInfo productInfo = MyRedisUtils.excute(formulaDTO.getProductCode(), keyProdu, callback);
        if (productInfo == null) {
            return GenericListResponse.ng(PremiumFormulaEnum.FAILED_PRODUCT_CODE.getMessage());
        }

        List<LifeInsuranceVO> lifeInsuranceVOS = formulaDTO.getLifeInsuranceVOS();
        if (lifeInsuranceVOS == null && lifeInsuranceVOS.size() == 0) {
            return GenericListResponse.ng("寿险传值vo对象不能为空");
        } else {
            for (LifeInsuranceVO lifeInsuranceVO : lifeInsuranceVOS) {
                if (StringUtils.isEmpty(lifeInsuranceVO.getRiskCode())) {
                    return GenericListResponse.ng("险种编码不能为空");
                }
                if (lifeInsuranceVO.getRiskCopies() == null) {
                    return GenericListResponse.ng("险种份数不能为空");
                }
                if (StringUtils.isEmpty(lifeInsuranceVO.getProgramCode()) && StringUtils.isEmpty(lifeInsuranceVO.getSalesRules())) {
                    return GenericListResponse.ng("方案编码或 销售属性值 不能为空");
                }
                if (lifeInsuranceVO.getProgramCode() != null) {
                    if (lifeInsuranceVO.getUserPurchaseAmount() == null) {
                        return GenericListResponse.ng("用户购买保额字段不能为空");
                    }
                }
            }
        }

        //保费计算因子
        Example example = new Example(InsuranceFactorRela.class);
        example.createCriteria().andEqualTo("productCode", formulaDTO.getProductCode()).andEqualTo("status", "1");
        List<InsuranceFactorRela> insuranceFactorRelas = insuranceFactorRelaMapper.selectByExample(example);
        if (insuranceFactorRelas != null && insuranceFactorRelas.size() > 0) {
            List<FactorInfo> factorInfos = formulaDTO.getFactorInfos();
            if (factorInfos == null || factorInfos.size() == 0) {
                return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_FACTORINFOS.getMessage());
            } else {
                if (insuranceFactorRelas.size() != factorInfos.size()) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_FORMULA_FACTORS.getMessage());
                } else {
                    for (FactorInfo factorInfo : factorInfos) {
                        if (factorInfo.getFactorCode() == null) {
                            return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_FACTORRELAID.getMessage());
                        }
                        //赔付率因子 和 优惠调整因子
                        if (LOSS_FACTOR_CODE.equals(factorInfo.getFactorCode()) || DISCOUNT_FACTOR_CODE.equals(factorInfo.getFactorCode())) {
                            continue;
                        }

                        keyInfare = new StringBuilder().append(PRODUCT).append("inFaRe_").append(factorInfo.getFactorCode()).toString();
                        RedisCallBackInterface<InsuranceFactorRela, String> callback3 = (String key) -> {
                            CallRsult callRsult = new CallRsult<String>();
                            Factor factor = factorMapper.selectByFactorCode(key);
                            if (factor != null) {
                                log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                                callRsult.setResultAndAllowCached(factor);
                            }
                            return callRsult;
                        };
                        Factor factor = MyRedisUtils.excute(factorInfo.getFactorCode(), keyInfare, callback3);

                        if (factor == null) {
                            return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_FACTOR_RELA_ID.getMessage());
                        }

                        if (StringUtils.isEmpty(factorInfo.getStringFactor())) {
                            return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_STRING_FACTOR.getMessage());
                        }
                    }
                }
            }
        } else {
            log.info("》》》》》》》》》》: 产品不含有保费因子");
        }


        return GenericListResponse.ok();
    }

    /**
     * 字段校验
     *
     * @param
     * @return ResultMsg，字段无异返回保费计算公式
     */
    private ResponseEntity<?> veryCountPremiumBean(CountPremiumBean countPremiumBean) {
        //续保状态
        if (StringUtils.isEmpty(countPremiumBean.getRenewalStatus())) {
            return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_RENENWALSTATUS.getMessage());
        } else {
            if (!(RENENWAL_STATUS_0.equals(countPremiumBean.getRenewalStatus()) || RENENWAL_STATUS_1.equals(countPremiumBean.getRenewalStatus()))) {
                return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_RENENWALSTATUS.getMessage());
            }
            if (RENENWAL_STATUS_0.equals(countPremiumBean.getRenewalStatus())) {
                if (StringUtils.isEmpty(countPremiumBean.getPolicyNo())) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_POLICYNO.getMessage());
                }
                if (countPremiumBean.getRenewal() == null) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_RENEWAL.getMessage());
                }
                if (StringUtils.isEmpty(countPremiumBean.getChannelNo())) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_CHANNELNO.getMessage());
                }
                if (countPremiumBean.getLastPremium() == null) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_LASTPREMIUM.getMessage());
                }
                if (StringUtils.isEmpty(countPremiumBean.getInscomp())) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_INSCOMP.getMessage());
                }
            }

        }
        //产品编码
        if (StringUtils.isEmpty(countPremiumBean.getProductCode())) {
            return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_PRODUCT_CODE.getMessage());
        }
        keyProdu = PRODUCT + "product_" + countPremiumBean.getProductCode();

        RedisCallBackInterface<ProductInfo, String> callback = (String key) -> {
            CallRsult callRsult = new CallRsult<String>();
            ProductInfo productInfo = productInfoMapper.selectByProductCode(key);
            if (productInfo != null) {
                log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                callRsult.setResultAndAllowCached(productInfo);
            }
            return callRsult;
        };
        ProductInfo productInfo = MyRedisUtils.excute(countPremiumBean.getProductCode(), keyProdu, callback);
        if (productInfo == null) {
            return GenericListResponse.ng(PremiumFormulaEnum.FAILED_PRODUCT_CODE.getMessage());
        }

        //方案校验
        List<InsuranceVO> insuranceVOS = countPremiumBean.getInsuranceVOS();
        if (insuranceVOS == null && insuranceVOS.size() == 0) {
            return GenericListResponse.ng("方案组合不能为空");
        }
        for (InsuranceVO insuranceVO : insuranceVOS) {
            //参保份数
            if (insuranceVO.getCount() == null) {
                return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_NUMBEROFPEOPLE.getMessage());
            }

            //方案编码
            if (StringUtils.isEmpty(insuranceVO.getProgramCode())) {
                return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_PROGRAM_CODE.getMessage());
            } else {
                keyProgram = new StringBuilder().append(PRODUCT).append("program_").append(insuranceVO.getProgramCode()).toString();
                RedisCallBackInterface<List<InsuranceProgram>, String> callback2 = (String key) -> {
                    CallRsult callRsult = new CallRsult<String>();
                    Example example = new Example(InsuranceProgram.class);
                    example.createCriteria().andEqualTo("programCode", key);
                    List<InsuranceProgram> insurancePrograms = insuranceProgramMapper.selectByExample(example);
                    if (insurancePrograms != null) {
                        log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                        callRsult.setResultAndAllowCached(insurancePrograms);
                    }
                    return callRsult;
                };
                List<InsuranceProgram> insurancePrograms = MyRedisUtils.excute(insuranceVO.getProgramCode(), keyProgram, callback2);
                if (insurancePrograms == null || insurancePrograms.size() == 0) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_PRODUCTPROGRAMCODE.getMessage());
                }
                String basicPremiumType = insurancePrograms.get(0).getBasicPremiumType();
                List<RiskBean> riskBeans = insuranceVO.getRiskBeans();
                if (riskBeans == null || riskBeans.size() == 0) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_RISKBEAN.getMessage());
                }
                //基本险限额组合保费
                if (TYPE_3.equals(basicPremiumType) || TYPE_4.equals(basicPremiumType)) {

                    for (RiskBean riskBean : riskBeans) {
                        if (StringUtils.isEmpty(riskBean.getRiskCode())) {
                            return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_RISKCODE.getMessage());
                        } else {
                            RiskInfo riskInfo = riskInfoMapper.selectByRiskCode(riskBean.getRiskCode());
                            if (riskInfo == null) {
                                return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_BASIC_RISK.getMessage());
                            }
                        }
                        List<FloatPremium> floatPremiums = riskBean.getFloatPremiums();
                        if ((floatPremiums == null || floatPremiums.size() == 0) && TYPE_3.equals(basicPremiumType)) {
                            return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_FLOATPREMIUMS.getMessage());
                        } else {
                            if (TYPE_3.equals(basicPremiumType)) {
                                for (FloatPremium floatPremium : floatPremiums) {
                                    if (StringUtils.isEmpty(floatPremium.getLimitCode())) {
                                        return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_LIMITID.getMessage());
                                    }
                                    if (floatPremium.getLiabilityLimitValues() == null && floatPremium.getLiabilityLimitValuesCode() == null) {
                                        return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_LIABILITYLIMITVALUESID.getMessage());
                                    }
                                    if (floatPremium.getLiabilityLimitValuesCode() != null) {
                                        LiabilityLimitValues liabilityLimitValues = liabilityLimitValuesMapper
                                                .selectByLiabilityLimitValuesCode(floatPremium.getLiabilityLimitValuesCode());
                                        if (liabilityLimitValues == null) {
                                            return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_LIMITVALUES_ID.getMessage());
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }

        //保费计算因子
        Example example = new Example(InsuranceFactorRela.class);
        example.createCriteria().andEqualTo("productCode", countPremiumBean.getProductCode()).andEqualTo("status", "1");
        List<InsuranceFactorRela> insuranceFactorRelas = insuranceFactorRelaMapper.selectByExample(example);
        if (insuranceFactorRelas != null && insuranceFactorRelas.size() > 0) {
            List<FactorInfo> factorInfos = countPremiumBean.getFactorInfos();
            if (factorInfos == null || factorInfos.size() == 0) {
                return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_FACTORINFOS.getMessage());
            } else {
                if (insuranceFactorRelas.size() != factorInfos.size()) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_FORMULA_FACTORS.getMessage());
                } else {
                    for (FactorInfo factorInfo : factorInfos) {
                        if (factorInfo.getFactorCode() == null) {
                            return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_FACTORRELAID.getMessage());
                        }
                        //赔付率因子 和 优惠调整因子
                        if (LOSS_FACTOR_CODE.equals(factorInfo.getFactorCode()) || DISCOUNT_FACTOR_CODE.equals(factorInfo.getFactorCode())) {
                            continue;
                        }

                        keyInfare = new StringBuilder().append(PRODUCT).append("inFaRe_").append(factorInfo.getFactorCode()).toString();
                        RedisCallBackInterface<InsuranceFactorRela, String> callback3 = (String key) -> {
                            CallRsult callRsult = new CallRsult<String>();
                            Factor factor = factorMapper.selectByFactorCode(key);
                            if (factor != null) {
                                log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                                callRsult.setResultAndAllowCached(factor);
                            }
                            return callRsult;
                        };
                        Factor factor = MyRedisUtils.excute(factorInfo.getFactorCode(), keyInfare, callback3);

                        if (factor == null) {
                            return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_FACTOR_RELA_ID.getMessage());
                        }

                        if (StringUtils.isEmpty(factorInfo.getStringFactor())) {
                            return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_STRING_FACTOR.getMessage());
                        }
                    }
                }
            }
        } else {
            log.info("》》》》》》》》》》: 产品不含有保费因子");
        }


        //保费计算公式
        String premiumDesign;
        keyPrefor = new StringBuilder().append(PRODUCT).append("preFor_").append(productInfo.getProductDesginId()).toString();
        RedisCallBackInterface<PremiumFormula, String> callback4 = (String key) -> {
            CallRsult callRsult = new CallRsult<String>();
            PremiumFormula premiumFormula = premiumFormulaMapper.selectByPrimaryKey(Integer.parseInt(key));
            if (premiumFormula != null) {
                log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                callRsult.setResultAndAllowCached(premiumFormula);
            }
            return callRsult;
        };
        PremiumFormula premiumFormula = MyRedisUtils.excute(productInfo.getProductDesginId().toString(), keyPrefor, callback4);
        if (premiumFormula != null) {
            premiumDesign = premiumFormula.getPremiumDesign().replace("[", "(").replace("]", "");
        } else {
            return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_PRODUCT_FORMULA.getMessage());
        }


        return GenericDataResponse.okWithData(premiumDesign);
    }

    @SuppressWarnings("unchecked")
    private ResponseEntity<?> verifyFormulaBean(FormulaBean formulaBean) {
        //续保状态
        if (StringUtils.isEmpty(formulaBean.getRenewalStatus())) {
            return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_RENENWALSTATUS.getMessage());
        } else {
            if (!(RENENWAL_STATUS_0.equals(formulaBean.getRenewalStatus()) || RENENWAL_STATUS_1.equals(formulaBean.getRenewalStatus()))) {
                return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_RENENWALSTATUS.getMessage());
            }
            if (RENENWAL_STATUS_0.equals(formulaBean.getRenewalStatus())) {
                if (StringUtils.isEmpty(formulaBean.getPolicyNo())) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_POLICYNO.getMessage());
                }
                if (formulaBean.getRenewal() == null) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_RENEWAL.getMessage());
                }
                if (StringUtils.isEmpty(formulaBean.getChannelNo())) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_CHANNELNO.getMessage());
                }
                if (formulaBean.getLastPremium() == null) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_LASTPREMIUM.getMessage());
                }
                if (StringUtils.isEmpty(formulaBean.getInscomp())) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_INSCOMP.getMessage());
                }
            }

        }

        //基本险参保份数
        if (formulaBean.getBasicCount() == null) {
            return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_NUMBEROFPEOPLE.getMessage());
        }
        //产品编码
        if (StringUtils.isEmpty(formulaBean.getProductCode())) {
            return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_PRODUCT_CODE.getMessage());
        }
        keyProdu = PRODUCT + "product_" + formulaBean.getProductCode();

        RedisCallBackInterface<ProductInfo, String> callback = (String key) -> {
            CallRsult callRsult = new CallRsult<String>();
            ProductInfo productInfo = productInfoMapper.selectByProductCode(key);
            if (productInfo != null) {
                log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                callRsult.setResultAndAllowCached(productInfo);
            }
            return callRsult;
        };
        ProductInfo productInfo = MyRedisUtils.excute(formulaBean.getProductCode(), keyProdu, callback);
        if (productInfo == null) {
            return GenericListResponse.ng(PremiumFormulaEnum.FAILED_PRODUCT_CODE.getMessage());
        }
        //基本险方案编码
        if (StringUtils.isEmpty(formulaBean.getProgramCode())) {
            return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_PROGRAM_CODE.getMessage());
        } else {
            keyProgram = new StringBuilder().append(PRODUCT).append("program_").append(formulaBean.getProgramCode()).toString();
            RedisCallBackInterface<List<InsuranceProgram>, String> callback2 = (String key) -> {
                CallRsult callRsult = new CallRsult<String>();
                Example example = new Example(InsuranceProgram.class);
                example.createCriteria().andEqualTo("programCode", key);
                List<InsuranceProgram> insurancePrograms = insuranceProgramMapper.selectByExample(example);
                if (insurancePrograms != null) {
                    log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                    callRsult.setResultAndAllowCached(insurancePrograms);
                }
                return callRsult;
            };
            List<InsuranceProgram> insurancePrograms = MyRedisUtils.excute(formulaBean.getProgramCode(), keyProgram, callback2);
            if (insurancePrograms == null || insurancePrograms.size() == 0) {
                return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_PRODUCTPROGRAMCODE.getMessage());
            }
            String basicPremiumType = insurancePrograms.get(0).getBasicPremiumType();
            List<RiskBean> riskBeans = formulaBean.getRiskBeans();
            if (riskBeans == null || riskBeans.size() == 0) {
                return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_RISKBEAN.getMessage());
            }
            //基本险限额组合保费
            if (TYPE_3.equals(basicPremiumType) || TYPE_4.equals(basicPremiumType)) {

                for (RiskBean riskBean : riskBeans) {
                    if (StringUtils.isEmpty(riskBean.getRiskCode())) {
                        return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_RISKCODE.getMessage());
                    } else {
                        RiskInfo riskInfo = riskInfoMapper.selectByRiskCode(riskBean.getRiskCode());
                        if (riskInfo == null) {
                            return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_BASIC_RISK.getMessage());
                        }

                    }
                    List<FloatPremium> floatPremiums = riskBean.getFloatPremiums();
                    if (floatPremiums == null || floatPremiums.size() == 0) {
                        return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_FLOATPREMIUMS.getMessage());
                    } else {
                        for (FloatPremium floatPremium : floatPremiums) {
                            if (StringUtils.isEmpty(floatPremium.getLimitCode())) {
                                return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_LIMITID.getMessage());
                            }
                            if (floatPremium.getLiabilityLimitValues() == null && floatPremium.getLiabilityLimitValuesCode() == null) {
                                return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_LIABILITYLIMITVALUESID.getMessage());
                            }
                            if (floatPremium.getLiabilityLimitValuesCode() != null) {
                                LiabilityLimitValues liabilityLimitValues = liabilityLimitValuesMapper
                                        .selectByLiabilityLimitValuesCode(floatPremium.getLiabilityLimitValuesCode());
                                if (liabilityLimitValues == null) {
                                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_LIMITVALUES_ID.getMessage());
                                }
                            }
                        }
                    }
                }


            }
        }


        //保费计算因子
        Example example = new Example(InsuranceFactorRela.class);
        example.createCriteria().andEqualTo("productCode", formulaBean.getProductCode());
        List<InsuranceFactorRela> insuranceFactorRelas = insuranceFactorRelaMapper.selectByExample(example);
        if (insuranceFactorRelas != null && insuranceFactorRelas.size() > 0) {
            List<FactorInfo> factorInfos = formulaBean.getFactorInfos();
            if (factorInfos == null || factorInfos.size() == 0) {
                return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_FACTORINFOS.getMessage());
            } else {
                if (insuranceFactorRelas.size() != factorInfos.size()) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_FORMULA_FACTORS.getMessage());
                } else {
                    for (FactorInfo factorInfo : factorInfos) {
                        if (factorInfo.getFactorCode() == null) {
                            return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_FACTORRELAID.getMessage());
                        }
                        //赔付率因子 和 优惠调整因子
                        if (LOSS_FACTOR_CODE.equals(factorInfo.getFactorCode()) || DISCOUNT_FACTOR_CODE.equals(factorInfo.getFactorCode())) {
                            continue;
                        }

                        keyInfare = new StringBuilder().append(PRODUCT).append("inFaRe_").append(factorInfo.getFactorCode()).toString();
                        RedisCallBackInterface<InsuranceFactorRela, String> callback3 = (String key) -> {
                            CallRsult callRsult = new CallRsult<String>();
                            Factor factor = factorMapper.selectByFactorCode(key);
                            if (factor != null) {
                                log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                                callRsult.setResultAndAllowCached(factor);
                            }
                            return callRsult;
                        };
                        Factor factor = MyRedisUtils.excute(factorInfo.getFactorCode(), keyInfare, callback3);

                        if (factor == null) {
                            return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_FACTOR_RELA_ID.getMessage());
                        }

                        if (StringUtils.isEmpty(factorInfo.getStringFactor())) {
                            return GenericListResponse.ng(PremiumFormulaEnum.PARAMETER_IS_NULL_STRING_FACTOR.getMessage());
                        }
                    }
                }
            }
        } else {
            log.info("》》》》》》》》》》: 产品不含有保费因子");
        }


        //保费计算公式
        String premiumDesign;
        keyPrefor = new StringBuilder().append(PRODUCT).append("preFor_").append(productInfo.getProductDesginId()).toString();
        RedisCallBackInterface<PremiumFormula, String> callback4 = (String key) -> {
            CallRsult callRsult = new CallRsult<String>();
            PremiumFormula premiumFormula = premiumFormulaMapper.selectByPrimaryKey(Integer.parseInt(key));
            if (premiumFormula != null) {
                log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                callRsult.setResultAndAllowCached(premiumFormula);
            }
            return callRsult;
        };
        PremiumFormula premiumFormula = MyRedisUtils.excute(productInfo.getProductDesginId().toString(), keyPrefor, callback4);
        if (premiumFormula != null) {
            premiumDesign = premiumFormula.getPremiumDesign().replace("[", "(").replace("]", "");
        } else {
            return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_PRODUCT_FORMULA.getMessage());
        }

        //附加险集合
        List<AdditionInsurance> additionInsurances = formulaBean.getAdditionInsurances();
        if (additionInsurances != null && additionInsurances.size() > 0) {
            for (AdditionInsurance additionInsurance : additionInsurances) {
                if (additionInsurance.getAdditionCount() == null) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_ADDITONCOUNT.getMessage());
                }
                if (StringUtils.isEmpty(additionInsurance.getAtionProgramCode())) {
                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_ADDITIONPROGRAMCODE.getMessage());
                } else {
                    keyProgramAtion = new StringBuilder().append(PRODUCT).append("program_").append(additionInsurance.getAtionProgramCode()).toString();
                    RedisCallBackInterface<List<InsuranceProgram>, String> callback2 = (String key) -> {
                        CallRsult callRsult = new CallRsult<String>();
                        Example example2 = new Example(InsuranceProgram.class);
                        example2.createCriteria().andEqualTo("programCode", key);
                        List<InsuranceProgram> insurancePrograms = insuranceProgramMapper.selectByExample(example2);
                        if (insurancePrograms != null) {
                            log.error("key {} 从数据库中查询的数据不为空，缓存数据", key);
                            callRsult.setResultAndAllowCached(insurancePrograms);
                        }
                        return callRsult;
                    };
                    List<InsuranceProgram> insurancePrograms = MyRedisUtils.excute(additionInsurance.getAtionProgramCode(), keyProgramAtion, callback2);
                    if (insurancePrograms == null || insurancePrograms.size() == 0) {
                        return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_PRODUCTPROGRAMCODE.getMessage());
                    } else {
                        String basicPremiumType = insurancePrograms.get(0).getBasicPremiumType();
                        //主险浮动保费
                        if (TYPE_3.equals(basicPremiumType) || TYPE_4.equals(basicPremiumType)) {
                            List<RiskBean> additionRiskBeans = additionInsurance.getAdditionRiskBeans();
                            if (additionRiskBeans == null || additionRiskBeans.size() == 0) {
                                return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_RISKBEAN.getMessage());
                            } else {
                                for (RiskBean additionRiskBean : additionRiskBeans) {
                                    if (StringUtils.isEmpty(additionRiskBean.getRiskCode())) {
                                        return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_RISKCODE.getMessage());
                                    } else {
                                        RiskInfo riskInfo = riskInfoMapper.selectByRiskCode(additionRiskBean.getRiskCode());
                                        if (riskInfo == null) {
                                            return GenericListResponse.ng(PremiumFormulaEnum.IS_FAILD_ADDTITON_RISK.getMessage());
                                        }
                                    }
                                    List<FloatPremium> floatPremiums = additionRiskBean.getFloatPremiums();

                                    if (floatPremiums == null || floatPremiums.size() == 0) {
                                        return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_ADDTITIONFLOATPREMIUMS.getMessage());
                                    } else {
                                        for (FloatPremium additionFloatPremium : floatPremiums) {
                                            if (StringUtils.isEmpty(additionFloatPremium.getLimitCode())) {
                                                return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_LIMITID.getMessage());
                                            }
                                            if (additionFloatPremium.getLiabilityLimitValues() == null && additionFloatPremium.getLiabilityLimitValuesCode() == null) {
                                                return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_LIABILITYLIMITVALUESID.getMessage());
                                            }
                                            if (additionFloatPremium.getLiabilityLimitValuesCode() != null) {
                                                LiabilityLimitValues liabilityLimitValues = liabilityLimitValuesMapper
                                                        .selectByLiabilityLimitValuesCode(additionFloatPremium.getLiabilityLimitValuesCode());
                                                if (liabilityLimitValues == null) {
                                                    return GenericListResponse.ng(PremiumFormulaEnum.IS_NULL_LIMITVALUES_ID.getMessage());
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return GenericDataResponse.okWithData(premiumDesign);


    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")

    public ResultMsg countPremium(FormulaBean formulaBean) {
       /* Premium premium = new Premium();
        long startTime = System.currentTimeMillis();
        //字段校验
        riskPremiums.clear();
        ResultMsg resultMsg1 = verifyFormulaBean(formulaBean);
        if (resultMsg1.getCode() != START_200) {
            return resultMsg1;
        }
        ResultVO verifyFormulaBean = (ResultVO) resultMsg1;
        String premiumDesign = (String) verifyFormulaBean.getData();

        //基本险基础保费
        Map<String, BigDecimal> map = new HashMap<>(16);
        ResultMsg resultMsg = basicPremium(formulaBean, map);
        if (resultMsg.getCode() != START_200) {
            return resultMsg;
        }
        ResultVO<Map<String, BigDecimal>> basicPremium = (ResultVO<Map<String, BigDecimal>>) resultMsg;
        map = basicPremium.getData();


        //附加险保费
        List<AdditionInsurance> additionInsurances = formulaBean.getAdditionInsurances();

        if (additionInsurances != null && additionInsurances.size() > 0) {
            //排序
            //Collections.sort(additionInsurances);
            for (AdditionInsurance additionInsurance : additionInsurances) {
                ResultMsg resultMsg3 = additionPremium(additionInsurance, map);
                if (resultMsg3.getCode() != START_200) {
                    return resultMsg3;
                }
                ResultVO<Map<String, BigDecimal>> additionPremium = (ResultVO<Map<String, BigDecimal>>) resultMsg3;
                map = additionPremium.getData();
            }
        }

        // 保费计算因子
        ResultMsg resultMsg2 = factorInfo(formulaBean, map);
        if (resultMsg2.getCode() != START_200) {
            return resultMsg2;
        }
        ResultVO<Map<String, BigDecimal>> factorInfo = (ResultVO<Map<String, BigDecimal>>) resultMsg2;
        map = factorInfo.getData();

        //赔付率因子/优惠调整因子
        ResultMsg resultMsg3 = lossRationFactor(formulaBean, map);
        if (resultMsg3.getCode() != START_200) {
            return resultMsg3;
        }
        ResultVO<Map<String, BigDecimal>> msg3 = (ResultVO<Map<String, BigDecimal>>) resultMsg3;
        map = msg3.getData();

        // 保费计算规则
        ResultMsg resultMsg5 = premiumRule(formulaBean, map, premiumDesign);
        if (resultMsg5.getCode() != START_200) {
            return resultMsg5;
        }
        ResultVO premiumRule = (ResultVO) resultMsg5;
        premiumDesign = (String) premiumRule.getData();


        // 对保费计算公式格式进行校验
        FormulaVerify formulaVerify = new FormulaVerify(premiumDesign);
        if (formulaVerify.checkValid()) {
            BigDecimal grossPremium;
            try {
                grossPremium = MathUtil.getBigDecimal(FormulaUtil.convertToCode(premiumDesign, map));
            } catch (Exception e) {
                e.printStackTrace();
                log.error(PremiumFormulaEnum.IS_HAS_UNDEFINED_VARIABLE.getMessage());
                return GenericListResponse.ng(PremiumFormulaEnum.IS_HAS_UNDEFINED_VARIABLE);
            }

            log.info("******总保费******：" + (grossPremium.setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
            long endTime = System.currentTimeMillis();
            log.info(">>>>>>>>>>>>>>>当前程序耗时：" + (endTime - startTime) + "ms");
            premium.setTotalPremium(grossPremium.setScale(2, BigDecimal.ROUND_HALF_UP));
            //premium.setRiskPremiums(riskPremiums);
            return ResultUtils.success(premium);
        } else {
            return GenericListResponse.ng(FormulaVerify.Msg);
        }*/
        return null;
    }
}
