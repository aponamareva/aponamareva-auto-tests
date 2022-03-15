package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ReleasesPage {

    public SelenideElement switchReleasesTagsButton() {
        return $("[aria-label='Releases and Tags navigation buttons']").as("кнопка переключения релизов и тэгов");
    }

    public SelenideElement searchInput() {
        return $(".subnav-search-input").as("инпут поиска");
    }

    public ElementsCollection releaseCard() {
        return $$("[data-test-selector='release-card']"); //карточка релиза
    }
}
