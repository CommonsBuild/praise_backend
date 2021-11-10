package com.praisesystem.backend.common.persistence;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "timestamp")
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", columnDefinition = "timestamp")
    LocalDateTime updatedAt;
}
