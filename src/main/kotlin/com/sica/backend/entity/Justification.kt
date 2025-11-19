package com.sica.backend.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "justifications", indexes = [
    Index(name = "idx_justification_student", columnList = "student_id"),
    Index(name = "idx_justification_status", columnList = "status"),
    Index(name = "idx_justification_attendance", columnList = "attendance_id")
])
@EntityListeners(AuditingEntityListener::class)
data class Justification(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_id", nullable = false, unique = true)
    val attendance: Attendance,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    val student: Student,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id", nullable = false)
    val professor: Person,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    val subject: Subject,

    @Column(nullable = false)
    val fileUrl: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val fileType: FileType,

    @Column(nullable = false, length = 1000)
    val description: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var status: JustificationStatus = JustificationStatus.PENDIENTE,

    @Column(length = 1000)
    var professorComment: String? = null,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null,

    var reviewedAt: LocalDateTime? = null
)
