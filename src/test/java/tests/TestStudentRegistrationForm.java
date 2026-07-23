package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static testdata.StudentRegistrationTestData.*;

public class TestStudentRegistrationForm extends TestBase {

    private final TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void successfulFillFormTest() {
        textBoxPage
                .openPage()
                .fillForm(FIRST_NAME, LAST_NAME, EMAIL, GENDER, MOBILE_NUMBER,
                        DAY, MONTH, YEAR, SUBJECT, HOBBY, PICTURE_NAME,
                        ADDRESS, STATE, CITY)
                .submitForm();

        textBoxPage.getModal().verifyModalVisible();

        textBoxPage.getTable()
                .verifyStudentName(FIRST_NAME, LAST_NAME)
                .verifyStudentEmail(EMAIL)
                .verifyGender(GENDER)
                .verifyMobile(MOBILE_NUMBER)
                .verifyDateOfBirth(DAY, MONTH, YEAR)
                .verifySubjects(SUBJECT)
                .verifyHobbies(HOBBY)
                .verifyPicture(PICTURE_NAME)
                .verifyAddress(ADDRESS)
                .verifyStateAndCity(STATE, CITY);

        textBoxPage.getModal().closeModal();
    }

    @Test
    void successfulMandatoryFieldsTest() {
        textBoxPage
                .openPage()
                .fillMandatoryFields(FIRST_NAME, LAST_NAME, EMAIL, GENDER, MOBILE_NUMBER)
                .submitForm();

        textBoxPage.getModal().verifyModalVisible();

        textBoxPage.getTable()
                .verifyStudentName(FIRST_NAME, LAST_NAME)
                .verifyStudentEmail(EMAIL)
                .verifyGender(GENDER)
                .verifyMobile(MOBILE_NUMBER);

        textBoxPage.getModal().closeModal();
    }

    @Test
    void negativeTestWhenFirstNameIsEmpty() {
        textBoxPage
                .openPage()
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setGender(GENDER)
                .setMobile(MOBILE_NUMBER)
                .scrollToSubmit()
                .submitForm();

        textBoxPage.getModal().verifyModalNotVisible();

        SelenideElement firstNameField = $("#firstName");
        firstNameField.shouldHave(attribute("required"));

        boolean isValid = executeJavaScript("return arguments[0].checkValidity();", firstNameField);
        assert !isValid : "Field should be invalid when empty";
    }

    @Test
    void negativeTestWhenLastNameIsEmpty() {
        textBoxPage
                .openPage()
                .setFirstName(FIRST_NAME)
                .setEmail(EMAIL)
                .setGender(GENDER)
                .setMobile(MOBILE_NUMBER)
                .scrollToSubmit()
                .submitForm();

        textBoxPage.getModal().verifyModalNotVisible();

        SelenideElement lastNameField = $("#lastName");
        lastNameField.shouldHave(attribute("required"));

        boolean isValid = executeJavaScript("return arguments[0].checkValidity();", lastNameField);
        assert !isValid : "Field should be invalid when empty";
    }

    @Test
    void negativeTestWhenMobileIsEmpty() {
        textBoxPage
                .openPage()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setGender(GENDER)
                .scrollToSubmit()
                .submitForm();

        textBoxPage.getModal().verifyModalNotVisible();

        SelenideElement mobileField = $("#userNumber");
        mobileField.shouldHave(attribute("required"));

        boolean isValid = executeJavaScript("return arguments[0].checkValidity();", mobileField);
        assert !isValid : "Field should be invalid when empty";
    }

    @Test
    void negativeTestWhenGenderIsEmpty() {
        textBoxPage
                .openPage()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setMobile(MOBILE_NUMBER)
                .scrollToSubmit()
                .submitForm();

        textBoxPage.getModal().verifyModalNotVisible();

        // Проверяем, что ни одна радиокнопка не выбрана
        $("#gender-radio-1").shouldNotBe(checked);
        $("#gender-radio-2").shouldNotBe(checked);
        $("#gender-radio-3").shouldNotBe(checked);

        boolean isFormValid = executeJavaScript("return document.querySelector('form').checkValidity();");
        assert !isFormValid : "Form should be invalid when gender not selected";
    }
}