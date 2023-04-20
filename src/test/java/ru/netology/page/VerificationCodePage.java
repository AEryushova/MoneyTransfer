package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class VerificationCodePage {
    private final SelenideElement codeField = $("[data-test-id=code] input");
    private final SelenideElement codeButton = $("[data-test-id=action-verify]");

    public VerificationCodePage(){
        codeField.shouldBe(Condition.visible);
    }
    public PersonalAccountPage validCode(DataHelper.CodeVerification codeInfo) {
        codeField.setValue(codeInfo.getCode());
        codeButton.click();
        return new PersonalAccountPage();
    }
}
