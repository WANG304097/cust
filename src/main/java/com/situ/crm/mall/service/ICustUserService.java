package com.situ.crm.mall.service;

import java.util.List;

import com.situ.crm.mall.model.CustUserModel;





public interface ICustUserService {

	
	int insert(CustUserModel model);
	int delete(Object id);
	int deleteModel(CustUserModel model);
	int update(CustUserModel model) ;
	CustUserModel selectById(Object id);
    CustUserModel selectByCode(String code);
	List<CustUserModel> selectAll(CustUserModel model);
	List<CustUserModel> selectModel(CustUserModel model);
    int selectCount(CustUserModel model);
}
