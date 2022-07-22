package com.dazn.playerapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.dazn.playerapp.di.idlingResource
import org.hamcrest.Matchers.allOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EventsFeature : BaseUiTest() {

    @Test
    fun displayLoaderWhileFetchingEvents() {
        IdlingRegistry.getInstance().unregister(idlingResource)
        assertDisplayed(R.id.loader)
    }

    @Test
    fun navigateToPlayer() {
        displayScreenTitle("Events")
        displaysEvents()
        openVideoFromHome()
        seeFullScreenPlayerView()
    }

    @Test
    fun navigateToSchedule() {
        displayScreenTitle("Events")
        userSeesNavbar()
        openSchedule()
        displayScreenTitle("Schedule")
    }

    @Test
    fun displaysListOfEvents() {
        displayScreenTitle("Events")
        displaysEvents()
        hidesLoader()
    }

    fun displaysEvents() {
        assertRecyclerViewItemCount(R.id.events_list, 16)

        onView(allOf(withId(R.id.title), isDescendantOfA(nthChildOf(withId(R.id.events_list), 0))))
            .check(matches(withText("Liverpool v Porto")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.subtitle), isDescendantOfA(nthChildOf(withId(R.id.events_list), 0))))
            .check(matches(withText("UEFA Champions League")))
            .check(matches(isDisplayed()))
    }

}
