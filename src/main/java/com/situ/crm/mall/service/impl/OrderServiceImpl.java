package com.situ.crm.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.mall.mapper.OrderMapper;
import com.situ.crm.mall.model.OrderModel;
import com.situ.crm.mall.service.IOrderService;
import com.situ.until.FmtEmpty;

@Service
public class OrderServiceImpl  implements IOrderService{
    
	@Autowired
	private OrderMapper mapper;
	
	@Override
	public int insert(OrderModel model) {
	
//		OrderModel em=selectByCode(model.getCustCode(),model.getuserCode(),model.getProdCode());
//		if(FmtEmpty.isEmpty(em)) {  //若em为空则说明数据库中无此条记录
			mapper.insert(model);
			return 0;
//		}
//		 return   1;
		
	}

	@Override
	public int delete(Object id) {
	
		return mapper.delete(id);
	}

	@Override
	public int deleteModel(OrderModel model) {
		// TODO Auto-generated method stub
		return mapper.deleteModel(model);
	}

	@Override
	public int update(OrderModel model) {
		// TODO Auto-generated method stub

		return mapper.update(model);
	}

	@Override
	public OrderModel selectById(Object id) {
		// TODO Auto-generated method stub
		return mapper.selectById(id);
	}


	@Override
	public List<OrderModel> selectAll(OrderModel model) {
		// TODO Auto-generated method stub
		return mapper.selectAll(model);
	}

	@Override
	public List<OrderModel> selectModel(OrderModel model) {
		// TODO Auto-generated method stub
		return mapper.selectModel(model);
	}

	@Override
	public int selectCount(OrderModel model) {
		// TODO Auto-generated method stub
		return mapper.selectCount(model);
	}

	@Override
	public OrderModel selectByCode(String custCode, String userCode,String prodCode) {

		return mapper.selectByCode(custCode, userCode,prodCode);
	}

}
