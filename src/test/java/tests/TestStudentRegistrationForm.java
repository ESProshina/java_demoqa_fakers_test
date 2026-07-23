package tests;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import utils.RandomUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class TestStudentRegistrationForm extends TestBase {

    private final TextBoxPage textBoxPage = new TextBoxPage();

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String mobileNumber;
    private String day;
    private String month;
    private String year;
    private String subject;
    private String hobby;
    private String pictureName;
    private String address;
    private String state;
    private String city;

    @BeforeEach
    void setUpTestData() {
        firstName = RandomUtils.getRandomFirstName();
        lastName = RandomUtils.getRandomLastName();
        email = RandomUtils.getRandomEmail(firstName, lastName);
        gender = RandomUtils.getRandomGender();
        mobileNumber = RandomUtils.getRandomMobileNumber();
        day = RandomUtils.getRandomDay();
        month = RandomUtils.getRandomMonth();
        year = RandomUtils.getRandomYear();
        subject = RandomUtils.getRandomSubject();
        hobby = RandomUtils.getRandomHobby();
        pictureName = "testbest.png";
        address = RandomUtils.getRandomAddress();
        state = RandomUtils.getRandomState();
        city = RandomUtils.getRandomCity(state);

        System.out.println("Test data:");
        System.out.println("State: " + state);
        System.out.println("City: " + city);
    }

    @Test
    void successfulFillFormTest() {
        textBoxPage
                .openPage()
                .fillForm(firstName, lastName, email, gender, mobileNumber,
                        day, month, year, subject, hobby, pictureName,
                        address, state, city)
                .submitForm();

        // Ждем появления модального окна
        sleep(1000);

        textBoxPage.getModal().verifyModalVisible();

        textBoxPage.getTable()
                .verifyStudentName(firstName, lastName)
                .verifyStudentEmail(email)
                .verifyGender(gender)
                .verifyMobile(mobileNumber)
                .verifyDateOfBirth(day, month, year)
                .verifySubjects(subject)
                .verifyHobbies(hobby)
                .verifyPicture(pictureName)
                .verifyAddress(address)
                .verifyStateAndCity(state, city);

        textBoxPage.getModal().closeModal();
    }

    @Test
    void successfulMandatoryFieldsTest() {
        textBoxPage
                .openPage()
                .fillMandatoryFields(firstName, lastName, email, gender, mobileNumber)
                .submitForm();

        // Ждем появления модального окна
        sleep(1000);

        textBoxPage.getModal().verifyModalVisible();

        textBoxPage.getTable()
                .verifyStudentName(firstName, lastName)
                .verifyStudentEmail(email)
                .verifyGender(gender)
                .verifyMobile(mobileNumber);

        textBoxPage.getModal().closeModal();
    }

    @Test
    void negativeTestWhenFirstNameIsEmpty() {
        textBoxPage
                .openPage()
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobile(mobileNumber)
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
                .setFirstName(firstName)
                .setEmail(email)
                .setGender(gender)
                .setMobile(mobileNumber)
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
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
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
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setMobile(mobileNumber)
                .scrollToSubmit()
                .submitForm();

        textBoxPage.getModal().verifyModalNotVisible();

        $("#gender-radio-1").shouldNotBe(checked);
        $("#gender-radio-2").shouldNotBe(checked);
        $("#gender-radio-3").shouldNotBe(checked);

        boolean isFormValid = executeJavaScript("return document.querySelector('form').checkValidity();");
        assert !isFormValid : "Form should be invalid when gender not selected";
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}