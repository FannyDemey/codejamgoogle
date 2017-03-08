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
        final String filename = "C-custom";
        List<String> lines = fileService.getInputFileToList(filename);
        log.debug("line.size" + lines.size());
        List<String> outputs = new ArrayList<>();
        for (int lineNb = 1; lineNb < lines.size(); lineNb++) {
            log.debug("case #{}", lineNb);
            //input
            //C V L : nb cons, nb voy, lenght
            List<Integer> inputline = Splitter.on(" ").splitToList(lines.get(lineNb))
                    .stream().map(Integer::parseInt).collect(Collectors.toList());
            Integer c = inputline.get(0);
            Integer v = inputline.get(1);
            Integer l = inputline.get(2);
            //output : nb word ( result modulo 10^9+7 (1000000007))
            List<String> possibilities = new ArrayList<>();

            List<String> possibilitiesCons = new ArrayList<>();
            List<String> possibilitiesVoy = new ArrayList<>();
            List<String> initialListCopyForConsonne = new ArrayList<>();
            List<String> initialListCopyForVoyelle = new ArrayList<>();
            possibilities.addAll(possibilitiesCons);
            possibilities.addAll(possibilitiesVoy);


            //initialisation
            for (int j = 0; j < c; j++) {
                possibilities.add("c");
            }
            for (int j = 0; j < v; j++) {
                possibilities.add("v");
            }
            if (l <= 1) {
                long nbWords = possibilities.stream().filter(word -> allowedword(word)).count();
                log.debug("lineNb {}, nbWords {}", lineNb, nbWords);
                outputs.add(generateOutput(lineNb, nbWords));
            } else {
                log.debug("size initial {} and elements:{}", possibilities.size(), possibilities.toString());

                int length = 1;
                // premier a avant dernier tour
                while (length < l - 1) {
                    initialListCopyForConsonne.addAll(possibilities.stream()
                            .map(word -> word.concat("c"))
                            .collect(Collectors.toList()));
                    initialListCopyForVoyelle.addAll(possibilities.stream()
                            .map(word -> word.concat("v"))
                            .collect(Collectors.toList()));

                    possibilities.clear();
                    possibilities.addAll(initialListCopyForConsonne);
                    possibilities.addAll(initialListCopyForVoyelle);
                    initialListCopyForConsonne.clear();
                    initialListCopyForVoyelle.clear();
                    length++;
                }

                initialListCopyForVoyelle.addAll(possibilities.stream()
                        .map(word -> word.concat("v"))
                        .collect(Collectors.toList()));


                log.debug("initialListCopyForVoyelle {} : {}", initialListCopyForVoyelle.size(), initialListCopyForVoyelle);
                initialListCopyForVoyelle = initialListCopyForVoyelle.stream().filter(word -> allowedword(word)).collect(Collectors.toList());
                log.debug("initialListCopyForVoyelle {} : {}", initialListCopyForVoyelle.size(), initialListCopyForVoyelle);
                long nbWords = initialListCopyForVoyelle.size() * v;

                log.debug("lineNb {}, nbWords {}", lineNb, nbWords);

                outputs.add(generateOutput(lineNb, nbWords));
            }

        }
        fileService.writeListToOutputFile(outputs, filename);

    }

    private boolean allowedword(String word) {
        return !word.contains("cc") && !word.endsWith("c");
    }

    private String generateOutput(int caseNb, long size) {
        size = size % 1000000007;
        String output = "Case #" + caseNb + ": " + size;
        return output;
    }
}
