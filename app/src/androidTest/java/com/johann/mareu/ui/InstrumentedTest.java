package com.johann.mareu.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.johann.mareu.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void instrumentedTest() {
        ViewInteraction textView = onView(
                allOf(withId(R.id.meetingRoom), withText("Salle Peach"),
                        withParent(withParent(withId(R.id.recyclerview))),
                        isDisplayed()));
        textView.check(matches(withText("Salle Peach")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.meetingRoom), withText("Salle Wario"),
                        withParent(withParent(withId(R.id.recyclerview))),
                        isDisplayed()));
        textView2.check(matches(withText("Salle Wario")));
    }




}
