package com.ssm.mapper;

import java.util.List;

import com.ssm.entity.Product;

public interface ProductMapper {
	//保存商品
	void save(Product product);
	//查询商品
	List<Product> list();
}
