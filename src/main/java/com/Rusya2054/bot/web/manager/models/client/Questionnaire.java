package com.Rusya2054.bot.web.manager.models.client;

import com.Rusya2054.bot.web.manager.models.management.Service;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "questionnaire")
public class Questionnaire {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "clientTgUserID")
    private Long clientTgUserID;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

    // TODO по идее под вопросом есть же service где хранит филиал, специлиста и названия
    @Column(name = "city")
    private String city;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;

    @Column(name = "feedBack")
    private String feedBack;
}
