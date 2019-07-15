package com.situ.crm.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.situ.crm.mall.model.OrderModel;

public interface OrderMapper {

	

	int insert(OrderModel model);
	int delete(Object id);
	int deleteModel(OrderModel model);
	int update(OrderModel model) ;
	OrderModel selectById(Object id);
    OrderModel selectByCode(@Param(value="custCode")String custCode
    		,@Param(value="userCode")String userCode
    		,@Param(value="prodCode")String prodCode);
	List<OrderModel> selectAll(OrderModel model);
	List<OrderModel> selectModel(OrderModel model);
    int selectCount(OrderModel model);
	
}
