package pages.components;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ModalComponent {

    private final SelenideElement modalTitle = $(".modal-title");
    private final SelenideElement closeButton = $("#closeLargeModal");
    private final SelenideElement modalContent = $(".modal-content");

    public ModalComponent verifyModalVisible() {
        modalTitle.shouldHave(text("Thanks for submitting the form"));
        modalContent.shouldBe(visible);
        return this;
    }

    public ModalComponent verifyModalNotVisible() {
        modalTitle.shouldNotBe(visible);
        return this;
    }

    public ModalComponent closeModal() {
        closeButton.click();
        return this;
    }
}
