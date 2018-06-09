package com.jt.plt.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.entity.FloatRate;

@Repository
public interface FloatRateMapper extends MyMapper<FloatRate> {

    FloatRate findFloatRate(Map<String, Object> map);
    /**
     * 
     * @param programCode
     * @return
     * 描述：通过方案编码查看费率
     */
    List<FloatRate> selectByProgramCode(String programCode);
    /**
     * 
     * @param programCode
     * @return
     * 描述：通过方案和限额编码查看费率
     */
    List<FloatRate> selectByprogramCodeAndLimitCode(@Param("progCode")String progCode,@Param("limitCode")String limitCode);
}