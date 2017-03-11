package com.demeys.app.services;

import com.google.common.base.Splitter;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by a508708 on 10/03/2017.
 */
@Slf4j
public class SampleService {
    private final FileService fileService;

    @Inject
    public SampleService(FileService fileService) {
        this.fileService = fileService;
    }

    public void TODOmethodName(){
        final String filename = "TODO-filename";
        List<String> lines = fileService.getInputFileToList(filename);
        List<String> outputs = new ArrayList<>();
        for (int lineNb = 1; lineNb < lines.size(); lineNb++) {
            log.debug("case #{}", lineNb);
            //input
            List<Integer> inputline = Splitter.on(" ").splitToList(lines.get(lineNb))
                    .stream().map(Integer::parseInt).collect(Collectors.toList()); //TODO type of element in list

            outputs.add(generateOutput(lineNb, "TODO" ));
        }
        fileService.writeListToOutputFile(outputs,filename);
    }

    private String generateOutput(int caseNb, String message) {
        String output = "Case #" + caseNb + ": " + message;
        return output;
    }
}
