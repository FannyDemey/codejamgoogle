package com.demeys.app.services;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by a508708 on 11/03/2017.
 */
public class PasswordServiceTest {

    private final FileService fileService = new FileService();
    private final PasswordService passwordService = new PasswordService(fileService);

    @Test
    public void generateMessage() throws Exception {
        List<String> passwords = new ArrayList<>();
        passwords.add("AB");
        passwords.add("REMI");
        passwords.add("MIRE");

        String result = passwordService.messageFromPasswords(1,passwords);
        assertThat(result).isEqualTo("Case #1: TODO");
    }

}