package com.sica.backend.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity
@Table(name = "access_records", indexes = [
    Index(name = "idx_access_user", columnList = "user_id"),
    Index(name = "idx_access_date", columnList = "date"),
    Index(name = "idx_access_type", columnList = "type")
])
@EntityListeners(AuditingEntityListener::class)
data class AccessRecord(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: AccessType,

    @Column(nullable = false)
    val date: LocalDate,

    @Column(nullable = false)
    val time: LocalTime,

    @Column(nullable = false)
    val device: String,

    @Column(nullable = false)
    val location: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val method: AccessMethod,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null
)
