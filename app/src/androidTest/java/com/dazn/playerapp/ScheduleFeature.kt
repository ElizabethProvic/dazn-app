package com.dazn.playerapp

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.dazn.playerapp.di.idlingResource
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScheduleFeature : BaseUiTest() {

    @Test
    fun displayLoaderWhileFetchingEvents() {
        IdlingRegistry.getInstance().unregister(idlingResource)
        assertDisplayed(R.id.loader)
    }

    @Test
    fun navigateToSchedule() {
        displayScreenTitle("Events")
        userSeesNavbar()
        openSchedule()
        displayScreenTitle("Schedule")
    }
}
