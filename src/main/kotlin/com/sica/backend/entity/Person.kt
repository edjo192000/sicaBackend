package com.sica.backend.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(name = "persons")
@EntityListeners(AuditingEntityListener::class)
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String? = null,

    @Column(nullable = false)
    val firstName: String,

    @Column(nullable = false)
    val lastName: String,

    val secondLastName: String? = null,

    @Column(nullable = false, unique = true)
    val email: String,

    val phone: String? = null,

    var photoUrl: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val type: PersonType,

    @Column(nullable = false)
    var active: Boolean = true,

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null,

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null
) {
    val fullName: String
        get() = buildString {
            append(firstName)
            append(" ")
            append(lastName)
            secondLastName?.let {
                append(" ")
                append(it)
            }
        }
}
