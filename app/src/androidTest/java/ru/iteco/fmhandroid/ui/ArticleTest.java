package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static ru.iteco.fmhandroid.ui.utils.Utils.waitDisplayed;

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


import ru.iteco.fmhandroid.ui.pageObject.Main;

import ru.iteco.fmhandroid.ui.pageObject.OurMission;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ArticleTest {
    Authorization authorization = new Authorization();
    Main main = new Main();
    AppBar appBar = new AppBar();
    OurMission ourMission = new OurMission();

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
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Тематические статьи")
    @Feature("Просмотр тематических статей")
    @Description("Должна открыться страница с тематическими статьями")
    @Test
    public void testShouldOpenPageWithThematicArticles() {
        appBar.switchToOurMission();
        ourMission.textScreenCheckIsDisplayed();
    }
}
