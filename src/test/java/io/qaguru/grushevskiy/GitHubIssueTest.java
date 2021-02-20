package io.qaguru.grushevskiy;

import com.codeborne.selenide.Condition;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class GitHubIssueTest {

    private static final String BASE_URL = "http://github.com";
    private static final String ISSUES = "Issues";
    private static final String REPOSITORY = "IlyaGrushevskiy/qaGuru_lesson_05";
    private static final String ISSUE_NUMBER = "#1";

    @Test
    @Owner("grushevskiy")
    @Severity(SeverityLevel.CRITICAL)
    @Tags({@Tag("web"), @Tag("critical")})
    @Link(name = "Base URL", value = BASE_URL)

    @Feature("Issues")
    @Story("Поск Issue")
    @DisplayName("Поиск Issue по номеру в репозитории")
    public void testIssueSearch() {

        step("Открыта главная страница", () -> open(BASE_URL));

        step("Выполнен поиск репозитория", () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("Выполнен переход в репозиторий", () -> $(By.linkText(REPOSITORY)).click());

        step("Выполнен переход в ISUUES", () -> $(withText(ISSUES)).click());

        step("Проверено наличие искомого ISSUE", () -> $(withText(ISSUE_NUMBER)).should(Condition.exist));
    }
}
