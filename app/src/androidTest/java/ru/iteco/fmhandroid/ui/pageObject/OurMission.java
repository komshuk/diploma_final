package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class OurMission {

    private int textScreen = R.id.our_mission_title_text_view;
    String text = "Love is all";
    public int getTextScreen() {
        return textScreen;
    }

    public void textScreenCheckIsDisplayed() {
        Allure.step("Проверка нахождения на экране Тематические статьи");
        onView(withId(textScreen)).check(matches(isDisplayed()));
        onView(withId(textScreen)).check(matches(withText(text)));
    }

}
