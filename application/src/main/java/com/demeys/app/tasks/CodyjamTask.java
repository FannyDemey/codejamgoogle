package com.demeys.app.tasks;

import com.demeys.app.services.CodyJamService;
import com.demeys.app.services.DancerService;
import com.demeys.app.services.PasswordService;
import com.demeys.app.services.UrsulaService;
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
    private final UrsulaService ursulaService;
    private final PasswordService passwordService;

    @Inject
    public CodyjamTask(CodyJamService codyJamService,DancerService dancerService, UrsulaService ursulaService,
                       PasswordService passwordService) {
        this.codyJamService = codyJamService;
        this.dancerService = dancerService;
        this.ursulaService = ursulaService;
        this.passwordService = passwordService;
    }

    @Override
    public void start() throws Exception {
       //  codyJamService.getReducedPricesv2();
      //  dancerService.getTheNeighbourgsOfK();
     //   ursulaService.getNbWordsPossibleForUrsulasLangage();
        passwordService.generateLettersMessage();
    }

    @Override
    public void stop() throws Exception {

    }
}
