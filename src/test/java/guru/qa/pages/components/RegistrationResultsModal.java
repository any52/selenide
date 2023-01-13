package guru.qa.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RegistrationResultsModal {
    private final String TITLE_TEXT = "Thanks for submitting the form";
    private SelenideElement
            modalWindowElement = $(".modal-dialog"),
            mainTitleElement = $("#example-modal-sizes-title-lg"),
            tableElement = $(".table-responsive");

    public void verifyModalAppears() {
        modalWindowElement.should(appear);
        mainTitleElement.shouldHave(text(TITLE_TEXT));
    }

    public void verifyResult(String key, String value) {
        tableElement.$(byText(key)).parent()
                .shouldHave(text(value));
    }
}
