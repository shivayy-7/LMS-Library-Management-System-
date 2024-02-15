package com.aashdit.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.lms.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

	List<Author> findAllByIsActive(boolean b);

	List<Author> findAllByIsActiveTrueOrderByAuthorName();

}
