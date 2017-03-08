package com.demeys.app.services;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Fanny on 26/02/2017.
 */
@Slf4j
public class FileService {

    public List<String> getInputFileToList(String fileName){
        try {
            String path = "input/"+fileName+".in";
            log.info("Reading file named :<{}>",path);
            List<String> lines = Files.readAllLines(Paths.get(path));
            return lines;
        } catch (IOException e){
            log.info("Pb reading file");
            return null;
        }
    }

    public Stream<String> getInputFileToStream(String fileName){
        try {
            Stream<String> lines = Files.lines(Paths.get(fileName));
            return lines;
        } catch (IOException e){
            System.out.print("Pb reading file");
            return null;
        }
    }

    public void writeListToOutputFile(List<String> outputs, String fileName){
        try{
            String filePath = "output/"+fileName + ".out";
            Path outputfile = Paths.get(filePath);
            Files.write(outputfile,outputs, Charset.defaultCharset());
        } catch (IOException e){

        }
    }

}
