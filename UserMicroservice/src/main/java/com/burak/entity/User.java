package com.burak.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_user")
@Builder @NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class  User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long authId;

    @Column(unique = true, nullable = false, length = 24)
    String username;

    String email;

    String firstname;

    String lastname;

    String phone;

    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdAt;
}

