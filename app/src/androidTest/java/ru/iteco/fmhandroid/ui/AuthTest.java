package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import static ru.iteco.fmhandroid.ui.utils.Utils.waitDisplayed;

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

import ru.iteco.fmhandroid.ui.pageObject.Main;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthTest {
    Authorization authorization = new Authorization();
    Main main = new Main();
    AppBar appBar = new AppBar();

    private final String successLogin = "login2";
    private final String successPassword = "password2";
    private final String registerLogin = "LOGIN2";
    private final String registerPassword = "PASSWORD2";
    private final String russianLogin = "логин2";
    private final String russianPassword = "пассворд2";
    private final String specSymbol = "lOGin #%@`<|&?>*";
    private final String more50Symbol = "loginloginloginloginloginloginloginloginloginlogin";
    private final String oneSymbol = "l";
    private static final String UNKNOWN_ERROR = "Something went wrong. Try again later.";
    private static final String EMPTY_CREDENTIALS_FIELDS = "Login and password cannot be empty";
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Rule
    public ScreenshotRule screenshotRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE,
            String.valueOf(System.currentTimeMillis()));

    @Before
    public void setUp() {
        onView(isRoot()).perform(waitDisplayed(appBar.getAppBarFragmentMain(), 6500));
        if (main.isDisplayedButtonProfile()) {
            appBar.pressOut();
        }
        mActivityScenarioRule.getScenario().
                onActivity(activity -> decorView = activity.getWindow().getDecorView());
    }

    @Severity(value = SeverityLevel.BLOCKER)
    @Epic("Авторизация")
    @Feature("Успешная авторизация в приложении и выход из профиля")
    @Description("После авторизации с валидными данными должен показаться главный экран, после выхода из профиля - экран авторизации")
    @Test
    public void testShouldLogInAndShowTheMainScreenAndLogOut() {
        authorization.inputLogin(successLogin);
        authorization.inputPassword(successPassword);
        authorization.pressButton();

        onView(isRoot()).perform(waitDisplayed(appBar.getPressProfile(), 9000));
        main.checkNews();
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("Авторизация")
    @Feature("Авторизация невалидного пользователя при использовании регистра")
    @Description("После авторизации с использованием регистра должен остаться на экране авторизации")
    @Test
    public void testShouldRemainOnTheMainScreenWhenEnteringDataUsingCase() {
        authorization.inputLogin(registerLogin);
        authorization.inputPassword(registerPassword);
        authorization.pressButton();
        authorization.checkAuth();
        authorization.checkToastWithErrorMessage(UNKNOWN_ERROR, decorView);
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature("Авторизация при введении в поле логин символов на кириллице")
    @Description("После авторизации с введением в поле логин символов на кириллице должен остаться на экране авторизации")
    @Test
    public void testShouldRemainOnTheMainScreenWhenEnteringInTheLoginFieldCharactersInCyrillic() {
        authorization.inputLogin(russianLogin);
        authorization.inputPassword(successPassword);
        authorization.pressButton();
        authorization.checkAuth();
        authorization.checkToastWithErrorMessage(UNKNOWN_ERROR, decorView);

    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature("Авторизация при введении в поле логин спецсимволов")
    @Description("После авторизации с введением в поле логин спецсимволов должен остаться на экране авторизации")
    @Test
    public void testShouldRemainOnTheMainScreenWhenEnteringInTheLoginFieldSpecialCharacters() {
        authorization.inputLogin(specSymbol);
        authorization.inputPassword(successPassword);
        authorization.pressButton();
        authorization.checkAuth();
        authorization.checkToastWithErrorMessage(UNKNOWN_ERROR, decorView);
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature("Авторизация при введении в поле логин 1 символ")
    @Description("После авторизации с введением в поле логин 1 символа должен остаться на экране авторизации")
    @Test
    public void testShouldRemainOnTheMainScreenWhenEnteringInTheLoginFieldOneCharacter() {
        authorization.inputLogin(oneSymbol);
        authorization.inputPassword(successPassword);
        authorization.pressButton();
        authorization.checkAuth();
        authorization.checkToastWithErrorMessage(UNKNOWN_ERROR, decorView);
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature("Авторизация при введении в поле логин 50 символов")
    @Description("После авторизации с введением в поле логин 50 символов должен остаться на экране авторизации")
    @Test
    public void testShouldRemainOnTheMainScreenWhenEnteringInTheLoginField50Characters() {
        authorization.inputLogin(more50Symbol);
        authorization.inputPassword(successPassword);
        authorization.pressButton();
        authorization.checkAuth();
        authorization.checkToastWithErrorMessage(UNKNOWN_ERROR, decorView);
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature(" Авторизация при введении в поле пароль символов на кириллице")
    @Description("После авторизации с введением в поле пароль символов на кириллице должен остаться на экране авторизации")
    @Test
    public void testShouldRemainOnTheMainScreenWhenEnteringInThePasswordFieldCharactersInCyrillic() {
        authorization.inputLogin(successLogin);
        authorization.inputPassword(russianPassword);
        authorization.pressButton();
        authorization.checkAuth();
        authorization.checkToastWithErrorMessage(UNKNOWN_ERROR, decorView);
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature(" Авторизация при введении в поле пароль спецсимволов")
    @Description("После авторизации с введением в поле пароль спецсимволов должен остаться на экране авторизации")
    @Test
    public void testShouldRemainOnTheMainScreenWhenEnteringInThePasswordFieldSpecialCharacters() {
        authorization.inputLogin(successLogin);
        authorization.inputPassword(specSymbol);
        authorization.pressButton();
        authorization.checkAuth();
        authorization.checkToastWithErrorMessage(UNKNOWN_ERROR, decorView);
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature(" Авторизация при введении в поле пароль 1 символа")
    @Description("После авторизации с введением в поле пароль  1 символа должен остаться на экране авторизации")
    @Test
    public void testShouldRemainOnTheMainScreenWhenEnteringInThePasswordField1Character() {
        authorization.inputLogin(successLogin);
        authorization.inputPassword(oneSymbol);
        authorization.pressButton();
        authorization.checkAuth();
        authorization.checkToastWithErrorMessage(UNKNOWN_ERROR, decorView);
    }

    @Severity(value = SeverityLevel.NORMAL)
    @Epic("Авторизация")
    @Feature(" Авторизация при введении в поле пароль 50 символов")
    @Description("После авторизации с введением в поле пароль  50 символов должен остаться на экране авторизации")
    @Test
    public void testShouldRemainOnTheMainScreenWhenEnteringInThePasswordField50Characters() {
        authorization.inputLogin(successLogin);
        authorization.inputPassword(more50Symbol);
        authorization.pressButton();
        authorization.checkAuth();
        authorization.checkToastWithErrorMessage(UNKNOWN_ERROR, decorView);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("Авторизация")
    @Feature(" Авторизация с пустым логином")
    @Description("После авторизации с пустым логином должен остаться на экране авторизации")
    @Test
    public void testShouldRemainOnTheMainScreenWhenEmptyLogin() {
        authorization.inputPassword(successPassword);
        authorization.pressButton();
        authorization.checkAuth();
        authorization.checkToastWithErrorMessage(EMPTY_CREDENTIALS_FIELDS, decorView);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("Авторизация")
    @Feature(" Авторизация с пустым паролем")
    @Description("После авторизации с пустым паролем должен остаться на экране авторизации")
    @Test
    public void testShouldRemainOnTheMainScreenWhenEmptyPassword() {
        authorization.inputLogin(successLogin);
        authorization.pressButton();
        authorization.checkAuth();
        authorization.checkToastWithErrorMessage(EMPTY_CREDENTIALS_FIELDS, decorView);
    }

    @Severity(value = SeverityLevel.CRITICAL)
    @Epic("Авторизация")
    @Feature("Авторизация с пустым логином и паролем")
    @Description("После авторизации с пустым логином и паролем должен остаться на экране авторизации")
    @Test
    public void testShouldRemainOnTheMainScreenWhenEmptyLoginAndPassword() {
        authorization.pressButton();
        authorization.checkAuth();
        authorization.checkToastWithErrorMessage(EMPTY_CREDENTIALS_FIELDS, decorView);
    }

}
