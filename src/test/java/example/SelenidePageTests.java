package example;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SelenidePageTests {

    @Test
    void successfulSearchSoftAssertionsTest() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $(byText("Show 2 more pages…")).click();
        $$(".Box-row").shouldHave(itemWithText("SoftAssertions"));
        $(byText("SoftAssertions")).click();
        $(".markdown-body").shouldHave(text("Using JUnit5 extend test class:"));
    }
}
