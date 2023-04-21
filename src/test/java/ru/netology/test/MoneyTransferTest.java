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
    @BeforeEach
    void shouldToGoToPersonalAccount() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
        var authorizationPage = new AuthorizationPage();
        var authInfo = DataHelper.AuthorizationInfo.getAuthorizationInfo();
        var verificationCodePage = authorizationPage.validLoginAndPassword(authInfo);
        var valCode = DataHelper.CodeVerification.getCodeVerification(authInfo);
        verificationCodePage.validCode(valCode);
    }

    @Test
    void shouldTransferMoneyFirstToSecondCardHappyPath() {
        var personalAccountPage = new PersonalAccountPage();
        var firstCard = DataHelper.CardsInfo.getFirstCardInfo();
        var secondCard = DataHelper.CardsInfo.getSecondCardInfo();
        int balanceFirstCard = personalAccountPage.getCardBalance(DataHelper.CardsInfo.getFirstCardInfo().getId());
        int balanceSecondCard = personalAccountPage.getCardBalance(DataHelper.CardsInfo.getSecondCardInfo().getId());
        int sumTransfer = DataHelper.TransferSum.getTransferSum(balanceFirstCard);
        int expectedBalanceFirstCard = balanceFirstCard - sumTransfer;
        int expectedBalanceSecondCard = balanceSecondCard + sumTransfer;
        var transferMoneyPage = personalAccountPage.selectCardToTransfer(secondCard);
        var personalAccountPageHappyTransfer = transferMoneyPage.transferMoney(sumTransfer, firstCard);
        var actualBalanceFirstCard = personalAccountPage.getCardBalance(DataHelper.CardsInfo.getFirstCardInfo().getId());
        var actualBalanceSecondCard = personalAccountPage.getCardBalance(DataHelper.CardsInfo.getSecondCardInfo().getId());
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
}