package com.Rusya2054.bot.web.manager.models.bot;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "bot_loggers")
@Data
public class BotLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dateTime")
    private LocalDateTime dateTime;

    @Column(name = "level")
    private String level;

    @Column(name = "message")
    private String message;
}
