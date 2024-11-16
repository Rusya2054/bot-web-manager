package com.Rusya2054.bot.web.manager.repositories.bot;

import com.Rusya2054.bot.web.manager.models.bot.BotLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BotLoggerRepository extends JpaRepository<BotLogger, Long> {
}
