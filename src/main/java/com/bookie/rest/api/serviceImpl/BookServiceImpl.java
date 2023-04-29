package com.bookie.rest.api.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookie.rest.api.entity.Book;
import com.bookie.rest.api.entity.Category;
import com.bookie.rest.api.repositories.ProductRepository;
import com.bookie.rest.api.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryServiceImpl categoryServiceImpl;
	
	@Override
	public Book saveBook(Book book) {
		this.productRepository.save(book);
		return book;
	}

	@Override
	public void deleteBookById(Long bookId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBookById(Long bookId) {
		// TODO Auto-generated method stub
		return null;
	}

}
