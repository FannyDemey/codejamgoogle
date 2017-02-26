package com.demeys.app.tasks;

import com.demeys.app.services.CodyJamService;
import com.google.inject.Inject;
import io.dropwizard.lifecycle.Managed;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Fanny on 26/02/2017.
 */
@Slf4j
public class CodyjamTask implements Managed {

    private final CodyJamService codyJamService;

    @Inject
    public CodyjamTask(CodyJamService codyJamService) {
        this.codyJamService = codyJamService;
    }

    @Override
    public void start() throws Exception {
        codyJamService.getReducedPrices();
    }

    @Override
    public void stop() throws Exception {

    }
}
