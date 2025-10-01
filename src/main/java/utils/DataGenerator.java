package utils;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class DataGenerator {
    public static String generateLogin() {
        var timestamp = System.currentTimeMillis();
        return "testplayer_" + timestamp;
    }

    public static String generateScreenName() {
        var random = new Random();
        return "testscreen_" + random.nextInt(99999);
    }
    public static String generatePassword() {
        final String LETTERS = "abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ";
        final String NUMBERS = "23456789";
        final String ALPHANUMERIC = LETTERS + NUMBERS;
        SecureRandom secureRandom = new SecureRandom();
        final int PASSWORD_LENGTH = ThreadLocalRandom.current().nextInt(7, 16);
        StringBuilder passwordBuilder = new StringBuilder(PASSWORD_LENGTH);
        passwordBuilder.append(LETTERS.charAt(secureRandom.nextInt(LETTERS.length())));
        passwordBuilder.append(NUMBERS.charAt(secureRandom.nextInt(NUMBERS.length())));
        int remainingLength = PASSWORD_LENGTH - 2;
        String randomFiller = secureRandom.ints(remainingLength, 0, ALPHANUMERIC.length())
                .mapToObj(ALPHANUMERIC::charAt)
                .map(String::valueOf)
                .collect(Collectors.joining());


        return passwordBuilder.append(randomFiller).toString();
    }

    public static int generateAge() {
        return ThreadLocalRandom.current().nextInt(17, 60);
    }

    public static int generateInvalidAge() {
        boolean lessThanSeventeen = ThreadLocalRandom.current().nextBoolean();
        if (lessThanSeventeen) {
            return ThreadLocalRandom.current().nextInt(0, 17);
        } else {
            return ThreadLocalRandom.current().nextInt(61, 101);
        }
    }
}

