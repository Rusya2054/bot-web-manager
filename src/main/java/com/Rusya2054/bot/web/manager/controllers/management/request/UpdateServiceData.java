package com.Rusya2054.bot.web.manager.controllers.management.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateServiceData {
    private Long serviceId;
    private String serviceName;
    private Long filialId;
    private Long specialistId;
    private Boolean isNew;
}
