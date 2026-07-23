package pages;

import pages.components.CalendarComponent;
import pages.components.ModalComponent;
import pages.components.StateCityComponent;
import pages.components.TableComponent;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {

    private final CalendarComponent calendar = new CalendarComponent();
    private final ModalComponent modal = new ModalComponent();
    private final StateCityComponent stateCity = new StateCityComponent();
    private final TableComponent table = new TableComponent();


    private final String firstNameField = "#firstName";
    private final String lastNameField = "#lastName";
    private final String emailField = "#userEmail";
    private final String genderWrapper = "#genterWrapper";
    private final String mobileField = "#userNumber";
    private final String dateOfBirthInput = "#dateOfBirthInput";
    private final String subjectsInput = "#subjectsInput";
    private final String hobbiesWrapper = "#hobbiesWrapper";
    private final String uploadPicture = "#uploadPicture";
    private final String addressField = "#currentAddress";
    private final String submitButton = "#submit";
    private final String stateField = "#state";
    private final String cityField = "#city";

    public TextBoxPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public TextBoxPage setFirstName(String firstName) {
        $(firstNameField).setValue(firstName);
        return this;
    }

    public TextBoxPage setLastName(String lastName) {
        $(lastNameField).setValue(lastName);
        return this;
    }

    public TextBoxPage setEmail(String email) {
        $(emailField).setValue(email);
        return this;
    }

    public TextBoxPage setGender(String gender) {
        $(genderWrapper).find(byText(gender)).click();
        return this;
    }

    public TextBoxPage setMobile(String mobile) {
        $(mobileField).setValue(mobile);
        return this;
    }

    public TextBoxPage setDateOfBirth(String day, String month, String year) {
        $(dateOfBirthInput).click();
        calendar.setDate(day, month, year);
        return this;
    }

    public TextBoxPage setSubject(String subject) {
        $(subjectsInput).setValue(subject).pressEnter();
        return this;
    }

    public TextBoxPage setHobby(String hobby) {
        $(hobbiesWrapper).find(byText(hobby)).click();
        return this;
    }

    public TextBoxPage uploadPicture(String pictureName) {
        $(uploadPicture).uploadFromClasspath(pictureName);
        return this;
    }

    public TextBoxPage setAddress(String address) {
        $(addressField).setValue(address);
        return this;
    }

    public TextBoxPage setStateAndCity(String state, String city) {
        stateCity.selectStateAndCity(state, city);
        return this;
    }

    public TextBoxPage submitForm() {
        $(submitButton).click();
        return this;
    }

    public TextBoxPage scrollToSubmit() {
        $(submitButton).scrollTo();
        return this;
    }

    public TextBoxPage fillForm(String firstName, String lastName, String email,
                                String gender, String mobile, String day,
                                String month, String year, String subject,
                                String hobby, String picture, String address,
                                String state, String city) {
        return setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobile(mobile)
                .setDateOfBirth(day, month, year)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadPicture(picture)
                .setAddress(address)
                .setStateAndCity(state, city);
    }

    public TextBoxPage fillMandatoryFields(String firstName, String lastName,
                                           String email, String gender, String mobile) {
        return setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setMobile(mobile);
    }

    public ModalComponent getModal() {
        return modal;
    }

    public TableComponent getTable() {
        return table;
    }
}