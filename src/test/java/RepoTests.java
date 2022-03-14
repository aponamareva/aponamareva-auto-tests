import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class RepoTests {

//    @BeforeEach
//    public void before() {
//        loginPage.authorizeErpApplication(WEB_CONFIG.getErpUsername(), WEB_CONFIG.getErpPassword());
//        invoicesPage.title()
//                .shouldBe(visible);
//    }

    @MethodSource("positiveChecks")
    @ParameterizedTest(name = "{displayName} {0}")
    @DisplayName("Поиск в релизах")
    public void shouldSearchReleasesTest(String type, String searchData, String releaseName){
        open("https://github.com/junit-team/junit4");
        $("[href='/junit-team/junit4/releases'].Link--primary")
                .click();
        $("[aria-label='Releases and Tags navigation buttons']")
                .shouldBe(Condition.visible);
        $(".subnav-search-input")
                .setValue(searchData)
                .pressEnter();
        $("[data-test-selector='release-card']",0)
                .shouldBe(Condition.visible);
//        $$("[data-test-selector='release-card']")
//                .forEach(result -> result.shouldHave(text(releaseName)));
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
                ),
                arguments(
                        "по буквам в версии",
                        "RC",
                        "RC"
                )
        );
    }

}
