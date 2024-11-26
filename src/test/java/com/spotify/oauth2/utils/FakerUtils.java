package com.spotify.oauth2.utils;

import com.github.javafaker.Faker;

public class FakerUtils {

    public static String generateName() {
        Faker faker = new Faker();
        String word = faker.lorem().word(); // Get a random word
        // Capitalize the first letter of the word
        String capitalizedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
        return faker.music().genre() + " Vibes - " + capitalizedWord;
    }

    public static String generateDescription() {
        Faker faker = new Faker();
        // Create a meaningful playlist description
        return "Description: This playlist features the best of " + faker.music().genre().toLowerCase() +
                " music for " + faker.lorem().word() + " moments. " +
                faker.lorem().sentence(12) + " Perfect for " + faker.lorem().word() + " and relaxation.";
    }

//    public static String generateDescription() {
//        Faker faker = new Faker();
//        return "Description: " + faker.lorem().sentence(50); // Generates a sentence with ~10 words
//    }

//        public static String generateDescription(){
//        Faker faker = new Faker();
//        return "Description " + faker.regexify("[ A-Za-z0-9_@./#&+-]{50}");
//    }
}
