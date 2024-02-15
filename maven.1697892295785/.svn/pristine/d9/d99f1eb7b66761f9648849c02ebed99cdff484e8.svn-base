package com.aashdit.lms.model;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "t_lms_membertype_fine")
@Data
public class MemberTypeFine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_type_fine_id")
    private Long memberTypeFineId;

    @Column(name = "fine_amount")
    private String fineAmount;

    @Column(name = "member_type_id")
    private Long memberTypeId;

    @Column(name = "is_active")
    private Boolean isActive;

    // Constructors, getters, setters, and other methods
}

