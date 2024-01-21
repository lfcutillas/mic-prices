package com.inditex.prices.infrastructure.entities;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditEntity {

	@CreatedBy
	protected String createdBy;
	@LastModifiedBy
	protected String lastModifiedBy;
	@CreatedDate
	protected LocalDateTime createdDate;
	@LastModifiedDate
	protected LocalDateTime lastModifiedDate;

}