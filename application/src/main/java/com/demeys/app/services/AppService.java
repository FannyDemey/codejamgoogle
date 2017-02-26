package com.demeys.app.services;

import com.demeys.app.api.App;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by TODO : set user name on 26/07/2016.
 */
@Slf4j
public class AppService {

    public App getAppName() {
        App app = new App("<%= projectName %>");
        return app;
    }

}
