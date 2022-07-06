package com.pia.appetiser.test

import com.pia.appetiser.test.data.source.repository.ItunesRepository
import com.pia.appetiser.test.domain.appusecase.UpdateFeaturedMoviesUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class UpdateFeaturedMoviesUseCaseTest {

    lateinit var useCase: UpdateFeaturedMoviesUseCase

    @MockK
    lateinit var repository: ItunesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this,relaxed = true)
        useCase = UpdateFeaturedMoviesUseCase(repository)
    }

    @Test
    fun `Given_featured_movies_api_is_success_when_fetched_then_update_local_data`() {
        val value = Completable.complete()

        every { repository.updateFeaturedMovies() } returns value
        useCase()
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertComplete()
            .dispose()

        verify(exactly = 1) {repository.updateFeaturedMovies()}

    }

}