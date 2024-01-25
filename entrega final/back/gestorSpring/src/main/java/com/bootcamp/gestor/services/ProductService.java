package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.ProductModel;
import com.bootcamp.gestor.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepo;
	
	public List<ProductModel> getProducts(){
	    return null;
	}

	public Optional<ProductModel> getProductById(int id) {
		return null;
	}

	public List<ProductModel> createProduct(ProductModel product){
		return null;
	}

	public List<ProductModel> deleteProduct(int id){
		return null;
	}

	public List<ProductModel> updateProduct(int id, ProductModel product){
		return null;
	}
}
