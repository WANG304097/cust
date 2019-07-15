package com.situ.crm.grant.mapper;

import java.util.List;

import com.situ.crm.grant.model.UserModel;

public  interface UserMapper {

	int insert(UserModel model);
	int delete(Object id);
	int deleteModel(UserModel model);
	int update(UserModel model) ;
	UserModel selectById(Object id);
    UserModel selectByCode(String code);
	List<UserModel> selectAll(UserModel model);
	List<UserModel> selectModel(UserModel model);
    int selectCount(UserModel model);
}
