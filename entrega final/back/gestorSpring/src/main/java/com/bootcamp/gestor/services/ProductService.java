package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.CategoryModel;
import com.bootcamp.gestor.models.ProductModel;
import com.bootcamp.gestor.models.SupplierModel;
import com.bootcamp.gestor.repositories.CategoryRepository;
import com.bootcamp.gestor.repositories.ProductRepository;
import com.bootcamp.gestor.repositories.SupplierRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepo;

	@Autowired
	SupplierRepository supplierRepo;

	@Autowired
	CategoryRepository categoryRepo;

	public List<ProductModel> getProducts() {
		return productRepo.findAll();
	}

	public ProductModel getProductById(int id) {
		return productRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Couldn´t find a product with the id " + id));
	}

	public List<ProductModel> createProduct(ProductModel product) {
		if (product.getProdName().isEmpty() || product.getProdDescription().isEmpty()
				|| product.getProdImage().isEmpty() || product.getProdPrice() == 0 || product.getProdSku().isEmpty())
			throw new IllegalArgumentException("There's missing data, can't create the product");

		Optional<SupplierModel> sup = supplierRepo.findById(product.getSupplier().getSupId());
		Optional<CategoryModel> cat = categoryRepo.findById(product.getCategory().getCatId());

		if (sup.isPresent() && cat.isPresent()) {
			SupplierModel supplier = sup.get();
			CategoryModel category = cat.get();
			for (ProductModel p : productRepo.findAll()) {
				if (supplier.getSupId() == p.getSupplier().getSupId() && product.getProdSku() == p.getProdSku())
					throw new EntityExistsException("The provider already offers this product");
			}
			product.setCategory(category);
			product.setSupplier(supplier);

		} else {
			throw new EntityNotFoundException("Couldn't find category or supplier of this product");
		}
		productRepo.save(product);
		return productRepo.findAll();
	}

	public String updateProduct(int id, ProductModel product) {

		Optional<ProductModel> productExists = productRepo.findById(id);
		if (productExists.isPresent()) {
			ProductModel prod = productExists.get();
			if (product.getProdName().isEmpty() || product.getProdDescription().isEmpty()
					|| product.getProdImage().isEmpty() || product.getProdPrice() == 0
					|| product.getProdSku().isEmpty())
				throw new IllegalArgumentException("There's missing data, can't create the product");

			Optional<SupplierModel> sup = supplierRepo.findById(product.getSupplier().getSupId());
			Optional<CategoryModel> cat = categoryRepo.findById(product.getCategory().getCatId());

			if (sup.isPresent() && cat.isPresent()) {
				SupplierModel supplier = sup.get();
				CategoryModel category = cat.get();
				for (ProductModel p : productRepo.findAll()) {
					if (supplier.getSupId() == p.getSupplier().getSupId() && product.getProdSku() == p.getProdSku())
						throw new EntityExistsException("The provider already offers this product");
				}
				prod.setCategory(category);
				prod.setSupplier(supplier);
				prod.setProdDescription(product.getProdDescription());
				prod.setProdName(product.getProdName());
				prod.setProdPrice(product.getProdPrice());
				prod.setProdImage(product.getProdImage());

				productRepo.save(prod);
				return "Product " + prod.getProdName() + " updated succesfully";
			} else {
				throw new EntityNotFoundException("Couldn't find category or supplier of this product");
			}
		} else {
			throw new EntityNotFoundException("Couldn´t find a product with the id " + id);
		}
	}

	public String deleteProduct(int id) {
		Optional<ProductModel> productExists = productRepo.findById(id);
		if (productExists.isPresent()) {
			ProductModel p = productExists.get();
			
			p.setProdAvailable(false);
			System.out.println(productRepo.save(p).getProdAvailable());
			return "Product " + p.getProdName() + " deleted succesfully";
		} else {
			throw new EntityNotFoundException("Couldn´t find a product with the id " + id);
		}
	}

}
