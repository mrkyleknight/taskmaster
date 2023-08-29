package com.kyleknight.taskmaster;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserProfileActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testImportantUIElements() {
        onView(withId(R.id.all_tasks_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testEditUsernameAndVerify() {
        // Navigate to UserProfileActivity
        onView(withId(R.id.MainActivitySettingsButton)).perform(click());

        // Clear existing text in the EditText
        onView(withId(R.id.UserProfileActivityInputText)).perform(clearText());

        // Type new username into the EditText with the correct ID
        onView(withId(R.id.UserProfileActivityInputText)).perform(typeText("NewUsername"));

        // Click save button with the correct ID
        onView(withId(R.id.UserProfileActivitySaveButton)).perform(click());

        // Go back to MainActivity
        onView(withContentDescription("Navigate up")).perform(click());

        // Assert that the new username is displayed correctly in MainActivity
        onView(withId(R.id.usernameDisplay)).check(matches(withText("NewUsername’s tasks")));
    }


    @Test
    public void testEditUsernameAndCheckHomepage() {
        // Navigate to UserProfileActivity
        onView(withId(R.id.MainActivitySettingsButton)).perform(click());

        // Clear existing text in the EditText
        onView(withId(R.id.UserProfileActivityInputText)).perform(clearText());

        // Type the new username into the EditText with the correct ID
        onView(withId(R.id.UserProfileActivityInputText))
                .perform(typeText("NewUsername"));

        // Click save button with the correct ID
        onView(withId(R.id.UserProfileActivitySaveButton)).perform(click());

        // Go back to MainActivity
        onView(withContentDescription("Navigate up")).perform(click());

        // Check that the new username is displayed on the homepage
        onView(withId(R.id.usernameDisplay))
                .check(matches(withText("NewUsername’s tasks")));
    }


    private void waitForNonEmptyRecyclerView(int recyclerViewId, long timeout) {
        long startTime = System.currentTimeMillis();
        long endTime = startTime + timeout;

        do {
            try {
                onView(withId(recyclerViewId)).check(matches(hasMinimumChildCount(1)));
                return;
            } catch (AssertionError e) {
                if (System.currentTimeMillis() > endTime) {
                    throw e;
                }
            }


            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // Do nothing
            }
        } while (true);
    }

}
