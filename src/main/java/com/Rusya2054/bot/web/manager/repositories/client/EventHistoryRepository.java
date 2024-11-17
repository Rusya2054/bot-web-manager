package com.Rusya2054.bot.web.manager.repositories.client;

import com.Rusya2054.bot.web.manager.models.client.EventHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventHistoryRepository extends JpaRepository<EventHistory, Long> {
}
