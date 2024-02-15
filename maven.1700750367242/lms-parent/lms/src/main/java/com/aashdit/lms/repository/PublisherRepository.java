package com.aashdit.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.lms.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {

	List<Publisher> findAllByIsActive(boolean b);

	Optional<Publisher> findByPublisherCode(String publisherCode);

	List<Publisher> findAllByIsActiveTrueOrderByPublisherName();

}
