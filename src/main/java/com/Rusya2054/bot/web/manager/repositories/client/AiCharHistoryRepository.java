package com.Rusya2054.bot.web.manager.repositories.client;

import com.Rusya2054.bot.web.manager.models.client.AiChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AiCharHistoryRepository extends JpaRepository<AiChatHistory, Long> {
}
