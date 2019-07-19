package sk.msvitok.app.web.rest.controller;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public abstract class BaseController {

    protected final MapperFacade mapperFacade = createMapperFacade();

    private MapperFacade createMapperFacade() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        initMapperFactory(mapperFactory);
        return mapperFactory.getMapperFacade();
    }

    protected void initMapperFactory(MapperFactory mapperFactory) {
        /* do-nothing */
    }
}
