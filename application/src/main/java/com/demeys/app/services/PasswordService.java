package com.demeys.app.services;

import com.google.common.base.Splitter;
import com.google.common.primitives.Chars;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by a508708 on 10/03/2017.
 */
@Slf4j
public class PasswordService {
    private final FileService fileService;

    @Inject
    public PasswordService(FileService fileService) {
        this.fileService = fileService;
    }

    public void generateLettersMessage(){
        final String filename = "/2016/D-small-practice-1";
        List<String> lines = fileService.getInputFileToList(filename);
        List<String> outputs = new ArrayList<>();
        for (int lineNb = 1, caseNb = 1; lineNb < lines.size(); lineNb = lineNb + 2, caseNb++) {
            log.debug("case #{}", caseNb);
            List<String> passwords = Splitter.on(" ").splitToList(lines.get(lineNb+1))
                    .stream().collect(Collectors.toList());
            outputs.add(messageFromPasswords(lineNb,passwords));
        }
        fileService.writeListToOutputFile(outputs,filename);
    }

    public String messageFromPasswords(Integer lineNb,List<String> passwords){
        if(passwords.stream().filter(pwd -> pwd.length() == 1).count() != 0){
            return (generateOutput(lineNb, "IMPOSSIBLE" ));
        } else {
            char[] message = IntStream.rangeClosed('A', 'Z')
                    .mapToObj(c -> "" + (char) c).collect(Collectors.joining()).toCharArray();
            LinkedList<Character> characterList = new LinkedList<>(Chars.asList(message));

            log.debug("characterList : {}",characterList);
            for(String pwd : passwords){
                if(generateMessage(characterList).contains(pwd)){
                    char[] pwdArray = pwd.toCharArray();

                }
            }
            return generateOutput(lineNb, "TODO" );
        }
    }

    public String generateOutput(int caseNb, String message) {
        String output = "Case #" + caseNb + ": " + message;
        return output;
    }

    public String generateMessage(LinkedList<Character> messageArray){
        String message = "";
        for (Character c : messageArray) {
            message += c;
        }
        return message;
    }
}
