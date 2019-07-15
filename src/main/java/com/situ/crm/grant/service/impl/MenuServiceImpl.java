package com.situ.crm.grant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.grant.mapper.MenuMapper;
import com.situ.crm.grant.model.MenuModel;
import com.situ.crm.grant.service.IMenuService;
import com.situ.until.FmtEmpty;


@Service
public class MenuServiceImpl  implements  IMenuService{
	  
		@Autowired
		private MenuMapper mapper;
		
		@Override
		public int insert(MenuModel model) {
			
			MenuModel em=selectByCode(model.getMenuCode());
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
		public int deleteModel(MenuModel model) {
			// TODO Auto-generated method stub
			return mapper.deleteModel(model);
		}

		@Override
		public int update(MenuModel model) {
			// TODO Auto-generated method stub
			return mapper.update(model);
		}

		@Override
		public MenuModel selectById(Object id) {
			// TODO Auto-generated method stub
			return mapper.selectById(id);
		}

		@Override
		public MenuModel selectByCode(String code) {
			// TODO Auto-generated method stub
			return mapper.selectByCode(code);
		}

		@Override
		public List<MenuModel> selectAll(MenuModel model) {
			// TODO Auto-generated method stub
			return mapper.selectAll(model);
		}

		@Override
		public List<MenuModel> selectModel(MenuModel model) {
			// TODO Auto-generated method stub
			return mapper.selectModel(model);
		}

		@Override
		public int selectCount(MenuModel model) {
			// TODO Auto-generated method stub
			return mapper.selectCount(model);
		}

}
