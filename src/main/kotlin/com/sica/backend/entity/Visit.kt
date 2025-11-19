package com.sica.backend.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "visits", indexes = [
    Index(name = "idx_visit_status", columnList = "status"),
    Index(name = "idx_visit_datetime", columnList = "visit_datetime")
])
@EntityListeners(AuditingEntityListener::class)
data class Visit(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Column(nullable = false)
    val visitorName: String,

    @Column(nullable = false)
    val visitDatetime: LocalDateTime,

    @Column(nullable = false)
    val personVisited: String,

    @Column(nullable = false)
    val visitorPhoto: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: VisitStatus = VisitStatus.PENDING,

    var authorizedBy: String? = null,

    var authorizedAt: LocalDateTime? = null,

    @Column(columnDefinition = "TEXT")
    var qrCodeBase64: String? = null,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null
)
