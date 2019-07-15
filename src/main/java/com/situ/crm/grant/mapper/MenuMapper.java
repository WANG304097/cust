package com.situ.crm.grant.mapper;

import java.util.List;

import com.situ.crm.grant.model.MenuModel;

public interface MenuMapper {
	int insert(MenuModel model);
	int delete(Object id);
	int deleteModel(MenuModel model);
	int update(MenuModel model) ;
	MenuModel selectById(Object id);
    MenuModel selectByCode(String code);
	List<MenuModel> selectAll(MenuModel model);
	List<MenuModel> selectModel(MenuModel model);
    int selectCount(MenuModel model);
}
