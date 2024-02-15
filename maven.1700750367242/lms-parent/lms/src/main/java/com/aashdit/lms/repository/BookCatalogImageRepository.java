package com.aashdit.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.lms.model.BookCatalogImage;

public interface BookCatalogImageRepository extends JpaRepository<BookCatalogImage, Long> {

	List<BookCatalogImage> findByBookCatalogBookCatalogId(Long bookCatalogId);

}
