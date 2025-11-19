package com.sica.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "students")
data class Student(
    @Id
    val personId: String,

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "person_id")
    val person: Person,

    @Column(nullable = false, unique = true)
    val enrollmentNumber: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val level: Level,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id", nullable = false)
    val division: Division,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id", nullable = false)
    val career: Career,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val shift: Shift
)
