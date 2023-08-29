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
    public void testTaskClickDisplaysTaskName() {
        // Wait for the RecyclerView to load data (replace with idling resources if you can)
        waitFor(2000);

        // Check if RecyclerView is displayed
        onView(withId(R.id.tasksRecyclerView)).check(matches(isDisplayed()));

        // Here we assume that the RecyclerView should have at least one item for this test to be meaningful.
        // If the RecyclerView is empty, then the below line will fail, giving us an indication that the test can't proceed.
        onView(withId(R.id.tasksRecyclerView)).check(matches(hasMinimumChildCount(1)));

        // Scroll to position 0 before clicking
        onView(withId(R.id.tasksRecyclerView)).perform(RecyclerViewActions.scrollToPosition(0));

        // Click on the item at position 0
        onView(withId(R.id.tasksRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // Assert that the task name is displayed correctly
        onView(withId(R.id.textView2)).check(matches(withText("Task1")));
    }

    // Helper function to introduce delay
    private void waitFor(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
