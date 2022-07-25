package com.dazn.playerapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dazn.playerapp.api.PlayerService
import com.dazn.playerapp.events.domain.GetDataUseCase
import com.dazn.playerapp.events.ui.EventsContract
import com.dazn.playerapp.events.ui.EventsPresenter
import com.dazn.playerapp.model.Event
import com.nhaarman.mockitokotlin2.*
import io.mockk.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class EventsPresenterTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val schedulers = RxImmediateSchedulerRule()

    @Mock
    lateinit var playerService: PlayerService

    @Mock
    lateinit var useCase: GetDataUseCase

    private lateinit var mockView: EventsContract.View
    private lateinit var spyPresenter: EventsContract.Presenter

    private var testSingle: Single<List<Event>>? = null

    val event = Event(mock(), "1", "", "subtitle", "title", "")
    val eventList = arrayListOf(event)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        mockView = mockk()
        spyPresenter = spyk(EventsPresenter(mockView))

        testSingle = Single.just(eventList)
    }

    @Test
    fun `get events success`() {
        `when`(playerService.getEvents()).thenReturn(testSingle)
    }

    @Test
    fun `get data`() {
        `when`(useCase.getEventsData()).thenReturn(testSingle)

        every { mockView.hideLoadingView() } just Runs
        every { mockView.setItems(eventList) } just Runs


        spyPresenter.getData()

        verify(exactly = 1) {
            mockView.hideLoadingView()
            mockView.setItems(eventList)
        }
    }
}