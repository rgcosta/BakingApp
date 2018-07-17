package com.example.romul.bakingapp;

import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isAbove;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;

@RunWith(AndroidJUnit4.class)
public class RecipesActivityScreenTest {

    public static final String INGREDIENTS_LABEL = "Ingredients List:";
    public static final String FIRST_DEFAULT_STEP_LABEL = "Recipe Introduction";
    private static final String FIRST_DEFAULT_RECIPE = "Nutella Pie";

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkFirstDefaultRecipe() {
        onView(withText(FIRST_DEFAULT_RECIPE)).check(matches(isDisplayed()));
    }

    @Test
    public void clickRecyclerViewItem_OpensStepsActivity(){

        onView(withRecyclerView(R.id.rv_recipes).atPosition(0)).perform(click());

        onView(withId(R.id.tv_ingredient_list_label)).check(matches(withText(INGREDIENTS_LABEL)));
        onView(withRecyclerView(R.id.rv_steps).atPosition(0)).check(matches(hasDescendant(withText(FIRST_DEFAULT_STEP_LABEL))));
    }


}
