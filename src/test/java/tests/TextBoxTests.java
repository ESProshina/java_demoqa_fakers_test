package tests;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TextBoxTestData.*;

public class TextBoxTests extends TestBase {

    @Test
    void successfulFillFormTest() {
        open("/text-box");
        $("[id=userName]").setValue(USER_NAME);
        $("[id=userEmail]").setValue(USER_EMAIL);
        $("[id=currentAddress]").setValue(CURRENT_ADDRESS);
        $("[id=permanentAddress]").setValue(PERMANENT_ADDRESS);
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text(USER_NAME));
        $("[id=output] [id=email]").shouldHave(text(USER_EMAIL));
        $("[id=output] [id=currentAddress]").shouldHave(text(CURRENT_ADDRESS));
        $("[id=output] [id=permanentAddress]").shouldHave(text(PERMANENT_ADDRESS));
    }
}