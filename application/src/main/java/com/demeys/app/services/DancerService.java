package com.demeys.app.services;

import com.google.common.base.Splitter;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by a508708 on 05/03/2017.
 */
@Slf4j
public class DancerService {
    private final FileService fileService;

    @Inject
    public DancerService(FileService fileService) {
        this.fileService = fileService;
    }

    public void getTheNeighbourgsOfK() {
        List<String> lines = fileService.getInputFileToList("input/B-large-practice.in");
        log.debug("line.size"+lines.size());
        List<String> outputs = new ArrayList<>();
        for (int lineNb = 1;lineNb < lines.size(); lineNb++) {
            log.debug("case #{}",lineNb);
            List<Long> inputline = Splitter.on(" ").splitToList(lines.get(lineNb))
                    .stream().map(Long::parseLong).collect(Collectors.toList());
            Long nbDancers = inputline.get(0);
            Long dancerK = inputline.get(1);
            Long nbTour = inputline.get(2);
            List<Long> dancers = LongStream.range(1,nbDancers+1).boxed().collect(Collectors.toList());
            log.debug("dancers:"+dancers.toString());
            //Move Dancers
            moveDancers(dancers,nbTour);
            //find K left & right
            ListIterator<Long> listIterator = dancers.listIterator();
            Long rightDancer= new Long(0);
            Long leftDancer= new Long(0);
            Long currentDancer;
            while (listIterator.hasNext() && rightDancer==0){
                currentDancer = listIterator.next();
                if(currentDancer==dancerK){
                    if(listIterator.previousIndex()>0){
                        rightDancer =dancers.get(listIterator.previousIndex()-1);
                    } else {
                        rightDancer = dancers.get(dancers.size()-1);
                    }
                    if(listIterator.hasNext()){
                        leftDancer = dancers.get(listIterator.nextIndex());
                    } else {
                        leftDancer = dancers.get(0);
                    }

                }
            }
            log.debug("dancer K <{}> has <{}> at his left and <{}> at his right",dancerK,leftDancer,rightDancer);
            outputs.add(generateOutput(lineNb,leftDancer,rightDancer));
        }
        fileService.writeListToOutputFile(outputs,"B-large-practice.out");
    }

    private String generateOutput(int caseNb, Long left, Long right) {
        String output = "Case #"+caseNb+": "+left+" "+right;
        System.out.println(output);
        return output;
    }

    public void moveDancers(List<Long> dancers, Long nbTour){
        for(int i=1;i<=nbTour;i++){
            int j = 0;
            if(i%2 != 0){
                log.debug("cas IMpair");

            } else {
                log.debug("cas PAIR");
                Long firstDancer = dancers.get(0);
                dancers.set(0, dancers.get(dancers.size()-1));
                dancers.set(dancers.size()-1, firstDancer);
                j = 1;
            }
            while(j<dancers.size()-1) {
                Long tempDancer = dancers.get(j);
                dancers.set(j,dancers.get(j+1));
                dancers.set(j+1,tempDancer);
                j=j+2;
            }
            log.debug("dancers after turn:"+dancers.toString());
        }
    }
}
