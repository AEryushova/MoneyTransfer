package ru.netology.data;

import lombok.Value;

import java.util.Random;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthorizationInfo {
        String login;
        String password;
    }

    @Value
    public static class CodeVerification {
        String code;
    }

    @Value
    public static class CardsInfo {
        String cardNumber;
        String id;
    }

    public static AuthorizationInfo getAuthorizationInfo() {
        return new AuthorizationInfo("vasya", "qwerty123");
    }

    public static CodeVerification getCodeVerification(AuthorizationInfo authInfo) {
        return new CodeVerification("12345");
    }

    public static CardsInfo getFirstCardInfo() {
        return new CardsInfo("5559 0000 0000 0001", "92df3f1c-a033-48e6-8390-206f6b1f56c0");
    }

    public static CardsInfo getSecondCardInfo() {
        return new CardsInfo("5559 0000 0000 0002", "0f3f5c2a-249e-4c3d-8287-09f7a039391d");
    }

    public static int getTransferSum(int balance) {
        return new Random().nextInt(balance) + 1;
    }
}

