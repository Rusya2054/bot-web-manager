package com.Rusya2054.bot.web.manager.models.client;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ai_chat_history")
public class AiChatHistory {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clientTgUserID")
    private Long clientTgUserID;

    @Column(name = "allHistory")
    private String allHistory;
}
