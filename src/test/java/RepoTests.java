import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RepoTests {

    @Owner("APonamareva")
    @Feature("Страница репозитория")

    @BeforeEach
    public void before() {
        open("https://github.com/junit-team/junit4");
        TestPages.repoPage.repoName()
                .shouldHave(text("junit-team / junit4"));
    }
    
    @Test
    @Story("Переключение веток")
    @DisplayName("Переключение на ветку fixtures")
    public void shouldSwitchBranchTest(){
        step("Открыть модалку переключения веток", () -> {

        });
        TestPages.repoPage.switchBranchesButton()
                .click();
        TestPages.repoPage.modalBranches()
                .shouldBe(visible);
        step("Переключиться на ветку fixtures", () -> {

        });
        TestPages.repoPage.fixtureBranch()
                .click();
        step("Проверить, что ветка корректная", () -> {

        });
        TestPages.repoPage.switchBranchesButton()
                .shouldHave(text("fixtures"));
    }

    @DisplayName("Поиск в релизах")
    @MethodSource("positiveChecks")
    @Story("Страница релизов и тэгов")
    @ParameterizedTest(name = "{displayName} {0}")
    public void shouldSearchReleasesTest(String type, String searchData, String releaseName){
        step("Открыть страницу релизов", () -> {
            TestPages.repoPage.releasesLink()
                    .click();
            TestPages.releasesPage.switchReleasesTagsButton()
                    .shouldBe(visible);
        });
        step("Ввести данные в поисковую строку", () -> {
            TestPages.releasesPage.searchInput()
                    .setValue(searchData)
                    .pressEnter();
        });
        step("Проверить, что результаты соответствуют поиску", () -> {
            TestPages.releasesPage.releaseCards()
                    .get(0)
                    .shouldBe(visible)
                    .shouldHave(text(releaseName));
        });
    }

    static Stream<Arguments> positiveChecks() {
        return Stream.of(
                arguments(
                        "по полному совпадению названия",
                        "JUnit 4.13 RC 2",
                        "JUnit 4.13 RC 2"
                ),
                arguments(
                        "по полной версии (beta вариант)",
                        "4.12 Beta 1",
                        "JUnit 4.12 Beta 1"
                ),
                arguments(
                        "по числовому значению в версии",
                        "4.13.1",
                        "JUnit 4.13.1"
                )
        );
    }

}
