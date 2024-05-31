package ru.iteco.fmhandroid.ui.pageObject;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


import android.os.SystemClock;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class FilterNewsScreen {

    private final ViewInteraction fillFilterCategory = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction submitFilter = onView(withId(R.id.filter_button));

    public ControlPanelNews selectAndSubmitFilter(String category) {
        Allure.step("Выбрать категорию для фильтрации и отфильтровать");
        fillFilterCategory.check(matches(isDisplayed()));
        fillFilterCategory.perform(replaceText(category));
        submitFilter.perform(click());
        return new ControlPanelNews();
    }

}
