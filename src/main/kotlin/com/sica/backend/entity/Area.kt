package com.sica.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "areas")
data class Area(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Column(nullable = false)
    val name: String,

    val description: String? = null,

    @Column(nullable = false)
    var active: Boolean = true
)
