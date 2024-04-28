package com.japago.vinilosmusic202412;

import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.contrib.PickerActions;

import static org.hamcrest.core.IsAnything.anything;

import android.support.test.InstrumentationRegistry;
import android.widget.DatePicker;

import com.japago.vinilosmusic202412.CrearAlbum;
import com.japago.vinilosmusic202412.R;

@RunWith(AndroidJUnit4.class)
public class CrearAlbumTest {

    @Rule
    public ActivityTestRule<CrearAlbum> activityRule = new ActivityTestRule<>(CrearAlbum.class);

    @Test
    public void checkInputFieldsAndButtons() {
        // Verificar que los campos de entrada y los botones están presentes
        onView(withId(R.id.idName)).check(matches(isDisplayed()));
        onView(withId(R.id.idCover)).check(matches(isDisplayed()));
        onView(withId(R.id.idReleaseDate)).check(matches(isDisplayed()));
        onView(withId(R.id.idGenre)).check(matches(isDisplayed()));
        onView(withId(R.id.idRecord)).check(matches(isDisplayed()));
        onView(withId(R.id.idDesc)).check(matches(isDisplayed()));
        onView(withId(R.id.submitButton)).check(matches(isDisplayed()));
        onView(withId(R.id.cmdRetornar)).check(matches(isDisplayed()));
    }

    @Test
    public void checkButtonFunctionality() {
        // Open the DatePicker dialog
        onView(withId(R.id.idReleaseDate)).perform(click());

        // Continue with the rest of your test
        onView(withId(R.id.idGenre)).perform(click());
        onData(anything()).atPosition(0).perform(click());

        onView(withId(R.id.idRecord)).perform(typeText("Sony Music"));
        onView(withId(R.id.idDesc)).perform(typeText("Test Description"));

        // Press the "Accept" button on the screen
        onView(withId(R.id.submitButton)).perform(click());
    }

    @Test
    public void checkInputFieldsAreEmptyAtStart() {
        // Verificar que los campos de entrada están vacíos al inicio
        onView(withId(R.id.idName)).check(matches(withText("")));
        onView(withId(R.id.idCover)).check(matches(withText("")));
        onView(withId(R.id.idReleaseDate)).check(matches(withText("")));
        onView(withId(R.id.idGenre)).check(matches(withText("")));
        onView(withId(R.id.idRecord)).check(matches(withText("")));
        onView(withId(R.id.idDesc)).check(matches(withText("")));
    }

    @Test
    public void checkErrorMessageWhenSubmittingEmptyForm() {
        // Intentar enviar el formulario con campos vacíos
        onView(withId(R.id.submitButton)).perform(click());

        // Verificar que se muestra un mensaje de error
        onView(withText("Validar:\n• Name\n• Cover\n• Release Date\n• Genre\n• Record\n• Description")).check(matches(isDisplayed()));
    }

    @Test
    public void checkSuccessMessageWhenSubmittingFilledForm() {
        // Llenar todos los campos del formulario
        onView(withId(R.id.idName)).perform(typeText("Test Album"));
        onView(withId(R.id.idCover)).perform(typeText("http://example.com/cover.jpg"));
        onView(withId(R.id.idReleaseDate)).perform(typeText("01/01/2022"));
        onView(withId(R.id.idGenre)).perform(typeText("Rock"));
        onView(withId(R.id.idRecord)).perform(typeText("Sony Music"));
        onView(withId(R.id.idDesc)).perform(typeText("Test Description"));

        // Enviar el formulario
        onView(withId(R.id.submitButton)).perform(click());

        // Verificar que se muestra un mensaje de éxito
        onView(withText("Registrado correctamente")).check(matches(isDisplayed()));
    }
}