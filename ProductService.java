package com.service;


import java.util.List;

import com.model.Product;
import com.repository.ProductRepository;

public class ProductService {
	ProductRepository productRepository=new ProductRepository();
	
	public void insertProduct(Product product) {
		productRepository.insertProduct(product);
	}
	
	public List<Product> fetchAllProducts(){
		return productRepository.fetchAllProducts();
	}
	
	public List<Product> increasePriceByCategory(double inc1,double inc2,List<Product> list) {
		return productRepository.increasePriceByCategory(inc1, inc2,list);
	}
	
	public String sumPrices() {
		return productRepository.sumPrices();
	}
	
	public boolean computeGreaterPrice(int id1,int id2) {
		return productRepository.computeGreaterPrice(id1, id2);
		
	}
}
