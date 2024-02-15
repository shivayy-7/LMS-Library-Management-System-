package com.aashdit.lms.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.lms.model.BookIssue;

public interface BookIssueRepository extends JpaRepository<BookIssue, Long> {

	List<BookIssue> findAllByIsActive(boolean b);

	List<BookIssue> findAllByStatus(String status);
    
	@Query(value = "select * from public.t_lms_book_issued where status in ('REJECTED','APPROVED')", nativeQuery = true)
	List<BookIssue> getAllByMemberStatus();

	Optional<BookIssue> findByBookIssuedId(Long bookIssueId);

	List<BookIssue> findByStatusInAndIsActive(List<String> statuses, boolean b);
    
	@Query(value = "select * from t_lms_book_issued tlbi where library_card_id=:lib_cardId", nativeQuery = true)
	List<BookIssue> getAllByLibraryCard(@Param("lib_cardId")Long lib_cardId);

	List<BookIssue> findAllByLibraryCardMemberMemberIdAndStatus(Long memberId, String statusReserved);

	
	@Query(value = "SELECT *\r\n"
			+ "FROM t_lms_book_issued tlbi\r\n"
			+ "WHERE return_date =:date\r\n"
			+ "LIMIT 10;", nativeQuery = true)
	List<BookIssue> findAllTODAYreturnbook(@Param("date")Date date);


//	List<BookIssue> findAllByStatusAndIsActive(List<String> statuses, boolean b);

}
