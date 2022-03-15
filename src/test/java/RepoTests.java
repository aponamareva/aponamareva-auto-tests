import com.codeborne.selenide.Condition;
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
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RepoTests {

    @BeforeEach
    public void before() {
        open("https://github.com/junit-team/junit4");
        TestPages.repoPage.repoName()
                .shouldHave(text("junit-team / junit4"));
    }
    
    @Test
    @DisplayName("Переключение на ветку fixtures")
    public void shouldSwitchBranchTest(){
        TestPages.repoPage.switchBranchesButton()
                .click();
        TestPages.repoPage.modalBranches()
                .shouldBe(visible);
        TestPages.repoPage.fixtureBranch()
                .click();
        TestPages.repoPage.switchBranchesButton()
                .shouldHave(text("fixtures"));
    }

    @MethodSource("positiveChecks")
    @ParameterizedTest(name = "{displayName} {0}")
    @DisplayName("Поиск в релизах")
    public void shouldSearchReleasesTest(String type, String searchData, String releaseName){
        TestPages.repoPage.releasesLink()
                .click();
        TestPages.releasesPage.switchReleasesTagsButton()
                .shouldBe(visible);
        TestPages.releasesPage.searchInput()
                .setValue(searchData)
                .pressEnter();
        TestPages.releasesPage.releaseCard()
                .get(0)
                .shouldBe(visible)
                .shouldHave(text(releaseName));
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
