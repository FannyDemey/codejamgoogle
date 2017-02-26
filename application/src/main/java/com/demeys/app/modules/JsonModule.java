package com.demeys.app.modules;

import com.demeys.app.DemeysAppDWConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Provides;
import io.dropwizard.setup.Bootstrap;

public class JsonModule {
    private Bootstrap<DemeysAppDWConfiguration> bootstrap;

    public JsonModule(Bootstrap<DemeysAppDWConfiguration> bootstrap){
        this.bootstrap = bootstrap;
    }

    @Provides
    public ObjectMapper providesObjectMapper() {
        return this.bootstrap.getObjectMapper();
    }
}
