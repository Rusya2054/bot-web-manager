package com.Rusya2054.bot.web.manager.factories;


import com.Rusya2054.bot.web.manager.models.management.Filial;
import com.Rusya2054.bot.web.manager.models.management.Specialist;

public final class AnotherFilialFactory {

    public static Filial getAnotherFilial(){
        final Filial anotherFilial = new Filial();
        anotherFilial.setId(1L);
        anotherFilial.setName("Другой");
        anotherFilial.setAddress("Другой филиал");
        return anotherFilial;
    }

    public static Filial getAnotherFilial(Specialist mainSpecialist){
        final Filial anotherFilial = new Filial();
        anotherFilial.setId(0L);
        anotherFilial.setName("Другой");
        anotherFilial.setAddress("Другой филиал");
        anotherFilial.setMainSpecialist(mainSpecialist);
        return anotherFilial;
    }
}
