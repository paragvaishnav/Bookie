package com.bookie.rest.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookie.rest.api.entity.Book;
import com.bookie.rest.api.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	public Category findCategoryByCategoryName(String categoryName);
}
