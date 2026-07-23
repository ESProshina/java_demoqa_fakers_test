package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        //  Configuration.browserVersion = "144.0";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.timeout = 10000; //defolt 4000
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();

    }
}
