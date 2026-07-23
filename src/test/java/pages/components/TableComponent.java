package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TableComponent {

    private final SelenideElement table = $(".table-responsive");

    public TableComponent verifyRow(String label, String expectedValue) {
        table.find(byText(label))
                .parent()
                .shouldHave(text(expectedValue));
        return this;
    }

    public TableComponent verifyStudentName(String firstName, String lastName) {
        verifyRow("Student Name", firstName + " " + lastName);
        return this;
    }

    public TableComponent verifyStudentEmail(String email) {
        verifyRow("Student Email", email);
        return this;
    }

    public TableComponent verifyGender(String gender) {
        verifyRow("Gender", gender);
        return this;
    }

    public TableComponent verifyMobile(String mobile) {
        verifyRow("Mobile", mobile);
        return this;
    }

    public TableComponent verifyDateOfBirth(String day, String month, String year) {
        verifyRow("Date of Birth", day + " " + month + "," + year);
        return this;
    }

    public TableComponent verifySubjects(String subject) {
        verifyRow("Subjects", subject);
        return this;
    }

    public TableComponent verifyHobbies(String hobby) {
        verifyRow("Hobbies", hobby);
        return this;
    }

    public TableComponent verifyPicture(String pictureName) {
        verifyRow("Picture", pictureName);
        return this;
    }

    public TableComponent verifyAddress(String address) {
        verifyRow("Address", address);
        return this;
    }

    public TableComponent verifyStateAndCity(String state, String city) {
        verifyRow("State and City", state + " " + city);
        return this;
    }
}