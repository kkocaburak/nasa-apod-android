package com.bkarakoca.ui_nasa_apod.apodlisting

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bkarakoca.domain.uimodel.apod.ApodListUIModel
import com.bkarakoca.domain.uimodel.apod.ApodUIModel
import com.bkarakoca.domain.usecase.GetLocalNasaApodListUseCase
import com.bkarakoca.domain.usecase.GetNasaApodListUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.*

@ExperimentalCoroutinesApi
class FRNasaApodListingVMTest {

    @MockK
    lateinit var getNasaApodListUseCase: GetNasaApodListUseCase

    @MockK
    lateinit var getLocalNasaApodListUseCase: GetLocalNasaApodListUseCase

    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: FRNasaApodListingVM

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this, relaxUnitFun = true)

        viewModel = spyk(FRNasaApodListingVM(getNasaApodListUseCase, getLocalNasaApodListUseCase))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `when fetchNasaApodList called with response should set uimodel`(): Unit = runBlocking {
        coEvery { getNasaApodListUseCase() } coAnswers {
            flowOf(apodListUIModelExample)
        }

        viewModel.fetchNasaApodList()
        delay(200L)

        Assert.assertTrue(viewModel.apodListUIModel.value != null)
        viewModel.apodListUIModel.value?.apodListLatest?.get(0)?.let {
            Assert.assertEquals(it.date, "2015")
        }
    }

    @Test
    fun `when fetchNasaApodList called with failure should show popup`(): Unit = runBlocking {
        coEvery { getNasaApodListUseCase() } coAnswers {
            flow {
                throw Exception()
            }
        }

        viewModel.fetchNasaApodList()
        delay(200L)

        Assert.assertTrue(viewModel.popup.value != null)
    }

    @Test
    fun `when onReorderClicked with title should sort ui lists`() {
        viewModel.apodListUIModel.value = apodListUIModelExample

        viewModel.orderApodList(byTitle = true, byDate = false)

        Assert.assertTrue(viewModel.apodListUIModel.value?.apodListLatest?.get(0)?.title == "a example")
    }

    @Test
    fun `when onReorderClicked with date should sort ui lists`() {
        viewModel.apodListUIModel.value = apodListUIModelExample

        viewModel.orderApodList(byTitle = false, byDate = true)

        Assert.assertTrue(viewModel.apodListUIModel.value?.apodListLatest?.get(0)?.date == "2020")
    }

    private val apodListUIModelExample = ApodListUIModel(
        apodListLatest = arrayListOf(
            ApodUIModel(
                title = "b example",
                explanation = "example explanation",
                date = "2015",
                imageUrl = "",
                isFavorite = false
            ),
            ApodUIModel(
                title = "a example",
                explanation = "example explanation",
                date = "2020",
                imageUrl = "",
                isFavorite = false
            )
        ),
        apodListFavorites = arrayListOf()
    )

}