package com.pia.appetiser.test.domain.appusecase


import com.pia.appetiser.test.data.entity.mapper.toDisplayableItunesDetails
import com.pia.appetiser.test.data.source.repository.ItunesRepository
import com.pia.appetiser.test.presentation.model.DisplayableItunesDetails
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class LoadTopMusicUseCase @Inject constructor(
    private val itunesRepository: ItunesRepository
)  {
    operator fun invoke() : Single<List<DisplayableItunesDetails>>  =
        itunesRepository
            .getTopMusic()
            .subscribeOn(Schedulers.io())
            .map { it.map { it.toDisplayableItunesDetails() } }
}