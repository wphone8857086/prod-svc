package com.jt.plt.product.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jt.plt.product.dto.Factor;
import com.jt.plt.product.dto.ProductInfoDTO;
import com.jt.plt.product.dto.ProductSimpleInfo;
import com.jt.plt.product.dto.ResultDTO;
import com.jt.plt.product.dto.channel.Query;
import com.jt.plt.product.dto.lifeapp.RiskParam;
import com.jt.plt.product.dto.program.ProgramInfoBean;
import com.jt.plt.product.dto.rule.RuleDTO;
import com.jt.plt.product.entity.calPremium.CountPremiumBean;
import com.jt.plt.product.entity.calPremium.FormulaBean;
import com.jt.plt.product.entity.calPremium.FormulaDTO;
import com.jt.plt.product.enums.ResultEnum;
import com.jt.plt.product.service.FormulaService;
import com.jt.plt.product.service.api.ProductInfoServiceApi;
import com.jt.plt.product.util.GenericListResponse;
import com.jt.plt.product.util.JsonUtil;
import com.jt.plt.product.util.ResultMsg;
import com.jt.plt.product.util.ReturnCode;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "产品中心服务调用接口", tags = {"产品中心服务调用接口"})
@RestController
public class ProdcutInfoController {
    private final static class ResultData extends ResultDTO<ProductInfoDTO> {
    }

    ;

    private final static class ResultProg extends ResultDTO<List<ProgramInfoBean>> {
    }

    ;

    private final static class ResultFactor extends ResultDTO<List<Factor>> {
    }

    ;

    private final static class ResultList extends ResultDTO<List<ProductSimpleInfo>> {
    }

    ;
    @Autowired
    private ProductInfoServiceApi productInfoServiceApi;
    @Autowired
    FormulaService formulaService;

    @ApiIgnore
    @PostMapping(value = "/calpremiumsvc", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody
    ResultMsg calPremium(@RequestBody FormulaBean formulaBean) {
        return formulaService.countPremium(formulaBean);
    }

    @ApiIgnore
    @PostMapping(value = "/calpremiumsvcs", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody
    ResponseEntity<?> calPremiums(@RequestBody List<FormulaBean> formulaBeans) {
        return formulaService.countPremiums(formulaBeans);
    }

    /**
     * 责任险保费计算
     *
     * @param countPremiumBeans
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/calpremiums" ,produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> premium(@RequestBody List<CountPremiumBean> countPremiumBeans) {
        return formulaService.newcountPremiums(countPremiumBeans);
    }

    /**
     * 寿险保费计算
     *
     * @param
     * @return
     */
    @ApiIgnore
    @ResponseBody
    @PostMapping(value = "/callifepremiums" ,produces ={MediaType.APPLICATION_JSON_UTF8_VALUE}  )
    public ResponseEntity<?> callifepremiums(@RequestBody List<FormulaDTO> formulaDTOS) {
        return formulaService.countLifePremiums(formulaDTOS);
    }

    @ApiIgnore
    @PostMapping("/testcallifepremiums")
    public ResponseEntity<?> testcallifepremiums(@RequestBody String data) {
        List<FormulaDTO> formulaDTOS = new ArrayList<>();
        try {
            formulaDTOS = JsonUtil.json2Object(data, new TypeReference<List<FormulaDTO>>() {
            });
            if (formulaDTOS == null || formulaDTOS.size() == ReturnCode.CODE0) {
                return GenericListResponse.ng(ResultEnum.PARAM_ERROR.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return GenericListResponse.ng(ResultEnum.PARAM_ERROR.getMessage());
        }
        return formulaService.countLifePremiums(formulaDTOS);
    }


    /**
     * 查询产品详情示例
     *
     * @param id 产品id
     * @return
     */
    @ApiOperation(value = "查询产品详情", notes = "通过产品编码查询产品详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品编码", required = true, dataType = "String", paramType = "query")
    })
    @ApiResponses({@ApiResponse(code = 200, response = ResultData.class, message = "险种列表信息")})
    @GetMapping(value = "/productdetailsvc", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody
    ResponseEntity<?> svc(@RequestParam("id") String id) {
        return productInfoServiceApi.findProductDetails(id);
    }

    /**
     * @param
     * @return 描述：方案列表
     */
    @ApiOperation(value = "查询方案详情", notes = "通过产品编码查询产品下方案详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品编码", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponses({@ApiResponse(code = 200, response = ResultProg.class, message = "方案详情信息")})
    @GetMapping(value = "/product_prog/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody
    ResultDTO<List<ProgramInfoBean>> getProject(@PathVariable String id) {
        List<ProgramInfoBean> progDTOList = productInfoServiceApi.findProgramInfo(id);
        return new ResultDTO<>(ResultEnum.SUCCESS, progDTOList);
    }

    /**
     * @param
     * @return 描述：因子列表
     */
    @ApiOperation(value = "查询产品因子详情", notes = "通过产品编码查询产品因子详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品编码", required = true, dataType = "String", paramType = "path")
    })
    @GetMapping(value = "/product_factor/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ApiResponses({@ApiResponse(code = 200, response = ResultFactor.class, message = "因子详情信息")})
    public @ResponseBody
    ResultDTO<List<Factor>> getFactor(@PathVariable("id") String id) {
        List<Factor> factorList = productInfoServiceApi.findFactorInfo(id);
        return new ResultDTO<>(ResultEnum.SUCCESS, factorList);
    }

    @ApiOperation(value = "查询产品列表", notes = "查询产品列表")
    @ApiResponses({@ApiResponse(code = 200, response = ResultList.class, message = "产品列表")})
    @GetMapping(value = "/product/list", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResultDTO<List<ProductSimpleInfo>> getProductList() {
        List<ProductSimpleInfo> productList = productInfoServiceApi.getProductList();
        if (productList == null) {
            return new ResultDTO<>(ResultEnum.NO_DATA);
        } else {
            return new ResultDTO<>(ResultEnum.SUCCESS, productList);
        }

    }

    @ApiOperation(value = "销售属性查询", notes = "销售属性查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "产品编码", required = true, dataType = "String", paramType = "path")
    })
    @GetMapping(value = "/product_rule/{id}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResultDTO<List<RuleDTO>> getSalesRule(@PathVariable("id") String productCode) {
        if (StringUtils.isEmpty(productCode)) {
            return new ResultDTO<List<RuleDTO>>(ResultEnum.PARAMETER_IS_NULL);
        }
        List<RuleDTO> list = productInfoServiceApi.findruleByProductCode(productCode);
        if (list == null) {
            return new ResultDTO<List<RuleDTO>>(ResultEnum.NO_DATA);

        }
        return new ResultDTO<List<RuleDTO>>(ResultEnum.SUCCESS, list);
    }

    @ApiOperation(value = "通过销售属性查询对应方案编码集合", notes = "通过销售属性查询对应方案集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "salesRules", value = "销售规则（入参格式：男,[20-40)）", required = true, dataType = "String", paramType = "query")
    })
    @PostMapping(value = "/product_prog", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResultDTO<List<String>> getProgramBySalesRules(String salesRules) {
        if (StringUtils.isEmpty(salesRules)) {
            return new ResultDTO<List<String>>(ResultEnum.PARAMETER_IS_NULL);
        }
        List<String> list = productInfoServiceApi.findProgramByRule(salesRules);
        if (list == null) {
            return new ResultDTO<List<String>>(ResultEnum.NO_DATA);
        }
        return new ResultDTO<>(ResultEnum.SUCCESS, list);
    }
    /**
     * 
     * @param channelCode
     * @return
     * 描述：通过渠道编码查询该渠道产品列表
     */
    @GetMapping(value="/product_channel/{id}",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findProductByChannelCode(@PathVariable("id") String channelCode){
    	return productInfoServiceApi.findProductByChannelCode(channelCode);
	}
    /**
     * 
     * @return
     * 描述：查询寿险所有承保公司及其承保的产品（主险信息）
     */
    @GetMapping(value="/product_main",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findMainRiskOfProduct(){
    	return productInfoServiceApi.findMainProduct();
    }
    /**
     * 
     * @return
     * 描述：查询寿险所有承保公司及其承保的产品（主险信息）
     */
    @PostMapping(value="/product_add",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAddRiskOfProductCode(@RequestBody List<String> list){
    	return productInfoServiceApi.findAddProduct(list);
    }
    /**
     * 
     * @return
     * 描述：通过产品和险种编码查询险种的责任
     */
    @PostMapping(value="/product_liability",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findproductOfLiabilityByCode(@RequestBody List<RiskParam> list){
    	return productInfoServiceApi.findLiabilityByCode(list);
    }
    /**
     * 
     * @return
     * 描述：查询寿险所有承保公司及其承保的产品（主险信息）
     */
    @PostMapping(value="/product_list",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findProductOfList(@RequestBody(required = false) Query query){
    	return productInfoServiceApi.findProductListByCond(query);
    }
    /**
     * 
     * @return
     * 描述：查询所有的保险公司
     */
    @GetMapping(value="/product_company",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllCompany(){
    	return productInfoServiceApi.findAllCompany();
    }

	@GetMapping(value = "/areainfo")
    public ResponseEntity<?>  area(){
        return productInfoServiceApi.selectArea();
    }
}
