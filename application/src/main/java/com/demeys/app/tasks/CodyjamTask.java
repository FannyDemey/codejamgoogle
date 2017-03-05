package com.demeys.app.tasks;

import com.demeys.app.services.CodyJamService;
import com.demeys.app.services.DancerService;
import com.google.inject.Inject;
import io.dropwizard.lifecycle.Managed;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Fanny on 26/02/2017.
 */
@Slf4j
public class CodyjamTask implements Managed {

    private final CodyJamService codyJamService;
    private final DancerService dancerService;


    @Inject
    public CodyjamTask(CodyJamService codyJamService,DancerService dancerService) {
        this.codyJamService = codyJamService;
        this.dancerService = dancerService;
    }

    @Override
    public void start() throws Exception {
       //  codyJamService.getReducedPricesv2();
        dancerService.getTheNeighbourgsOfK();
    }

    @Override
    public void stop() throws Exception {

    }
}
