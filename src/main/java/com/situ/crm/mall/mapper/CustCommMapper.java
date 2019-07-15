package com.situ.crm.mall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.situ.crm.mall.model.CustCommModel;

public interface CustCommMapper {

	

	int insert(CustCommModel model);
	int delete(Object id);
	int deleteModel(CustCommModel model);
	int update(CustCommModel model) ;
	CustCommModel selectById(Object id);
    CustCommModel selectByCode(@Param(value="custCode")String custCode,@Param(value="userCode")String userCode);
	List<CustCommModel> selectAll(CustCommModel model);
	List<CustCommModel> selectModel(CustCommModel model);
    int selectCount(CustCommModel model);
	
}
