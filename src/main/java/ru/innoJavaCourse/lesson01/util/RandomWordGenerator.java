package ru.innoJavaCourse.lesson01.util;

import java.util.Random;

public class RandomWordGenerator {

    //for name: getRandomName(3,3)
    //for value: getRandomName(3,10)

    public static String getRandomWord(int minLength, int maxLength) {
        Random random = new Random();
        char[] word = new char[random.nextInt(maxLength - minLength + 1) + minLength];
        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + random.nextInt(26));
        }
        return new String(word);
    }
}
