package com.situ.crm.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.crm.mall.mapper.ProductMapper;
import com.situ.crm.mall.model.ProductModel;
import com.situ.crm.mall.service.IProductService;
import com.situ.until.FmtEmpty;
import com.situ.until.MD5;
@Service
public class ProductServiceImpl  implements IProductService{
    
	@Autowired
	private ProductMapper mapper;
	
	@Override
	public int insert(ProductModel model) {
	
		ProductModel em=selectByCode(model.getCode());
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
	public int deleteModel(ProductModel model) {
		// TODO Auto-generated method stub
		return mapper.deleteModel(model);
	}

	@Override
	public int update(ProductModel model) {
		// TODO Auto-generated method stub

		return mapper.update(model);
	}

	@Override
	public ProductModel selectById(Object id) {
		// TODO Auto-generated method stub
		return mapper.selectById(id);
	}

	@Override
	public ProductModel selectByCode(String code) {
		// TODO Auto-generated method stub
		return mapper.selectByCode(code);
	}

	@Override
	public List<ProductModel> selectAll(ProductModel model) {
		// TODO Auto-generated method stub
		return mapper.selectAll(model);
	}

	@Override
	public List<ProductModel> selectModel(ProductModel model) {
		// TODO Auto-generated method stub
		return mapper.selectModel(model);
	}

	@Override
	public int selectCount(ProductModel model) {
		// TODO Auto-generated method stub
		return mapper.selectCount(model);
	}

}
