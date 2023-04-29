package com.bookie.rest.api.service;

import java.util.List;

import com.bookie.rest.api.entity.Book;

public interface BookService {

	public Book saveBook(Book book);
	public void deleteBookById(Long bookId);
	public List<Book> getAllBooks();
	public Book getBookById(Long bookId);
}
