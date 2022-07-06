package com.pia.appetiser.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pia.appetiser.test.data.source.repository.ItunesRepository
import com.pia.appetiser.test.domain.appusecase.LoadFeaturedMoviesUseCase
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


//@RunWith(AndroidJUnit4::class)
//class UseCaseTest {
//
////    @get:Rule
////    val instantExecutorRule = InstantTaskExectuorRule()
////
////    @get:Rule
////    val activityRule = ActivityScenarioRule(Test)
//
//    lateinit var useCase: LoadFeaturedMoviesUseCase
//
//    @MockK
//    lateinit var repository: ItunesRepository
//
//    @Before
//    fun setUp() {
//        MockKAnnotations.init(this,relaxed = true)
//        useCase = LoadFeaturedMoviesUseCase(repository)
//    }
//
//    @Test
//    fun `Given_featured_movies_are_not_null_when_fetched_then_results_has_data`() {
//        useCase.invoke()
//        verify(exactly = 1) {repository.getFeaturedMovies()}
//
//    }



//}