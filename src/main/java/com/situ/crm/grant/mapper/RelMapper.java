package com.situ.crm.grant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.situ.crm.grant.model.RelModel;

public interface RelMapper {
	int insert(RelModel model);
	int delete(Object id);
	int deleteModel(RelModel model);
	int update(RelModel model) ;
	RelModel selectById(Object id);
    RelModel selectByCode(@Param(value="roleCode")String roleCode,@Param(value="menuCode")String menuCode);
	List<RelModel> selectAll(RelModel model);
	List<RelModel> selectModel(RelModel model);
    int selectCount(RelModel model);
}
