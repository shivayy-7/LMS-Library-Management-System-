package com.aashdit.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aashdit.lms.model.LibraryCard;

@Repository
public interface LibraryCardRepository extends JpaRepository<LibraryCard, Long> {

	@Query(value="select * from t_lms_library_card ds where ds.library_card_id=:libcardId ",nativeQuery = true)
	LibraryCard findByLib_cardId(@Param("libcardId") Long lib_cardId);

	@Query(value="select * from public.t_lms_library_card ks join public.t_lms_mst_member tlmm \r\n"
			+ "on ks.member_id =tlmm.member_id  where tlmm.member_id =:memberId",nativeQuery = true)
	LibraryCard findByMemberAndmemberId(@Param("memberId")Long memberId);

	@Query(value="SELECT v.approve_no  \r\n"
			+ "FROM public.t_lms_library_card v \r\n"
			+ "WHERE v.approve_no LIKE :approve_no% \r\n"
			+ "AND v.is_active = true",nativeQuery = true)
	List<Object[]> findApprovalNoAutoSearch(@Param("approve_no")String aaprovalNo);

	@Query(value="select * from t_lms_library_card ds where ds.approve_no=:approve_no  ",nativeQuery = true)
	LibraryCard findCardByApprovalNo(@Param("approve_no")String sentapprovalNo);

	LibraryCard findByMemberMemberId(Long memberId);
    


}