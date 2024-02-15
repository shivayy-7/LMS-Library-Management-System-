package com.aashdit.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.lms.model.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {

//	List<Library> findAllByIsActiveTrue();

	Optional<Library> findByLibCode(String libCode);

	List<Library> findAllByIsActive(boolean b);

}
