package com.situ.crm.mall.service;

import java.util.List;

import com.situ.crm.mall.model.OrderModel;





public interface IOrderService {

	
	int insert(OrderModel model);
	int delete(Object id);
	int deleteModel(OrderModel model);
	int update(OrderModel model) ;
	OrderModel selectById(Object id);
    OrderModel selectByCode(String custCode,String userCode,String prodCode);
	List<OrderModel> selectAll(OrderModel model);
	List<OrderModel> selectModel(OrderModel model);
    int selectCount(OrderModel model);
}
