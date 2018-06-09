package com.jt.plt.product.controller;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jt.plt.product.dto.Factor;
import com.jt.plt.product.dto.InusureCompanyDTO;
import com.jt.plt.product.dto.ProductInfoDTO;
import com.jt.plt.product.dto.ResultDTO;
import com.jt.plt.product.dto.program.ProgramInfoBean;
import com.jt.plt.product.entity.calPremium.FormulaBean;
import com.jt.plt.product.enums.ResultEnum;
import com.jt.plt.product.service.FormulaService;
import com.jt.plt.product.service.api.ProductInfoServiceApi;
import com.jt.plt.product.util.JsonUtil;
import com.jt.plt.product.util.ResultMsg;
import com.jt.plt.product.util.ResultUtils;
import com.jt.plt.product.util.ReturnCode;


@RestController
@RequestMapping(value = "")
public class ProdcutInfoController {

    @Autowired
    FormulaService formulaService;

    @PostMapping(value="/calpremiumsvc", produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody ResultMsg calPremium(@RequestBody FormulaBean formulaBean){
        return formulaService.countPremium(formulaBean);
    }

    @PostMapping("/premium")
    public ResultMsg premium(@RequestBody String str) {
		FormulaBean formulaBean= null;
		try {
			formulaBean = JsonUtil.json2Object(str, FormulaBean.class);
			if(formulaBean == null || str ==  null){
				return ResultUtils.warnMsg(ResultEnum.PARAM_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//log.error(ExceptionMsgUtil.getExceptionMsg(e));
			return ResultUtils.warnMsg(ResultEnum.PARAM_ERROR);
		}
		return formulaService.countPremium(formulaBean);
	}

    @PostMapping("/premiums")
    public ResultMsg premiums(@RequestBody String str) {
        List<FormulaBean> formulaBeans = new ArrayList<>();
        try {
            formulaBeans = JsonUtil.json2Object(str, new TypeReference<List<FormulaBean>>() {
            });
            if (formulaBeans == null || formulaBeans.size() == ReturnCode.CODE0) {
                return ResultUtils.warnMsg(ResultEnum.PARAM_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.warnMsg(ResultEnum.PARAM_ERROR);
        }
        return formulaService.countPremiums(formulaBeans);
    }


    @Autowired
    private ProductInfoServiceApi productInfoServiceApi;

    /**
     * 查询产品详情示例
     * @param id 产品id
     * @return
     */
    @GetMapping(value="/productdetailsvc", produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody
    ResultDTO<ProductInfoDTO> svc(@RequestParam("id") String id) {
        ProductInfoDTO piDTO = productInfoServiceApi.findProductInfo(id);
        if(piDTO==null) {
        	return new ResultDTO(ResultEnum.NO_DATA);
        }
        List<Factor> fList = productInfoServiceApi.findFactorInfo(id);
        if(fList!=null&&fList.size()>0) {
            piDTO.setHasFactors("1");
            piDTO.setFactorList(fList);
        }else {
            piDTO.setHasFactors("0");
        }
        List<ProgramInfoBean> programList = productInfoServiceApi.findProgramInfo(id);
        boolean mark = false;
        for (ProgramInfoBean programInfoBean : programList) {
			if(StringUtils.equals(programInfoBean.getRiskFlag(), "A")) {
				mark = true;
				break;
			}
		}
        if(mark) {
        	piDTO.setHasAttachRisk(ReturnCode.STATUS_CODE_1);
        }else {
        	piDTO.setHasAttachRisk(ReturnCode.STATUS_CODE_0);
        }
        piDTO.setProgList(productInfoServiceApi.findProgramInfo(id));
        List<InusureCompanyDTO> icList = productInfoServiceApi.findInusureCompanyInfo(id);
        if(icList!=null&&icList.size()>0) {
        	piDTO.setInusureCompanyList(icList);
        }
        return new ResultDTO(ResultEnum.SUCCESS,piDTO);
    }
    /**
     * 
     * @param
     * @return
     * 描述：方案列表
     */
    @GetMapping(value="/product_prog/{id}",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody ResultDTO<List<ProgramInfoBean>> getProject(@PathVariable String id) {
    	List<ProgramInfoBean> progDTOList = productInfoServiceApi.findProgramInfo(id);
    	return new ResultDTO(ResultEnum.SUCCESS,progDTOList);
    }
    /**
     * 
     * @param
     * @return
     * 描述：因子列表
     */
    @GetMapping(value="/product/factor/{id}",produces={MediaType.APPLICATION_JSON_UTF8_VALUE})
    public @ResponseBody ResultDTO<List<Factor>> getFactor(@PathVariable("id") String id) {
    	List<Factor> factorList = productInfoServiceApi.findFactorInfo(id);
    	return new ResultDTO(ResultEnum.SUCCESS,factorList);
    }
}
