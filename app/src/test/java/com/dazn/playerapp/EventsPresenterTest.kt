package com.dazn.playerapp

import com.dazn.playerapp.api.PlayerService
import com.dazn.playerapp.domain.GetDataUseCase
import com.dazn.playerapp.ui.events.EventsContract
import com.dazn.playerapp.ui.events.EventsPresenter
import com.dazn.playerapp.model.Event
import com.dazn.playerapp.util.SchedulerProvider
import com.dazn.playerapp.util.TestSchedulerProvider
import com.nhaarman.mockitokotlin2.*
import io.mockk.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class EventsPresenterTest {

    private var mockSchedulers: SchedulerProvider = TestSchedulerProvider()

    @Mock
    lateinit var playerService: PlayerService

    @Mock
    lateinit var useCase: GetDataUseCase

    private lateinit var mockView: EventsContract.View
    private lateinit var spyPresenter: EventsContract.Presenter

    private var testSingle: Single<List<Event>>? = null

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        mockView = mockk()
        spyPresenter = spyk(EventsPresenter(mockSchedulers, useCase))

        testSingle = Single.just(mockEventList)
    }

    @Test
    fun `get events success`() {
        `when`(playerService.getEvents()).thenReturn(testSingle)
    }

    @Test
    fun `get events data`() {
        `when`(useCase.getEventsData()).thenReturn(testSingle)

        every { mockView.hideLoadingView() } just Runs
        every { mockView.setItems(mockEventList) } just Runs


        spyPresenter.getData(mockView)

        verify(exactly = 1) {
            mockView.hideLoadingView()
            mockView.setItems(mockEventList)
        }
    }

    @Test
    fun `get events data fails`() {
        `when`(useCase.getEventsData()).thenReturn(Single.error(Throwable()))

        every { mockView.hideLoadingView() } just Runs
        every { mockView.showErrorMessage() } just Runs

        spyPresenter.getData(mockView)

        verify(exactly = 1) {
            mockView.hideLoadingView()
            mockView.showErrorMessage()
        }
    }

    companion object {
        private val mockEvent = Event(mock(), "1", "", "subtitle", "title", "")
        val mockEventList = arrayListOf(mockEvent)
    }
}