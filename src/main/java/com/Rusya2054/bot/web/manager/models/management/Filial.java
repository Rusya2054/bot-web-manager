package com.Rusya2054.bot.web.manager.models.management;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "filials")
public class Filial {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phoneNumber;

    private String federalNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private FilialImage image;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "specialist_id", referencedColumnName = "id")
    private Specialist mainSpecialist;

}
