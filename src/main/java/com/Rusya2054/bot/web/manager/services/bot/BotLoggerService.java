package com.Rusya2054.bot.web.manager.services.bot;

import com.Rusya2054.bot.web.manager.models.bot.BotLogger;
import com.Rusya2054.bot.web.manager.repositories.bot.BotLoggerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BotLoggerService {
    private final BotLoggerRepository botLoggerRepository;

    public List<BotLogger> getAllLogs(){
        // TODO: сортировка по времени
        // TODO: выбрать интервал
        // TODO: перегрузка: выбрать интервал + уровень сообщения
        // TODO: выбрать сообщения
        return botLoggerRepository.findAll();
    }
}
