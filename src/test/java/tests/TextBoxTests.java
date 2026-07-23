package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import utils.RandomUtils;

import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.attribute;

public class TextBoxTests extends TestBase {

    private final TextBoxPage textBoxPage = new TextBoxPage();
    private final Faker faker = new Faker(new Locale("en-US"));

    private String fullName;
    private String email;
    private String currentAddress;
    private String permanentAddress;

    @BeforeEach
    void setUpTestData() {
        fullName = faker.name().fullName();
        email = faker.internet().emailAddress(fullName.toLowerCase().replace(" ", "."));
        currentAddress = faker.address().fullAddress();
        permanentAddress = faker.address().fullAddress();
    }

    @Test
    void successfulFillFormTest() {
        textBoxPage
                .openTextBoxPage()  // Используем правильный метод для Text Box
                .setFullName(fullName)
                .setEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .clickSubmit();

        textBoxPage.getOutput()
                .shouldHave(text("Name:" + fullName))
                .shouldHave(text("Email:" + email))
                .shouldHave(text("Current Address :" + currentAddress))
                .shouldHave(text("Permananet Address :" + permanentAddress))
                .shouldBe(visible);
    }

    @Test
    void negativeTestWithInvalidEmail() {
        String invalidEmail = faker.internet().emailAddress().replace("@", "at");

        textBoxPage
                .openTextBoxPage()
                .setFullName(fullName)
                .setEmail(invalidEmail)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress)
                .clickSubmit();

        textBoxPage.getEmailField().shouldHave(attribute("class", "field-error"));
    }

    @Test
    void negativeTestWithEmptyFields() {
        textBoxPage
                .openTextBoxPage()
                .clickSubmit();

        textBoxPage.getFullNameField().shouldHave(attribute("class", "field-error"));
        textBoxPage.getEmailField().shouldHave(attribute("class", "field-error"));
    }

    @Test
    void testWithRandomDataUsingUtils() {
        String randomFullName = RandomUtils.getRandomFirstName() + " " + RandomUtils.getRandomLastName();
        String randomEmail = RandomUtils.getRandomEmail();
        String randomCurrentAddress = RandomUtils.getRandomAddress();
        String randomPermanentAddress = RandomUtils.getRandomAddress();

        textBoxPage
                .openTextBoxPage()
                .setFullName(randomFullName)
                .setEmail(randomEmail)
                .setCurrentAddress(randomCurrentAddress)
                .setPermanentAddress(randomPermanentAddress)
                .clickSubmit();

        textBoxPage.getOutput()
                .shouldHave(text("Name:" + randomFullName))
                .shouldHave(text("Email:" + randomEmail))
                .shouldHave(text("Current Address :" + randomCurrentAddress))
                .shouldHave(text("Permananet Address :" + randomPermanentAddress))
                .shouldBe(visible);
    }
}