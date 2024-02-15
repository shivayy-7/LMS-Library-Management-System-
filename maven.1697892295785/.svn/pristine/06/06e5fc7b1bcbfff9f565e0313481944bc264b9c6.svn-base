package com.aashdit.lms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.lms.model.Rack;

public interface RackRepository extends JpaRepository<Rack, Long> {

	Optional<Rack> findByRackCode(String rackCode);

	List<Rack> findAllByIsActive(boolean b);

}
