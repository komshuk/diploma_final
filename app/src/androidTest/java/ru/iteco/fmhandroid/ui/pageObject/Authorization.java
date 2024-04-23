package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertContains;
import static com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import static ru.iteco.fmhandroid.ui.utils.Utils.waitDisplayed;

import android.view.View;
import android.widget.EditText;
import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;


public class Authorization extends BasePage {
    Main main = new Main();
    AppBar appBar = new AppBar();

    private final int inputLogin = R.id.login_text_input_layout;
    private final int inputPassword = R.id.password_text_input_layout;
    private final ViewInteraction materialButton = onView(withId(R.id.enter_button));
    private final ViewInteraction textViewAuth = onView(withText("Authorization"));

    @Step("Ввод в поле логин {login}")
    public void inputLogin(String login) {
        ViewInteraction textInputEditText = onView(allOf(
                isDescendantOfA(withId(inputLogin)),
                isAssignableFrom(EditText.class)));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(login), closeSoftKeyboard());

    }

    @Step("Ввод в поле пароль {password}")
    public void inputPassword(String password) {
        Allure.step("Ввод в поле пароль {password}");
        ViewInteraction textInputEditText3 = onView(allOf(
                isDescendantOfA(withId(inputPassword)),
                isAssignableFrom(EditText.class)));
        textInputEditText3.check(matches(isDisplayed()));
        textInputEditText3.perform(replaceText(password), closeSoftKeyboard());
    }

    @Step("Нажатие на кнопку ВОЙТИ")
    public void pressButton() {
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
    }

    @Step("Успешная авторизация пользователя")
    public void loginSuccessful() {
        inputLogin("login2");
        inputPassword("password2");
        pressButton();
        onView(isRoot()).perform(waitDisplayed(appBar.getPressProfile(), 6000));
        main.checkNews();
    }

    @Step("Проверка видимости элемента с текстом Authorization")
    public void checkAuth() {
        textViewAuth.check(matches(isDisplayed()));
        textViewAuth.check(matches(withText("Authorization")));
    }

    @Step("Проверка отображения toast с текстом ошибки {text}")
    public void checkToastWithErrorMessage(String errorText, View decorView) {
        onView(withText(errorText))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));

    }

}
