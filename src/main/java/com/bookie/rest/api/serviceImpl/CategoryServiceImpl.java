package com.bookie.rest.api.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookie.rest.api.entity.Category;
import com.bookie.rest.api.repositories.CategoryRepository;
import com.bookie.rest.api.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category save(Category category) {
		this.categoryRepository.save(category);
		return category;
	}

	@Override
	public void deleteCategoryById(Long bookId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category getCategoryById(Long bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category getCategoryByName(String categoryName) {
		return this.categoryRepository.findCategoryByCategoryName(categoryName);
	}

}
