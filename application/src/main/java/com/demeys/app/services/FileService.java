package com.demeys.app.services;

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
public class FileService {

    public List<String> getInputFileToList(String fileName){
        try {
            System.out.print("File to List of String");
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            return lines;
        } catch (IOException e){
            System.out.print("Pb reading file");
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

    public void writeListToOutputFile(List<String> outputs){
        try{
            Path outputfile = Paths.get("output/output.txt");
            Files.write(outputfile,outputs, Charset.defaultCharset());
        } catch (IOException e){

        }
    }

}
