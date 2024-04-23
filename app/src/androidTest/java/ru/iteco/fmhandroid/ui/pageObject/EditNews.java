package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class EditNews {

    private final ViewInteraction editCategory = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction editTitle = onView(withId(R.id.news_item_title_text_input_edit_text));
    private final ViewInteraction editTime = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    private final ViewInteraction editDate = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    private final ViewInteraction editDescription = onView(withId(R.id.news_item_description_text_input_edit_text));
    private final ViewInteraction save = onView(withId(R.id.save_button));
    private final int buttonSave = R.id.save_button;
    public int getButtonSave() {
        return buttonSave;
    }


    @Step("Редактирование значения в поле категория на {text}")
    public void editCategory(String text) {
        editCategory.check(matches(isDisplayed()));
        editCategory.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Редактирование значения в поле заголовок на {text}")
    public void editTitle(String text) {
        editTitle.check(matches(isDisplayed()));
        editTitle.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Редактирование значения в поле дата на {text}")
    public void editDate(String text) {
        editDate.check(matches(isDisplayed()));
        editDate.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Редактирование значения в поле время на {text}")
    public void editTime(String text) {
        editTime.check(matches(isDisplayed()));
        editTime.perform(replaceText(text), closeSoftKeyboard());

    }

    @Step("Редактирование значения в поле описание на {text}")
    public void editDescription(String text) {
        editDescription.check(matches(isDisplayed()));
        editDescription.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Нажатие на кнопку Сохранить")
    public void pressSave() {
        closeSoftKeyboard();
        save.check(matches(isDisplayed()));
        save.perform(scrollTo(), click());
    }

}
