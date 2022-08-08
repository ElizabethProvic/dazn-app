package com.dazn.playerapp

import com.dazn.playerapp.domain.GetDataUseCase
import com.dazn.playerapp.model.ScheduleItem
import com.dazn.playerapp.ui.schedule.ScheduleContract
import com.dazn.playerapp.ui.schedule.SchedulePresenter
import com.dazn.playerapp.util.SchedulerProvider
import com.dazn.playerapp.util.TestSchedulerProvider
import com.nhaarman.mockitokotlin2.*
import io.mockk.*
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit

class SchedulePresenterTest {

    private var mockSchedulers: SchedulerProvider = TestSchedulerProvider()

    @Mock
    lateinit var useCase: GetDataUseCase

    private lateinit var mockView: ScheduleContract.View
    private lateinit var spyPresenter: ScheduleContract.Presenter

    private var testSingle: Single<List<ScheduleItem>>? = null

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)

        mockView = mockk()
        spyPresenter = spyk(SchedulePresenter(mockSchedulers, useCase))

        testSingle = Single.just(mockScheduleList)
    }

    @Test
    fun `get schedule data`() {
        val testScheduler= mockSchedulers.timer as TestScheduler

        `when`(useCase.getScheduleData()).thenReturn(testSingle)

        every { mockView.hideLoadingView() } just Runs
        every { mockView.setItems(mockScheduleList) } just Runs

        spyPresenter.getData(mockView)
        testScheduler.advanceTimeBy(60, TimeUnit.SECONDS)

        verify(exactly = 3) {
            mockView.hideLoadingView()
            mockView.setItems(mockScheduleList)
        }
    }

    @Test
    fun `get schedule data fails`() {
        `when`(useCase.getScheduleData()).thenReturn(Single.error(Throwable()))

        every { mockView.hideLoadingView() } just Runs
        every { mockView.showErrorMessage() } just Runs

        spyPresenter.getData(mockView)

        verify(exactly = 1) {
            mockView.hideLoadingView()
            mockView.showErrorMessage()
        }
    }

    companion object {
        private val mockScheduleItem = ScheduleItem("1", "title", "", mock(), "")
        val mockScheduleList = arrayListOf(mockScheduleItem)
    }
}