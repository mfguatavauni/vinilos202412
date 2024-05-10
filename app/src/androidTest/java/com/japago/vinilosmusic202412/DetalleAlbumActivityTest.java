package com.japago.vinilosmusic202412;

import android.support.test.espresso.action.ViewActions;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class DetalleAlbumActivityTest {

    @Rule
    public ActivityScenarioRule<DetalleAlbumActivity> activityRule =
            new ActivityScenarioRule<>(DetalleAlbumActivity.class);

    @Test
    public void test_isActivityInView() {
        Espresso.onView(ViewMatchers.withId(R.id.albumCover)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.albumName)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.albumGenre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.albumDescription)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.btnRegresarDetalleAlbumes)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void test_albumDetailsAreDisplayedCorrectly() {
        String expectedAlbumName = "Expected Album Name";
        String expectedAlbumGenre = "Expected Album Genre";
        String expectedAlbumDescription = "Expected Album Description";

        Espresso.onView(ViewMatchers.withId(R.id.albumName)).check(ViewAssertions.matches(ViewMatchers.withText(expectedAlbumName)));
        Espresso.onView(ViewMatchers.withId(R.id.albumGenre)).check(ViewAssertions.matches(ViewMatchers.withText(expectedAlbumGenre)));
        Espresso.onView(ViewMatchers.withId(R.id.albumDescription)).check(ViewAssertions.matches(ViewMatchers.withText(expectedAlbumDescription)));
    }
}