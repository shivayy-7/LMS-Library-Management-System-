package com.aashdit.lms.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aashdit.lms.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	
	List<Book> findByBookCatalogBookCatalogIdAndIsActive(Long bookCatalogId, boolean b);

	Book findByBookUkNoAndIsActive(String bookId, boolean b);

	Book findByBookId(Long bookId);

	List<Book> findAllByBookCatalogBookCatalogId(Long bookCatalogId);

	Book findByBookId(Book book);

	@Query(value="SELECT * \r\n"
			+ "FROM public.t_lms_mst_book ds \r\n"
			+ "WHERE ds.book_catalog_id = :bookctgId \r\n"
			+ "AND ds.is_active = true \r\n"
			+ "AND (ds.status is null or ds.status = '');\r\n"
			+ "",nativeQuery = true)
	List<Book> findByBookCatalogId(@Param("bookctgId")Long bookCatalogId);

	Collection<Long> findByBookCatalogAndIsActive(Long bookCatalogId, boolean b);

	List<Book> findAllByBookCatalogBookCatalogIdAndIsActive(Long bookCatalogId, boolean b);
    
	@Query(value = "SELECT tlmb.* FROM t_lms_mst_book_catalog tlmbc "
	        + "JOIN t_lms_mst_book tlmb ON tlmb.book_catalog_id = tlmbc.book_catalog_id "
	        + "WHERE tlmb.book_catalog_id = :bookCatalogId "
	        + "AND NOT EXISTS ("
	        + "    SELECT * FROM t_lms_book_issued tlbi "
	        + "    WHERE tlbi.book_id = tlmb.book_id "
	        + "    AND tlbi.status IN ('APPROVED', 'RESERVED') "
	        + ") LIMIT 1", nativeQuery = true)
	Book getBookByBookCatalogId(@Param("bookCatalogId") Long bookCatalogId);

	@Query(value = "SELECT tlmb.* FROM t_lms_mst_book_catalog tlmbc "
	        + "JOIN t_lms_mst_book tlmb ON tlmb.book_catalog_id = tlmbc.book_catalog_id "
	        + "WHERE tlmb.book_catalog_id = :bookCatalogId "
	        + "AND NOT EXISTS ("
	        + "    SELECT * FROM t_lms_book_issued tlbi "
	        + "    WHERE tlbi.book_id = tlmb.book_id "
	        + "    AND tlbi.status IN ('APPROVED', 'RESERVED') "
	        + ") ", nativeQuery = true)
	List<Book> getAllBookByBookCatalogId(@Param("bookCatalogId") Long bookCatalogId);

//List<Book> findAllByIsActive(boolean b);

}
