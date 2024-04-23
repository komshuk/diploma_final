package ru.iteco.fmhandroid.ui.pageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasData;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Intent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.Intents;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class AboutApp {

    private final ViewInteraction privacyPolicy = onView(withId(R.id.about_privacy_policy_value_text_view));
    private final ViewInteraction termsOfUse = onView(withId(R.id.about_terms_of_use_value_text_view));

    private final int buttonBack = R.id.about_back_image_button;
    public int getButtonBack() {
        return buttonBack;
    }

    @Step("Проверка Intent пользовательского соглашения с url {url}")
    public void intentTermOfUse(String url) {
        Intents.init();
        termsOfUse.perform(click());
        intended(hasAction(Intent.ACTION_VIEW));
        intended(hasData(url));
        Intents.release();
    }

    @Step("Проверка Intent политики конфиденциальности с url {url}")
    public void intentPrivatePolicy(String url) {
        Intents.init();
        privacyPolicy.perform(click());
        intended(hasAction(Intent.ACTION_VIEW));
        intended(hasData(url));
        Intents.release();
    }

    @Step("Нажатие на кнопку Назад")
    public void back() {
        onView(withId(buttonBack)).perform(click());
    }

}


