package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ModalComponent {

    private final SelenideElement modal = $(".modal-content");
    private final SelenideElement modalTitle = $(".modal-title");
    private final SelenideElement closeButton = $("#closeLargeModal");

    public ModalComponent verifyModalVisible() {
        // Ждем появления модального окна
        modal.shouldBe(visible);
        // Проверяем заголовок
        modalTitle.shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    public ModalComponent verifyModalNotVisible() {
        modal.shouldNotBe(visible);
        return this;
    }

    public void closeModal() {
        closeButton.click();
    }
}