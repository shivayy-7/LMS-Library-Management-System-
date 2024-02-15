package com.aashdit.lms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.aashdit.framework.core.model.Auditable;
import com.aashdit.umt.model.User;


@Entity
@Table(name = "t_mst_lookup_value")
@Inheritance(strategy =InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING,
	   name = "lookup_code",
	  columnDefinition = "VARCHAR(64)"
	)
public class LookupValue extends Auditable<User> implements Serializable {
	private static final long serialVersionUID = -1690076517522282465L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "lookup_value_id")
	private Long valueId;

	@Column(name = "lookup_value_code")
	private String valueCode;
	
	@Column(name = "lookup_code", insertable = false, updatable = false)
	private String code;
	
	@NotNull
	@Column(name = "lookup_value_en")
	private String valueEn;

	@NotNull
	@Column(name = "lookup_value_hi")
	private String valueHi;

	@NotNull
	@Column(name = "is_active")
	private Boolean isActive;

	public Long getValueId() {
		return valueId;
	}

	public void setValueId(Long valueId) {
		this.valueId = valueId;
	}

	public String getValueCode() {
		return valueCode;
	}

	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValueEn() {
		return valueEn;
	}

	public void setValueEn(String valueEn) {
		this.valueEn = valueEn;
	}

	public String getValueHi() {
		return valueHi;
	}

	public void setValueHi(String valueHi) {
		this.valueHi = valueHi;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
