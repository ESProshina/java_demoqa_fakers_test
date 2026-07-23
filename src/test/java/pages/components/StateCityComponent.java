package pages.components;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StateCityComponent {

    public void selectStateAndCity(String state, String city) {
        // Выбираем штат
        $("#state").click();
        sleep(500);
        $(byText(state)).shouldBe(visible).click();

        // Ждем загрузки городов
        sleep(1000);

        // Выбираем город
        $("#city").click();
        sleep(500);
        $(byText(city)).shouldBe(visible).click();
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}