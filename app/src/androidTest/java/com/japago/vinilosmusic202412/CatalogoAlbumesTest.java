package com.japago.vinilosmusic202412;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.matcher.BundleMatchers.hasEntry;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import android.app.Activity;
import android.service.autofill.Validator;
import android.support.test.espresso.proto.matcher.RootMatchers;
import android.view.View;

import androidx.annotation.ContentView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class CatalogoAlbumesTest {

    @Rule
    public ActivityScenarioRule<CatalogoAlbumes> activityRule = new ActivityScenarioRule<>(CatalogoAlbumes.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void tearDown() {
        Intents.release();
    }

    @Test
    public void testElementsVisibility() {
        // Verifica que los elementos visibles estén presentes en la pantalla
        onView(withId(R.id.btnRegresarCatalogoAlbumes)).check(matches(isDisplayed()));
        onView(withId(R.id.imgCabecera)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerViewAlbumes)).check(matches(isDisplayed()));
    }

    //**Pruebe la funcionalidad del botón**: Esta prueba garantizará que la actividad MainActivity se lance cuando se haga clic en el botón.
    @Test
    public void testButtonFunctionality() {
        // Lanza la actividad
        ActivityScenario.launch(CatalogoAlbumes.class);

        // Realiza un click en el botón
        onView(withId(R.id.btnRegresarCatalogoAlbumes)).perform(click());

        // Verifica que la actividad MainActivity se ha lanzado
        Intents.intended(IntentMatchers.hasComponent(MainActivity.class.getName()));
    }

    //**Probar el recuento de elementos de RecyclerView**: esta prueba garantizará que RecyclerView esté lleno con la cantidad correcta de elementos.
    @Test
    public void testRecyclerViewItemCount() {
        // Lanza la actividad
        try (ActivityScenario<CatalogoAlbumes> scenario = ActivityScenario.launch(CatalogoAlbumes.class)) {
            // Espera a que los datos se carguen en el RecyclerView
            Thread.sleep(2000); // Asegúrate de manejar esta excepción

            // Verifica que el RecyclerView tenga el número correcto de elementos
            onView(withId(R.id.recyclerViewAlbumes))
                    .check(new RecyclerViewItemCountAssertion(15)); // Reemplazar con el número esperado de elementos
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //**Pruebe la funcionalidad del clic en el RecyclerView**: Esta prueba simulará un clic en un elemento del RecyclerView y verificará que se realice la acción esperada.
    @Test
    public void testRecyclerViewItemClick() {
        // Lanza la actividad
        try (ActivityScenario<CatalogoAlbumes> scenario = ActivityScenario.launch(CatalogoAlbumes.class)) {
            // Espera a que los datos se carguen en el RecyclerView
            Thread.sleep(2000); // Asegúrate de manejar esta excepción

            // Simula un click en el primer elemento del RecyclerView
            onView(withId(R.id.recyclerViewAlbumes))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

            // Verifica el comportamiento esperado
            // Esto dependerá de lo que se supone que debe suceder cuando se hace clic en un elemento
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //**Pruebe la funcionalidad del botón Atrás**: Esta prueba simulará un clic en el botón Atrás y verificará que regrese a la actividad anterior.
    @Test
    public void testBackButtonFunctionality() {
        // Lanza la actividad MainActivity
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {

            onView(withId(R.id.cvAlbumes)).perform(click());

            // Simula un click en el botón de regreso
            Espresso.pressBack();

            // Verifica que la actividad MainActivity se ha lanzado
            Intents.intended(IntentMatchers.hasComponent(MainActivity.class.getName()));
        }
    }

    @Test
    public void testGuestCreateAlbum()
    {
        //Vamos a la pestaña Login
        try (ActivityScenario<Login> scenario = ActivityScenario.launch(Login.class)) {
            //En la vista Login ubicamos el accceso como invitado
            onView(withId(R.id.Usuario2)).perform(click());

            //Verificamos la presencia del botón crear Album
            withId(R.id.btnCrearAlbum).matches(isNotClickable());
        }
    }

    @Test
    public void testCreateNullAlbum()
    {
        //Vamos a la pestaña Login
        try (ActivityScenario<Login> scenario = ActivityScenario.launch(Login.class)) {
            //En la vista Login ubicamos el accceso como coleccionista
            onView(withId(R.id.Usuario1)).perform(click());

            //Verificamos la presencia del botón crear Album
            withId(R.id.btnCrearAlbum).matches(isClickable());
            onView(withId(R.id.btnCrearAlbum)).perform(click());

            //Intentar guardar
            withId(R.id.submitButton).matches(isClickable());
            onView(withId(R.id.submitButton)).perform(click());
            withId(R.id.submitButton).matches(isClickable());
        }
    }

    /*@Test
    public void testCreateBorderAlbum()
    {
        //Vamos a la pestaña Login
        try (ActivityScenario<Login> scenario = ActivityScenario.launch(Login.class)) {
            //En la vista Login ubicamos el accceso como coleccionista
            onView(withId(R.id.Usuario1)).perform(click());

            //Verificamos la presencia del botón crear Album
            withId(R.id.btnCrearAlbum).matches(isClickable());
            onView(withId(R.id.btnCrearAlbum)).perform(click());

            //Verificamos la presencia del botón guardar
            withId(R.id.submitButton).matches(isClickable());

            //Diligenciamos  el formulario
            onView(withId(R.id.idName)).perform(ViewActions.typeText("Black Album"));
            onView(withId(R.id.idCover)).perform(ViewActions.typeText("https://www.youtube.com/watch?v=DqDeH3hwxfw"));
            //onView(withId(R.id.idReleaseDate)).perform(ViewActions.click());
            //onView(withId(R.id.idReleaseDate)).perform(PickerActions.setDate(1991, 8, 12));

            onView(withId(R.id.idGenre)).perform(click());
            //onData(allOf(is(instanceOf(String.class)), is("Rock"))).perform(click());

            //onView(withId(R.id.idRecord)).perform(ViewActions.typeText("Sony Music"));
            //onView(withId(R.id.idDesc)).perform(ViewActions.typeText("Metallica, es el quinto álbum de estudio del grupo musical estadounidense de metal Metallica. Fue el primer álbum de estudio de Metallica producido por Bob Rock"));

            onView(withId(R.id.submitButton)).perform(click());
            withId(R.id.submitButton).matches(isNotClickable());
        }
    }*/
}