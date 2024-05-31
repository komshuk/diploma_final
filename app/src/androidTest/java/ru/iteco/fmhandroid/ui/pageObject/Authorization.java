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

    public void inputLogin(String login) {
        Allure.step("Ввод в поле логин " + login);
        ViewInteraction textInputEditText = onView(allOf(
                isDescendantOfA(withId(inputLogin)),
                isAssignableFrom(EditText.class)));
        textInputEditText.check(matches(isDisplayed()));
        textInputEditText.perform(replaceText(login), closeSoftKeyboard());

    }

    public void inputPassword(String password) {
        Allure.step("Ввод в поле пароль " + password);
        ViewInteraction textInputEditText3 = onView(allOf(
                isDescendantOfA(withId(inputPassword)),
                isAssignableFrom(EditText.class)));
        textInputEditText3.check(matches(isDisplayed()));
        textInputEditText3.perform(replaceText(password), closeSoftKeyboard());
    }

    public void pressButton() {
        Allure.step("Нажатие на кнопку ВОЙТИ");
        materialButton.check(matches(isDisplayed()));
        materialButton.perform(click());
    }

    public void loginSuccessful() {
        Allure.step("Успешная авторизация пользователя");
        inputLogin("login2");
        inputPassword("password2");
        pressButton();
        onView(isRoot()).perform(waitDisplayed(appBar.getPressProfile(), 6000));
        main.checkNews();
    }

    public void checkAuth() {
        Allure.step("Проверка видимости элемента с текстом 'Authorization'");
        textViewAuth.check(matches(isDisplayed()));
        textViewAuth.check(matches(withText("Authorization")));
    }

    public void checkToastWithErrorMessage(String errorText, View decorView) {
        Allure.step("Проверка отображения toast с текстом ошибки " + errorText);
        onView(withText(errorText))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }

}
