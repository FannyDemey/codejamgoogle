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
        List<String> lines = fileService.getInputFileToList("input/C-small-practice-2.in");
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
            possibilities.addAll(possibilitiesCons);
            possibilities.addAll(possibilitiesVoy);


            //initialisation
            for (int j = 0; j < c; j++) {
                possibilities.add("c");
            }
            for (int j = 0; j < v; j++) {
                possibilities.add("v");
            }

            log.debug("size initial {} and elements:{}", possibilities.size(), possibilities.toString());

            int length = 1;
            while (length < l) {

                List<String> initialListCopyForConsonne = new ArrayList<>();
                List<String> initialListCopyForVoyelle = new ArrayList<>();

                initialListCopyForConsonne.addAll(possibilities.stream()
                        .map(word -> word.concat("c"))
                        .collect(Collectors.toList()));
                initialListCopyForVoyelle.addAll(possibilities.stream()
                        .map(word -> word.concat("v"))
                        .collect(Collectors.toList()));
                possibilities.clear();
                possibilitiesCons.clear();
                possibilitiesVoy.clear();

                possibilities.addAll(initialListCopyForConsonne);
                possibilities.addAll(initialListCopyForVoyelle);
                possibilitiesCons.addAll(initialListCopyForConsonne);
                possibilitiesVoy.addAll(initialListCopyForVoyelle);
                //  log.debug("{} possibilities : {}",possibilities.size(),possibilities.toString());

                length++;
            }
/*
        log.debug("possibilities {}",possibilities);
        log.debug("possibilitiesCons {}, possibilitiesVoy {}", possibilitiesCons.size(), possibilitiesVoy.size());
        log.debug("possibilitiesCons all to be removed {}",possibilitiesCons);
*/

            long nbWords;
            if(l>1){
             //   log.debug("possibilitiesVoy before filter : {}",possibilitiesVoy);
                possibilitiesVoy = possibilitiesVoy.stream().filter(word -> this.allowedword(word)).collect(Collectors.toList());
            //    log.debug("possibilitiesVoy after : {}",possibilitiesVoy);
                nbWords = possibilitiesVoy.size() * v;
            } else {
                nbWords = possibilities.stream().filter(word -> this.allowedword(word)).count();
            }
            log.debug("lineNb {}, nbWords {}",lineNb,nbWords);

            outputs.add(generateOutput(lineNb, nbWords));
        }
        fileService.writeListToOutputFile(outputs, "C-small-practice-2.out");

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
