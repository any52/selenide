package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import guru.qa.data.Languages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ParameterizedTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @ValueSource(strings = {
            "фен",
            "тостер"
    })
    @ParameterizedTest(name = "Поиск осуществлен по значению {0}")
    void dnsValueSourceSearchTest(String productName) {
        open("https://www.dns-shop.ru/");
        $("[name='q']").setValue(productName).pressEnter();
        $$("[data-id='product']").first().shouldHave(text(productName));
    }

    @CsvSource({
            "ноутбук, Ноутбуки",
            "смартфон, Смартфоны"
    })
    @ParameterizedTest(name = "В результате поиска товара {0} должна появится его категория {1}")
    void dnsCsvSourceSearchTest(
            String productName,
            String productCategory
    ) {
        open("https://www.dns-shop.ru/");
        $("[name='q'").setValue(productName).pressEnter();
        $(".products-page__title").$("h1").shouldHave(text(productCategory));
    }

    @CsvFileSource(resources = "/testData.csv")
    @ParameterizedTest(name = "В результате поиска товара {0} должна появится его категория {1}")
    void dnsCsvFileSourceSearchTest(
            String productName,
            String productCategory
    ) {
        open("https://www.dns-shop.ru/");
        $("[name='q']").setValue(productName).pressEnter();
        $(".products-page__title").$("h1").shouldHave(text(productCategory));
    }

    static Stream<Arguments> miroLocaleDataProvider() {
        return Stream.of(
                Arguments.of(Languages.English, List.of(
                        "Product" + " " + "Solutions" + " " + "Resources" + " " + "Enterprise" + " " +
                                "Pricing")),
                Arguments.of(Languages.Italiano, List.of(
                        "Prodotto" + " " + "Soluzioni" + " " + "Risorse" + " " + "Enterprise" + " " + "Prezzi"))
        );
    }

    @MethodSource("miroLocaleDataProvider")
    @ParameterizedTest(name = "Для языка {0} отображаются кнопки {1}")
    void miroSiteShouldContainAllOfButtonsForGivenLocale(
            Languages language,
            List<String> buttons
    ) {
        open("https://miro.com/");
        $(".LanguageSwitcher__SwitcherContainer-sc-1n6vypn-0").click();
        $(byText(language.name())).click();
        $$(".fIGZUK").filter(visible).shouldHave(texts(buttons));

    }

}
