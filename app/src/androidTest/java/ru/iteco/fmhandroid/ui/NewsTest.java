package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import static ru.iteco.fmhandroid.ui.utils.Utils.waitDisplayed;

import android.os.SystemClock;
import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.Feature;
import io.qameta.allure.kotlin.Severity;
import io.qameta.allure.kotlin.SeverityLevel;

import ru.iteco.fmhandroid.ui.pageObject.AppBar;
import ru.iteco.fmhandroid.ui.pageObject.Authorization;

import ru.iteco.fmhandroid.ui.pageObject.BasePage;
import ru.iteco.fmhandroid.ui.pageObject.ControlPanelNews;

import ru.iteco.fmhandroid.ui.pageObject.CreateNews;

import ru.iteco.fmhandroid.ui.pageObject.EditNews;

import ru.iteco.fmhandroid.ui.pageObject.FilterNewsScreen;
import ru.iteco.fmhandroid.ui.pageObject.Main;
import ru.iteco.fmhandroid.ui.pageObject.News;

import ru.iteco.fmhandroid.ui.utils.Utils;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsTest extends BasePage {

    Authorization authorization = new Authorization();
    News news = new News();
    ControlPanelNews controlPanelNews = new ControlPanelNews();
    AppBar appBar = new AppBar();
    CreateNews createNews = new CreateNews();
    Utils utils = new Utils();
    EditNews editNews = new EditNews();
    Main main = new Main();

    private final static String CATEGORY = "День рождения";
    private final static String CATEGORY_FOR_DELETE = "Зарплата";
    private final static String LAST_DATE = "01.01.2020";
    private final static String DESCRIPTION = "тест";
    private final static String TIME = "12:00";
    private final static String EDIT_TIME = "16:00";
    private final static String EDIT_DESCRIPTION = "тест редактирование";
    private final static String EDIT_CATEGORY = "Объявление";
    private final static String TEXT_ERROR_WRONG_DATA = "Неверно указан период";
    private final static String FILL_EMPTY_FIELDS = "Fill empty fields";
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));

    @Before
    public void setUp() {
        onView(isRoot()).perform(waitDisplayed(appBar.getAppBarFragmentMain(), 5000));
        if (!main.isDisplayedButtonProfile()) {
            authorization.loginSuccessful();
        }
        mActivityScenarioRule.getScenario().
                onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("Новости")
    @Feature("Успешное создание новости")
    @Description("Должна создаться новость c указанной темой в панели управления новостей")
    @Test
    public void testShouldLog1InAndShowTheMainScreenAndLogOut() {
        appBar.switchToNews();
        news.switchControlPanelNews();
        controlPanelNews.addNews();
        createNews.addCategory(CATEGORY);
        String text = "Создание новости";
        createNews.addTitle(text);
        createNews.addDate(utils.currentDate());
        createNews.addTime(TIME);
        createNews.addDescription(DESCRIPTION);
        createNews.pressSave();
        controlPanelNews.sortNewsList();
        controlPanelNews.searchNewsAndCheckIsDisplayed(text);

    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("Новости")
    @Feature("Создание новости с датой публикации в прошлом")
    @Description("При создании новости в прошлом должна появляться ошибка")
    @Test
    public void testShouldStayOnNewsCreationScreenWhenCreatingNewsInPast() {
        appBar.switchToNews();
        news.switchControlPanelNews();
        controlPanelNews.addNews();
        createNews.addCategory(CATEGORY);
        String text = "Создание новости в прошлом";
        createNews.addTitle(text);
        createNews.addDate(LAST_DATE);
        createNews.addTime(TIME);
        createNews.addDescription(DESCRIPTION);
        createNews.pressSave();
        createNews.checkErrorDisplayed(TEXT_ERROR_WRONG_DATA, decorView);
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Новости")
    @Feature("Создание новости с датой публикации спустя 5 лет")
    @Description("При создании новости спустя 5 лет должна появляться ошибка")
    @Test
    public void testShouldStayOnNewsCreationScreenWhenCreatingANewsStoryAfter5Years() {
        appBar.switchToNews();
        news.switchControlPanelNews();
        controlPanelNews.addNews();
        createNews.addCategory(CATEGORY);
        String text = "Создание новости в будущем";
        createNews.addTitle(text);
        createNews.addDate(utils.dateMore5Year());
        createNews.addTime(TIME);
        createNews.addDescription(DESCRIPTION);
        createNews.pressSave();
        createNews.checkErrorDisplayed(TEXT_ERROR_WRONG_DATA, decorView);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("Новости")
    @Feature("Создание новости с пустыми полями")
    @Description("При создании новости c пустыми полями должны появляться иконки с ошибкой и toast c текстом")
    @Test
    public void testShouldStayOnNewsCreationScreenWhenCreatingNewsWithEmptyFields() {
        appBar.switchToNews();
        news.switchControlPanelNews();
        controlPanelNews.addNews();
        onView(isRoot()).perform(waitDisplayed(createNews.getButtonSave(), 5000));
        createNews.pressSave();
        createNews.checkErrorDisplayed(FILL_EMPTY_FIELDS, decorView);

    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Новости")
    @Feature("Редактирование новости")
    @Description("После редактирования новость должна отредактироваться, проверяем изменение темы")
    @Test
    public void testShouldEditTheNewsAfterEditing() {
        appBar.switchToNews();
        news.switchControlPanelNews();
        controlPanelNews.addNews();
        String title = "Создание новости" + Math.random();

        createNews.createNewsItem(CATEGORY, title, utils.currentDate(), TIME, DESCRIPTION);
        controlPanelNews.searchNewsAndCheckIsDisplayed(title);
        controlPanelNews.pressEditPanelNews(title);
        editNews.editCategory(EDIT_CATEGORY);
        String editTitle = "Редактирование" + Math.random();
        editNews.editTitle(editTitle);
        editNews.editDate(utils.dateMore1Month());
        editNews.editTime(EDIT_TIME);
        editNews.editDescription(EDIT_DESCRIPTION);
        editNews.pressSave();
        controlPanelNews.searchNewsAndCheckIsDisplayed(editTitle);

    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Новости")
    @Feature("Удаление новости")
    @Description("После удаления новости c указанной темой не должно быть в панели управления новостей")
    @Test
    public void testShouldNewsBeDeletedAfterDeletion() {
        appBar.switchToNews();
        news.switchControlPanelNews();
        controlPanelNews.addNews();
        String title = "Удаление новости" + Math.random();
        createNews.createNewsItem(CATEGORY_FOR_DELETE, title, utils.currentDate(), TIME, DESCRIPTION);
        FilterNewsScreen filterNewsScreen = controlPanelNews.filterNewsToFindItemToDelete();
        ControlPanelNews controlPanelNews1 = filterNewsScreen.selectAndSubmitFilter(CATEGORY_FOR_DELETE);
        controlPanelNews.searchNewsAndCheckIsNotDisplayed(title);

    }
}
