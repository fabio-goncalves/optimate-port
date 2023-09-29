package br.com.optimate.manager.config;

import org.apache.maven.surefire.shared.lang3.RandomStringUtils;

public class UtilsRandom {

    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(60);
    }
}
