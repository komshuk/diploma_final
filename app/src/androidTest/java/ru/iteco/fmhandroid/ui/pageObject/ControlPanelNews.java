package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import static ru.iteco.fmhandroid.ui.utils.Utils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class ControlPanelNews {
    CreateNews createNews = new CreateNews();
    EditNews editNews = new EditNews();
    private final int buttonAddNews = R.id.add_news_image_view;
    public int getButtonAddNews() {
        return buttonAddNews;
    }
    private int buttonEditNews = R.id.edit_news_item_image_view;
    private final int sortNewsButton = R.id.sort_news_material_button;
    private final int buttonDeleteNews = R.id.delete_news_item_image_view;
    private final ViewInteraction newsItemTitle = onView(withId(R.id.news_item_title_text_view));
    private final ViewInteraction buttonOk = onView(withId(android.R.id.button1));
    private final ViewInteraction openFilterNewsButton = onView(withId(R.id.filter_news_material_button));

    public void addNews() {
        Allure.step("Нажатие на кнопку 'Добавить новость'");
        onView(withId(buttonAddNews)).check(matches(isDisplayed()));
        onView(withId(buttonAddNews)).perform(click());
        onView(isRoot()).perform(waitDisplayed(createNews.getButtonSave(), 6000));
    }

    public void pressEditPanelNews(String text) {
        Allure.step("Нажатие на кнопку 'Редактирование новостей'");
        ViewInteraction edit = onView(allOf(withId(buttonEditNews), withContentDescription(text)));
        edit.check(matches(isDisplayed()));
        edit.perform(click());
        onView(isRoot()).perform(waitDisplayed(editNews.getButtonSave(), 6000));
    }

    public FilterNewsScreen filterNewsToFindItemToDelete() {
        Allure.step("Открыть фильтр новостей по категории");
        openFilterNewsButton.perform(click());
        return new FilterNewsScreen();
    }

    public void deleteNews(String text) {
        Allure.step("Нажатие на кнопку Удалить новость");
        ViewInteraction delete = onView(allOf(withId(buttonDeleteNews), withContentDescription(text)));
        delete.check(matches(isDisplayed()));
        delete.perform(click());
        buttonOk.check(matches(isDisplayed()));
        buttonOk.perform(click());
    }

    public void searchNewsAndCheckIsDisplayed(String text) {
        Allure.step("Поиск новости с заголовком " + text + " и проверка ее видимости");
        onView(withText(text)).check(matches(isDisplayed()));
    }

    public void searchNewsAndCheckIsNotDisplayed(String text) {
        Allure.step("Поиск новости с заголовком " + text + " и проверка ее отсутствия");
        onView(withText(text)).check(matches(not((isDisplayed()))));
    }

    public void sortNewsList() {
        Allure.step("Сортировка новостей по дате создания");
        onView(withId(sortNewsButton)).check(matches(isDisplayed()));
        onView(withId(sortNewsButton)).perform(click());
    }
}
