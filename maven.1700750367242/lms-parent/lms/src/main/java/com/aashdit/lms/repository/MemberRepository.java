package com.aashdit.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aashdit.lms.model.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	Member findByMemberId(Long membberId);

	Member findByMemberCode(String mamberCode);

	List<Member> findAllByIsActiveTrueOrderByMemberId();

	@Query("FROM Member WHERE aadharNo=:aadharNo")
	List<Member> findByAadharNO(@Param("aadharNo") String aadharNo);

	Optional<Member> findByEmailIdAndIsActive(String email, boolean b);

}