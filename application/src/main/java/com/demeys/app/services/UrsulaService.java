package com.demeys.app.services;

import com.google.common.base.Splitter;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by a508708 on 06/03/2017.
 */
@Slf4j
public class UrsulaService {
    private final FileService fileService;

    @Inject
    public UrsulaService(FileService fileService) {
        this.fileService = fileService;
    }


    public void getNbWordsPossibleForUrsulasLangage() {
        List<String> lines = fileService.getInputFileToList("input/B-large-practice.in");
        log.debug("line.size"+lines.size());
        List<String> outputs = new ArrayList<>();
        for (int lineNb = 1;lineNb < lines.size(); lineNb++) {
            log.debug("case #{}", lineNb);
            //input
            //C V L : nb cons, nb voy, lenght
            List<Integer> inputline = Splitter.on(" ").splitToList(lines.get(lineNb))
                    .stream().map(Integer::parseInt).collect(Collectors.toList());
            Integer c = inputline.get(0);
            Integer v = inputline.get(1);
            Integer l = inputline.get(2);
            //output : nb word ( result modulo 10^9+7 (1000000007))

        }
    }
}
