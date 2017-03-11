package com.demeys.app.services;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by A508708 on 11/03/2017.
 */
@Slf4j
public class IOPrinterService {

    private final FileService fileService;

    @Inject
    public IOPrinterService(FileService fileService) {
        this.fileService = fileService;
    }

    public void TODOmethodName(){
        final String filename = "C-small-attempt0";
        List<String> lines = fileService.getInputFileToList(filename);
        List<String> outputs = new ArrayList<>();
        for (int lineNb = 1; lineNb < lines.size(); lineNb++) {
            log.debug("case #{}", lineNb);
            //input
            List<Integer> inputline = Splitter.on(" ").splitToList(lines.get(lineNb))
                    .stream().map(Integer::parseInt).collect(Collectors.toList()); //TODO type of element in list
            Integer size = inputline.get(0);
            Integer nbIOToPrint = inputline.get(1);
            log.debug("size {} nbIO {}",size,nbIOToPrint);
            List<String> result = new ArrayList<>();
            String line = "O";
            for(int i=0;i<nbIOToPrint;i++){
                if(line.length()+4<size){
                    line+="I/OO";
                }
                else{
                    if(line.length()<size){
                        line+=Strings.repeat("O",size-line.length()-1);
                    }
                    result.add(line);
                    result.add(Strings.repeat("O",size-1));
                    line="OI/O";
                }
            }
            if(line.length()<size){
                line+=Strings.repeat("O",size-line.length()-1);
            }
            result.add(line);
            log.debug(""+result);
            outputs.add(generateOutput(lineNb, result ));
        }
        fileService.writeListToOutputFile(outputs,filename);
    }

    private String generateOutput(int caseNb, List<String> result) {
        String output = "Case #" + caseNb +":" ;
        for (String l : result){
            output+="\n"+l;
        }
        return output;
    }
}
