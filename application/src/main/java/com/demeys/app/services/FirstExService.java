package com.demeys.app.services;

import com.google.common.base.Splitter;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by a508708 on 10/03/2017.
 */
@Slf4j
public class FirstExService {
    private final FileService fileService;

    @Inject
    public FirstExService(FileService fileService) {
        this.fileService = fileService;
    }

    public void TODOmethodName(){
        final String filename = "B-large";
        List<String> lines = fileService.getInputFileToList(filename);
        List<String> outputs = new ArrayList<>();
        for (int lineNb = 1, caseNb = 1; lineNb < lines.size(); lineNb = lineNb + 2, caseNb++) {
            log.debug("caseNb #{}", caseNb);
            Integer nnbActors = Integer.parseInt(lines.get(lineNb));
            //input
            List<Double> probabilities = Splitter.on(" ").splitToList(lines.get(lineNb+1))
                    .stream().map(Double::parseDouble).collect(Collectors.toList()); //TODO type of element in list
            log.debug("probabilities:{}", probabilities);
            Collections.sort(probabilities);
            log.debug("probabilities:{}", probabilities);
            Double result = new Double(1.0);
            Integer size = probabilities.size();
            for(int i=0;i<probabilities.size()/2;i++){
                log.debug("{} * {}", probabilities.get(i), probabilities.get(size-1-i));
                result *= 1-(probabilities.get(i)*probabilities.get(size-1-i));
            }
            outputs.add(generateOutput(caseNb, result ));
        }
        fileService.writeListToOutputFile(outputs,filename);
    }

    private String generateOutput(int caseNb, Double proba) {
        String output = "Case #" + caseNb + ": " + proba;
        log.debug(output);
        return output;
    }
}
