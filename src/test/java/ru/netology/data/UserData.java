package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;
import java.util.Random;

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

    public static CardNumber getFirstCardNumber() {
        return new CardNumber("5559 0000 0000 0001", "'92df3f1c-a033-48e6-8390-206f6b1f56c0'");
    }

    public static CardNumber getSecondCardNumber() {
        return new CardNumber("5559 0000 0000 0002", "'0f3f5c2a-249e-4c3d-8287-09f7a039391d'");
    }

    public static String generateValidAmount(int balance){
        String amount = String.valueOf(new Random().nextInt(Math.abs(balance)+1));
        return amount;
    }

    public static String generateInvalidAmount(int balance){
        String amount = String.valueOf(Math.abs(balance) + new Random().nextInt(10000));
        return amount;
    }
}
