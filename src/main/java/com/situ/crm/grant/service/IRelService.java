package com.situ.crm.grant.service;

import java.util.List;

import com.situ.crm.grant.model.RelModel;

public interface IRelService {

	int insert(RelModel model);
	int delete(Object id);
	int deleteModel(RelModel model);
	int update(RelModel model) ;
	RelModel selectById(Object id);
    RelModel selectByCode(String roleCode,String menuCode);
	List<RelModel> selectAll(RelModel model);
	List<RelModel> selectModel(RelModel model);
    int selectCount(RelModel model);
}
