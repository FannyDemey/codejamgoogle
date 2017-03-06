package com.demeys.app.services;

import com.google.common.base.Splitter;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
            List<Integer> inputline = Splitter.on(" ").splitToList(lines.get(lineNb))
                    .stream().map(Integer::parseInt).collect(Collectors.toList());
            Integer nbDancers = inputline.get(0);
            Integer dancerK = inputline.get(1);
            Integer nbTour = inputline.get(2);
            Integer rightDancer,leftDancer;
            Integer dancerKInitialPosition = dancerK -1; //go from num dancer to index
            Integer rightDancerInitialPosition,leftDancerInitialPosition;
            Integer rightDancerPosition,leftDancerPosition;
            nbTour = nbTour % nbDancers;
            //Step 1 : Find Dancer K
            Integer dancerKFinalPosition = (dancerKInitialPosition %2 == 0)? ((dancerKInitialPosition)+nbTour):((dancerKInitialPosition)-nbTour);
            dancerKFinalPosition = dancerKFinalPosition % nbDancers;
            while(dancerKFinalPosition<0){
                dancerKFinalPosition+=nbDancers;
            }
            //find left
            leftDancerPosition = (dancerKFinalPosition + 1)  % nbDancers;
            rightDancerPosition = (dancerKFinalPosition -1 ) % nbDancers;
            if(rightDancerPosition<0) {
                rightDancerPosition+= nbDancers;
            }
            log.debug("dancerKFinalPosition {}, left position {}, right position {}",dancerKFinalPosition,leftDancerPosition,rightDancerPosition);

            if(dancerKInitialPosition %2 != 0){
                rightDancerInitialPosition = (rightDancerPosition - nbTour) % nbDancers;
                leftDancerInitialPosition = (leftDancerPosition - nbTour) % nbDancers;
                if(rightDancerInitialPosition<0) {
                    rightDancerInitialPosition += nbDancers;
                }
                if(leftDancerInitialPosition<0){
                    leftDancerInitialPosition += nbDancers;
                }
            } else {
                rightDancerInitialPosition = (rightDancerPosition + nbTour) % nbDancers;
                leftDancerInitialPosition = (leftDancerPosition + nbTour) % nbDancers;
            }

            leftDancer = leftDancerInitialPosition +1; //go from index to num dancer
            rightDancer = rightDancerInitialPosition +1; //go from index to num dancer

            log.debug("dancer K <{}> has <{}> left position and <{}> right position ",dancerK,leftDancerPosition,rightDancerPosition);
            log.debug("dancer K <{}> has <{}> at his left and <{}> at his right",dancerK,leftDancer,rightDancer);
            outputs.add(generateOutput(lineNb,leftDancer,rightDancer));
        }
        fileService.writeListToOutputFile(outputs,"B-large-practice.out");
    }

    private String generateOutput(int caseNb, Integer left, Integer right) {
        String output = "Case #"+caseNb+": "+left+" "+right;
        System.out.println(output);
        return output;
    }

    public void moveDancers(List<Integer> dancers, Integer nbTour){
        for(int i=1;i<=nbTour;i++){
            int j = 0;
            if(i%2 != 0){
                log.debug("cas IMpair");

            } else {
                log.debug("cas PAIR");
                Integer firstDancer = dancers.get(0);
                dancers.set(0, dancers.get(dancers.size()-1));
                dancers.set(dancers.size()-1, firstDancer);
                j = 1;
            }
            while(j<dancers.size()-1) {
                Integer tempDancer = dancers.get(j);
                dancers.set(j,dancers.get(j+1));
                dancers.set(j+1,tempDancer);
                j=j+2;
            }
            log.debug("dancers after turn:"+dancers.toString());
        }
    }
}
