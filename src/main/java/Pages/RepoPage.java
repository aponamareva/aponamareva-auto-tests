package Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class RepoPage {

    public SelenideElement repoName() {
        return $(".flex-wrap.flex-items-center.wb-break-word").as("заголовок репозитория");
    }

    public SelenideElement switchBranchesButton() {
        return $("[title='Switch branches or tags']").as("кнопка переключения веток");
    }

    public SelenideElement modalBranches() {
        return $(".js-branches-tags-tabs").as("выпадающая модалка переключения веток");
    }

    public SelenideElement fixtureBranch() {
        return $(byText("fixtures")).as("кнопка переключения на ветку fixtures");
    }

    public SelenideElement releasesLink() {
        return $("[href='/junit-team/junit4/releases'].Link--primary").as("ссылка на релизы");
    }
}
