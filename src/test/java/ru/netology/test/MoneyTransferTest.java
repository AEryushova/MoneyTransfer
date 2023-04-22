package ru.netology.test;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.AuthorizationPage;
import ru.netology.page.PersonalAccountPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    private PersonalAccountPage personalAccountPage;

    @BeforeEach
    void shouldToGoToPersonalAccount() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        var authorizationPage = new AuthorizationPage();
        var authInfo = DataHelper.getAuthorizationInfo();
        var verificationCodePage = authorizationPage.validLoginAndPassword(authInfo);
        var valCode = DataHelper.getCodeVerification(authInfo);
         personalAccountPage=verificationCodePage.validCode(valCode);
    }
    @Test
    void shouldTransferMoneyFirstToSecondCardHappyPath() {
        var firstCard = DataHelper.getFirstCardInfo();
        var secondCard = DataHelper.getSecondCardInfo();
        int balanceFirstCard = personalAccountPage.getCardBalance(DataHelper.getFirstCardInfo());
        int balanceSecondCard = personalAccountPage.getCardBalance(DataHelper.getFirstCardInfo());
        int sumTransfer = DataHelper.getTransferSum(balanceFirstCard);
        int expectedBalanceFirstCard = balanceFirstCard - sumTransfer;
        int expectedBalanceSecondCard = balanceSecondCard + sumTransfer;
        var transferMoneyPage = personalAccountPage.selectCardToTransfer(secondCard);
        var personalAccountPageHappyTransfer = transferMoneyPage.transferMoney(sumTransfer, firstCard);
        var actualBalanceFirstCard = personalAccountPage.getCardBalance(DataHelper.getFirstCardInfo());
        var actualBalanceSecondCard = personalAccountPage.getCardBalance(DataHelper.getSecondCardInfo());
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
}