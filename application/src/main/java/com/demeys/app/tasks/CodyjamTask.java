package com.demeys.app.tasks;

import com.demeys.app.services.CodyJamService;
import com.demeys.app.services.ConcertService;
import com.demeys.app.services.DancerService;
import com.demeys.app.services.FirstExService;
import com.demeys.app.services.IOPrinterService;
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
    private final IOPrinterService ioPrinterService;
    private final ConcertService concertService;
    private final FirstExService firstExService;

    @Inject
    public CodyjamTask(CodyJamService codyJamService,IOPrinterService ioPrinterService, ConcertService concertService,
                       FirstExService firstExService) {
        this.codyJamService = codyJamService;
        this.ioPrinterService = ioPrinterService;
        this.concertService = concertService;
        this.firstExService = firstExService;
    }

    @Override
    public void start() throws Exception {
       //  codyJamService.getReducedPricesv2();
      //  dancerService.getTheNeighbourgsOfK();
     //   ursulaService.getNbWordsPossibleForUrsulasLangage();
        ioPrinterService.TODOmethodName();
    }

    @Override
    public void stop() throws Exception {

    }
}
