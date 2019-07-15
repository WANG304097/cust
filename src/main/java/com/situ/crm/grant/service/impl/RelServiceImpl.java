package com.situ.crm.grant.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.grant.mapper.RelMapper;
import com.situ.crm.grant.model.RelModel;
import com.situ.crm.grant.service.IRelService;
import com.situ.until.FmtEmpty;


@Service
public class RelServiceImpl  implements  IRelService{
	  
		@Autowired
		private RelMapper mapper;
		
		@Override
		public int insert(RelModel model) {
			
			RelModel em=selectByCode(model.getRoleCode(),model.getMenuCode());
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
		public int deleteModel(RelModel model) {
			// TODO Auto-generated method stub
			return mapper.deleteModel(model);
		}

		@Override
		public int update(RelModel model) {
			// TODO Auto-generated method stub
			
			
			RelModel em=selectByCode(model.getRoleCode(),model.getMenuCode());
			if(FmtEmpty.isEmpty(em)) {  //若em为空则说明数据库中无此条记录
				mapper.update(model);
				return 1;
			}
			 return   0;
			
		}

		@Override
		public RelModel selectById(Object id) {
			// TODO Auto-generated method stub
			return mapper.selectById(id);
		}

		@Override
		public RelModel selectByCode(String roleCode,String menuCode) {
			// TODO Auto-generated method stub
			return mapper.selectByCode(roleCode,menuCode);
		}

		@Override
		public List<RelModel> selectAll(RelModel model) {
			// TODO Auto-generated method stub
			return mapper.selectAll(model);
		}

		@Override
		public List<RelModel> selectModel(RelModel model) {
			// TODO Auto-generated method stub
			return mapper.selectModel(model);
		}

		@Override
		public int selectCount(RelModel model) {
			// TODO Auto-generated method stub
			return mapper.selectCount(model);
		}

}
