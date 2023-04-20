package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class AuthorizationPage {
    private final SelenideElement loginField = $("[data-test-id=login] input");
    private final SelenideElement passwordField = $("[data-test-id=password] input");
    private final SelenideElement authorizationButton = $("[data-test-id=action-login]");

    public VerificationCodePage validLoginAndPassword(DataHelper.AuthorizationInfo authinfo) {
        loginField.setValue(authinfo.getLogin());
        passwordField.setValue(authinfo.getPassword());
        authorizationButton.click();
        return new VerificationCodePage();
    }
}
