package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static ru.iteco.fmhandroid.ui.utils.Utils.waitDisplayed;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class News {

    ControlPanelNews controlPanelNews = new ControlPanelNews();
    private final int buttonControlPanelNews = R.id.edit_news_material_button;
    public int getButtonControlPanelNews() {
        return buttonControlPanelNews;
    }

    @Step("Переход на Панель управления новостями")
    public void switchControlPanelNews() {
        onView(withId(buttonControlPanelNews)).check(matches(isDisplayed()));
        onView(withId(buttonControlPanelNews)).perform(click());
        onView(isRoot()).perform(waitDisplayed(controlPanelNews.getButtonAddNews(), 6000));
    }

}
