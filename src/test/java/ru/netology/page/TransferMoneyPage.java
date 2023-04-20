package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TransferMoneyPage {
    private final SelenideElement sumField = $("[data-test-id='amount'] input");
    private final SelenideElement fromField = $("[data-test-id='from'] input");
    private final SelenideElement transferButton = $("[data-test-id='action-transfer']");
    private final SelenideElement cancelButton = $("[data-test-id='action-cancel']");

    public PersonalAccountPage transferMoney(int sumTransfer, DataHelper.CardsInfo cardsInfo) {
        sumField.setValue(String.valueOf(sumTransfer));
        fromField.setValue(cardsInfo.getCardNumber());
        transferButton.click();
        return new PersonalAccountPage();
    }

    public PersonalAccountPage cancelTransferMoney(int sumTransfer, DataHelper.CardsInfo cardsInfo) {
        sumField.setValue(String.valueOf(sumTransfer));
        fromField.setValue(cardsInfo.getCardNumber());
        cancelButton.click();
        return new PersonalAccountPage();
    }
}
