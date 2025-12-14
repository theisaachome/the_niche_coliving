package com.theniche.colivin.util;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CodeGenerator {
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int RANDOM_LENGTH = 6;

    private  static String prefixFormat = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));

    public static String generateTenantCode() {

        // generate 4 random characters
        StringBuilder randomPart = new StringBuilder(RANDOM_LENGTH);
        for (int i = 0; i < RANDOM_LENGTH; i++) {
            int index = RANDOM.nextInt(CHAR_POOL.length());
            randomPart.append(CHAR_POOL.charAt(index));
        }
        // final code format: yyyyMM-XXXX
        return  "T" + randomPart+prefixFormat;
    }

    public static String generateHouseCode() {
        // generate 4 random characters
        StringBuilder randomPart = new StringBuilder(RANDOM_LENGTH);
        for (int i = 0; i < RANDOM_LENGTH; i++) {
            int index = RANDOM.nextInt(CHAR_POOL.length());
            randomPart.append(CHAR_POOL.charAt(index));
        }
        // final code format: yyyyMM-XXXX
        return   "H"+ randomPart+prefixFormat;
    }
    public static String generateRoomCode() {
        // generate 4 random characters
        StringBuilder randomPart = new StringBuilder(RANDOM_LENGTH);
        for (int i = 0; i < RANDOM_LENGTH; i++) {
            int index = RANDOM.nextInt(CHAR_POOL.length());
            randomPart.append(CHAR_POOL.charAt(index));
        }
        // final code format: yyyyMM-XXXX
        return   "R"+ randomPart+prefixFormat;
    }

    public static void main(String[] args) {
        System.out.println(CodeGenerator.generateHouseCode());
        System.out.println(CodeGenerator.generateTenantCode());
        System.out.println(CodeGenerator.generateRoomCode());
    }
}
