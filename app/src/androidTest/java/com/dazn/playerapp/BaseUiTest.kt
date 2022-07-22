package com.dazn.playerapp

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions
import com.dazn.playerapp.di.idlingResource
import com.dazn.playerapp.view.MainActivity
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseUiTest {

    val activityRule = ActivityTestRule(MainActivity::class.java)
        @Rule get

    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    fun hidesLoader() {
        BaristaVisibilityAssertions.assertNotDisplayed(R.id.loader)
    }

    fun displayScreenTitle(title: String) {
        BaristaVisibilityAssertions.assertDisplayed(title)
    }

    fun openVideoFromHome() {
        Espresso.onView(ViewMatchers.withId(R.id.events_list))
            .perform(
                RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                    ViewMatchers.withChild(ViewMatchers.withText("Liverpool v Porto")),
                    ViewActions.click()
                )
            )
    }

    fun openSchedule() {
        Espresso.onView(ViewMatchers.withId(R.id.navigation_schedule))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .perform(ViewActions.click())
    }

    fun seeFullScreenPlayerView() {
        Espresso.onView(ViewMatchers.withId(R.id.video_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun userSeesNavbar() {
        Espresso.onView(ViewMatchers.withId(R.id.nav_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }
}