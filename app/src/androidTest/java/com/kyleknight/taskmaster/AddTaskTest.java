package com.kyleknight.taskmaster;


import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class AddTaskTest {

    @Rule
    public IntentsTestRule<AddTasks> intentsTestRule = new IntentsTestRule<>(AddTasks.class);

    @Test
    public void testAddTaskAndVerifyOnMainPage() {
        // Enter task details
        onView(withId(R.id.TaskTitleInput)).perform(replaceText("New Task Title"));
        onView(withId(R.id.taskDescriptionInput)).perform(replaceText("Task Description"));

        // Click the "Add Task" button
        onView(withId(R.id.AddTaskActivityButton)).perform(click());

        // Verify the added task is displayed on the main page
        onView(withId(R.id.tasksRecyclerView))
                .check(matches(hasDescendant(withText("New Task Title")))); // Assuming task title is displayed in the RecyclerView item

        // Optionally, you can add more verification checks to ensure other aspects of the added task are displayed correctly
    }
}
