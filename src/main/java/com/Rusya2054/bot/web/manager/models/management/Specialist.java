package com.Rusya2054.bot.web.manager.models.management;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "specialists")
public class Specialist {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "post")
    private String post;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "tgUserID")
    private Long tgUserID;

    @Column(name = "isActive")
    private boolean isActive = true;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private SpecialistImage image;
}
