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

    @Step("Нажатие на кнопку Добавить новость")
    public void addNews() {
        onView(withId(buttonAddNews)).check(matches(isDisplayed()));
        onView(withId(buttonAddNews)).perform(click());
        onView(isRoot()).perform(waitDisplayed(createNews.getButtonSave(), 6000));
    }

    @Step("Нажатие на кнопку Редактирование новостей")
    public void pressEditPanelNews(String text) {
        ViewInteraction edit = onView(allOf(withId(buttonEditNews), withContentDescription(text)));
        edit.check(matches(isDisplayed()));
        edit.perform(click());
        onView(isRoot()).perform(waitDisplayed(editNews.getButtonSave(), 6000));
    }

    @Step("Открыть фильтр новостей по категории")
    public FilterNewsScreen filterNewsToFindItemToDelete() {
        openFilterNewsButton.perform(click());
        return new FilterNewsScreen();
    }

    @Step("Нажатие на кнопку Удалить новость")
    public void deleteNews(String text) {
        ViewInteraction delete = onView(allOf(withId(buttonDeleteNews), withContentDescription(text)));
        delete.check(matches(isDisplayed()));
        delete.perform(click());
        buttonOk.check(matches(isDisplayed()));
        buttonOk.perform(click());
    }

    @Step("Поиск новости с заголовком {text} и проверка ее видимости")
    public void searchNewsAndCheckIsDisplayed(String text) {
        onView(withText(text)).check(matches(isDisplayed()));
    }

    @Step("Поиск новости с заголовком {text} и проверка ее отсутствия")
    public void searchNewsAndCheckIsNotDisplayed(String text) {
        onView(withText(text)).check(matches(not((isDisplayed()))));
    }

    @Step("Отсортировать новости по дате создания")
    public void sortNewsList() {
        onView(withId(sortNewsButton)).check(matches(isDisplayed()));
        onView(withId(sortNewsButton)).perform(click());
    }
}
