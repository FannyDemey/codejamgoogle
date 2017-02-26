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
            Integer nbItems = Integer.parseInt(lines.get(lineNb));
            System.out.print("nbItems"+nbItems);

            List<Integer> prices = Splitter.on(" ").splitToList(lines.get(lineNb+1))
                    .stream().map(Integer::parseInt).collect(Collectors.toList());

             List<Integer> nonReducedPrices = Lists.reverse(prices).stream()
                    .filter(price -> this.isAStandartPrice(price,prices))
                     .collect(Collectors.toList());
            System.out.print(nonReducedPrices.toString());

            List<Integer> reducedPrices = prices;
            reducedPrices.removeAll(nonReducedPrices);

             //Case doublon
            List<Integer> toBeRemoved = new ArrayList<>();
            if(reducedPrices.size()<nbItems){
                System.out.println("Trop de nonReducedPrices");
                for(Integer next : nonReducedPrices){
                    if(nonReducedPrices.contains(next/75*100)){
                        System.out.println("price "+ next + "contient son 75");
                        if(!toBeRemoved.contains(next/75*100)){
                            System.out.println("pas encore en liste");
                            reducedPrices.add(next*75/100);
                        }
                    }
                }
                nonReducedPrices.removeAll(toBeRemoved);
            }

            outputs.add(generateOutput(caseNb,prices));
        }
        fileService.writeListToOutputFile(outputs);
        return outputs;
    }

    private String generateOutput(Integer caseNb, List<Integer> reducedPrice) {
        String output = "Case #"+caseNb+": "+ StringUtils.join(reducedPrice, " ");
        System.out.println(output);
        return output;
    }

    private Boolean isAStandartPrice(Integer price, List<Integer> prices){
        Integer reducedPrice = price*75/100;
        if(prices.contains(price*75/100)){
            Integer doubleReducedPrice = reducedPrice*75/100;
            return true;
        }
        return false;
    }

}
