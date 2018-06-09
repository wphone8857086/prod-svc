package com.prod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jt.plt.product.ProductService4Application;
import com.jt.plt.product.entity.FloatRate;
import com.jt.plt.product.entity.calPremium.FormulaBean;
import com.jt.plt.product.enums.ResultEnum;
import com.jt.plt.product.mapper.FloatRateMapper;
import com.jt.plt.product.service.FormulaService;
import com.jt.plt.product.util.JsonUtil;
import com.jt.plt.product.util.ResultMsg;
import com.jt.plt.product.util.ReturnCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保费计算单元测试
 *
 * @author wephone
 * @create 2018-04-27 17:19
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductService4Application.class)
@ComponentScan
public class PremiumTest {

    /**
     * 1、参保人数变为份数 count     √
     * 2、把浮动方案id 提取出来      √
     * 3、去循环                     √
     * 4、常量单独提取 便于日后优化  √
     * 5、可读性
     */

    private final Logger log = LoggerFactory.getLogger(PremiumTest.class);

    @Autowired
    FormulaService formulaService;

    @Autowired
    FloatRateMapper floatRateMapper;

    /**
     *
     */
    @Test
    public void test0() {
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("progId", "WF_51");
        map2.put("limitId", "WF责任限额7");
        map2.put("limitValues", 500000.0);
        FloatRate floatRate = floatRateMapper.findFloatRate(map2);
        System.out.println(floatRate.getRate());
    }



    /**
     * 单_保费测试
     */
    @Test
    public void test3() {
        long startTime = System.currentTimeMillis();

        String str = "{\n" +
                "\"renenwalStatus\":\"1\",    \n" +
                "\"basicCount\":2,\n" +
                "\"productCode\":\"WF-001001\",\n" +
                "\"programCode\":\"WF18FA570065\",\n" +
                "\"floatPremiums\":[\n" +
                "{\n" +
                "\"limitCode\":\"WF责任限额7\",\n" +
                "\"liabilityLimitValues\":\"500000\"\n" +
                "}\n" +
                "],\n" +
                "\"factorInfos\":[\n" +
                "{\n" +
                "\"factorCode\":231,\n" +
                "\"stringFactor\":\"8000\"\n" +
                "},\n" +
                "{\n" +
                "\"factorCode\":232,\n" +
                "\"stringFactor\":\"1001\"\n" +
                "},\n" +
                "{\n" +
                "\"factorCode\":233,\n" +
                "\"stringFactor\":\"1004\"\n" +
                "},\n" +
                "{\n" +
                "\"factorCode\":234,\n" +
                "\"stringFactor\":\"1.2\"\n" +
                "},\n" +
                "{\n" +
                "\"factorCode\":235,\n" +
                "\"stringFactor\":\"15\"\n" +
                "},\n" +
                "{\n" +
                "\"factorCode\":236,\n" +
                "\"stringFactor\":\"5\"\n" +
                "},\n" +
                "{\n" +
                "\"factorCode\":237,\n" +
                "\"stringFactor\":\"1\"\n" +
                "},\n" +
                "{\n" +
                "\"factorCode\":238,\n" +
                "\"stringFactor\":\"1003\"\n" +
                "},\n" +
                "{\n" +
                "\"factorCode\":239,\n" +
                "\"stringFactor\":\"0.95\"\n" +
                "}\n" +
                "],\n" +
                "\"additionInsurances\":[\n" +
                "{\n" +
                "\"additionCount\":1,\n" +
                "\"ationProgramCode\":\"WF18FA779572007\"\n" +
                "},\n" +
                "{\n" +
                "\"additionCount\":1,\n" +
                "\"ationProgramCode\":\"WF18FA779572007\"\n" +
                "}\n" +
                "]\n" +
                "}";
        FormulaBean formulaBean = null;
        try {
            formulaBean = JsonUtil.json2Object(str, FormulaBean.class);
            if (formulaBean == null) {
                log.error(ResultEnum.PARAM_ERROR.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(ResultEnum.PARAM_ERROR.getMessage());
        }
        ResultMsg resultMsg = formulaService.countPremium(formulaBean);
        System.out.println(resultMsg.getMsg());
        long endTime = System.currentTimeMillis();
        System.out.println("当前程序耗时：" + (endTime - startTime) + "ms");

        // ******总保费******：5496.30

        // old  当前程序耗时：555ms
        // new  当前程序耗时：577ms

    }

    /**
     * 集合保费测试
     */
    @Test
    public void test4() {
        long startTime = System.currentTimeMillis();
        String str = "[{\"count\": 1,\n" +
                "\"productCode\": \"WF-001002\",\n" +
                "\"basicPremium\":2000,\n" +
                "\"progId\": \"WF_51\",\n" +
                "\"floatPremiums\": [{\n" +
                "\"limitId\":\"WF责任限额7\",\n" +
                "\"liabilityLimitValues\":500000    \n" +
                "},\n" +
                "{\"limitId\":\"WF责任限额id\",\n" +
                "    \"liabilityLimitValuesId\": 171},\n" +
                "{\"limitId\":\"WF责任限额id\",\n" +
                "    \"liabilityLimitValuesId\": 170},\n" +
                "{\"limitId\":\"WF责任限额6\",\n" +
                "    \"liabilityLimitValuesId\": 174}],\n" +
                "\"factorInfos\": [{\n" +
                "\"factorRelaId\": 1001,\n" +
                "\"stringFactor\": \"30\"\n" +
                "},\n" +
                "{\n" +
                "\"factorRelaId\": 1004,\n" +
                "\"stringFactor\": \"60\"\n" +
                "},{\n" +
                "\"factorRelaId\": 1005,\n" +
                "\"stringFactor\": \"150\"\n" +
                "}],\n" +
                "\"additionPremium\":1000,\n" +
                "\"ationProgId\": \"WF_51\",\n" +
                "\"additionFloatPremiums\":[\n" +
                "{\n" +
                "    \"limitId\":\"WF责任限额7\",\n" +
                "\"liabilityLimitValues\":500000    \n" +
                "},\n" +
                "{\"limitId\":\"WF责任限额id\",\n" +
                "    \"liabilityLimitValuesId\": 171},\n" +
                "{\"limitId\":\"WF责任限额id\",\n" +
                "    \"liabilityLimitValuesId\": 170}],\n" +
                "\"additionFactorInfos\":[{\n" +
                "\"factorRelaId\":2051,\n" +
                "\"stringFactor\":6500\n" +
                "},{\"factorRelaId\":2052,\n" +
                "\"stringFactor\":1001}]\n" +
                "},{\"count\": 1,\n" +
                "\"productCode\": \"WF-001002\",\n" +
                "\"basicPremium\":2000,\n" +
                "\"progId\": \"WF_51\",\n" +
                "\"floatPremiums\": [{\n" +
                "\"limitId\":\"WF责任限额7\",\n" +
                "\"liabilityLimitValues\":500000    \n" +
                "},\n" +
                "{\"limitId\":\"WF责任限额id\",\n" +
                "    \"liabilityLimitValuesId\": 171},\n" +
                "{\"limitId\":\"WF责任限额id\",\n" +
                "    \"liabilityLimitValuesId\": 170},\n" +
                "{\"limitId\":\"WF责任限额6\",\n" +
                "    \"liabilityLimitValuesId\": 174}],\n" +
                "\"factorInfos\": [{\n" +
                "\"factorRelaId\": 1001,\n" +
                "\"stringFactor\": \"30\"\n" +
                "},\n" +
                "{\n" +
                "\"factorRelaId\": 1004,\n" +
                "\"stringFactor\": \"60\"\n" +
                "},{\n" +
                "\"factorRelaId\": 1005,\n" +
                "\"stringFactor\": \"150\"\n" +
                "}],\n" +
                "\"additionPremium\":1000,\n" +
                "\"ationProgId\": \"WF_51\",\n" +
                "\"additionFloatPremiums\":[\n" +
                "{\n" +
                "    \"limitId\":\"WF责任限额7\",\n" +
                "\"liabilityLimitValues\":500000    \n" +
                "},\n" +
                "{\"limitId\":\"WF责任限额id\",\n" +
                "    \"liabilityLimitValuesId\": 171},\n" +
                "{\"limitId\":\"WF责任限额id\",\n" +
                "    \"liabilityLimitValuesId\": 170}],\n" +
                "\n" +
                "\"additionFactorInfos\":[{\n" +
                "\"factorRelaId\":2051,\n" +
                "\"stringFactor\":6500\n" +
                "},{\"factorRelaId\":2052,\n" +
                "\"stringFactor\":1001}]\n" +
                "}]";
        List<FormulaBean> formulaBeans = new ArrayList<>();
        try {
            formulaBeans = JsonUtil.json2Object(str, new TypeReference<List<FormulaBean>>() {
            });
            if (formulaBeans == null || formulaBeans.size() == ReturnCode.CODE0) {
                log.error(ResultEnum.PARAM_ERROR.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(ResultEnum.PARAM_ERROR.getMessage());
        }
        System.out.println(formulaService.countPremiums(formulaBeans).getMsg());
        long endTime = System.currentTimeMillis();
        System.out.println("当前程序耗时：" + (endTime - startTime) + "ms");

        // ******总保费******：10992.60  new
        // 当前程序耗时：714ms           new
        // 当前程序耗时：647ms          new+1
        // 当前程序耗时：674ms          reids
    }

    @Test
    public void test10() {
        Map<String, Object> map2 = new HashMap<>(16);
        map2.put("programCode", "WF18FA570065");
        map2.put("limitCode", "WF责任限额id");
        map2.put("limitValues",10000);
        FloatRate floatRate = floatRateMapper.findFloatRate(map2);
        System.out.println(floatRate.getRate());
    }

}
