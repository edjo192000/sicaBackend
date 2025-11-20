package com.sica.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "visits", indexes = {
    @Index(name = "idx_visit_status", columnList = "status"),
    @Index(name = "idx_visit_datetime", columnList = "visit_datetime")
})
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String visitorName;

    @Column(nullable = false)
    private LocalDateTime visitDatetime;

    @Column(nullable = false)
    private String personVisited;

    @Column(nullable = false)
    private String visitorPhoto;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VisitStatus status = VisitStatus.PENDING;

    private String authorizedBy;

    private LocalDateTime authorizedAt;

    @Column(columnDefinition = "TEXT")
    private String qrCodeBase64;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
