package com.microservice.accounts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass // tells Spring that this is base class for all entity class extending this class
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public class BaseEntity {
    @CreatedDate
    @Column(updatable=false)
    private LocalDateTime createdAt; // column name will be 'created_at'

    @CreatedBy
    @Column(updatable=false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable=false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable=false)
    private String updatedBy;
}
