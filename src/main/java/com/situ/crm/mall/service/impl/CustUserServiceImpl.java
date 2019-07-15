package com.situ.crm.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.mall.mapper.CustUserMapper;
import com.situ.crm.mall.model.CustUserModel;
import com.situ.crm.mall.service.ICustUserService;
import com.situ.until.FmtEmpty;
import com.situ.until.MD5;
@Service
public class CustUserServiceImpl  implements ICustUserService{
    
	@Autowired
	private CustUserMapper mapper;
	
	@Override
	public int insert(CustUserModel model) {
	
		CustUserModel em=selectByCode(model.getCustCode());
		if(FmtEmpty.isEmpty(em)) {  //若em为空则说明数据库中无此条记录
			mapper.insert(model);
			return 0;
		}
		 return   1;
		
	}

	@Override
	public int delete(Object id) {
	
		return mapper.delete(id);
	}

	@Override
	public int deleteModel(CustUserModel model) {
		// TODO Auto-generated method stub
		return mapper.deleteModel(model);
	}

	@Override
	public int update(CustUserModel model) {
		// TODO Auto-generated method stub

		return mapper.update(model);
	}

	@Override
	public CustUserModel selectById(Object id) {
		// TODO Auto-generated method stub
		return mapper.selectById(id);
	}

	@Override
	public CustUserModel selectByCode(String code) {
		// TODO Auto-generated method stub
		return mapper.selectByCode(code);
	}

	@Override
	public List<CustUserModel> selectAll(CustUserModel model) {
		// TODO Auto-generated method stub
		return mapper.selectAll(model);
	}

	@Override
	public List<CustUserModel> selectModel(CustUserModel model) {
		// TODO Auto-generated method stub
		return mapper.selectModel(model);
	}

	@Override
	public int selectCount(CustUserModel model) {
		// TODO Auto-generated method stub
		return mapper.selectCount(model);
	}

}
