import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AuthTest {

    @Test
    @Owner("APonamareva")
    @Feature("Авторизация")
    @Story("Переход в профиль")
    @DisplayName("Авторизация и открытие профиля")
    public void shouldAuthTest() {
        open("https://github.com/");
        step("Кликнуть на кнопку входа", () -> {
            $("[href='/login']").click();
        });
        step("Ввести логин", () -> {
            $("#login_field").sendKeys("abc");
        });
        step("Ввести пароль", () -> {
            $("#password").sendKeys("123");
        });
        step("Кликнуть на кнопку входа", () -> {
            $(".js-sign-in-button").click();
            $("#repos-container").shouldBe(Condition.visible);
        });
        step("Раскрыть меню профиля", () -> {
            $("[aria-label='View profile and more']").click();
        });
        step("Перейти на страницу своего профиля", () -> {
            $(byText("Your profile")).click();
            $(".p-nickname").shouldBe(Condition.visible);
        });
    }
}
