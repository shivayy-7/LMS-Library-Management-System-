package com.aashdit.lms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.lms.model.Gender;

import java.util.List;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {

	List<Gender> findAllByIsActiveTrue();

	Gender findByGenderCode(String gender);

	Gender findByGenderId(Long genderId);
}
