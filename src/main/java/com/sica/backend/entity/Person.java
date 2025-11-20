package com.sica.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "persons")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String secondLastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    private String photoUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PersonType type;

    @Column(nullable = false)
    private Boolean active = true;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public String getFullName() {
        StringBuilder fullName = new StringBuilder();
        fullName.append(firstName);
        fullName.append(" ");
        fullName.append(lastName);
        if (secondLastName != null) {
            fullName.append(" ");
            fullName.append(secondLastName);
        }
        return fullName.toString();
    }
}
