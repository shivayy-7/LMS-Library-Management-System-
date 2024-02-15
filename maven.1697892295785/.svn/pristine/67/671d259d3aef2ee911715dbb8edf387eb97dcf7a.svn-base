package com.aashdit.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.lms.model.LookupValue;

public interface LookupValueRepository  extends JpaRepository<LookupValue, Long>{

	List<LookupValue> findByCodeOrderByValueCodeDesc(String dcode);

	@Query(value="select * from t_mst_lookup_value tmlv where tmlv.lookup_code =:language And is_active =:isActive ", nativeQuery = true)
	List<LookupValue> getLanguageByCode(@Param("language") String language, @Param("isActive") boolean isActive);

	List<LookupValue> findAllByCodeAndIsActive(String language, boolean b);


}
