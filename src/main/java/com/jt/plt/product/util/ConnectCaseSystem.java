package com.jt.plt.product.util;/**
 * @Description:
 * @Auther: wephone
 * @Date: 2018/5/30 10:06
 * @ModifiedDate：
 * @Copyright:江泰保险股份有限公司
 */


import com.fasterxml.jackson.core.type.TypeReference;
import com.jt.plt.product.entity.calPremium.CaseSystemResult;
import lombok.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class ConnectCaseSystem {
    //预生产地址：http://10.30.131.76:6062/jtsearch/a/ajcxtb/query?
    //生产地址：http://172.16.7.141:6062/jtsearch/a/ajcxtb/query?

    Log log = LogFactory.getLog(getClass());
    private final String PreURL = "http://172.16.7.141:6062/jtsearch/a/ajcxtb/query?";
    private final UUID uuid;
    private final String policyNo;    //保单号
    private  String via ;              //渠道代码
    private  String inscomp ;         //出单公司编号



    private @Getter
    StringBuffer parameterString = new StringBuffer();

    public ConnectCaseSystem(UUID uuid, String policyNo, String via, String inscomp) {
        this.uuid = uuid;
        this.policyNo = policyNo;
        this.via = via;
        this.inscomp = inscomp;
    }


    //返回接收到的json数据
    public List<CaseSystemResult> connectUrl() throws IOException {
        List<CaseSystemResult> caseSystemResults = new ArrayList<>();

        log.info("开始从案件系统接收案件信息");
        String strURL = PreURL + parameterString;
        strURL.replace(" ", "");
        BufferedReader buffer = null;
        HttpURLConnection urlcon = null;
        StringBuffer result = new StringBuffer();
        URL url = new URL(strURL);
        urlcon = (HttpURLConnection) url.openConnection();
        System.out.println("调用外部接口，URL：" + url);
        urlcon.connect();//获取连接
        urlcon.setConnectTimeout(10000);
        urlcon.setReadTimeout(10000);

        System.out.println("链接成功");
        InputStream inS = urlcon.getInputStream();

        buffer = new BufferedReader(new InputStreamReader(inS, "UTF-8"));
        String temp = null;
        while ((temp = buffer.readLine()) != null) {
            result.append(temp);
        }
        if (buffer != null) {
            buffer.close();
            urlcon.disconnect();
        }
        log.info("获取到数据结束.");
        log.info("数据为：" + result);
         caseSystemResults = JsonUtil.json2Object(result.toString(), new TypeReference<List<CaseSystemResult>>() {
			});
        return caseSystemResults;
    }


    //设置get请求参数串
    public void setParameterString() {
        parameterString.append("policyNo=" + policyNo.trim());
        parameterString.append("&via=" + via);
        parameterString.append("&inscomp=" + inscomp);
        parameterString.append("&uuid=" + uuid);
    }

     private BigDecimal calculateLossRationFactor(CaseSystemResult caseSystemResult) {
	    log.info(caseSystemResult);
		if(!caseSystemResult.getEndcasetype().equals("")){
			if ("00".equals(caseSystemResult.getEndcasetype())) {
				return new BigDecimal(caseSystemResult.getEndcasetype());
			} else {
				return new BigDecimal(0);
			}
		}else {
			if(caseSystemResult.getFixamount() != 0) {
				return new BigDecimal(caseSystemResult.getFixamount());
			}else {
					if (caseSystemResult.getEvelamount() != 0 ) {
						return new BigDecimal(caseSystemResult.getEvelamount());
					}else {
						if (caseSystemResult.getLoss() != 0 ) {
							return new BigDecimal(caseSystemResult.getLoss());
						}else {
							return new BigDecimal(0);
						}
					}
				  }
		}
	}

	//sum Loss
	public BigDecimal calculateSumLossRationFactor( List<CaseSystemResult> caseSystemResults){

		BigDecimal sum= new BigDecimal(0);
		for (CaseSystemResult caseSystemResult : caseSystemResults) {
			sum = sum.add(calculateLossRationFactor(caseSystemResult));
			System.out.println("每次sum的值:"+sum);
		}
		return sum;
	}



}
