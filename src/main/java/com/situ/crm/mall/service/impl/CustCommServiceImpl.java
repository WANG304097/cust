package com.situ.crm.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.mall.mapper.CustCommMapper;
import com.situ.crm.mall.model.CustCommModel;
import com.situ.crm.mall.service.ICustCommService;
import com.situ.until.FmtEmpty;

@Service
public class CustCommServiceImpl  implements ICustCommService{
    
	@Autowired
	private CustCommMapper mapper;
	
	@Override
	public int insert(CustCommModel model) {
	
//		CustCommModel em=selectByCode(model.getCustCode(),model.getUserCode());
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
	public int deleteModel(CustCommModel model) {
		// TODO Auto-generated method stub
		return mapper.deleteModel(model);
	}

	@Override
	public int update(CustCommModel model) {
		// TODO Auto-generated method stub

		return mapper.update(model);
	}

	@Override
	public CustCommModel selectById(Object id) {
		// TODO Auto-generated method stub
		return mapper.selectById(id);
	}


	@Override
	public List<CustCommModel> selectAll(CustCommModel model) {
		// TODO Auto-generated method stub
		return mapper.selectAll(model);
	}

	@Override
	public List<CustCommModel> selectModel(CustCommModel model) {
		// TODO Auto-generated method stub
		return mapper.selectModel(model);
	}

	@Override
	public int selectCount(CustCommModel model) {
		// TODO Auto-generated method stub
		return mapper.selectCount(model);
	}

	@Override
	public CustCommModel selectByCode(String custCode, String userCode) {

		return mapper.selectByCode(custCode, userCode);
	}

}
