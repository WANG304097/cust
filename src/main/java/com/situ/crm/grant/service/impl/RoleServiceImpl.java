package com.situ.crm.grant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.grant.mapper.RoleMapper;
import com.situ.crm.grant.model.RoleModel;
import com.situ.crm.grant.service.IRoleService;
import com.situ.until.FmtEmpty;


@Service
public class RoleServiceImpl  implements  IRoleService{
	  
		@Autowired
		private RoleMapper mapper;
		
		@Override
		public int insert(RoleModel model) {
			
			RoleModel em=selectByCode(model.getRoleCode());
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
		public int deleteModel(RoleModel model) {
			// TODO Auto-generated method stub
			return mapper.deleteModel(model);
		}

		@Override
		public int update(RoleModel model) {
			// TODO Auto-generated method stub
			return mapper.update(model);
		}

		@Override
		public RoleModel selectById(Object id) {
			// TODO Auto-generated method stub
			return mapper.selectById(id);
		}

		@Override
		public RoleModel selectByCode(String code) {
			// TODO Auto-generated method stub
			return mapper.selectByCode(code);
		}

		@Override
		public List<RoleModel> selectAll(RoleModel model) {
			// TODO Auto-generated method stub
			return mapper.selectAll(model);
		}

		@Override
		public List<RoleModel> selectModel(RoleModel model) {
			// TODO Auto-generated method stub
			return mapper.selectModel(model);
		}

		@Override
		public int selectCount(RoleModel model) {
			// TODO Auto-generated method stub
			return mapper.selectCount(model);
		}

}
