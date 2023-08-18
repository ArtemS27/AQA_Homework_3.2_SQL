package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class UserData {
    private UserData() {}

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfoWithData(){
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo generateRandomUser(){
        return new AuthInfo(generateRandomLogin(), generateRandomPassword());
    }

    private static String generateRandomLogin(){
        Faker faker = new Faker(new Locale("en"));
        return faker.name().username();
    }

    private static String generateRandomPassword(){
        Faker faker = new Faker(new Locale("en"));
        return  faker.internet().password();
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static String generateVerificationCode() {
        Faker faker = new Faker(new Locale("en"));
        return faker.numerify("######");
    }

    @Value
    public static class CardNumber {
        private String number;
        private String cardId;
    }

}
