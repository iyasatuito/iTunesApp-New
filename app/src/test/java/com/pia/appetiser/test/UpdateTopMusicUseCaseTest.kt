package com.pia.appetiser.test

import com.pia.appetiser.test.data.source.repository.ItunesRepository
import com.pia.appetiser.test.domain.appusecase.UpdateTopMusicUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class UpdateTopMusicUseCaseTest {
    lateinit var useCase: UpdateTopMusicUseCase

    @MockK
    lateinit var repository: ItunesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this,relaxed = true)
        useCase = UpdateTopMusicUseCase(repository)
    }

    @Test
    fun `Given_top_music_api_is_success_when_fetched_then_update_local_data`() {
        val value = Completable.complete()

        every { repository.updateTopMusic() } returns value
        useCase()
            .test()
            .awaitDone(3, TimeUnit.SECONDS)
            .assertComplete()
            .dispose()

        verify(exactly = 1) {repository.updateTopMusic()}

    }
}