package com.aashdit.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.lms.model.LibrarySection;

public interface LibrarySectionRepository extends JpaRepository<LibrarySection, Long> {

	List<LibrarySection> findAllByIsActive(boolean b);

	List<LibrarySection> findByLibraryLibId(Long libId);

	List<LibrarySection> findAllByLibraryLibIdAndIsActive(Long libId, boolean b);

	Optional<LibrarySection> findBySecId(Long secId);

}
