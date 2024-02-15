package com.aashdit.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.lms.model.BookCatalog;

public interface BookCatalogRepository extends JpaRepository<BookCatalog, Long> {

	List<BookCatalog> findAllByIsActive(boolean b);

	Optional<BookCatalog> findByBookCatalogCode(String catalogCode);

	@Query(value="SELECT v.img_path, v.book_titles, v.book_catalog_code \r\n"
			+ "FROM public.t_lms_mst_book_catalog v \r\n"
			+ "JOIN public.t_lms_mst_sub_category tlmsc ON tlmsc.sub_category_id = v.sub_category_id \r\n"
			+ "JOIN public.t_lms_mst_category tlmc ON tlmc.category_id = tlmsc.category_id \r\n"
			+ "WHERE (v.book_titles LIKE :books || '%' OR tlmsc.sub_category_name LIKE :books || '%' OR tlmc.category_name LIKE :books || '%')\r\n"
			+ "AND v.is_active = true;\r\n"
			+ "",nativeQuery = true)
	List<Object[]> findBooksByBookName(@Param("books")String books);

	
	@Query(value="select tlmbc.\"language\"  from public.t_lms_mst_book_catalog tlmbc group by tlmbc.\"language\" ",nativeQuery = true)
	List<Object[]> findAllLanguageList();

	@Query(value="select tlmbc.* from public.t_lms_mst_book_catalog tlmbc join t_lms_mst_sub_category tlmsc \r\n"
			+ "on tlmbc.sub_category_id = tlmsc.sub_category_id \r\n"
			+ "where tlmsc.sub_category_id =:subId",nativeQuery = true)
	List<BookCatalog> findBysubCatgoriesId(@Param("subId")Long subcategoryId);

	@Query(value="SELECT tlmbc.* "
	        + "FROM public.t_lms_mst_book_catalog tlmbc "
	        + "WHERE tlmbc.book_titles = :booksDel", nativeQuery = true)
	List<BookCatalog> findByBooksName(@Param("booksDel") String booksDel);

	
	@Query(value="SELECT tlmbc.* \r\n"
			+ "FROM public.t_lms_mst_book_catalog tlmbc \r\n"
			+ "join t_mst_lookup_value tmlv \r\n"
			+ "on tmlv.lookup_value_id = tlmbc.\"language\" \r\n"
			+ "WHERE tlmbc.\"language\"  = :languageId\r\n"
			+ "and tmlv.lookup_code ='LANGUAGE_TYPE'", nativeQuery = true)
	List<BookCatalog> findByBookLanguageId(@Param("languageId")Long languageId);

	Optional<BookCatalog> findByIsbnNoAndIsActive(String id, Boolean b);

	@Query(value="SELECT * FROM public.t_lms_mst_book_catalog;", nativeQuery = true)
	List<BookCatalog> findAllByIsActiveLimtTwinty(boolean b);


	@Query(value="select tlmbc.* from public.t_lms_mst_book_catalog tlmbc join t_lms_mst_sub_category tlmsc \r\n"
			+ "on tlmbc.sub_category_id = tlmsc.sub_category_id \r\n"
			+ "join t_lms_mst_category tlmc on tlmc.category_id =tlmsc.category_id \r\n"
			+ "where tlmc.category_name =:catName", nativeQuery = true)
	List<BookCatalog> findByBooKCatName(@Param("catName")String catName);



	

}
