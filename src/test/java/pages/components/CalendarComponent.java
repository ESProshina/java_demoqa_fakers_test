package pages.components;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class CalendarComponent {

    public void setDate(String day, String month, String year) {
        // 1. Кликаем по полю даты рождения
        $("#dateOfBirthInput").click();

        // 2. Ждем появления календаря
        $(".react-datepicker").shouldBe(visible);

        // 3. Выбираем месяц
        $(".react-datepicker__month-select").selectOption(month);

        // 4. Выбираем год
        $(".react-datepicker__year-select").selectOption(year);

        // 5. Небольшая пауза для обновления календаря
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 6. Выбираем день через JavaScript
        String dayNumber = day.replaceFirst("^0", "");

        // JavaScript для поиска и клика по дню
        String jsScript = String.format(
                "var days = document.querySelectorAll('.react-datepicker__day:not(.react-datepicker__day--outside-month)');" +
                        "for(var i = 0; i < days.length; i++) {" +
                        "  if(days[i].textContent.trim() === '%s') {" +
                        "    days[i].click();" +
                        "    return true;" +
                        "  }" +
                        "}" +
                        "return false;",
                dayNumber
        );

        // Выполняем JavaScript и проверяем результат
        Boolean clicked = executeJavaScript(jsScript);

        // Если не нашли день через JavaScript, пробуем Selenide
        if (!clicked) {
            // ИСПРАВЛЕНО: используем find() вместо filterBy()
            $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
                    .find(exactText(dayNumber))
                    .click();
        }
    }
}