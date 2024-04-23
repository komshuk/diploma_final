package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class Main {

    private final ViewInteraction textViewMainNews = onView(withText("News"));
    private final int containerNews = R.id.container_list_news_include_on_fragment_main;

    public int getContainerNews() {
        return containerNews;
    }

    @Step("Метод, позволяющий определить в каком состоянии находится система: " +
            "если true, то на главном экране, если false - на странице авторизации")
    public Boolean isDisplayedButtonProfile() {
        try {
            onView(withId(containerNews)).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException e) {
            return false;
        }
    }

    @Step("Проверка видимости элемента с текстом Новости")
    public void checkNews() {
        Allure.step("Проверка видимости элемента с текстом Новости");
        textViewMainNews.check(matches(isDisplayed()));
        textViewMainNews.check(matches(withText("News")));
    }
}


