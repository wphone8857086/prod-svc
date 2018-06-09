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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

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


    public static String keyLimitValues = null;

    public static String keyLimitValuesAtion = null;


    @Autowired
    ConfCoefficientMapper confCoefficientMapper;

    @Autowired
    InsuranceFactorRelaMapper insuranceFactorRelaMapper;

    @Autowired
    InsuranceProgramMapper insuranceProgramMapper;

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
    private RedisTemplate redisTemplate;




    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    public ResultMsg countPremium(FormulaBean formulaBean) {
        long startTime = System.currentTimeMillis();
        //字段校验
        ResultMsg resultMsg1 = verifyFormulaBean(formulaBean);
        if (resultMsg1.getCode() != START_200) {
            return resultMsg1;
        }
        ResultVO verifyFormulaBean = (ResultVO) resultMsg1;
        String premiumDesign = (String) verifyFormulaBean.getData();

        // 主险基础保费
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
            int count = 0;
            for (AdditionInsurance additionInsurance : additionInsurances) {
                ResultMsg resultMsg3 = additionPremium(additionInsurance, map, count);
                if (resultMsg3.getCode() != START_200) {
                    return resultMsg3;
                }
                ResultVO<Map<String, BigDecimal>> additionPremium = (ResultVO<Map<String, BigDecimal>>) resultMsg3;
                map = additionPremium.getData();
                count++;
            }
        } else {
            map.put(ATION, BigDecimal.ZERO);
            map.put(ATION1, BigDecimal.ZERO);
            map.put(ATION2, BigDecimal.ZERO);
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
                return ResultUtils.warnMsg(PremiumFormulaEnum.IS_HAS_UNDEFINED_VARIABLE);
            }

            log.info("******总保费******：" + (grossPremium.setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
            long endTime = System.currentTimeMillis();
            log.info(">>>>>>>>>>>>>>>当前程序耗时：" + (endTime - startTime) + "ms");
            return ResultUtils.success(grossPremium.setScale(2, BigDecimal.ROUND_HALF_UP));
        } else {
            return ResultUtils.warnMsg(FormulaVerify.Msg);
        }
    }


    /**
     * @param formulaBeans 保费计算视图集合
     * @return ResultMsg
     */
    @Override
    public ResultMsg countPremiums(List<FormulaBean> formulaBeans) {
        long startTime = System.currentTimeMillis();

        BigDecimal allPremiums = BigDecimal.ZERO;
        for (FormulaBean formulaBean : formulaBeans) {
            //字段校验
            ResultMsg resultMsg1 = verifyFormulaBean(formulaBean);
            if (resultMsg1.getCode() != START_200) {
                return resultMsg1;
            }
            ResultVO verifyFormulaBean = (ResultVO) resultMsg1;
            String premiumDesign = (String) verifyFormulaBean.getData();


            // 主险基础保费
            Map<String, BigDecimal> map = new HashMap<>(16);
            ResultMsg resultMsg = basicPremium(formulaBean, map);
            if (resultMsg.getCode() != START_200) {
                return resultMsg;
            }

            ResultVO<Map<String, BigDecimal>> basicPremium = (ResultVO<Map<String, BigDecimal>>) resultMsg;
            map = basicPremium.getData();


            // 主险因子
            ResultMsg resultMsg2 = factorInfo(formulaBean, map);
            if (resultMsg2.getCode() != START_200) {
                return resultMsg2;
            }
            ResultVO<Map<String, BigDecimal>> factorInfo = (ResultVO<Map<String, BigDecimal>>) resultMsg2;
            map = factorInfo.getData();


            //附加险保费
            List<AdditionInsurance> additionInsurances = formulaBean.getAdditionInsurances();
            if (additionInsurances != null && additionInsurances.size() > 0) {
                int count = 0;
                for (AdditionInsurance additionInsurance : additionInsurances) {
                    ResultMsg resultMsg3 = additionPremium(additionInsurance, map, count);
                    if (resultMsg3.getCode() != START_200) {
                        return resultMsg3;
                    }
                    ResultVO<Map<String, BigDecimal>> additionPremium = (ResultVO<Map<String, BigDecimal>>) resultMsg3;
                    map = additionPremium.getData();
                    count++;
                }
            } else {
                map.put(ATION, BigDecimal.ZERO);
                map.put(ATION1, BigDecimal.ZERO);
                map.put(ATION2, BigDecimal.ZERO);
                log.info("》》》》》》》》:不含附加险");
            }


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

                BigDecimal apart;
                try {
                    apart = (BigDecimal) FormulaUtil.convertToCode(premiumDesign, map);
                } catch (Exception e) {
                    log.error(PremiumFormulaEnum.IS_HAS_UNDEFINED_VARIABLE.getMessage());
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_HAS_UNDEFINED_VARIABLE);
                }
                //每单保费
                BigDecimal grossPremium = BigDecimal.ZERO;
                if (apart != null && !Objects.equals(apart, BigDecimal.ZERO)) {
                    grossPremium = apart.multiply(formulaBean.getBasicCount());
                }
                // 总保费
                allPremiums = allPremiums.add(grossPremium);
            } else {
                return ResultUtils.warnMsg(FormulaVerify.Msg);
            }
        }
        log.info("******总保费******：" + (allPremiums.setScale(2, BigDecimal.ROUND_HALF_UP)).toString());
        long endTime = System.currentTimeMillis();
        log.info(">>>>>>>>>>>>>>>当前程序耗时：" + (endTime - startTime) + "ms");

        return ResultUtils.success(allPremiums.setScale(2, BigDecimal.ROUND_HALF_UP));
    }

    private static Map<String, BigDecimal> otherMethod(Map<String, BigDecimal> map, ConfCoefficient confCoefficient, String factorCodeName, double parseDouble) {
        Map<String, BigDecimal> map2 = new HashMap<>(16);
        map2.put(A, new BigDecimal(parseDouble));
        String replace = confCoefficient.getFactorValues().replace("[", "(").replace("]", ")");
        BigDecimal factorValues = BigDecimal.valueOf((Double) FormulaUtil.convertToCode(replace, map2));
        map.put(factorCodeName, factorValues);
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
    private ResultMsg premiumRule(FormulaBean formulaBean, Map<String, BigDecimal> map, String premiumDesign) {
        List<PremiumCalculateRule> premiumCalculateRules;
        keyPrcare = keyProdu + "prcare_" + formulaBean.getProductCode();
        //判断key是否存在
        Boolean flg6 = redisTemplate.hasKey(keyPrcare);
        if (flg6) {
            premiumCalculateRules = (List<PremiumCalculateRule>) redisTemplate.opsForValue().get(keyPrcare);
        } else {
            // 从数据库查询
            Example example2 = new Example(PremiumCalculateRule.class);
            example2.createCriteria().andEqualTo("productCode", formulaBean.getProductCode());
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
                return ResultUtils.warnMsg(PremiumFormulaEnum.IS_HAS_UNDEFINED_VARIABLE);
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
        return ResultUtils.success(premiumDesign);
    }


    /**
     * 计算主险保费
     *
     * @param formulaBean 保费计算视图
     * @param map         map 集合
     */
    private ResultMsg basicPremium(FormulaBean formulaBean, Map<String, BigDecimal> map) {
        BigDecimal basicPremium = BigDecimal.ZERO;
        InsuranceProgram insuranceProgram1 = insuranceProgramMapper.selectByProgramCode(formulaBean.getProgramCode());
        // 判断 保费计算类型 1-固定 3-浮动 4 固定+浮动
        switch (insuranceProgram1.getBasicPremiumType()) {
            case "1":
                basicPremium = BigDecimal.valueOf(insuranceProgram1.getBasicPremium());
                map.put(BASIC, basicPremium.multiply(formulaBean.getBasicCount()));
                break;
            case "3":
                //主险保费类型为浮动
                ResultMsg resultMsg = countBaseFloatPremium(formulaBean, basicPremium);
                if (resultMsg.getCode() != START_200) {
                    return resultMsg;
                }
                ResultVO premium = (ResultVO) resultMsg;
                basicPremium = (BigDecimal) premium.getData();
                map.put(BASIC, basicPremium.multiply(formulaBean.getBasicCount()));
                break;
            case "4":
                BigDecimal basicPremium3 = BigDecimal.ZERO;
                List<LiabilityLimitRela> liabilityLimitRelas = liabilityLimitRelaMapper.selectByProgramCode(formulaBean.getProgramCode());
                for (LiabilityLimitRela liabilityLimitRela : liabilityLimitRelas) {
                    basicPremium3 = basicPremium3.add(BigDecimal.valueOf(liabilityLimitRela.getPremium()));
                }
                ResultMsg resultMsg1 = countBaseFloatPremium(formulaBean, basicPremium3);
                if (resultMsg1.getCode() != START_200) {
                    return resultMsg1;
                }
                ResultVO premium2 = (ResultVO) resultMsg1;
                BigDecimal bigDecimal = (BigDecimal) premium2.getData();
                basicPremium = basicPremium3.add(bigDecimal);
                map.put(BASIC, basicPremium.multiply(formulaBean.getBasicCount()));
                break;
            default:
                return ResultUtils.warnMsg(ResultEnum.NO_DATA);
        }
        return ResultUtils.success(map);
    }

    /**
     * 赔付率因子
     *
     * @param formulaBean 保费计算视图
     * @return ResultMsg
     */
    private ResultMsg lossRationFactor(FormulaBean formulaBean, Map<String, BigDecimal> map) {

        // 判断是否续保
        if (RENENWAL_STATUS_0.equals(formulaBean.getRenenwalStatus())) {


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
        return ResultUtils.success(map);
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


    /**
     * 计算主险浮动方案保费
     *
     * @param formulaBean  保费计算视图
     * @param basicPremium 基础保费
     * @return ResultMsg
     */
    @SuppressWarnings("unchecked")
    private ResultMsg countBaseFloatPremium(FormulaBean formulaBean, BigDecimal basicPremium) {
        //主险保费类型为浮动
        List<FloatPremium> floatPremiums = formulaBean.getFloatPremiums();
        if (floatPremiums.size() > 0) {
            for (FloatPremium floatPremium : floatPremiums) {
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
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_FAILD_PROGRAMVALUES);
                } else {
                    if (floatPremium.getLiabilityLimitValuesCode() == null) {
                        basicPremium = basicPremium.add(BigDecimal.valueOf(floatRate.getRate() * floatPremium.getLiabilityLimitValues()));
                    } else {
                        basicPremium = basicPremium.add(BigDecimal.valueOf(floatRate.getRate() * liabilityLimitValues.getLiabilityLimitValues()));
                    }
                }
            }
        } else {
            return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_FLOATPREMIUMS);
        }
        return ResultUtils.success(basicPremium);
    }

    /**
     * 计算附加险浮动保费
     *
     * @param additionInsurance 附加险对象
     * @return ResultMsg
     */
    @SuppressWarnings("unchecked")
    private ResultMsg countAdditionFloatPremium(AdditionInsurance additionInsurance) {
        BigDecimal additionPremium = BigDecimal.ZERO;
        List<FloatPremium> additionFloatPremiums = additionInsurance.getAdditionFloatPremiums();
        if (additionFloatPremiums != null && additionFloatPremiums.size() > 0) {

            for (FloatPremium additionFloatPremium : additionFloatPremiums) {
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
                map2.put("progId", additionInsurance.getAtionProgramCode());
                map2.put("limitId", additionFloatPremium.getLimitCode());
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
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_FAILD_PROGRAMVALUES);
                } else {
                    if (additionFloatPremium.getLiabilityLimitValuesCode() == null) {
                        additionPremium = additionPremium.add(BigDecimal.valueOf(floatRate.getRate() * additionFloatPremium.getLiabilityLimitValues()));
                    } else {
                        additionPremium = additionPremium.add(BigDecimal.valueOf(floatRate.getRate() * liabilityLimitValues.getLiabilityLimitValues()));
                    }
                }

            }

        }
        return ResultUtils.success(additionPremium);
    }


    /**
     * 保费计算因子
     *
     * @param formulaBean 保费计算视图
     * @param map         map集合
     * @return ResultMsg
     */
    @SuppressWarnings("unchecked")
    private ResultMsg factorInfo(FormulaBean formulaBean, Map<String, BigDecimal> map) {
        List<FactorInfo> factorInfos = formulaBean.getFactorInfos();
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
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_FAILD_FACTORCODE);
                }
                //因子参与计算参数名
                String factorCodeName = factor.getFactorCodeName();

                keyConf = new StringBuilder().append(PRODUCT).append("conf_").append(factorInfo.getFactorRelaId()).toString();
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

                List<ConfCoefficient> confCoefficients = MyRedisUtils.excute(factorInfo.getFactorRelaId().toString(), keyConf, callback8);
                if (confCoefficients == null || confCoefficients.size() == 0) {
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_CONFCOEFFICIENTS);
                }

                if (!FormulaUtil.pandun(factorInfo.getStringFactor())) {
                    map.put(factorCodeName, BigDecimal.ZERO);
                } else {
                    double parseDouble = Double.parseDouble(factorInfo.getStringFactor());
                    map = switchMethod(map, confCoefficients, parseDouble, factorCodeName, factorInfo);
                }

                BigDecimal aDouble = map.get(factorCodeName);
                if (aDouble == null) {
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_FAIL_FACTOR_VALUES);
                }
            }

        }
        return ResultUtils.success(map);
    }


    /**
     * 计算附加险保费
     *
     * @param additionInsurance 附加险视图
     * @param map               map集合
     * @param count             循环次数
     * @return ResultMsg
     */
    @SuppressWarnings("unchecked")
    private ResultMsg additionPremium(AdditionInsurance additionInsurance, Map<String, BigDecimal> map, int count) {
        BigDecimal additionPremium;
        InsuranceProgram insuranceProgram1 = insuranceProgramMapper.selectByProgramCode(additionInsurance.getAtionProgramCode());
        // 判断 保费计算类型 0-固定 1-浮动 2 固定+浮动
        if (insuranceProgram1 != null) {
            switch (insuranceProgram1.getBasicPremiumType()) {
                case "1":
                    additionPremium = BigDecimal.valueOf(insuranceProgram1.getBasicPremium());
                    if (count == 0) {
                        map.put(ATION, additionPremium.multiply(additionInsurance.getAdditionCount()));
                    } else if (count == 1) {
                        map.put(ATION1, additionPremium.multiply(additionInsurance.getAdditionCount()));
                    } else {
                        map.put(ATION2, additionPremium.multiply(additionInsurance.getAdditionCount()));
                    }

                    break;
                case "3":
                    //主险保费类型为浮动
                    ResultMsg resultMsg = countAdditionFloatPremium(additionInsurance);
                    if (resultMsg.getCode() != START_200) {
                        return resultMsg;
                    }
                    ResultVO premium = (ResultVO) resultMsg;
                    additionPremium = (BigDecimal) premium.getData();
                    if (count == 0) {
                        map.put(ATION, additionPremium.multiply(additionInsurance.getAdditionCount()));
                    } else if (count == 1) {
                        map.put(ATION1, additionPremium.multiply(additionInsurance.getAdditionCount()));
                    } else {
                        map.put(ATION2, additionPremium.multiply(additionInsurance.getAdditionCount()));
                    }

                    break;
                case "4":
                    BigDecimal basicPremium3 = BigDecimal.ZERO;
                    List<LiabilityLimitRela> liabilityLimitRelas = liabilityLimitRelaMapper.selectByProgramCode(additionInsurance.getAtionProgramCode());
                    for (LiabilityLimitRela liabilityLimitRela : liabilityLimitRelas) {
                        basicPremium3 = basicPremium3.add(BigDecimal.valueOf(liabilityLimitRela.getPremium()));
                    }
                    ResultMsg resultMsg1 = countAdditionFloatPremium(additionInsurance);
                    if (resultMsg1.getCode() != START_200) {
                        return resultMsg1;
                    }
                    ResultVO premium2 = (ResultVO) resultMsg1;
                    BigDecimal bigDecimal = (BigDecimal) premium2.getData();
                    additionPremium = basicPremium3.add(bigDecimal);
                    if (count == 0) {
                        map.put(ATION, additionPremium.multiply(additionInsurance.getAdditionCount()));
                    } else if (count == 1) {
                        map.put(ATION1, additionPremium.multiply(additionInsurance.getAdditionCount()));
                    } else {
                        map.put(ATION2, additionPremium.multiply(additionInsurance.getAdditionCount()));
                    }
                    break;
                default:
                    return ResultUtils.warnMsg(ResultEnum.NO_DATA);
            }
        } else {
            if (count == 0) {
                map.put(ATION, BigDecimal.ZERO);
            } else if (count == 1) {
                map.put(ATION1, BigDecimal.ZERO);
            } else {
                map.put(ATION2, BigDecimal.ZERO);
            }

        }

        return ResultUtils.success(map);
    }


    /**
     * 字段校验
     *
     * @param formulaBean 保费计算视图
     * @return ResultMsg，字段无异返回保费计算公式
     */
    @SuppressWarnings("unchecked")
    private ResultMsg verifyFormulaBean(FormulaBean formulaBean) {
        //续保状态
        if (StringUtils.isEmpty(formulaBean.getRenenwalStatus())) {
            return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_RENENWALSTATUS);
        } else {
            if (!(RENENWAL_STATUS_0.equals(formulaBean.getRenenwalStatus()) || RENENWAL_STATUS_1.equals(formulaBean.getRenenwalStatus()))) {
                return ResultUtils.warnMsg(PremiumFormulaEnum.IS_FAILD_RENENWALSTATUS);
            }
            if (RENENWAL_STATUS_0.equals(formulaBean.getRenenwalStatus())) {
                if (StringUtils.isEmpty(formulaBean.getPolicyNo())) {
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_POLICYNO);
                }
                if (formulaBean.getRenewal() == null) {
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_RENEWAL);
                }
                if (StringUtils.isEmpty(formulaBean.getChannelNo())) {
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_CHANNELNO);
                }
                if (formulaBean.getLastPremium() == null) {
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_LASTPREMIUM);
                }
                if (StringUtils.isEmpty(formulaBean.getInscomp())) {
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_INSCOMP);
                }
            }

        }

        //主险参保份数
        if (formulaBean.getBasicCount() == null) {
            return ResultUtils.warnMsg(PremiumFormulaEnum.PARAMETER_IS_NULL_NUMBEROFPEOPLE);
        }
        //产品编码
        if (StringUtils.isEmpty(formulaBean.getProductCode())) {
            return ResultUtils.warnMsg(PremiumFormulaEnum.PARAMETER_IS_NULL_PRODUCT_CODE);
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
            return ResultUtils.warnMsg(PremiumFormulaEnum.FAILED_PRODUCT_CODE);
        }
        //主险方案编码
        if (StringUtils.isEmpty(formulaBean.getProgramCode())) {
            return ResultUtils.warnMsg(PremiumFormulaEnum.PARAMETER_IS_NULL_PROGRAM_CODE);
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
                return ResultUtils.warnMsg(PremiumFormulaEnum.IS_FAILD_PRODUCTPROGRAMCODE);
            }
            String basicPremiumType = insurancePrograms.get(0).getBasicPremiumType();
            //主险浮动保费
            if (TYPE_3.equals(basicPremiumType) || TYPE_4.equals(basicPremiumType)) {
                List<FloatPremium> floatPremiums = formulaBean.getFloatPremiums();
                if (floatPremiums == null || floatPremiums.size() == 0) {
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_FLOATPREMIUMS);
                } else {
                    for (FloatPremium floatPremium : floatPremiums) {
                        if (StringUtils.isEmpty(floatPremium.getLimitCode())) {
                            return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_LIMITID);
                        }
                        if (floatPremium.getLiabilityLimitValues() == null && floatPremium.getLiabilityLimitValuesCode() == null) {
                            return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_LIABILITYLIMITVALUESID);
                        }
                        if (floatPremium.getLiabilityLimitValuesCode() != null) {
                            LiabilityLimitValues liabilityLimitValues = liabilityLimitValuesMapper
                                    .selectByLiabilityLimitValuesCode(floatPremium.getLiabilityLimitValuesCode());
                            if (liabilityLimitValues == null) {
                                return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_LIMITVALUES_ID);
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
                return ResultUtils.warnMsg(PremiumFormulaEnum.PARAMETER_IS_NULL_FACTORINFOS);
            } else {
                if (insuranceFactorRelas.size() != factorInfos.size()) {
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_FAILD_FORMULA_FACTORS);
                } else {
                    for (FactorInfo factorInfo : factorInfos) {
                        if (factorInfo.getFactorCode() == null) {
                            return ResultUtils.warnMsg(PremiumFormulaEnum.PARAMETER_IS_NULL_FACTORRELAID);
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
                            return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_FACTOR_RELA_ID);
                        }
                        if (factorInfo.getFactorRelaId() == null) {
                            return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_FACTORRELA_ID);
                        }

                        if (StringUtils.isEmpty(factorInfo.getStringFactor())) {
                            return ResultUtils.warnMsg(PremiumFormulaEnum.PARAMETER_IS_NULL_STRING_FACTOR);
                        }
                    }
                }
            }
        } else {
            log.info("》》》》》》》》》》: 产品不含有保费因子");
        }

        if (formulaBean.getFactorInfos() == null) {
            return ResultUtils.warnMsg(PremiumFormulaEnum.PARAMETER_IS_NULL_FACTORINFOS);
        } else {
            List<FactorInfo> factorInfos = formulaBean.getFactorInfos();
            for (FactorInfo factorInfo : factorInfos) {
                if (factorInfo.getFactorCode() == null) {
                    return ResultUtils.warnMsg(PremiumFormulaEnum.PARAMETER_IS_NULL_FACTORRELAID);
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
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_FACTOR_RELA_ID);
                }
                if (factorInfo.getStringFactor() == null) {
                    return ResultUtils.warnMsg(PremiumFormulaEnum.PARAMETER_IS_NULL_STRING_FACTOR);
                }
            }
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
            return ResultUtils.warnMsg(PremiumFormulaEnum.IS_FAILD_PRODUCT_FORMULA);
        }

        //附加险集合
        List<AdditionInsurance> additionInsurances = formulaBean.getAdditionInsurances();
        if (additionInsurances != null && additionInsurances.size() > 0) {
            for (AdditionInsurance additionInsurance : additionInsurances) {
                if (additionInsurance.getAdditionCount() == null) {
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_ADDITONCOUNT);
                }
                if (StringUtils.isEmpty(additionInsurance.getAtionProgramCode())) {
                    return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_ADDITIONPROGRAMCODE);
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
                        return ResultUtils.warnMsg(PremiumFormulaEnum.IS_FAILD_PRODUCTPROGRAMCODE);
                    } else {
                        String basicPremiumType = insurancePrograms.get(0).getBasicPremiumType();
                        //主险浮动保费
                        if (TYPE_3.equals(basicPremiumType) || TYPE_4.equals(basicPremiumType)) {
                            List<FloatPremium> additionFloatPremiums = additionInsurance.getAdditionFloatPremiums();
                            if (additionFloatPremiums == null || additionFloatPremiums.size() == 0) {
                                return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_FLOATPREMIUMS);
                            } else {
                                for (FloatPremium additionFloatPremium : additionFloatPremiums) {
                                    if (StringUtils.isEmpty(additionFloatPremium.getLimitCode())) {
                                        return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_LIMITID);
                                    }
                                    if (additionFloatPremium.getLiabilityLimitValues() == null && additionFloatPremium.getLiabilityLimitValuesCode() == null) {
                                        return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_LIABILITYLIMITVALUESID);
                                    }
                                    if (additionFloatPremium.getLiabilityLimitValuesCode() != null) {
                                        LiabilityLimitValues liabilityLimitValues = liabilityLimitValuesMapper
                                                .selectByLiabilityLimitValuesCode(additionFloatPremium.getLiabilityLimitValuesCode());
                                        if (liabilityLimitValues == null) {
                                            return ResultUtils.warnMsg(PremiumFormulaEnum.IS_NULL_LIMITVALUES_ID);
                                        }
                                    }

                                }
                            }
                        }
                    }
                }
            }
        }


        return ResultUtils.success(premiumDesign);


    }
}