package com.situ.crm.mall.service;

import java.util.List;

import com.situ.crm.mall.model.CustCommModel;





public interface ICustCommService {

	
	int insert(CustCommModel model);
	int delete(Object id);
	int deleteModel(CustCommModel model);
	int update(CustCommModel model) ;
	CustCommModel selectById(Object id);
    CustCommModel selectByCode(String custCode,String userCode);
	List<CustCommModel> selectAll(CustCommModel model);
	List<CustCommModel> selectModel(CustCommModel model);
    int selectCount(CustCommModel model);
}
