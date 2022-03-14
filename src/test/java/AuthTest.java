import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AuthTest {

    @Test
    public void shouldAuthTest() {
        open("https://github.com/");
        $("[href='/login']").click();
        $("#login_field").sendKeys("asponamareva@gmail.com");
        $("#password").sendKeys("NASTYA_belka18");
        $(".js-sign-in-button").click();
        $("#repos-container").shouldBe(Condition.visible);
        $("[aria-label='View profile and more']").click();
        $(byText("Your profile")).click();
        $(".p-nickname").shouldBe(Condition.visible);
    }
}
