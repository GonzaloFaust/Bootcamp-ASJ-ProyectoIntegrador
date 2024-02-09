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

	public List<ProductModel> getProductsSearch(String searchTerm, Integer category) {
		if (category == null) {
	        category = -1; 
	    }
		if(searchTerm==null) {
			searchTerm="";
		}
		return productRepo.findByTitleContainingAndCategory(searchTerm, category);
	}
	
	public List<ProductModel> getProductsBySupplier(int id){
		Optional<SupplierModel> sup= supplierRepo.findById(id);
		if(sup.isPresent()) {
			List<ProductModel> list= productRepo.findBysupplier(sup.get());

		return list;
		}
		else {
			throw new EntityNotFoundException("No se pudo encontrar el proveedor");
		}
	}

	public ProductModel getProductById(int id) {
		return productRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar un producto con el id " + id));
	}

	public List<ProductModel> createProduct(ProductModel product) {
		if (product.getProdName().isEmpty() || product.getProdDescription().isEmpty()
				 || product.getProdPrice() <= 0 || product.getProdSku().isEmpty())
			throw new IllegalArgumentException("Faltan datos, no se puede crear el producto");

		Optional<SupplierModel> sup = supplierRepo.findById(product.getSupplier().getSupId());
		Optional<CategoryModel> cat = categoryRepo.findById(product.getCategory().getCatId());

		if (sup.isPresent() && cat.isPresent()) {
			SupplierModel supplier = sup.get();
			CategoryModel category = cat.get();
			
			for (ProductModel p : productRepo.findAll()) {
				if (supplier.getSupId() == p.getSupplier().getSupId() && product.getProdSku().equals(p.getProdSku()) && p.getProdId()!= product.getProdId())
					throw new EntityExistsException("El proveedor ya ofrece este producto");
			}
			product.setCategory(category);
			product.setSupplier(supplier);

		} else {
			throw new EntityNotFoundException("No se pudo encontrar la categoría o el proveedor de este producto");
		}
		productRepo.save(product);
		return productRepo.findAll();
	}

	public ProductModel updateProduct(int id, ProductModel product) {

		Optional<ProductModel> productExists = productRepo.findById(id);
		if (productExists.isPresent()) {
			ProductModel prod = productExists.get();
			if (product.getProdName().isEmpty() || product.getProdDescription().isEmpty()
					|| product.getProdImage().isEmpty() || product.getProdPrice() == 0
					|| product.getProdSku().isEmpty())
				throw new IllegalArgumentException("Faltan datos, no se puede crear el producto");

			Optional<SupplierModel> sup = supplierRepo.findById(product.getSupplier().getSupId());
			Optional<CategoryModel> cat = categoryRepo.findById(product.getCategory().getCatId());

			if (sup.isPresent() && cat.isPresent()) {
				SupplierModel supplier = sup.get();
				CategoryModel category = cat.get();
				
				
				for (ProductModel p : productRepo.findAll()) {
					if (supplier.getSupId() == p.getSupplier().getSupId() && product.getProdSku().equals(p.getProdSku()) && p.getProdId()!= product.getProdId())
						throw new EntityExistsException("El proveedor ya ofrece este producto");
				}
				
				
				prod.setCategory(category);
				prod.setSupplier(supplier);
				prod.setProdDescription(product.getProdDescription());
				prod.setProdName(product.getProdName());
				prod.setProdPrice(product.getProdPrice());
				prod.setProdImage(product.getProdImage());
				prod.setProdAvailable(product.getProdAvailable());

				productRepo.save(prod);
				return prod;
			} else {
				throw new EntityNotFoundException("No se pudo encontrar la categoría o el proveedor de este producto");
			}
		} else {
			throw new EntityNotFoundException("No se pudo encontrar un producto con el id " + id);
		}
	}

	public ProductModel makeAvailable(int id) {
		Optional<ProductModel> productExists = productRepo.findById(id);
		if (productExists.isPresent()) {
			ProductModel prod = productExists.get();
			if(prod.getProdAvailable()==true)
				throw new EntityExistsException("El producto ya está disponible");
			prod.setProdAvailable(true);
			productRepo.save(prod);
			return prod;
		} else {
			throw new EntityNotFoundException("No se pudo encontrar un producto con el id " + id);
		}
	}

	public String deleteProduct(int id) {
		Optional<ProductModel> productExists = productRepo.findById(id);
		if (productExists.isPresent()) {
			ProductModel p = productExists.get();
			
			p.setProdAvailable(false);
			productRepo.save(p);
			return "Producto " + p.getProdName() + " eliminado exitosamente";
		} else {
			throw new EntityNotFoundException("No se pudo encontrar un producto con el id " + id);
		}
	}

}
