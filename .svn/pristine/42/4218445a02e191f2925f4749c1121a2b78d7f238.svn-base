package com.jt.plt.product.mapper;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.entity.LiabilityLimitValues;

import tk.mybatis.mapper.provider.SpecialProvider;

import java.util.List;

@Repository
public interface LiabilityLimitValuesMapper extends MyMapper<LiabilityLimitValues> {
	
	@Options(useGeneratedKeys = true, keyProperty = "liabilityLimitValuesId")
    @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
	@Override
    int insert(LiabilityLimitValues liabilityLimitValues);
	
	@Options(useGeneratedKeys = true, keyProperty = "liabilityLimitValuesId")
    @InsertProvider(type = SpecialProvider.class, method = "dynamicSQL")
	@Override
    int insertList(List<LiabilityLimitValues> liabilityLimitValues);

	LiabilityLimitValues selectByLiabilityLimitValuesCode(String liabilityLimitValuesCode);
	/**
	 * 
	 * @param min
	 * @param max
	 * @return
	 * 描述：描述：通过区间查找区间中包含的限额值ID 区间：()
	 */
	List<LiabilityLimitValues> findLimitValues1(@Param("min") Double min, @Param("max") Double max, @Param("liabilityLimitId") String liabilityLimitId);
	/**
	 *
	 * @param min
	 * @param max
	 * @return
	 * 描述：描述：通过区间查找区间中包含的限额值ID 区间：(]
	 */
	List<LiabilityLimitValues> findLimitValues2(@Param("min") Double min, @Param("max") Double max, @Param("liabilityLimitId") String liabilityLimitId);
	/**
	 *
	 * @param min
	 * @param max
	 * @return
	 * 描述：描述：通过区间查找区间中包含的限额值ID 区间：[)
	 */
	List<LiabilityLimitValues> findLimitValues3(@Param("min") Double min, @Param("max") Double max, @Param("liabilityLimitId") String liabilityLimitId);
	/**
	 *
	 * @param min
	 * @param max
	 * @return
	 * 描述：描述：通过区间查找区间中包含的限额值ID 区间：[]
	 */
	List<LiabilityLimitValues> findLimitValues4(@Param("min") Double min, @Param("max") Double max, @Param("liabilityLimitId") String liabilityLimitId);
}