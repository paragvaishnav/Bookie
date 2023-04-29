package com.bookie.rest.api.service;

import java.util.List;
import com.bookie.rest.api.entity.Category;

public interface CategoryService {

	public Category save(Category category);
	public void deleteCategoryById(Long bookId);
	public Category getCategoryByName(String categoryName);
	public List<Category> getAllCategories();
	public Category getCategoryById(Long bookId);
}
