package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {

    // ============ ПОЛЯ ДЛЯ PRACTICE FORM (Регистрация студента) ============
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement mobileInput = $("#userNumber");
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement addressInput = $("#currentAddress");
    private final SelenideElement stateInput = $("#state");
    private final SelenideElement cityInput = $("#city");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement pictureInput = $("#uploadPicture");

    // ============ ПОЛЯ ДЛЯ TEXT BOX ФОРМЫ ============
    private final SelenideElement fullNameInput = $("#userName");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement permanentAddressInput = $("#permanentAddress");
    private final SelenideElement textBoxSubmitButton = $("#submit");

    // ============ КОМПОНЕНТЫ ============
    private final CalendarComponent calendar = new CalendarComponent();
    private final ModalComponent modal = new ModalComponent();
    private final StateCityComponent stateCity = new StateCityComponent();
    private final TableComponent table = new TableComponent();

    // ============ МЕТОДЫ ДЛЯ PRACTICE FORM (Регистрация студента) ============

    public TextBoxPage openPage() {
        open("https://demoqa.com/automation-practice-form");
        return this;
    }

    public TextBoxPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public TextBoxPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public TextBoxPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public TextBoxPage setGender(String gender) {
        $(byText(gender)).click();
        return this;
    }

    public TextBoxPage setMobile(String mobile) {
        mobileInput.setValue(mobile);
        return this;
    }

    public TextBoxPage setDateOfBirth(String day, String month, String year) {
        calendar.setDate(day, month, year);
        return this;
    }

    public TextBoxPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public TextBoxPage setHobby(String hobby) {
        $(byText(hobby)).click();
        return this;
    }

    public TextBoxPage uploadPicture(String pictureName) {
        pictureInput.uploadFromClasspath(pictureName);
        return this;
    }

    public TextBoxPage setAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    public TextBoxPage setStateAndCity(String state, String city) {
        stateCity.selectStateAndCity(state, city);
        return this;
    }

    public TextBoxPage submitForm() {
        submitButton.click();
        return this;
    }

    public TextBoxPage scrollToSubmit() {
        submitButton.scrollTo();
        return this;
    }

    public TextBoxPage fillForm(String firstName, String lastName, String email,
                                String gender, String mobile, String day,
                                String month, String year, String subject,
                                String hobby, String pictureName, String address,
                                String state, String city) {
        setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobile(mobile)
                .setDateOfBirth(day, month, year)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadPicture(pictureName)
                .setAddress(address)
                .setStateAndCity(state, city);
        return this;
    }

    public TextBoxPage fillMandatoryFields(String firstName, String lastName,
                                           String email, String gender, String mobile) {
        setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobile(mobile);
        return this;
    }

    public ModalComponent getModal() {
        return modal;
    }

    public TableComponent getTable() {
        return table;
    }

    // ============ МЕТОДЫ ДЛЯ TEXT BOX ФОРМЫ ============

    // ОТКРЫТЬ СТРАНИЦУ TEXT BOX - ЭТОТ МЕТОД ДОЛЖЕН БЫТЬ!
    public TextBoxPage openTextBoxPage() {
        open("https://demoqa.com/text-box");
        return this;
    }

    public TextBoxPage setFullName(String fullName) {
        fullNameInput.setValue(fullName);
        return this;
    }

    public TextBoxPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);
        return this;
    }

    public TextBoxPage setPermanentAddress(String permanentAddress) {
        permanentAddressInput.setValue(permanentAddress);
        return this;
    }

    public TextBoxPage clickSubmit() {
        textBoxSubmitButton.click();
        return this;
    }

    public SelenideElement getOutput() {
        return $("#output");
    }

    public SelenideElement getEmailField() {
        return emailInput;
    }

    public SelenideElement getFullNameField() {
        return fullNameInput;
    }

    public TextBoxPage fillTextBoxForm(String fullName, String email,
                                       String currentAddress, String permanentAddress) {
        setFullName(fullName)
                .setEmail(email)
                .setCurrentAddress(currentAddress)
                .setPermanentAddress(permanentAddress);
        return this;
    }
}