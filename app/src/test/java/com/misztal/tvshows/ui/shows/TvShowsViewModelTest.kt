package com.misztal.tvshows.ui.shows

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.misztal.tvshows.ExceptionHandler
import com.misztal.tvshows.data.ShowsRepository
import com.misztal.tvshows.network.api.model.TvShow
import com.misztal.tvshows.network.api.model.response.TvShows
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by kmisztal on 15/06/2018.
 *
 * @author Krzysztof Misztal
 */
class TvShowsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    val errorMessage = 10
    val pages = 3
    val initialState = TvShowsState(emptyList(), true, false, null)

    @Mock
    lateinit var mockShowRepository: ShowsRepository

    @Mock
    lateinit var mockExceptionHandler: ExceptionHandler
    lateinit var testScheduler: TestScheduler

    lateinit var tested: TvShowsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()

        whenever(mockExceptionHandler.getErrorMessage(any())).thenReturn(errorMessage)
        whenever(mockShowRepository.getPopularTvShows(any(), any(), any())).doAnswer {
            val page = it.arguments[0] as Int
            val shows = if (page > pages) emptyList() else generateShowList(10, page)

            Single.just(TvShows(page, 10, 2, shows))
        }

        tested = TvShowsViewModel(mockShowRepository, testScheduler, mockExceptionHandler)
    }

    @Test
    fun shouldHaveDefaultFilterSet() {
        assertEquals(TvShowsViewModel.DEFAULT_FILTER, tested.yearFilter)
    }

    @Test
    fun shouldEmitInitialState() {
        assertEquals(initialState, tested.stateData.value)
    }

    @Test
    fun shouldFetchFirstPage() {
        val shows = generateShowList(10, 1)
        testScheduler.triggerActions()

        val state = tested.stateData.value
        val expectedState = TvShowsState(shows, false, false, null)
        assertEquals(expectedState, state)
    }

    @Test
    fun shouldFetchSecondPage() {
        val firstPage = generateShowList(10, 1)
        val secondPage = generateShowList(10, 2)

        tested.fetchNextPage()
        testScheduler.triggerActions()
        tested.fetchNextPage()
        testScheduler.triggerActions()

        val state = tested.stateData.value
        val expectedState = TvShowsState(firstPage + secondPage, false, false, null)
        assertEquals(expectedState, state)
    }

    private fun generateShowList(count: Int, page: Int): List<TvShow> {
        if (count <= 0) {
            return emptyList();
        }
        val show = TvShow(1, null, null, 1.0f, 1.0f, 5, "2017-02-01", "Show", "Show", "Blabla")
        val list = ArrayList<TvShow>()

        for (i in 0 until count) {
            list.add(show.copy(id = count * page + i))
        }

        return list
    }

}