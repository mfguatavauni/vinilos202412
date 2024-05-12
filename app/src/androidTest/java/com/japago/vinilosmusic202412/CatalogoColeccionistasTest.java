package com.japago.vinilosmusic202412;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class CatalogoColeccionistasTest {

    @Rule
    public ActivityScenarioRule<CatalogoColeccionistas> activityRule =
            new ActivityScenarioRule<>(CatalogoColeccionistas.class);

    @Test
    public void testRecyclerViewExists() {
        Espresso.onView(ViewMatchers.withId(R.id.collector_item_rv))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testButtonExistsAndClickNavigatesToMainActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.btnRegresarCatalogoAlbumes))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.main)) // Reemplaza esto con un ID de vista en MainActivity
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}