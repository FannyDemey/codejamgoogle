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
public class ConcertService {
    private final FileService fileService;

    @Inject
    public ConcertService(FileService fileService) {
        this.fileService = fileService;
    }

    public void TODOmethodName(){
        final String filename = "A-large";
        List<String> lines = fileService.getInputFileToList(filename);
        List<String> outputs = new ArrayList<>();
        Integer nbTest = Integer.parseInt(lines.get(0));
        log.debug("nbTest:"+nbTest);
        List<Integer> line1 = Splitter.on(" ").splitToList(lines.get(1))
                .stream().map(Integer::parseInt).collect(Collectors.toList());
        Integer nbFriends = line1.get(0);
        Integer rowSize = line1.get(1);
        log.debug("nbFriends:"+nbFriends);
        for (int lineNb = 2, caseNb = 1; lineNb < lines.size(); lineNb = lineNb + 1, caseNb++) {
            log.debug("nbFriends:"+nbFriends);
            log.debug("rowSize:"+rowSize);
            List<List<Integer>> tickets = new ArrayList<>();
            for(int i =0; i<nbFriends;i++){
                List<Integer> ticket = Splitter.on(" ").splitToList(lines.get(lineNb))
                        .stream().map(Integer::parseInt).collect(Collectors.toList());
                tickets.add(ticket);
                log.debug("ticket:"+ticket);
                lineNb++;
            }
            tickets = tickets.stream().distinct().collect(Collectors.toList());
            List<Integer> maxFriendInEachRows = new ArrayList<>();
            for(int j=1; j<=rowSize;j++){
                Integer maxFriendForThisRow=0;
                for(List<Integer> t : tickets){
                    if(t.contains(j)){
                        maxFriendForThisRow++;
                    }
                }
                maxFriendInEachRows.add(maxFriendForThisRow);
            }
            Integer max = Collections.max(maxFriendInEachRows);
            outputs.add(generateOutput(caseNb, max ));
            if(caseNb<nbTest){
                List<Integer> nextLine = Splitter.on(" ").splitToList(lines.get(lineNb))
                        .stream().map(Integer::parseInt).collect(Collectors.toList());
                log.debug("nextLine"+nextLine);
                nbFriends = nextLine.get(0);
                rowSize = nextLine.get(1);
            } else {
                lineNb = lines.size();
            }
        }
        fileService.writeListToOutputFile(outputs,filename);

    }

    private String generateOutput(int caseNb, Integer result) {
        String output = "Case #" + caseNb + ": " + result;
        log.debug(output);
        return output;
    }
}
