package com.bookie.rest.api.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookie.rest.api.entity.Book;
import com.bookie.rest.api.entity.Category;
import com.bookie.rest.api.repositories.CategoryRepository;
import com.bookie.rest.api.serviceImpl.BookServiceImpl;
import com.bookie.rest.api.utility.ApiResponse;

@RestController
@RequestMapping("api/admin/")
@CrossOrigin
public class ProductController {
	private static final Logger logger = LogManager.getLogger(ProductController.class);

	@Autowired
	public BookServiceImpl bookServiceImpl;
	
	@Autowired
	public CategoryRepository categoryRepository;

	@PreAuthorize("hasAnyAuthority('admin')")
	@PostMapping("save/{categoryId}/book")
	public ResponseEntity<?> saveBook(@PathVariable("categoryId")Long categoryId,@RequestBody Book book) {
		if (!StringUtils.isEmpty(book)) {
			Category category = this.categoryRepository.findById(categoryId).get();
			if(category!=null) {
				book.setCategory(category);
			}
			this.bookServiceImpl.saveBook(book);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(ApiResponse.getMessage(book.getBookId(), "Book Added Successfully", 201));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ApiResponse.getMessage(null, "All values are required", 400));
	}

	@PreAuthorize("hasAnyAuthority('admin')")
	@PostMapping("save/category")
	public ResponseEntity<?> saveCategory(@RequestBody Category category) {
		if (!StringUtils.isEmpty(category)) {
			this.categoryRepository.save(category);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(ApiResponse.getMessage(category.getCategoryId(), "Book Added Successfully", 201));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ApiResponse.getMessage(null, "All values are required", 400));
	}
	
	@GetMapping("all")
	public ResponseEntity<?> getAllBooks() {
		if (this.bookServiceImpl.getAllBooks().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.body(ApiResponse.getMessage(null, "There are no books available", 202));
		}
		List<Book> list = this.bookServiceImpl.getAllBooks();
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.getMessage(list, "All values are required", 200));
	}

	
}
