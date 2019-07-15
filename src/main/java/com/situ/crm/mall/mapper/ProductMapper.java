package com.situ.crm.mall.mapper;

import java.util.List;

import com.situ.crm.mall.model.ProductModel;



public interface ProductMapper {

	int insert(ProductModel model);
	int delete(Object id);
	int deleteModel(ProductModel model);
	int update(ProductModel model) ;
	ProductModel selectById(Object id);
    ProductModel selectByCode(String code);
	List<ProductModel> selectAll(ProductModel model);
	List<ProductModel> selectModel(ProductModel model);
    int selectCount(ProductModel model);
	
}
