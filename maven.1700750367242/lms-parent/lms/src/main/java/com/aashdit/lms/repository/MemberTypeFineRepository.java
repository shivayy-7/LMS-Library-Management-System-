package com.aashdit.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.lms.model.MemberTypeFine;

public interface MemberTypeFineRepository extends JpaRepository<MemberTypeFine, Long> {

	MemberTypeFine findByMemberTypeId(Long memberTypeId);

}
