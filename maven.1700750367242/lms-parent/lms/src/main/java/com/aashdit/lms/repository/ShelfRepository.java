package com.aashdit.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.lms.model.Shelf;

public interface ShelfRepository extends JpaRepository<Shelf, Long> {

	List<Shelf> findAllByRackRackId(Long rackId);

	Shelf findByShelfId(Long shelfId);

}
