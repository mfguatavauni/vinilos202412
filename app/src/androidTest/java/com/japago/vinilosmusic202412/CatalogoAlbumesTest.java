package com.japago.vinilosmusic202412;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.espresso.contrib.RecyclerViewActions;

import org.junit.After;
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
        Espresso.onView(ViewMatchers.withId(R.id.btnRegresarCatalogoAlbumes)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.imgCabecera)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewAlbumes)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    //**Pruebe la funcionalidad del botón**: Esta prueba garantizará que la actividad MainActivity se lance cuando se haga clic en el botón.
    @Test
    public void testButtonFunctionality() {
        // Lanza la actividad
        ActivityScenario.launch(CatalogoAlbumes.class);

        // Realiza un click en el botón
        Espresso.onView(ViewMatchers.withId(R.id.btnRegresarCatalogoAlbumes)).perform(ViewActions.click());

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
            Espresso.onView(ViewMatchers.withId(R.id.recyclerViewAlbumes))
                    .check(new RecyclerViewItemCountAssertion(11)); // Reemplazar con el número esperado de elementos
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
            Espresso.onView(ViewMatchers.withId(R.id.recyclerViewAlbumes))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

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

            Espresso.onView(ViewMatchers.withId(R.id.cvAlbumes)).perform(ViewActions.click());

            // Simula un click en el botón de regreso
            Espresso.pressBack();

            // Verifica que la actividad MainActivity se ha lanzado
            Intents.intended(IntentMatchers.hasComponent(MainActivity.class.getName()));
        }
    }

}