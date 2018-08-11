package com.jt.plt.product.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jt.plt.product.base.MyMapper;
import com.jt.plt.product.dto.ProductSimpleInfo;
import com.jt.plt.product.dto.channel.LifeProductDTO;
import com.jt.plt.product.dto.channel.Query;
import com.jt.plt.product.dto.lifeapp.ProductSimpleInfoDTO;
import com.jt.plt.product.entity.ProductInfo;

@Repository
public interface ProductInfoMapper extends MyMapper<ProductInfo> {
    /**
	 *
	 * @param productCode
	 * @return
	 * 描述：通过产品编码查询产品信息
	 */
	ProductInfo selectByProductCode(String productCode);
	List<ProductSimpleInfo> selectSimpleInfoList();
	List<String> selectProductByType(String productType);
	List<ProductSimpleInfoDTO> selectByChannelCode(String channelCode);
	List<LifeProductDTO> findProductListByCond(Query query);
	List<LifeProductDTO> findProductList();
}