package ru.iteco.fmhandroid.ui.pageObject;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.containsString;

import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class FilterNewsScreen {

    private final ViewInteraction fillFilterCategory = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction submitFilter = onView(withId(R.id.filter_button));

    @Step("Выбрать категорию для фильтрации и отфильтровать")
    public ControlPanelNews selectAndSubmitFilter(String category) {
        fillFilterCategory.check(matches(isDisplayed()));
        fillFilterCategory.perform(replaceText(category));
        SystemClock.sleep(2000);
        submitFilter.perform(click());
        return new ControlPanelNews();
    }

}
