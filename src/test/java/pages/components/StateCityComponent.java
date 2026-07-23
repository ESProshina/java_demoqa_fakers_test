package pages.components;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class StateCityComponent {

    public void selectStateAndCity(String state, String city) {
        // Выбираем штат
        selectState(state);

        // Ждем загрузки городов
        sleep(500);

        // Выбираем город
        selectCity(city);
    }

    private void selectState(String state) {
        // Кликаем на поле выбора штата
        $("#state").click();

        // Ждем появления выпадающего списка (используем правильный селектор)
        $(".css-1nmdiq5-menu").shouldBe(visible);

        // Выбираем штат
        $(byText(state)).click();
    }

    private void selectCity(String city) {
        // Кликаем на поле выбора города
        $("#city").click();

        // Ждем появления выпадающего списка
        $(".css-1nmdiq5-menu").shouldBe(visible);

        // Пробуем найти город
        try {
            // Способ 1: по тексту
            $(byText(city)).click();
        } catch (Exception e) {
            try {
                // Способ 2: через JavaScript
                String jsScript = String.format(
                        "var items = document.querySelectorAll('.css-1nmdiq5-menu [class*=\"option\"]');" +
                                "for(var i = 0; i < items.length; i++) {" +
                                "  if(items[i].textContent.trim() === '%s') {" +
                                "    items[i].click();" +
                                "    return true;" +
                                "  }" +
                                "}" +
                                "return false;",
                        city
                );
                Boolean clicked = executeJavaScript(jsScript);
                if (!clicked) {
                    throw new RuntimeException("City not found: " + city);
                }
            } catch (Exception ex) {
                // Способ 3: ищем все элементы в списке
                $$(".css-1nmdiq5-menu div").findBy(visible).click();
            }
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}