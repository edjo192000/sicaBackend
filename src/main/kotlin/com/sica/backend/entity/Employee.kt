package com.sica.backend.entity

import jakarta.persistence.*
import java.time.LocalTime

@Entity
@Table(name = "employees")
data class Employee(
    @Id
    val personId: String,

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "person_id")
    val person: Person,

    @Column(nullable = false, unique = true)
    val employeeNumber: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id", nullable = false)
    val area: Area,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val shift: Shift,

    @Column(nullable = false)
    val entryTime: LocalTime,

    @Column(nullable = false)
    val exitTime: LocalTime
)
