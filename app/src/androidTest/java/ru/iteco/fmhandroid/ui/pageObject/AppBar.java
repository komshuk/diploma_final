package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import static ru.iteco.fmhandroid.ui.utils.Utils.waitDisplayed;
import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class AppBar {

    News news = new News();
    AboutApp aboutApp = new AboutApp();

    Main main = new Main();
    OurMission ourMission = new OurMission();

    private final int appBarFragmentMain = R.id.container_custom_app_bar_include_on_fragment_main;

    public int getAppBarFragmentMain() {
        return appBarFragmentMain;
    }

    private final int pressProfile = R.id.authorization_image_button;

    public int getPressProfile() {
        return pressProfile;
    }

    private final ViewInteraction mainMenuNews = onView(
            allOf(withId(android.R.id.title), withText("News")));

    private final ViewInteraction mainMenuAboutApp = onView(
            allOf(withId(android.R.id.title), withText("About")));

    private final ViewInteraction mainMenuMain = onView(
            allOf(withId(android.R.id.title), withText("Main")));

    private final ViewInteraction out = onView(withText("Log out"));
    private final ViewInteraction buttonMainMenu = onView(withId(R.id.main_menu_image_button));

    private final ViewInteraction buttonOurMission = onView(withId(R.id.our_mission_image_button));

    public void pressOut() {
        Allure.step("Выход из профиля");
        ViewInteraction buttonProfile = onView(withId(pressProfile));
        buttonProfile.check(matches(isDisplayed()));
        buttonProfile.perform(click());

        out.check(matches(isDisplayed()));
        out.perform(click());
    }

    public void switchToNews() {
        Allure.step("Переход на экран Новости");
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());

        mainMenuNews.check(matches(isDisplayed()));
        mainMenuNews.perform(click());
        onView(isRoot()).perform(waitDisplayed(news.getButtonControlPanelNews(), 5000));
    }

    public void switchToAboutApp() {
        Allure.step("Переход на экран О приложении");
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());

        mainMenuAboutApp.check(matches(isDisplayed()));
        mainMenuAboutApp.perform(click());
        onView(isRoot()).perform(waitDisplayed(aboutApp.getButtonBack(), 5000));
    }

    public void switchToMain() {
        Allure.step("Переход на главный экран");
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());

        mainMenuMain.check(matches(isDisplayed()));
        mainMenuMain.perform(click());
        onView(isRoot()).perform(waitDisplayed(main.getContainerNews(), 5000));
    }

    public void switchToOurMission() {
        Allure.step("Переход на экран Тематические статьи");
        buttonOurMission.check(matches(isDisplayed()));
        buttonOurMission.perform(click());
        onView(isRoot()).perform(waitDisplayed(ourMission.getTextScreen(), 5000));
    }

}
