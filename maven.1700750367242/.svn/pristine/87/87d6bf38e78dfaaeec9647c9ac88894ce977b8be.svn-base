package com.aashdit.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.lms.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>  {
	
	List<Category> findAllByIsActiveTrueOrderByCategoryName();

	Category findByCategoryId(Long categoryId);

	List<Category> findAllByIsActiveTrueOrderByCategoryId();

}
