package com.theniche.colivin.domain.common;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AppCodeGenerator {
    private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int RANDOM_LENGTH = 4;

    public static String generateTenantCode() {
        // get current year and month
        LocalDate now = LocalDate.now();
        String year = now.format(DateTimeFormatter.ofPattern("yyyy"));
        String month = now.format(DateTimeFormatter.ofPattern("MM"));

        // generate 4 random characters
        StringBuilder randomPart = new StringBuilder(RANDOM_LENGTH);
        for (int i = 0; i < RANDOM_LENGTH; i++) {
            int index = RANDOM.nextInt(CHAR_POOL.length());
            randomPart.append(CHAR_POOL.charAt(index));
        }

        // final code format: yyyyMM-XXXX
        return year + month  + randomPart;
    }
}
