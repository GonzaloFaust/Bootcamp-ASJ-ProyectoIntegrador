package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.gestor.models.CategoryModel;
import com.bootcamp.gestor.models.FieldModel;
import com.bootcamp.gestor.models.ProductModel;
import com.bootcamp.gestor.models.SupplierModel;
import com.bootcamp.gestor.repositories.CategoryRepository;
import com.bootcamp.gestor.repositories.FieldRepository;
import com.bootcamp.gestor.repositories.ProductRepository;
import com.bootcamp.gestor.repositories.SupplierRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	FieldRepository fieldRepo;
	@Autowired
	SupplierRepository supplierRepo;
	@Autowired
	ProductRepository productRepo;

	public List<CategoryModel> getCategories() {
		return categoryRepo.findAll();
	}

	public CategoryModel getCategoryById(int id) {
		return categoryRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("No se pudo encontrar una categoría con el id " + id));
	}

	public List<CategoryModel> getCategoryBySupplier(int id) {
		Optional<SupplierModel> sup = supplierRepo.findById(id);
		if (sup.isPresent()) {
			return this.categoryRepo.getCategoriesBySupplier(id);
		} else {
			throw new EntityNotFoundException("No se encontró el proveedor");
		}
	}

	public List<CategoryModel> createCategory(CategoryModel category) {
		if (category.getCatName().isEmpty())
			throw new IllegalArgumentException("El nombre de la categoría está vacío");
		Optional<FieldModel> f = fieldRepo.findById(category.getField().getFieldId());
		if (f.isPresent()) {
			FieldModel field = f.get();

			for (CategoryModel c : categoryRepo.findAll()) {
				if (c.getCatName().toLowerCase().equals(category.getCatName().toLowerCase())
						&& c.getField().getFieldId() == field.getFieldId())
					throw new EntityExistsException("El rubro ya posee esta categoría");
			}
			category.setField(field);
		} else {
			throw new EntityNotFoundException("No se pudo encontrar el rubro");
		}
		categoryRepo.save(category);
		return categoryRepo.findAll();

	}

	public String updateCategory(int id, CategoryModel category) {
		Optional<CategoryModel> categoryExists = categoryRepo.findById(id);
		if (categoryExists.isPresent()) {
			if (category.getCatName().isEmpty())
				throw new IllegalArgumentException("El nombre de la categoría está vacío");

			Optional<FieldModel> f = fieldRepo.findById(category.getField().getFieldId());
			if (f.isPresent()) {
				FieldModel field = f.get();
				CategoryModel cat = categoryExists.get();
				for (CategoryModel c : categoryRepo.findAll()) {
					if (c.getCatName().toLowerCase().equals(category.getCatName().toLowerCase())
							&& c.getField().getFieldId() == field.getFieldId() && c.getCatId() != cat.getCatId())
						throw new EntityExistsException("El rubro ya posee esta categoría");
				}

				cat.setCatName(category.getCatName());
				cat.setCatDetail(category.getCatDetail());
				cat.setField(field);

				categoryRepo.save(cat);

				return "Categoría " + cat.getCatName() + "modificada exitosamente";
			} else {
				throw new EntityNotFoundException("No se pudo encontrar el rubro");
			}
		} else {
			throw new EntityNotFoundException("No se pudo encontrar una categoría con el id " + id);
		}
	}

	public String deleteCategory(int id) {
		System.out.println("entra al servicio");
		Optional<CategoryModel> categoryExists = categoryRepo.findById(id);

		if (categoryExists.isPresent()) {
			CategoryModel c = categoryExists.get();
			List<ProductModel> products = productRepo.findBycategory(c);
			if (products.size() > 0) {
				throw new EntityExistsException(
						"La categoría posee productos, antes de eliminarla debe estar vacía");
			} else {

				categoryRepo.deleteById(id);
				return "Categoría " + c.getCatName() + " eliminada exitosamente";

			}

		} else {
			throw new EntityNotFoundException("No se pudo encontrar una categoría con el id " + id);
		}
	}

}
