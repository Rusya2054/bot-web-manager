package com.Rusya2054.bot.web.manager.factories;


import com.Rusya2054.bot.web.manager.models.management.Service;

public final class AnotherServiceFactory {

    public static Service getAnotherService(){
        final Service anotherService = new Service();
        anotherService.setId(1L);
        anotherService.setName("Другая услуга");
        return anotherService;
    }

    // TODO: переопределить в будущем если понадобиться
//    public static Filial getAnotherService(Specialist mainSpecialist){
//        final Filial anotherFilial = new Filial();
//        anotherFilial.setId(0L);
//        anotherFilial.setName("Другой");
//        anotherFilial.setAddress("Другой филиал");
//        anotherFilial.setMainSpecialist(mainSpecialist);
//        return anotherFilial;
//    }
}
