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
import ru.iteco.fmhandroid.ui.pageObject.AboutApp;
import ru.iteco.fmhandroid.ui.pageObject.AppBar;
import ru.iteco.fmhandroid.ui.pageObject.Authorization;

import ru.iteco.fmhandroid.ui.pageObject.Main;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AboutAppTest {
    Authorization authorization = new Authorization();

    AppBar appBar = new AppBar();
    Main main = new Main();

    AboutApp aboutApp = new AboutApp();
    private final String urlPrivacyPolicy = "https://vhospice.org/#/privacy-policy";
    private final String urlTermsOfUse = "https://vhospice.org/#/terms-of-use";

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

    @Epic("О приложении")
    @Feature("Просмотр политики конфиденциальности")
    @Description("Должна открыться политика конфиденциальности")
    @Test
    public void testShouldOpenPrivacyPolicy() {
        appBar.switchToAboutApp();
        aboutApp.intentPrivatePolicy(urlPrivacyPolicy);
        aboutApp.back();
    }

    @Epic("О приложении")
    @Feature("Просмотр пользовательского соглашения")
    @Description("Должно открыться пользовательское соглашение")
    @Test
    public void testShouldOpenTermsOfUse() {
        appBar.switchToAboutApp();
        aboutApp.intentTermOfUse(urlTermsOfUse);
        aboutApp.back();
    }
}
