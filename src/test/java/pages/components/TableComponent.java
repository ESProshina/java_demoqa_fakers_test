package pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TableComponent {

    private final String tableSelector = ".table-responsive";

    public TableComponent verifyRow(String label, String expectedValue) {
        $(tableSelector)
                .$(byText(label))
                .parent()
                .shouldHave(text(expectedValue));
        return this;
    }

    public TableComponent verifyStudentName(String firstName, String lastName) {
        return verifyRow("Student Name", firstName + " " + lastName);
    }

    public TableComponent verifyStudentEmail(String email) {
        return verifyRow("Student Email", email);
    }

    public TableComponent verifyGender(String gender) {
        return verifyRow("Gender", gender);
    }

    public TableComponent verifyMobile(String mobile) {
        return verifyRow("Mobile", mobile);
    }

    public TableComponent verifyDateOfBirth(String day, String month, String year) {
        return verifyRow("Date of Birth", day + " " + month + "," + year);
    }

    public TableComponent verifySubjects(String subject) {
        return verifyRow("Subjects", subject);
    }

    public TableComponent verifyHobbies(String hobby) {
        return verifyRow("Hobbies", hobby);
    }

    public TableComponent verifyPicture(String pictureName) {
        return verifyRow("Picture", pictureName);
    }

    public TableComponent verifyAddress(String address) {
        return verifyRow("Address", address);
    }

    public TableComponent verifyStateAndCity(String state, String city) {
        return verifyRow("State and City", state + " " + city);
    }
}
