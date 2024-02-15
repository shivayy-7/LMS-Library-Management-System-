package com.aashdit.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.lms.model.SubCategory;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

	SubCategory findBySubCategoryId(Long subCategoryId);

	List<SubCategory> findAllByIsActiveTrueOrderBySubCategoryId();

	@Query(value="select * from public.t_lms_mst_sub_category ks where ks.category_id =:category_id ",nativeQuery = true)
	List<SubCategory> findByCatagoiesId(@Param("category_id")Long categoryId);

}
