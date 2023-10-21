package br.com.optimate.manager.config;

import org.apache.maven.surefire.shared.lang3.RandomStringUtils;

import java.util.Random;

public class UtilsRandom {

    public static String generatePassword() {
        return RandomStringUtils.randomAlphanumeric(60);
    }

    public static String generateColorHex() {
        // create random object - reuse this as often as possible
        Random random = new Random();
        // create a big random number - maximum is ffffff (hex) = 16777215 (dez)
        int nextInt = random.nextInt(256 * 256 * 256);
        // format it as hexadecimal string
        return String.format("%06x", nextInt);
    }
}
