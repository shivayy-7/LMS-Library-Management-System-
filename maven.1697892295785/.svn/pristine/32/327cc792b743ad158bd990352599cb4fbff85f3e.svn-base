package com.aashdit.lms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;


@Entity
@Data
public class Notification {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @Column(name = "content_text", nullable = false)
    private String contentText;

    @Column(name = "status", length = 12)
    private String status;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "email_id")
    private String emailId;

}
