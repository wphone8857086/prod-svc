package com.jt.plt.product.mapper;

import java.util.List;
import java.util.Map;

import com.jt.plt.product.entity.RiskInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.Page;
import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.entity.InsuranceLiability;
import com.jt.plt.product.vo.LiabilityListVO;

@Mapper
@Repository
public interface InsuranceLiabilityMapper extends MyMapper<InsuranceLiability> {
	
	List<LiabilityListVO> findLiabilityList(@Param("name")String name,@Param("creatBy")String creatBy);
	
	/**
	 * 责任级联险种
	 * @return
	 */
	List<InsuranceLiability> select2RiskInfo();
	
	/**
	 * 责任级联险种模糊查询
	 */
	List<InsuranceLiability> select2RiskInfoByLike(Map<String,String> map);
	
	/**
	 * 责任级联险种分页查询
	 * @return
	 */
	Page<InsuranceLiability> select2RiskInfoByPage();
	
	/**
	 * 多表联合查询通过责任id
	 * @return
	 */
	List<InsuranceLiability> selectAllInfoByID(String id);

	InsuranceLiability selectByInsuranceLiabilityCode(String liabilityCode);

}