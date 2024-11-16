package com.Rusya2054.bot.web.manager.models.client;

import com.Rusya2054.bot.web.manager.models.management.Service;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "event_history")
public class EventHistory {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

    @Column(name = "clientTgUserID")
    private Long clientTgUserID;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service;

    @Column(name = "feedBack")
    private String feedBack;


}
