package com.demeys.app;

import com.demeys.app.tasks.CodyjamTask;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import ru.vyarus.dropwizard.guice.GuiceBundle;

/**
 * Created by TODO : set user name on TODO : set date.
 */
public class DemeysAppDWApplication extends Application<DemeysAppDWConfiguration> {

    @Override
    public void initialize(Bootstrap<DemeysAppDWConfiguration> bootstrap) {
        bootstrap.addBundle(GuiceBundle.builder()
                .enableAutoConfig(getClass().getPackage().getName())
                .searchCommands(true)
                .build()
        );

        bootstrap.addBundle(new ViewBundle<>());
    }

    @Override
    public void run(DemeysAppDWConfiguration demeysAppDWConfiguration, Environment environment) throws Exception {
    }

    public static void main(String[] args) throws Exception {
        new DemeysAppDWApplication().run(args);
        //new CodyjamTask().start();
    }
}
