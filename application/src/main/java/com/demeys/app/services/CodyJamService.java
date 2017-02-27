package com.demeys.app.services;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import freemarker.template.utility.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Fanny on 26/02/2017.
 */
public class CodyJamService {
    private final FileService fileService;

    @Inject
    public CodyJamService(FileService fileService){
        this.fileService=fileService;
    }

    public List<String> getReducedPrices(){
        List<String> lines =fileService.getInputFileToList("input/A-small-practice.in");
        List<String> outputs = new ArrayList<>();
        for (int lineNb =1, caseNb=1; lineNb<lines.size();lineNb=lineNb+2,caseNb++) {
            Long nbItems = Long.parseLong(lines.get(lineNb));
            System.out.print("nbItems"+nbItems);

            List<Long> prices = Splitter.on(" ").splitToList(lines.get(lineNb+1))
                    .stream().map(Long::parseLong).collect(Collectors.toList());

             List<Long> nonReducedPrices = Lists.reverse(prices).stream()
                    .filter(price -> this.isAStandartPrice(price,prices))
                     .collect(Collectors.toList());
            System.out.println("nonReducedPrices"+nonReducedPrices.toString());

            List<Long> reducedPrices = prices;
            reducedPrices.removeAll(nonReducedPrices);

             //Case doublon
            List<Long> toBeRemoved = new ArrayList<>();
            while(reducedPrices.size()<nbItems){
                System.out.println("Trop de nonReducedPrices");
                for(Long next : nonReducedPrices){
                    if(nonReducedPrices.contains(next*75/100)){
                        System.out.println("price "+ next + "contient son 75");
                        if(!toBeRemoved.contains(next*75/100)){
                            System.out.println("pas encore en liste");
                            toBeRemoved.add(next*75/100);
                            reducedPrices.add(next*75/100);
                            System.out.println("reducedPrices after adding"+reducedPrices);

                        }
                    }
                }
                nonReducedPrices.removeAll(toBeRemoved);
            }
            Collections.sort(reducedPrices);
            outputs.add(generateOutput(caseNb,reducedPrices));
        }
        fileService.writeListToOutputFile(outputs);
        return outputs;
    }

    private String generateOutput(int caseNb, List<Long> reducedPrice) {
        String output = "Case #"+caseNb+": "+ StringUtils.join(reducedPrice, " ");
        System.out.println(output);
        return output;
    }

    private Boolean isAStandartPrice(Long price, List<Long> prices){
        Long reducedPrice = price*75/100;
        if(prices.contains(price*75/100)){
            Long doubleReducedPrice = reducedPrice*75/100;
            return true;
        }
        return false;
    }

}
