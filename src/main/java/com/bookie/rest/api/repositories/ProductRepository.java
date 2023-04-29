package com.bookie.rest.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookie.rest.api.entity.Book;

@Repository
public interface ProductRepository extends JpaRepository<Book, Long>{

}
