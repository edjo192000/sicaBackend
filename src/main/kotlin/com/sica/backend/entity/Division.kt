package com.sica.backend.entity

import jakarta.persistence.*

@Entity
@Table(name = "divisions")
data class Division(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Column(nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val level: Level,

    @Column(nullable = false)
    var active: Boolean = true
)
