package pages.components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class StateCityComponent {

    public StateCityComponent selectState(String state) {
        $("#state").click();
        $("#state").parent().find(byText(state)).click();
        return this;
    }

    public StateCityComponent selectCity(String city) {
        $("#city").click();
        $("#city").parent().find(byText(city)).click();
        return this;
    }

    public StateCityComponent selectStateAndCity(String state, String city) {
        return selectState(state).selectCity(city);
    }
}