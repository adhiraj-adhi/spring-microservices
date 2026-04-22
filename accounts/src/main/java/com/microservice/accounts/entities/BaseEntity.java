package com.microservice.accounts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@MappedSuperclass // tells Spring that this is base class for all entity class extending this class
@Getter @Setter @ToString
public class BaseEntity {
    @Column(updatable=false)
    private LocalDateTime createdAt; // column name will be 'created_at'
    @Column(updatable=false)
    private String createdBy;
    @Column(insertable=false)
    private LocalDateTime updatedAt;
    @Column(insertable=false)
    private String updatedBy;
}
