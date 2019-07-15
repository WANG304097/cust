package com.situ.crm.grant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.grant.mapper.UserMapper;
import com.situ.crm.grant.model.UserModel;
import com.situ.crm.grant.service.IUserService;
import com.situ.until.FmtEmpty;
import com.situ.until.MD5;
@Service
public class UserServiceImpl  implements IUserService{
    
	@Autowired
	private UserMapper mapper;
	
	@Override
	public int insert(UserModel model) {
		model.setUserPass(MD5.encode(model.getUserPass()));
		UserModel em=selectByCode(model.getUserCode());
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
	public int deleteModel(UserModel model) {
		// TODO Auto-generated method stub
		return mapper.deleteModel(model);
	}

	@Override
	public int update(UserModel model) {
		// TODO Auto-generated method stub
		model.setUserPass(MD5.encode(model.getUserPass()));
		return mapper.update(model);
	}

	@Override
	public UserModel selectById(Object id) {
		// TODO Auto-generated method stub
		return mapper.selectById(id);
	}

	@Override
	public UserModel selectByCode(String code) {
		// TODO Auto-generated method stub
		return mapper.selectByCode(code);
	}

	@Override
	public List<UserModel> selectAll(UserModel model) {
		// TODO Auto-generated method stub
		return mapper.selectAll(model);
	}

	@Override
	public List<UserModel> selectModel(UserModel model) {
		// TODO Auto-generated method stub
		return mapper.selectModel(model);
	}

	@Override
	public int selectCount(UserModel model) {
		// TODO Auto-generated method stub
		return mapper.selectCount(model);
	}

}
