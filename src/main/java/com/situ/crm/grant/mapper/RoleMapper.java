package com.situ.crm.grant.mapper;

import java.util.List;

import com.situ.crm.grant.model.RoleModel;

public interface RoleMapper {
	int insert(RoleModel model);
	int delete(Object id);
	int deleteModel(RoleModel model);
	int update(RoleModel model) ;
	RoleModel selectById(Object id);
    RoleModel selectByCode(String code);
	List<RoleModel> selectAll(RoleModel model);
	List<RoleModel> selectModel(RoleModel model);
    int selectCount(RoleModel model);
}
