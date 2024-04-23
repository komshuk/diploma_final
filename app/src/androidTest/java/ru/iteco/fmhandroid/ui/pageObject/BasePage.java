package ru.iteco.fmhandroid.ui.pageObject;

import android.os.IBinder;
import android.view.WindowManager;

import androidx.test.espresso.Root;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;

import io.qameta.allure.android.rules.ScreenshotRule;
import ru.iteco.fmhandroid.ui.AppActivity;

public class BasePage {

    @Rule
    public ScreenshotRule logcatRule = new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "failure");
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    public static class ToastMatcher extends TypeSafeMatcher<Root> {
        @Override
        public void describeTo(Description description) {
            description.appendText("is toast");
        }

        @Override
        public boolean matchesSafely(Root root) {
            int type = root.getWindowLayoutParams().get().type;
            if ((type == WindowManager.LayoutParams.TYPE_TOAST)) {
                IBinder windowToken = root.getDecorView().getWindowToken();
                IBinder appToken = root.getDecorView().getApplicationWindowToken();
                if (windowToken == appToken) {
                    //means this window isn't contained by any other windows.
                    return true;
                }
            }
            return false;
        }
    }



}
