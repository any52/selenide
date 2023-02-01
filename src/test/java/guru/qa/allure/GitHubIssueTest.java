package guru.qa.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.tests.TestBase;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class GitHubIssueTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String ISSUE_NAME = "issue_to_test_allure_report";
    private static final String URL = "https://github.com";
    private SelenideElement searchInput = $(".header-search-input");
    private SelenideElement repositoryLink = $(linkText(REPOSITORY));
    private SelenideElement issueTab = $("#issues-tab");

    @Test
    @Story("Проверка названия issue на github")
    @DisplayName("Тест на проверку issue с использованием Listener")
    @Owner("almoiseeva")
    public void testIssueSearchSimple() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open(URL);
        searchInput.click();
        searchInput.sendKeys(REPOSITORY);
        searchInput.submit();
        repositoryLink.click();
        issueTab.click();
        $(withText(ISSUE_NAME)).should(Condition.exist);
    }

    @Test
    @Story("Проверка названия issue на github")
    @DisplayName("Тест на проверку задачи с использованием lambda/step ")
    @Owner("almoiseeva")
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open(URL);
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            searchInput.click();
            searchInput.sendKeys(REPOSITORY);
            searchInput.submit();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            repositoryLink.click();
        });
        step("Открываем таб Issues", () -> {
            issueTab.click();
        });
        step("Проверяем наличие Issue с именем " + ISSUE_NAME, () -> {
            $(withText(ISSUE_NAME)).should(Condition.exist);
        });
    }

    @Test
    @Story("Проверка названия issue на github")
    @DisplayName("Тест на проверку задачи с использованием шагов с аннотацией @Step")
    @Owner("almoiseeva")
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithNumber(ISSUE_NAME);

    }

}

