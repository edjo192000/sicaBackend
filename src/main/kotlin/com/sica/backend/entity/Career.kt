package com.sica.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "careers")
data class Career(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Column(nullable = false)
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id", nullable = false)
    val division: Division,

    @Column(nullable = false)
    var active: Boolean = true
)
