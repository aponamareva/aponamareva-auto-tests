package Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class RepoPage {

    public SelenideElement switchBranchesButton() {
        return $("[title='Switch branches or tags']").as("кнопка переключения веток");
    }

    public SelenideElement releasesLink() {
        return $("[href='/junit-team/junit4/releases'].Link--primary").as("ссылка на релизы");
    }

}
