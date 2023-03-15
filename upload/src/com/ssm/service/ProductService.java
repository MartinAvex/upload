package com.ssm.service;

import java.util.List;

import com.ssm.entity.Product;

public interface ProductService {

	List<Product> list();

	void save(Product product);

	void delete();

}
