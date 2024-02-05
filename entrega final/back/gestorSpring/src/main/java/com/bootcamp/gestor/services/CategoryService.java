package com.bootcamp.gestor.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.bootcamp.gestor.models.CategoryModel;
import com.bootcamp.gestor.models.FieldModel;
import com.bootcamp.gestor.repositories.CategoryRepository;
import com.bootcamp.gestor.repositories.FieldRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	FieldRepository fieldRepo;

	public List<CategoryModel> getCategories() {
		return categoryRepo.findAll();
	}

	public CategoryModel getCategoryById(int id) {
		return categoryRepo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Couldn´t find a category with the id " + id));
	}

	public List<CategoryModel> createCategory(CategoryModel category) {
		if (category.getCatName().isEmpty())
			throw new IllegalArgumentException("Name of category is empty");
		Optional<FieldModel> f = fieldRepo.findById(category.getField().getFieldId());
		if (f.isPresent()) {
			FieldModel field = f.get();

			for (CategoryModel c : categoryRepo.findAll()) {
				if (c.getCatName().toLowerCase().equals(category.getCatName().toLowerCase())
						&& c.getField().getFieldId()== field.getFieldId())
					throw new EntityExistsException("The value already exists");
			}
			category.setField(field);
		} else {
			throw new EntityNotFoundException("Couldn't find the state of this address");
		}
		categoryRepo.save(category);
		return categoryRepo.findAll();

	}
	
	
	public String updateCategory(int id, CategoryModel category) {
		Optional<CategoryModel> categoryExists = categoryRepo.findById(id);
		if(categoryExists.isPresent()) {
			if (category.getCatName().isEmpty() )
				throw new IllegalArgumentException("There's missing data");
			
			Optional<FieldModel> f = fieldRepo.findById(category.getField().getFieldId());
			if(f.isPresent()) {
				for(CategoryModel c : categoryRepo.findAll()) {
					if(c.getCatName().toLowerCase().equals(category.getCatName().toLowerCase()))
						throw new EntityExistsException("The value already exists");
				}
				
				CategoryModel c= categoryExists.get();
				FieldModel field = f.get();
				
				c.setCatName(category.getCatName());
				c.setCatDetail(category.getCatDetail());
				c.setField(field);
				
				categoryRepo.save(c);
				
				return "Category " + c.getCatId() + " updated succesfully";
			}
			else {
				throw new EntityNotFoundException("Couldn´t find a field for this category");
			}
		}
		else
		{
			throw new EntityNotFoundException("Couldn´t find a category with the id " + id);
		}
	}
	

	public String deleteCategory(int id) {
		Optional<CategoryModel> categoryExists = categoryRepo.findById(id);
		if (categoryExists.isPresent()) {
			CategoryModel c = categoryExists.get();
			categoryRepo.deleteById(id);
			return "Category " + c.getCatId() + " deleted succesfully";
		} else {
			throw new EntityNotFoundException("Couldn´t find a catgeory with the id " + id);
		}
	}

	
}
